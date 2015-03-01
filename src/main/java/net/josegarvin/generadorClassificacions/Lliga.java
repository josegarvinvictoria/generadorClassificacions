package net.josegarvin.generadorClassificacions;

import java.util.ArrayList;

/**
 * Classe per crear objectes de tipus "Lliga".
 *
 * @author Jose Garvin Victoria
 *
 */
public class Lliga {

	/**
	 * Variable on s'emmagatzema el nom de la lliga.
	 */
	private String nomLliga;

	/**
	 * ArrayList de "EstadisticaEquip" on es desen totes
	 * les estadistiques de tots els equips que formen
	 * la lliga.
	 */
	private ArrayList<EstadisticaEquip> classificacio;

	/**
	 * Constructor d'objectes de tipus "Lliga".
	 *
	 * @param nom
	 * 			--> Nom de la lliga.
	 *
	 * @param classficacioLliga
	 * 			--> ArrayList amb les estadistiques de
	 * 				cada equip.
	 */
	public Lliga(final String nom, final ArrayList<EstadisticaEquip>
	classficacioLliga) {
		this.setNomLliga(nom);
		this.setClassificacio(classficacioLliga);
	}

	/**
	 * Mètode per obtenir la variable "classificacio".
	 *
	 * @return
	 * 		--> Retorna un ArrayList de tipus "EstadisticaEquip"
	 */
	public final ArrayList<EstadisticaEquip> getClassificacio() {
		return classificacio;
	}

	/**
	 * Mètode per assignar valor a la variable "classificacio".
	 *
	 * @param classificacioA
	 * 			-->  ArrayList de tipus "EstadisticaEquip".
	 */
	public final void setClassificacio(final ArrayList<EstadisticaEquip>
	classificacioA) {
		this.classificacio = classificacioA;
	}

	/**
	 * Mètode per obtenir el nom de la lliga.
	 *
	 * @return
	 * 		--> String corresponent al nom de la lliga.
	 */
	public final String getNomLliga() {
		return nomLliga;
	}

	/**
	 * Mètode per assignar el nom a la lliga.
	 *
	 * @param nomLligaA
	 *          --> String corresponent al nom de la lliga.
	 */
	public final void setNomLliga(final String nomLligaA) {
		this.nomLliga = nomLligaA;
	}
}
