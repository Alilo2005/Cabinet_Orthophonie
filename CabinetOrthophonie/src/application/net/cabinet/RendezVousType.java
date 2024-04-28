package application.net.cabinet;

public enum RendezVousType {
	
	Consultation(1.5),SeanceDeSuivi(1),AtelierDeGroupe(1);
	double time ;
	RendezVousType(double time){
		this.time = time;
	}
	RendezVousType(){
		
	}
}
