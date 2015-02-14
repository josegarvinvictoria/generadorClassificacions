package net.josegarvin.generadorClassificacions;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Controlador {

	ArrayList<String> equipsLliga;
	DefaultTableModel estadisticaEquipModel = new DefaultTableModel();
	String[] capsaleres = {"Nom equip", "Victories", "Empats", "Derrotes", "Punts"};
	
	public Controlador(){
		
	}

	public ArrayList<String> getEquipsLliga() {
		return equipsLliga;
	}

	public void setEquipsLliga(ArrayList<String> equipsLliga) {
		this.equipsLliga = equipsLliga;
	}
	
	public void carregarEquips(JComboBox<String> comboEquips){
		for(int i = 0; i<equipsLliga.size() - 1; i++){
			comboEquips.addItem(equipsLliga.get(i));
		}
	}
	
	public void generarEstadisticaEquips(){
		
		ArrayList<EstadisticaEquip> estadisticaEquip = new ArrayList<EstadisticaEquip>();
		for(int i = 1; i<equipsLliga.size();i++){
			estadisticaEquip.add(new EstadisticaEquip(equipsLliga.get(i)));
			
		}
		
			estadisticaEquipModel.addColumn(estadisticaEquip.get(0).getNomEquip());
		
		//estadisticaEquipsToString();
	}
	
	public void estadisticaEquipsToJTable(JTable taulaEstadistiques){
		taulaEstadistiques = new JTable((TableModel) estadisticaEquipModel);
		
		generarEstadisticaEquips();
	}
	
	  public void generarCapsaleres(FinestraClassificacions finestra) {
		  
		    for(String capsalera: capsaleres) {
		      estadisticaEquipModel.addColumn(capsalera);
		    }

		  }

	public DefaultTableModel getEstadisticaEquipModel() {
		return estadisticaEquipModel;
	}

	public void setEstadisticaEquipModel(DefaultTableModel estadisticaEquipModel) {
		this.estadisticaEquipModel = estadisticaEquipModel;
	}
	  
	  
//	public void estadisticaEquipsToString(){
//		for(int i = 0; i<estadisticaEquipModel.size();i++){
//			EstadisticaEquip equipActual = estadisticaEquipModel.get(i);
//			System.out.println(equipActual.getNomEquip() + " Victories:" + equipActual.getGuanyats() +
//					" Derrotes:" + equipActual.getPerduts() + " Empats:" + equipActual.getEmpatats() +
//					" Punts:" + equipActual.getPunts());
//		}
//	}
}
