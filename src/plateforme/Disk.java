package plateforme;
import java.util.ArrayList;
import java.util.Random;

import operatingsystem.OS;
import process.Instruction;
import process.AppFile;
import process.File;

public class Disk {
	private static final int size = 10*1024*1024; // GB convertit en KB

	private static int tailleDispo = size;
	
	public static ArrayList<File> fileOnDisk = new ArrayList<>();
	
	
	
	public int getTailleDispo() {
		return tailleDispo;
	}
	public static void setTailleDispo(int taille) {
		tailleDispo = taille;
	}
	public int getSize() {
		return size;
	}
	
	public static void write(AppFile file) {
			tailleDispo = tailleDispo - file.getInstructions().size();
	}
	
	/*
	 * Load The OS to the Disk
	 */
	public void loadOS() {
		ArrayList<Instruction> instructions = new ArrayList<>();
		
		Instruction instruction;
		
		byte i;
		// Load OperatingSys
		//creation des instructions de l'app
		for (i = 0; i < 15000 ; i++ ) {
			instruction = new Instruction(false,i);
			instructions.add(instruction);
		}
		AppFile OperatingSys = new AppFile(OS.rootFile,"OperatingSys",1000,instructions);
		write(OperatingSys);
		tailleDispo = tailleDispo - instructions.size();
		
	}

	/*
	 * Load the applications to the disk 
	 * this method creates the app and its instruction.
	 * a random generator tells which istruction provides interruption.
	 * 
	 */
	public void loadAppOnDisk() {
		ArrayList<Instruction> instructions = new ArrayList<>();
		Random rand = new Random();
		Instruction instruction;
		
		byte i;
		// Load MuzixPlayer
		//creation des instructions de l'app
		System.out.println("Loading Player");
		for (i = 0; i < 15 ; i++ ) {
			instruction = new Instruction(rand.nextBoolean(),i);
			instructions.add(instruction);
		}
		AppFile MusicPlayer = new AppFile(OS.userFile,"MusicPlayer",10,instructions);
		write(MusicPlayer);
		tailleDispo = tailleDispo - instructions.size();
		fileOnDisk.add(MusicPlayer);
		
		// Load Tetris
		//creation des instructions de l'app
		System.out.println("Loading Tetris");
		instructions = new ArrayList<>(); //reinitialisation de l'arraylist des instructions
		for (i = 0; i < 20 ; i++ ) {
			instruction = new Instruction(rand.nextBoolean(),i);
			instructions.add(instruction);
		}
		AppFile Tetris = new AppFile(OS.userFile,"Tetris",10,instructions);
		write(Tetris);
		tailleDispo = tailleDispo - instructions.size();
		fileOnDisk.add(Tetris);
		
		// Load Calculator
		//creation des instructions de l'app
		System.out.println("Loading Calculator");
		instructions = new ArrayList<>(); //reinitialisation de l'ArrayList des instructions
		for (i = 0; i < 11 ; i++ ) {
			instruction = new Instruction(rand.nextBoolean(),i);
			instructions.add(instruction);
		}
		AppFile Calculator = new AppFile(OS.userFile,"Calculator",10,instructions);
		write(Calculator);
		tailleDispo = tailleDispo - instructions.size();
		fileOnDisk.add(Calculator);
		
		// Load Writer
		//creation des instructions de l'app
		System.out.println("Loading Writer");
		instructions = new ArrayList<>(); //reinitialisation de l'ArrayList des instructions
		for (i = 0; i < 50 ; i++ ) {
			instruction = new Instruction(rand.nextBoolean(),i);
			instructions.add(instruction);
		}
		AppFile Writer = new AppFile(OS.userFile,"Writer",10,instructions);
		write(Writer);
		tailleDispo = tailleDispo - instructions.size();
		fileOnDisk.add(Writer);

		// Load FIFA
		//creation des instructions de l'app
		System.out.println("Loading FIFA");
		instructions = new ArrayList<>(); //reinitialisation de l'ArrayList des instructions
		for (i = 0; i < 70; i++ ) {
			instruction = new Instruction(rand.nextBoolean(),i);
			instructions.add(instruction);
		}
		AppFile FIFA = new AppFile(OS.userFile,"FIFA",10,instructions);
		write(FIFA);
		tailleDispo = tailleDispo - instructions.size();
		fileOnDisk.add(FIFA);
		
		// Load PhotoViewer
		//creation des instructions de l'app
		System.out.println("Loading PhotoViewer");
		instructions = new ArrayList<>(); //reinitialisation de l'arraylist des instructions
		for (i = 0; i < 40 ; i++ ) {
			instruction = new Instruction(rand.nextBoolean(),i);
			instructions.add(instruction);
		}
		AppFile PhotoViewer = new AppFile(OS.userFile,"PhotoViewer",10,instructions);
		write(PhotoViewer);
		tailleDispo = tailleDispo - instructions.size();
		fileOnDisk.add(PhotoViewer);
		
		// Load MovieMaker
		//creation des instructions de l'app
		System.out.println("Loading MovieMaker");
		instructions = new ArrayList<>(); //reinitialisation de l'arraylist des instructions
		for (i = 0; i < 55 ; i++ ) {
			instruction = new Instruction(rand.nextBoolean(),i);
			instructions.add(instruction);
		}
		AppFile MovieMaker = new AppFile(OS.userFile,"MovieMaker",10,instructions);
		write(MovieMaker);
		tailleDispo = tailleDispo - instructions.size();
		fileOnDisk.add(MovieMaker);
		
	}
	
}
