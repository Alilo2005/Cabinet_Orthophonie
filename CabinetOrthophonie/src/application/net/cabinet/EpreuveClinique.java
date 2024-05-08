package application.net.cabinet;

public class EpreuveClinique {

	String[] observationsCliniques;
	Test[] tests;
	public EpreuveClinique(String[] observationsCliniques, Test[] tests) {
		super();
		this.observationsCliniques = observationsCliniques;
		this.tests = tests;
	}
	
	public EpreuveClinique() {
		super();
	}

	public String[] getObservationsCliniques() {
		return observationsCliniques;
	}
	public void setObservationsCliniques(String[] observationsCliniques) {
		this.observationsCliniques = observationsCliniques;
	}
	public Test[] getTests() {
		return tests;
	}
	public void setTests(Test[] tests) {
		this.tests = tests;
	}
	
}
