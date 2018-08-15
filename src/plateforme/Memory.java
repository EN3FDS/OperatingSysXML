package plateforme;

import java.util.ArrayList;

import process.PCB;
import process.Process;

public class Memory {
	//definition de la taille de la memoire
	
	private final static int taille = 1024*1024; // taille en MB
	public ArrayList<Process> ListOfProcess = new ArrayList<>();
	private static int NbreDePages = taille*1024/4; //la taille des pages est de 4k
	private int tailleDispo=taille;
	
	/**
	 * Current PCB this can be used to keep in touch with the process that is running
	 */ 
	public PCB currentPCB;

	//getter
	public int getTailleDispo() {
		return tailleDispo;
	}

	public void setTailleDispo(int tailleDispo) {
		this.tailleDispo = tailleDispo;
	}
	
	public static int getNbreDePages() {
		return NbreDePages;
	}

	/*
	 * Getter et Setter de la liste de Process
	 */


	public ArrayList<Process> getListOfProcess() {
		return ListOfProcess;
	}
	
	/**
	 * @return the taille
	 */
	public int getTaille() {
		return taille;
	}
	

}
