package net.josegarvin.generadorClassificacions;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class Controlador {

	ArrayList<String> equipsLliga;	
	
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
}
