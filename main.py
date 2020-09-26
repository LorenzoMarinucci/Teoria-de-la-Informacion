import sys
import calculos
from lectura import generar_diccionario
import simulacion

def main():
    if len(sys.argv) < 3:
        print("Se debe incluir el path al archivo de lectura y la cantidad de símbolos de la secuencia.")
        return
    path = sys.argv[1]
    n = int(sys.argv[2])
    fuenteOriginal = generar_diccionario(path)

    secuencia = simulacion.simular_secuencia(fuenteOriginal, n)
    fuenteSimulada = simulacion.generar_fuente(secuencia)
    simulacion.ajustar_fuente(original = fuenteOriginal, simulada = fuenteSimulada)

    print("Distribución de probabilidades en cada fuente\n")
    print("Símbolo   Fuente original   Fuente simulada")
    for simbolo in fuenteOriginal:
        print("{:^7}   {:^15.4f}   {:^16.4f}".format(simbolo, fuenteOriginal[simbolo], fuenteSimulada[simbolo]))
    
    informacionFuenteSimulada = calculos.cantInformacion(fuenteSimulada)
    informacionFuenteOriginal = calculos.cantInformacion(fuenteOriginal)
    print("\nInformación otorgada por cada símbolo\n")
    print("Símbolo   Fuente original   Fuente simulada")
    for simbolo in fuenteOriginal:
        print("{:^7}   {:^15.4f}   {:^16.4f}".format(simbolo, informacionFuenteOriginal[simbolo], informacionFuenteSimulada[simbolo]))

    entropiaFuenteSimulada = calculos.entropia(cantInformacion = informacionFuenteSimulada, simbolos = fuenteSimulada)
    entropiaFuenteOriginal = calculos.entropia(cantInformacion = informacionFuenteOriginal, simbolos = fuenteOriginal)
    print("\nEntropia de cada fuente\n")
    print("Fuente original   Fuente simulada\n")
    print("{:^15.4f}   {:^16.4f}".format(entropiaFuenteOriginal, entropiaFuenteSimulada))

if __name__ == "__main__":
    main()