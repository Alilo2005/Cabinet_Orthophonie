package application.net.cabinet;

public class Enfant extends Patient  {
	
	private String classeDeEtude;
	private String[] telParent;
	public String getClasseDeEtude() {
		return classeDeEtude;
	}
	public void setClasseDeEtude(String classeDeEtude) {
		this.classeDeEtude = classeDeEtude;
	}
	public String[] getTelParent() {
		return telParent;
	}
	public void setTelParent(String[] telParent) {
		this.telParent = telParent;
	}
	
}
