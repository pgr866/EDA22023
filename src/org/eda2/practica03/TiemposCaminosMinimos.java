package org.eda2.practica03;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import org.eda2.practica03.CaminosMinimos.*;

/**
 * Clase que genera un grafo aleatorio, calcula los tiempos de ejecución de los
 * diferentes algoritmos para calcular los caminos mínimos entre un vértice y
 * todos los demás y los escribe en un archivo de texto.
 */
public class TiemposCaminosMinimos {
	/**
	 * Valor que se utiliza para representar una distancia infinita.
	 */
	static final int INF = Integer.MAX_VALUE / 2;

	/**
	 * Método que crea un grafo aleatorio con el número de vértices y aristas
	 * especificados y lo escribe en un archivo.
	 * 
	 * @param numV número de vértices que tendrá el grafo.
	 * @param numA número de aristas que tendrá el grafo.
	 * 
	 * @throws Exception si el número de aristas es menor que el número de vértices
	 *                   menos 1, lo que hace imposible que el grafo sea conexo.
	 */
	public static void grafoCreator(int numV, int numA) throws Exception {
		String ruta = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org" + File.separator
				+ "eda2" + File.separator + "practica03" + File.separator + "data.txt";
		FileWriter escritor = new FileWriter(new File(ruta));

		// Escribir la primera línea
		escritor.write("0\n");

		// Escribir el número de vértices
		escritor.write(numV + "\n");

		// Escribir los vértices
		for (int i = 0; i < numV; i++) {
			escritor.write(i + "\n");
		}

		// Escribir el número de aristas
		escritor.write(numA + "\n");

		int[][] matrizAdyacencias = generateRandomConnectedGraph(numV, numA);
		for (int i = 0; i < numV; i++) {
			for (int j = 0; j < numV; j++)
				if (matrizAdyacencias[i][j] != 0)
					escritor.write(i + " " + j + " " + matrizAdyacencias[i][j] + "\n");
		}
		escritor.close();
	}

	/**
	 * Método que genera un grafo aleatorio conexo con el número de vértices y
	 * aristas especificados.
	 * 
	 * @param numV número de vértices que tendrá el grafo.
	 * @param numA número de aristas que tendrá el grafo.
	 * 
	 * @return la matriz de adyacencias del grafo.
	 * 
	 * @throws Exception si el número de aristas es menor que el número de vértices
	 *                   menos 1, lo que hace imposible que el grafo sea conexo.
	 */
	public static int[][] generateRandomConnectedGraph(int numV, int numA) throws Exception {
		int[][] adjMatrix = new int[numV][numV];
		Random rand = new Random();

		// Compruebo que el número de aristas es al menos número de vértices - 1
		if (numA < numV - 1)
			throw new Exception("Un grafo necesita más aristas para ser conexo.");

		// Inicializo la matriz con todo 0
		for (int i = 0; i < numV; i++)
			for (int j = 0; j < numV; j++)
				adjMatrix[i][j] = 0;

		// Creo las mínimas aristas necesarias para que sea conexo, que son número de
		// vértices - 1
		int numAMin = numV - 1;
		for (int i = 0; i < numAMin; i++)
			adjMatrix[i][i + 1] = rand.nextInt(500) + 1;

		// Completamos con aristas adicionales hasta alcanzar el número de aristas
		// deseado
		int numAAct = numA - numAMin;
		for (int i = 0; i < numAAct; i++) {
			int v1 = rand.nextInt(numV);
			int v2 = rand.nextInt(numV);
			if (adjMatrix[v1][v2] == 0)
				adjMatrix[v1][v2] = rand.nextInt(500) + 1;
			else
				i--;
		}
		return adjMatrix;
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
	 * Metodo principal que realiza la medicion de tiempos de los algoritmos de
	 * caminos minimos
	 *
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		System.out.println("Tiempos promedio de ejecución Problemas de Caminos Minimos\n");
//		System.out.println("1) Dijkstra O(n)\n2) BellmanFord O(n)\n3) FloydWarshall O(n)");
//		System.out.print("Elija el algoritmo a medir: ");
//		Scanner entrada = new Scanner(System.in);
//		int algoritmo = entrada.nextInt();
//		System.out.println();
//		String nombreAlgoritmo = "";
//		long startTime = 0, endTime = 0;
//		long[] tiempos = new long[21];
//
//		String archivo = "";
//		System.out.println("1) graphEDAland.txt\n2) graphEDAlandLarge.txt\n3) Aleatorio (data.txt)");
//		System.out.print("Archivo a cargar: ");
//		switch (entrada.nextInt()) {
//		case 1:
//			archivo = "graphEDAland.txt";
//			break;
//		case 2:
//			archivo = "graphEDAlandLarge.txt";
//			break;
//		case 3:
//			archivo = "data.txt";
//			System.out.print("Número de vértices: ");
//			int n = entrada.nextInt();
//			System.out.print("Número de aristas: ");
//			int m = entrada.nextInt();
//			System.out.println();
//			grafoCreator(n, m);
//			break;
//		default:
//			throw new Exception("Error, introduzca un numero del 1 al 3.");
//		}
//
//		entrada.close();
//
//		for (int i = 0; i < tiempos.length; i++) {
//			switch (algoritmo) {
//			case 1:
//				CaminosMinimos.load(archivo, Algoritmo.Dijkstra);
//				startTime = System.nanoTime();
//				CaminosMinimos.dijkstra(0);
//				endTime = System.nanoTime();
//				nombreAlgoritmo = "Dijkstra O(n): ";
//				break;
//			case 2:
//				CaminosMinimos.load(archivo, Algoritmo.BellmanFord);
//				startTime = System.nanoTime();
//				CaminosMinimos.bellmanFord(0);
//				endTime = System.nanoTime();
//				nombreAlgoritmo = "BellmanFord O(n): ";
//				break;
//			case 3:
//				CaminosMinimos.load(archivo, Algoritmo.FloydWarshall);
//				startTime = System.nanoTime();
//				CaminosMinimos.floydWarshall();
//				endTime = System.nanoTime();
//				nombreAlgoritmo = "FloydWarshall O(n): ";
//				break;
//			default:
//				throw new Exception("Error, introduzca un numero del 1 al 3.");
//			}
//			tiempos[i] = endTime - startTime;
//		}
//
//		System.out.println(nombreAlgoritmo + (long) mediaArray(quitarMayor(tiempos)) + " ns");
//		System.out.println(nombreAlgoritmo + (long) mediaArray(quitarMayor(tiempos)) + " nanosegundos. " + getVarianza(quitarMayor(tiempos)));

		CaminosMinimos.load("graphEDAland.txt", Algoritmo.BellmanFord);
//		int[][] resultado = CaminosMinimos.grafo;
//		int[][] resultado = CaminosMinimos.bellmanFord(1);
//		
//		System.out.println(resultado.length);
//		
//		String cadena = "{ ";
//		for (int i=0; i<resultado.length; i++) {
//			cadena += "{";
//			for (int j=0; j<resultado.length; j++) {
//				cadena += resultado[i][j];
//				if (j != resultado.length - 1) cadena += ", ";
//			}
//			cadena += "}\n";
//		}
//		cadena +=" }";
//		System.out.println(cadena);
		
		
		
//		CaminosMinimos.dijkstra(0);
//		CaminosMinimos.load("graphEDAland.txt", Algoritmo.BellmanFord);
//		System.out.println(CaminosMinimos.getPathVerticeIntermedio(1, 10, 7, Algoritmo.BellmanFord));
//		grafoCreator(50, 100);

	}
}
