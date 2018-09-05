package interruption;

import operatingsystem.OS;
import operatingsystem.Scheduler;

public class Interruption extends Thread {

	public Interruption() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Cette methode permet de generer des interruptions
	 * elle s'apparente a l'interruption vector table des 
	 * vrais systemes d'exploitations. 
	 * @param nummApp
	 * @param numInterruption
	 */
	public void makeInterruption(int nummApp, int numInterruption) {
		
		switch (numInterruption) {
		case 0:
		case 1:
			//On ne met rien dans ce case car que l'interruption soit 0, un ou 2 on fait appel a la meme 
			// fonction. 
		case 2:
			OS.systemCall.makeSystemCall(nummApp, numInterruption);
			break;
			
		case 11: 
			//IO interrupt
			IORequest ioRequest = new IORequest(OS.RAM.currentPCB, OS.RAM.currentPCB.getPid());
			OS.scheduler.addRequestToIOQueue(ioRequest);
			
									
		}
	}

}
