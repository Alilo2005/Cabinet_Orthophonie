package application.net.cabinet;

import java.util.Map;

public class Orthophoniste {
	
	private Compte compte;
	private Agenda agenda;
	private Map<Integer,DossierPatient> dossiers;
	private Map<Integer,Patient> patients;
	
	
	
	public Orthophoniste(Compte compte, Agenda agenda,
			Map<Integer, DossierPatient> dossiers,
			Map<Integer, Patient> patients) {
		super();
		this.compte = compte;
		this.agenda = agenda;
		this.dossiers = dossiers;
		this.patients = patients;
	}
	
	

	public Orthophoniste() {
		super();
	}



	/*
	 * Consultation
	 */
	public void pregrammerRenderVous(String date,String heure,RendezVousType type,double duree,
			String nom,String prenom,int age){
		//TODO
	}
	
	/*
	 * SeanceDeSuivi
	 */
	public void pregrammerRenderVous(String date,String heure,RendezVousType type,double duree,
			int idDossier,boolean isPresentiel){
		//TODO
	}
	
	/*
	 * AtelierDeGroupe
	 */
	public void pregrammerRenderVous(String date,String heure,RendezVousType type,double duree,
			String thematique,int[] idpatients){
		//TODO
	}
	
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Map<Integer, DossierPatient> getDossiers() {
		return dossiers;
	}

	public void setDossiers(Map<Integer, DossierPatient> dossiers) {
		this.dossiers = dossiers;
	}

	public Map<Integer,Patient> getPatients() {
		return patients;
	}

	public void setPatients(Map<Integer,Patient> patients) {
		this.patients = patients;
	}

}
