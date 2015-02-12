package net.josegarvin.generadorClassificacions;

public class EstadisticaEquip {
	
	String nomEquip;
	int guanyats;
	int empatats;
	int perduts;
	int punts;
	
	public EstadisticaEquip(String nom){
		this.nomEquip = nom;
		this.guanyats = 0;
		this.empatats = 0;
		this.perduts = 0;
		this.punts = 0;		
	}

	public String getNomEquip() {
		return nomEquip;
	}

	public void setNomEquip(String nomEquip) {
		this.nomEquip = nomEquip;
	}

	public int getGuanyats() {
		return guanyats;
	}

	public void setGuanyats(int guanyats) {
		this.guanyats = guanyats;
	}

	public int getEmpatats() {
		return empatats;
	}

	public void setEmpatats(int empatats) {
		this.empatats = empatats;
	}

	public int getPerduts() {
		return perduts;
	}

	public void setPerduts(int perduts) {
		this.perduts = perduts;
	}

	public int getPunts() {
		return punts;
	}

	public void setPunts(int punts) {
		this.punts = punts;
	}
	
	
	

}
