package grafos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Grafo {

	private ArrayList<Vertice> vertices;
	private ArrayList<HashSet<Integer>> listaDeVecinos;
	private ArrayList<Arista> aristas;


	int peso=100;

	public Grafo(int n) {

		vertices = new ArrayList<Vertice>();
		aristas = new ArrayList<Arista>();
		listaDeVecinos = new ArrayList<HashSet<Integer>>();

		for (int i = 0; i < n; i++) {
			vertices.add(new Vertice(null));
			listaDeVecinos.add(new HashSet<Integer>());
		}

		agregarCoordenadasPredeterminadas();

	}

	public void agregarCoordenadasPredeterminadas() {
		leerArchivoYAgregarCoordenadas();
	}

	public void leerArchivoYAgregarCoordenadas() {
		File file = new File("prueba.txt");

		try {
			FileInputStream fis = new FileInputStream(file);
			Scanner scanner = new Scanner(fis);
			int i = 0;
			while (scanner.hasNextDouble() && i < listaDeVecinos.size()) {
				vertices.get(i).agregarCoordenada(scanner.nextDouble(), scanner.nextDouble());
				i++;
			}

			//System.out.println(getVertices().toString());
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void agregarArista(int verticeInicio, int verticeDestino) {

		if (verticeInicio >= listaDeVecinos.size() || verticeDestino >= listaDeVecinos.size()) {
			throw new RuntimeException();
		}

		if (verticeInicio != verticeDestino) {
			listaDeVecinos.get(verticeInicio).add(verticeDestino);
			listaDeVecinos.get(verticeDestino).add(verticeInicio);
			
			aristas.add(new Arista(verticeInicio, verticeDestino, peso));
			peso -=5;
		}

	}

	public void eliminarArista(int verticeInicio, int verticeDestino) {

		if (existeArista(verticeInicio, verticeDestino)) {
			listaDeVecinos.get(verticeInicio).remove(verticeDestino);
			listaDeVecinos.get(verticeDestino).remove(verticeInicio);
		}

	}

	public boolean existeArista(int vertice1, int vertice2) {

		return listaDeVecinos.get(vertice1).contains(vertice2);

	}

	public void crearGrafoCompleto() {
		for(int i=0; i<listaDeVecinos.size();i++) {
			for (int j =0; j<listaDeVecinos.size(); j++) {
				agregarArista(i,j);
			}
		}
	}
	
	public ArrayList<Coordinate> devolverCoordenadasEntreAristas(Arista a) {
		
		ArrayList<Coordinate> c= new ArrayList<Coordinate>();
		int vI= a.verticeInicio;
		int vD= a.verticeDestino;
		
		c.add(vertices.get(vI).getCoordenada());
		c.add(vertices.get(vI).getCoordenada());
		c.add(vertices.get(vD).getCoordenada());
		
		return c;
		
	}
	
	public Arista devolverArista(int n) {
		
		return aristas.get(n);
		
	}
	
	public Double getLatitud(int i) {
		return vertices.get(i).getLatitud();

	}

	public Double getLongitud(int i) {
		return vertices.get(i).getLongitud();

	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public ArrayList<HashSet<Integer>> getListaVecinos(){
	return listaDeVecinos;
	}
	
	public ArrayList<Arista> getAristas() {
		return aristas;
	}

	public int[] Vecinos(int i) {
		// Consulta un vertice y devuelve los vecinos
		return null;
	}

	public int vertices() {
		return vertices.size();
	}

	public double verValor(Integer integer, Integer integer2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
