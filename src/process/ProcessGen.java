package process;

//import java.util.ArrayList;
import java.util.Random;

import operatingsystem.OS;
import process.Process;

public class ProcessGen extends Thread {
	
//	private static ArrayList<Process> ListOfProcess = new ArrayList<>();
	private Random rand= new Random();
	int num;
	//constructor
	public ProcessGen(String s) {
		super(s);
	}
	
	
	public void run() {
		while(true) {		
			if (OS.scheduler.getReadyQueue().size() == 5) {
				try {
					Thread.sleep(20);
				}catch(Exception e) {
					
				}
				try {
					System.out.println("###########queue pleine###########");
					System.out.println("La queue est pleine avec : "+ OS.scheduler.getProcessQueue().size());
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (OS.scheduler.getReadyQueue().size() < 5){
				try {
					Thread.sleep(100);
					//Generation du processus
					OS.interruption.makeInterruption(rand.nextInt(5), 1);
					
				} catch (/*Interrupted*/Exception e) {
					System.out.println("Proces Not Generated");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(20);
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



