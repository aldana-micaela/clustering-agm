package grafos;

import java.util.ArrayList;

public class AGM {
	private Grafo grafo;
	private Grafo agm;
	private ArrayList<Integer> Marcados;
	private ArrayList<Integer> NoMarcados;
	private int cantidadDeMarcados;

	public AGM(Grafo g) {
		cantidadDeMarcados = 0;
		grafo = g;
		agm = new Grafo(grafo.getListaVecinos().size());
		Marcados = new ArrayList<Integer>();
		NoMarcados = new ArrayList<Integer>();
		inicializar(); 

	}

	private void inicializar() {
		int vertice = 0;
		cargarNoMarcados();
		Marcados.add(vertice); // marcamos el vertice 0 como comienzo del arbol
		NoMarcados.remove(vertice); // sacamos de los no marcados al vertice de inicio
	}

	private void cargarNoMarcados() {
		for (int i = 0; i < grafo.getListaVecinos().size(); i++) {
			NoMarcados.add(i);
		}

	}

//crea AGM
	public void construirArbol() { //

		while (cantidadDeMarcados < grafo.getListaVecinos().size()) {
			buscarPesoMinimo();

		}

	}

//alg de prim o intento xd
	private void buscarPesoMinimo() { 
		double peso = 0;

		for (int marcado = 0; marcado < Marcados.size(); marcado++) {
			for (int noMarcado = 0; noMarcado < NoMarcados.size(); noMarcado++) {
				if (peso == 0) {
					peso = grafo.verValor(Marcados.get(marcado), NoMarcados.get(noMarcado));
				} else {

					peso = Math.min(peso, grafo.verValor(Marcados.get(marcado), NoMarcados.get(noMarcado)));

				}

//				}

			}
		}
		cantidadDeMarcados++;
		agregarEnlaceDeMenorPeso(peso);

	}

	private void agregarEnlaceDeMenorPeso(double pesoMenorElegido) {

		for (int marcado = 0; marcado < Marcados.size(); marcado++) {
			for (int noMarcado = 0; noMarcado < NoMarcados.size(); noMarcado++) {

				if (grafo.verValor(Marcados.get(marcado), NoMarcados.get(noMarcado)) == pesoMenorElegido) {
					agm.agregarArista(Marcados.get(marcado), NoMarcados.get(noMarcado)); // me faltaria agregar el peso
					Marcados.add(NoMarcados.get(noMarcado));
					NoMarcados.remove(NoMarcados.get(noMarcado));

				}

			}
		}
	}



	public Grafo dameGrafoAGM() {
		return agm;
	}


}
