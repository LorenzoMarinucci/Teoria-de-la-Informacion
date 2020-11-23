package compresor;

import java.util.HashMap;
import java.util.Set;

public class ComprimirYDescomprimir {

	public static String comprimir(String mensaje, HashMap<Character,String> alfabetoCodigo) {
		String codificacion = "";
		Character simboloActual;
		String valor;
		int longitud = mensaje.length();
		mensaje = mensaje.toLowerCase();
	    for(int i=0;i<longitud;i++) {
	        simboloActual = mensaje.charAt(i);
	        valor = alfabetoCodigo.get(simboloActual);
	        codificacion += valor;
	    }
	    return codificacion;
    }

    public static String descomprimir(String mensaje, HashMap<Character,String> alfabetoCodigo) {
    	String simbolos = "";
		 String decodificacion = "";
		 int longitud = mensaje.length();
		 HashMap<String,Character> invertido = invertir(alfabetoCodigo);
		 for (int i = 0; i < longitud ; i++) {
		        simbolos += mensaje.charAt(i);
		        if(invertido.containsKey(simbolos)) {
		            decodificacion += invertido.get(simbolos);
		            simbolos = "";
		        }
		 }
		 return decodificacion;
    }
	
    private static HashMap<String, Character> invertir(HashMap<Character, String> alfabetoCodigo) {
		HashMap<String,Character> invertido = new HashMap<String,Character>();
		Set<Character> claves = alfabetoCodigo.keySet();
		for(Character clave:claves) 
			invertido.put(alfabetoCodigo.get(clave), clave);
		return invertido;
	}
}
