package net.josegarvin.generadorClassificacions;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

/**
 * Finestra principal del programa.
 *
 * @author Jose Garvin Victoria
 *
 */
public class FinestraClassificacions extends JFrame {

	/**
	 * Milisegons d'espera que utilitza l'objecte "Timer".
	 */
	private static final int TEMPS_ESPERA = 2000;

	/**
	 *Numero de serialització auto-generat.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Finestra que es genera al crear una nova lliga.
	 */
	private CrearLliga finestraCrearLliga;

	/**
	 * Finestra que es genera al entrar una nova jornada.
	 */
	private PartitNou finestraPartitNou;

	/**
	 * Ruta al fitxer de la lliga oberta en un moment concret.
	 */
	private File rutaLligaActual;

	/**
	 * Nom de la lliga carregada en un moment concret.
	 */
	private String nomLliga;

	/**
	 * JTable de la finestra principal del programa.
	 */
	private JTable table;

	/**
	 * JLabel utilitzat per mostrar missatges a l'usuari del programa.
	 */
	private JLabel infoBox = new JLabel("");

	/**
	 * Titol del contenidor de la taula.
	 */
	private TitledBorder border = null;

	private JPanel contentPane;

	/**
	 * Constructor d'objectes "FinestraClassificacions".
	 *
	 * @param controlador Objecte "controlador".
	 * 				-->
	 */
	public FinestraClassificacions(final Controlador controlador) {
		nomLliga = controlador.obtenirNomLliga();
		setLocationRelativeTo(null);
		this.setResizable(false);
		setTitle("Classificacions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 314);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFitxer = new JMenu("Fitxer");
		menuBar.add(mnFitxer);

		JMenuItem mntmNovaLliga = new JMenuItem("Nova Lliga");
		mntmNovaLliga.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {

				int seleccio = -1;
				finestraCrearLliga =
						new CrearLliga(controlador);

				// Si el model conté dades.
				if (controlador.hiHaDadesCarregades()
						&& controlador.isHiHaCanvis()) {
					// Pregunto a l'usuari que vol fer
					// amb les dades actuals.
					seleccio =
					queFemAmbLesDadesActuals(controlador);

					// Si l'usuari escull "Yes".
					if (seleccio == 0) {
						String xml =
						controlador.generarXML();
						FileWriter fw = null;

						// Comprovo que es coneix una
						// ruta per desar les dades, si
						// es coneix la ruta de sortida
						// deso el fitxer directament.
						if (rutaLligaActual != null) {

							try {
								fw =
							new FileWriter(
							rutaLligaActual);
								fw.write(xml);
								fw.close();
							} catch (
							IOException e1) {
								e1
							.printStackTrace();
							}

				// En el cas de NO coneixer la ruta de sortida,
				// l'indiquem a l'usuari que l'especifiqui
				// a partir d'un fileChooser.
						} else {
							JOptionPane
							.showMessageDialog(
							null,
							"No es coneix cap "
							+ "ruta de sortida "
							+ "per aquesta lliga. "
							+ "\nEspecifica a on"
							+ " vols desar la"
							+ " lliga:");

						onDesemLesDadesActuals(fw, xml);
						}

					controlador.setHiHaCanvis(false);

					}
					// Si l'usuari escull "No".
					if (seleccio == 1) {
						finestraCrearLliga
						.setModal(true);
						finestraCrearLliga
						.setVisible(true);
						nomLliga = controlador
						.getNomLliga();
						rutaLligaActual = null;
						border.setTitle(nomLliga);
						repaint();

					}
				} else {
					finestraCrearLliga.setModal(true);
					finestraCrearLliga.setVisible(true);
					nomLliga = controlador.getNomLliga();
					rutaLligaActual = null;
					border.setTitle(nomLliga);
					repaint();
				}

			}
		});
		mnFitxer.add(mntmNovaLliga);

		JSeparator separator = new JSeparator();
		mnFitxer.add(separator);

		JMenuItem mntmObrirLliga = new JMenuItem("Obrir Lliga");
		mntmObrirLliga.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {

				int seleccio = -1;

				// Si el model conté dades.
				if (controlador.hiHaDadesCarregades()
						&& controlador.isHiHaCanvis()) {

				//Pregunto a l'usuari que vol fer amb les dades
				//actuals.
					seleccio =
					queFemAmbLesDadesActuals(controlador);

					// Si l'usuari escull "Yes".
					if (seleccio == 0) {
						String xml =
						controlador.generarXML();
						FileWriter fw = null;

						if (rutaLligaActual != null) {

							try {
								fw = new
								FileWriter(
								rutaLligaActual);
								fw.write(xml);
								fw.close();
								JOptionPane.
								showMessageDialog(
								null,
								"Lliga desada "
								+ "correctament"
								+ ".");
							} catch (
							IOException e1) {
								e1
								.printStackTrace();
							}
						} else {
							JOptionPane
							.showMessageDialog(
							null,
							"No es coneix cap ruta "
							+ "de sortida per "
							+ "aquesta lliga. "
							+ "\nEspecifica a "
							+ "on vols desar la "
							+ "lliga.");
							onDesemLesDadesActuals(
								fw, xml);
						}
						controlador.setHiHaCanvis(
							false);

					}

				}

				if (seleccio == 0
					|| seleccio == 1
					|| seleccio == -1) {

					JFileChooser fileChooser =
							new JFileChooser();

					fileChooser.showOpenDialog(
						FinestraClassificacions.this);

					File entrada =
						fileChooser.getSelectedFile();
					rutaLligaActual = entrada;

					if (entrada != null) {

						if (controlador
							.validarXMLambXSD(
							entrada)) {

							Lliga lligaFutbol =
							controlador
							.recuperarLligaXML(
							entrada);

							controlador
							.setEstadisticaEquip(
							lligaFutbol
							.getClassificacio());

							controlador
							.carregarDadesTaula();

							nomLliga =
							lligaFutbol.
							getNomLliga();

							border
							.setTitle(nomLliga);

							repaint();

							FinestraClassificacions
							.this.infoBox
							.setText("Lliga oberta "
							+ "correctament.");
							esperarIborrar(
							TEMPS_ESPERA);

						} else {
							infoBox
							.setText("Aquest fitxer"
							+ " no es vàlid!");

							esperarIborrar(
							TEMPS_ESPERA);
						}
					}
				}
			}
		});
		mnFitxer.add(mntmObrirLliga);

		JMenuItem mntmDesarLliga = new JMenuItem("Desar Lliga");
		mntmDesarLliga.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String xml = controlador.generarXML();
				FileWriter fw = null;

				if (rutaLligaActual != null) {

					try {
						fw = new
						FileWriter(rutaLligaActual);

						fw.write(xml);

						fw.close();

						FinestraClassificacions.this.
						infoBox.setText("Lliga desada"
							+ " correctament.");
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} else {

					try {
						JFileChooser fileChooser =
							new JFileChooser();

						fileChooser.showSaveDialog(
						FinestraClassificacions.this);

						File sortida = fileChooser
							.getSelectedFile();

						if (sortida != null) {

							fw = new FileWriter(
							sortida + ".xml");

							fw.write(xml);

							fw.close();

							FinestraClassificacions
							.this.infoBox.setText(""
							+ "Lliga desada "
							+ "correctament.");

							rutaLligaActual
							= sortida;

							esperarIborrar(
							TEMPS_ESPERA);
						}

					} catch (IOException e1) {
						FinestraClassificacions
						.this.infoBox.setText(
						"No s'ha pogut desar la"
						+ " lliga.");
					}
				}
			}
		});
		mnFitxer.add(mntmDesarLliga);

		JSeparator separator1 = new JSeparator();
		mnFitxer.add(separator1);

		JMenuItem mntmSortir = new JMenuItem("Sortir");
		mntmSortir.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		mnFitxer.add(mntmSortir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("",
			"[426px][]", "[229px][][][]"));

		JPanel tableContainer = new JPanel();

		tableContainer.setBorder(border =
			new TitledBorder(null, nomLliga,
				TitledBorder.LEADING, TitledBorder.TOP,
				null, null));

		contentPane.add(tableContainer, "cell 0 0,grow");
		tableContainer.setLayout(new MigLayout("", "[404px,grow]",
				"[152px,grow]"));

		JScrollPane scrollPane = new JScrollPane();
		tableContainer.add(scrollPane, "cell 0 0,grow");

		table = new JTable();
		table.setModel(controlador.getEstadisticaEquipModel());
		scrollPane.setViewportView(table);

		JButton btnEntrarUnaNova =
		new JButton("Entrar una nova Jornada");

		btnEntrarUnaNova.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				finestraPartitNou = new PartitNou(controlador);
				finestraPartitNou.setVisible(true);
			}
		});
		contentPane.add(btnEntrarUnaNova, "flowx,cell 0 2");

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 2,alignx center,aligny center");
		panel.setLayout(new MigLayout("", "[180px]", "[8px]"));

		panel.add(infoBox, "cell 0 0,alignx left,aligny top");

		if (controlador.getEstadisticaEquipModel()
			.getColumnCount() == 0) {
			controlador.generarCapsaleres();
		}
	}

	/**
	 * Mètode per obtenir el nom de la lliga carregada.
	 *
	 * @return
	 * 		--> Retorna el nom de la lliga carregada.
	 */
	public final String getNomLliga() {
		return nomLliga;
	}

	/**
	 * Mètode per assignar el nom de la lliga carregada.
	 *
	 * @param nomLligaA
	 * 			--> Nom de la lliga.
	 */
	public final void setNomLliga(final String nomLligaA) {
		this.nomLliga = nomLligaA;
	}

	/**
	 * Mètode que s'encarrega de preguntar a l'usuari que vol fer
	 * amb la lliga que té oberta en el moment d'obrir o crear una
	 * nova lliga.
	 *
	 * @param controlador
	 * 			--> Objecte "Controlador".
	 *
	 * @return
	 * 		--> Retorna un enter referent a l'opció escollida per
	 * 			l'usuari.
	 */
	public final int queFemAmbLesDadesActuals(final
			Controlador controlador) {
		int seleccio = -1;

		seleccio = JOptionPane
				.showConfirmDialog(FinestraClassificacions.this,
						"Actualment hi ha una lliga "
						+ "oberta. Vols desar els"
						+ " canvis realitzats?");

		return seleccio;
	}

	/**
	 * Mètode que s'encarrega d'obrir on "fileChooser" per
	 * tal de que l'usuari indiqui a on vol desar les dades
	 * actuals.
	 *
	 * @param fw
	 * 		--> Objecte "FileWriter".
	 *
	 * @param xml
	 * 		--> String corresponent al XML generat a partir
	 * 			de la lliga oberta.
	 */
	public final void onDesemLesDadesActuals(FileWriter fw,
			final String xml) {

		try {
			JFileChooser fileChooser = new JFileChooser();

			fileChooser.showSaveDialog(
				FinestraClassificacions.this);
			File sortida = fileChooser.getSelectedFile();

			if (sortida != null) {

				fw = new FileWriter(sortida + ".xml");
				fw.write(xml);
				fw.close();
				rutaLligaActual = sortida;
				JOptionPane.showMessageDialog(null,
						"Lliga desada correctament.");
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			FinestraClassificacions.this.infoBox
			.setText("No s'ha pogut desar la lliga.");
		}

	}

	/**
	 * Mètode per tal d'esperar un numero concret de milisegons
	 * i esborrar el contingut de l'objecte "infoBox".
	 *
	 * @param milisegons
	 * 			--> Milisegons d'espera.
	 */
	public final void esperarIborrar(final int milisegons) {
		Timer timer = new Timer(milisegons, new ActionListener() {

			public void actionPerformed(final ActionEvent e) {
				infoBox.setText("");
			}
		});
		timer.start();
		timer.setRepeats(false);
	}

}
