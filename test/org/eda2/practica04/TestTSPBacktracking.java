package org.eda2.practica04;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import org.junit.*;

/**
 * Clase que representa el conjunto de tests para los distintos metodos del
 * problema del viajante de comercio
 * 
 * @author equipo-jfm112-pgr866
 */
public class TestTSPBacktracking {
	double[][] graphEDAlandNewroads = TSPBacktracking.load("graphEDAlandNewroads.txt");
	double[][] graphEDAlandCombustible = TSPBacktracking.load("graphEDAlandCombustible.txt");
	ArrayList<Double> esperado1 = new ArrayList<>(
			List.of(4999.0, 5192.0, 5203.0, 5246.0, 5271.0, 5443.0, 5208.0, 5257.0, 5391.0, 5231.0, 5365.0, 4711.0,
					4904.0, 4915.0, 4958.0, 4983.0, 5155.0, 5257.0, 5231.0, 4915.0, 5203.0, 5208.0, 5443.0, 5155.0,
					5391.0, 5365.0, 4999.0, 4711.0, 5246.0, 4958.0, 5271.0, 4983.0, 4904.0, 5192.0));
	double esperado2 = 4711.0;
	int[] esperado3 = { 0, 1, 5, 4, 2, 3, 8, 7, 11, 18, 19, 20, 17, 16, 14, 13, 12, 15, 10, 9, 6 };
	double esperado4 = 342.69999999997435;
	int[] esperado5 = { 0, 1, 5, 4, 2, 3, 8, 7, 18, 19, 20, 17, 16, 14, 13, 12, 15, 11, 10, 9, 6 };
	double esperado6 = 4869.0;
	int[] esperado7 = { 21, 0, 6, 9, 10, 15, 12, 13, 14, 16, 17, 20, 19, 18, 11, 7, 8, 3, 2, 4, 5, 1 };
	double esperado8 = 5049.0;
	int[] esperado9 = { 22, 18, 11, 7, 8, 3, 2, 4, 5, 1, 21, 0, 6, 9, 10, 15, 12, 13, 14, 16, 17, 20, 19 };
	double esperado10 = 5233.0;
	int[] esperado11 = { 23, 17, 20, 19, 22, 7, 8, 3, 2, 4, 5, 1, 21, 0, 6, 9, 10, 12, 13, 14, 16, 15, 11, 18 };
	double esperado12 = 5297.0;
	int[] esperado13 = { 24, 15, 11, 18, 23, 17, 20, 19, 22, 7, 8, 3, 2, 4, 5, 1, 21, 0, 6, 9, 10, 12, 13, 14, 16 };
	double esperado14 = 5311.0;
	int[] esperado15 = { 25, 15, 12, 13, 14, 16, 24, 11, 18, 23, 17, 20, 19, 22, 7, 8, 3, 2, 4, 5, 1, 21, 0, 6, 9, 10 };
	double esperado16 = 5322.0;
	int[] esperado17 = { 26, 5, 1, 21, 0, 6, 9, 10, 25, 15, 12, 13, 14, 16, 24, 11, 18, 23, 17, 20, 19, 22, 7, 8, 3, 2,
			4 };
	double esperado18 = 5289.0;
	int[] esperado19 = { 27, 20, 19, 22, 7, 8, 3, 2, 4, 26, 5, 1, 21, 0, 6, 9, 10, 25, 15, 12, 13, 14, 16, 24, 11, 18,
			23, 17 };

	/**
	 * Prueba el método btSalesperson para encontrar todos los circuitos posibles en
	 * el grafo y compara el resultado con el esperado.
	 */
	@Test
	public void testTodosCircuitosPosibles() {
		ArrayList<Double> resultado1 = TSPBacktracking.btSalesperson(graphEDAlandNewroads);
		assertEquals(resultado1, esperado1);
	}

	/**
	 * Prueba el método btSalespersonBest para encontrar el mejor circuito en el
	 * grafo y compara el resultado con el esperado.
	 */
	@Test
	public void testMejorCircuitoAlmeria() {
		double resultado2 = TSPBacktracking.btSalespersonBest(graphEDAlandNewroads);
		assertEquals(resultado2, esperado2, 0.001);
		int[] resultado3 = TSPBacktracking.bestTourSoFar;
		assertArrayEquals(resultado3, esperado3);
	}

	/**
	 * Prueba el método btSalespersonBest para encontrar el mejor circuito en el
	 * grafo de EDAland con combustible y compara el resultado con el esperado.
	 */
	@Test
	public void testMejorCircuitoAlmeriaCombustible() {
		double resultado4 = TSPBacktracking.btSalespersonBest(graphEDAlandCombustible);
		assertEquals(resultado4, esperado4, 0.001);
		int[] resultado5 = TSPBacktracking.bestTourSoFar;
		assertArrayEquals(resultado5, esperado5);
	}

	/**
	 * Prueba el método btSalespersonBest para encontrar el mejor circuito en un
	 * grafo modificado cargado desde un archivo y compara el resultado con el
	 * esperado.
	 *
	 * @throws IOException Si ocurre un error de entrada/salida al leer o escribir
	 *                     en archivos.
	 */
	@Test
	public void testMejorCircuitoCualquieraModificado() throws IOException {
		String rutaLeer = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
				+ File.separator + "eda2" + File.separator + "practica04" + File.separator + "graphEDAlandNewroads.txt";
		Scanner scanner = new Scanner(new File(rutaLeer));
		ArrayList<String> lineas = new ArrayList<>();
		while (scanner.hasNext())
			lineas.add(scanner.nextLine());
		scanner.close();

		String rutaEscribir = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "org"
				+ File.separator + "eda2" + File.separator + "practica04" + File.separator
				+ "graphEDAlandNewroadsModificado.txt";
		FileWriter escritor = new FileWriter(new File(rutaEscribir));
		for (String linea : lineas)
			escritor.write(linea + "\n");
		escritor.close();

		double[][] graphEDAlandNewroadsModificado = TSPBacktracking.load("graphEDAlandNewroadsModificado.txt");
		TreeMap<String, Integer> aristas = new TreeMap<>();
		aristas.put("Almeria", 202);
		aristas.put("Granada", 129);
		aristas.put("Cadiz", 233);
		TSPBacktracking.addCapitalProvincia("Malaga", aristas);
		graphEDAlandNewroadsModificado = TSPBacktracking.load("graphEDAlandNewroadsModificado.txt");
		double resultado6 = TSPBacktracking.btSalespersonBest(graphEDAlandNewroadsModificado);
		int[] resultado7 = TSPBacktracking.cambiarSource(TSPBacktracking.bestTourSoFar, "Malaga");
		assertEquals(resultado6, esperado6, 0.001);
		assertArrayEquals(resultado7, esperado7);

		aristas.clear();
		aristas.put("Caceres", 201);
		aristas.put("Valladolid", 121);
		aristas.put("Vigo", 415);
		TSPBacktracking.addCapitalProvincia("Salamanca", aristas);
		graphEDAlandNewroadsModificado = TSPBacktracking.load("graphEDAlandNewroadsModificado.txt");
		double resultado8 = TSPBacktracking.btSalespersonBest(graphEDAlandNewroadsModificado);
		int[] resultado9 = TSPBacktracking.cambiarSource(TSPBacktracking.bestTourSoFar, "Salamanca");
		assertEquals(resultado8, esperado8, 0.001);
		assertArrayEquals(resultado9, esperado9);

		aristas.clear();
		aristas.put("Valladolid", 58);
		aristas.put("Bilbao", 244);
		aristas.put("Oviedo", 253);
		TSPBacktracking.addCapitalProvincia("Palencia", aristas);
		graphEDAlandNewroadsModificado = TSPBacktracking.load("graphEDAlandNewroadsModificado.txt");
		double resultado10 = TSPBacktracking.btSalespersonBest(graphEDAlandNewroadsModificado);
		int[] resultado11 = TSPBacktracking.cambiarSource(TSPBacktracking.bestTourSoFar, "Palencia");
		assertEquals(resultado10, esperado10, 0.001);
		assertArrayEquals(resultado11, esperado11);

		aristas.clear();
		aristas.put("Madrid", 236);
		aristas.put("Zaragoza", 159);
		aristas.put("Bilbao", 229);
		TSPBacktracking.addCapitalProvincia("Soria", aristas);
		graphEDAlandNewroadsModificado = TSPBacktracking.load("graphEDAlandNewroadsModificado.txt");
		double resultado12 = TSPBacktracking.btSalespersonBest(graphEDAlandNewroadsModificado);
		int[] resultado13 = TSPBacktracking.cambiarSource(TSPBacktracking.bestTourSoFar, "Soria");
		assertEquals(resultado12, esperado12, 0.001);
		assertArrayEquals(resultado13, esperado13);

		aristas.clear();
		aristas.put("Madrid", 310);
		aristas.put("Zaragoza", 171);
		aristas.put("Valencia", 144);
		TSPBacktracking.addCapitalProvincia("Teruel", aristas);
		graphEDAlandNewroadsModificado = TSPBacktracking.load("graphEDAlandNewroadsModificado.txt");
		double resultado14 = TSPBacktracking.btSalespersonBest(graphEDAlandNewroadsModificado);
		int[] resultado15 = TSPBacktracking.cambiarSource(TSPBacktracking.bestTourSoFar, "Teruel");
		assertEquals(resultado14, esperado14, 0.001);
		assertArrayEquals(resultado15, esperado15);

		aristas.clear();
		aristas.put("Sevilla", 145);
		aristas.put("Jaen", 108);
		aristas.put("Huelva", 243);
		aristas.put("Badajoz", 264);
		TSPBacktracking.addCapitalProvincia("Cordoba", aristas);
		graphEDAlandNewroadsModificado = TSPBacktracking.load("graphEDAlandNewroadsModificado.txt");
		double resultado16 = TSPBacktracking.btSalespersonBest(graphEDAlandNewroadsModificado);
		int[] resultado17 = TSPBacktracking.cambiarSource(TSPBacktracking.bestTourSoFar, "Cordoba");
		assertEquals(resultado16, esperado16, 0.001);
		assertArrayEquals(resultado17, esperado17);

		aristas.clear();
		aristas.put("Corunya", 101);
		aristas.put("Valladolid", 353);
		aristas.put("Oviedo", 252);
		TSPBacktracking.addCapitalProvincia("Lugo", aristas);
		graphEDAlandNewroadsModificado = TSPBacktracking.load("graphEDAlandNewroadsModificado.txt");
		double resultado18 = TSPBacktracking.btSalespersonBest(graphEDAlandNewroadsModificado);
		int[] resultado19 = TSPBacktracking.cambiarSource(TSPBacktracking.bestTourSoFar, "Lugo");
		assertEquals(resultado18, esperado18, 0.001);
		assertArrayEquals(resultado19, esperado19);
	}

}
