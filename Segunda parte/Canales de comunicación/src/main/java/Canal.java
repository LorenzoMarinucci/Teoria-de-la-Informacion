import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Canal {

    public ArrayList<Simbolo> probabilidadesAPriori;
    public double[][] matrizDelCanal;

    public Canal(ArrayList<Simbolo> probabilidadesAPriori, double[][] matrizDelCanal) {
        this.probabilidadesAPriori = probabilidadesAPriori;
        this.matrizDelCanal = matrizDelCanal;
    }

    public String envio_y_recepcion(int cantidadSimbolos) {

        ArrayList<String> mensajeEnviado = enviar_mensaje(cantidadSimbolos);
        return recibir_mensaje(mensajeEnviado);


        while (it.hasNext()) {

            Double numeroAleatorio = r.nextDouble();
            Double sumatoria = 0.;


        }

    }

    private ArrayList<String> recibir_mensaje(ArrayList<String> mensajeEnviado) {

        ArrayList<String> mensajeRecibido = new ArrayList<String>();

    }

    private ArrayList<String> enviar_mensaje(int cantidadSimbolos) {

        ArrayList<String> mensaje;
        Random r = new Random();

        for (int i = 1; i <= cantidadSimbolos; i++) {

            Double numeroAleatorio = r.nextDouble();
            Double sumatoria = 0.;
            Iterator<Simbolo> it = probabilidadesAPriori.iterator();
            Simbolo actual;

            while (it.hasNext() && sumatoria < numeroAleatorio) {
                actual = it.next();
                sumatoria += actual.probabilidad;
            }

            mensaje.add(actual.simbolo);
        }

        return mensaje;
    }
}
