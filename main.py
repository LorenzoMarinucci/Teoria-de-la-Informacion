import sys
from calculos import obtener_datos
from lectura import generar_diccionario
from simulacion import simular_secuencia, generar_fuente

class simbolo():
    pass

def main():
    if len(sys.argv < 3):
        print("Se debe incluir el path al archivo de lectura y la cantidad de símbolos de la secuencia.")
        return
    path = sys.argv[1]
    n = sys.argv[2]
    simbolos = generar_diccionario(path)
    secuencia = simular_secuencia(simbolos, n)
    fuenteSimulada = generar_fuente(secuencia)
    informacion, entropia = obtener_datos(simbolos, secuencia)
    print("""Cantidad de información presente en la secuencia: {} bits.
    Entropía de la fuente: {} bits.""".format(informacion, entropia))

    

if __name__ == "__main__":
    main()