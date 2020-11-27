package compresor;

import java.util.HashMap;
import java.util.Set;

public class Huffman extends Compresor{

    public static HashMap<Character,String> generarAlfabetoCodigo(HashMap<Character,Float> probabilidades){
		if(probabilidades.size() == 2) {
	    	Float minimaProb = (float) 2;
	    	Float maximaProb = (float) -1;
	    	Character minimoChar = null;
	    	Character maximoChar = null;
	    	HashMap<Character,String> codificacionH = new HashMap<Character,String>();
	    	Set<Character> claves = probabilidades.keySet();
	    	for(Character clave:claves) {
	    		Float probabilidad = probabilidades.get(clave);
	    		if(probabilidad < minimaProb) {
	    			minimaProb = probabilidad;
	    			minimoChar = clave;
	    		}
	    		if(probabilidad > maximaProb) {
	    			maximaProb = probabilidad;
	    			maximoChar = clave;
	    		}
	    	}
	    	codificacionH.put(minimoChar, "0");
	    	codificacionH.put(maximoChar, "1");
	    	return codificacionH;
	    }
	    else if(probabilidades.size() == 1) {
	    	HashMap<Character,String> codificacionH = new HashMap<Character,String>();
			Set<Character> claves = probabilidades.keySet();
			for(Character clave:claves) 
				codificacionH.put(clave, "0");
			return codificacionH;
	    }
	    else {
	        HashMap<Character,Float> copiaProbabilidades = (HashMap<Character,Float>)probabilidades.clone();
	        Character minimo1 = getClaveMinimo(copiaProbabilidades);
	        Float suma = copiaProbabilidades.get(minimo1);
	        copiaProbabilidades.remove(minimo1);
	        Character minimo2 = getClaveMinimo(copiaProbabilidades);
	        suma += copiaProbabilidades.get(minimo2);
	        copiaProbabilidades.remove(minimo2);
	        copiaProbabilidades.put(minimo1, suma);    
	        HashMap<Character,String> codificacionH = generarAlfabetoCodigo(copiaProbabilidades);
	        String nuevoCodigoLista = codificacionH.get(minimo1);
	        codificacionH.remove(minimo1);
	        codificacionH.put(minimo1, nuevoCodigoLista+"0");
	        codificacionH.put(minimo2, nuevoCodigoLista+"1");
	        return codificacionH;
	    }
	}

    private static Character getClaveMinimo(HashMap<Character,Float> probabilidades) {
    	Float minimaProb = (float) 1,probabilidad;
		Character minimoChar = null;
		Set<Character> claves = probabilidades.keySet();
		for(Character clave:claves) {
			probabilidad = probabilidades.get(clave);
			if(probabilidad <= minimaProb) {
				minimaProb = probabilidad;
				minimoChar = clave;
			}
		}
		return minimoChar;
    }
}
