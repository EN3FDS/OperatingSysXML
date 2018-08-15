package plateforme;


import java.util.ArrayList;

import operatingsystem.OS;
import process.PCB;
import process.Process;

public class MMU {
	
	/**
	 * Methode d'allocations et de desallocation de memoire
	 **/
	 public synchronized void allocateMemoryToProcess(Process process,int priority) {
		 
		 int taille=  process.getSize();
		 //Diminution de la taille disponible dans la memoire
		 OS.RAM.setTailleDispo(OS.RAM.getTailleDispo()-taille);
		 
		 //Création du PCB
		 PCB PCB = new PCB(process , priority);
		 
		 //Ajouter le PCB dans les files concernées
		 OS.scheduler.addPCBToReadyQueue(PCB); 
		 OS.scheduler.addPCBToProcessQueue(PCB);
		 
		 //Ajouter le processus dans la liste des process sur le ram
		 OS.RAM.ListOfProcess.add(process);
		 

	 }
	 
	 public synchronized void deallocateMemoryFromProcess(Process process) {
		 int taille=  process.getSize();
		 OS.RAM.setTailleDispo(OS.RAM.getTailleDispo()+taille);
		 
		 //Retirer le processus de la liste des processus de la liste
		 ArrayList<Process> list = new ArrayList<>();
			OS.RAM.ListOfProcess.forEach(data->{
				if (data.getId() != process.getId()){
					list.add(data);
				}
			});
			OS.RAM.ListOfProcess = new ArrayList<>();
			OS.RAM.ListOfProcess.addAll(list);
		 
	 }
	 
	

}
