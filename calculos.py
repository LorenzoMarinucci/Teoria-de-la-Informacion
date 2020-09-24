'''
Created on 22 sep. 2020

@author: GuidoGarcia
'''
import math

'''
Parametros:Diccionario:Objeto que contiene a los simbolos como claves y la distribucion de probabilidad 
           como valor.
Funcion:Dado el diccionario de simbolos, se procede a calcular la cantidad de informacion aportada por 
        cada simbolo.
Retorno:Vector con la cantidad de informacion perteneciente aportada por simbolo.
'''
def calculaCantInformacion(diccionario):
    probabilidades = diccionario.values();
    cantInformacion = [];
    i = 0;
    for probabilidad in probabilidades:
        if(probabilidad != 0):
            cantInformacion.insert(i, math.log(1/probabilidad , 2));
        else:
            cantInformacion.insert(i, 0.0);
        i = i + 1;
    return cantInformacion;

'''
Parametros:CantInformacion:Vector que contiene en cada una de sus posiciones la cantidad de informacion
                           aportada por cada simbolo.
           Diccionario:Objeto que contiene a los simbolos como claves y la distribucion de probabilidad 
                       como valor.
Funcion:Dada la cantidad de informacion aportada por cada elemento, almacenada en un vector se procede a
        calcular la entropia realizando la sumatoria de cada producto entre la cantidad de informacion y
        probabilidad de ocurrencia de un simbolo determinado.
Retorno:Valor de la entropia.
'''
def calculaEntropia(cantInformacion,diccionario):
    probabilidades = diccionario.values();
    i = 0;
    entropia = 0;
    for probabilidad in probabilidades:
        entropia += probabilidad * cantInformacion[i];
        i = i +1;
    return entropia;