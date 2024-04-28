package application.net.cabinet;

import java.util.ArrayList;

public class Exercice {

	private String consigne;
	private String nomMateriel;
	private ArrayList<Integer> score;
	public Exercice(String consigne, String nomMateriel,ArrayList<Integer> score) {
		super();
		this.consigne = consigne;
		this.nomMateriel = nomMateriel;
		this.score = score;
	}
	public String getConsigne() {
		return consigne;
	}
	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}
	public String getNomMateriel() {
		return nomMateriel;
	}
	public void setNomMateriel(String nomMateriel) {
		this.nomMateriel = nomMateriel;
	}
	public ArrayList<Integer> getScore() {
		return score;
	}
	public void setScore(ArrayList<Integer> score) {
		this.score = score;
	}
	
	public void afficher(){
		//TODO
	};
	public void repondre(){
		//TODO
	};
	public void evaluer(){
		//TODO
	};
	
}
