package application.net.cabinet;

public class FicheSuivi {

	private Objectif[] objectives;
	

	public FicheSuivi(Objectif[] objectives) {
		super();
		this.objectives = objectives;
	}
	

	public FicheSuivi() {
		super();
	}


	public Objectif[] getObjectives() {
		return objectives;
	}

	public void setObjectives(Objectif[] objectives) {
		this.objectives = objectives;
	}
}
