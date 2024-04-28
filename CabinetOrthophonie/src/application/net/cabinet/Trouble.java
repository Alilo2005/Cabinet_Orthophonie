package application.net.cabinet;

public class Trouble {

	private String nom;
	private TroubleCategorie categorie;
	public Trouble(String nom, TroubleCategorie categorie) {
		super();
		this.nom = nom;
		this.categorie = categorie;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public TroubleCategorie getCategorie() {
		return categorie;
	}
	public void setCategorie(TroubleCategorie categorie) {
		this.categorie = categorie;
	}
	
}
