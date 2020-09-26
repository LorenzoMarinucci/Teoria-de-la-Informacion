import sys
import calculos
import visualizacion
from lectura import generar_diccionario
from simulacion import generar_fuente_simulada

def main():
    if len(sys.argv) < 3:
        print("Se debe incluir el path al archivo de lectura y la cantidad de símbolos de la secuencia.")
        return
    path = sys.argv[1]
    n = int(sys.argv[2])

    fuenteOriginal = generar_diccionario(path)
    fuenteSimulada = generar_fuente_simulada(fuenteOriginal, n)

    visualizacion.mostrar_distribucion(fuenteOriginal,fuenteSimulada)
    
    informacionFuenteSimulada = calculos.cantInformacion(fuenteSimulada)
    informacionFuenteOriginal = calculos.cantInformacion(fuenteOriginal)
    
    visualizacion.mostrar_informacion(fuenteOriginal,informacionFuenteOriginal,informacionFuenteSimulada)

    entropiaFuenteSimulada = calculos.entropia(cantInformacion = informacionFuenteSimulada, simbolos = fuenteSimulada)
    entropiaFuenteOriginal = calculos.entropia(cantInformacion = informacionFuenteOriginal, simbolos = fuenteOriginal)
    
    visualizacion.mostrar_entropia(entropiaFuenteOriginal,entropiaFuenteSimulada)

if __name__ == "__main__":
    main()