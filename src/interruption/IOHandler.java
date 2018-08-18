package interruption;

import java.util.Random;

import operatingsystem.OS;
import process.PCB;
public class IOHandler extends Thread{
	Random rand =  new Random();
	int timeSleep;
	int minTime = 500;
	int maxTime = 1000;
	
	public void run() {
		while(true) {
			//Setting the amount of sleeping time for the thread
			timeSleep = rand.nextInt(maxTime - minTime) + minTime;
			//Picking the PCB from the IOQueue			
			PCB pcb;
			try {
				
				pcb = OS.scheduler.pickRequestFromIOQueue().getPcb();
				
				//Simulates the handling of the IORequest
				Thread.sleep(timeSleep);
				
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
