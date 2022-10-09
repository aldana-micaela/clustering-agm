package logica;

public class ClusterAGM {

	public static Grafo cluster(Grafo g) {
		
		if(g == null)
			throw new RuntimeException();
		else
		g.eliminarArista(getAristaMasPesada(g)[0], getAristaMasPesada(g)[1]);
		
		return g;
	}

	public static int[] getAristaMasPesada(Grafo g) {

		double peso = 0;
		int[] verticesMasPesados = new int[2];

		for (int i = 0; i < g.getListaVecinos().size(); i++) {

			for (Integer v : g.getListaVecinos().get(i)) {
				if (g.getPesoArista(i, v) > peso) {
					peso = g.getPesoArista(i, v);
					verticesMasPesados[0] = i;
					verticesMasPesados[1] = v;
				}
			}
		}
		return verticesMasPesados;
	}
	
	
}
