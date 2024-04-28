package application.net.cabinet;

public class QO extends Question{
	
	private String categorie; 
	private String reponse;
    public QO(){

    }
    public QO(String question){
        super(question);
    }
    
	public QO(String question,int score, String reponse) throws InexpectedValue {
		super(question,score);
		this.reponse = reponse;
	}
	
	public QO(String question,String categorie, String reponse) {
		super(question);
		this.categorie = categorie;
		this.reponse = reponse;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse){
		this.reponse = reponse;
	}
	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void repondre() {
		// TODO Auto-generated method stub
		
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	@Override
	public void evaluer() {
		// TODO Auto-generated method stub
		
	}
}

