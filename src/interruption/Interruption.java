package interruption;

import operatingsystem.OS;
import process.PCB;

public class Interruption extends Thread {

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
			
		case 11: 
			//IO interrupt
			IORequest ioRequest = new IORequest(OS.RAM.currentPCB, OS.RAM.currentPCB.getPid());
			OS.scheduler.addRequestToIOQueue(ioRequest);
			PCB newPCB = OS.scheduler.removePCBFromReadyQueue();
			
			OS.cpu.execute(newPCB.getProcess(), newPCB);
			
		}
	}

}
