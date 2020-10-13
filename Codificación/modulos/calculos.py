from math import log

def entropia(probabilidadSimbolos):
    return sum(p ** log(1/p, 2) for p in probabilidadSimbolos)

def longitudMedia():
    pass

def isCompacto():
    pass

def cumpleKraft():
    pass