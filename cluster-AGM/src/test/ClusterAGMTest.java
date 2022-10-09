package test;

import static org.junit.Assert.*;
import org.junit.*;

import logica.AGM;
import logica.ClusterAGM;
import logica.Grafo;

public class ClusterAGMTest {

	Grafo grafo;
	Grafo cluster;
	
	@Test
	public void getAristaMasPesadaTest() {
		
		grafo = new Grafo(21);
		grafo.agregarArista(0, 20); //arista mas pesada
		grafo.agregarArista(19, 20); 
		
		
		assertEquals(ClusterAGM.getAristaMasPesada(grafo)[0], 0);
		assertEquals(ClusterAGM.getAristaMasPesada(grafo)[1], 20);
		
		
	}
	@Test
	public void clusterTest() {
		
		grafo = new Grafo(21);
		grafo.agregarArista(0, 20); //arista mas pesada
		grafo.agregarArista(19, 20); 
		
		cluster = ClusterAGM.cluster(grafo);
		assertFalse(cluster.existeArista(0, 20));
		assertTrue(cluster.existeArista(19, 20));
		
		
	}
	
	@Test(expected = NullPointerException.class)
	public void clusterConGrafoNuloTest() {
		AGM.subGrafoAGM(grafo, 10);
		
		
	}
	
	
}
