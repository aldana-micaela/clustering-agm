package interfaceGrafo;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import grafos.Grafo;

public class InterfaceGrafo {

	private JFrame frame;
	private JMapViewer mapa;
	private Grafo grafo;
	private MapPolygon poligono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGrafo window = new InterfaceGrafo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceGrafo() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		grafo = new Grafo(5);
		grafo.crearGrafoCompleto();
		crearFrame();
		crearMapa();
		agregarVertices();
	}
	
	private void crearPoligono() {
		ArrayList<Coordinate> coor;
		int n=0;
		
		while(n < grafo.getAristas().size()) {
			
			coor = grafo.devolverCoordenadasEntreAristas(grafo.devolverArista(n));
			poligono = new MapPolygonImpl(coor);
			poligono.getStyle().setColor(Color.RED);
			mapa.addMapPolygon(poligono);
			
			n++;
		}
		
	}
	
	private void crearFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 838, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("JMapViewer");
		frame.setResizable(false);
	}
	

	@SuppressWarnings("deprecation")
	private void crearMapa() {
		
		Coordinate coor= new Coordinate (-34.52848536990668, -58.706273075149376);
		mapa = new JMapViewer();
		frame.getContentPane().add(mapa);
		mapa.setDisplayPosition(coor, 14);
		mapa.setZoomContolsVisible(false);
		crearPoligono();
		
	}
	
	public void agregarVertices() {
		int i = 0;
		
		while (i<grafo.getVertices().size()) {
			mapa.addMapMarker(new MapMarkerDot (new Coordinate(grafo.getLatitud(i), grafo.getLongitud(i))));
			i++; 
			
		}
	}
		

}
