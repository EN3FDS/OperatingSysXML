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
	private static PriorityQueue<PCB> swapQueue = new PriorityQueue<PCB>();
	
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
	
	//Add PCB to Swapping QUeue
	public synchronized void addPCBToSwapQueue(PCB pcb) {
		lock.lock();
		OS.outlog("Scheduler -> Add PCB of Process "+ pcb.getPid()+" to SwapQueue");
		try {
			swapQueue.add(pcb);
		}
		finally {
			lock.unlock();
		}		
	}
	
	//RemovePCB from swap queue
	public synchronized PCB pickPCBFromSwapQueue() {
		lock.lock();
		OS.outlog("Scheduler -> Picking PCB from SwapQueue");
		try {
			return swapQueue.poll();
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
	
	public synchronized PCB getTailPCBFromReadyQueue() {
		// COpy the Ready queue to a temporary ready queue
		PriorityQueue<PCB> readyQueueTemp = new PriorityQueue<PCB>();
		 
		//PCB pp =readyQueue.poll();
		
		// We take all the PCB except the last one
		while(readyQueue.size()>1)
		{
			readyQueueTemp.add(readyQueue.poll());
			
		}
		PCB p = readyQueue.poll();
		while(readyQueueTemp.size()>0)
		{
			readyQueue.add(readyQueueTemp.poll());
			
		}
		System.out.println("!@#$%^&*Getting the tail Process ID (*&^%$#@!" + p.getPid());
		//We return the Last PCB
		return p;
	}
}
