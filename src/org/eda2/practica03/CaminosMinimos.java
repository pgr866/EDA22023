package org.eda2.practica03;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase que representa los Caminos Mínimos en un grafo.
 */
public class CaminosMinimos {

	public static int[][] grafo;
	private static boolean dirigido;
	private static String[] vertices;

	/**
	 * Enumeración de los algoritmos disponibles para calcular los caminos mínimos.
	 */
	public enum Algoritmo {
		Dijkstra, BellmanFord, FloydWarshall
	};

	static final int INF = Integer.MAX_VALUE / 2;

	/**
	 * Carga un archivo que contiene la representación del grafo.
	 * 
	 * @param filename  Nombre del archivo a cargar.
	 * @param algoritmo Algoritmo a utilizar para calcular los caminos mínimos.
	 */
	public static void load(String filename, Algoritmo algoritmo) {
		try {
			String ruta = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
					+ File.separator + "eda2" + File.separator + "practica03" + File.separator + filename;
			Scanner scanner = new Scanner(new File(ruta));

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
			grafo = new int[numVertices][numVertices];
			vertices = new String[numVertices];

			// Lee la sección de vértices
			for (int i = 0; i < vertices.length; i++)
				vertices[i] = scanner.nextLine().trim();

			Arrays.sort(vertices);
			// Lee la sección de aristas
			int numAristas = Integer.parseInt(scanner.nextLine());
			for (int i = 0; i < numAristas; i++) {
				String[] items = scanner.nextLine().split(" ");
				int origen = Arrays.binarySearch(vertices, items[0].trim());
				int destino = Arrays.binarySearch(vertices, items[1].trim());
				int peso = Integer.parseInt(items[2].trim());
				grafo[origen][destino] = peso;

				// Si el grafo no es dirigido, agregamos la arista en ambos sentidos
				if (!dirigido)
					grafo[destino][origen] = peso;
			}

			switch (algoritmo) {
			case BellmanFord:
				for (int i = 0; i < grafo.length; i++) {
					for (int j = 0; j < grafo.length; j++)
						if (grafo[i][j] == 0)
							grafo[i][j] = INF;
				}
				break;
			case FloydWarshall:
				for (int i = 0; i < grafo.length; i++) {
					for (int j = 0; j < grafo.length; j++)
						if (grafo[i][j] == 0 && i != j)
							grafo[i][j] = INF;
				}
				break;
			}
			scanner.close();
		} catch (Exception e) {
			System.err.println("Error al cargar el archivo: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve el índice del vértice de menor distancia que aún no ha sido visitado
	 * 
	 * @param dist   Array de distancias
	 * @param sptSet Array de booleanos que indica si el vértice ya ha sido visitado
	 * 
	 * @return Índice del vértice de menor distancia no visitado, -1 si todos los
	 *         vértices han sido visitados
	 */
	public static int minDistance(int dist[], Boolean sptSet[]) {
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < dist.length; v++) {
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}
		}

		return min_index;
	}

	/**
	 * Implementación del algoritmo de Dijkstra para encontrar el camino más corto
	 * desde un vértice origen a todos los demás vértices en un grafo ponderado.
	 * 
	 * @param src Vértice origen
	 * 
	 * @return Array con las distancias mínimas desde el vértice origen hasta cada
	 *         uno de los demás vértices, y otro array con los predecesores de cada
	 *         vértice en el camino más corto desde el vértice origen
	 */
	public static int[][] dijkstra(int src) {
		int V = grafo.length;
		int dist[] = new int[V];

		Boolean sptSet[] = new Boolean[V];
		int S[] = new int[V];

		for (int i = 0; i < V; i++) {
			dist[i] = grafo[src][i] != 0 ? grafo[src][i] : Integer.MAX_VALUE;
			sptSet[i] = false;
			S[i] = grafo[src][i] != 0 ? src : -1;
		}

		dist[src] = 0;
		S[src] = src;
		sptSet[src] = true;

		for (int count = 0; count < V - 1; count++) {
			int u = minDistance(dist, sptSet);
			sptSet[u] = true;
			for (int v = 0; v < V; v++) {
				if (!sptSet[v] && grafo[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + grafo[u][v] < dist[v]) {
					dist[v] = dist[u] + grafo[u][v];
					S[v] = u;
				}
			}
		}
		return new int[][] { dist, S };
	}

	/**
	 * Clase interna que representa una arista del grafo
	 */
	static class Edge {
		int src, dest, weight;

		Edge() {
			src = dest = weight = 0;
		}

		Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	};

	/**
	 * 
	 * Implementación del algoritmo de Bellman-Ford para encontrar el camino más
	 * corto desde un vértice origen a todos los demás vértices en un grafo
	 * ponderado que puede contener aristas con pesos negativos.
	 * 
	 * @param src Vértice origen
	 * 
	 * @return Array con las distancias mínimas desde el vértice origen hasta cada
	 *         uno de los demás vértices, y otro array con los predecesores de cada
	 *         vértice en el camino más corto desde el vértice origen. Devuelve una
	 *         matriz vacía si el grafo contiene un ciclo negativo.
	 */
	public static int[][] bellmanFord(int src) {
		int V = grafo.length;
		int dist[] = new int[V];
		int pred[] = new int[V];

		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (grafo[i][j] != INF) {
					edges.add(new Edge(i, j, grafo[i][j]));
				}
			}
		}

		for (int i = 0; i < V; i++) {
			dist[i] = INF;
			pred[i] = -1;
		}
		dist[src] = 0;

		for (int i = 1; i < V; i++) {
			for (Edge e : edges) {
				int u = e.src;
				int v = e.dest;
				int weight = e.weight;
				if ((dist[u] != INF) && (dist[u] + weight < dist[v])) {
					dist[v] = dist[u] + weight;
					pred[v] = u;
				}
			}
		}

		for (Edge e : edges) {
			int u = e.src;
			int v = e.dest;
			int weight = e.weight;
			if ((dist[u] != INF) && (dist[u] + weight < dist[v])) {
				System.out.println("Graph contains negative weight cycle");
				return new int[0][0];
			}
		}
		return new int[][] { dist, pred };
	}

	/**
	 * Genera el camino desde un vértice origen hasta un vértice destino utilizando
	 * el array de predecesores
	 * 
	 * @param source      Vértice origen
	 * @param destination Vértice destino
	 * @param S           Array de predecesores
	 * @return Cadena que representa el camino desde el vértice origen hasta el
	 *         vértice destino
	 */
	public static String generatePath(int source, int destination, int[] S) {
		return (destination == source ? "" : generatePath(source, S[destination], S) + " -> ") + "V" + destination;
	}

	/**
	 * Obtiene el camino más corto desde un vértice origen hasta un vértice destino
	 * pasando por un vértice intermedio utilizando el algoritmo especificado
	 * 
	 * @param src               Vértice origen
	 * @param verticeIntermedio Vértice intermedio
	 * @param dest              Vértice destino
	 * @param algoritmo         Algoritmo a utilizar (Dijkstra, BellmanFord,
	 *                          FloydWarshall)
	 * @return Cadena que representa el camino más corto desde el vértice origen
	 *         hasta el vértice destino pasando por el vértice intermedio
	 */
	public static String getPathVerticeIntermedio(int src, int verticeIntermedio, int dest, Algoritmo algoritmo) {
		int[] S1 = null;
		int[] S2 = null;
		switch (algoritmo) {
		case Dijkstra:
			S1 = dijkstra(src)[1];
			S2 = dijkstra(verticeIntermedio)[1];
			break;
		case BellmanFord:
			S1 = bellmanFord(src)[1];
			S2 = bellmanFord(verticeIntermedio)[1];
			break;
		case FloydWarshall:
			return "Método no implementado para Floyd";
		}

		String cadena = generatePath(verticeIntermedio, dest, S2);
		String vi = String.valueOf(verticeIntermedio);
		int i = cadena.indexOf(vi) + vi.length();
		cadena = cadena.substring(i);

		return generatePath(src, verticeIntermedio, S1) + cadena;
	}

	/**
	 * Este método implementa el algoritmo de Floyd-Warshall para encontrar el
	 * camino más corto entre todos los pares de vértices en un grafo.
	 * 
	 * @return Una matriz tridimensional que contiene la longitud de los caminos más
	 *         cortos entre todos los pares de vértices y una matriz bidimensional
	 *         que contiene los nodos intermedios del camino más corto.
	 */
	public static int[][][] floydWarshall() {
		int i, j, k;
		int V = grafo.length;
		int[][] S = new int[V][V];

		for (i = 0; i < V; i++) {
			for (j = 0; j < V; j++) {
				S[i][j] = -1;
			}
		}

		for (k = 0; k < V; k++) {
			for (i = 0; i < V; i++) {
				for (j = 0; j < V; j++) {
					if (grafo[i][k] + grafo[k][j] < grafo[i][j]) {
						grafo[i][j] = grafo[i][k] + grafo[k][j];
						S[i][j] = k;
					}
				}
			}
		}
		return new int[][][] { grafo, S };
	}
}
