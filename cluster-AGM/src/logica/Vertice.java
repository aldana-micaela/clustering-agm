package logica;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Vertice {
	
	Coordinate coordenada;
	
	public Vertice(Coordinate coordenada) {

		this.coordenada= coordenada;
		
	}
	

	public Double getLatitud() {
		return coordenada.getLat();

	}

	public Double getLongitud() {
		return coordenada.getLon();

	}
	
	public void agregarCoordenada(double lat, double lon) {
		coordenada= new Coordinate (lat, lon);
	}
	
	
	public Coordinate getCoordenada() {
		return coordenada;
	}

	


}
