package interruption;

import operatingsystem.OS;
import process.PCB;
public class IOHandler extends Thread{
	
	public void run() {
		while(true) {
			//Picking the PCB from the IOQueue
			
			PCB pcb;
			try {
				
				pcb = OS.scheduler.pickRequestFromIOQueue().getPcb();
				
				Thread.sleep(1000);
				
				OS.scheduler.addPCBToReadyQueue(pcb);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			}
			
			
		}
	}

	public IOHandler(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

}
