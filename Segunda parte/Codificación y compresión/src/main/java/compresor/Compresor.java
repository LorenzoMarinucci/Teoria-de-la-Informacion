package compresor;

import java.util.HashMap;
import java.util.Set;

public class Compresor {

	//El metodo se utiliza para comprimir tanto con la codificacion de Huffman como con la codificacion de Shannon-Fano.
	//El parametro mensaje es el mensaje que se desea comprimir.
	//El parametro alfabetoCodigo es la codificacion que se va a utilizar para comprimir el mensaje,puede ser la de Huffman o la
	//de Shannon-Fano.
	//El retorno del metodo es el mensaje comprimido.
	public static String comprimir(String mensaje, HashMap<Character,String> alfabetoCodigo) {
		String codificacion = "";
		for(Character simbolo:mensaje.toCharArray())
	        codificacion += alfabetoCodigo.get(simbolo);
	    return codificacion;
    }
	
}
