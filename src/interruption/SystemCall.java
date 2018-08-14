package interruption;

import operatingsystem.OS;
import plateforme.Disk;
import process.AppFile;
import process.Process;

public class SystemCall {


	public SystemCall() {
		// TODO Auto-generated constructor stub
	}
/*	
	public SystemCall(String name, short address) {
		this.name=name;
		this.address=address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAddress() {
		return address;
	}

	public void setAddress(short address) {
		this.address = address;
	}

	public int getSIZE() {
		return SIZE;
	}
*/
	public void makeSystemCall(int numApp, int i) {
		switch (i){
		case 0: 
			//Kill a Process			
			OS.mmu.deallocateMemoryFromProcess(OS.RAM.currentPCB.getProcess());	
			OS.scheduler.removePCBFromProcessQueue(OS.RAM.currentPCB);
			break;
			
		case 1: 
			try {
				//System.out.println("SyctemCall generated");
				//generate process from application on disk
				AppFile appFile = (AppFile) Disk.fileOnDisk.get(numApp);
				Process p = new Process(OS.IDProcess,appFile.getName(),appFile.getInstructions());
				
				//augmenter de 1 la variable qui crée les identifiants pour les processus
				OS.IDProcess++; 
				
				//Allouer la memoire au processus
				OS.mmu.allocateMemoryToProcess(p,appFile.getPriority());		
				
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
