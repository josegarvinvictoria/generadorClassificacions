package net.josegarvin.generadorClassificacions;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Finestra utilitzada per crear lligues des del programa.
 * @author Jose Garvin Victoria
 *
 */
public class CrearLliga extends JDialog {

	/**
	 *UID de serialització auto-generat.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Contenidor JPanel del qual penjen tots els elements de la finestra.
	 */
	private JPanel contentPane;

	/**
	 * JTextField on s'especificarà el nom de la lliga a crear.
	 */
	private JTextField nomLliga;

	/**
	 *  JTextField on s'especificaran els noms dels equips que
	 *  formen la lliga a crear.
	 */
	private JTextField nouEquip;

	/**
	 * JLabel que utilitza el programa per mostrar missatges a l'usuari.
	 */
	private JLabel infoBox;

	/**
	 * ArrayList de "String" on s'emmagatzemen els noms dels equips que
	 * formen la lliga a crear.
	 */
	private ArrayList<String> equipsLliga = new ArrayList<String>();

	/**
	 * Model del JList de la finestra.
	 */
	private DefaultListModel<String> listModel;

	/**
	 * JList on s'aniran afegint els noms del nous equips.
	 */
	private JList<String> list;

	/**
	 * Constructor de la finestra "CrearLliga".
	 * @param controlador
	 * 				--> Objecte "Controlador".
	 */
	public CrearLliga(final Controlador controlador) {
		setLocationRelativeTo(null);
		this.setResizable(false);
		setTitle("Crear una lliga");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 312);
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
			public void actionPerformed(final ActionEvent e) {
				if (list.getSelectedIndex() != -1) {
					listModel.remove(
						list.getSelectedIndex());
				}
			}
		});
		btnTreure.setBounds(11, 199, 117, 25);
		contentPane.add(btnTreure);
		contentPane.add(list);
		JButton btnSortir = new JButton("Crear");
		btnSortir.setBounds(363, 236, 74, 25);
		btnSortir.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				//Si hi ha algun equip a la llista i a més hi ha
				//un nom de lliga.
				boolean dadesOk = listModel.size() != 0
					&& !nomLliga.getText().equals("");

				if (dadesOk) {
					equipsLliga.add(nomLliga.getText());
				for (int i = 0; i < listModel.size(); i++) {
					equipsLliga.add(listModel.get(i));
				}


				//Paso les dades al controlador.
				controlador.setNomLliga(nomLliga.getText());
				controlador.setEquipsLliga(equipsLliga);
				controlador.generarEstadisticaEquips();
				controlador.carregarDadesTaula();
				controlador.setHiHaCanvis(true);


				//Oculto la finestra
				CrearLliga.this.setVisible(false);


				} else {
					infoBox.setText(
						"No hi ha prous dades!");
				}

				dispose();
			}
		});
		contentPane.add(btnSortir);

		infoBox = new JLabel("");
		infoBox.setBounds(11, 226, 229, 25);
		contentPane.add(infoBox);
	}

	/**
	 * Mètode per afegir equips al model de la llista de la finestra.
	 */
	public final void afegirEquip() {
		if (!nouEquip.getText().equals("")) {
			listModel.addElement(nouEquip.getText());
			nouEquip.setText("");
		}
	}
}
