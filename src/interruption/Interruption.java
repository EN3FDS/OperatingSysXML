package interruption;

import operatingsystem.OS;
import operatingsystem.Scheduler;

public class Interruption extends Thread {

	public Interruption() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void makeInterruption(int a, int i) {
		
		switch (i) {
		case 0:
		case 1:
			//On ne met rien dans ce case car que línterruption soit un ou 2 on fait appel a la meme 
			// fonction. 
		case 2:
			OS.systemCall.makeSystemCall(a, i);
			break;
			
		case 11: 
			//IO interrupt
			IORequest ioRequest = new IORequest(OS.RAM.currentPCB, OS.RAM.currentPCB.getPid());
			OS.scheduler.addRequestToIOQueue(ioRequest);
			System.out.println("la que des requetes IO a "+ Scheduler.getIoRequestQueue().size()+" IORequest");
									
		}
	}

}
