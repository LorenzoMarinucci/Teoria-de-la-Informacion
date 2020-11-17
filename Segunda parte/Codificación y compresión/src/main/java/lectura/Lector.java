package lectura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Lector {

    public String leerArchivo(String path){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();
            String linea = br.readLine();
            while (linea != null){
                sb.append(linea);
                linea = br.readLine();
            }
            br.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no fue encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Character,Float> generarDistribucion(String mensaje){
        HashMap<Character,Float> distribucion = new HashMap<Character, Float>();
        int N = 0;
        char[] caracteres = mensaje.toCharArray();
        for (char caracter: caracteres){
            if (distribucion.containsKey(caracter))
                distribucion.put(caracter, (float) (distribucion.get(caracter) + 1.));
            else
                distribucion.put(caracter, (float) (distribucion.get(caracter) + 1.));
            N += 1;
        }
        for (char caracter: distribucion.keySet()){
            distribucion.put(caracter,distribucion.get(caracter)/(float) N);
        }
        return distribucion;
    }

}
