package grafos;

import java.util.LinkedList;

public class AGM {
	
	static LinkedList<Integer> marcados= new LinkedList<Integer> ();
	static Grafo grafoNuevo;
		
	
	public static Grafo subGrafoAGM (Grafo g, int k) {
		grafoNuevo= new Grafo(g.getListaVecinos().size());
		marcados.add(k);
		
		int i = 0;
		while(i<g.getListaVecinos().size()-1) {
				elegirAristaMasBarata(g, marcados.getFirst());
			i++;
		}
		marcados.removeAll(marcados);
		return grafoNuevo;
	}

	public static void elegirAristaMasBarata(Grafo g, int vertice){
			int vecinoMasBarato = getVecinoMasBaratoNOmarcado(g, vertice);
			grafoNuevo.agregarArista(vertice, vecinoMasBarato );
	}
		 
	public static int getVecinoMasBaratoNOmarcado(Grafo g, int vertice) {
		double menorPeso= 100;
		int vecino=0;
		
		for (Integer vecinoBarato : BFS.alcanzables(g, vertice)) {
			if(!marcados.contains(vecinoBarato) && vertice != vecinoBarato) {
				
				if(g.getPesoArista(vertice, vecinoBarato) <= menorPeso) {
					menorPeso=g.getPesoArista(vertice, vecinoBarato);
					vecino=vecinoBarato;
				}
			}
		}
		marcados.addFirst(vecino);
		return vecino;
	}
}

