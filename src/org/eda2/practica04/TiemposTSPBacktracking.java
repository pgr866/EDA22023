package org.eda2.practica04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Clase que contiene métodos relacionados con la medición de tiempos del
 * problema del vendedor viajero utilizando el algoritmo de backtracking.
 */
public class TiemposTSPBacktracking {

	/**
	 * Imprime el tiempo promedio de ejecución del algoritmo btSalesperson
	 * utilizando el grafo graphEDAlandNewroadsModificado. El tiempo se imprime en
	 * nanosegundos (ns).
	 */
	public static void printTiempoApartado5() {
		long startTime = 0, endTime = 0;
		long[] tiempos = new long[21];
		double[][] graphEDAlandNewroadsModificado = TSPBacktracking.load("graphEDAlandNewroadsModificado.txt");
		for (int i = 0; i < tiempos.length; i++) {
			startTime = System.nanoTime();
			TSPBacktracking.btSalesperson(graphEDAlandNewroadsModificado);
			endTime = System.nanoTime();
			tiempos[i] = endTime - startTime;
		}
		System.out.println((long) mediaArray(quitarMayor(tiempos)) + " ns");
	}

	/**
	 * Realiza el apartado 5 de la práctica: Crea una versión modificada del grafo
	 * original. Imprime el tiempo promedio de ejecución del algoritmo btSalesperson
	 * para el grafo original y para cada grafo modificado.
	 *
	 * @throws IOException si ocurre un error de lectura o escritura de archivos.
	 */
	public static void apartado5() throws IOException {
		// Cargar el grafo original
		String rutaLeer = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
				+ File.separator + "eda2" + File.separator + "practica04" + File.separator + "graphEDAlandNewroads.txt";
		Scanner scanner = new Scanner(new File(rutaLeer));
		ArrayList<String> lineas = new ArrayList<>();
		while (scanner.hasNext())
			lineas.add(scanner.nextLine());
		scanner.close();

		// Crear el grafo modificado y guardarlo en un archivo
		String rutaEscribir = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
				+ File.separator + "eda2" + File.separator + "practica04" + File.separator
				+ "graphEDAlandNewroadsModificado.txt";
		FileWriter escritor = new FileWriter(new File(rutaEscribir));
		for (String linea : lineas)
			escritor.write(linea + "\n");
		escritor.close();

		// Imprimir tiempo promedio para el grafo original
		printTiempoApartado5();

		// Grafo modificado
		TreeMap<String, Integer> aristas = new TreeMap<>();
		aristas.put("Almeria", 202);
		aristas.put("Granada", 129);
		aristas.put("Cadiz", 233);
		TSPBacktracking.addCapitalProvincia("Malaga", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Caceres", 201);
		aristas.put("Valladolid", 121);
		aristas.put("Vigo", 415);
		TSPBacktracking.addCapitalProvincia("Salamanca", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Valladolid", 58);
		aristas.put("Bilbao", 244);
		aristas.put("Oviedo", 253);
		TSPBacktracking.addCapitalProvincia("Palencia", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Madrid", 236);
		aristas.put("Zaragoza", 159);
		aristas.put("Bilbao", 229);
		TSPBacktracking.addCapitalProvincia("Soria", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Madrid", 310);
		aristas.put("Zaragoza", 171);
		aristas.put("Valencia", 144);
		TSPBacktracking.addCapitalProvincia("Teruel", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Sevilla", 145);
		aristas.put("Jaen", 108);
		aristas.put("Huelva", 243);
		aristas.put("Badajoz", 264);
		TSPBacktracking.addCapitalProvincia("Cordoba", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Corunya", 101);
		aristas.put("Valladolid", 353);
		aristas.put("Oviedo", 252);
		TSPBacktracking.addCapitalProvincia("Lugo", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Zaragoza", 177);
		aristas.put("Lerida", 324);
		aristas.put("Bilbao", 155);
		TSPBacktracking.addCapitalProvincia("Pamplona", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Valencia", 199);
		aristas.put("Albacete", 161);
		aristas.put("Madrid", 166);
		TSPBacktracking.addCapitalProvincia("Cuenca", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Madrid", 74);
		aristas.put("Jaen", 279);
		aristas.put("Badajoz", 363);
		TSPBacktracking.addCapitalProvincia("Toledo", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Lugo", 225);
		aristas.put("Palencia", 134);
		aristas.put("Oviedo", 118);
		aristas.put("Valladolid", 142);
		TSPBacktracking.addCapitalProvincia("Toledo", aristas);
		printTiempoApartado5();

		aristas.clear();
		aristas.put("Barcelona", 270);
		aristas.put("Lerida", 112);
		aristas.put("Zaragoza", 74);
		aristas.put("Pamplona", 165);
		TSPBacktracking.addCapitalProvincia("Huesca", aristas);
		printTiempoApartado5();
	}

	/**
	 * Genera un grafo aleatorio conectado y hamiltoniano.
	 * 
	 * @param numV El número de vértices del grafo.
	 * @return Una matriz de adyacencia que representa el grafo generado.
	 * @throws Exception Si ocurre un error durante la generación del grafo.
	 */
	public static double[][] generateRandomConnectedHamiltonGraph(int numV) throws Exception {
		double[][] adjMatrix = new double[numV][numV];
		Random rand = new Random();

		// Inicializo la matriz con todo 0
		for (int i = 0; i < numV; i++)
			for (int j = 0; j < numV; j++)
				adjMatrix[i][j] = 0;

		// Creo las mínimas aristas necesarias para que sea conexo y de Hamilton, que
		// son número de vértices
		for (int i = 0; i < numV - 1; i++)
			adjMatrix[i][i + 1] = rand.nextInt(500) + 1;

		// Cierro el ciclo de Hamilton
		adjMatrix[numV - 1][0] = rand.nextInt(500) + 1;

		// Hasta ahora cada vértice tiene 2 aristas salientes, y completamos con aristas
		// adicionales hasta cada vértice, en promedio, tenga 5.
		for (int i = 0; i < 1.5 * numV; i++) {
			int v1 = rand.nextInt(numV);
			int v2 = rand.nextInt(numV);
			if (adjMatrix[v1][v2] == 0 && v1 != v2) {
				double temp = rand.nextInt(500) + 1;
				adjMatrix[v1][v2] = temp;
				adjMatrix[v2][v1] = temp;
			} else
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
	 * Método principal que mide los tiempos promedio de ejecución para los
	 * Problemas del Viajante de Comercio utilizando Backtracking. Permite elegir
	 * entre diferentes algoritmos y número de vértices.
	 * 
	 * @param args Los argumentos de la línea de comandos (no se utilizan).
	 * @throws Exception Si ocurre un error durante la ejecución.
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("Tiempos promedio de ejecución Problemas del Viajante de Comercio con Backtracking O(n!)\n");
		System.out.println("1) Todos los circuitos posibles\n2) Mejor circuito\n3) Apartado 5\n");
		System.out.print("Elija el algoritmo a medir: ");
		Scanner entrada = new Scanner(System.in);
		int algoritmo = entrada.nextInt();
		long startTime = 0, endTime = 0;
		long[] tiempos = new long[21];
		System.out.print("Elija el número de vértices (n): ");
		entrada = new Scanner(System.in);
		int n = entrada.nextInt();

		double[][] G;
		for (int i = 0; i < tiempos.length; i++) {
			switch (algoritmo) {
			case 1:
				G = generateRandomConnectedHamiltonGraph(n);
				startTime = System.nanoTime();
				TSPBacktracking.btSalesperson(G);
				endTime = System.nanoTime();
				break;
			case 2:
				G = generateRandomConnectedHamiltonGraph(n);
				startTime = System.nanoTime();
				TSPBacktracking.btSalespersonBest(G);
				endTime = System.nanoTime();
				break;
			case 3:
				apartado5();
				return;
			default:
				throw new Exception("Error, introduzca un numero del 1 al 3.");
			}
			tiempos[i] = endTime - startTime;
		}

		System.out.println((long) mediaArray(quitarMayor(tiempos)) + " ns");
//		System.out.println((long) mediaArray(quitarMayor(tiempos)) + " nanosegundos. " + getVarianza(quitarMayor(tiempos)));
	}
}
