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
	
//	Grafo grafo;
//	AGM agm;

	private void inicializarGrafo() {
		grafo = new Grafo(5);
		grafo.crearGrafoCompleto();
	}

	@Test
	public void cantidadAristasDiferentesTest() {
		inicializarGrafo();
		System.out.println(grafo.getListaVecinos().toString());

		Grafo agm = AGM.subGrafoAGM(grafo, 1);
		System.out.println(agm.getListaVecinos().toString());

		assertTrue(grafo.Vecinos(0) != agm.Vecinos(0));

	}

	@Test
	public void getVecinoMasBaratoNOmarcadoTest() {
		inicializarGrafo();

	//	assertEquals(2, AGM.getVecinoMasBaratoNOmarcado(grafo, 1));
	}



//		@Before
//		public void setUp() throws Exception {
//			grafo = new Grafo(4);
//			grafo.agregarArista(0, 1);
//			grafo.agregarArista(0, 2);
//			grafo.agregarArista(0, 3);
//			grafo.agregarArista(1, 2);
//			grafo.agregarArista(1, 3);
//			grafo.agregarArista(2, 3);
//		}
//
//		@Test
//		public void agmMismoTamanioGrafo() {
//
//			agm = new AGM(grafo);
//			agm.construirArbol();
//
//			assertEquals(grafo.tamano(), agm.dameGrafoAGM().tamano(), 0);
//
//		}
//
//		@Test
//		public void aristaEnCeroAgm() {
//
//			agm = new AGM(grafo);
//			agm.construirArbol();
//
//			assertEquals(0.0, agm.dameGrafoAGM().verValor(0, 3), 0);
//			assertEquals(0.0, agm.dameGrafoAGM().verValor(2, 1), 0);
//			assertEquals(0.0, agm.dameGrafoAGM().verValor(3, 0), 0);
//
//		}
//
//		@Test
//		public void aristaEliminadaAgm() {
//
//			agm = new AGM(grafo);
//			agm.construirArbol();
//
//			assertFalse(agm.dameGrafoAGM().existeArista(1, 2));
//		}
//
//		@Test
//		public void hayAristaAgm() {
//
//			agm = new AGM(grafo);
//			agm.construirArbol();
//
//			assertTrue(agm.dameGrafoAGM().existeArista(0, 1));
//			assertTrue(agm.dameGrafoAGM().existeArista(0, 2));
//			assertTrue(agm.dameGrafoAGM().existeArista(2, 3));
//
//		}
//
//		@Test
//		public void aristasConPesoCorrectoAgm() {
//
//			agm = new AGM(grafo);
//			agm.construirArbol();
//
//			assertEquals(3, agm.dameGrafoAGM().verValor(0, 1), 0);
//			assertEquals(1, agm.dameGrafoAGM().verValor(0, 2), 0);
//			assertEquals(2, agm.dameGrafoAGM().verValor(2, 3), 0);
//
//		}
//
//		@Test
//		public void AgmCompleto() {
//
//			agm = new AGM(grafo);
//			agm.construirArbol();
//
//			assertTrue(agm.dameGrafoAGM().existeArista(0, 1));
//			assertTrue(agm.dameGrafoAGM().existeArista(0, 2));
//			assertTrue(agm.dameGrafoAGM().existeArista(2, 3));
//
//			assertFalse(agm.dameGrafoAGM().existeArista(0, 3));
//			assertFalse(agm.dameGrafoAGM().existeArista(2, 1));
//			assertFalse(agm.dameGrafoAGM().existeArista(3, 1));
//
//		}
//
//		@Test
//		public void grafoEsDistintoDelAgm() {
//
//			agm = new AGM(grafo);
//			agm.construirArbol();
//
//			assertFalse(grafo == agm.dameGrafoAGM());
//
//		}


}
