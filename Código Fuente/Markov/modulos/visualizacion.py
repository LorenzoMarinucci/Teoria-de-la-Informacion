def imprimir_matriz(matriz, simbolos):
    print("      ", " ".join(simbolo.center(8) for simbolo in simbolos))
    for indice, fila in enumerate(matriz):
        print("{:^5}".format(simbolos[indice])," ".join("{:.6f}".format(valor) for valor in fila))

def imprimir_vector(vector):
    for i in range(len(vector)):
        print("V" + str(i+1) + ":",vector[i])