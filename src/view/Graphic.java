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


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    
    public void run(){
        
        
    }
    
}
