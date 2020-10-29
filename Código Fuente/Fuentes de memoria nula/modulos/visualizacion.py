def mostrar_distribucion(fuenteOriginal, fuenteSimulada):
    print("_____________________________________________")
    print("\nDistribución de probabilidades en cada fuente\n")
    print("Símbolo   Fuente original   Fuente simulada")
    for simbolo in fuenteOriginal:
        print("{:^7}   {:^15.4f}   {:^16.4f}".format(simbolo, fuenteOriginal[simbolo], fuenteSimulada[simbolo]))

def mostrar_informacion(informacionFuenteOriginal, informacionFuenteSimulada):
    print("_____________________________________________")
    print("\nInformación otorgada por cada símbolo\n")
    print("Símbolo   Fuente original   Fuente simulada")
    for simbolo in informacionFuenteOriginal.keys():
        print("{:^7}   {:^15.4f}   {:^16.4f}".format(simbolo, informacionFuenteOriginal[simbolo], informacionFuenteSimulada[simbolo]))

def mostrar_entropia(entropiaFuenteOriginal, entropiaFuenteSimulada):
    print("_____________________________________________")
    print("\nEntropia de cada fuente\n")
    print("Fuente original   Fuente simulada\n")
    print("{:^15.4f}   {:^16.4f}".format(entropiaFuenteOriginal, entropiaFuenteSimulada))
    print("_____________________________________________")