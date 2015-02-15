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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CrearLliga extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomLliga;
	private JTextField nouEquip;
	private ArrayList<String> equipsLliga = new ArrayList<String>();
	DefaultListModel<String> listModel;
	JList<String> list;

	/**
	 * Create the frame.
	 */
	public CrearLliga(final Controlador controlador, final FinestraClassificacions finestraClassificacions) {
		setLocationRelativeTo(null);
		setTitle("Crear una lliga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		btnAfegir.setBounds(11, 174, 117, 25);
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
		btnTreure.setBounds(11, 205, 117, 25);
		contentPane.add(btnTreure);
		contentPane.add(list);
		JButton btnSortir = new JButton("Crear");
		btnSortir.setBounds(363, 236, 74, 25);
		btnSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si hi ha algun equip a la llista i a més hi ha
				//un nom de lliga.
				boolean dadesOk = listModel.size() != 0 && !labelNomLliga.getText().equals("");
				
				if(dadesOk){
					equipsLliga.add(nomLliga.getText());
				for(int i = 0; i<listModel.size();i++){
					equipsLliga.add(listModel.get(i));
				}
				
				
				//Paso les dades al controlador.
				controlador.setEquipsLliga(equipsLliga);				
				controlador.carregarDadesTaula();
				finestraClassificacions.setNomLliga(controlador.obtenirNomLliga());
				CrearLliga.this.setVisible(false);
				}else{
					System.out.println("No s'han especificat prous dades!");
				}
				
				System.out.println(equipsLliga.toString());
			}
		});
		contentPane.add(btnSortir);	
	}
	
	
	public void afegirEquip(){
		if(!nouEquip.getText().equals("")){
			listModel.addElement(nouEquip.getText());
			nouEquip.setText("");
		}
		System.out.println(listModel);
	}
	
	
}
