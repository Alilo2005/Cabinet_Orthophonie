package application.net.cabinet;

public class BO {

	private Anamnese anamnese;
	private EpreuveClinique epreuve;
	private Diagnostic diagnostic;
	private ProjetTherapeutique projet;
	public BO(Anamnese anamnese, EpreuveClinique epreuve,
			Diagnostic diagnostic, ProjetTherapeutique projet) {
		super();
		this.anamnese = anamnese;
		this.epreuve = epreuve;
		this.diagnostic = diagnostic;
		this.projet = projet;
	}
	public BO(EpreuveClinique epreuve, Diagnostic diagnostic,
			ProjetTherapeutique projet) {
		super();
		this.epreuve = epreuve;
		this.diagnostic = diagnostic;
		this.projet = projet;
	}
	public BO() {
		super();
	}
	public Anamnese getAnamnese() {
		return anamnese;
	}
	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}
	public EpreuveClinique getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(EpreuveClinique epreuve) {
		this.epreuve = epreuve;
	}
	public Diagnostic getDiagnostic() {
		return diagnostic;
	}
	public void setDiagnostic(Diagnostic diagnostic) {
		this.diagnostic = diagnostic;
	}
	public ProjetTherapeutique getProjet() {
		return projet;
	}
	public void setProjet(ProjetTherapeutique projet) {
		this.projet = projet;
	}
	
}
