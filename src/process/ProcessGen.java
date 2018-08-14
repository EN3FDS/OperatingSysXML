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
	
	public int Genarate() {
		return  rand.nextInt(5);
	}
	
 	public void witch_process(int a) {
		switch (a) {
		case 0:
			//AppelSystem
			OS.systemCall.makeSystemCall(a, 1);
			break;
		case 1:
			//AppelSystem 
			OS.systemCall.makeSystemCall(a, 1);
			break;
		case 2:
			//AppelSystem
			OS.systemCall.makeSystemCall(a, 1);
			break;
		case 3:
			//AppelSystem
			OS.systemCall.makeSystemCall(a, 1);
			break;
		case 4:
			//AppelSystem
			OS.systemCall.makeSystemCall(a, 1);
		}
		
	}
	
	public static void aff(String s) {
		System.out.println(s);
	}
	
	public void run() {
		while(true) {		
			if (OS.scheduler.getProcessQueue().size() == 5) {
				try {
					System.out.println("###########queue pleine###########");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (OS.scheduler.getProcessQueue().size() < 5){
				try {
					//Thread.sleep(1000);
					int k= Genarate();
					witch_process(k);
				} catch (/*Interrupted*/Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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



