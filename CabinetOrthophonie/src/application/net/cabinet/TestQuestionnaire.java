package application.net.cabinet;

import java.util.HashSet;

public class TestQuestionnaire extends Test {

	private HashSet<Question> questions;

	
	public TestQuestionnaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestQuestionnaire(String nom, String capacite,double compteRendu,String conclusion,HashSet<Question> questions) throws OverCapacityEception {
		super(nom, capacite,compteRendu,conclusion);
		this.questions = questions;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repondre() {
		// TODO Auto-generated method stub
		
	}

	public HashSet<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(HashSet<Question> questions) {
		this.questions = questions;
	}

	@Override
	public void evaluer() {
		// TODO Auto-generated method stub
		
	}
}
