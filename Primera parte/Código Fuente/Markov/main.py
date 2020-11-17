from modulos import calculos
from modulos.visualizacion import imprimir_matriz, imprimir_vector
from modulos.lectura import leer_matriz
import sys

def main():
    if (len(sys.argv) < 2):
        print("Debe incluirse el path al archivo que contiene los símbolos y la matriz.")
        return
    path = sys.argv[1]
    matrizDeTransicion, simbolos = leer_matriz(path)
    estadoEstacionario = calculos.calculoV(matrizDeTransicion)

    print("Matriz generada:\n")
    imprimir_matriz(matrizDeTransicion, simbolos)

    print("\nEstado estacionario:\n")
    imprimir_vector(estadoEstacionario)

    print("\nEntropia:\n")
    print(calculos.calculoEntropiaMarkov(estadoEstacionario,matrizDeTransicion))

if __name__ == "__main__":
    main()
