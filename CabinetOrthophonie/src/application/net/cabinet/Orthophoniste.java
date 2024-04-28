package application.net.cabinet;

public class Orthophoniste {
	
	Compte compte;
	Agenda agenda;
	
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
}
