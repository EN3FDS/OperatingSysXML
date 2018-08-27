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
import plateforme.CommonSpace;
import plateforme.Disk;
import plateforme.MMU;
import plateforme.Memory;
import process.File;
import process.Process;
import process.ProcessGen;
import view.Graphic;

import java.io.IOException;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import interruption.IOHandler;

/**
 *
 * @author Nahomie Labonté
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
		public static CPU cpu = new CPU();
		public static Executor execute;
		public static SystemCall systemCall = new SystemCall();
		public static Interruption interruption = new Interruption();
		public static IOHandler ioHandler;
		public static CommonSpace info =  new CommonSpace(true);
		public static int IDProcess = 1;

	        public static MenuBar menubar;
	        public static TableView<Process> table;

	/**Graphic for the manual mode****************************************************************/
	        private Stage primaryStage;
	        private BorderPane mainLayout;
	        

    
		    @Override
		    public void start(Stage primaryStage)  {
		        // System.out.println("hello");
				try {
					this.primaryStage = primaryStage;
					this.primaryStage.setTitle("ManualMode");
					showView();
					
					/*
					Parent rot = FXMLLoader.load(getClass().getResource("/operatingsystem/Login.fxml"));
		                        Scene sc = new Scene(rot);
		                        primaryStage.setScene(sc);
			             //   System.out.println("hello");
			                primaryStage.setTitle("LALA-OS");
		                        primaryStage.show();
					//BorderPane root = new BorderPane(); 
					//Scene scene = new Scene(root,800,550, Color.WHITESMOKE);
				// start	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					//primaryStage.setScene(scene);
			              
			                    //***********MENU**********
			                    
			                 //   menubar = new MenuBar(); 
			                 //   menubar = Graphic.menu();
			                 //   root.setTop(menubar);
			                 //   primaryStage.show();
			                    
			                 //   table = new TableView<>();
			                  //  table = Graphic.tabview();
			                  //  root.setRight(table);*/
				} catch(Exception e) {
					
					e.printStackTrace();
				}
		    }
		      
		    private void showView() throws IOException {
		    	Parent root = FXMLLoader.load(OS.class.getResource("view/Graphic.fxml"));
		    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		    	//FXMLLoader loader = new FXMLLoader();
		    	//loader.setLocation(getClass().getResource("../view/view.manualMode/ManualGen.fxml"));
		    	//mainLayout = loader.load();
		    	Scene scene = new Scene(root);
		    	primaryStage.setScene(scene);
		    	primaryStage.show();
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
		 ioHandler = new IOHandler("IOHandler");
		 System.out.println("Launching threads");
		generateur.start();
		execute.start();
		ioHandler.start();
                launch(args);
		
	}    
}
