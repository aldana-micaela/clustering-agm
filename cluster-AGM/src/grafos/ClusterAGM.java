package grafos;


public class ClusterAGM {
	
	public static Grafo cluster(Grafo g, int cantClusters) {
		int i= 1;
		while(i<cantClusters) {
			g.eliminarArista(g.getAristaMasPesada()[0], g.getAristaMasPesada()[1]);
			i++;
		}
		return g;
	}
}
