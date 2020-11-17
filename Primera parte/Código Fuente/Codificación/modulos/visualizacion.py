def muestraCodificacion(codificacion):
    print("\nCodificacion\n")
    print("Símbolo   Palabra codigo")
    for simbolo in codificacion:
        print("{:^7}        {}".format(simbolo,codificacion[simbolo]))