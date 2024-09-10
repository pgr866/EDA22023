package org.eda2.practica02;

import java.util.*;

import org.eda2.practica02.Mochila.Lingote;
import org.eda2.practica02.Mochila.Objeto;

/**
 * Clase que representa los diferentes metodos necesarios para calcular los
 * tiempos de ejecucion promedios de los algoritmos del problema de la mochila.
 * 
 * @author equipo-jfm112-pgr866
 */
public class TiemposMochila {

	/**
	 * Método que genera una mochila aleatoria con objetos de pesos y valores
	 * aleatorios.
	 * 
	 * @param numObjetos número de objetos que se incluirán en la mochila.
	 * @param capacidad  capacidad máxima de la mochila.
	 * @return Una nueva instancia de la clase Mochila con objetos aleatorios y una
	 *         capacidad aleatoria.
	 */
	public static Mochila mochilaCreator(int numObjetos, int capacidad) {
		// Crear una lista de objetos aleatorios
		List<Objeto> objetos = new ArrayList<>();
		for (int i = 0; i < numObjetos; i++) {
			// Generar un peso y valor aleatorio para el objeto
			double peso = 1 + (Math.random() * ((capacidad * 1.1 - 1) + 1));
			double valor = 1 + (Math.random() * ((2000000 - 1) + 1));
			objetos.add(new Objeto(peso, valor));
		}
		// Crear una nueva instancia de la clase Mochila con la lista de objetos
		// aleatorios y la capacidad máxima
		return new Mochila(objetos, capacidad);
	}

	/**
	 * Método que genera una mochila aleatoria con lingotes de peso y valor
	 * determinado por su tipo de lingote.
	 * 
	 * @param numObjetos número de lingotes que se incluirán en la mochila.
	 * @param capacidad  capacidad máxima de la mochila.
	 * @return Una nueva instancia de la clase Mochila con lingotes aleatorios.
	 * @throws Exception si se produce un error al crear la mochila.
	 */
	public static Mochila lingotesCreator(int numObjetos, int capacidad) throws Exception {
		// Crear una lista de lingotes aleatorios
		List<Objeto> objetos = new ArrayList<>();
		for (int i = 0; i < numObjetos; i++) {
			// Elegir un tipo aleatorio de lingote y crear un lingote con ese tipo
			List<String> tipos = new ArrayList<>(Arrays.asList("oro", "plata", "bronce"));
			objetos.add(new Lingote(tipos.get(new Random().nextInt(tipos.size()))));
		}
		// Crear una nueva instancia de la clase Mochila con la lista de lingotes
		// aleatorios y la capacidad máxima
		return new Mochila(objetos, capacidad);
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
	 * @throws Exception si el tipo de lingote no es valido
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Tiempos promedio de ejecución Problema de la Mochila\n");
		System.out.println("1) Mochila Fraccionaria O(n)\n" + "2) Mochila Entera O(n)\n" + "3) Mochila Mitad O(n)");
		System.out.print("Elija el algoritmo a medir: ");
		Scanner entrada = new Scanner(System.in);
		int algoritmo = entrada.nextInt();
		System.out.println();
		System.out.print("Número de objetos: ");
		int n = entrada.nextInt();
		System.out.println();
		System.out.println("1) Objetos\n" + "2) Lingotes");
		System.out.print("Elija el tipo de objeto: ");
		Mochila mochila;
		switch (entrada.nextInt()) {
		case 1:
			mochila = mochilaCreator(n, 100);
			break;
		case 2:
			mochila = lingotesCreator(n, 100);
			break;
		default:
			throw new Exception("Solo existen lingotes de oro, plata o bronce.");
		}

		entrada.close();
		System.out.println();
		String nombreAlgoritmo = "";
		long startTime = 0, endTime = 0;
		long[] tiempos = new long[21];

		for (int i = 0; i < tiempos.length; i++) {
			switch (algoritmo) {
			case 1:
				startTime = System.nanoTime();
				mochila.getMochilaFraccionaria();
				endTime = System.nanoTime();
				nombreAlgoritmo = "Mochila Fraccionaria O(n): ";
				break;
			case 2:
				startTime = System.nanoTime();
				mochila.getMochilaEntera();
				endTime = System.nanoTime();
				nombreAlgoritmo = "Mochila Entera O(n): ";
				break;
			case 3:
				startTime = System.nanoTime();
				mochila.getMochilaMitad();
				endTime = System.nanoTime();
				nombreAlgoritmo = "Mochila Mitad O(n): ";
				break;
			default:
				throw new Exception("Error, introduzca un numero del 1 al 3.");
			}
			tiempos[i] = endTime - startTime;
		}

//		System.out.println(nombreAlgoritmo + (long) mediaArray(quitarMayor(tiempos)) + " ns");
		System.out.println(nombreAlgoritmo + (long) mediaArray(quitarMayor(tiempos)) + " nanosegundos. " + getVarianza(quitarMayor(tiempos)));

//		int[] ns = {50, 200, 1000, 5000, 50000, 100000, 500000, 1000000, 10000000, 20000000};
//		Mochila mochila;
//		for (int n : ns) {
//			System.out.print(n + ", ");
//			mochila = mochilaCreator(n, 100);
//			long startTime = 0, endTime = 0;
//			long[] tiempos = new long[21];
//			for (int i = 0; i < tiempos.length; i++) {
//				startTime = System.nanoTime();
//				mochila.getMochilaFraccionaria();
//				endTime = System.nanoTime();
//				tiempos[i] = endTime - startTime;
//			}
//			System.out.println(mediaArray(quitarMayor(tiempos)));
//		}
//		
//		for (int n : ns) {
//			System.out.print(n + ", ");
//			mochila = mochilaCreator(n, 100);
//			long startTime = 0, endTime = 0;
//			long[] tiempos = new long[21];
//			for (int i = 0; i < tiempos.length; i++) {
//				startTime = System.nanoTime();
//				mochila.getMochilaEntera();
//				endTime = System.nanoTime();
//				tiempos[i] = endTime - startTime;
//			}
//			System.out.println(mediaArray(quitarMayor(tiempos)));
//		}
//		
//		for (int n : ns) {
//			System.out.print(n + ", ");
//			mochila = mochilaCreator(n, 100);
//			long startTime = 0, endTime = 0;
//			long[] tiempos = new long[21];
//			for (int i = 0; i < tiempos.length; i++) {
//				startTime = System.nanoTime();
//				mochila.getMochilaMitad();
//				endTime = System.nanoTime();
//				tiempos[i] = endTime - startTime;
//			}
//			System.out.println(mediaArray(quitarMayor(tiempos)));
//		}
//		
//		for (int n : ns) {
//			System.out.print(n + ", ");
//			mochila = lingotesCreator(n, 100);
//			long startTime = 0, endTime = 0;
//			long[] tiempos = new long[21];
//			for (int i = 0; i < tiempos.length; i++) {
//				startTime = System.nanoTime();
//				mochila.getMochilaFraccionaria();
//				endTime = System.nanoTime();
//				tiempos[i] = endTime - startTime;
//			}
//			System.out.println(mediaArray(quitarMayor(tiempos)));
//		}
		
	}

}
