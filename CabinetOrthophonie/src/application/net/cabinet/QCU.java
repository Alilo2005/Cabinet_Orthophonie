package application.net.cabinet;


public class QCU extends Question{
    private String[] propositions;
    private String propositionValide;
    private String[] propositionsErronnee;
    public QCU(){

    }
    public QCU(String question,int score,String[] propositions,String propositionValide) throws InexpectedValue{
        super(question,score);
        setPropositions(propositions);
        setPropositionValide(propositionValide);
    }
    public String[] getPropositions() {
        return propositions;
    }

    public void setPropositions(String[] propositions) {
        this.propositions = propositions;
    }

    public String getPropositionValide() {
        return propositionValide;
    }

    public void setPropositionValide(String propositionValide) {
        this.propositionValide = propositionValide;
    }
    public String[] getPropositionsErronnee(){
        return propositionsErronnee;
    }

    public void setPropositionsErronnee(String[] propositionsErronnee) {
        this.propositionsErronnee = propositionsErronnee;
    }
	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void repondre() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void evaluer() {
		// TODO Auto-generated method stub
		
	}
}
