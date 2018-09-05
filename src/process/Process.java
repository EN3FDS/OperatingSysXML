package process;

import java.util.ArrayList;

public class Process {
	private int id;
	private String nom;
	private int size;
	private int numApp;
	private int priority;
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}



	private ArrayList<Instruction> instructions= new ArrayList<>();
	//Constructor
	public Process(int i, String s,int app,ArrayList<Instruction> instructions) {
		this.id = i;
		this.nom = s;
		this.instructions = instructions;
		numApp = app;
		this.size = instructions.size();
	}
	public Process(int i, String s,int app,ArrayList<Instruction> instructions,int priority) {
		this.id = i;
		this.nom = s;
		this.instructions = instructions;
		numApp = app;
		this.size = instructions.size();
		this.priority=priority;
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
