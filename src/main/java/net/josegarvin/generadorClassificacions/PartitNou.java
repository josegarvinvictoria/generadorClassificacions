package net.josegarvin.generadorClassificacions;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * Classe per crear objectes de tipus "PartitNou".
 *
 * @author Jose Garvin Victoria
 *
 */
public class PartitNou extends JFrame {

	/**
	 * Numero de serialització auto-generat.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Contenidor del objectes de la finestra.
	 */
	private JPanel contentPane;

	/**
	 * JTextField on s'especifica el resultat del primer equip.
	 */
	private JTextField resultat1;

	/**
	 * JTextField on s'especifica el resultat del segon equip.
	 */
	private JTextField resultat2;

	/**
	 * Boto "Aceptar".
	 */
	private JButton btnAcceptar;

	/**
	 * Boto "Cancela".
	 */
	private JButton btnCancella;

	/**
	 * JLabel utilitzat per mostrar missatges a l'usuari del programa.
	 */
	private JLabel infoBox;

	/**
	 * Constructor d'objectes "PartitNou".
	 *
	 * @param controlador
	 * 			--> Objecte "Controlador".
	 */
	public PartitNou(final Controlador controlador) {
		setLocationRelativeTo(null);
		this.setResizable(false);
		setTitle("Partit nou");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 184);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout(
			"", "[15.00][113.00,grow][3.00][grow][13.00]",
			"[][][4.00][][42.00]"));

		infoBox = new JLabel("Especifica els equips i els resultats:");
		infoBox.setFont(new Font("Dialog", Font.BOLD, 12));
		infoBox.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(infoBox, "cell 1 0 3 1");

		final JComboBox<String> llistatEquips1 =
			new JComboBox<String>();
		contentPane.add(llistatEquips1, "cell 1 1,growx");

		resultat1 = new JTextField();
		contentPane.add(resultat1, "cell 3 1,growx");
		resultat1.setColumns(10);

		final JComboBox<String> llistatEquips2 =
			new JComboBox<String>();
		contentPane.add(llistatEquips2, "cell 1 3,growx");

		resultat2 = new JTextField();
		contentPane.add(resultat2, "cell 3 3,growx");
		resultat2.setColumns(10);

		btnAcceptar = new JButton("Acceptar");
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				infoBox.setText("");

				String equip1 = llistatEquips1
					.getSelectedItem().toString();
				String equip2 = llistatEquips2
					.getSelectedItem().toString();
				String resultatE1 = resultat1.getText();
				String resultatE2 = resultat2.getText();

				if (equip1.equals(equip2)) {
					infoBox.setText(""
					+ "Revisa els equips escollits!");

				} else if (!isNumeric(resultatE1)
						|| !isNumeric(resultatE2)) {
					infoBox.setText("Cal que el "
					+ "resultat sigui un numero enter!");

				} else {
					controlador.calcularPunts(equip1,
					Integer.parseInt(resultatE1), equip2,
					Integer.parseInt(resultatE2));

					controlador.setHiHaCanvis(true);
				}

			}
		});
		contentPane.add(btnAcceptar, "cell 1 4,alignx center");
		btnCancella = new JButton("Cancel.la");
		btnCancella.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				PartitNou.this.setVisible(false);
			}
		});
		contentPane.add(btnCancella, "cell 3 4,alignx center");

		controlador.carregarEquips(llistatEquips1);
		controlador.carregarEquips(llistatEquips2);

	}

	/**
	 * Mètode per esbrinar si un "String" es un valor numeric.
	 *
	 * @param cadena
	 * 			--> Cadena de text a tractar
	 *
	 * @return
	 * 		--> True si la cadena correspon a un valor numeric
	 * 			i False si no.
	 */
	private static boolean isNumeric(final String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
