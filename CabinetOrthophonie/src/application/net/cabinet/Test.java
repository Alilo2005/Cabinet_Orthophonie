package application.net.cabinet;

public abstract class Test {

	private String nom;
	private String capacite;
	private double compteRendu;
	private String conclusion;
	
	public Test(String nom, String capacite,double compteRendu,String conclusion) throws OverCapacityEception {
		super();
		this.nom = nom;
		this.setCapacite(capacite);
		this.compteRendu = compteRendu;
		this.conclusion = conclusion;
	}
	public Test(){
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCapacite() {
		return capacite;
	}
	public void setCapacite(String capacite) throws OverCapacityEception {
		this.capacite = capacite;
	}
	
	public abstract void afficher();
	public abstract void repondre();
	public abstract void evaluer();
	public double getCompteRendu() {
		return compteRendu;
	}
	public void setCompteRendu(double compteRendu) {
		this.compteRendu = compteRendu;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
}
