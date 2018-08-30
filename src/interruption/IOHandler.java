package interruption;

import java.util.Random;

import operatingsystem.OS;
import process.PCB;
public class IOHandler extends Thread{
	
	/*Cette portion de code genere un nombre aleatoire 
	 * qui permet de simuler le temps de traitement du processus
	 */
	Random rand =  new Random();
	int timeSleep;
	int minTime = 1000;
	int maxTime = 2000;
	
	public void run() {
		while(true) {
			//Setting the amount of sleeping time for the thread
			timeSleep = rand.nextInt(maxTime - minTime) + minTime;
			//Picking the PCB from the IOQueue			
			PCB pcb;
			try {
				
				pcb = OS.scheduler.pickRequestFromIOQueue().getPcb();
				OS.outlog("IOHandler -> IO Interruption from Process "+pcb.getPid()+" Treated");
				//Simulates the handling of the IORequest
				Thread.sleep(timeSleep);
				
				OS.outlog("IOHandler -> Process "+pcb.getPid()+" ready");
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
