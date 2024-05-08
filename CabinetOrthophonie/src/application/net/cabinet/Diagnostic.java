package application.net.cabinet;

public class Diagnostic {

	private Trouble[] troubles;
	

	public Diagnostic(Trouble[] troubles) {
		super();
		this.troubles = troubles;
	}
	
	

	public Diagnostic() {
		super();
	}



	public Trouble[] getTroubles() {
		return troubles;
	}

	public void setTroubles(Trouble[] troubles) {
		this.troubles = troubles;
	}
}
