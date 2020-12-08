package calculos;

public class Canal {

    private double[][] matCanal;
    private double[] probEntrada;
    private double[] probSalida;
    private double[][] matPPost;

    /**
     * Constructor de la clase. Guarda los valores para la matriz de canal y las probabilidades de las entradas, así como también
     * las probabilidades de las salidas y la matriz de probabilidades a posteriori
     * @param matP: matriz de canal
     * @param probA: probabilidades de entrada
     */
    public Canal(double[][] matP, double[] probA) {
        this.matCanal = matP;
        this.probEntrada = probA;
        this.probSalida = this.calculaProbB();
        this.matPPost = this.calculaMatPPost();
    }

    /**
     * Método que calcula la matriz de probabilidades a posteriori
     * @return matriz de probabilidades a posteriori
     */
    private double[][] calculaMatPPost() {
        int columnas = this.matCanal[0].length;
        int filas = this.matCanal.length;
        int i, j;
        double[][] matPaPost = new double[filas][columnas];
        for (i = 0; i < filas; i++)
            for (j = 0; j < columnas; j++)
                matPaPost[i][j] = (this.matCanal[i][j] * this.probEntrada[i]) / this.probSalida[j];
        return matPaPost;
    }

    /**
     * Método que calcula las probabilidades de las salidas
     * @return probabilidades de las salidas
     */
    private double[] calculaProbB() {
        int columnas = this.matCanal[0].length;
        int filas = this.matCanal.length;
        double[] probB = new double[columnas];
        int i, j;
        for (i = 0; i < columnas; i++)
            for (j = 0; j < filas; j++)
                probB[i] += this.probEntrada[j] * this.matCanal[j][i];
        return probB;
    }

    /**
     * Método que realiza el cálculo de la equivocación de un canal
     * @param entropiaPost: entropía a posteriori
     * @return equivocación de un canal
     */
    public double calculoEquivocacion(double[] entropiaPost) {
        double entropia = 0.;
        int i;
        for (i = 0; i < entropiaPost.length; i++)
            entropia += this.probSalida[i] * entropiaPost[i];
        return entropia;
    }

    /**
     * Método que calcula la entropía de la salida conociendo la entrada a partir de la matriz de canal y las probabilidades de las entradas
     * @return entropía de la salida conociendo la entrada
     */
    public double entropiaBA() {
        double entropia = 0.;
        for (int i = 0; i < matCanal.length; i++) {
            double aux = 0;
            for (int j = 0; j < matCanal[i].length; j++) {
                if (matCanal[i][j] != 0)
                    aux += matCanal[i][j] * Math.log(1 / matCanal[i][j]) / Math.log(2);
            }
            entropia += aux * probEntrada[i];
        }
        return entropia;
    }

    /**
     * Método que calcula la información mutua de un canal a partir de la resta entre la entropía de la entrada y la equivocación del canal
     * @param equivocacion: equivocación del canal
     * @return información mutua
     */
    public double calculoInformacionMutuaAB(double equivocacion) {
        return this.calculoEntropiaAPriori() - equivocacion;
    }

    /**
     * Método que calcula la información mutua de un canal a partir de la resta entre la entropía de la salida y la entropía de la salida
     * conociendo la entrada
     * @param entropiaBA: entropía de la salida conociendo la entrada
     * @return información mutua
     */
    public double calculoInformacionMutuaBA(double entropiaBA) {
        return calculoEntropiaSalida() - entropiaBA;
    }

    /**
     * Método que calcula la información mutua de un canal a partir de las probabilidades de los sucesos simultáneos del canal
     * @return información mutua
     */
    public double calculoInformacionMutuaConSucesosSimultaneos() {
        double p, informacion = 0;
        int i, j;
        for (i = 0; i < matCanal.length; i++)
            for (j = 0; j < matCanal[0].length; j++) {
                p = matCanal[i][j] * probEntrada[i];
                if (p != 0)
                    informacion += p * Math.log(p / (probEntrada[i] * probSalida[j])) / Math.log(2);
            }
        return informacion;
    }

    /**
     * Método que calcula el valor para la entropía de la salida de un canal a partir de las probabilidades de salida
     * @return entropía de la salida
     */
    public double calculoEntropiaSalida() {
        double suma = 0, probabilidad;
        int i, elementos = this.probSalida.length;
        for (i = 0; i < elementos; i++) {
            probabilidad = this.probSalida[i];
            suma += probabilidad * -1 * (Math.log(probabilidad) / Math.log(2));
        }
        return suma;
    }

    /**
     * Método que calcula la entropía a priori de un canal a partir de las probabilidades de entrada
     * @return entropía a priori
     */
    public double calculoEntropiaAPriori() {
        double suma = 0, probabilidad;
        int i, elementos = this.probEntrada.length;
        for (i = 0; i < elementos; i++) {
            probabilidad = this.probEntrada[i];
            suma += probabilidad * -1 * (Math.log(probabilidad) / Math.log(2));
        }
        return suma;
    }

    /**
     * Método que calcula la entropía a posteriori de un canal a partir de la matriz de probabilidades a posteriori
     * @return entropía a postriori
     */
    public double[] calculoEntropiaAPosteriori() {
        int fila = this.matPPost.length;
        int columna = this.matPPost[0].length;
        double[] entropias = new double[columna];
        int i, j;
        double probabilidad = 0;
        for (i = 0; i < fila; i++) {
            for (j = 0; j < columna; j++) {
                probabilidad = this.matPPost[i][j];
                if (probabilidad != 0)
                    entropias[j] += probabilidad * -1 * (Math.log(probabilidad) / Math.log(2));
            }
        }
        return entropias;
    }

    public double[] getProbSalida() {
        return probSalida;
    }

    public double[][] getMatPPost() {
        return matPPost;
    }
}
