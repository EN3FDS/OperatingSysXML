package process;


import java.time.Instant;
import java.util.Date;


public class PCB implements Comparable<PCB> {
	//Les variables
	
	private int pid;
	private String status;
	private int priority;
	private Date DateCreated;
	private byte addressIP = 0;// recevra l'adresse de la prochaine instruction a executer 
	private byte finalAddress;//recevra l'adresse de la derniere instruction du programme 
	private Process process;
	

	//Create Constructors
	public PCB (Process p, int priority) {
		this.pid = p.getId();
		this.priority = priority;
		this.DateCreated = Date.from(Instant.now());
		this.process = p;
		this.finalAddress =(byte) ((byte) p.getInstructions().size()-1);
	}
	
	//getter et setter
	
	public Process getProcess() {
		return process;
	}
	public byte getAddressIP() { 
		return addressIP;
	}

	public void setAddressIP(byte addressIP) {
		this.addressIP = addressIP;
	}

	public byte getFinalAddress() {
		return finalAddress;
	}
	
	public Date getDateCreated() {
		return DateCreated;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPid() {
		return pid;
	}

	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	/*
	 * Compare PCB
	 */
	public int compareTo(PCB pcb2) {
		if (this.getPriority() != pcb2.getPriority()) {
			return pcb2.getPriority() - this.getPriority();
		} else {
			return pcb2.getDateCreated().compareTo(this.getDateCreated());
		}

	}
	
}
