package plateforme;

import process.Instruction;
import process.PCB;
import process.Process;

import java.util.ArrayList;
import java.util.Random;

import operatingsystem.OS;
public class CPU {
	
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

		public void execute(Process p, PCB pcb) {//aura une boucle for qui partira de l'adresse actuelle a l'adresse finale du process. si gon interrupt lap fe yon break
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
				this.randomValueRegisters();
				if(p.getInstructions().get(i).isInterrupted()) {
					pcb.setAddressIP((byte) IP);//on enregistre dans le pcb l'adresse de la prochaine instruction
					//la on fait appel au gestionnaire d'interruption 
					//pour l'instant on fait un somple affichage
					/*
					 * on fait automatiquement appel au main, 
					 * qui lui fait appel au scheduler pour ajouter un autre programme a l'execution
					 * et aussi mettre le pcb dans la file waiting
					 */
					
				}
			}
			
			
		}

	
// reflechir encore plus sur le fonctionnement de l'executeru afin que le thread se mette en pause pour attendre le cpu
}
