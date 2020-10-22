'''
Created on 22 sep. 2020

@author: GuidoGarcia
'''
import math

def cantInformacion(fuente):
    '''Dada la fuente de simbolos, se procede a calcular la cantidad de informacion aportada por
    cada simbolo.
     
    Parametros:
        Fuente:Objeto que contiene a los simbolos como claves y la distribucion de probabilidad
        como valor.
    Retorno:Fuente con la cantidad de informacion aportada por simbolo.
    '''
    cantInformacion = {}
    for simbolo in fuente:
        if (fuente[simbolo]):
            cantInformacion[simbolo] = math.log(1/fuente[simbolo], 2)
        else:
            cantInformacion[simbolo] = 0
    return cantInformacion

def entropia(cantInformacion,probabilidades):
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
    for simbolo in probabilidades:
        entropia += probabilidades[simbolo] * cantInformacion[simbolo]
    return entropia