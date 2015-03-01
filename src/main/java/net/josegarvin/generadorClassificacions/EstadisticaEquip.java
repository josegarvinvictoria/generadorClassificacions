package net.josegarvin.generadorClassificacions;

/**
 * Classe per crear objectes de tipus "EstadisticaEquip" en
 * els quals s'emmagatzemara el nom de l'equip, els partits guanyats,
 * els partits perduts, els empatats i els punts.
 *
 * @author Jose Garvin Victoria
 *
 */
public class EstadisticaEquip {

	/**
	 * Nom de l'equip.
	 */
	private String nomEquip;

	/**
	 * Numero de partits guanyats.
	 */
	private int guanyats;

	/**
	 * Numero de partits empatats.
	 */
	private int empatats;

	/**
	 * Numero de partits perduts.
	 */
	private int perduts;

	/**
	 * Numero de punts.
	 */
	private int punts;

	/**
	 * Contructor d'objectes de tipus "EstadisticaEquip".
	 * @param nom
	 * 			--> Nom de l'equip.
	 */
	public EstadisticaEquip(final String nom) {
		this.nomEquip = nom;
		this.guanyats = 0;
		this.empatats = 0;
		this.perduts = 0;
		this.punts = 0;
	}

	/**
	 * Mètode per obtenir el nom de l'equip.
	 *
	 * @return
	 * 		--> String corresponent al nom de l'equip.
	 */
	public final String getNomEquip() {
		return nomEquip;
	}

	/**
	 * Mètode per assignar un nom d'equip.
	 *
	 * @param nomEquipA
	 * 			--> Nom de l'equip a assignar.
	 */
	public final void setNomEquip(final String nomEquipA) {
		this.nomEquip = nomEquipA;
	}

	/**
	 * Mètode per obtenir el numero de partits guanyats.
	 *
	 * @return
	 * 		--> Numero de partits guanyats.
	 */
	public final int getGuanyats() {
		return guanyats;
	}

	/**
	 * Mètode per assignar el numero de partits guanyats.
	 *
	 * @param guanyatsA
	 * 			-->Numero de partits guanyats.
	 */
	public final void setGuanyats(final int guanyatsA) {
		this.guanyats = guanyatsA;
	}

	/**
	 * Mètode per obtenir el numero de partits empatats.
	 *
	 * @return
	 * 		-->Numero de partits empatats.
	 */
	public final int getEmpatats() {
		return empatats;
	}

	/**
	 * Mètode per assignar el numero de partits empatats.
	 * @param empatatsA
	 * 			-->Numero de partits empatats.
	 */
	public final void setEmpatats(final int empatatsA) {
		this.empatats = empatatsA;
	}

	/**
	 *  Mètode per obtenir el numero de partits guanyats.
	 *
	 * @return
	 * 		--> Numero de partits perduts.
	 */
	public final int getPerduts() {
		return perduts;
	}

	/**
	 * Mètode per assignar el numero de partits perduts.
	 *
	 * @param perdutsA
	 * 			--> Mètode de partits empatats.
	 */
	public final void setPerduts(final int perdutsA) {
		this.perduts = perdutsA;
	}

	/**
	 *  Mètode per obtenir el numero de punts.
	 *
	 * @return
	 * 		-->Numero de punts.
	 */
	public final int getPunts() {
		return punts;
	}

	/**
	 * Mètode per assignar el numero de punts.
	 *
	 * @param puntsA
	 * 			--> Numero de punts.
	 */
	public final void setPunts(final int puntsA) {
		this.punts = puntsA;
	}
}
