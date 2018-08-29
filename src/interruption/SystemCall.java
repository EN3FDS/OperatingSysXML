package interruption;

import operatingsystem.OS;
import plateforme.Disk;
import process.AppFile;
import process.Process;

public class SystemCall {


	public SystemCall() {
		// TODO Auto-generated constructor stub
	}

	public void makeSystemCall(int numApp, int i) {
		switch (i){
		case 0: 
			//Kill a Process	
			System.out.println("Killing Process %%%%%%%%%%%");
			OS.mmu.deallocateMemoryFromProcess(OS.RAM.currentPCB.getProcess(),numApp);	
			OS.scheduler.removePCBFromProcessQueue(OS.RAM.currentPCB);
			break;
			
		case 1: 
			try {
				//System.out.println("SyctemCall generated");
				//generate process from application on disk
				
				// checking if the program is'nt already in memory,
				//if this is the case, then we just stop the method from running
				for(int j = 0; j<OS.RAM.ListOfProcess.size(); j++) {
					if(OS.RAM.ListOfProcess.get(j)== numApp) {
						return;
					}
				}
				AppFile appFile = (AppFile) Disk.fileOnDisk.get(numApp);
				if(OS.RAM.getTailleDispo() <= appFile.getInstructions().size()) {
					//On fait u return, ce qui signifie qui le processus n'est pas créé
					//On va aussi mettre un log ici pour indiquer l'abandon du processus
					System.out.println("@@@@@@@@@@@@Memoire pleine@@@@@@@@@@@@@@@");
					
					return;
				}
				
				Process p = new Process(OS.IDProcess, appFile.getName(), numApp, appFile.getInstructions());
				
				//augmenter de 1 la variable qui crée les identifiants pour les processus
				OS.IDProcess++; 
				
				//Allouer la memoire au processus
				OS.mmu.allocateMemoryToProcess(p,appFile.getPriority(),numApp);		
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Processus Non Créé");
				e.printStackTrace();
			}		

			break;
		default:
			//do nothing
		}
	}
}
