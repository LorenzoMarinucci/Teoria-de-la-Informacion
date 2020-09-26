'''
Created on 22 sep. 2020

@author: GuidoGarcia
'''
import math

def cantInformacion(diccionario):
    '''Dado el diccionario de simbolos, se procede a calcular la cantidad de informacion aportada por 
    cada simbolo.
     
    Parametros:
        Diccionario:Objeto que contiene a los simbolos como claves y la distribucion de probabilidad 
        como valor.
    Retorno:Diccionario con la cantidad de informacion aportada por simbolo.
    '''
    cantInformacion = {}
    for simbolo in diccionario:
        if (diccionario[simbolo]):
            cantInformacion[simbolo] = math.log(1/diccionario[simbolo], 2)
        else:
            cantInformacion[simbolo] = 0
    return cantInformacion

def entropia(cantInformacion, simbolos):
    ''' Dada la cantidad de informacion aportada por cada elemento, almacenada en un diccionario, se procede a
        calcular la entropia realizando la sumatoria de cada producto entre la cantidad de informacion y
        probabilidad de ocurrencia de un simbolo determinado.

    Parametros:
        CantInformacion:Diccionario que contiene en cada una de sus posiciones la cantidad de informacion
        aportada por cada simbolo.
        Simbolos:Diccionario que contiene a los simbolos como claves y la distribucion de probabilidad 
        como valor.
    Retorno:Valor de la entropia.
    '''
    entropia = 0
    for simbolo in simbolos:
        entropia += simbolos[simbolo] * cantInformacion[simbolo]
    return entropia