import random

def generarCodificacion1(diccionario):
    '''
    En base a la cantidad de simbolos se calcula la cantidad de bits necesarios para generar las palabras codigo,
    posteriormente se recorre el diccionario y a cada clave se le asigna una palabra codigo.

    Parametros:

    diccionario: son los simbolos iniciales con sus probabilidades asociadas.

    Retorna: diccionario con los simbolos como clave y la palabra codigo como valor.
    '''
    j = 0
    claves = diccionario.keys()
    codigo = {}
    cantBits = len(str(bin(len(diccionario)-1))[2:])
    for i in claves:
        codigo.setdefault(i,str(str(bin(j))[2:]).zfill(cantBits))
        j = j + 1
    return codigo

def generarCodificacion2(diccionario):
    '''
    Se crea una copia del diccionario y posteriormente se procede a buscar la probabilidad mas alta, luego de ello se
    ubica el simbolo al que pertenece la misma y se le asocia una palabra codigo, luego de realizar lo antes mencionado
    se elimina la clave-valor del diccionario y se repite el procedimiento una y otra vez hasta que no quedan mas
    elementos.

    Parametros:

    diccionario: son los simbolos iniciales con sus probabilidades asociadas.

    Retorna: diccionario con los simbolos como clave y la palabra codigo como valor.
    '''
    inicial = "1"
    longitud = 1
    codigo = {}
    dicCopia = diccionario.copy()
    while(dicCopia):
        maxProb = max(dicCopia.values())
        clave = list(dicCopia.keys())[list(dicCopia.values()).index(maxProb)]
        dicCopia.pop(clave)
        codigo.setdefault(clave, str(inicial).zfill(longitud))
        longitud = longitud + 1
    return codigo

def generarCodificacion(diccionario):
    '''
    Se selecciona un numero aleatorio y en base a dicho numero se elige un metodo de codificacion u otro con el cual se
    va a generar la salida.

    Parametros:

    diccionario: son los simbolos iniciales con sus probabilidades asociadas.

    Retorna: diccionario con los simbolos como clave y la palabra codigo como valor.
    '''
    valor = random.randint(0, 1)
    if valor == 0:
        codigo = generarCodificacion1(diccionario)
    else:
        codigo = generarCodificacion2(diccionario)
    return codigo