package plateforme;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import operatingsystem.OS;
import process.PCB;
import process.Process;

public class MMU {
	
	/**
	 * Methode d'allocations et de desallocation de memoire
	 **/
	 public synchronized void allocateMemoryToProcess(Process process, int priority, int numApp) {
		 
		 int taille=  process.getSize();
		 //Diminution de la taille disponible dans la memoire
		 OS.RAM.setTailleDispo(OS.RAM.getTailleDispo() - taille);
		 
		 //Cr�ation du PCB
		 PCB PCB = new PCB(process , priority);
		 
		 //Ajouter le PCB dans les files concern�es		 
		 OS.scheduler.addPCBToReadyQueue(PCB); 
		 OS.scheduler.addPCBToProcessQueue(PCB);
		 OS.outlog("Process "+PCB.getPid()+" ready");
		 //Ajouter le processus dans la liste des process sur le ram
		 OS.RAM.ListOfProcess.add(numApp);


	 }
	 
	 //pour desallouer definitivement de la memoire
	 public synchronized void deallocateMemoryFromProcess(Process process, int numApp) {
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
			
			OS.outlog("Process with ID: " + process.getId() + " Removed From Memory");
		 
	 }
	 
	 //Pour desallouer de la memoire quand on fait du swapping
	private void deallocateMemoryFromTailProcess(PCB pcb) {
		 int taille=  pcb.getProcess().getSize();
		 OS.RAM.setTailleDispo(OS.RAM.getTailleDispo()+taille);
		 //we don't remove the process from the list of the ram because it is still in the execution cycle
		 
	 }
	 

	private void allocateMemoryToTailProcess(PCB pcb) {
		 int taille=  pcb.getProcess().getSize();
		 OS.RAM.setTailleDispo(OS.RAM.getTailleDispo()-taille);
	 }
	 
	 public synchronized void makeSwappingNewProcess(Process p) {
		 ///search for the less prioritary pcb in the ready queue
		 PCB lastPCB;
		 PCB pcb = new PCB(p , p.getPriority());
		try {
			
			lastPCB = OS.scheduler.getTailPCBFromReadyQueue();
			System.out.println("Getting Tail ID= "+ lastPCB.getPid());
			 if(lastPCB != null) {
				 System.out.println("SWAPPING");
				 pcb.setDateCreated(Date.from(Instant.now()));
				 OS.scheduler.addPCBToReadyQueue(pcb);
				 //deallocate memory from lastPCB
				 deallocateMemoryFromTailProcess(lastPCB);
				 
				 //Allocate Memory to newPCB
				 allocateMemoryToProcess(pcb.getProcess(),pcb.getPriority(),pcb.getProcess().getNumApp());
				 
				 //Put Last PCB in the SwapQueue
				 OS.scheduler.addPCBToSwapQueue(lastPCB);
				 OS.outlog("MMU -> Swap Process "+lastPCB.getPid()+" to process "+pcb.getPid());
				 System.out.println("MMU -> Swap Process "+lastPCB.getPid()+" to process "+pcb.getPid());
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Mechant");
			OS.outlog(e.getMessage());
			e.printStackTrace();
		}		 			 		 
	 }
	 
	 public synchronized void makeSwappingOldProcess(PCB pcb) {
		 ///search for the less prioritary pcb in the ready queue
		 PCB lastPCB;
		try {
			lastPCB = OS.scheduler.getTailPCBFromReadyQueue();
			 if(lastPCB != null) {		
				 pcb.setDateCreated(Date.from(Instant.now()));
				 OS.scheduler.addPCBToReadyQueue(pcb);
				 //deallocate memory from lastPCB
				 deallocateMemoryFromTailProcess(lastPCB);
				 
				 //Allocate Memory to newPCB
				 allocateMemoryToTailProcess(pcb);
				 
				 //Put Last PCB in the SwapQueue
				 OS.scheduler.addPCBToSwapQueue(lastPCB);
				 OS.outlog("MMU -> Swap Process "+lastPCB.getPid()+" to process "+pcb.getPid());
				 System.out.println("MMU -> Old Swap Process "+lastPCB.getPid()+" to process "+pcb.getPid());
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			OS.outlog(e.getMessage());
		}		 			 		 
	 }

}
