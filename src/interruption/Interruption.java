package interruption;

import operatingsystem.OS;

public class Interruption {

	public Interruption() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void makeInterruption(int a, int i) {
		
		switch (i) {
		case 1:
			OS.systemCall.makeSystemCall(a, i);
			break;
		case 2:
			OS.systemCall.makeSystemCall(a, i);
			break;
			
		case 3: 
			//IO interrupt
		}
	}

}
