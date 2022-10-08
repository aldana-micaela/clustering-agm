package grafos;

import java.util.LinkedList;

public class AGM {

	static LinkedList<Integer> marcados;
	static Grafo grafoNuevo;

	public static Grafo subGrafoAGM(Grafo g, int k) {
		
		grafoNuevo = new Grafo(g.getListaVecinos().size());
		marcados = new LinkedList<Integer>();
		
		marcados.add(k);

		int i = 0;
		while (i < g.getListaVecinos().size()-1) {
			System.out.println(grafoNuevo.getListaVecinos().toString());
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
	
//	public static Grafo subGrafoAGM (Grafo g, int k) {
//		grafoNuevo= new Grafo(g.getListaVecinos().size());
//		marcados = new LinkedList<Integer>();
//		marcados.add(k);
//		
//		int i = 0;
//		while(i<g.getListaVecinos().size()-1) {
//				elegirAristaMasBarata(g, marcados.getFirst());
//			i++;
//		}
//		return grafoNuevo;
//	}
//
//	public static void elegirAristaMasBarata(Grafo g, int vertice){
//			int vecinoMasBarato = getVecinoMasBaratoNOmarcado(g, vertice);
//			grafoNuevo.agregarArista(vertice, vecinoMasBarato );
//	}
//		 
//	public static int getVecinoMasBaratoNOmarcado(Grafo g, int vertice) {
//		double menorPeso= 100;
//		int vecino=0;
//		
//		for (Integer vecinoBarato : BFS.alcanzables(g, vertice)) {
//			if(!marcados.contains(vecinoBarato) && vertice != vecinoBarato) {
//				
//				if(g.getPesoArista(vertice, vecinoBarato) <= menorPeso) {
//					menorPeso=g.getPesoArista(vertice, vecinoBarato);
//					vecino=vecinoBarato;
//				}
//			}
//		}
//		marcados.addFirst(vecino);
//		return vecino;
//	}

	
	

}
