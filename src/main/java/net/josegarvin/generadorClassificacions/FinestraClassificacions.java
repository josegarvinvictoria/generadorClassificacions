package net.josegarvin.generadorClassificacions;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinestraClassificacions extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	CrearLliga finestraCrearLliga;
	PartitNou finestraPartitNou;
	
	Controlador controlador = new Controlador();

	 /**
	   * Launch the application.
	   */
//	  public static void main(String[] args) {
//	    EventQueue.invokeLater(new Runnable() {
//	      public void run() {
//	        try {
//	          JFrame frame = new JFrame();
//	          frame.setVisible(true);
//	        } catch (Exception e) {
//	          e.printStackTrace();
//	        }
//	      }
//	    });
//	  }
	/**
	 * Create the frame.
	 */
	public FinestraClassificacions() {
		setLocationRelativeTo(null);
		setTitle("Classificacions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFitxer = new JMenu("Fitxer");
		menuBar.add(mnFitxer);
		
		JMenuItem mntmNovaLliga = new JMenuItem("Nova Lliga");
		mntmNovaLliga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finestraCrearLliga = new CrearLliga(controlador, FinestraClassificacions.this);
				finestraCrearLliga.setVisible(true);
			}
		});
		mnFitxer.add(mntmNovaLliga);
		
		JSeparator separator = new JSeparator();
		mnFitxer.add(separator);
		
		JMenuItem mntmObrirLliga = new JMenuItem("Obrir Lliga");
		mnFitxer.add(mntmObrirLliga);
		
		JMenuItem mntmDesarLliga = new JMenuItem("Desar Lliga");
		mnFitxer.add(mntmDesarLliga);
		
		JSeparator separator_1 = new JSeparator();
		mnFitxer.add(separator_1);
		
		JMenuItem mntmSortir = new JMenuItem("Sortir");
		mntmSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFitxer.add(mntmSortir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[426px]", "[229px][][][]"));
		
		JPanel tableContainer = new JPanel();
		tableContainer.setBorder(new TitledBorder(null, "Nom de la lliga", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(tableContainer, "cell 0 0,grow");
		tableContainer.setLayout(new MigLayout("", "[404px]", "[152px]"));
		
		table = new JTable();
		table.setModel(controlador.getEstadisticaEquipModel());
		tableContainer.add(table, "cell 0 0,grow");
		
		JButton btnEntrarUnaNova = new JButton("Entrar una nova Jornada");
		btnEntrarUnaNova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finestraPartitNou = new PartitNou(controlador);
				finestraPartitNou.setVisible(true);
			}
		});
		contentPane.add(btnEntrarUnaNova, "cell 0 2");
		controlador.generarCapsaleres(this);
		
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	

}
