package org.eda2.practica02;

import static org.junit.Assert.*;

import org.junit.*;
import java.util.*;
import org.eda2.practica02.Mochila.*;

/**
 * Clase que representa el conjunto de tests para los distintos metodos del
 * problema de la Mochila
 * 
 * @author equipo-jfm112-pgr866
 */
public class TestMochila {

	List<Objeto> objetos0 = new ArrayList<>();
	List<Objeto> objetos1 = new ArrayList<>();
	List<Objeto> objetos2 = new ArrayList<>();
	List<Objeto> objetos3 = new ArrayList<>();
	List<Objeto> objetos4 = new ArrayList<>();
	List<Objeto> objetos5 = new ArrayList<>();
	List<Objeto> objetos6 = new ArrayList<>();
	List<Objeto> objetos7 = new ArrayList<>();
	List<Objeto> objetos8 = new ArrayList<>();

	List<Objeto> esperado0 = new ArrayList<>();
	List<Objeto> esperado1 = new ArrayList<>();
	List<Objeto> esperado2 = new ArrayList<>();
	List<Objeto> esperado3 = new ArrayList<>();
	List<Objeto> esperado4 = new ArrayList<>();
	List<Objeto> esperado5 = new ArrayList<>();
	List<Objeto> esperado6 = new ArrayList<>();
	List<Objeto> esperado7 = new ArrayList<>();
	List<Objeto> esperado8 = new ArrayList<>();

	/**
	 * Configura los objetos necesarios antes de cada prueba.
	 */
	@Before
	public void setUp() {

		objetos1 = new ArrayList<>(Arrays.asList(new Objeto(23, 92), new Objeto(31, 57), new Objeto(29, 49),
				new Objeto(44, 68), new Objeto(53, 60), new Objeto(38, 43), new Objeto(63, 67), new Objeto(85, 84),
				new Objeto(89, 87), new Objeto(82, 72)));

		objetos2 = new ArrayList<>(Arrays.asList(new Objeto(12, 24), new Objeto(7, 13), new Objeto(11, 23),
				new Objeto(8, 15), new Objeto(9, 16)));

		objetos3 = new ArrayList<>(Arrays.asList(new Objeto(56, 50), new Objeto(59, 50), new Objeto(80, 64),
				new Objeto(64, 46), new Objeto(75, 50), new Objeto(17, 5)));

		objetos4 = new ArrayList<>(Arrays.asList(new Objeto(31, 70), new Objeto(10, 20), new Objeto(20, 39),
				new Objeto(19, 37), new Objeto(4, 7), new Objeto(3, 5), new Objeto(6, 10)));

		objetos5 = new ArrayList<>(Arrays.asList(new Objeto(25, 350), new Objeto(35, 400), new Objeto(45, 450),
				new Objeto(5, 20), new Objeto(25, 70), new Objeto(3, 8), new Objeto(2, 5), new Objeto(2, 5)));

		objetos6 = new ArrayList<>(Arrays.asList(new Objeto(41, 442), new Objeto(50, 525), new Objeto(49, 511),
				new Objeto(59, 593), new Objeto(55, 546), new Objeto(57, 564), new Objeto(60, 617)));

		objetos7 = new ArrayList<>(Arrays.asList(new Objeto(70, 135), new Objeto(73, 139), new Objeto(77, 149),
				new Objeto(80, 150), new Objeto(82, 156), new Objeto(87, 163), new Objeto(90, 173), new Objeto(94, 184),
				new Objeto(98, 192), new Objeto(106, 201), new Objeto(110, 210), new Objeto(113, 214),
				new Objeto(115, 221), new Objeto(118, 229), new Objeto(120, 240)));

		objetos8 = new ArrayList<>(
				Arrays.asList(new Objeto(382745, 825594), new Objeto(799601, 1677009), new Objeto(909247, 1676628),
						new Objeto(729069, 1523970), new Objeto(467902, 943972), new Objeto(44328, 97426),
						new Objeto(34610, 69666), new Objeto(698150, 1296457), new Objeto(823460, 1679693),
						new Objeto(903959, 1902996), new Objeto(853665, 1844992), new Objeto(551830, 1049289),
						new Objeto(610856, 1252836), new Objeto(670702, 1319836), new Objeto(488960, 953277),
						new Objeto(951111, 2067538), new Objeto(323046, 675367), new Objeto(446298, 853655),
						new Objeto(931161, 1826027), new Objeto(31385, 65731), new Objeto(496951, 901489),
						new Objeto(264724, 577243), new Objeto(224916, 466257), new Objeto(169684, 369261)));

	}

	/**
	 * Conjunto de pruebas para el problema de la Mochila Fraccionada
	 */
	@Test
	public void testMochilaFraccionada() {

		assertEquals(new Mochila(objetos0, 165).getMochilaFraccionaria(), esperado0);

		esperado1 = new ArrayList<>(Arrays.asList(objetos1.get(0), objetos1.get(1), objetos1.get(2), objetos1.get(3),
				new Objeto(38.0, 43.0188679245283)));
		assertEquals(new Mochila(objetos1, 165).getMochilaFraccionaria(), esperado1);

		esperado2 = new ArrayList<>(Arrays.asList(objetos2.get(2), objetos2.get(0), new Objeto(3.0, 5.625)));
		assertEquals(new Mochila(objetos2, 26).getMochilaFraccionaria(), esperado2);

		esperado3 = new ArrayList<>(Arrays.asList(objetos3.get(0), objetos3.get(1), new Objeto(75.0, 60.0)));
		assertEquals(new Mochila(objetos3, 190).getMochilaFraccionaria(), esperado3);

		esperado4 = new ArrayList<>(Arrays.asList(objetos4.get(0), objetos4.get(1), new Objeto(9.0, 17.55)));
		assertEquals(new Mochila(objetos4, 50).getMochilaFraccionaria(), esperado4);

		esperado5 = new ArrayList<>(Arrays.asList(objetos5.get(0), objetos5.get(1), new Objeto(44.0, 440.0)));
		assertEquals(new Mochila(objetos5, 104).getMochilaFraccionaria(), esperado5);

		esperado6 = new ArrayList<>(
				Arrays.asList(objetos6.get(0), objetos6.get(1), objetos6.get(2), new Objeto(30.0, 308.5)));
		assertEquals(new Mochila(objetos6, 170).getMochilaFraccionaria(), esperado6);

		esperado7 = new ArrayList<>(Arrays.asList(objetos7.get(14), objetos7.get(8), objetos7.get(7), objetos7.get(13),
				objetos7.get(2), objetos7.get(0), objetos7.get(6), new Objeto(83.0, 159.50434782608696)));
		assertEquals(new Mochila(objetos7, 750).getMochilaFraccionaria(), esperado7);

		esperado8 = new ArrayList<>(Arrays.asList(objetos8.get(5), objetos8.get(21), objetos8.get(23), objetos8.get(15),
				objetos8.get(10), objetos8.get(0), objetos8.get(9), objetos8.get(1), objetos8.get(19), objetos8.get(16),
				objetos8.get(3), objetos8.get(22), objetos8.get(12), new Objeto(115091.0, 234762.52284628278)));
		assertEquals(new Mochila(objetos8, 6404180).getMochilaFraccionaria(), esperado8);

	}

	/**
	 * Conjunto de pruebas para el problema de la Mochila Entera
	 */
	@Test
	public void testMochilaEntera() {

		assertEquals(new Mochila(objetos0, 165).getMochilaEntera(), esperado0);

		esperado1 = new ArrayList<>(
				Arrays.asList(objetos1.get(0), objetos1.get(1), objetos1.get(2), objetos1.get(3), objetos1.get(5)));
		assertEquals(new Mochila(objetos1, 165).getMochilaEntera(), esperado1);

		esperado2 = new ArrayList<>(Arrays.asList(objetos2.get(2), objetos2.get(0)));
		assertEquals(new Mochila(objetos2, 26).getMochilaEntera(), esperado2);

		esperado3 = new ArrayList<>(Arrays.asList(objetos3.get(0), objetos3.get(1), objetos3.get(3)));
		assertEquals(new Mochila(objetos3, 190).getMochilaEntera(), esperado3);

		esperado4 = new ArrayList<>(Arrays.asList(objetos4.get(0), objetos4.get(1), objetos4.get(4), objetos4.get(5)));
		assertEquals(new Mochila(objetos4, 50).getMochilaEntera(), esperado4);

		esperado5 = new ArrayList<>(Arrays.asList(objetos5.get(0), objetos5.get(1), objetos5.get(3), objetos5.get(4),
				objetos5.get(5), objetos5.get(6), objetos5.get(6)));
		assertEquals(new Mochila(objetos5, 104).getMochilaEntera(), esperado5);

		esperado6 = new ArrayList<>(Arrays.asList(objetos6.get(0), objetos6.get(1), objetos6.get(2)));
		assertEquals(new Mochila(objetos6, 170).getMochilaEntera(), esperado6);

		esperado7 = new ArrayList<>(Arrays.asList(objetos7.get(14), objetos7.get(8), objetos7.get(7), objetos7.get(13),
				objetos7.get(2), objetos7.get(0), objetos7.get(6), objetos7.get(1)));
		assertEquals(new Mochila(objetos7, 750).getMochilaEntera(), esperado7);

		esperado8 = new ArrayList<>(Arrays.asList(objetos8.get(5), objetos8.get(21), objetos8.get(23), objetos8.get(15),
				objetos8.get(10), objetos8.get(0), objetos8.get(9), objetos8.get(1), objetos8.get(19), objetos8.get(16),
				objetos8.get(3), objetos8.get(22), objetos8.get(12), objetos8.get(6)));
		assertEquals(new Mochila(objetos8, 6404180).getMochilaEntera(), esperado8);

	}

	/**
	 * Conjunto de pruebas para el problema de la Mochila Mitad
	 */
	@Test
	public void testMochilaMitad() {

		assertEquals(new Mochila(objetos0, 165).getMochilaMitad(), esperado0);

		esperado1 = new ArrayList<>(Arrays.asList(objetos1.get(0), objetos1.get(1), objetos1.get(2), objetos1.get(3),
				new Objeto(26.5, 30.0)));
		assertEquals(new Mochila(objetos1, 165).getMochilaMitad(), esperado1);

		esperado2 = new ArrayList<>(Arrays.asList(objetos2.get(2), objetos2.get(0)));
		assertEquals(new Mochila(objetos2, 26).getMochilaMitad(), esperado2);

		esperado3 = new ArrayList<>(
				Arrays.asList(objetos3.get(0), objetos3.get(1), new Objeto(40.0, 32.0), new Objeto(32.0, 23.0)));
		assertEquals(new Mochila(objetos3, 190).getMochilaMitad(), esperado3);

		esperado4 = new ArrayList<>(Arrays.asList(objetos4.get(0), objetos4.get(1), objetos4.get(4), objetos4.get(5)));
		assertEquals(new Mochila(objetos4, 50).getMochilaMitad(), esperado4);

		esperado5 = new ArrayList<>(Arrays.asList(objetos5.get(0), objetos5.get(1), new Objeto(22.5, 225.0),
				objetos5.get(3), new Objeto(12.5, 35.0), objetos5.get(5), new Objeto(1.0, 2.5)));
		assertEquals(new Mochila(objetos5, 104).getMochilaMitad(), esperado5);

		esperado6 = new ArrayList<>(
				Arrays.asList(objetos6.get(0), objetos6.get(1), objetos6.get(2), new Objeto(30.0, 308.5)));
		assertEquals(new Mochila(objetos6, 170).getMochilaMitad(), esperado6);

		esperado7 = new ArrayList<>(Arrays.asList(objetos7.get(14), objetos7.get(8), objetos7.get(7), objetos7.get(13),
				objetos7.get(2), objetos7.get(0), objetos7.get(6), new Objeto(57.5, 110.5)));
		assertEquals(new Mochila(objetos7, 750).getMochilaMitad(), esperado7);

		esperado8 = new ArrayList<>(Arrays.asList(objetos8.get(5), objetos8.get(21), objetos8.get(23), objetos8.get(15),
				objetos8.get(10), objetos8.get(0), objetos8.get(9), objetos8.get(1), objetos8.get(19), objetos8.get(16),
				objetos8.get(3), objetos8.get(22), objetos8.get(12), objetos8.get(6)));
		assertEquals(new Mochila(objetos8, 6404180).getMochilaMitad(), esperado8);

	}

	/**
	 * Conjunto de pruebas para el problema de la Mochila Fraccionada usando
	 * lingotes
	 * 
	 * @throws Exception si el tipo de lingote no es valido
	 */
	@Test
	public void testLingotes() throws Exception {

		assertEquals(new Mochila(new ArrayList<>(), 50).getMochilaFraccionaria(), esperado0);

		for (int i = 0; i < 2; i++)
			esperado1.add(new Lingote("oro"));
		for (int i = 0; i < 1; i++)
			esperado1.add(new Lingote("plata"));
		for (int i = 0; i < 2; i++)
			esperado1.add(new Lingote("bronce"));
		esperado1.add(new Objeto(6.200000000000003, 275.90000000000015));
		assertEquals(new Mochila(2, 1, 10, 50).getMochilaFraccionaria(), esperado1);

		for (int i = 0; i < 4; i++)
			esperado2.add(new Lingote("oro"));
		for (int i = 0; i < 30; i++)
			esperado2.add(new Lingote("plata"));
		for (int i = 0; i < 2; i++)
			esperado2.add(new Lingote("bronce"));
		esperado2.add(new Objeto(2.3999999999999915, 106.79999999999961));
		assertEquals(new Mochila(4, 30, 10, 100).getMochilaFraccionaria(), esperado2);

		for (int i = 0; i < 1; i++)
			esperado3.add(new Lingote("oro"));
		for (int i = 0; i < 30; i++)
			esperado3.add(new Lingote("plata"));
		for (int i = 0; i < 17; i++)
			esperado3.add(new Lingote("bronce"));
		esperado3.add(new Objeto(4.599999999999994, 204.69999999999976));
		assertEquals(new Mochila(1, 30, 100, 200).getMochilaFraccionaria(), esperado3);
	}

	/**
	 * Conjunto de pruebas para el problema de la Mochila Fraccionada usando un
	 * numero de lingotes infinitos
	 * 
	 * @throws Exception si el tipo de lingote no es valido
	 */
	@Test
	public void testLingotesInfinitos() throws Exception {
		for (int i = 0; i < 8; i++)
			esperado1.add(new Lingote("oro"));
		esperado1.add(new Objeto(0.7999999999999936, 49700.51612903186));
		assertEquals(new Mochila(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 100).getMochilaFraccionaria(),
				esperado1);
	}

}
