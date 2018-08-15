package operatingsystem;

import java.util.ArrayList;
import process.PCB;
import process.Process;
import operatingsystem.OS;

public class Executor extends Thread {
private static ArrayList<Process> ListOfProcess = new ArrayList<>();
	
	public Executor(String string) {//executer un processus
		// TODO Auto-generated constructor stub
		super(string);
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				//on retire le pcb de la liste readyqueue
				PCB pcb = OS.scheduler.removePCBFromReadyQueue(); 
				System.out.println("ID du processus: "+ pcb.getPid());
				OS.RAM.currentPCB = pcb;
				
				int i;
				ListOfProcess=OS.RAM.getListOfProcess(); //on prend la liste des processus deja sur la memoire
				for(i=0;i<ListOfProcess.size();i++) {
					if (pcb.getPid()==ListOfProcess.get(i).getId()) { //prend le processus correspondant au PCB choisi
						//simulation d'execution a cet espace on fait appel au cpu
						aff(ListOfProcess.get(i).toString());	
						
						//Boucle permettant de parcourir les instructions du processus
						for (int j = pcb.getAddressIP(); j <= pcb.getFinalAddress(); j++) {
							pcb.setAddressIP((byte) (pcb.getAddressIP() + 1));
							// Si on trouve une interruption
							if (pcb.getProcess().getInstructions().get(j).isInterrupted()) {
								//faire línterruption
							}
							
						
							// Quand on arrive a la fin du processus
							// On génère le systemcall de fin de programme
							if (pcb.getAddressIP() == pcb.getFinalAddress()) {
								System.out.println("****************FIn de ce processus*********************");
								OS.systemCall.makeSystemCall(10, 0);
								break;
							}
						}
						break;
					}
						
				}
				System.out.println(this.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("exception generated");
				e.printStackTrace();
				
			}
			
		}
	}
	private static void aff(String s) {
		System.out.println(s);
	}

}
