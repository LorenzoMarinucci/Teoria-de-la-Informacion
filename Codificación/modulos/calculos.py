from math import log

def entropia(probabilidadSimbolos):
    return sum(p ** log(1/p, 2) for p in probabilidadSimbolos)

def longitudMedia(probabilidadSimbolos,codificacion):
    return sum(p * len(c) for p,c in zip(probabilidadSimbolos,codificacion))

def isCompacto(probabilidadSimbolos,codificacion):
    return longitudMedia(probabilidadSimbolos,codificacion) >= entropia(probabilidadSimbolos)

def cumpleKraft(codificacion):
    return sum(2 ** -len(c) for c in codificacion)