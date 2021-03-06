package operatingsystem;


import process.PCB;

import java.util.Random;

import operatingsystem.OS;

public class Executor extends Thread {
	
	/*Cette portion de code genere un nombre aleatoire 
	 * qui permet de simuler le temps de traitement du processus
	 */
	Random rand =  new Random();
	int timeSleep;
	int minTime = 100;
	int maxTime = 200;
	
	/*Constructor
	 */
	public Executor(String string) {//executer un processus
		// TODO Auto-generated constructor stub
		super(string);
	}
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
		}
		while(true) {
			timeSleep = rand.nextInt(maxTime - minTime) + minTime;
			try {
				Thread.sleep(timeSleep);
			}catch(Exception e) {
				
			}
			
			try {
					//System.out.println("La ready queue a "+Scheduler.getReadyQueue().size()+" processus.");
				//on retire le pcb de la liste readyqueue
				PCB pcb = OS.scheduler.removePCBFromReadyQueue(); 
				System.out.println("ID du processus: "+ pcb.getPid());
				OS.RAM.currentPCB = pcb;
				OS.cpu.execute(pcb.getProcess(), pcb);
				
				System.out.println(this.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block			
			}
			
			try {
				Thread.sleep(timeSleep);
			}catch(Exception e) {
				
			}
		}
	}
}
