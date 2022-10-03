package grafos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BFS {

	private static ArrayList<Integer> L;
	private static boolean[] marcados;
	
	
	public static  boolean esConexo(Grafo g) {
		if(g==null) {
			throw new IllegalArgumentException("El Grafo que es null");

		} if(g.vertices()==0) {
			return true;
		}
		return false;

//		return g.getLongitud(0) ; //Falta pensar bien esto 
			
	}

	

	public static Set<Integer> alcanzables(Grafo grafo, int origen) {
		Set<Integer> ret = new HashSet<Integer>();
		   inicializar(grafo,origen);
		   
		   while(L.size()>0) {
				Integer i=L.get(0);
				marcados[i]=true;
				ret.add(i);
				
				agregarVecinosPendientes(grafo, i);
				L.remove(0);
				
			}
		return ret;

		
	}

	 private static void agregarVecinosPendientes(Grafo g, int i) {
		 for (int vertice : g.Vecinos(i)) {
			 if(marcados[vertice]==false && L.contains(vertice)==false);
		 }
	 }

	private static void inicializar(Grafo grafo, int origen) {
		L = new ArrayList<Integer>();
		L.add(origen);
		marcados = new boolean[grafo.vertices()];
		
	}

}
