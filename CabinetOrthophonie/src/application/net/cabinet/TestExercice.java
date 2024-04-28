package application.net.cabinet;

public class TestExercice extends Test {
	private Exercice[] exercices;

	public TestExercice(String nom, String capacite,double compteRendu,String conclusion, Exercice[] exercices) throws OverCapacityEception {
		super(nom, capacite,compteRendu,conclusion);
		this.exercices = exercices;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repondre() {
		// TODO Auto-generated method stub
		
	}

	public Exercice[] getExercices() {
		return exercices;
	}

	public void setExercices(Exercice[] exercices) {
		this.exercices = exercices;
	}

	@Override
	public void evaluer() {
		// TODO Auto-generated method stub
		
	}

}
