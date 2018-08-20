package plateforme;


import process.PCB;
import process.Process;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import operatingsystem.OS;
public class CPU extends Thread{
	Lock lock = new ReentrantLock();
	Condition ExecutorCondition = lock.newCondition();
	
		private int frequency;
		private int AX;
		private int BX;
		private int CX;
		private int DX;

		private int SI;
		private int DI;
		private int SP;
		private int BP;

		private int IP;
		private int CS;
		private int DS;
		private int SS;
		private int ES;

		private Random rand = new Random();

		private int flagsRegister;

		public CPU() {
			this.randomValueRegisters();
		}


		
		public void randomValueRegisters() {
			AX = rand.nextInt();
			BX = rand.nextInt();
			CX = rand.nextInt();
			DX = rand.nextInt();

			SI = rand.nextInt();
			DI = rand.nextInt();
			SP = rand.nextInt();
			BP = rand.nextInt();

			//IP = rand.nextInt();
			CS = rand.nextInt();
			DS = rand.nextInt();
			SS = rand.nextInt();
			ES = rand.nextInt();

			flagsRegister = rand.nextInt();
		}

		/**cette methode est utilisee par l'executeur
		 * Elle permet d'executer les instructions du processus un par un. 
		 *  Arrive a une instruction generant une onterruption, elle fait 
		 *  appel au gestionnaire d'inturreption.
		 * @param p
		 * @param pcb
		 */
		public synchronized void execute(Process p, PCB pcb) {
			lock.lock();
			OS.RAM.currentPCB = pcb;
			byte i;
			try {

				for (i=pcb.getAddressIP();i<=pcb.getFinalAddress();i++) {
					IP= i+1;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
					System.out.println("________ExecutingProcess_______-_ ID =" +pcb.getPid());
					// Quand on arrive a la fin du processus
					// On génère le systemcall de fin de programme
					if (pcb.getAddressIP() == pcb.getFinalAddress()) {
						System.out.println("########Fin de ce processus########");
						OS.interruption.makeInterruption(pcb.getProcess().getNumApp(), 0);
				lock.unlock();
						return;
					}
					
					//mise a jour du IP dans le pcb
					pcb.setAddressIP((byte) IP);
					this.randomValueRegisters();
					if(p.getInstructions().get(i).isInterrupted()) {
						System.out.println("\n\t\tInterruption générée");
						OS.interruption.makeInterruption(11,11);
						lock.unlock();
						return;
					}
				}
			}
			finally {
				lock.unlock();
			}
																												
		}
}
