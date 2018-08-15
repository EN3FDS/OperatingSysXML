package process;
import java.util.Random;

public class Instruction {
	Random rand = new Random();
	private boolean interrupted; //dit si l'instruction genere une interruption ou pas
	private byte address; //Adresse logique de l'instruction en question
	private short typeOfInterruption;
	//Constructor
	public Instruction(boolean interrupted, byte address) {
		super();
		this.interrupted = interrupted;
		this.address = address;
	}
	
	/*
	 * Getters 
	 */
	public boolean isInterrupted() { 
		return interrupted;
	}
	public byte getAddress() {
		return address;
	}
	
	/*Dis quel type dínterruption est generee
	dependemment dy type, il sera géré de tel ou tel façon.*/
	public short getTypeOfInterruption() { 
		typeOfInterruption = (short) rand.nextInt(5);
		return typeOfInterruption;
	}
	public String toString() {
		return "(Interrupted: " + interrupted + ", Address: " + address + ")";
	}

}
