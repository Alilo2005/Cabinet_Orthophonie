package application.net.cabinet;

import java.time.LocalDate;

public class Adulte extends Patient {

	private String diplome;
	private String profession;
	private String tel;
	
	
	public Adulte(String nom, String prenom, int age,
			LocalDate dateDeNaissance, String lieuDeNaissance,
			boolean isFirstTime, int idDossier, String diplome,
			String profession, String tel) {
		super(nom, prenom, age, dateDeNaissance, lieuDeNaissance, isFirstTime,
				idDossier);
		this.diplome = diplome;
		this.profession = profession;
		this.tel = tel;
	}
	
	
	public Adulte(String nom, String prenom, int age,
			LocalDate dateDeNaissance, String lieuDeNaissance,
			boolean isFirstTime, int idDossier) {
		super(nom, prenom, age, dateDeNaissance, lieuDeNaissance, isFirstTime,
				idDossier);
	}
	
	


	public Adulte() {
		super();
	}


	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
