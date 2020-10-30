from math import log, ceil

def entropia(probabilidadSimbolos):
    '''
    Calcula la entropía para una fuente

    Parámetros:

    probabilidadSimbolos: lista con la probabilidad de cada símbolo

    Retorna: valor de la entropía
    '''
    return sum(p * log(1/p, 2) for p in probabilidadSimbolos)

def longitudMedia(probabilidadSimbolos,codificacion):
    '''
    Calcula la longitud media de los simbolos de la fuente

    Parámetros:

    probabilidadSimbolos: diccionario con la probabilidad de cada símbolo como valor
    codificacion: diccionario con los simbolos a modo de llaves y su palabra código como valor

    Retorna: el valor de la longitud media
    '''
    return sum(probabilidadSimbolos[simbolo] * len(codificacion[simbolo]) for simbolo in codificacion.keys())

def isCompacto(probabilidadSimbolos,codificacion,r):
    '''
    Determina si un código es compacto

    Parámetros:

    probabilidadSimbolos: diccionario con la probabilidad de cada símbolo como valor
    codificacion: diccionario con los simbolos a modo de llaves y su palabra código como valor
    r: cantidad de símbolos del alfabeto código (r = 2 para un código binario)

    Retorna: True si el código es compacto, False en caso contrario
    '''
    compacto = True
    for suceso in codificacion.keys():
        alfa = log(probabilidadSimbolos[suceso]) / log(1/r)
        if (alfa <= len(codificacion[suceso]) and ceil(alfa) >= len(codificacion[suceso])) :
            compacto = False
            break
    return compacto

def cumpleKraft(codificacion):
    '''
    Determina si cumple con la inecuación de Kraft

    Parámetros:

    codificación: diccionario con los simbolos a modo de llaves y su palabra código como valor

    Retorna: True si cumple con la inecuación de Kraft, False en caso contrario
    '''
    return sum(2 ** -len(c) for c in codificacion) <= 1