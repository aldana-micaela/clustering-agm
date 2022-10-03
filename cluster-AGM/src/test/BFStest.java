package test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import grafos.Assert;
import grafos.BFS;
import grafos.Grafo;
import grafos.Vertice;

public class BFStest {
	
	private Grafo grafo;
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testNull() {
		BFS.esConexo(null);
		
	}
	
//	@Test
//	public void conexoTest() {
//		grafo = inicializarGrafo();
//		Set<Integer> alcanzables = BFS.alcanzables(grafo, 0);
//		int[] esperados = { 0, 1, 2, 3};
//		Assert.iguales(esperados, alcanzables);
//	}
	
	@Test
	public void NoconexoTest() {
		grafo = inicializarGrafo();
		grafo.agregarArista(3, 4);
		assertFalse(BFS.esConexo(grafo));
	}
	
	@Test
	public void VacioTest() {
		grafo = new Grafo(0);
		assertTrue(BFS.esConexo(grafo));
	}

	private Grafo inicializarGrafo() {
		grafo = new Grafo(6);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 2);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(3, 4);
		return grafo;
	}

}
