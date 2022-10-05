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
	
	@Test
	public void conexoTest() {
		grafo = inicializarGrafo();
		Set<Integer> alcanzables = BFS.alcanzables(grafo, 0);
		int[] esperados = { 0, 1, 2, 3, 4};
		Assert.iguales(esperados, alcanzables);
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
	
	@Test (expected = IllegalArgumentException.class)
	public void VacioTest() {
		grafo = new Grafo(0);
	}

	private Grafo inicializarGrafo() {
		grafo = new Grafo(5);
		grafo.crearGrafoCompleto();
		return grafo;
	}

}
