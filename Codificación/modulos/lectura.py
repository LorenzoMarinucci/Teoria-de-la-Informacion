def leerFuente(path):
    '''Lee una fuente del archivo de texto pasado como parámetro.
    Devuelve la misma en un diccionario con los símbolos como llave
    y la probabilidad como valor'''
    
    with open(path, encoding='utf-8') as archivoFuente:
        # se supone que el archivo viene estructurado por "SIMBOLO PROBABILIDAD" en cada linea
        simbolos = {valores[0]: float(valores[1]) for valores in (linea.split() for linea in archivoFuente)}
    
    return simbolos