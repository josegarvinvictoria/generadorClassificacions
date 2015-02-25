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

public class Controlador {

	ArrayList<String> equipsLliga = new ArrayList<String>();;
	ArrayList<EstadisticaEquip> estadisticaEquip = new ArrayList<EstadisticaEquip>();
	DefaultTableModel estadisticaEquipModel = new DefaultTableModel();
	String nomLliga ="";
	String[] capsaleres = { "Nom equip", "Victories", "Empats", "Derrotes",
			"Punts" };
	XStream xstream = new XStream();
	boolean hiHaCanvis = false;
	

	public Controlador() {
		xstream.alias("lliga", Lliga.class);
		xstream.alias("equip", EstadisticaEquip.class);
	}

	public ArrayList<String> getEquipsLliga() {
		return equipsLliga;
	}

	public void setEquipsLliga(ArrayList<String> equipsLliga) {
		this.equipsLliga = equipsLliga;
	}

	
	public ArrayList<EstadisticaEquip> getEstadisticaEquip() {
		return estadisticaEquip;
	}

	public void setEstadisticaEquip(ArrayList<EstadisticaEquip> estadisticaEquip) {
		this.estadisticaEquip = estadisticaEquip;
	}

	public void carregarEquips(JComboBox<String> comboEquips) {
		for (int i = 0; i < estadisticaEquip.size(); i++) {
			comboEquips.addItem(estadisticaEquip.get(i).getNomEquip());
		}
	}

	public void generarEstadisticaEquips() {
		estadisticaEquip = new ArrayList<EstadisticaEquip>();
		for (int i = 1; i < equipsLliga.size(); i++) {
			estadisticaEquip.add(new EstadisticaEquip(equipsLliga.get(i)));
		}

	}

	public void carregarDadesTaula() {
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

	public void netejarTaula() {
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

	private Object[] generarLinia(EstadisticaEquip equip) {
		Object[] data = new Object[5];

		data[0] = equip.nomEquip;
		data[1] = equip.guanyats;
		data[2] = equip.empatats;
		data[3] = equip.perduts;
		data[4] = equip.punts;

		return data;
	}

	public void generarCapsaleres() {

		for (String capsalera : capsaleres) {
			estadisticaEquipModel.addColumn(capsalera);
		}

	}

	public DefaultTableModel getEstadisticaEquipModel() {
		return estadisticaEquipModel;
	}

	public void setEstadisticaEquipModel(DefaultTableModel estadisticaEquipModel) {
		this.estadisticaEquipModel = estadisticaEquipModel;
	}

	public void calcularPunts(String equip1, int resultat1, String equip2,
			int resultat2) {
		EstadisticaEquip equipA1 = equipStringToEstadisticaEquip(equip1);
		EstadisticaEquip equipA2 = equipStringToEstadisticaEquip(equip2);

		if (resultat1 == resultat2) {
			equipA1.setEmpatats(equipA1.getEmpatats() + 1);
			equipA2.setEmpatats(equipA2.getEmpatats() + 1);
			assignarPunts(equip1, 1);
			assignarPunts(equip2, 1);
		}

		if (resultat1 > resultat2) {
			equipA1.setGuanyats(equipA1.getGuanyats() + 1);
			equipA2.setPerduts(equipA2.getPerduts() + 1);
			assignarPunts(equip1, 3);
		}

		if (resultat2 > resultat1) {
			equipA2.setGuanyats(equipA2.getGuanyats() + 1);
			equipA1.setPerduts(equipA1.getPerduts() + 1);
			assignarPunts(equip2, 3);
		}

		carregarDadesTaula();
	}

	public void assignarPunts(String equip, int punts) {

		for (int i = 0; i < estadisticaEquip.size(); i++) {
			if (estadisticaEquip.get(i).getNomEquip().equals(equip)) {
				estadisticaEquip.get(i).setPunts(
						estadisticaEquip.get(i).getPunts() + punts);
				break;
			}
		}

	}

	public EstadisticaEquip equipStringToEstadisticaEquip(String equip) {

		for (int i = 0; i < estadisticaEquip.size(); i++) {
			EstadisticaEquip equipActual = estadisticaEquip.get(i);
			if (equip.equals(equipActual.getNomEquip())) {
				return equipActual;
			}
		}

		return null;

	}

	public String obtenirNomLliga() {
		if (nomLliga.equals("")) {
			return "Carrega o crea una lliga i gaudeix del programa";
		} else {
			return nomLliga;
		}
	}

	public void ordenarClassificacio() {
		Collections.sort(estadisticaEquip, new Comparator<EstadisticaEquip>() {
			public int compare(final EstadisticaEquip e1,
					final EstadisticaEquip e2) {

				return new Integer(e2.getPunts()).compareTo(new Integer(e1
						.getPunts()));
			}
		});
	}

	public String generarXML() {
		
		Lliga lligaFutbol = new Lliga(obtenirNomLliga(), estadisticaEquip);
		
		
		
		
		String xml = xstream.toXML(lligaFutbol);
		System.out.println("La lliga en XML té el següent aspecte:");
		System.out.println("----------------------------");
		System.out.println(xml);
		System.out.println("----------------------------");
		return xml;
	}

	public Lliga recuperarLligaXML(File entrada) {
		try{
			Lliga n = (Lliga)xstream.fromXML(new FileInputStream(entrada));
			Lliga lligaFutbol = (Lliga)xstream.fromXML(new FileReader(entrada.getAbsoluteFile()));
			this.nomLliga = lligaFutbol.nomLliga;
			return n;
			
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public boolean validarXMLambXSD(File xml){
		
            
            Schema schema;
			try {
				SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				schema = factory.newSchema(new File("/home/b4tm4n/lliga/lliga.xsd"));
				Validator validator = schema.newValidator();
	            validator.validate(new StreamSource(xml));
	            return true;
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Fitxer no trobat!");
			}
			return false;
       
        
	}
	
	public boolean hiHaDadesCarregades(){
		
		if(this.getEstadisticaEquipModel().getRowCount() != 0){
			return true;
		}
		return false;
	}
	
	public void renicialitzarModel(){
		this.estadisticaEquipModel = new DefaultTableModel();
	}

	public boolean isHiHaCanvis() {
		return hiHaCanvis;
	}

	public void setHiHaCanvis(boolean hiHaCanvis) {
		this.hiHaCanvis = hiHaCanvis;
	}

	
	
}
