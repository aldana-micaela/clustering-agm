package test;

import static org.junit.Assert.*;
import org.junit.*;

import grafos.Grafo;

public class GrafoTest {

	Grafo grafo = new Grafo(5);

	@Test
	public void agregarAristaTest() {
		grafo.agregarArista(4, 0);

		assertTrue(grafo.existeArista(4, 0));
		assertTrue(grafo.existeArista(0, 4));

	}

	@Test
	public void agregarAristaEntreVerticesIgualesTest() {
		grafo.agregarArista(4, 4);
		assertFalse(grafo.existeArista(4, 4));

	}

	@Test(expected = Exception.class)
	public void agregarAristasAVerticesFueraDeRango() {
		grafo.agregarArista(5, 5);

	}

	@Test(expected = Exception.class)
	public void agregarAristasAVerticesFueraDeRango1() {
		grafo.agregarArista(5, 4);

	}

	@Test
	public void eliminarAristasTest() {
		grafo.agregarArista(1, 2);
		grafo.eliminarArista(2, 1);

		assertFalse(grafo.existeArista(1, 2));
		assertFalse(grafo.existeArista(2, 1));

	}

	@Test
	public void crearGrafoCompletoTest() {
		grafo.crearGrafoCompleto();

		System.out.println(grafo.getListaVecinos().toString());
		assertTrue(grafo.existeArista(1, 0));
		assertTrue(grafo.existeArista(2, 0));
		assertTrue(grafo.existeArista(3, 0));
		assertTrue(grafo.existeArista(4, 0));
		assertTrue(grafo.existeArista(0, 1));
		assertTrue(grafo.existeArista(0, 2));
		assertTrue(grafo.existeArista(0, 3));
		assertTrue(grafo.existeArista(0, 4));
	}

	@Test
	public void getAristaMasPesada() {

		grafo.crearGrafoCompleto();
		
		System.out.println(grafo.getAristaMasPesada()[0] + " " + grafo.getAristaMasPesada()[1]);
		assertEquals(0, grafo.getAristaMasPesada()[0]);
		assertEquals(4, grafo.getAristaMasPesada()[1]);
	}

	@Test
	public void getPesosAristasTest() {
		grafo.crearGrafoCompleto();

		System.out.println(grafo.getPesoArista(2, 4));
		System.out.println(grafo.getPesoArista(4, 2));

		assertTrue(grafo.getPesoArista(4, 2) == grafo.getPesoArista(2, 4));

	}

}
