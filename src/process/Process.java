package process;

import java.util.ArrayList;

public class Process {
	private int id;
	private String nom;
	private int size;
	private int numApp;
	
	private ArrayList<Instruction> instructions= new ArrayList<>();
	//Constructor
	public Process(int i, String s,int app,ArrayList<Instruction> instructions) {
		this.id = i;
		this.nom = s;
		this.instructions = instructions;
		numApp = app;
	}
	public ArrayList<Instruction> getInstructions() {
		return instructions;
	}
	public void setInstructions(ArrayList<Instruction> instructions) {
		this.instructions = instructions;
	}
	//GEtters
	public int getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	public int getSize() {
		return size;
	}
	public int getNumApp() {
		return numApp;
	}


	
	public String toString() {
		return "ProcessID: "+id+" ProcessName: "+nom+" ProcessSize: "+size;
	}

}
