package logica;

import java.util.LinkedList;

public class AGM {

	static LinkedList<Integer> marcados;
	static Grafo grafoNuevo;

	public static Grafo subGrafoAGM(Grafo g, int k) {
		grafoNuevo = new Grafo(g.getListaVecinos().size());
		marcados = new LinkedList<Integer>();

		marcados.add(k);
		int i = 0;
		while (i < g.getListaVecinos().size() - 1) {
			getParDeVerticesMasBarato(g);
			i++;
		}
		return grafoNuevo;
	}

	public static void getParDeVerticesMasBarato(Grafo g) {
		double menorPeso = 10;
		int[] vecino = new int[2];

		for (Integer verticeInicial : marcados) {

			for (Integer alcanzablesDeLosMarcados : BFS.alcanzables(g, verticeInicial))
				if (!marcados.contains(alcanzablesDeLosMarcados)
						&& (g.getPesoArista(verticeInicial, alcanzablesDeLosMarcados) <= menorPeso)) {

					menorPeso = g.getPesoArista(verticeInicial, alcanzablesDeLosMarcados);
					vecino[0] = verticeInicial;
					vecino[1] = alcanzablesDeLosMarcados;
				}
		}
		grafoNuevo.agregarArista(vecino[0], vecino[1]);
		marcados.add(vecino[1]);
	}

	
	public static Grafo dameGrafo() {
		return grafoNuevo;
		
	}
	
}