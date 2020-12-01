package calculos;

public class Canal {

    private double[][] matCanal;
    private double[] probEntrada;
    private double[] probSalida;
    private double[][] matPPost;

    public Canal(double[][] matP, double[] probA) {
        this.matCanal = matP;
        this.probEntrada = probA;
        this.probSalida = this.calculaProbB();
        this.matPPost = this.calculaMatPPost();
    }

    private double[][] calculaMatPPost() {
        int filas = this.matCanal[0].length;
        int columnas = this.matCanal.length;
        int i, j;
        double[][] matPaPost = new double[filas][columnas];
        for (i = 0; i < filas; i++)
            for (j = 0; j < columnas; j++)
                matPaPost[i][j] = (this.matCanal[j][i] * this.probEntrada[j]) / this.probSalida[i];
        return matPaPost;
    }

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

    public double calculoEquivocacion(double[] entropiaPost) {
        double entropia = 0.;
        int i;
        for (i = 0; i < entropiaPost.length; i++)
            entropia += this.probSalida[i] * entropiaPost[i];
        return entropia;
    }

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

    public double calculoInformacionMutuaAB(double equivocacion) {
        return this.calculoEntropiaAPriori() - equivocacion;
    }

    public double calculoInformacionMutuaBA(double entropiaBA) {
        return calculoEntropiaSalida() - entropiaBA;
    }

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

    public double calculoEntropiaSalida() {
        double suma = 0, probabilidad;
        int i, elementos = this.probSalida.length;
        for (i = 0; i < elementos; i++) {
            probabilidad = this.probSalida[i];
            suma += probabilidad * -1 * (Math.log(probabilidad) / Math.log(2));
        }
        return suma;
    }

    public double calculoEntropiaAPriori() {
        double suma = 0, probabilidad;
        int i, elementos = this.probEntrada.length;
        for (i = 0; i < elementos; i++) {
            probabilidad = this.probEntrada[i];
            suma += probabilidad * -1 * (Math.log(probabilidad) / Math.log(2));
        }
        return suma;
    }

    public double[] calculoEntropiaAPosteriori() {
        int fila = this.matPPost.length;
        int columna = this.matPPost[0].length;
        double[] entropias = new double[fila];
        int i, j;
        double probabilidad = 0;
        for (i = 0; i < fila; i++) {
            for (j = 0; j < columna; j++) {
                probabilidad = this.matPPost[i][j];
                if (probabilidad != 0)
                    entropias[i] += probabilidad * -1 * (Math.log(probabilidad) / Math.log(2));
            }
        }
        return entropias;
    }

    public double entropiaAfin() {
        double entropia = 0.;

        for (int i = 0; i < matCanal.length; i++)
            for (int j = 0; j < matCanal[i].length; j++) {
                double sucesoSimultaneo = probEntrada[i] * matCanal[i][j];
                if (sucesoSimultaneo != 0.)
                    entropia += sucesoSimultaneo * Math.log(1 / sucesoSimultaneo) / Math.log(2);
            }

        return entropia;
    }

    public double[] getProbSalida() {
        return probSalida;
    }

    public double[][] getMatPPost() {
        return matPPost;
    }
}
