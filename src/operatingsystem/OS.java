/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

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
import plateforme.Disk;
import plateforme.MMU;
import plateforme.Memory;
import process.File;
import process.Process;
import process.ProcessGen;
import view.Graphic;

/**
 *
 * @author Nahomie Labont�
 */
public class OS extends Application {
	// fichier root
		public static File rootFile = new File(null, "root", true);
		// fichier user
		public static File userFile = new File(rootFile, "user", true);

		
		public static MMU mmu = new MMU();
		public static Memory RAM = new Memory();
		public static Scheduler scheduler= new Scheduler();
		public static ProcessGen generateur;
		public static Disk disk = new Disk();
		public static Executor execute;
		public static SystemCall systemCall = new SystemCall();
		public static int IDProcess = 1;
	        public static MenuBar menubar;
	        public static TableView<Process> table;
	        
	        

    
    @Override
    public void start(Stage primaryStage)  {
        // System.out.println("hello");
		try {
			//Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
	                System.out.println("hello");
	                primaryStage.setTitle("LALA-OS");
			BorderPane root = new BorderPane(); 
			Scene scene = new Scene(root,800,550, Color.WHITESMOKE);
		//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
		}
    }
    
    
	private static void startup() {
        
		System.out.println("loading app");
		disk.loadAppOnDisk();
	}
	
	public static void main(String[] args) {
	//	Main.RAM.AddProcessToList(Main.disk.p1000);
		startup();
		System.out.println("Finishing Loading App");
		
		generateur = new ProcessGen("Generateur");
		 execute = new Executor("Executeur");
		 System.out.println("Launching threads");
		generateur.start();
		execute.start();
               launch(args);
		
	}    
}
