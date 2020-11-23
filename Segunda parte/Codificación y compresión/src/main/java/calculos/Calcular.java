package calculos;

import java.util.ArrayList;
import java.util.HashMap;

public class Calcular {

    public static float entropia(ArrayList<Float> probabilidades){
        float e = 0;
        for(float p: probabilidades){
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

    public static String tasa_compresion(String mensajeOriginal, String mensajeComprimido){ //preguntar
        int N = (int) Math.floor(bytesString(mensajeOriginal) / Math.ceil(mensajeComprimido.length()/8));
        return N + ":1";
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
