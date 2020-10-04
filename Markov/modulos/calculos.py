import math
import numpy as np
from filecmp import cmp

from pip._vendor.distlib.compat import raw_input


def calculoEntropiaMarkov(vectorV, matriz):
    tamanio = len(vectorV)
    total = 0
    for j in range(tamanio):
        sumFilas = 0
        for i in range(tamanio):
            probAct = matriz[i][j]
            if(probAct != 0):
                sumFilas += probAct * math.log(1/probAct,2)
        total += sumFilas * vectorV[j]
    return total

#vector = [1/3,0.5,1/6];
#matriz = [[0.5,1/3,0],[0.5,1/3,1],[0,1/3,0]];
#print(calculoEntropiaMarkov(vector, matriz));

def matrizIdentidad(n):
    return np.identity(n)

def calculoV(matriz,n):
    identidad = matrizIdentidad(n)
    matrizNueva = matriz - identidad
    b = []
    for j in range(n):
        if (j==n-1):
            b.append(1)
        else:
            b.append(0)
        matrizNueva[n-1][j] = 1
    A = np.array(matrizNueva)
    b = np.array(b)
    x = np.linalg.solve(A,b)
    return x

#matriz = [[0.371,0.0389,0.615,0.17],[0.375,0.0816,0.122,0.209],[0.252,0.85,0.199,0.324],[0.002,0.0295,0.064,0.297]]
#matriz2 = [[1/2,1/3,0],[1/2,1/3,1],[0,1/3,0]]

#calculoV(matriz,4)
