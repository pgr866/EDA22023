package org.eda2.practica01;

import static org.junit.Assert.*;
import org.junit.Test;
import org.eda2.practica01.MaxCSS.*;

/**
 * Clase que representa el conjunto de tests para los distintos metodos de Subsecuencia Maxima
 * 
 * @author equipo-jfm112-pgr866
 */
public class TestSubsecuencia {
	
	// secuencia original
	int[] dias1 = { 29, -7, 14, 31, 1, -47, 30, 7, -39, 23, -20, -36, -41, 27, 15, -34, 48, 35, -46, -16, 32, 18, 5, -33, 27, -28, -22, 12, 11, -42, 13 };
	Subsequence resultado1 = new Subsequence(13, 17, 91);
	
	// 50 lementos
	int[] dias2 = { -27, 43, 7, 31, -17, -28, -23, 13, 27, -21,
					-39, 8, 35, 15, -6, -10, -25, -40, 42, -8,
					49, -5, 41, -27, 13, 25, 18, -22, 45, -5,
					-10, 20, -34, -1, -35, -39, 27, -23, -44, -8,
					-10, 28, -37, -48, -44, 31, 7, 19, -17, -23 };
	Subsequence resultado2 = new Subsequence(18, 31, 176);
	
	// 100 elementos
	int[] dias3 = { -34, 15, -27, 44, -40, -23, 33, 43, -23, 12, -50, 13, -28, 41, 3, -17, -31, 24, -4, 27,
					5, 45, -2, -23, -16, 49, -27, -33, -50, 11, 6, 49, -3, 16, -48, -50, 47, -39, 44, -35,
					-28, -42, 49, -4, 3, -36, 16, -30, -28, 50, -42, -14, 34, 33, 44, -42, -42, -27, -26, 35,
					-17, 14, 28, -23, 31, 15, 26, -48, -20, -3, -38, 4, -3, -43, 18, -24, 47, 26, 11, 30,
					-36, -49, 32, -2, 27, -3, -5, 45, -8, -15, 23, 11, -45, -20, -29, 11, -41, -45, 27, -37 };
	Subsequence resultado3 = new Subsequence(76, 91, 134);
	
	// 200 elementos
	int[] dias4 = { -17, -48, 49, -37, 7, 3, -42, 39, -10, -50, 20, 26, -14, 0, -22, -3, -36, -49, 19, 14,
					-31, -24, 43, -23, 24, 41, 13, 8, -48, -33, -36, -33, -49, -45, -22, 23, 4, 38, 34, -10,
					22, 34, -16, 10, 39, -28, 25, 15, 10, -29, -39, 14, -14, -14, -16, 9, 1, 39, -36, 14,
					-9, -4, 12, -5, -21, 39, 28, 47, 24, -40, -44, 21, -41, -4, -29, 17, 0, -21, -9, 36,
					-11, 11, 0, 16, 18, 12, 26, -50, -49, 29, 19, 26, -15, -38, -16, 16, 20, -14, -50, -37,
					-18, 25, -42, 46, -41, -37, 12, -7, -23, 5, -1, -22, -18, 32, 15, 34, 0, 48, 21, 38,
					-12, -24, -25, 12, 41, 43, 21, 47, 50, -43, -45, 45, 29, -12, -46, 11, 40, 6, -25, -39,
					-6, 33, -4, -35, -11, -11, -49, 0, 43, 21, -11, 46, 29, -9, -22, 16, -27, 27, -27, 49,
					-38, -17, 36, 14, 33, 40, 21, -29, -32, -14, -6, -17, -14, 34, 39, -42, 23, 49, -40, -17,
					32, 43, 15, -13, -3, -9, -36, 32, 16, 19, 6, -23, -27, -1, 13, -29, 4, -46, -3, -1 };
	Subsequence resultado4 = new Subsequence(113, 190, 439);
	
	// array vacio
	int[] dias5 = { };
	Subsequence resultado5 = new Subsequence(0, 0, 0);
	
	// todos los elementos negativos
	int[] dias6 = { -31, -45, -39, -42, -2, -34, -17, -19, -36, -19,
					-24, -11, -12, -45, -22, -34, -18, -14, -2, -6,
					-33, -49, 0, -42, -48, -36, -11, -13, -36, -30,
					-12, -42, -43, -33, -42, -42, -18, -4, -49, -26,
					-30, -42, -26, -14, -15, -39, -16, -36, -32, -49,
					-1, -38, -50, -29, -50, -9, -35, -38, -27, -5,
					-24, -39, -13, -23, -34, -37, -18, -10, -41, -12,
					-12, -33, -30, -45, -32, -43, -28, -48, -6, -44,
					-50, -49, -25, -13, -17, -6, -41, -48, -37, -28,
					-28, -14, -48, -25, -49, -23, -17, -23, -27, -39 };
	Subsequence resultado6 = new Subsequence(0, 0, 0);
	
	/**
	 * Conjunto de pruebas para el metodo iterativo de Subsecuencia Maxima de orden n3
	 */
	@Test
	public void testSubMaxCubic() {
		assertEquals(MaxCSS.maxSubsequenceSumIterativeCubic(dias1), resultado1);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeCubic(dias2), resultado2);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeCubic(dias3), resultado3);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeCubic(dias4), resultado4);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeCubic(dias5), resultado5);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeCubic(dias6), resultado6);
	}
	
	/**
	 * Conjunto de pruebas para el metodo iterativo de Subsecuencia Maxima de orden n2
	 */
	@Test
	public void testSubMaxQuadratic() {
		assertEquals(MaxCSS.maxSubsequenceSumIterativeQuadratic(dias1), resultado1);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeQuadratic(dias2), resultado2);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeQuadratic(dias3), resultado3);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeQuadratic(dias4), resultado4);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeQuadratic(dias5), resultado5);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeQuadratic(dias6), resultado6);
	}

	/**
	 * Conjunto de pruebas para el metodo iterativo de Subsecuencia Maxima de orden n
	 */
	@Test
	public void testSubMaxLinear() {
		assertEquals(MaxCSS.maxSubsequenceSumIterativeLinear(dias1), resultado1);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeLinear(dias2), resultado2);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeLinear(dias3), resultado3);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeLinear(dias4), resultado4);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeLinear(dias5), resultado5);
		assertEquals(MaxCSS.maxSubsequenceSumIterativeLinear(dias6), resultado6);
	}
	
	/**
	 * Conjunto de pruebas para el metodo recursivo de Subsecuencia Maxima de orden n log n
	 */
	@Test
	public void testSubMaxDyVOrdenNLogN() {
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyV(dias1), resultado1);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyV(dias2), resultado2);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyV(dias3), resultado3);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyV(dias4), resultado4);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyV(dias5), resultado5);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyV(dias6).sum, 0);
	}
	
	/**
	 * Conjunto de pruebas para el metodo recursivo de Subsecuencia Maxima de orden n
	 */
	@Test
	public void testSubMaxDyVOrdenN() {
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVCompare(dias1).maxSum, 91);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVCompare(dias2).maxSum, 176);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVCompare(dias3).maxSum, 134);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVCompare(dias4).maxSum, 439);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVCompare(dias5).maxSum, 0);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVCompare(dias6).maxSum, 0);
		
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVLinear(dias1).max, resultado1);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVLinear(dias2).max, resultado2);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVLinear(dias3).max, resultado3);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVLinear(dias4).max, resultado4);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVLinear(dias5).max, resultado5);
		assertEquals(MaxCSS.maxContiguousSubsequenceSumDyVLinear(dias6).max.sum, 0);
	}
}
