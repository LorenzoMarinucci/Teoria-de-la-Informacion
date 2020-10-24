import math
import numpy as np
from sympy import *
from sympy.solvers.solveset import linsolve
from filecmp import cmp

from pip._vendor.distlib.compat import raw_input


def calculoEntropiaMarkov(vectorV, matriz):
    tamanio = len(vectorV)
    total = 0
    for j in range(tamanio):
        sumFilas = 0
        for i in range(tamanio):
            probAct = matriz[i][j]
            if (probAct != 0):
                sumFilas += probAct * math.log(1 / probAct, 2)
        total += sumFilas * vectorV[j]
    return total

def calculoV(matriz, n):
    matrizNueva = matriz - np.identity(n)
    b = []
    for j in range(n):
        if j == n - 1:
            b.append(1)
        else:
            b.append(0)
        matrizNueva[n - 1][j] = 1
    A = np.array(matrizNueva)
    b = np.array(b)
    x = np.linalg.solve(A, b)
    return x
