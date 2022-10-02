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

	public Grafo(int n) {

		vertices = new ArrayList<Vertice>();
		listaDeVecinos = new ArrayList<HashSet<Integer>>();

		for (int i = 0; i < n; i++) {
			vertices.add(new Vertice(i, null));
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
			while (scanner.hasNextDouble() && i < vertices.size()) {
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

		if (verticeInicio >= vertices.size() || verticeDestino >= vertices.size()) {
			throw new RuntimeException();
		}

		if (verticeInicio != verticeDestino) {
			listaDeVecinos.get(verticeInicio).add(verticeDestino);
			listaDeVecinos.get(verticeDestino).add(verticeInicio);
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
		for(int i=0; i<vertices.size();i++) {
			for (int j =0; j<vertices.size(); j++) {
				agregarArista(i,j);
			}
		}
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
	
	
	
	
	
	
	
	
	
	
	
}
