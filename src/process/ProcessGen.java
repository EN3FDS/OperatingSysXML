package process;

//import java.util.ArrayList;
import java.util.Random;

import operatingsystem.OS;
import operatingsystem.Scheduler;

public class ProcessGen extends Thread {
	//Setting the timeSleep for the thread
	Random random =  new Random();
	int timeSleep;
	int minTime = 50;
	int maxTime = 100;
	
	private Random rand= new Random();
	int num;
	//constructor
	public ProcessGen(String s) {
		super(s);
	}
	
	
	public void run() {
		while(true) {	
			timeSleep = random.nextInt(maxTime - minTime) + minTime;
			if (Scheduler.getReadyQueue().size() == 5) {
				try {
					Thread.sleep(timeSleep);
				}catch(Exception e) {
					
				}
				try {
					System.out.println("###########queue pleine###########");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (Scheduler.getReadyQueue().size() < 5){
				try {
					//Generation du processus
					OS.interruption.makeInterruption(rand.nextInt(5), 1);
					
				} catch (/*Interrupted*/Exception e) {
					System.out.println("Proces Not Generated");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(timeSleep);
				}catch(Exception e) {
					
				}
			}

		}
		
	}
	

}
	
/*	private static void Verification(int id,Process p) {		
		int i=0;
		ListOfProcess = Main.RAM.getListOfProcess();
		System.out.println("Probleme:"+p.toString());
		do {
			for( i=0;i<ListOfProcess.size();i++) {
				
				if( id == ListOfProcess.get(i).getId()) {
					
					break;
				}
			}
			if( id == ListOfProcess.get(i).getId()) {
				break;
			}
			else {
				CreateAndAddPCB(p);
				break;
			}
			
		}
		while(true);
	}
	
*/



