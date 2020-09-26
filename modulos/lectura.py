def generar_diccionario(path):
    """Toma como argumento el path a un archivo, lo abre y pasa la distribución de
    probabilidades a un diccionario, con los símbolos como llaves."""

    simbolos = {}
    with open(path, encoding='utf-8') as archivoSimbolos:
        # se supone que el archivo viene estructurado por "SIMBOLO PROBABILIDAD" en cada linea
        for linea in archivoSimbolos:
            valores = linea.split()
            simbolos[valores[0]]=float(valores[1])
    return simbolos