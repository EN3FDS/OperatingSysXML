/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author SOLITIONS PC_01
 */


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import process.Process;



public class Graphic implements Runnable {
 
    public static MenuBar menu() {
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("File");
                Menu overview = new Menu("Overview");
                Menu memory = new Menu("Memory");
                Menu disk = new Menu("Disk");
		Menu Help = new Menu("Help");
                /*******Sous-Menu*******/
                MenuItem Simulate = new MenuItem("Simulate");
                MenuItem Exit = new MenuItem("Exit");
                MenuItem About = new MenuItem("About");
                MenuItem Log = new MenuItem("Log");
                /******Ajout de sous-menu*******/
                file.getItems().add(Simulate);
                file.getItems().add(Exit);
                Help.getItems().add(About);
                
                
                Exit.setOnAction(e->{System.exit(0);});
            
             
                menuBar.getMenus().addAll(file,overview, memory, disk, Help);
		return menuBar;
            
	}
    
    public static TableView<Process> tabview(){
		TableColumn<Process, String> nameColumn =new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Process, String> pidColumn =new TableColumn<>("Pid");
		pidColumn.setMinWidth(100);
		pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid_process"));
                TableColumn<Process, String> priorityColumn =new TableColumn<>("Priority");
		priorityColumn.setMinWidth(100);
		priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
		TableView<Process> table = new TableView<>();
		table.getColumns().add(pidColumn);
		table.getColumns().add(nameColumn);
                table.getColumns().add(priorityColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		return table;
	}
    
    
       public static Tab Overview(){
                Tab over = new Tab("Overview");
                BorderPane root = new BorderPane();
               VBox global_vbox = new VBox();
		HBox hbox = new HBox();
		VBox label_vbox = new VBox();
		TableView<Process> table = new TableView<>();
                table = Graphic.tabview();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		HBox.setMargin(table, new Insets(0, 0, 0, 100));
		hbox.getChildren().addAll(table);
		global_vbox.getChildren().addAll(root, hbox, label_vbox);
		over.setContent(global_vbox);
		over.setClosable(false);
                
                 TabPane tabpane = new TabPane();
			//tabpane.setSide(Side.LEFT);
			//tabpane.setNodeOrientation(NodeOrientation.INHERIT);
                
                tabpane.getTabs().setAll(over);
                
                return(over);
    }
    
    public void run(){
        
        
    }
    
}
