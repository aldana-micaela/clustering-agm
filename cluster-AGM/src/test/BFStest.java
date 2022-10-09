package test;

import static org.junit.Assert.*;


import java.util.HashSet;
import org.junit.Test;

import logica.BFS;
import logica.Grafo;

public class BFStest {

	private Grafo grafo;
	

	private Grafo inicializarGrafo() {
		grafo = new Grafo(5);
		grafo.crearGrafoCompleto();
		return grafo;
	}

	@Test
	public void NoconexoTest() {
		grafo = inicializarGrafo();
		grafo.crearGrafoCompleto();
		grafo.eliminarArista(1, 0);
		grafo.eliminarArista(1, 2);
		grafo.eliminarArista(1, 3);
		grafo.eliminarArista(1, 4);
		
		assertFalse(BFS.esConexo(grafo));
	}
	
	@Test
	public void conexoTest2() {
		grafo = inicializarGrafo();
		grafo.crearGrafoCompleto();
		grafo.eliminarArista(1, 0);
		grafo.eliminarArista(1, 2);
		// grafo.eliminarArista(1, 3);
		grafo.eliminarArista(1, 4);
		
		assertTrue(BFS.esConexo(grafo));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNull() {
		BFS.esConexo(null);

	}

	@Test
	public void alcanzablesTest() {
		grafo = inicializarGrafo();
		HashSet<Integer> alcanzables = BFS.alcanzables(grafo, 0);
		int[] esperados = { 0, 1, 2, 3, 4 };
		Assert.iguales(esperados, alcanzables);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void valorFueraDeRangoBordeEnAlcanzablesTest() {
		grafo = new Grafo(10);
		
		BFS.alcanzables(grafo, 10);
		
	}
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void valorFueraDeRangoEnAlcanzablesTest() {
		grafo = new Grafo(10);
		
		BFS.alcanzables(grafo, 20);
		
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void valorNegativoEnAlcanzablesTest() {
		grafo = new Grafo(10);
		
		BFS.alcanzables(grafo, -1);
		
	}

	

}
