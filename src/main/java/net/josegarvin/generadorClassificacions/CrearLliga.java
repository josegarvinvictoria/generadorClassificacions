package net.josegarvin.generadorClassificacions;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class CrearLliga extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomLliga;
	private JTextField nouEquip;
	JLabel infoBox;
	private ArrayList<String> equipsLliga = new ArrayList<String>();
	DefaultListModel<String> listModel;
	JList<String> list;

	/**
	 * Create the frame.
	 */
	public CrearLliga(final Controlador controlador) {
		setLocationRelativeTo(null);
		this.setResizable(false);
		setTitle("Crear una lliga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.addWindowListener( new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent e) {
				System.out.println("Tanca!!!!!!");
				
				CrearLliga.this.obtenirIMostrarFinestraPare();
				
			}
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		

		    });
		
		
		
		
		final JLabel labelNomLliga = new JLabel("Nom de la lliga: ");
		labelNomLliga.setBounds(11, 13, 113, 15);
		contentPane.add(labelNomLliga);

		nomLliga = new JTextField();
		nomLliga.setBounds(134, 11, 303, 19);
		contentPane.add(nomLliga);
		nomLliga.setColumns(10);

		JLabel lblEquip = new JLabel("Equip:");
		lblEquip.setBounds(11, 36, 44, 15);
		contentPane.add(lblEquip);
		listModel = new DefaultListModel<String>();

		nouEquip = new JTextField();
		nouEquip.setBounds(11, 57, 114, 19);
		contentPane.add(nouEquip);
		nouEquip.setColumns(10);

		JButton btnAfegir = new JButton("Afegir");
		btnAfegir.setBounds(11, 168, 117, 25);
		btnAfegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afegirEquip();
			}
		});

		contentPane.add(btnAfegir);
		list = new JList<String>(listModel);
		list.setBounds(134, 42, 303, 188);
		JButton btnTreure = new JButton("Treure");
		btnTreure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() != -1){
					listModel.remove(list.getSelectedIndex());
					System.out.println(list.toString());
				}
			}
		});
		btnTreure.setBounds(11, 199, 117, 25);
		contentPane.add(btnTreure);
		contentPane.add(list);
		JButton btnSortir = new JButton("Crear");
		btnSortir.setBounds(363, 236, 74, 25);
		btnSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si hi ha algun equip a la llista i a més hi ha
				//un nom de lliga.
				boolean dadesOk = listModel.size() != 0 && !nomLliga.getText().equals("");
				
				if(dadesOk){
					equipsLliga.add(nomLliga.getText());
				for(int i = 0; i<listModel.size();i++){
					equipsLliga.add(listModel.get(i));
				}
				
				
				//Paso les dades al controlador.
				controlador.setEquipsLliga(equipsLliga);
				controlador.generarEstadisticaEquips();
				controlador.carregarDadesTaula();
				
				
				//Oculto la finestra
				CrearLliga.this.setVisible(false);
				
				FinestraClassificacions finestraPrincipal = new FinestraClassificacions(controlador);
				finestraPrincipal.border.setTitle(nomLliga.getText());
				finestraPrincipal.setVisible(true);
				}else{
					infoBox.setText("No hi ha prous dades!");
				}
				
				System.out.println(equipsLliga.toString());
			}
		});
		contentPane.add(btnSortir);	
		
		infoBox = new JLabel("");
		infoBox.setBounds(11, 226, 229, 25);
		contentPane.add(infoBox);
	}
	
	
	public void afegirEquip(){
		if(!nouEquip.getText().equals("")){
			listModel.addElement(nouEquip.getText());
			nouEquip.setText("");
		}
		System.out.println(listModel);
	}
	
	public String getnomLliga(){
		return nomLliga.getText();
	}
	
	public void obtenirIMostrarFinestraPare(){
		FinestraClassificacions topFrame = (FinestraClassificacions) SwingUtilities.getWindowAncestor(this);
		
		topFrame.setVisible(true);
	}
}
