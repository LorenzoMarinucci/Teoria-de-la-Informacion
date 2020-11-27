package compresor;

import java.util.HashMap;
import java.util.Set;

public class Compresor {

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

    private static HashMap<String, Character> invertir(HashMap<Character, String> alfabetoCodigo) {
		HashMap<String,Character> invertido = new HashMap<String,Character>();
		Set<Character> claves = alfabetoCodigo.keySet();
		for(Character clave:claves) 
			invertido.put(alfabetoCodigo.get(clave), clave);
		return invertido;
	}
}
