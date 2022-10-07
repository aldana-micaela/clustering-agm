package grafos;


public class ClusterAGM {
	
	public static Grafo cluster(Grafo g, int cantClusters) {
		int i= 1;
		while(i<cantClusters) {
			g.eliminarArista(getAristaMasPesada(g)[0], getAristaMasPesada(g)[1]);
			i++;
		}
		return g;
	}
	
	
	public static int [] getAristaMasPesada(Grafo g) {
		double peso=0;
		int [] verticesMasPesados = new int[2];
		
		for(int i=0; i<g.getListaVecinos().size();i++) {
			
			for (Integer v: g.getListaVecinos().get(i)) {
			
				if(g.getPesoArista(i,v) > peso) {
					
					peso = g.getPesoArista(i,v);
					verticesMasPesados[0]=i;
					verticesMasPesados[1]=v;
					
				}
				
			}
		}
		
		return verticesMasPesados;
		
	}
}
