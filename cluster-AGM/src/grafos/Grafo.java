package grafos;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Grafo {

	private ArrayList<Vertice> vertices;
	private ArrayList<HashSet<Integer>> listaDeVecinos;
	private double[][] matrizDePesos;
	private boolean esCompleto;

	public Grafo(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("ingresar cantidad de vertices mayor a 1");
		
		vertices = new ArrayList<Vertice>();
		listaDeVecinos = new ArrayList<HashSet<Integer>>();
		matrizDePesos = new double[n][n];
		esCompleto = false;

		for (int i = 0; i < n; i++) {
			vertices.add(new Vertice(null));
			listaDeVecinos.add(new HashSet<Integer>());
		}

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

			matrizDePesos[verticeInicio][verticeDestino] = distanciaDeEuclides(
					vertices.get(verticeInicio).getCoordenada(), vertices.get(verticeDestino).getCoordenada());
			matrizDePesos[verticeDestino][verticeInicio] = distanciaDeEuclides(
					vertices.get(verticeInicio).getCoordenada(), vertices.get(verticeDestino).getCoordenada());

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
		for (int i = 0; i < listaDeVecinos.size(); i++) {
			for (int j = 0; j < listaDeVecinos.size(); j++) {
				agregarArista(i, j);
			}
		}
		esCompleto = true;
	}

	public ArrayList<Coordinate> devolverCoordenadasEntreAristas(int i, int j) {

		ArrayList<Coordinate> c = new ArrayList<Coordinate>();

		c.add(vertices.get(i).getCoordenada());
		c.add(vertices.get(i).getCoordenada());
		c.add(vertices.get(j).getCoordenada());
		return c;

	}

	public double distanciaDeEuclides(Coordinate PuntoUno, Coordinate PuntoDos) {
		// RAIZ( (x2-x1)^2 + (y2-y1)^2 )
		BigDecimal x1 = new BigDecimal(PuntoUno.getLat());
		BigDecimal y1 = new BigDecimal(PuntoUno.getLon());
		BigDecimal x2 = new BigDecimal(PuntoDos.getLat());
		BigDecimal y2 = new BigDecimal(PuntoDos.getLon());
		BigDecimal exponenciar1 = new BigDecimal("0.0");
		BigDecimal exponenciar2 = new BigDecimal("0.0");
		BigDecimal resta1 = new BigDecimal("0.0");
		BigDecimal resta2 = new BigDecimal("0.0");
		BigDecimal sumar = new BigDecimal("0.0");
		double resultado = 0;

		resta1 = x2.subtract(x1);
		resta2 = y2.subtract(y1);
		exponenciar1 = resta1.multiply(resta1);
		exponenciar2 = resta2.multiply(resta2);
		sumar = exponenciar1.add(exponenciar2);
		resultado = Math.sqrt(sumar.doubleValue());

		return resultado;

	}

	public ArrayList<Integer> getVecinos(int i) {
		ArrayList<Integer> vecinos = new ArrayList<Integer>(listaDeVecinos.get(i).size() - 1);

		for (Integer v : listaDeVecinos.get(i))
			vecinos.add(v);

		return vecinos;
	}

	public boolean esCompleto() {
		return esCompleto;
	}

	public Double getLatitud(int i) {
		return vertices.get(i).getLatitud();

	}

	public Double getLongitud(int i) {
		return vertices.get(i).getLongitud();

	}

	public void removerAristas() {
		for (int i = 0; i < listaDeVecinos.size(); i++) {
			listaDeVecinos.get(i).removeAll(listaDeVecinos.get(i));
		}
		esCompleto = false;
	}

	public double getPesoArista(int i, int j) {
		return matrizDePesos[i][j];
	}

	public ArrayList<HashSet<Integer>> getListaVecinos() {
		return listaDeVecinos;
	}

}
