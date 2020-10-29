import math

def cantInformacion(fuente):
    '''Dada la fuente de simbolos, se procede a calcular la cantidad de informacion aportada por
    cada simbolo.
     
    Parámetros:

    fuente: objeto que contiene a los simbolos como claves y su probabilidad como valor.
    
    Retorna: diccionario con la cantidad de informacion aportada por simbolo.
    '''
    cantInformacion = {}
    for simbolo in fuente:
        if (fuente[simbolo]):
            cantInformacion[simbolo] = math.log(1/fuente[simbolo], 2)
        else:
            cantInformacion[simbolo] = 0
    return cantInformacion

def entropia(cantInformacion, probabilidades):
    ''' Dada la cantidad de informacion aportada por cada elemento, almacenada en un diccionario, se procede a
        calcular la entropia realizando la sumatoria de cada producto entre la cantidad de informacion y
        probabilidad de ocurrencia de un simbolo determinado.

    Parámetros:

    cantInformacion: diccionario que contiene en cada uno de sus valores la cantidad de informacion aportada por cada simbolo.
    simbolos: diccionario que contiene a los simbolos como claves y su respectiva probabilidad como valor.

    Retorna: valor de la entropia.
    '''
    entropia = 0
    for simbolo in probabilidades:
        entropia += probabilidades[simbolo] * cantInformacion[simbolo]
    return entropia