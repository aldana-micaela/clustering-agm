package test;

import static org.junit.Assert.*;

import org.junit.Test;
import grafos.AGM;
import grafos.Grafo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import grafos.AGM;
import grafos.Grafo;

public class AGMTest {

	private Grafo grafo;
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void subGrafoAgmTest() {
		grafo = new Grafo(10);
		
		AGM.subGrafoAGM(grafo, 10);
		
		
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void subGrafoAgmTest2() {
		grafo = new Grafo(10);
		
		AGM.subGrafoAGM(grafo, 20);
		
		
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void subGrafoAgmTest3() {
		grafo = new Grafo(10);
		
		AGM.subGrafoAGM(grafo, -1);
		
	}
	
	

	@Test
	public void getVecinoMasBaratoNOmarcadoTest() {
		grafo = new Grafo(21);
		grafo.agregarArista(0, 20);
		grafo.agregarArista(19, 20); //arista mas barata
		
		Grafo agm = AGM.subGrafoAGM(grafo,0);
		assertTrue(agm.existeArista(19, 20));
		assertFalse(agm.existeArista(0, 20));
		assertTrue(agm.existeArista(19, 0));
		

	}



	
		public void inicilizacion(){
			grafo = new Grafo(4);
			grafo.agregarArista(0, 1);
			grafo.agregarArista(0, 2);
			grafo.agregarArista(0, 3);
			grafo.agregarArista(1, 2);
			grafo.agregarArista(1, 3);
			grafo.agregarArista(2, 3);
		}

		@Test
		public void agmMismoTamanioGrafo() {
			grafo = new Grafo(4);
			grafo.agregarArista(0, 1);
			grafo.agregarArista(0, 2);
			grafo.agregarArista(0, 3);
			grafo.agregarArista(1, 2);
			grafo.agregarArista(1, 3);
			grafo.agregarArista(2, 3);
			
			Grafo subgrafo = new Grafo(4);
			subgrafo.agregarArista(1, 2);
			subgrafo.agregarArista(0, 1);
			subgrafo.agregarArista(1, 3);

			AGM agm = new AGM();
			agm.subGrafoAGM(grafo, 0);
			System.out.println(
			grafo.getPesoArista(0, 1) );
			System.out.println(grafo.getPesoArista(0, 2) );
			System.out.println(grafo.getPesoArista(0, 3) );
			System.out.println(grafo.getPesoArista(1, 2) );
			System.out.println(grafo.getPesoArista(1, 3) );
			System.out.println(grafo.getPesoArista(2, 3) );

			assertEquals(subgrafo.getLongitud(0),agm.dameGrafo().getLongitud(0));

		}


		




}
