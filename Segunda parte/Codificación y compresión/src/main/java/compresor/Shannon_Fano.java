package compresor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Shannon_Fano extends ComprimirYDescomprimir{

    public static HashMap<Character,String> generarAlfabetoCodigo(HashMap<Character,Float> probabilidades){
        if(probabilidades.size()==2) {
        	Float minimaProb = (float) 2;
	    	Float maximaProb = (float) -1;
	    	Character minimoChar = null;
	    	Character maximoChar = null;
	    	HashMap<Character,String> codificacionSF = new HashMap<Character,String>();
	    	Set<Character> claves = probabilidades.keySet();
	    	for(Character clave:claves) {
	    		Float probabilidad = probabilidades.get(clave);
	    		if(probabilidad < minimaProb) {
	    			minimaProb = probabilidad;
	    			minimoChar = clave;
	    		}
	    		if(probabilidad >= maximaProb) {
	    			maximaProb = probabilidad;
	    			maximoChar = clave;
	    		}
	    	}
	    	codificacionSF.put(minimoChar, "0");
	    	codificacionSF.put(maximoChar, "1");
	    	return codificacionSF;
        } 
        else if(probabilidades.size()==1) {
        	HashMap<Character,String> codificacionSF = new HashMap<Character,String>();
			Set<Character> claves = probabilidades.keySet();
			for(Character clave:claves) 
				codificacionSF.put(clave, "1");
			return codificacionSF;
        } 
        else {
        	ArrayList<Character> claveOrdenada = ordenarProbabilidades(probabilidades);
        	HashMap<Character,Float> conjunto1 = generaConjunto1(probabilidades,claveOrdenada);
        	HashMap<Character,String> mapCodigo1 = generarAlfabetoCodigo(conjunto1);
        	HashMap<Character,String> mapCodigo2 = generarAlfabetoCodigo(generaConjunto2(conjunto1,probabilidades));
        	Set<Character> claves;
        	if(mapCodigo1.size() > 1) {
        		claves = mapCodigo1.keySet();
        		for(Character clave:claves)
        			mapCodigo1.replace(clave, "1"+mapCodigo1.get(clave));
        	}
        	if(mapCodigo2.size() > 1) {
        		claves = mapCodigo2.keySet();
        		for(Character clave:claves)
        			mapCodigo2.replace(clave, "0"+mapCodigo2.get(clave));
        	}
        	mapCodigo1.putAll(mapCodigo2);
        	return mapCodigo1;
        }
    }

	private static HashMap<Character, Float> generaConjunto2(HashMap<Character, Float> conjunto1, HashMap<Character,Float> probabilidades) {
		HashMap<Character,Float> conjunto2 = new HashMap<Character,Float>();
		Set<Character> claves = probabilidades.keySet();
    	for(Character clave:claves) {
    		if(!conjunto1.containsKey(clave))
    			conjunto2.put(clave, probabilidades.get(clave));
    	}
		return conjunto2;
	}

	private static HashMap<Character, Float> generaConjunto1(HashMap<Character, Float> probabilidades,ArrayList<Character> claveOrdenada) {
		HashMap<Character,Float> conjunto1 = new HashMap<Character,Float>();
		Float suma = (float) 0;
		Float mitadTotal = (float) 0;
        Float total = (float) 0,diferencia1,diferencia2;
        Float probabilidad;
        boolean mejorDiferencia;
        for(Character clave:claveOrdenada) 
        	total += probabilidades.get(clave);
        mitadTotal = total/2;
        for(Character clave:claveOrdenada) {
        	probabilidad = probabilidades.get(clave);
        	diferencia1 = Math.abs(suma + probabilidad - mitadTotal);
            diferencia2 = Math.abs((total - suma) - mitadTotal);
            if(diferencia1 < diferencia2)
                mejorDiferencia = true;
            else
                mejorDiferencia = false;
            suma += probabilidad;          
            if(suma <= mitadTotal || mejorDiferencia || conjunto1.isEmpty())
                conjunto1.put(clave, probabilidad);
        }
		return conjunto1;
	}

	public static ArrayList<Character> ordenarProbabilidades(HashMap<Character, Float> probabilidades) {
		int tamanio = probabilidades.size();
		Float maximo = (float) -1,probabilidad;
		Character maxClave = null;
		ArrayList<Character> probOrdenada = new ArrayList<Character>();
		HashMap<Character, Float> copiaProbabilidades = (HashMap<Character, Float>) probabilidades.clone();
		Set<Character> claves;
		while(tamanio != probOrdenada.size()) {
			claves = copiaProbabilidades.keySet();
			for(Character clave:claves) {
				probabilidad = copiaProbabilidades.get(clave);
				if(probabilidad > maximo) {
					maximo = probabilidad;
					maxClave = clave;
				}
			}
			copiaProbabilidades.remove(maxClave);
			probOrdenada.add(maxClave);
			maximo = (float) -1;
		}
		return probOrdenada;
	}

}
