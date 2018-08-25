/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interruption;

import process.PCB;

/**
 *
 * @author SOLITIONS PC_01
 */
public class IORequest implements Comparable<IORequest> {
 
    
    private PCB pcb;
	private int num_Int;
	
	public IORequest(PCB pcb, int numInt) {
		super();
		this.pcb = pcb;
		this.num_Int = numInt;
	}

	public synchronized PCB getPcb() {
		return pcb;
	}

	public synchronized int getNumInt() {
		return num_Int;
	}

	@Override
	public int compareTo(IORequest arg0) {
		// TODO Auto-generated method stub
		return pcb.compareTo(arg0.getPcb());
	}
    
    
}
