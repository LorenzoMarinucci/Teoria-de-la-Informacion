import math
import numpy as np

def calculoEntropiaMarkov(vectorV, matriz):
    '''
    Teniendo los valores para el vector de estado estacionario y la matriz de transición, se procede a
    calcular la entropía de la fuente de tipo Markov calculando la entropía para cada elemento en cada fila
    de la matriz de transición, luego realizando el producto de este valor con su correspondiente en el
    vector de estado estacionario, y por último sumando todos los valores resultantes de los cálculos
    mencionados anteriormente

    Parámetros:

    vectorV: lista que contiene los valores para las componentes del vector de estado estacionario V*
    matriz: lista de listas que simula la matriz de transición correspondiente a la fuente

    Retorna: el valor para la entropía de la fuente
    '''
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

def calculoV(matriz):
    '''
    Teniendo la matriz de transición para la fuente, se procede a calcular los valores para las componentes
    del vector de estado estacionario V* calculando (M-I).V*=0, reemplazando la última fila de la matriz
    ampliada correspondiente al sistema de ecuaciones resultante por la ecuación correspondiente a la sumatoria
    de las componentes de V* igualada a 1. Finalmente resuelve un sistema de ecuaciones compatible determinado.

    Parámetros:

    matriz: lista de listas que simula la matriz de transición correspondiente a la fuente

    Retorna: el vector de estado estacionario V*
    '''
    n = len(matriz)
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
