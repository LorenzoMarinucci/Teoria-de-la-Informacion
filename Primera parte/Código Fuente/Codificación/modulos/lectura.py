def leerFuente(path):
    '''Lee una fuente del archivo de texto pasado como parámetro.
    Devuelve la misma en un diccionario con los símbolos como llave
    y la probabilidad como valor

    Parámetros:

    path: ruta al archivo que contiene los simbolos y sus respectivas probabilidades

    Retorna: diccionario son los simbolos a modo de claves y las probabilidades como valor
    '''
    with open(path, encoding='utf-8') as archivoFuente:
        simbolos = {valores[0]: float(valores[1]) for valores in (linea.split() for linea in archivoFuente)}
    return simbolos