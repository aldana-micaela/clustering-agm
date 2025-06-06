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

import logica.AGM;
import logica.ClusterAGM;
import logica.Grafo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;



public class Mapa {
	private Grafo grafo;
	private Grafo agm;
	private Grafo cluster;
	

	private JFrame frame;
	private JMapViewer mapa;
	private MapPolygon poligono;
	private JPanel panel;

	private JLabel textoCantVertices;
	private JTextField cantVertices;
	private JLabel textoCantidadCluster;
	
	int cantidadCluster;
	
	private JLabel excepcion;

	private JButton btnOK;
	private JButton btnX;
	private JButton btnAGM;
	private JButton btnGC;
	private JButton btnCluster;
	private JButton btnInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mapa window = new Mapa();
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
	public Mapa() {
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

		campoDeTextoCantidadVerticeDelGrafo();
		textoCantidadDeCluster();

		btnOK();
		crearBtnGrafoCompleto();
		crearBtnAGM();
		crearBtnX();
		btnCluster();
		crearBotonInfo();

	}

	private void crearFrame() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(153, 204, 204));
		frame.setBounds(100, 100, 918, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("JMapViewer");
		frame.setResizable(false);
	}

	private void crearPanel() {
		panel = new JPanel();
		panel.setBounds(10, 10, 632, 497);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
	}

	private void crearMapa() {

		Coordinate coor = new Coordinate(-34.52848536990668, -58.706273075149376);
		mapa = new JMapViewer();
		mapa.setBounds(0, 0, 632, 497);
		mapa.setDisplayPosition(coor, 14);
		panel.add(mapa);

	}
	

	private void campoDeTextoCantidadVerticeDelGrafo() {
		textoCantVertices = new JLabel("Cantidad de vertices:");
		textoCantVertices.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textoCantVertices.setBounds(667, 90, 132, 35);
		frame.getContentPane().add(textoCantVertices);

		cantVertices = new JTextField();
		cantVertices.setBounds(809, 94, 29, 30);
		frame.getContentPane().add(cantVertices);

		mensajeExcepcionCantidadVertices();
	}
	
	private void textoCantidadDeCluster() {
		cantidadCluster= 1;
		textoCantidadCluster = new JLabel("Cantidad de Cluster: " + cantidadCluster);
		textoCantidadCluster.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textoCantidadCluster.setBounds(697, 326, 141, 30);
		frame.getContentPane().add(textoCantidadCluster);
	}

	private void mensajeExcepcionCantidadVertices() {

		excepcion = new JLabel("Ingrese una cantidad mayor a 0 y menor a 67.");
		excepcion.setForeground(Color.RED);
		excepcion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		excepcion.setBounds(667, 40, 214, 36);
		frame.getContentPane().add(excepcion);
		excepcion.setVisible(false);

	}

	private void btnOK() {
		btnOK = new JButton("Ok");
		btnOK.setBackground(Color.BLACK);
		btnOK.setBounds(848, 92, 45, 32);
		frame.getContentPane().add(btnOK);
		btnOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int cantidadV;

				if (cantVertices.getText().isEmpty()) {
					excepcion.setVisible(true);
				} else {
					cantidadV = Integer.parseInt(cantVertices.getText());

					if (cantidadV <= 0 || cantidadV >66) {
						excepcion.setVisible(true);
					} else {
						excepcion.setVisible(false);
						mapa.removeAllMapMarkers();
						mapa.removeAllMapPolygons();
						grafo = new Grafo(cantidadV);
						agregarVertices(grafo);
						btnGC.setEnabled(true);
						btnX.setEnabled(true);
						btnCluster.setEnabled(false);
						btnAGM.setEnabled(false);

					}
				}

			}
		});

	}

	private void crearBtnGrafoCompleto() {

		btnGC = new JButton("Grafo completo");
		btnGC.setBackground(Color.BLACK);
		btnGC.setBounds(697, 154, 141, 35);
		frame.getContentPane().add(btnGC);
		btnGC.setEnabled(false);

		btnGC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grafo.crearGrafoCompleto();
				crearAristas(grafo);
				btnAGM.setEnabled(true);
				btnCluster.setEnabled(false);
				cantidadCluster=1;
				textoCantidadCluster.setText("Cantidad de Cluster: " + cantidadCluster);

			}
		});
	}

	public void crearBtnX() {
		btnX = new JButton("X");
		btnX.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnX.setBackground(new Color(255, 69, 0));
		btnX.setForeground(Color.BLACK);
		btnX.setBounds(848, 154, 45, 35);
		frame.getContentPane().add(btnX);
		btnX.setEnabled(false);
		btnX.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapPolygons();
				grafo.removerAristas();
				btnAGM.setEnabled(false);
				btnCluster.setEnabled(false);

			}
		});

	}
	
	private void btnCluster() {
		btnCluster = new JButton("Cluster");
		btnCluster.setBackground(new Color(0, 0, 0));
		btnCluster.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCluster.setBounds(697, 366, 141, 35);
		frame.getContentPane().add(btnCluster);
		btnCluster.setEnabled(false);
				
		btnCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					mapa.removeAllMapPolygons();
					cluster = ClusterAGM.cluster(agm);
					crearAristas(cluster);
					cantidadCluster++;
					textoCantidadCluster.setText("Cantidad de Cluster: " + cantidadCluster);
					if(cantidadCluster>=grafo.getListaVecinos().size()) 
						btnCluster.setEnabled(false);
					else 
						btnCluster.setEnabled(true);
				
			}
		});
	}
	private void crearBotonInfo() {
		btnInfo = new JButton("?");
		btnInfo.setBackground(SystemColor.infoText);
		btnInfo.setForeground(Color.RED);
		btnInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnInfo.setBounds(856, 477, 40, 30);
		frame.getContentPane().add(btnInfo);
		
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(frame,
						"Uso de la interfaz:\n"
								+ "-Seleccione la cantidad de vertices dentro del rango [0-66]\n" +
								"-Presione el boton 'Grafo completo', y luego genere el AGM.\n" + "-Por ultimo presione el boton 'Cluster' para crear tantos clusters como desee.");

			

			}
		});
	}

	private void crearBtnAGM() {
		btnAGM = new JButton("AGM");
		btnAGM.setBackground(new Color(0, 0, 0));
		btnAGM.setBounds(697, 244, 141, 35);
		frame.getContentPane().add(btnAGM);
		btnAGM.setEnabled(false);

		btnAGM.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

					mapa.removeAllMapPolygons();
					agm = AGM.subGrafoAGM(grafo, 0);
					crearAristas(agm);
					btnCluster.setEnabled(true);
					cantidadCluster=1;
					textoCantidadCluster.setText("Cantidad de Cluster: " + cantidadCluster);
				
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
