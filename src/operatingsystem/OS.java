/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import interruption.Interruption;
import interruption.SystemCall;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import plateforme.CPU;
import plateforme.Disk;
import plateforme.MMU;
import plateforme.Memory;
import process.Fichier;
import process.Process;
import process.ProcessGen;
import view.Graphic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import interruption.IOHandler;

/**
 *
 * @author Nahomie Labont�
 */
public class OS extends Application {
	// fichier root
		public static Fichier rootFile = new Fichier(null, "root", true);
		// fichier user
		public static Fichier userFile = new Fichier(rootFile, "user", true);

		
		public static MMU mmu = new MMU();
		public static Memory RAM = new Memory();
		public static Scheduler scheduler= new Scheduler();
		public static ProcessGen generateur;
		public static Disk disk = new Disk();
		public static CPU cpu = new CPU();
		public static Executor execute;
		public static SystemCall systemCall = new SystemCall();
		public static Interruption interruption = new Interruption();
		public static IOHandler ioHandler;
		public static int IDProcess = 1;
	        public static MenuBar menubar;
	        public static TableView<Process> table;
	        
	        //Fichier Log qui gardera trace de toutes les actions du systeme
	        public static PrintStream log;
	        

    
    @Override
    public void start(Stage stage) throws Exception {
        // System.out.println("hello");
/*		try {
			Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
	                System.out.println("hello");
	                primaryStage.setTitle("LALA-OS");
			BorderPane root = new BorderPane(); 
			Scene scene = new Scene(root,800,550, Color.WHITESMOKE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
	              
	                    //***********MENU**********
	                    
	                    menubar = new MenuBar(); 
	                    menubar = Graphic.menu();
	                    root.setTop(menubar);
	                    primaryStage.show();
	                    
	                    table = new TableView<>();
	                    table = Graphic.tabview();
	                    root.setRight(table);
		} catch(Exception e) {
			e.printStackTrace();
		}*/
    }
    
    
	private static void startup() {
		
		try {
			log = new PrintStream(new File( "log.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       // disk.loadOS();
        
        outlog("BIOS ...");
		outlog("POST ...");
		// Memoire principale
		outlog("RAM ... ok!");
		// Memoire secondaire
		outlog("Disk ... ok!");
		// BIOS
		outlog("Loading Operating System ...");
		outlog("	");
		outlog("Setup...");
		outlog("Initialisation...");
		outlog("Start Kernel...");     
		outlog("Loading Application on Disk");
		disk.loadAppOnDisk();
	}
	
	public static void main(String[] args) {
	//	Main.RAM.AddProcessToList(Main.disk.p1000);
		startup();
		outlog("Finishing Loading App");
		
		generateur = new ProcessGen("Generateur");
		 execute = new Executor("Executeur");
		 ioHandler = new IOHandler("IOHandler");
		 outlog("Launching threads");
		generateur.start();
		execute.start();
		ioHandler.start();
            //    launch(args);
		
	}   
	
	public static void outlog(String message) {
		log.println(message);
	}
}
