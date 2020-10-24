from math import log, ceil

def entropia(probabilidadSimbolos):
    return sum(p ** log(1/p, 2) for p in probabilidadSimbolos)

def longitudMedia(probabilidadSimbolos,codificacion):
    return sum(p * len(c) for p,c in zip(probabilidadSimbolos,codificacion))

def isCompacto(probabilidadSimbolos, codificacion, r):
    compacto = True
    
    for suceso in codificacion.keys():
        alfa = log(probabilidadSimbolos[suceso]) /  log(1/r)
        if (alfa >= len(codificacion[suceso]) and ceil(alfa) <= len(codificacion[suceso])) :
            compacto = False
            break
    return compacto

def cumpleKraft(codificacion):
    return sum(2 ** -len(c) for c in codificacion) <= 1