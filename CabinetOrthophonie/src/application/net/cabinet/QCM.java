package application.net.cabinet;


public class QCM extends Question{
    private String[] propositions;
    private String[] propositionsValides;
    private String[] propositionsErronnee;


    public QCM(){

    }
    public QCM(String question,int score,String[] propositions,String[] propositionsValides,String[] propositionsErronnee) throws InexpectedValue{
        super(question,score);
        setPropositions(propositions);
        setPropositionsValides(propositionsValides);
        setPropositionsErronnee(propositionsErronnee);
    }
    public String[] getPropositionsValides() {
        return propositionsValides;
    }

    public void setPropositionsValides(String[] propositionsValides) {
        this.propositionsValides = propositionsValides;
    }

    public String[] getPropositions() {
        return propositions;
    }

    public void setPropositions(String[] propositions) {
        this.propositions = propositions;
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

