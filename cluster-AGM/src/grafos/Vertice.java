package grafos;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Vertice {
	
	int identificacionVertice;
	Coordinate coordenada;
	
	public Vertice(int identificacion, Coordinate coordenada) {

		this.identificacionVertice = identificacion;
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
	
	public int getIdentificacionVertice() {
		return identificacionVertice;
	}
	
	public Coordinate getCoordenada() {
		return coordenada;
	}

	@Override
	public String toString() {
		return "Vertice [Vertice=" + identificacionVertice + ", coordenada=" + coordenada + "]";
	}
	
	


}
