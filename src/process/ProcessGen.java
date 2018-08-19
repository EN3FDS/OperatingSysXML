package process;

//import java.util.ArrayList;
import java.util.Random;

import operatingsystem.OS;
import operatingsystem.Scheduler;

public class ProcessGen extends Thread {
	
	/*Cette portion de code genere un nombre aleatoire 
	 * qui permet de simuler le temps de traitement du processus
	 */
	Random random =  new Random();
	int timeSleep;
	int minTime = 50;
	int maxTime = 100;
	
	private Random rand= new Random();
	int num;
	//constructor
	public ProcessGen(String s) {
		super(s);
	}
	
	
	public void run() {
		while(true) {	
			timeSleep = random.nextInt(maxTime - minTime) + minTime;
			if (Scheduler.getReadyQueue().size() == 5) {
				try {
					Thread.sleep(timeSleep);
				}catch(Exception e) {
					
				}
				try {
					System.out.println("###########queue pleine###########");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (Scheduler.getReadyQueue().size() < 5){
				try {
					//Generation du processus
					OS.interruption.makeInterruption(rand.nextInt(5), 1);
					
				} catch (/*Interrupted*/Exception e) {
					System.out.println("Proces Not Generated");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(timeSleep);
				}catch(Exception e) {
					
				}
			}

		}
		
	}
	

}
	



