package compresor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Shannon_Fano extends Compresor{
	
	/* 
	 * El metodo genera el alfabeto codigo utilizando el algoritmo de Shannon-Fano de manera recursiva.
	 */
    public static HashMap<Character,String> generarAlfabetoCodigo(HashMap<Character,Float> probabilidades){
    	//Si la cantidad de elementos del hashMap probabilidades es igual a 2 se busca el de mayor probabilidad 
    	//para asignarle un 1 y el de minima probabilidad para asignarle un 0.Luego estas asignaciones se almacenan 
    	//en un hashMap utilizando como clave el simbolo y como contenido la asignacion antes mencionada en forma de string.
    	//Por supuesto las asignaciones de 1 y 0 son absolutamente arbitrarias.
        if(probabilidades.size()==2) {
        	HashMap<Character,String> codificacionSH = new HashMap<Character,String>();
			Iterator<Character> itSimbolos = probabilidades.keySet().iterator();
			Character simbolo1 = itSimbolos.next(), simbolo2 = itSimbolos.next();
			if(probabilidades.get(simbolo1) < probabilidades.get(simbolo2)) {
				codificacionSH.put(simbolo1, "0");
	    		codificacionSH.put(simbolo2, "1");
			} else {
				codificacionSH.put(simbolo1, "1");
		    	codificacionSH.put(simbolo2, "0");
			}
			return codificacionSH;
        } 
        //Si la cantidad de elementos del hashMap probabilidades es igual a 1,directamente se le asigna al unico elemento un 1.
        else if(probabilidades.size()==1) {
        	HashMap<Character,String> codificacionSF = new HashMap<Character,String>();
			Set<Character> simbolos = probabilidades.keySet();
			for(Character simbolo:simbolos) 
				codificacionSF.put(simbolo, "1");
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
        	ArrayList<Character> simbolosOrdenados = ordenarProbabilidades(probabilidades);
        	HashMap<Character,Float> conjunto1 = generaConjunto1(probabilidades,simbolosOrdenados);
        	HashMap<Character,String> codificacion1 = generarAlfabetoCodigo(conjunto1);
        	HashMap<Character,String> codificacion2 = generarAlfabetoCodigo(generaConjunto2(conjunto1,probabilidades));
        	Set<Character> simbolos;
        	if(codificacion1.size() > 1) {
        		simbolos = codificacion1.keySet();
        		for(Character simbolo:simbolos)
        			codificacion1.replace(simbolo, "1"+codificacion1.get(simbolo));
        	}
        	if(codificacion2.size() > 1) {
        		simbolos = codificacion2.keySet();
        		for(Character simbolo:simbolos)
        			codificacion2.replace(simbolo, "0"+codificacion2.get(simbolo));
        	}
        	codificacion1.putAll(codificacion2);
        	return codificacion1;
        }
    }

    //Apartir del conjunto 1 generado anteriormente y de las probabilidades que dieron lugar a la creacion del conjunto 1,se arma
    //el conjunto 2.
    //Simplemente el metodo agrega en el conjunto 2 todos aquellos elementos del conjunto de probabilidades que no estan en el 
    //conjunto 1.
	private static HashMap<Character, Float> generaConjunto2(HashMap<Character, Float> conjunto1, HashMap<Character,Float> probabilidades) {
		HashMap<Character,Float> conjunto2 = new HashMap<Character,Float>();
		Set<Character> simbolos = probabilidades.keySet();
    	for(Character simbolo:simbolos) {
    		if(!conjunto1.containsKey(simbolo))
    			conjunto2.put(simbolo, probabilidades.get(simbolo));
    	}
		return conjunto2;
	}

	//El metodo genera el conjunto 1.
	//Los pasos que sigue para lograrlo son:
	//1:Calcular el total de la suma de las probabilidades del conjunto de probabilidades.
	//2:Se divide el total en 2 para tener una medida de cuando se deberia parar de agregar elementos al conjunto 1.
	//3:Se recorre todo el conjunto de probabilidades de manera ordenada y para cada simbolo se calculan las probabilidades acumuladas,
	//  las cuales determinan si se tiene una mejor diferencia al agregar un simbolo con su probabilidad al conjunto 1 o al conjunto 2.
	private static HashMap<Character, Float> generaConjunto1(HashMap<Character, Float> probabilidades,ArrayList<Character> simbolosOrdenados) {
		HashMap<Character,Float> conjunto1 = new HashMap<Character,Float>();
		Iterator<Character> itSimbolos = simbolosOrdenados.iterator();
		Character simbolo;
		Float suma = (float) 0;
	    Float total = (float) 0,probAcumulada1,probAcumulada2;
	    Float probabilidad;
	    boolean mejorDiferencia = true;
	    for(Character simboloOrd:simbolosOrdenados) 
	       	total += probabilidades.get(simboloOrd);
	    while(itSimbolos.hasNext() && mejorDiferencia) {
	       	simbolo = itSimbolos.next();
	       	probabilidad = probabilidades.get(simbolo);
	       	probAcumulada1 = Math.abs(suma + probabilidad);
	        probAcumulada2 = Math.abs(total - suma);
	        if(probAcumulada1 >= probAcumulada2)
	            mejorDiferencia = false;
	        suma += probabilidad;          
	        if(mejorDiferencia || conjunto1.isEmpty())
	        	conjunto1.put(simbolo, probabilidad);
	    }
		return conjunto1;
	}
	
  //El metodo ordena las probabilidades de mayor a menor y devuelve una lista de caracteres que reprensenta dicho orden. 
	public static ArrayList<Character> ordenarProbabilidades(HashMap<Character, Float> probabilidades) {
		int tamanio = probabilidades.size();
		Float maximo = (float) -1,probabilidad;
		Character maxSimbolo = null;
		ArrayList<Character> probOrdenada = new ArrayList<Character>();
		HashMap<Character, Float> copiaProbabilidades = (HashMap<Character, Float>) probabilidades.clone();
		Set<Character> simbolos;
		while(tamanio != probOrdenada.size()) {
			simbolos = copiaProbabilidades.keySet();
			for(Character simbolo:simbolos) {
				probabilidad = copiaProbabilidades.get(simbolo);
				if(probabilidad > maximo) {
					maximo = probabilidad;
					maxSimbolo = simbolo;
				}
			}
			copiaProbabilidades.remove(maxSimbolo);
			probOrdenada.add(maxSimbolo);
			maximo = (float) -1;
		}
		return probOrdenada;
	}

}
