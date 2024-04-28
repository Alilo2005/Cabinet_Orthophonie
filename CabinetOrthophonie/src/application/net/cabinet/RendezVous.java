package application.net.cabinet;

public class RendezVous {
	
	private double duree;
	private RendezVousType type;
	private String resume;
	
	
	public RendezVous(RendezVousType type,Genre genre){
		setType(type);
		switch(type){
		case AtelierDeGroupe:
			setDuree(1);
			break;
		case Consultation:
			switch(genre){
			case Adulte:
				setDuree(1.5);
				break;
			case Enfant:
				setDuree(2.5);
				break;
			default:
				setDuree(1.5);
				break;
			
			}
			break;
		case SeanceDeSuivi:
			setDuree(1);
			break;
		default:
			setDuree(1);
			break;
		}
	}
	
	public RendezVous(double duree,RendezVousType type,String resume){
		setDuree(duree);
		setType(type);
		setResume(resume);
	}
	
	public RendezVous(double duree,RendezVousType type){
		setDuree(duree);
		setType(type);
	}
	
	public RendezVous(){
		
	}
	
	public double getDuree() {
		return duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public RendezVousType getType() {
		return type;
	}

	public void setType(RendezVousType type) {
		this.type = type;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}
