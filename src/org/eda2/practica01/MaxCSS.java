package org.eda2.practica01;

/**
 * Clase que representa los diferentes metodos para calcular la Subsecuencia
 * Máxima de un array.
 * 
 * @author equipo-jfm112-pgr866
 */
public class MaxCSS {

	/**
	 * Clase que representa una subsecuencia, con su inicio, fin y suma.
	 */
	static class Subsequence {
		int start;
		int end;
		int sum;

		/**
		 * Constructor por defecto Subsequence
		 * 
		 * Crea una secuencia nula
		 */
		public Subsequence() {
		}

		/**
		 * Constructor Subsequence
		 * 
		 * @param start es el indice de inicio de la subsecuencia
		 * @param end   es el indice de final de la subsecuencia
		 */
		public Subsequence(int start, int end, int sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
		}

		/**
		 * Metodo que comprueba si dos secuencias son iguales
		 * 
		 * @param obj cualquer objeto Object
		 * @return true si las dos secuencias son iguales
		 * @return false si las dos secuencias no son iguales
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Subsequence other = (Subsequence) obj;
			if (end != other.end)
				return false;
			if (start != other.start)
				return false;
			if (sum != other.sum)
				return false;
			return true;
		}
	}

	/**
	 * Clase que representa tres subsecuencias, la izquierda, la derecha y la
	 * maxima, además de la suma total.
	 */
	static class Subsequences {
		Subsequence max;
		Subsequence left;
		Subsequence right;
		int total;

		/**
		 * Constructor de Subsequences
		 * 
		 * @param max   es la subsecuencia maxima
		 * @param left  es la subsecuencia izquierda
		 * @param right es la subsecuencia derecha
		 */
		public Subsequences(Subsequence max, Subsequence left, Subsequence right, int total) {
			this.max = max;
			this.left = left;
			this.right = right;
			this.total = total;
		}

		/**
		 * Metodo que comprueba si dos Subsequences son iguales
		 * 
		 * @param obj cualquer objeto Object
		 * @return true si las dos Subsequences son iguales
		 * @return false si las dos Subsequences no son iguales
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Subsequences other = (Subsequences) obj;
			if (left == null) {
				if (other.left != null)
					return false;
			} else if (!left.equals(other.left))
				return false;
			if (max == null) {
				if (other.max != null)
					return false;
			} else if (!max.equals(other.max))
				return false;
			if (right == null) {
				if (other.right != null)
					return false;
			} else if (!right.equals(other.right))
				return false;
			if (total != other.total)
				return false;
			return true;
		}
	}

	/**
	 * Clase que representa las distintas sumas de una subsecuencia
	 */
	static class SumSubsequence {
		int totalSum;
		int maxSum;
		int maxPrefix;
		int maxSuffix;

		/**
		 * Constructor SumSubsequence
		 * 
		 * @param totalSum  es la suma total de la subsecuencia
		 * @param maxSum    es el maximo valor actualizado entre los anteriores maxSum
		 *                  izquierdo y derecho y la suma del sufijo izquierdo y el
		 *                  prefijo derecho
		 * @param maxPrefix es el maximo prefijo actual
		 * @param maxSuffix es el maximo sufijo actual
		 */
		public SumSubsequence(int totalSum, int maxSum, int maxPrefix, int maxSuffix) {
			this.totalSum = totalSum;
			this.maxSum = maxSum;
			this.maxPrefix = maxPrefix;
			this.maxSuffix = maxSuffix;
		}

		/**
		 * Metodo que comprueba si dos SumSubsequence son iguales
		 * 
		 * @param obj cualquer objeto Object
		 * @return true si las dos SumSubsequence son iguales
		 * @return false si las dos SumSubsequence no son iguales
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SumSubsequence other = (SumSubsequence) obj;
			if (maxPrefix != other.maxPrefix)
				return false;
			if (maxSuffix != other.maxSuffix)
				return false;
			if (maxSum != other.maxSum)
				return false;
			if (totalSum != other.totalSum)
				return false;
			return true;
		}
	}

	/**
	 * Metodo iterativo que localiza la secuencia máxima de un array, de orden O(n3)
	 * 
	 * @param arr   es el array en cuestion
	 * 
	 * @return maxSubsequence es la instancia del resultado de la maxima
	 *         subsecuencia encontrada
	 */
	public static Subsequence maxSubsequenceSumIterativeCubic(int[] arr) {
		Subsequence maxSubsequence = new Subsequence();
		maxSubsequence.sum = 0;

		for (int i = 0; i < arr.length; i++)
			for (int j = i; j < arr.length; j++) {
				int thisSum = 0;
				for (int k = i; k <= j; k++)
					thisSum += arr[k];

				if (thisSum > maxSubsequence.sum) {
					maxSubsequence.sum = thisSum;
					maxSubsequence.start = i;
					maxSubsequence.end = j;
				}
			}
		return maxSubsequence;
	}

	/**
	 * Metodo iterativo que localiza la secuencia máxima de un array, de orden O(n2)
	 * 
	 * @param arr   es el array en cuestion
	 * 
	 * @return maxSubsequence es la instancia del resultado de la maxima
	 *         subsecuencia encontrada
	 */
	public static Subsequence maxSubsequenceSumIterativeQuadratic(int[] arr) {
		Subsequence maxSubsequence = new Subsequence();
		maxSubsequence.sum = 0;

		for (int i = 0; i < arr.length; i++) {
			int thisSum = 0;
			for (int j = i; j < arr.length; j++) {
				thisSum += arr[j];

				if (thisSum > maxSubsequence.sum) {
					maxSubsequence.sum = thisSum;
					maxSubsequence.start = i;
					maxSubsequence.end = j;
				}
			}
		}
		return maxSubsequence;
	}

	/**
	 * Metodo iterativo que localiza la secuencia máxima de un array, de orden O(n)
	 * 
	 * @param arr   es el array en cuestion
	 * 
	 * @return maxSubsequence es la instancia del resultado de la maxima
	 *         subsecuencia encontrada
	 */
	public static Subsequence maxSubsequenceSumIterativeLinear(int[] arr) {
		Subsequence maxSubsequence = new Subsequence();
		maxSubsequence.sum = 0;
		int thisSum = 0;

		for (int i = 0, j = 0; j < arr.length; j++) {
			thisSum += arr[j];
			if (thisSum > maxSubsequence.sum) {
				maxSubsequence.sum = thisSum;
				maxSubsequence.start = i;
				maxSubsequence.end = j;
			} else if (thisSum < 0) {
				i = j + 1;
				thisSum = 0;
			}
		}
		return maxSubsequence;
	}

	/**
	 * Metodo privado mediante el cual se realiza la recursividad, y que localiza la
	 * secuencia máxima de un array, de orden O(n log n)
	 * 
	 * @param arr   es el array en cuestion
	 * @param left  es el indice del primer elemento de la subsecuencia
	 * @param right es el indice del ultimo elemento de la subsecuencia
	 * 
	 * @return maxSubsequence es la instancia del resultado de la maxima
	 *         subsecuencia encontrada
	 */
	private static Subsequence maxContiguousSubsequenceSumDyV(int[] arr, int left, int right) {
		if (arr.length == 0)
			return new Subsequence(0, 0, 0);
		if (left == right) { // Caso base
			Subsequence maxSubsequence = new Subsequence();
			maxSubsequence.start = left;
			maxSubsequence.end = left;
			maxSubsequence.sum = arr[left];
			return maxSubsequence;
		}

		int center = (left + right) / 2;

		Subsequence leftSubsequence = maxContiguousSubsequenceSumDyV(arr, left, center);
		Subsequence rightSubsequence = maxContiguousSubsequenceSumDyV(arr, center + 1, right);

		Subsequence crossingSubsequence = new Subsequence();
		int leftBorderSum = 0, maxLeftBorderSum = 0;
		crossingSubsequence.start = center + 1;
		for (int i = center; i >= left; i--) {
			leftBorderSum += arr[i];
			if (leftBorderSum > maxLeftBorderSum) {
				maxLeftBorderSum = leftBorderSum;
				crossingSubsequence.start = i;
			}
		}

		int rightBorderSum = 0, maxRightBorderSum = 0;
		crossingSubsequence.end = center;
		for (int i = center + 1; i <= right; i++) {
			rightBorderSum += arr[i];
			if (rightBorderSum > maxRightBorderSum) {
				maxRightBorderSum = rightBorderSum;
				crossingSubsequence.end = i;
			}
		}
		crossingSubsequence.sum = maxLeftBorderSum + maxRightBorderSum;

		if ((leftSubsequence.sum >= rightSubsequence.sum) && (leftSubsequence.sum >= crossingSubsequence.sum))
			return leftSubsequence;
		else if ((rightSubsequence.sum >= leftSubsequence.sum) && (rightSubsequence.sum >= crossingSubsequence.sum))
			return rightSubsequence;
		else
			return crossingSubsequence;

	}

	/**
	 * Metodo publico que llama al metodo privado recursivo, y que localiza la
	 * secuencia máxima de un array, de orden O(n log n)
	 * 
	 * @param arr es el array en cuestion
	 * 
	 * @return la instancia del resultado de la maxima subsecuencia encontrada
	 */
	public static Subsequence maxContiguousSubsequenceSumDyV(int[] arr) {
		return maxContiguousSubsequenceSumDyV(arr, 0, arr.length - 1);
	}

	/**
	 * Metodo privado mediante el cual se realiza la recursividad, y que localiza la
	 * secuencia máxima de un array, de orden O(n)
	 * 
	 * @param arr   es el array en cuestion
	 * @param left  es el indice del primer elemento de la subsecuencia
	 * @param right es el indice del ultimo elemento de la subsecuencia
	 * 
	 * @return devuelve una instacia de SumSubsecuence, que contiene en el atributo
	 *         maxSum la suma de la subsecuencia maxima, pero no localiza sus
	 *         indices
	 */
	private static SumSubsequence maxContiguousSubsequenceSumDyVCompare(int[] arr, int left, int right) {
		if (arr.length == 0)
			return new SumSubsequence(0, 0, 0, 0);
		if (left == right) { // Caso base
			return new SumSubsequence(arr[left], arr[left], arr[left], arr[left]);
		}

		int center = (left + right) / 2;

		SumSubsequence leftSS = maxContiguousSubsequenceSumDyVCompare(arr, left, center);
		SumSubsequence rightSS = maxContiguousSubsequenceSumDyVCompare(arr, center + 1, right);

		int totalSum = leftSS.totalSum + rightSS.totalSum;
		int maxSum = Math.max(Math.max(leftSS.maxSum, rightSS.maxSum), leftSS.maxSuffix + rightSS.maxPrefix);
		int maxPrefix = Math.max(leftSS.maxPrefix, leftSS.totalSum + rightSS.maxPrefix);
		int maxSuffix = Math.max(rightSS.maxSuffix, rightSS.totalSum + leftSS.maxSuffix);

		return new SumSubsequence(totalSum, maxSum, maxPrefix, maxSuffix);
	}

	/**
	 * Metodo publico que llama al metodo privado recursivo, y que localiza la
	 * secuencia máxima de un array, de orden O(n)
	 * 
	 * @param arr es el array en cuestion
	 * 
	 * @return devuelve una instacia de SumSubsecuence, que contiene en el atributo
	 *         maxSum la suma de la subsecuencia maxima, pero no localiza sus
	 *         indices
	 */
	public static SumSubsequence maxContiguousSubsequenceSumDyVCompare(int[] arr) {
		return maxContiguousSubsequenceSumDyVCompare(arr, 0, arr.length - 1);
	}

	/**
	 * Metodo privado mediante el cual se realiza la recursividad, y que localiza la
	 * secuencia máxima de un array, de orden O(n)
	 * 
	 * @param arr   es el array en cuestion
	 * @param left  es el indice del primer elemento de la subsecuencia
	 * @param right es el indice del ultimo elemento de la subsecuencia
	 * 
	 * @return devuelve una instacia de Subsequences, la cual contiene en el
	 *         atributo max la subsecuencia maxima, con su suma y sus indices
	 *         localizados
	 */
	private static Subsequences maxContiguousSubsequenceSumDyVLinear(int[] arr, int left, int right) {
		if (arr.length == 0)
			return new Subsequences(new Subsequence(0, 0, 0), new Subsequence(0, 0, 0), new Subsequence(0, 0, 0), 0);
		if (left == right) { // Caso base
			Subsequence auxBase = new Subsequence();
			auxBase.start = left;
			auxBase.end = left;
			auxBase.sum = arr[left];
			return new Subsequences(auxBase, auxBase, auxBase, arr[left]);
		}

		int center = (left + right) / 2;

		Subsequences leftSS = maxContiguousSubsequenceSumDyVLinear(arr, left, center);
		Subsequences rightSS = maxContiguousSubsequenceSumDyVLinear(arr, center + 1, right);

		int total = leftSS.total + rightSS.total;

		Subsequence izqSS = new Subsequence();
		Subsequence auxLeft = new Subsequence();
		auxLeft.start = left;
		auxLeft.end = rightSS.left.end;
		auxLeft.sum = leftSS.total + rightSS.left.sum;
		if (leftSS.left.sum >= auxLeft.sum)
			izqSS = leftSS.left;
		else
			izqSS = auxLeft;

		Subsequence derSS = new Subsequence();
		Subsequence auxRight = new Subsequence();
		auxRight.start = leftSS.right.start;
		auxRight.end = right;
		auxRight.sum = rightSS.total + leftSS.right.sum;
		if (rightSS.right.sum >= auxRight.sum)
			derSS = rightSS.right;
		else
			derSS = auxRight;

		Subsequence maxSS = new Subsequence();
		Subsequence auxCrossing = new Subsequence();
		auxCrossing.start = leftSS.right.start;
		auxCrossing.end = rightSS.left.end;
		auxCrossing.sum = leftSS.right.sum + rightSS.left.sum;
		if ((leftSS.max.sum >= rightSS.max.sum) && (leftSS.max.sum >= auxCrossing.sum)) {
			maxSS = leftSS.max;
		} else if ((rightSS.max.sum >= leftSS.max.sum) && (rightSS.max.sum >= auxCrossing.sum)) {
			maxSS = rightSS.max;
		} else {
			maxSS = auxCrossing;
		}

		return new Subsequences(maxSS, izqSS, derSS, total);
	}

	/**
	 * Metodo publico que llama al metodo privado recursivo, y que localiza la
	 * secuencia máxima de un array, de orden O(n)
	 * 
	 * @param arr es el array en cuestion
	 * 
	 * @return devuelve una instacia de Subsequences, la cual contiene en el
	 *         atributo max la subsecuencia maxima, con su suma y sus indices
	 *         localizados
	 */
	public static Subsequences maxContiguousSubsequenceSumDyVLinear(int[] arr) {
		return maxContiguousSubsequenceSumDyVLinear(arr, 0, arr.length - 1);
	}
}
