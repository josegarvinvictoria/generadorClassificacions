package net.josegarvin.generadorClassificacions;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PartitNou extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnAcceptar;
	private JButton btnCancella;

	/**
	 * Create the frame.
	 */
	public PartitNou(final Controlador controlador) {
		setTitle("Partit nou");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[15.00][113.00,grow][3.00][grow][13.00]", "[][4.00][][8.00][]"));
		
		JComboBox<String> llistatEquips1 = new JComboBox<String>();
		contentPane.add(llistatEquips1, "cell 1 0,growx");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 0,growx");
		textField.setColumns(10);
		
		JComboBox<String> llistatEquips2 = new JComboBox<String>();
		contentPane.add(llistatEquips2, "cell 1 2,growx");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 3 2,growx");
		textField_1.setColumns(10);
		
		btnAcceptar = new JButton("Acceptar");
		contentPane.add(btnAcceptar, "cell 1 4,alignx center");
		
		btnCancella = new JButton("Cancel.la");
		contentPane.add(btnCancella, "cell 3 4,alignx center");
		
		controlador.carregarEquips(llistatEquips1);
		controlador.carregarEquips(llistatEquips2);
		
	}

}
