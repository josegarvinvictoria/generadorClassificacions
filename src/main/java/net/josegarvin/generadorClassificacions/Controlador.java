package net.josegarvin.generadorClassificacions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

/**
 * Classe "Controlador" que conté tota la lògica del programa. En aquesta classe
 * hi ha tots el mètodes que realitzen les tasques sobre fitxers, tasques sobre
 * la taula i el model del programa, assignació de punts, ordenació de la
 * classificació, etc.
 *
 * @author Jose Garvin Victoria
 *
 */
public class Controlador {

	/**
	 * Número de punts a assignar a l'equip guanyador de una jornada.
	 */
	private static final int PUNTS_VICTORIA = 3;

	/**
	 * Tamany de l'array de tipus "Object" resultant al generar una linia
	 * per afegir dades al model.
	 */
	private static final int TAMANY_ARRAY_LINIA = 5;

	/**
	 * ArrayList de "String" on desaran els noms dels nous equips al
	 * crear una nova lliga.
	 */
	private ArrayList<String> equipsLliga = new ArrayList<String>();

	/**
	 * ArrayList de "EstadisticaEquip" on es desaran els objectes
	 * "EstadisticaEquip" desprès de ser creats.
	 */
	private ArrayList<EstadisticaEquip> estadisticaEquip =
			new ArrayList<EstadisticaEquip>();

	/**
	 * Model de la taula del programa.
	 */
	private DefaultTableModel estadisticaEquipModel =
			new DefaultTableModel();

	/**
	 * Variable on s'emmagatzema el nom de la lliga.
	 */
	private String nomLliga = "";

	/**
	 * Array de "String" on s'emmagatzemen les capçaleres de la taula.
	 */
	private String[] capsaleres =
		{"Nom equip", "Victories", "Empats", "Derrotes", "Punts"};

	/**
	 * Instancia de l'objecte xstream a partir de la classe XStream.
	 * Xstream es una llibreria externa molt util que ens facilita
	 * el tractat de fitxers XML.
	 */
	private XStream xstream = new XStream();

	/**
	 * Variable de tipus "boolean" que utilitza el programa per determinar
	 * si l'usuari ha realizat canvis en una lliga determinada.
	 */
	private boolean hiHaCanvis = false;

	/**
	 * Constructor de la classe.
	 */
	public Controlador() {
		/**
		 * Amb les dos linies que es mostren a continuació, estic
		 * assignant un alias per a cada una de les classes que serà
		 * representada en XML. D'aquesta manera aconsegueixo que el
		 * fitxer XML que es generi a partir de la classe XStream
		 * utilitzi uns "tags" més entendibles per a l'usuari.
		 */
		xstream.alias("lliga", Lliga.class);
		xstream.alias("equip", EstadisticaEquip.class);
	}

	/**
	 * Mètode per obtenir l'ArrayList de "String" amb els noms dels
	 * equips que formen la lliga.
	 *
	 * @return --> Retorna un ArrayList de "String" amb els noms
	 * del equips que formen una lliga.
	 */
	public final ArrayList<String> getEquipsLliga() {
		return equipsLliga;
	}

	/**
	 * Mètode per assignar l'ArrayList de "String" amb els noms dels
	 * equips que formen la lliga.
	 *
	 * @param equipsLligaA --> ArrayList de "String" amb els noms
	 * del equips que formen una lliga.
	 */
	public final void setEquipsLliga(final ArrayList<String> equipsLligaA) {
		this.equipsLliga = equipsLligaA;
	}

	/**
	 * Mètode per obtenir l'ArrayList d'objectes de tipus "EstadisticaEquip"
	 * amb les estadistiques de cadascun del equips que formen una lliga.
	 *
	 * @return --> ArrayList d'objectes de tipus "EstadisticaEquip"
	 * amb les estadistiques de cadascun del equips que formen una lliga.
	 */
	public final ArrayList<EstadisticaEquip> getEstadisticaEquip() {
		return estadisticaEquip;
	}

	/**
	 * Mètode per assignar l'ArrayList d'objectes de tipus
	 * "EstadisticaEquip" amb les estadistiques de cadascun
	 * del equips que formen una lliga.
	 *
	 * @param estadisticaEquipA -->ArrayList d'objectes de tipus
	 * "EstadisticaEquip" amb les estadistiques de cadascun del
	 * equips que formen una lliga.
	 */
	public final void setEstadisticaEquip(final ArrayList<EstadisticaEquip>
	estadisticaEquipA) {
		this.estadisticaEquip = estadisticaEquipA;
	}

	/**
	 * Mètode que s'encarrega d'emplenar els JComboBox de la finestra
	 * "Partit Nou".
	 *
	 * @param comboEquips --> JComboBox a emplenar.
	 */
	public final void carregarEquips(final JComboBox<String> comboEquips) {
		for (int i = 0; i < estadisticaEquip.size(); i++) {
			comboEquips.addItem(estadisticaEquip.get(i)
					.getNomEquip());
		}
	}

	/**
	 * Mètode que s'encarrega de generar un ArrayList d'objectes
	 * "EstadisticaEquip" a partir de l'ArrayList amb els noms
	 * del equips.
	 */
	public final void generarEstadisticaEquips() {
		estadisticaEquip = new ArrayList<EstadisticaEquip>();
		for (int i = 1; i < equipsLliga.size(); i++) {
			estadisticaEquip.add(new
					EstadisticaEquip(equipsLliga.get(i)));
		}

	}

	/**
	 * Mètode que s'encarrega de carregar els equips en el
	 * model de la taula.
	 */
	public final void carregarDadesTaula() {
		netejarTaula();
		ordenarClassificacio();

		if (estadisticaEquip.size() == 0) {
			generarEstadisticaEquips();
		}

		for (int i = 0; i < estadisticaEquip.size(); i++) {
			Object[] linia = generarLinia(estadisticaEquip.get(i));

			estadisticaEquipModel.addRow(linia);
		}
	}

	/**
	 * Mètode que s'encarrega de netejar el model de la taula.
	 */
	public final void netejarTaula() {
		try {
			DefaultTableModel referencia = estadisticaEquipModel;
			int a = referencia.getRowCount() - 1;
			for (int i = 0; i <= a; i++) {
				estadisticaEquipModel.removeRow(0);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Mètode que s'encarrega de generar la linia per afegir dades
	 * al model de la taula del programa.
	 *
	 * @param equip --> Objecte de tipus "EstadisticaEquip" a partir
	 * del qual es generara la linia.
	 *
	 * @return --> Retorna un objecte de tipus "Object[]" corresponent
	 * a la linia generada.
	 */
	private Object[] generarLinia(final EstadisticaEquip equip) {
		Object[] data = new Object[TAMANY_ARRAY_LINIA];

		data[0] = equip.getNomEquip();
		data[1] = equip.getGuanyats();
		data[2] = equip.getEmpatats();
		data[3] = equip.getPerduts();
		data[4] = equip.getPunts();

		return data;
	}

	/**
	 * Mètode que s'encarrega de generar les capçaleres de la taula.
	 */
	public final void generarCapsaleres() {
		for (String capsalera : capsaleres) {
			estadisticaEquipModel.addColumn(capsalera);
		}
	}

	/**
	 * Mètode per obtenir el "DefaultTableModel" corresponent a la
	 * taula principal del programa.
	 *
	 * @return --> Retorna el "DefaultTableModel".
	 */
	public final DefaultTableModel getEstadisticaEquipModel() {
		return estadisticaEquipModel;
	}

	/**
	 * Mètode per assignar el "DefaultTableModel" corresponent a la
	 * taula principal del programa.
	 *
	 * @param estadisticaEquipModelA --> "DefaultTableModel" a assignar.
	 */
	public final void setEstadisticaEquipModel(
			final DefaultTableModel estadisticaEquipModelA) {
		this.estadisticaEquipModel = estadisticaEquipModelA;
	}

	/**
	 * Mètode que s'encarrega de calcular els punts que s'assignaran
	 * a cadascun dels equips que juguen un partit.
	 *
	 * @param equip1
	 * 			--> Nom del primer equip.
	 * @param resultat1
	 * 			--> Resultat del primer equip.
	 * @param equip2
	 * 			--> Nom del segon equip.
	 * @param resultat2
	 * 			--> Resultat del segon equip.
	 */
	public final void calcularPunts(final String equip1,
			final int resultat1, final String equip2,
			final int resultat2) {
		EstadisticaEquip equipA1 =
				equipStringToEstadisticaEquip(equip1);
		EstadisticaEquip equipA2 =
				equipStringToEstadisticaEquip(equip2);

		if (resultat1 == resultat2) {
			equipA1.setEmpatats(equipA1.getEmpatats() + 1);
			equipA2.setEmpatats(equipA2.getEmpatats() + 1);
			assignarPunts(equip1, 1);
			assignarPunts(equip2, 1);
		}

		if (resultat1 > resultat2) {
			equipA1.setGuanyats(equipA1.getGuanyats() + 1);
			equipA2.setPerduts(equipA2.getPerduts() + 1);
			assignarPunts(equip1, PUNTS_VICTORIA);
		}

		if (resultat2 > resultat1) {
			equipA2.setGuanyats(equipA2.getGuanyats() + 1);
			equipA1.setPerduts(equipA1.getPerduts() + 1);
			assignarPunts(equip2, PUNTS_VICTORIA);
		}

		carregarDadesTaula();
	}

	/**
	 * Mètode que s'encarrega d'assignar punts a un equip.
	 *
	 * @param equip
	 * 			--> Equip al que se li assignaran el punts.
	 * @param punts
	 * 			--> Numero de punts a assignar.
	 */
	public final void assignarPunts(final String equip, final int punts) {

		for (int i = 0; i < estadisticaEquip.size(); i++) {
			if (estadisticaEquip.get(i).getNomEquip()
					.equals(equip)) {
				estadisticaEquip.get(i).setPunts(
				estadisticaEquip.get(i).getPunts() + punts);
				break;
			}
		}

	}

	/**
	 * Mètode per generar objectes "EstadisticaEquip" a partir del nom
	 * d'un equip.
	 *
	 * @param equip
	 * 			--> Nom de l'equip.
	 * @return
	 * 			--> Retorna un objecte de tipus
	 * 				"EstadisticaEquip".
	 */
	public final EstadisticaEquip equipStringToEstadisticaEquip(
			final String equip) {

		for (int i = 0; i < estadisticaEquip.size(); i++) {
			EstadisticaEquip equipActual = estadisticaEquip.get(i);
			if (equip.equals(equipActual.getNomEquip())) {
				return equipActual;
			}
		}

		return null;

	}

	/**
	 * Mètode utilitzat per obtenir el nom de la lliga.
	 * @return
	 * 		--> Retorna un String corresponent al nom de la lliga.
	 */
	public final String obtenirNomLliga() {
		if (nomLliga.equals("")) {
			return "Carrega o crea una lliga i gaudeix"
					+ " del programa";
		} else {
			return nomLliga;
		}
	}

	/**
	 * Mètode que s'encarrega d'ordenar l'ArrayList "EstadisticaEquip"
	 * a partir del número de punts.
	 */
	public final void ordenarClassificacio() {
		Collections.sort(estadisticaEquip,
				new Comparator<EstadisticaEquip>() {
			public int compare(final EstadisticaEquip e1,
					final EstadisticaEquip e2) {

				return new Integer(e2.getPunts())
				.compareTo(new Integer(e1
						.getPunts()));
			}
		});
	}

	/**
	 * Mètode que s'encarrega de generar un fitxer XML a partir del
	 * nom de la lliga i l'ArrayList d'objectes de tipus "EstadisticaEquip".
	 *
	 * @return --> Retorna un "String" corresponent al XML generat.
	 */
	public final String generarXML() {

		Lliga lligaFutbol = new Lliga(obtenirNomLliga(),
				estadisticaEquip);

		String xml = xstream.toXML(lligaFutbol);
		System.out.println("La lliga en XML té el següent aspecte:");
		System.out.println("----------------------------");
		System.out.println(xml);
		System.out.println("----------------------------");
		return xml;
	}

	/**
	 * Mètode que s'encarrega de recuperar una lliga a partir d'un fitxer
	 * en format XML prèviament validat per un XSD.
	 *
	 * @param entrada
	 * 			--> Fitxer XML des del qual es recuperara la
	 * 				lliga.
	 * @return --> Retorna un objecte de tipus "Lliga" generat a partir
	 * 			   des del fitxer XML.
	 */
	public final Lliga recuperarLligaXML(final File entrada) {
		try {
			Lliga n = (Lliga) xstream.fromXML(
					new FileInputStream(entrada));
			Lliga lligaFutbol = (Lliga) xstream.fromXML(
					new FileReader(entrada
					.getAbsoluteFile()));
			this.nomLliga = lligaFutbol.getNomLliga();
			return n;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Mètode que s'encarrega de validar un fitxer XML a partir
	 * d'un fitxer XSD.
	 *
	 * @param xml --> Objecte de tipus "File" corresponent al fitxer
	 * 				a validar.
	 * @return --> Retorna un boolea. "True" si el fitxer s'ha validat
	 * 				correctament, "False" si no
	 * 				s'ha pogut validar el fitxer.
	 */
	public final boolean validarXMLambXSD(final File xml) {

		Schema schema;
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(
							XMLConstants
							.W3C_XML_SCHEMA_NS_URI);
			schema = factory.newSchema(getClass().
					getResource("/lliga.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			return true;
		} catch (SAXException e) {

			return false;
		} catch (IOException e) {

			System.out.println("Fitxer no trobat!");
		}
		return false;

	}

	/**
	 * Mètode per determinar si hi ha dades carregades al model
	 * de la taula principal del programa.
	 *
	 * @return
	 * 		--> Retorna "True" si hi ha dades carregades. "False" si
	 * 			no hi ha dades carregades.
	 */
	public final boolean hiHaDadesCarregades() {

		if (this.getEstadisticaEquipModel().getRowCount() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * Mètode per reinicialitzar al model de la taula principal
	 * del programa.
	 */
	public final void renicialitzarModel() {
		this.estadisticaEquipModel = new DefaultTableModel();
	}

	/**
	 * Mètode per obtenir el valor de la variable "boolean"
	 * hiHaCanvis.
	 *
	 * @return
	 * 		--> Retorna el valor de la variable hiHaCanvis.
	 */
	public final boolean isHiHaCanvis() {
		return hiHaCanvis;
	}

	/**
	 * Mètode per assignar un valor a la variable de tipus "boolean"
	 * hiHaCanvis.
	 *
	 * @param hiHaCanvisA --> Valor a assignar.
	 */
	public final void setHiHaCanvis(final boolean hiHaCanvisA) {
		this.hiHaCanvis = hiHaCanvisA;
	}

	/**
	 * Mètode per obtenir el valor de la variable nomLliga.
	 * @return
	 * 		--> Retorna el valor de la variable nomLliga.
	 */
	public final String getNomLliga() {
		return nomLliga;
	}

	/**
	 * Mètode per assignar valor a la variable nomLliga.
	 * @param nomLligaA
	 * 				--> Nom de la lliga.
	 */
	public final void setNomLliga(final String nomLligaA) {
		this.nomLliga = nomLligaA;
	}

}
