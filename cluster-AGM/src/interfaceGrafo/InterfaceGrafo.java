package interfaceGrafo;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import grafos.Grafo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class InterfaceGrafo {

	private JFrame frame;
	private JMapViewer mapa;
	private JPanel panel;
	private JButton btnAGM;
	private JButton btnGC;
	private JLabel textoCantVertices;
	private JTextField cantVertices;
	private Grafo grafo;
	private MapPolygon poligono;
	private JButton btnOK;
	private JButton btnX;
	private JLabel excepcion;

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
		crearFrame();
		crearPanel();
		crearMapa();
		campoDeTexto();
		crearBtnAGM();
		crearBtnGrafoCompleto();
		crearBtnX();
		
		
	}
	
	
	private void crearFrame() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 852, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("JMapViewer");
		frame.setResizable(false);
	}
	
	private void crearPanel () {
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 604, 497);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
	}
	

	@SuppressWarnings("deprecation")
	private void crearMapa() {
		
		Coordinate coor= new Coordinate (-34.52848536990668, -58.706273075149376);
		mapa = new JMapViewer();
		mapa.setBounds(0, 0, 595, 487);
		mapa.setDisplayPosition(coor, 14);
		mapa.setZoomContolsVisible(false);
		panel.add(mapa);
		
	}

	private void crearAristas() {
		ArrayList<Coordinate> coor;
		
		for(int i = 0; i < grafo.getListaVecinos().size(); i ++) {
			
			for(Integer v: grafo.getListaVecinos().get(i)) {
				
				coor = grafo.devolverCoordenadasEntreAristas(i,v);
				poligono = new MapPolygonImpl(coor);
				poligono.getStyle().setColor(Color.RED);
				mapa.addMapPolygon(poligono);
				
			}
		}
	}
	
	
	private void campoDeTexto() {
		textoCantVertices = new JLabel("Cantidad de vertices:");
		textoCantVertices.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textoCantVertices.setBounds(617, 59, 132, 35);
		frame.getContentPane().add(textoCantVertices);
		
		cantVertices = new JTextField();
		cantVertices.setBounds(742, 64, 29, 28);
		frame.getContentPane().add(cantVertices);
		
		
		btnOK();
		mensajeExcepcionCantidadVertices();
	}

	private void btnOK() {
		{
			btnOK = new JButton("Ok");
			btnOK.setBackground(Color.BLACK);
			btnOK.setBounds(779, 62, 45, 32);
			frame.getContentPane().add(btnOK);
			btnOK.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					int cantidadV= Integer.parseInt(cantVertices.getText());
					
					if(cantVertices.getText().isEmpty() || cantidadV <=0) {
						excepcion.setVisible(true);
					} else {
						excepcion.setVisible(false);
						mapa.removeAllMapMarkers();
						mapa.removeAllMapPolygons();
						grafo = new Grafo(cantidadV);
						agregarVertices();
						btnGC.setEnabled(true);
						btnX.setEnabled(true);
					}
				}
			});
		}
	}
	
	private void mensajeExcepcionCantidadVertices () {
		
			excepcion = new JLabel("Ingrese una cantidad mayor a 0.");
			excepcion.setForeground(Color.RED);
			excepcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			excepcion.setBounds(617, 10, 207, 36);
			frame.getContentPane().add(excepcion);
			excepcion.setVisible(false);
			
		
	}
	
	private void crearBtnGrafoCompleto() {

		btnGC = new JButton("Crear grafo completo");
		btnGC.setBackground(Color.BLACK);
		btnGC.setBounds(617, 162, 141, 35);
		frame.getContentPane().add(btnGC);
		btnGC.setEnabled(false);
		
		btnGC.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				grafo.crearGrafoCompleto();
				crearAristas();
			}
		});
	}
	
	public void crearBtnX () {
			btnX = new JButton("X");
			btnX.setFont(new Font("Tahoma", Font.BOLD, 10));
			btnX.setBackground(new Color(255, 69, 0));
			btnX.setForeground(Color.BLACK);
			btnX.setMnemonic('a');
			btnX.setBounds(779, 162, 45, 35);
			frame.getContentPane().add(btnX);
			btnX.setEnabled(false);
			
			btnX.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					mapa.removeAllMapPolygons();
				}
			});
			
			btnX.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					btnX.setLayout(null);
					btnX.setBackground(Color.RED);
			}
		});
	}
	
	private void crearBtnAGM() {
		btnAGM = new JButton("AGM");
		btnAGM.setBounds(617, 308, 141, 30);
		frame.getContentPane().add(btnAGM);
		btnAGM.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ClusterAGM();
				frame.setVisible(false);
			}
		});
	}
	
	public void agregarVertices() {
		int i = 0;
		while (i<grafo.getListaVecinos().size()) {
			mapa.addMapMarker(new MapMarkerDot (i+"", new Coordinate(grafo.getLatitud(i), grafo.getLongitud(i))));
			i++; 
			
		}
	}
	
	
}
