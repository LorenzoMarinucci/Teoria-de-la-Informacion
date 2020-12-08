package compresor;

public class RLC {
    
	/**
	 * Método que comprime un mensaje por el método de RLC
	 * @param mensaje: mensaje a comprimir
	 * @return mensaje comprimido
	 */
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

}
