package plateforme;


import process.PCB;
import process.Process;

import java.util.Random;

import operatingsystem.OS;
public class CPU extends Thread{
	
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

		public synchronized void execute(Process p, PCB pcb) {//aura une boucle for qui partira de l'adresse actuelle a l'adresse finale du process. si gon interrupt lap fe yon break
			//epitou, nap fe IP a pran valeur i+1 ki reprezante next adress d'execution. 
			//le gen interrupt, lap rele main nan kap rele scheduler a kap regle kose li byen pwop
			// Execute and return the interrupt number
			/*
			 * cette methode devra recevoir et le pcb et le process
			 * le pcb pour savoir l'adresse actuelle permettant de commencer l'execution 
			 * process pour effectivement faire cette execution
			 */
			byte i;
			for (i=pcb.getAddressIP();i<=pcb.getFinalAddress();i++) {
				IP= i+1;
				
				// Quand on arrive a la fin du processus
				// On génère le systemcall de fin de programme
				if (pcb.getAddressIP() == pcb.getFinalAddress()) {
					System.out.println("****************Fin de ce processus*********************");
					OS.interruption.makeInterruption(10, 0);
					notifyAll();

					
					break;
				}
				
				//mise a jour du IP dans le pcb
				pcb.setAddressIP((byte) IP);
				this.randomValueRegisters();
				if(p.getInstructions().get(i).isInterrupted()) {
					System.out.println("\n\t\tInterruption générée");
					OS.interruption.makeInterruption(11,11);
					notifyAll();
				}								
			}						
		}
}
