package test;

import static org.junit.Assert.*;
import org.junit.Test;
import grafos.AGM;
import grafos.Grafo;

public class AGMTest {
	
	private Grafo grafo;
	
	private void inicializarGrafo() {
		grafo = new Grafo(5);
		grafo.crearGrafoCompleto();
	}

	@Test
	public void cantidadAristasDiferentesTest() {
		inicializarGrafo();
		System.out.println(grafo.getListaVecinos().toString());
		
		Grafo agm= AGM.subGrafoAGM(grafo, 1);
		System.out.println(agm.getListaVecinos().toString());
		
		assertTrue(grafo.Vecinos(0) != agm.Vecinos(0));
		
	}
	
	@Test
	public void getVecinoMasBaratoNOmarcadoTest() {
		inicializarGrafo();
		
		assertEquals(2, AGM.getVecinoMasBaratoNOmarcado(grafo, 1));
		
	}

	
		
		
	

	
	
	

}
