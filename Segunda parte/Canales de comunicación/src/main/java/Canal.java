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
	
	//Correcto.
	private double[][] calculaMatPPost() {
		int filas = this.matCanal[0].length;
		int columnas = this.matCanal.length;
		int i,j;
		double[][] matPAB = new double[filas][columnas];
		for(i = 0 ; i < filas ; i++) 
			for(j = 0; j < columnas ; j++)
				matPAB[i][j] = (this.matCanal[j][i]*this.probEntrada[j])/this.probSalida[i];
		return matPAB;
	}

	//Correcto.
	private double[] calculaProbB() {
		int columnas = this.matCanal[0].length;
		double[] probB = new double[columnas];
		int i,j;
		for(i = 0; i < columnas ; i++) 
			for(j = 0; j < this.matCanal.length; j++) 
				probB[i] += this.probEntrada[j]*this.matCanal[j][i];
		return probB;
	}

	//Correcto.
	public double calculoEquivocacion(double[] entropiaPost) {
		double entropia = 0.;
		int i;
		for(i = 0 ; i < entropiaPost.length ; i++) 
			entropia += this.probSalida[i]*entropiaPost[i];
		return entropia;
	}
	
	public double calculoInformacionMutua(double equivocacion) {
		return this.calculoEntropiaAPriori() - equivocacion;
	}
	
	//Correcto.
	public double calculoEntropiaAPriori() {
		double suma = 0,probabilidad;
		int i,elementos = this.probEntrada.length;
		for(i = 0; i < elementos; i++) {
			probabilidad = this.probEntrada[i];
			suma += probabilidad *-1*(Math.log(probabilidad) / Math.log(2));
		}
		return suma;
	}
	
	//Correcto.
	public double[] calculoEntropiaAPosteriori() {
		int fila = this.matPPost.length;
		int columna = this.matPPost[0].length;
		double[] entropias = new double[fila];
		int i,j;
		double probabilidad = 0;
		for(i = 0 ; i < fila ; i++) {
			for(j = 0 ; j < columna ; j++) {
				probabilidad = this.matPPost[i][j];
				if(probabilidad != 0)
					entropias[i] += probabilidad*-1*(Math.log(probabilidad)/Math.log(2));
			}
		}
		return entropias;
	}
}
