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
			OS.outlog("SystemCall -> Killing Process "+OS.RAM.currentPCB.getPid());
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
					//On fait la demande de swapping, si cela ne fonctionne pas on abandonne la fonction					
					System.out.println("@@@@@@@@@@@@Memoire pleine@@@@@@@@@@@@@@@");
					
					try {
						
						OS.outlog("SystemCall -> Memory Full. Swapping");
						Process p = new Process(OS.IDProcess, appFile.getName(), numApp, appFile.getInstructions(),appFile.getPriority());
						OS.mmu.makeSwappingNewProcess(p);
						
					}catch(Exception e) {
						System.out.println("SWAPPINGNew Failed");
						OS.outlog("OS -> Cannot Make Swapping");
						OS.outlog(e.getMessage());
						return;
					}
					
					
				}
				
				Process p = new Process(OS.IDProcess, appFile.getName(), numApp, appFile.getInstructions());
				OS.outlog("SystemCall -> Process Created. ID: "+p.getId()+"\tProgram name: "+appFile.getName());
				//augmenter de 1 la variable qui cr�e les identifiants pour les processus
				OS.IDProcess++; 
				
				//Allouer la memoire au processus
				OS.mmu.allocateMemoryToProcess(p,appFile.getPriority(),numApp);	
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Processus Non Cr��");
				OS.outlog("SystemCall -> Cannot create Process");
				OS.outlog(e.getMessage());
			}		

			break;
		default:
			//do nothing
		}
	}
}
