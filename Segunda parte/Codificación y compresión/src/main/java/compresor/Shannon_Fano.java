package compresor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Shannon_Fano extends Compresor{
	
	/* 
	 * El metodo genera el alfabeto codigo utilizando el algoritmo de Shannon-Fano de manera recursiva.
	 */
    public static HashMap<Character,String> generarAlfabetoCodigo(HashMap<Character,Float> probabilidades){
    	//Si la cantidad de elementos del hashMap probabilidades es igual a 2 se busca el de mayor probabilidad 
    	//para asignarle un 1 y el de minima probabilidad para asignarle un 0.Luego estas asignaciones se almacenan 
    	//en un hashMap utilizando como clave el simbolo y como contenido la asignacion antes mencionada en forma de string.
    	//Por supuesto las asignaciones antes mencionadas son absolutamente arbitrarias.
    	//Finalmente se retorna el hashMap antes descripto.
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
        //Si la cantidad de elementos del hashMap probabilidades es igual a 1,directamente se le asigna al unico elemento un 1.
        //Esto se almacena en un hashMap que usa de claves a los simbolos y como valores a la asignacion en forma de string.
        //Finalmente se retorna el hashMap antes descripto.
        else if(probabilidades.size()==1) {
        	HashMap<Character,String> codificacionSF = new HashMap<Character,String>();
			Set<Character> claves = probabilidades.keySet();
			for(Character clave:claves) 
				codificacionSF.put(clave, "1");
			return codificacionSF;
        } 
        //Si la cantidad de elementos es mayor a 2 entonces lo que se procede a hacer es:
        //1:Ordenar las probabilidades.
        //2:Generar el conjunto 1.
        //3:LLamar recursivamente a la funcion generarAlfabetoCodigo con el conjunto 1.
        //4:LLamar recursivamente a la funcion generarAlfabetoCodigo con el conjunto 2,el cual se genera apartir del conjunto 1.
        //5:Se verifica si la codificacion generada apartir del codigo 1 tiene mas de un elemento,de ser asi a todos los valores 
        //	de los simbolos dentro de esa codificacion se les agrega un 1 delante.
        //6:Analogo a 5 pero con la codificacion generada apartir del conjunto 2 y agregandole a los valores de los simbolos dentro
        //	de esa codificacion un 0 delante.
        //7:Por ultimo se arma un conjunto unico y se retorna.
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

    //Apartir del conjunto 1 generado anteriormente y de las probabilidades que dieron lugar a la creacion del conjunto 1,se arma
    //el conjunto 2.
    //Simplemente el metodo agrega en el conjunto 2 todos aquellos elementos del conjunto de probabilidades que no estan en el 
    //conjunto 1.
	private static HashMap<Character, Float> generaConjunto2(HashMap<Character, Float> conjunto1, HashMap<Character,Float> probabilidades) {
		HashMap<Character,Float> conjunto2 = new HashMap<Character,Float>();
		Set<Character> claves = probabilidades.keySet();
    	for(Character clave:claves) {
    		if(!conjunto1.containsKey(clave))
    			conjunto2.put(clave, probabilidades.get(clave));
    	}
		return conjunto2;
	}

	//El metodo genera el conjunto 1.
	//Los pasos que sigue para lograrlo son:
	//1:Calcular el total de la suma de las probabilidades del conjunto de probabilidades.
	//2:Se divide el total en 2 para tener una medida de cuando se deberia parar de agregar elementos al conjunto 1.
	//3:Se recorre todo el conjunto de probabilidades de manera ordenada y para cada simbolo se calculan dos diferencias,las cuales 
	//  determinan si se tiene una mejor diferencia respecto del valor medio al agregar un simbolo con su probabilidad al conjunto 
	//	1 o al conjunto 2.
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

  //El metodo ordena las probabilidades de mayor a menor y devuelve una lista de caracteres que reprensenta dicho orden. 
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
