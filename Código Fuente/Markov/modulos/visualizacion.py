def imprimir_matriz(matriz, simbolos):
    print("      ", " ".join(simbolo.center(8) for simbolo in simbolos)) # imprime los caracteres sobre sus respectivas columnas
    for indice, fila in enumerate(matriz):
        print("{:^5}".format(simbolos[indice]), # imprime el caracter asociado a la fila
              " ".join("{:.6f}".format(valor) for valor in fila)) # imprime la fila de valores

def imprimir_vector(vector):
    for i in range(len(vector)):
        print("V" + str(i+1) + ":",vector[i])