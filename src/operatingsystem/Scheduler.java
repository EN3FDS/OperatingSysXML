package operatingsystem;


import java.util.ArrayList;
import java.util.PriorityQueue;

import interruption.IORequest;
import process.PCB;


public class Scheduler {
	private static PriorityQueue<PCB> readyQueue = new PriorityQueue<PCB>();
	
	//Queue pour les requete IO
	public static PriorityQueue<IORequest> ioRequestQueue = new PriorityQueue<IORequest>();
	/*
	 * le processsQueue permet aussi de faire en sorte que le generate
	 */
	private static ArrayList<PCB> processQueue = new ArrayList<>();
	
	//Getters and Setters
	public static ArrayList<PCB> getProcessQueue() {
		return processQueue;
	}
	public static void setProcessQueue(ArrayList<PCB> processQueue) {
		Scheduler.processQueue = processQueue;
	}
	public static PriorityQueue<PCB> getReadyQueue() {
		return readyQueue;
	}
	
	//ajouter un PCB dans la file
	public synchronized void addPCBToReadyQueue(PCB pcb) {
		readyQueue.add(pcb);
	}
	
	public synchronized void addPCBToProcessQueue(PCB pcb) {
		processQueue.add(pcb);
	}
	
	//enlever un PCB de la queue
	
	public synchronized PCB removePCBFromReadyQueue() {
		return readyQueue.poll();
	}
	// Enlever un element de la liste ProcessQUEUE
	
	public synchronized void removePCBFromProcessQueue(PCB pcb) {
		ArrayList<PCB> list = new ArrayList<>();
		processQueue.forEach(data->{
			if (data.getPid() != pcb.getPid()){
				list.add(data);
			}
		});
		processQueue = new ArrayList<>();
		processQueue.addAll(list);
	}
	
	//Ajouter une requete a la liste
	public synchronized void addRequestToIOQueue(IORequest ioRequest){
		ioRequestQueue.add(ioRequest);
	}
	
	//Retirer une requete de la liste
	public synchronized IORequest pickRequestFromIOQueue() {
		return ioRequestQueue.poll();
	}
}
