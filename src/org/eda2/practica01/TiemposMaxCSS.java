package org.eda2.practica01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase que representa los diferentes metodos necesarios para calcular los
 * tiempos de ejecucion promedios de los algoritmos de Subsecuencia Maxima de un
 * array.
 * 
 * @author equipo-jfm112-pgr866
 */
public class TiemposMaxCSS {

	/**
	 * Metodo que genera una secuencia aleatoria de numeros en el rango [-50, 50]
	 * 
	 * @param numYears numero de anos para los que generar la secuencia aleatoria
	 * @return resultado es la secuencia aleatoria
	 */
	public static int[] arrayCreator(int numYears) {
		int resultado[] = new int[365 * numYears];
		for (int i = 0; i < resultado.length; i++)
			resultado[i] = -50 + (int) (Math.random() * ((50 + 50) + 1));
		return resultado;
	}

	/**
	 * Metodo que ordena el array por parametro para devolver una copia quitandole
	 * el mayor elemento
	 * 
	 * @param tiempos es el array original de tiempos de ejecucion
	 * @return resultado es el array ordenado sin el elemento mayor
	 */
	public static long[] quitarMayor(long[] tiempos) {
		long[] resultado = new long[tiempos.length - 1];
		Arrays.sort(tiempos);

		for (int i = 0; i < resultado.length; i++)
			resultado[i] = tiempos[i];

		return resultado;
	}

	/**
	 * Metodo que calcula la media de los elementos de un array
	 * 
	 * @param tiempos es el array original de tiempos de ejecucion
	 * @return devuelve la media de los elementos del array
	 */
	public static double mediaArray(long[] tiempos) {
		long suma = 0;
		for (int i = 0; i < tiempos.length; i++)
			suma += tiempos[i];
		return (double) suma / (double) (tiempos.length);
	}

	/**
	 * Metodo que calcula el estadistico de varianza de los tiempos de ejecucion
	 * 
	 * @param tiempos es el array original de tiempos de ejecucion
	 * @return devuelve el estadistico de varianza de los tiempos de ejecucion
	 */
	public static double getVarianza(long[] tiempos) {
		double media = mediaArray(tiempos);
		long sumatoria = 0;

		for (int i = 0; i < tiempos.length; i++)
			sumatoria += Math.pow(tiempos[i] - media, 2);

		return 1 - (Math.sqrt((double) sumatoria / (double) (tiempos.length)) / media);
	}

	/**
	 * Metodo main, que va a ejecutar cada uno de los algoritmos para imprimir por
	 * pantalla sus tiempos promedios de ejecucion
	 * 
	 * @param args argumentos
	 */
	public static void main(String[] args) {
		System.out.println("Tiempos promedio de ejecución Subsecuencia Máxima - Estadístico de varianza\n");
		System.out.print("1) Iterativo O(n3)\n" + "2) Iterativo O(n2)\n" + "3) Iterativo O(n)\n" + "4) DyV O(n log n)\n"
				+ "5) DyV Compare O(n)\n" + "6) DyV Linear O(n)\n");
		System.out.print("Elija el algoritmo a medir: ");
		Scanner entrada = new Scanner(System.in);
		int algoritmo = entrada.nextInt();

		System.out.print("Número de años: ");
		int[] dias = arrayCreator(entrada.nextInt());
		entrada.close();
		System.out.println();
		String nombreAlgoritmo = "";
		long startTime = 0, endTime = 0;
		long[] tiempos = new long[21];

		for (int i = 0; i < tiempos.length; i++) {
			switch (algoritmo) {
			case 1:
				startTime = System.nanoTime();
				MaxCSS.maxSubsequenceSumIterativeCubic(dias);
				endTime = System.nanoTime();
				nombreAlgoritmo = "Iterativo O(n3): ";
				break;
			case 2:
				startTime = System.nanoTime();
				MaxCSS.maxSubsequenceSumIterativeQuadratic(dias);
				endTime = System.nanoTime();
				nombreAlgoritmo = "Iterativo O(n2): ";
				break;
			case 3:
				startTime = System.nanoTime();
				MaxCSS.maxSubsequenceSumIterativeLinear(dias);
				endTime = System.nanoTime();
				nombreAlgoritmo = "Iterativo O(n): ";
				break;
			case 4:
				startTime = System.nanoTime();
				MaxCSS.maxContiguousSubsequenceSumDyV(dias);
				endTime = System.nanoTime();
				nombreAlgoritmo = "DyV O(n log n): ";
				break;
			case 5:
				startTime = System.nanoTime();
				MaxCSS.maxContiguousSubsequenceSumDyVCompare(dias);
				endTime = System.nanoTime();
				nombreAlgoritmo = "DyV Compare O(n): ";
				break;
			case 6:
				startTime = System.nanoTime();
				MaxCSS.maxContiguousSubsequenceSumDyVLinear(dias);
				endTime = System.nanoTime();
				nombreAlgoritmo = "DyV Linear O(n): ";
				break;
			}
			tiempos[i] = endTime - startTime;
		}

		System.out.println(nombreAlgoritmo + (long) mediaArray(quitarMayor(tiempos)));

//		System.out.println(nombreAlgoritmo + (long) mediaArray(quitarMayor(tiempos)) + " nanosegundos. " + getVarianza(quitarMayor(tiempos)));

	}
}