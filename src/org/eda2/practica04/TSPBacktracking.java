package org.eda2.practica04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Clase que implementa la técnica de backtracking para resolver el problema del
 * vendedor viajero (TSP). Se proporcionan diferentes métodos para cargar el
 * grafo de un archivo, crear un nuevo grafo a partir de otro con aristas que
 * contienen información adicional, y para resolver el TSP.
 * 
 * @author equipo-jfm112-pgr866
 */
public class TSPBacktracking {
	/** Constante que se utiliza como valor infinito */
	static final int INF = Integer.MAX_VALUE / 2;

	/** Arreglo que guarda el tour parcial actual */
	static int[] partialTour;

	/** Arreglo que guarda el mejor tour hasta el momento */
	static int[] bestTourSoFar;

	/** Costo del mejor tour hasta el momento */
	static double costOfBestTourSoFar;

	/** Lista que almacena todos los tours generados */
	static ArrayList<int[]> allTours;

	/** Lista que almacena los costos de los tours generados */
	static ArrayList<Double> costOfAllTours;

	/** Costo del tour parcial actual */
	static double costOfPartialTour;

	/** Valor del mínimo costo de arista */
	static double costMinimumEdge;

	/** Arreglo que almacena los nombres de los vértices del grafo */
	static String[] vertices;

	/** Número de nodos podados */
	static int prunedNode;

	/**
	 * Carga el grafo a partir del archivo con el nombre dado.
	 * 
	 * @param filename nombre del archivo que contiene el grafo
	 * @return una matriz de adyacencia que representa el grafo
	 */
	public static double[][] load(String filename) {
		try {
			String ruta = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
					+ File.separator + "eda2" + File.separator + "practica04" + File.separator + filename;
			Scanner scanner = new Scanner(new File(ruta));

			boolean dirigido;
			// Lee la sección de tipo de grafo
			String tipo = scanner.nextLine();
			if (tipo.equals("0")) {
				dirigido = false;
			} else if (tipo.equals("1")) {
				dirigido = true;
			} else {
				throw new IllegalArgumentException("Tipo de grafo no válido: " + tipo);
			}

			// Lee número de vértices
			int numVertices = Integer.parseInt(scanner.nextLine());
			double[][] grafo = new double[numVertices][numVertices];
			vertices = new String[numVertices];

			// Lee la sección de vértices
			for (int i = 0; i < vertices.length; i++)
				vertices[i] = scanner.nextLine().trim();

			// Lee la sección de aristas
			int numAristas = Integer.parseInt(scanner.nextLine());
			for (int i = 0; i < numAristas; i++) {
				String[] items = scanner.nextLine().split(" ");
				int origen = Arrays.asList(vertices).indexOf(items[0].trim());
				int destino = Arrays.asList(vertices).indexOf(items[1].trim());
				double peso = Double.parseDouble(items[2].trim());
				grafo[origen][destino] = peso;

				// Si el grafo no es dirigido, agregamos la arista en ambos sentidos
				if (!dirigido)
					grafo[destino][origen] = peso;
			}
			scanner.close();
			return grafo;
		} catch (Exception e) {
			System.err.println("Error al cargar el archivo: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Crea un nuevo grafo a partir de un archivo de entrada, añadiendo información
	 * sobre combustible a las aristas. El nuevo grafo se escribe en un archivo de
	 * salida.
	 *
	 * @throws Exception si ocurre un error al cargar o escribir el archivo
	 */
	public static void graphEDAlandCombustibleCreator() throws Exception {
		try {
			// Ruta del archivo de entrada
			String rutaLeer = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
					+ File.separator + "eda2" + File.separator + "practica04" + File.separator
					+ "graphEDAlandNewroads.txt";

			// Scanner para leer el archivo de entrada
			Scanner scanner = new Scanner(new File(rutaLeer));

			// Ruta del archivo de salida
			String rutaEscribir = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
					+ File.separator + "eda2" + File.separator + "practica04" + File.separator
					+ "graphEDAlandCombustible.txt";

			// Escritor para escribir en el archivo de salida
			FileWriter escritor = new FileWriter(new File(rutaEscribir));

			// Lee y escribe la primera línea del archivo de entrada
			escritor.write(scanner.nextLine() + "\n");

			// Lee y escribe el número de vértices
			int numVertices = Integer.parseInt(scanner.nextLine());
			escritor.write(Integer.toString(numVertices) + "\n");

			// Lee y escribe los nombres de los vértices
			for (int i = 0; i < numVertices; i++)
				escritor.write(scanner.nextLine() + "\n");

			// Lee y escribe el número de aristas
			int numAristas = Integer.parseInt(scanner.nextLine());
			escritor.write(Integer.toString(numAristas) + "\n");

			// Genera un número aleatorio para el cálculo del combustible de cada arista
			Random rand = new Random();

			// Lee cada arista del archivo de entrada, calcula el combustible y escribe la
			// arista en el archivo de salida
			for (int i = 0; i < numAristas; i++) {
				String[] items = scanner.nextLine().split(" ");
				double peso = Double.parseDouble(items[2].trim());
				double random = (0.05 + (0.1 - 0.05) * rand.nextDouble());
				double combustible = (double) ((int) (peso * random * 100)) / 100;
				escritor.write(items[0].trim() + " " + items[1].trim() + " " + combustible + "\n");
			}

			// Cierra los scanners y escritores
			scanner.close();
			escritor.close();
		} catch (Exception e) {
			System.err.println("Error al cargar el archivo: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Intercambia los elementos en las posiciones i y j del arreglo a.
	 *
	 * @param a el arreglo en el que se realizará el intercambio
	 * @param i la posición del primer elemento a intercambiar
	 * @param j la posición del segundo elemento a intercambiar
	 */
	public static void swap(int[] a, int i, int j) {
		// Don't bother to check that indexes i and j
		// are in bounds. Java will do this and throw
		// an ArrayIndexOutOfBoundsException if i or
		// j is out of bounds.
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	/**
	 * Agrega una nueva capital de provincia al grafo, actualizando el archivo de
	 * entrada.
	 *
	 * @param nombre  el nombre de la nueva capital de provincia a agregar
	 * @param aristas el mapa de aristas que representa las conexiones de la nueva
	 *                capital con otros vértices
	 * @return true si se agrega la capital de provincia con éxito, false si el
	 *         nombre ya existe en el grafo
	 * @throws IOException si ocurre un error al cargar o escribir el archivo
	 */
	public static boolean addCapitalProvincia(String nombre, TreeMap<String, Integer> aristas) throws IOException {
		String rutaLeer = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
				+ File.separator + "eda2" + File.separator + "practica04" + File.separator
				+ "graphEDAlandNewroadsModificado.txt";
		Scanner scanner = new Scanner(new File(rutaLeer));

		ArrayList<String> lineas = new ArrayList<>();
		lineas.add(scanner.nextLine());
		String aux = scanner.nextLine();
		lineas.add(aux);
		int numVertices = Integer.parseInt(aux);

		// Lee los vértices existentes y verifica si el nombre de la nueva capital ya
		// existe
		for (int i = 0; i < numVertices; i++) {
			aux = scanner.nextLine();
			lineas.add(aux);
			if (aux.equals(nombre))
				return false;
		}

		// Agrega la nueva capital de provincia a la lista de vértices y actualiza el
		// número de vértices
		lineas.add(nombre);
		lineas.set(1, Integer.toString(++numVertices));

		int numAristas = Integer.parseInt(scanner.nextLine());
		lineas.add(Integer.toString(numAristas + aristas.size()));

		// Lee las aristas existentes y las agrega a la lista de líneas
		for (int i = 0; i < numAristas; i++)
			lineas.add(scanner.nextLine());

		scanner.close();

		// Agrega las nuevas aristas de la nueva capital de provincia al mapa de aristas
		// y a la lista de líneas
		for (Entry<String, Integer> entrada : aristas.entrySet())
			lineas.add(nombre + " " + entrada.getKey() + " " + entrada.getValue());

		String rutaEscribir = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
				+ File.separator + "eda2" + File.separator + "practica04" + File.separator
				+ "graphEDAlandNewroadsModificado.txt";
		FileWriter escritor = new FileWriter(new File(rutaEscribir));

		// Escribe todas las líneas en el archivo de salida
		for (String linea : lineas)
			escritor.write(linea + "\n");

		escritor.close();
		return true;
	}

	/**
	 * Calcula la arista mínima en una matriz de adyacencia.
	 *
	 * @param G la matriz de adyacencia
	 * @return el valor de la arista mínima en la matriz
	 */
	public static double computeMinimumEdge(double[][] G) {
		int n = G.length;
		double minEdge = INF;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i != j) && (G[i][j] < minEdge))
					minEdge = G[i][j];
			}
		}
		return minEdge;
	}

	/**
	 * Cambia la fuente de un tour dado, reordenando los elementos del tour.
	 *
	 * @param tour   el tour original
	 * @param source la nueva fuente del tour
	 * @return el tour con la fuente cambiada
	 */
	public static int[] cambiarSource(int[] tour, String source) {
		int n = tour.length;
		int src = Arrays.asList(vertices).indexOf(source);
		if (src == 0)
			return tour;
		int iSrc = -1;
		for (int i = 0; i < tour.length; i++) {
			if (tour[i] == src)
				iSrc = i;
		}
		int[] temp = new int[n];
		System.arraycopy(tour, iSrc, temp, 0, n - iSrc);
		System.arraycopy(tour, 0, temp, n - iSrc, iSrc);
		System.arraycopy(temp, 0, tour, 0, n);
		return tour;
	}

	/**
	 * Realiza una búsqueda exhaustiva para encontrar todos los posibles recorridos
	 * de un problema del vendedor viajero (TSP).
	 *
	 * @param G la matriz de adyacencia que representa el grafo del problema TSP
	 * @return una lista de los costos de todos los recorridos encontrados
	 */
	public static ArrayList<Double> btSalesperson(double[][] G) {
		// set partialTour to identity permutation
		int n = G.length;
		partialTour = new int[n];
		for (int i = 0; i < n; i++)
			partialTour[i] = i;

		allTours = new ArrayList<>();
		costOfAllTours = new ArrayList<>();
		costOfPartialTour = 0;

		// search permutations of partialTour[2:n]
		rTSP(G, 1, n);
		return costOfAllTours;
	}

	/**
	 * Realiza una búsqueda en profundidad recursiva para encontrar todos los
	 * posibles recorridos de un problema del vendedor viajero (TSP).
	 *
	 * @param G            la matriz de adyacencia que representa el grafo del
	 *                     problema TSP
	 * @param currentLevel el nivel actual de búsqueda en el árbol de recursión
	 * @param n            el número total de vértices en el grafo
	 */
	public static void rTSP(double[][] G, int currentLevel, int n) {
		if (currentLevel == n) { // at parent of a leaf
			// complete tour by adding last two edges
			if (G[partialTour[n - 1]][0] != 0) {
				int[] aux = new int[partialTour.length];
				System.arraycopy(partialTour, 0, aux, 0, partialTour.length);
				allTours.add(aux);
				costOfAllTours.add(costOfPartialTour + G[partialTour[n - 1]][0]);
			}
		} else { // try out subtrees
			for (int j = currentLevel; j < n; j++) {
				// is move to subtree labeled partialTour[j] possible?
				if (G[partialTour[currentLevel - 1]][partialTour[j]] != 0) {
					swap(partialTour, currentLevel, j);
					costOfPartialTour += G[partialTour[currentLevel - 1]][partialTour[currentLevel]];
					rTSP(G, currentLevel + 1, n);
					costOfPartialTour -= G[partialTour[currentLevel - 1]][partialTour[currentLevel]];
					swap(partialTour, currentLevel, j);
				}
			}
		}
	}

	/**
	 * Realiza una búsqueda exhaustiva para encontrar el costo del mejor recorrido
	 * posible en un problema del vendedor viajero (TSP).
	 *
	 * @param G la matriz de adyacencia que representa el grafo del problema TSP
	 * @return el costo del mejor recorrido encontrado, -1 si no es posible
	 *         encontrar un recorrido
	 */
	public static double btSalespersonBest(double[][] G) {
		// set partialTour to identity permutation
		int n = G.length;
		partialTour = new int[n];
		for (int i = 0; i < n; i++)
			partialTour[i] = i;

		// compute initial value of costOfBestTourSoFar
		costOfBestTourSoFar = 0;
		prunedNode = 0;
		for (int i = 0; i < n; i++) {// find max cost edge out of vertex i
			// maxCost will eventually be max weight of an out edge from i
			double maxCost = 0;
			// outEdge will become true iff i has an out edge
			boolean outEdge = false;
			for (int j = 0; j < n; j++) {
				if (G[i][j] != 0) {// found an out edge from vertex i
					outEdge = true;
					if (G[i][j] > maxCost)
						maxCost = G[i][j];
				}
			}

			// make sure vertex i has an out edge
			if (!outEdge)
				// no edge out of vertex i, no tour is possible
				return -1;

			// vertex i has an out edge
			costOfBestTourSoFar += maxCost;
		}
//		System.out.println("costOfBestTourSoFar = " + costOfBestTourSoFar);

		// costOfBestTourSoFar = INF;

		bestTourSoFar = new int[n];
		costOfPartialTour = 0;

		// search permutations of partialTour[2:n]
		rTSPBest(G, 1, n);
		return costOfBestTourSoFar;
	}

	/**
	 * Realiza una búsqueda en profundidad recursiva para encontrar el costo del
	 * mejor recorrido posible en un problema del vendedor viajero (TSP).
	 *
	 * @param G            la matriz de adyacencia que representa el grafo del
	 *                     problema TSP
	 * @param currentLevel el nivel actual de búsqueda en el árbol de recursión
	 * @param n            el número total de vértices en el grafo
	 */
	public static void rTSPBest(double[][] G, int currentLevel, int n) { // search from a node at currentLevel
		if (currentLevel == n) { // at parent of a leaf
			// complete tour by adding last two edges

			// int sum = 0;
			// for (int j = 0; j < n - 1; j++) {
			// System.out.print(partialTour[j + 1] + ", ");
			// sum += G[partialTour[j]][partialTour[j + 1]];
			// }
			// System.out.println(" - sum = "+ sum + " - "+ costOfPartialTour + " - " +
			// costOfBestTourSoFar);

			if (G[partialTour[n - 1]][0] != 0 && (costOfPartialTour + G[partialTour[n - 1]][0] < costOfBestTourSoFar)) { // better
																															// found
				for (int j = 0; j < n; j++) {
					bestTourSoFar[j] = partialTour[j];
					// System.out.print(partialTour[j] + ", ");
				}
				// System.out.println();
				costOfBestTourSoFar = costOfPartialTour + G[partialTour[n - 1]][0];
			}
		} else { // try out subtrees
			for (int j = currentLevel; j < n; j++) {
				// is move to subtree labeled partialTour[j] possible?
				if (G[partialTour[currentLevel - 1]][partialTour[j]] != 0 && (costOfPartialTour
						+ G[partialTour[currentLevel - 1]][partialTour[j]] < costOfBestTourSoFar)) { // search this
																										// subtree
					swap(partialTour, currentLevel, j);
					costOfPartialTour += G[partialTour[currentLevel - 1]][partialTour[currentLevel]];

					double estimatedTourCost = costOfPartialTour + ((n - currentLevel + 1) * costMinimumEdge);
					if (estimatedTourCost < costOfBestTourSoFar)
						rTSPBest(G, currentLevel + 1, n);
					else
						prunedNode++;

					costOfPartialTour -= G[partialTour[currentLevel - 1]][partialTour[currentLevel]];
					swap(partialTour, currentLevel, j);
				}
			}
		}
	}

	/**
	 * Este método main demuestra la solución del problema del vendedor viajero (TSP)
	 * utilizando un algoritmo de búsqueda exhaustiva.
	 *
	 * @param args Los argumentos de la línea de comandos (no se utilizan en este caso)
	 * @throws Exception Si ocurre un error durante la ejecución del algoritmo
	 */
	public static void main(String[] args) throws Exception {
//		 double[][] graph = {
//		 { 0, 10, 15, 20 },
//		 { 10, 0, 35, 25 },
//		 { 15, 35, 0, 30 },
//		 { 20, 25, 30, 0 }
//		 };

		double[][] graph = { { 0, 374, 200, 223, 108, 178, 252, 285, 240, 356 },
				{ 374, 0, 255, 166, 433, 199, 135, 95, 136, 17 }, { 200, 255, 0, 128, 277, 128, 180, 160, 131, 247 },
				{ 223, 166, 128, 0, 430, 47, 52, 84, 40, 155 }, { 108, 433, 277, 430, 0, 453, 478, 344, 389, 423 },
				{ 178, 199, 128, 47, 453, 0, 91, 110, 64, 181 }, { 252, 135, 180, 52, 478, 91, 0, 114, 83, 117 },
				{ 285, 95, 160, 84, 344, 110, 114, 0, 47, 78 }, { 240, 136, 131, 40, 389, 64, 83, 47, 0, 118 },
				{ 356, 17, 247, 155, 423, 181, 117, 78, 118, 0 } };

//		double[][] graph = { { 0, 2451, 713, 1018, 1631, 1374, 2408, 213, 2571, 875, 1420, 2145, 1972 },
//				{ 2451, 0, 1745, 1524, 831, 1240, 959, 2596, 403, 1589, 1374, 357, 579 },
//				{ 713, 1745, 0, 355, 920, 803, 1737, 851, 1858, 262, 940, 1453, 1260 },
//				{ 1018, 1524, 355, 0, 700, 862, 1395, 1123, 1584, 466, 1056, 1280, 987 },
//				{ 1631, 831, 920, 700, 0, 663, 1021, 1769, 949, 796, 879, 586, 371 },
//				{ 1374, 1240, 803, 862, 663, 0, 1681, 1551, 1765, 547, 225, 887, 999 },
//				{ 2408, 959, 1737, 1395, 1021, 1681, 0, 2493, 678, 1724, 1891, 1114, 701 },
//				{ 213, 2596, 851, 1123, 1769, 1551, 2493, 0, 2699, 1038, 1605, 2300, 2099 },
//				{ 2571, 403, 1858, 1584, 949, 1765, 678, 2699, 0, 1744, 1645, 653, 600 },
//				{ 875, 1589, 262, 466, 796, 547, 1724, 1038, 1744, 0, 679, 1272, 1162 },
//				{ 1420, 1374, 940, 1056, 879, 225, 1891, 1605, 1645, 679, 0, 1017, 1200 },
//				{ 2145, 357, 1453, 1280, 586, 887, 1114, 2300, 653, 1272, 1017, 0, 504 },
//				{ 1972, 579, 1260, 987, 371, 999, 701, 2099, 600, 1162, 1200, 504, 0 } };

		// double[][] graph = {
		// { 0, 98, 147, 135, 58 },
		// { 98, 0, 113, 137, 142 },
		// { 147, 113, 0, 56, 167 },
		// { 135, 137, 56, 0, 133 },
		// { 58, 142, 167, 133, 0 },
		// };

//		 double[][] graph = {
//		 { 0, 8, 6, 4, 0 },
//		 { 8, 0, 5, 10, 6 },
//		 { 6, 5, 0, 20, 0 },
//		 { 4, 10, 20, 0, 2 },
//		 { 0, 6, 0, 2, 0 },
//		 };

		System.out.println(btSalespersonBest(graph));
		System.out.println(Arrays.toString(bestTourSoFar));

	}
}
