package net.josegarvin.generadorClassificacions;

import java.util.ArrayList;

public class Lliga {

	String nomLliga;
	ArrayList<EstadisticaEquip> classificacio;
	
	public Lliga(String nom, ArrayList<EstadisticaEquip> classficacioLliga){
		this.nomLliga = nom;
		this.classificacio = classficacioLliga;
	}
}
