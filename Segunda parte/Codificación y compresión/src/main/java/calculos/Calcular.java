package calculos;

import java.util.ArrayList;
import java.util.HashMap;

public class Calcular {

	/**
	 * Método que calcula la entropía de una fuente
	 * @param probabilidades: distribución de probabilidades de una fuente
	 * @return entropía de la fuente
	 */
    public static float entropia(HashMap<Character,Float> probabilidades){
        float e = 0;
        for(float p: probabilidades.values()){
            e += p*Math.log(1./p)/Math.log(2);
        }
        return e;
    }

    /**
     * Método que calcula la longitud media del código relacionado con una fuente
     * @param probabilidades: distribución de probabilidades de una fuente
     * @param codigos: códigos para los símbolos de una fuente
     * @return longitud media del código
     */
    public static float longitudMedia(HashMap<Character,Float> probabilidades, HashMap<Character,String> codigos){
        float l = 0;
        for(char simbolo: probabilidades.keySet()){
            l += probabilidades.get(simbolo)*(codigos.get(simbolo).length());
        }
        return l;
    }

    /**
     * Método que calcula el rendimiento de una codificación
     * @param entropia: entropía de una fuente
     * @param longitudMedia: longitud media de un código
     * @return rendimiento de una codificación
     */
    public static float rendimiento(float entropia, float longitudMedia){
        return (entropia/longitudMedia);
    }

    /**
     * Método que calcula la redundancia de una codificación
     * @param entropia: entropía de una fuente
     * @param longitudMedia: longitud media de un código
     * @return redundancia de una codificación
     */
    public static float redundancia(float entropia, float longitudMedia){
        return ((longitudMedia-entropia)/longitudMedia);
    }
    
    /**
     * Método que calcula la tasa de compresión para una codificación resultante de Huffman o Shannon-Fano
     * @param mensajeOriginal: mensaje sin comprimir
     * @param mensajeComprimido: mensaje comprimido por Huffman o Shannon-Fano
     * @return tasa de compresión
     */
    public static String tasa_compresion(String mensajeOriginal, String mensajeComprimido){
        Float N = (float) ((float) bytesString(mensajeOriginal) / Math.ceil(mensajeComprimido.length()/8.));
        return String.format("%.2f",N) + ":1";
    }

    /**
     * Método que calcula la tasa de compresión para una codificación resultante de RLC
     * @param mensajeOriginal: mensaje sin comprimir
     * @param mensajeComprimido: mensaje comprimido por Huffman o Shannon-Fano
     * @return tasa de compresión
     */
    public static String tasa_compresionRLC(String mensajeOriginal, String mensajeComprimido){
        Float N = ( (float) bytesString(mensajeOriginal)) / bytesRLC(mensajeComprimido);
        return String.format("%.3f",N) + ":1";
    }

    /**
     * Método que calcula los bytes que ocupa un mensaje comprimido por RLC
     * @param mensajeComprimido: mensaje comprimido por RLC
     * @return cantidad de bytes
     */
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

    /**
     * Método que calcula los bytes que ocupa un mensaje comprimido por Huffman o Shannon-Fano
     * @param mensaje: mensaje comprimido por Huffman o Shannon-Fano
     * @return cantidad de bytes
     */
    public static int bytesString(String mensaje){
        char[] caracteres = mensaje.toCharArray();
        int n = 0;
        for (Character caracter: caracteres){
            n += caracter.toString().getBytes().length;
        }
        return n;
    }
}
