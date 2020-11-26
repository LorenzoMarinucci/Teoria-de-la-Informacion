package lectura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import calculos.Canal;

public class Lectura {
	
	public static Canal leerDatos(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String linea = br.readLine();
			String[] lineaPartida;
			int entradas = Integer.parseInt(linea.split(" ")[0]);
			int salidas = Integer.parseInt(linea.split(" ")[1]);
			lineaPartida = br.readLine().split(" ");
			double[] probabilidadEntradas = new double[entradas];
			double[][] matCanal = new double[entradas][salidas];
			for (int i = 0; i<entradas; i++) {
				probabilidadEntradas[i] = Float.parseFloat(lineaPartida[i]);
			}
			for (int i = 0; i<entradas; i++) {
				lineaPartida = br.readLine().split(" ");
				for (int j = 0; j<salidas; j++) {
					matCanal[i][j] = Float.parseFloat(lineaPartida[j]);
				}
			}
			return new Canal(matCanal,probabilidadEntradas);
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
