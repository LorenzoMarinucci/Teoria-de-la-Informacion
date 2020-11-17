from random import random

def generar_fuente_simulada(fuenteOriginal, n):
    """Dada una distribución de probabilidades guardada en un diccionario, generar una secuencia de
    longitud n y construir una nueva distribución.
    
    Parámetros:
    
    fuenteOriginal: diccionario con las probabilidades de cada símbolo.
    n: longitud de la secuencia.
    
    Retorna: diccionario con las probabilidades de cada símbolo, en base a la simulación."""

    secuencia = _simular_secuencia(fuenteOriginal, n)
    fuenteSimulada = _generar_distribucion(secuencia, fuenteOriginal.keys())
    return fuenteSimulada

def _simular_secuencia(simbolos, n):
    """En base a la longitud n de la secuencia, generar la misma en base
    a las probabilidades de cada símbolo.
    
    Parámetros:
    
    simbolos: diccionario con la distribución de probabilidades de la fuente original.
    n: longitud de la secuencia a generar.
    
    Retorna: cadena de caracteres simulada."""

    secuencia = ""

    for i in range(n):
        sumaIntervalo = 0
        numeroAletorio = random()
        for simbolo in simbolos.keys():
            sumaIntervalo += simbolos[simbolo]
            if sumaIntervalo >= numeroAletorio:
                secuencia += simbolo
                break
    
    return secuencia

def _generar_distribucion(secuencia, simbolosOriginales):
    """De acuerdo a una secuencia determinada y los simbolos originales, retornar un diccionario con la
    distribución de probabilidades de la secuencia simulada.
    
    Parámetros:
    
    secuencia: cadena de caracteres previamente simulada.
    simbolosOriginales: diccionario con la distribución de probabilidades de la fuente original.
    
    Retorna: diccionario con la distribución de probabilidades de la fuente simulada."""

    fuenteSimulada = {}

    for simbolo in simbolosOriginales:
        fuenteSimulada[simbolo] = secuencia.count(simbolo) / len(secuencia)
    return fuenteSimulada