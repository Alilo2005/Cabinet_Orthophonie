package application.net.cabinet;

public abstract class Question {
    private String question;
    private int score;
    public Question(){

    }
    public Question(String question){
    	this.question = question;
    }
    public Question(String question,int score) throws InexpectedValue{
        setQuestion(question);
        setScore(score);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) throws InexpectedValue {
        this.question = question;
    }
    
    abstract public void afficher();
    abstract public void repondre();
    abstract public void evaluer();
	public double getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
    
}

