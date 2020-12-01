package calculos;

import java.util.ArrayList;
import java.util.HashMap;

public class Calcular {

    public static float entropia(HashMap<Character,Float> probabilidades){
        float e = 0;
        for(float p: probabilidades.values()){
            e += p*Math.log(1./p)/Math.log(2);
        }
        return e;
    }

    public static float longitudMedia(HashMap<Character,Float> probabilidades, HashMap<Character,String> codigos){
        float l = 0;
        for(char simbolo: probabilidades.keySet()){
            l += probabilidades.get(simbolo)*(codigos.get(simbolo).length());
        }
        return l;
    }

    public static float rendimiento(float entropia, float longitudMedia){
        return (entropia/longitudMedia);
    }

    public static float redundancia(float entropia, float longitudMedia){
        return ((longitudMedia-entropia)/longitudMedia);
    }

    public static String tasa_compresion(String mensajeOriginal, String mensajeComprimido){
        Float N = (float) ((float) bytesString(mensajeOriginal) / Math.ceil(mensajeComprimido.length()/8.));
        return String.format("%.2f",N) + ":1";
    }

    public static String tasa_compresionRLC(String mensajeOriginal, String mensajeComprimido){
        Float N = ( (float) bytesString(mensajeOriginal)) / bytesRLC(mensajeComprimido);
        return String.format("%.3f",N) + ":1";
    }

    private static int bytesRLC(String mensajeComprimido){
        String aux = mensajeComprimido;
        Character simbolo;
        Integer cantidad, bytes = 0;
        while (!aux.isEmpty()) {
            simbolo = aux.charAt(0);
            aux = aux.substring(2);
            cantidad = Integer.parseInt(aux.split(" ")[0]);
            aux = aux.substring(cantidad.toString().length());
            bytes += simbolo.toString().getBytes().length;
            bytes += Integer.BYTES;
            if (!aux.isEmpty())
                aux = aux.substring(1);
        }
        return bytes;
    }

    public static int bytesString(String mensaje){
        char[] caracteres = mensaje.toCharArray();
        int n = 0;
        for (Character caracter: caracteres){
            n += caracter.toString().getBytes().length;
        }
        return n;
    }
}
