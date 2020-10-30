import sys
from modulos.lectura  import leerFuente
from modulos.codificacion import generarCodificacion
from modulos.calculos import entropia, longitudMedia, isCompacto, cumpleKraft
from modulos.visualizacion import muestraCodificacion

class SinRutaArchivoException(Exception):
    pass

def main():
    if len(sys.argv) < 2:
       raise SinRutaArchivoException('No ingresó la ruta del archivo') 
    path = sys.argv[1]
    fuente = leerFuente(path)
    codificacion = generarCodificacion(fuente)

    muestraCodificacion(codificacion)
    print('\nEntropía de la fuente: ', entropia(fuente.values()))
    print('Longitud media: ', longitudMedia(fuente,codificacion))
    print('El código es compacto: ', isCompacto(fuente,codificacion, 2))
    print('Cumple la inecuación de Kraft: ', cumpleKraft(codificacion))

if __name__ == '__main__':
    main()