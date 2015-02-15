package net.josegarvin.generadorClassificacions;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Controlador {

	ArrayList<String> equipsLliga = new ArrayList<String>();;
	ArrayList<EstadisticaEquip> estadisticaEquip = new ArrayList<EstadisticaEquip>();
	DefaultTableModel estadisticaEquipModel = new DefaultTableModel();
	String[] capsaleres = { "Nom equip", "Victories", "Empats", "Derrotes",
			"Punts" };

	public Controlador() {

	}

	public ArrayList<String> getEquipsLliga() {
		return equipsLliga;
	}

	public void setEquipsLliga(ArrayList<String> equipsLliga) {
		this.equipsLliga = equipsLliga;
	}

	public void carregarEquips(JComboBox<String> comboEquips) {
		for (int i = 1; i < equipsLliga.size(); i++) {
			comboEquips.addItem(equipsLliga.get(i));
		}
	}

	public void generarEstadisticaEquips() {

		
		for (int i = 1; i < equipsLliga.size(); i++) {
			estadisticaEquip.add(new EstadisticaEquip(equipsLliga.get(i)));
		}

		// estadisticaEquipModel.addColumn(estadisticaEquip.get(0).getNomEquip());

		// estadisticaEquipsToString();
	}

	public void carregarDadesTaula() {
		//estadisticaEquipModel.addRow(generarLinia());
		generarEstadisticaEquips();
		
		for(int i = 0; i<estadisticaEquip.size(); i++){
			Object[] linia = generarLinia(estadisticaEquip.get(i));
			
			estadisticaEquipModel.addRow(linia);
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

	public String obtenirNomLliga(){
		if(equipsLliga.size() == 0){
			return "Carrega o crea una lliga i gaudeix del programa";
		}
		else{
			return equipsLliga.get(0);
		}
	}
	// public void estadisticaEquipsToString(){
	// for(int i = 0; i<estadisticaEquipModel.size();i++){
	// EstadisticaEquip equipActual = estadisticaEquipModel.get(i);
	// System.out.println(equipActual.getNomEquip() + " Victories:" +
	// equipActual.getGuanyats() +
	// " Derrotes:" + equipActual.getPerduts() + " Empats:" +
	// equipActual.getEmpatats() +
	// " Punts:" + equipActual.getPunts());
	// }
	// }
}
