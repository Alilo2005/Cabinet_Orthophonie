package application.net.cabinet;

public class Objectif {

	private String nom;
	private Terme terme;
	private boolean isAtteint;
	
	public Objectif(String nom, Terme terme, boolean isAtteint) {
		super();
		this.nom = nom;
		this.terme = terme;
		this.isAtteint = isAtteint;
	}
	
	public Objectif() {
		super();
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Terme getTerme() {
		return terme;
	}
	public void setTerme(Terme terme) {
		this.terme = terme;
	}
	public boolean isAtteint() {
		return isAtteint;
	}
	public void setAtteint(boolean isAtteint) {
		this.isAtteint = isAtteint;
	}
	
	
}
