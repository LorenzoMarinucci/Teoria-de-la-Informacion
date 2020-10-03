#!.\Scripts\python.exe

from modulos.visualizacion import imprimir_matriz
import modulos.calculos as calculos
from modulos.lectura import leer_matriz
import numpy as np
import sys

def main():
    if (len(sys.argv) < 2):
        print("Debe incluirse el path al archivo que contiene los símbolos y la matriz.")
        return
    path = sys.argv[1]
    matrizDeTransicion, simbolos = leer_matriz(path)
    print("Matriz generada:\n")
    imprimir_matriz(matrizDeTransicion, simbolos)

if __name__ == "__main__":
    main()