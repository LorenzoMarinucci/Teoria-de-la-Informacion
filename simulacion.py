from random import random

def simular_secuencia(simbolos, n):
    """En base a la longitud n de la secuencia, generar la misma en base
    a las probabilidades de cada símbolo."""

    secuencia = ""

    for i in range(n):
        sumaIntervalo = 0
        numeroAletorio = random()
        for simbolo in simbolos.keys():
            sumaIntervalo += simbolos[simbolo]
            if (sumaIntervalo >= numeroAletorio): # de darse la condición, el numero aleatorio queda dentro del intervalo del símbolo
                secuencia += simbolo
                break
    
    return secuencia

def generar_fuente(secuencia):
    """De acuerdo a una secuencia determinada, retornar un diccionario con la
    distribución de probabilidades de la fuente."""

    fuente = dict.fromkeys(secuencia) # genera un diccionario cuyas llaves son los simbolos en la secuencia, sin repetir los mismos
    
    for simbolo in fuente:
        fuente[simbolo] = secuencia.count(simbolo) / len(secuencia) # cada valor del diccionario contiene la probabilidad del símbolo
    
    return fuente

def ajustar_fuente(original, simulada):
    """En caso de que uno de los simbolos de la fuente original no se haya
    generado en la simulada, asignar una probabilidad de 0 a dicho símbolo
    en el diccionario"""
    
    for simbolo in original.keys():
        if simbolo not in simulada.keys():
            simulada[simbolo] = 0