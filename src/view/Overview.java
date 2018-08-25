/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;;
import process.PCB;

/**
 *
 * @author SOLITIONS PC_01
 */
public class Overview extends BorderPane{
    private FlowPane fPane = new FlowPane();
    
    private static ListView<PCB> listReadyQueue = new ListView<>();
    private static ListView<PCB> listIOQueue = new ListView<>();
    private static GridPane infoCurrentProcess = new GridPane();
   private static Label idProcess = new Label("");
    private static Label nameProcess = new Label("");
    private static Label sizeProcess = new Label("");
    private static ProgressBar progressBar = new ProgressBar();
    
    public Overview()
    {
                       
                // *****Ready QueueList********
		BorderPane paneListReadyQueue = new BorderPane();
		listReadyQueue.setFocusTraversable(false);
		listReadyQueue.setMaxHeight(50);
		listReadyQueue.setMinWidth(500);
		listReadyQueue.setDisable(false);
		listReadyQueue.setOrientation(Orientation.HORIZONTAL);
                
                paneListReadyQueue.setPadding(new Insets(0, 0, 10, 10));
		paneListReadyQueue.setTop(new Label("Ready Queue"));
		paneListReadyQueue.setCenter(listReadyQueue);

		// *****IO Queue*******
		BorderPane paneIoQueue = new BorderPane();
		listIOQueue.setFocusTraversable(false);
		listIOQueue.setMaxHeight(50);
		listIOQueue.setMinWidth(500);
		listIOQueue.setDisable(false);
		listIOQueue.setOrientation(Orientation.HORIZONTAL);

		paneIoQueue.setPadding(new Insets(0, 0, 10, 10));
		paneIoQueue.setTop(new Label("I\\O Queue"));
		paneIoQueue.setCenter(listIOQueue);
                
		// *****ProgressBar********
		BorderPane paneProgressBar = new BorderPane();
		progressBar.setFocusTraversable(false);

		paneProgressBar.setCenter(progressBar);
		paneProgressBar.setPadding(new Insets(10, 10, 10, 10));
		
                
                /// ********Processes Pane********
		ProcessPane processPane = new ProcessPane();
		

		fPane.setPadding(new Insets(30));

		
		fPane.getChildren().add(paneProgressBar);
		fPane.getChildren().add(paneListReadyQueue);
		fPane.getChildren().add(paneIoQueue);

		this.setCenter(fPane);
		this.setRight(processPane);
                
    }   
    
   /* public synchronized static void refreshListReadyQueue() {
		Platform.runLater(() -> {
			listReadyQueue.getItems().clear();

			ObservableList<PCB> list = FXCollections.observableArrayList();
			Scheduler.getReadyQueue().forEach(data -> {
				list.add(data);
			});

			listReadyQueue.setItems(list);
		});
	}*/
    
   /* public synchronized static void refreshListIOQueue() {
		Platform.runLater(() -> {
			listIOQueue.getItems().clear();

			ObservableList<PCB> list = FXCollections.observableArrayList();
			if (Scheduler.ioQueue != null) {
				Scheduler.ioQueue.forEach(data -> {
					if (data!=null) {
						list.add(data.getPcb());
					}
				});
			}
			listIOQueue.setItems(list);
			
		});
	}*/
    
   
    /*public synchronized static void refreshProgressBar(int current, int MAX) {
		Platform.runLater(() -> {
			progressBar.setProgress(((double) current / (double) MAX));
		});
	}*/
    
}
