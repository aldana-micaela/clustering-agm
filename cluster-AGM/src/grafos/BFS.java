package grafos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BFS {

	private static ArrayList<Integer> L;
	private static boolean[] marcados;

	public static boolean esConexo(Grafo g) {
		if (g == null)
			throw new IllegalArgumentException("El Grafo que es null");

		return alcanzables(g, 0).size() == g.getListaVecinos().size();

	}

	public static HashSet<Integer> alcanzables(Grafo grafo, int origen) {

		HashSet<Integer> ret = new HashSet<Integer>();
		inicializar(grafo, origen);

		while (L.size() > 0) {
			Integer i = L.get(0);
			marcados[i] = true;
			ret.add(i);

			agregarVecinosPendientes(grafo, i);
			L.remove(0);

		}
		return ret;

	}

	private static void agregarVecinosPendientes(Grafo g, int i) {
		for (Integer vertice : g.getVecinos(i))
			if (marcados[vertice] == false && L.contains(vertice) == false)
				L.add(vertice);
	}

	private static void inicializar(Grafo grafo, int origen) {
		L = new ArrayList<Integer>();
		L.add(origen);
		marcados = new boolean[grafo.getListaVecinos().size()];

	}

}
