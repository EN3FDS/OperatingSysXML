package operatingsystem;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interruption.IORequest;
import process.PCB;



public class Scheduler {
	public static Lock lock = new ReentrantLock();
	
	private static PriorityQueue<PCB> readyQueue = new PriorityQueue<PCB>();
	
	//Queue pour les requete IO
	public static PriorityQueue<IORequest> ioRequestQueue = new PriorityQueue<IORequest>();
	public static PriorityQueue<IORequest> getIoRequestQueue() {
		return ioRequestQueue;
	}

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
		lock.lock();
		OS.outlog("Scheduler -> Add PCB of Process "+ pcb.getPid()+" to ReadyQueue");
		try {
			readyQueue.add(pcb);
		}
		finally {
			lock.unlock();
		}
		
	}
	
	public synchronized void addPCBToProcessQueue(PCB pcb) {
		lock.lock();
		OS.outlog("Scheduler -> Add PCB of Process "+ pcb.getPid()+" to ProcessQueue");
		try {
			processQueue.add(pcb);
		}
		finally {
			lock.unlock();
		}
		
	}
	
	//enlever un PCB de la queue
	
	public synchronized PCB removePCBFromReadyQueue() {
		lock.lock();
		OS.outlog("Scheduler -> Picking PCB from ReadyQueue");
		try {
			return readyQueue.poll();
		}
		finally {
			lock.unlock();
		}
		
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
		OS.outlog("Scheduler -> Remove PCB of Process "+ pcb.getPid()+" from ProcessQueue");
	}
	
	//Ajouter une requete a la liste
	public synchronized void addRequestToIOQueue(IORequest ioRequest){
		lock.lock();
		try {
			OS.outlog("Scheduler -> Add Request of Process "+ ioRequest.getPcb().getPid() +" to IOQueue");
			ioRequestQueue.add(ioRequest);
		}
		finally {
			lock.unlock();
		}		
	}
	
	//Retirer une requete de la liste
	public synchronized IORequest pickRequestFromIOQueue() {
		lock.lock();
		try {
			OS.outlog("Scheduler -> Picking Request from IOQueue");
			return ioRequestQueue.poll();
		}
		finally {
			lock.unlock();
		}		
	}
}
