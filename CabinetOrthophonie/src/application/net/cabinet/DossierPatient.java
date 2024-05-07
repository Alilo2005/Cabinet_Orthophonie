package application.net.cabinet;

public class DossierPatient {

	private int numDossier;
	private RendezVous[] rendezVous;
	private BO[] BOs;
	private FicheSuivi monFicheDeSuivi;
	
	
	public DossierPatient(int numDossier, RendezVous[] mesRendezVous,
			BO[] mesBOs, FicheSuivi monFicheDeSuivi) {
		super();
		this.numDossier = numDossier;
		this.rendezVous = mesRendezVous;
		this.BOs = mesBOs;
		this.monFicheDeSuivi = monFicheDeSuivi;
	}
	
	public DossierPatient() {
		super();
	}

	public int getNumDossier() {
		return numDossier;
	}
	public void setNumDossier(int numDossier) {
		this.numDossier = numDossier;
	}
	public RendezVous[] getMesRendezVous() {
		return rendezVous;
	}
	public void setMesRendezVous(RendezVous[] mesRendezVous) {
		this.rendezVous = mesRendezVous;
	}
	public BO[] getMesBOs() {
		return BOs;
	}
	public void setMesBOs(BO[] mesBOs) {
		this.BOs = mesBOs;
	}
	public FicheSuivi getMonFicheDeSuivi() {
		return monFicheDeSuivi;
	}
	public void setMonFicheDeSuivi(FicheSuivi monFicheDeSuivi) {
		this.monFicheDeSuivi = monFicheDeSuivi;
	}
	
}
