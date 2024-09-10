package org.eda2.practica02;

import java.util.*;

/**
 * Clase que implementa tres algoritmos greedy para resolver el problema de la
 * mochila: mochila fraccionaria, mochila entera y mochila de objetos con peso
 * máximo de la mitad del peso total de la mochila.
 * 
 * @author equipo-jfm112-pgr866
 */
public class Mochila {
	private List<Objeto> objetos; // Lista de objetos disponibles para ser metidos en la mochila
	private int capacidad; // Peso máximo que puede soportar la mochila

	/**
	 * Constructor de la clase Mochila que inicializa los valores de los objetos y
	 * la capacidad de la misma.
	 * 
	 * @param objetos   Lista de objetos disponibles para ser metidos en la mochila.
	 * @param capacidad Peso máximo que puede soportar la mochila.
	 */
	public Mochila(List<Objeto> objetos, int capacidad) {
		this.objetos = objetos;
		this.capacidad = capacidad;
	}

	/**
	 * Crea una nueva instancia de la clase Mochila con una cantidad específica de
	 * lingotes de oro, plata y bronce.
	 * 
	 * @param numOro    la cantidad de lingotes de oro a agregar a la mochila.
	 * @param numPlata  la cantidad de lingotes de plata a agregar a la mochila.
	 * @param numBronce la cantidad de lingotes de bronce a agregar a la mochila.
	 * @param capacidad la capacidad máxima de la mochila.
	 * @throws Exception si la capacidad es menor o igual a cero.
	 */
	public Mochila(int numOro, int numPlata, int numBronce, int capacidad) throws Exception {
		// Asigna la capacidad de la mochila.
		this.capacidad = capacidad;
		// Inicializa la lista de objetos.
		objetos = new ArrayList<>();

		// Si la cantidad de lingotes de bronce es el valor máximo de un entero, asigna
		// la cantidad máxima de lingotes de bronce que caben en la mochila.
		if (numBronce == Integer.MAX_VALUE)
			numBronce = capacidad / 9 + 1;
		// Agrega los lingotes de bronce a la mochila.
		for (int i = 0; i < numBronce; i++)
			objetos.add(new Lingote("bronce"));

		// Si la cantidad de lingotes de plata es el valor máximo de un entero, asigna
		// la capacidad total de la mochila como la cantidad de lingotes de plata.
		if (numPlata == Integer.MAX_VALUE)
			numPlata = capacidad;
		// Agrega los lingotes de plata a la mochila.
		for (int i = 0; i < numPlata; i++)
			objetos.add(new Lingote("plata"));

		// Si la cantidad de lingotes de oro es el valor máximo de un entero, calcula la
		// cantidad máxima de lingotes de oro que caben en la mochila.
		if (numOro == Integer.MAX_VALUE)
			numOro = (int) (((double) capacidad) / 12.4) + 1;
		// Agrega los lingotes de oro a la mochila.
		for (int i = 0; i < numOro; i++)
			objetos.add(new Lingote("oro"));
	}

	/**
	 * La clase Objeto representa un objeto que puede ser utilizado en el contexto
	 * del problema de la mochila.
	 * 
	 * Un objeto tiene un peso y un valor, y un método que calcula su valor unitario
	 * (valor/peso).
	 */
	public static class Objeto {
		protected double peso; // Peso del objeto
		protected double valor; // Valor del objeto

		/**
		 * Crea un nuevo objeto con el peso y valor especificados.
		 * 
		 * @param peso  el peso del objeto
		 * @param valor el valor del objeto
		 */
		public Objeto(double peso, double valor) {
			this.peso = peso;
			this.valor = valor;
		}

		/**
		 * Devuelve el peso del objeto.
		 * 
		 * @return el peso del objeto
		 */
		public double getPeso() {
			return peso;
		}

		/**
		 * Establece el peso del objeto.
		 * 
		 * @param peso el peso a establecer
		 */
		public void setPeso(double peso) {
			this.peso = peso;
		}

		/**
		 * Devuelve el valor del objeto.
		 * 
		 * @return el valor del objeto
		 */
		public double getValor() {
			return valor;
		}

		/**
		 * Establece el valor del objeto.
		 * 
		 * @param valor el valor a establecer
		 */
		public void setValor(double valor) {
			this.valor = valor;
		}

		/**
		 * Calcula el valor unitario del objeto (valor/peso).
		 * 
		 * @return el valor unitario del objeto
		 */
		public double getValorUnitario() {
			return valor / peso;
		}

		/**
		 * Devuelve una representación en cadena del objeto.
		 * 
		 * @return una representación en cadena del objeto
		 */
		@Override
		public String toString() {
			return "Objeto [peso=" + peso + ", valor=" + valor + "]";
		}

		/**
		 * Sobrescribe el método equals de la clase Object. Compara si el objeto actual
		 * es igual al objeto especificado.
		 * 
		 * @param obj el objeto a comparar con el objeto actual
		 * @return true si el objeto especificado es igual al objeto actual; false de lo
		 *         contrario
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Objeto other = (Objeto) obj;
			if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
				return false;
			if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
				return false;
			return true;
		}

	}

	/**
	 * Clase que representa un objeto lingote que hereda de la clase Objeto. Cada
	 * lingote tiene un tipo (oro, plata o bronce), peso y valor asignados según el
	 * tipo.
	 */
	public static class Lingote extends Objeto {

		private String tipo;

		/**
		 * Constructor de la clase Lingote.
		 * 
		 * @param tipo el tipo de lingote, puede ser "oro", "plata" o "bronce"
		 * @throws Exception si el tipo de lingote no es valido
		 */
		public Lingote(String tipo) throws Exception {
			super(0, 0);
			this.tipo = tipo;
			switch (tipo.toLowerCase()) {
			case "oro":
				this.peso = 12.4;
				this.valor = 770358.0;
				break;
			case "plata":
				this.peso = 1;
				this.valor = 743.41;
				break;
			case "bronce":
				this.peso = 9.0;
				this.valor = 400.5;
				break;
			default:
				throw new Exception("Solo existen lingotes de oro, plata o bronce.");
			}
		}

		/**
		 * Devuelve una representación en cadena del lingote.
		 * 
		 * @return una representación en cadena del lingote
		 */
		@Override
		public String toString() {
			return "Lingote [tipo=" + tipo + ", peso=" + peso + ", valor=" + valor + "]";
		}

	}

	/**
	 * Calcula el valor total de una lista de objetos dados.
	 * 
	 * @param objetos La lista de objetos de los cuales se quiere calcular el valor
	 *                total.
	 * @return El valor total de los objetos de la lista.
	 */
	public double getValorMochila(List<Objeto> objetos) {
		double resultado = 0.0;
		for (Objeto objeto : objetos)
			resultado += objeto.getValor();
		return resultado;
	}

	/**
	 * Método que resuelve el problema de la mochila fraccionaria, de orden O(n log n).
	 * 
	 * @return Lista de objetos que se deben meter en la mochila y la fracción de
	 *         cada objeto que se debe meter.
	 */
	public List<Objeto> getMochilaFraccionaria() {
		// Ordena la lista de objetos en orden descendente según su valor unitario
		List<Objeto> objetos = new ArrayList<>(this.objetos);
		objetos.sort(Comparator.comparingDouble(Objeto::getValorUnitario).reversed());
		// Inicializa la capacidad actual y la lista de objetos seleccionados
		double capacidadActual = capacidad;
		List<Objeto> sol = new ArrayList<>();

		// Itera sobre la lista de objetos, añadiendolos enteros, una fracción del mismo
		// o nada, según la capacidad disponible en ese momento
		for (Objeto objeto : objetos) {
			if (objeto.getPeso() <= capacidadActual) {
				capacidadActual -= objeto.getPeso();
				sol.add(objeto);
				if (capacidadActual == 0)
					break;
			} else {
				sol.add(new Objeto(capacidadActual, capacidadActual * objeto.getValorUnitario()));
				break;
			}
		}
		// Devuelve la lista de objetos seleccionados para la mochila
		return sol;
	}

	/**
	 * Método que resuelve el problema de la mochila entera, de orden O(n log n).
	 * 
	 * @return Lista de objetos enteros que se deben meter en la mochila.
	 */
	public List<Objeto> getMochilaEntera() {
		// Ordena la lista de objetos en orden descendente según su valor unitario
		List<Objeto> objetos = new ArrayList<>(this.objetos);
		objetos.sort(Comparator.comparingDouble(Objeto::getValorUnitario).reversed());
		// Inicializa la capacidad actual y la lista de objetos seleccionados
		double capacidadActual = capacidad;
		List<Objeto> sol = new ArrayList<>();

		// Itera sobre la lista de objetos, añadiendolos enteros o nada, según la
		// capacidad disponible en ese momento
		for (Objeto objeto : objetos) {
			if (objeto.getPeso() <= capacidadActual) {
				capacidadActual -= objeto.getPeso();
				sol.add(objeto);
			}
		}
		// Devuelve la lista de objetos seleccionados para la mochila
		return sol;
	}

	/**
	 * Método que resuelve el problema de la mochila de objetos con peso máximo de
	 * la mitad del peso total de la mochila, de orden O(n log n).
	 * 
	 * @return Lista de objetos que se deben meter en la mochila y la fracción de
	 *         cada objeto que se debe meter, o la mitad del objeto si su peso es
	 *         mayor a la mitad de la capacidad restante.
	 */
	public List<Objeto> getMochilaMitad() {
		// Ordena la lista de objetos en orden descendente según su valor unitario
		List<Objeto> objetos = new ArrayList<>(this.objetos);
		objetos.sort(Comparator.comparingDouble(Objeto::getValorUnitario).reversed());
		// Inicializa la capacidad actual y la lista de objetos seleccionados
		double capacidadActual = capacidad;
		List<Objeto> sol = new ArrayList<>();

		// Itera sobre la lista de objetos, añadiendolos enteros, la mitad, o nada según
		// la capacidad disponible en ese momento
		for (Objeto objeto : objetos) {
			if (objeto.getPeso() <= capacidadActual) {
				capacidadActual -= objeto.getPeso();
				sol.add(objeto);
			} else if (objeto.getPeso() / 2 <= capacidadActual) {
				capacidadActual -= objeto.getPeso() / 2;
				sol.add(new Objeto(objeto.getPeso() / 2, objeto.getPeso() / 2 * objeto.getValorUnitario()));
			}
			if (capacidadActual == 0)
				break;
		}
		// Devuelve la lista de objetos seleccionados para la mochila
		return sol;
	}

}
