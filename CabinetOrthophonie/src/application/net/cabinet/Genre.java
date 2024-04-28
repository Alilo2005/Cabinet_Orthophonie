package application.net.cabinet;

public enum Genre {
	Adulte(1.5),Enfant(2.5);
	double time;
	Genre(double time){
		this.time = time;
	}
	
	Genre(){
		
	}
}
