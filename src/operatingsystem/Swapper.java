package operatingsystem;

import java.util.Random;

import process.PCB;

public class Swapper extends Thread {
	Random rand =  new Random();
	int timeSleep;
	int minTime = 750;
	int maxTime = 1500;
	
	public void run() {
		while(true) {
			//Setting the amount of sleeping time for the thread
			timeSleep = rand.nextInt(maxTime - minTime) + minTime;
			//Picking the PCB from the IOQueue			
			PCB pcb;
			try {
				Thread.sleep(timeSleep);
				
				pcb = OS.scheduler.pickPCBFromSwapQueue();
				OS.outlog("Swapper -> Picking Process "+pcb.getPid()+" FromSwapQueue");
				if(pcb != null)
				OS.mmu.makeSwappingOldProcess(pcb);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				OS.outlog(e.getMessage());
				
			}
			
			
		}
	}

	public Swapper() {
		super();
		// TODO Auto-generated constructor stub
	}

}
