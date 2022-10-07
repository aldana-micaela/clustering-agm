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

import grafos.AGM;
import grafos.ClusterAGM;
import grafos.Grafo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;


public class InterfaceGrafo {
	private Grafo grafo;
	private Grafo agm;

	private JFrame frame;
	private JMapViewer mapa;
	private MapPolygon poligono;
	private JPanel panel;

	private JLabel textoCantVertices;
	private JLabel textoVerticeInicio;
	private JTextField campoTextoVerticeInicio;
	private JTextField cantVertices;

	private JLabel excepcion;
	private JLabel excepcionVerticeInicio;

	private JButton btnOK;
	private JButton btnX;
	private JButton btnAGM;
	private JButton btnGC;
	private JButton btnCluster;

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

		campoDeTextoVerticeInicioAGM();
		campoDeTextoCantidadVerticeDelGrafo();

		btnOK();
		crearBtnGrafoCompleto();
		crearBtnAGM();
		crearBtnX();
		btnCluster();

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

	private void crearPanel() {
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 604, 497);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
	}

	private void crearMapa() {

		Coordinate coor = new Coordinate(-34.52848536990668, -58.706273075149376);
		mapa = new JMapViewer();
		mapa.setBounds(0, 0, 595, 487);
		mapa.setDisplayPosition(coor, 14);
		panel.add(mapa);

	}

	private void campoDeTextoCantidadVerticeDelGrafo() {
		textoCantVertices = new JLabel("Cantidad de vertices:");
		textoCantVertices.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textoCantVertices.setBounds(617, 59, 132, 35);
		frame.getContentPane().add(textoCantVertices);

		cantVertices = new JTextField();
		cantVertices.setBounds(742, 64, 29, 30);
		frame.getContentPane().add(cantVertices);

		mensajeExcepcionCantidadVertices();
	}

	public void campoDeTextoVerticeInicioAGM() {

		textoVerticeInicio = new JLabel("Vertice de inicio:");
		textoVerticeInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textoVerticeInicio.setBounds(622, 236, 110, 35);
		frame.getContentPane().add(textoVerticeInicio);

		campoTextoVerticeInicio = new JTextField();
		campoTextoVerticeInicio.setBounds(742, 236, 29, 30);
		frame.getContentPane().add(campoTextoVerticeInicio);

		mensajeExcepcionVerticeInicio();

	}

	private void mensajeExcepcionCantidadVertices() {

		excepcion = new JLabel("Ingrese una cantidad mayor a 0.");
		excepcion.setForeground(Color.RED);
		excepcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		excepcion.setBounds(617, 10, 207, 36);
		frame.getContentPane().add(excepcion);
		excepcion.setVisible(false);

	}

	private void mensajeExcepcionVerticeInicio() {
		excepcionVerticeInicio = new JLabel();
		excepcionVerticeInicio.setForeground(Color.RED);
		excepcionVerticeInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		excepcionVerticeInicio.setBounds(624, 202, 207, 30);
		excepcionVerticeInicio.setVisible(false);
	}

	private void btnOK() {
		btnOK = new JButton("Ok");
		btnOK.setBackground(Color.BLACK);
		btnOK.setBounds(779, 62, 45, 32);
		frame.getContentPane().add(btnOK);
		btnOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int cantidadV;

				if (cantVertices.getText().isEmpty()) {
					excepcion.setVisible(true);
				} else {
					cantidadV = Integer.parseInt(cantVertices.getText());

					if (cantidadV <= 0) {
						excepcion.setVisible(true);
					} else {
						excepcion.setVisible(false);
						mapa.removeAllMapMarkers();
						mapa.removeAllMapPolygons();
						grafo = new Grafo(cantidadV);
						agregarVertices(grafo);
						System.out.println(grafo.getListaVecinos().toString());
						btnGC.setEnabled(true);
						btnX.setEnabled(true);

					}
				}

			}
		});

	}

	private void crearBtnGrafoCompleto() {

		btnGC = new JButton("Crear grafo completo");
		btnGC.setBackground(Color.BLACK);
		btnGC.setBounds(624, 137, 141, 35);
		frame.getContentPane().add(btnGC);
		btnGC.setEnabled(false);

		btnGC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				grafo.crearGrafoCompleto();
				crearAristas(grafo);
				btnAGM.setEnabled(true);
				System.out.println(grafo.getListaVecinos().toString());

			}
		});
	}

	public void crearBtnX() {
		btnX = new JButton("X");
		btnX.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnX.setBackground(new Color(255, 69, 0));
		btnX.setForeground(Color.BLACK);
		btnX.setMnemonic('a');
		btnX.setBounds(779, 137, 45, 35);
		frame.getContentPane().add(btnX);
		btnX.setEnabled(false);

		frame.getContentPane().add(excepcionVerticeInicio);

		btnX.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapPolygons();
				grafo.removerAristas();
				btnAGM.setEnabled(false);
				System.out.println(grafo.getListaVecinos().toString());

			}
		});

	}
	
	private void btnCluster() {
		btnCluster = new JButton("Cluster");
		btnCluster.setBackground(Color.BLUE);
		btnCluster.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCluster.setBounds(656, 415, 141, 30);
		frame.getContentPane().add(btnCluster);
		
		btnCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapPolygons();
				crearAristas(ClusterAGM.cluster(agm, 2));
			}
		});
	}

	private void crearBtnAGM() {
		btnAGM = new JButton("AGM");
		btnAGM.setBackground(Color.BLACK);
		btnAGM.setBounds(656, 281, 141, 30);
		frame.getContentPane().add(btnAGM);
		btnAGM.setEnabled(false);

		btnAGM.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (campoTextoVerticeInicio.getText().isEmpty()) {
					excepcionVerticeInicio.setText("Ingrese un vertice de inicio v\u00E1lido.");
					excepcionVerticeInicio.setVisible(true);

				}

				else if (grafo.getListaVecinos().size() <= Integer.parseInt(campoTextoVerticeInicio.getText())
						|| Integer.parseInt(campoTextoVerticeInicio.getText()) < 0) {

					excepcionVerticeInicio.setText("Vertice fuera de rango.");
					excepcionVerticeInicio.setVisible(true);

				} else {
					int verticeInicio = Integer.parseInt(campoTextoVerticeInicio.getText());
					excepcionVerticeInicio.setVisible(false);
					mapa.removeAllMapPolygons();
					agm = AGM.subGrafoAGM(grafo, verticeInicio);
					crearAristas(agm);
					System.out.println(agm.getListaVecinos().toString());
				}
			}
		});
	}

	public void agregarVertices(Grafo grafo) {

		int i = 0;
		while (i < grafo.getListaVecinos().size()) {
			mapa.addMapMarker(new MapMarkerDot(i + "", new Coordinate(grafo.getLatitud(i), grafo.getLongitud(i))));
			i++;

		}
	}

	private void crearAristas(Grafo grafo) {
		ArrayList<Coordinate> coor;

		for (int i = 0; i < grafo.getListaVecinos().size(); i++) {

			for (Integer v : grafo.getListaVecinos().get(i)) {

				coor = grafo.devolverCoordenadasEntreAristas(i, v);
				poligono = new MapPolygonImpl(coor);
				poligono.getStyle().setColor(Color.RED);
				mapa.addMapPolygon(poligono);

			}
		}
	}
}
