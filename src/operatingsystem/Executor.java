package operatingsystem;


import process.PCB;

import java.util.Random;

import operatingsystem.OS;

public class Executor extends Thread {
	Random rand =  new Random();
	int timeSleep;
	int minTime = 150;
	int maxTime = 300;
	
	public Executor(String string) {//executer un processus
		// TODO Auto-generated constructor stub
		super(string);
	}
	public void run() {
		while(true) {
			timeSleep = rand.nextInt(maxTime - minTime) + minTime;
			try {
				Thread.sleep(timeSleep);
			}catch(Exception e) {
				
			}
			
			try {
				System.out.println("La ready queue a "+Scheduler.getReadyQueue().size()+" processus.");
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
