import re

def leer_matriz(path):
    """A partir del path de un archivo que contiene los símbolos en la primera línea y luego los valores ordenados por filas y columnas,
    retorna una matriz con los valores y una lista con los símbolos. La posición en la lista de símbolos se mapea a la fila/columna de la matriz.

    Parámetros:

    path: ruta al archivo que contiene los valores para la matriz de transición

    Retorna: una lista que representa a la matriz y una lista con los símbolos de la fuente
    """

    matriz = []

    with open(path, encoding = 'utf-8') as archivoMatriz:
        linea = archivoMatriz.readline()
        linea = linea.strip() # borra espacios en blanco antes del primer símbolo y después del último
        linea = re.sub('\s+', ' ', linea) # reemplaza cualquier cantidad de espacios en blanco por sólo uno
        simbolos = linea.split(" ") # genera lista de simbolos
        for linea in archivoMatriz: # se recorren las líneas restantes
            linea = linea.strip()
            linea = re.sub('\s+', ' ', linea)
            # genera una lista con cada valor presente en la línea, luego inserta la lista (fila) en la última posición de la matriz
            matriz.append([float(valor) for valor in linea.split(" ")]) 
        

    return (matriz, simbolos)
