import sys
from lectura import leerFuente
from codificacion import generarCodificacion
from calculos import *

def main():
    if len(sys.argv < 2):
       raise SinRutaArchivoException('No ingresó la ruta del archivo') 
    path = sys.argv[1]
    fuente = leerFuente(path)
    codificacion = generarCodificacion(fuente)

    print('Entropía de la fuente: ', entropia(fuente.values()))
    print('Longitud media: ', longitudMedia())
    print('El código es compacto: ', isCompacto())
    print('Cumple la inecuación de Kraft: ', cumpleKraft())

if __name__ == '__main__':
    main()