package plateforme;


import java.util.ArrayList;

import operatingsystem.OS;
import process.PCB;
import process.Process;

public class MMU {
	
	/**
	 * Methode d'allocations et de desallocation de memoire
	 **/
	 public synchronized void allocateMemoryToProcess(Process process,int priority,int numApp) {
		 
		 int taille=  process.getSize();
		 //Diminution de la taille disponible dans la memoire
		 OS.RAM.setTailleDispo(OS.RAM.getTailleDispo()-taille);
		 
		 //Création du PCB
		 PCB PCB = new PCB(process , priority);
		 
		 //Ajouter le PCB dans les files concernées
		 OS.scheduler.addPCBToReadyQueue(PCB); 
		 OS.scheduler.addPCBToProcessQueue(PCB);
		 
		 //Ajouter le processus dans la liste des process sur le ram
		 OS.RAM.ListOfProcess.add(numApp);
		 

	 }
	 
	 public synchronized void deallocateMemoryFromProcess(Process process, int numApp) {
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$"+OS.RAM.ListOfProcess.size());
		 int taille=  process.getSize();
		 OS.RAM.setTailleDispo(OS.RAM.getTailleDispo()+taille);
		 
		 //Retirer le processus de la liste des processus de la liste
		 /* on parcourt laliste de processus deja dans la memoire et 
		  * une fois que l'on trouve une correspondance avec le processus que l'on veut tuer,
		  *  on l'emleve de la liste
		  */
		 ArrayList<Integer> list = new ArrayList<>();
			for(int i = 0; i< OS.RAM.ListOfProcess.size(); i++)
				{
					if (OS.RAM.ListOfProcess.get(i) != numApp) {

						list.add(OS.RAM.ListOfProcess.get(i));
					}
				}

			OS.RAM.ListOfProcess = new ArrayList<>();
			OS.RAM.ListOfProcess.addAll(list);
		 
	 }
	 
	

}
