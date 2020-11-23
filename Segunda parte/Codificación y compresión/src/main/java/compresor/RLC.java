package compresor;

public class RLC {
    
    public static String comprimir(String mensaje) {
        StringBuilder sb = new StringBuilder();
        Character actual = mensaje.charAt(0);
        Integer cont = 0;
        char[] caracteres = mensaje.toCharArray();
        for (Character caracter: caracteres){
            if (actual != caracter) {
                sb.append(actual + " " + cont.toString() + " ");
                actual = caracter;
                cont = 1;
            }
            else
                cont++;
        }
        sb.append(actual + " " + cont.toString());
        return sb.toString();
    }

    public static String descomprimir(String mensaje) {
        StringBuilder sb = new StringBuilder();
        String aux = mensaje;
        Character simbolo;
        Integer cantidad,i;
        while (!aux.isEmpty()) {
        	simbolo = aux.charAt(0);
        	aux = aux.substring(2);
        	System.out.println(aux);
        	cantidad = Integer.parseInt(aux.split(" ")[0]);
        	for (i=1;i<=cantidad;i++) {
        		sb.append(simbolo);
        	}
        	aux = aux.substring(cantidad.toString().length());
        	if (!aux.isEmpty())
        		aux = aux.substring(1);
        }
        return sb.toString();
    }
    
}
