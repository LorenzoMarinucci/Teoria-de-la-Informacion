from random import random

def generar_fuente_simulada(fuenteOriginal, n):
    """Dada una distribución de probabilidades guardada en un diccionario, generar una secuencia de
    longitud n y construir una nueva distribución"""

    secuencia = _simular_secuencia(fuenteOriginal, n)
    fuenteSimulada = _generar_distribucion(secuencia, fuenteOriginal.keys())
    return fuenteSimulada

def _simular_secuencia(simbolos, n):
    """En base a la longitud n de la secuencia, generar la misma en base
    a las probabilidades de cada símbolo."""

    secuencia = ""

    for i in range(n):
        sumaIntervalo = 0
        numeroAletorio = random()
        for simbolo in simbolos.keys():
            sumaIntervalo += simbolos[simbolo]
            if sumaIntervalo >= numeroAletorio: # de darse la condición, el numero aleatorio queda dentro del intervalo del símbolo
                secuencia += simbolo
                break
    
    return secuencia

def _generar_distribucion(secuencia, simbolosOriginales):
    """De acuerdo a una secuencia determinada y los simbolos originales, retornar un diccionario con la
    distribución de probabilidades de la secuencia simulada."""

    fuenteSimulada = {}

    for simbolo in simbolosOriginales:
        fuenteSimulada[simbolo] = secuencia.count(simbolo) / len(secuencia) # cada valor del diccionario contiene la probabilidad del símbolo

    return fuenteSimulada