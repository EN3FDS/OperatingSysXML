/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import process.PCB;
import process.ProcessGen;

/**
 *
 * @author SOLITIONS PC_01
 */
public class ProcessPane extends BorderPane {
    
    public static TableView<PCB> TablePCB = new TableView<PCB>();
    private static TableColumn<PCB, String> pidColumn = new TableColumn<PCB, String>();
    private static TableColumn<PCB, String> nameColumn = new TableColumn<PCB, String>();
    
    private ObservableList<PCB> DataPCB = FXCollections.observableArrayList();
    private ContextMenu mn_context = new ContextMenu();
        
    public ProcessPane()
    {
        pidColumn.setText("PID");
        nameColumn.setText("ProcessName");
        TablePCB.getColumns().add(pidColumn);
        TablePCB.getColumns().add(nameColumn);
        
        pidColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
   
        TablePCB.setItems(DataPCB);
        
        MenuItem kill = new MenuItem("ENd Task");
        kill.setOnAction(e ->{
            int id = TablePCB.getSelectionModel().getSelectedItem().getPid();
            ProcessGen.idProcess = id;
            ProcessGen.manualOn = true;
            ProcessGen.numInt = 128;
	    ProcessGen.numSC = 1;
        });
        
        mn_context.getItems().add(kill);
        TablePCB.setOnMouseClicked(e->{
            if(e.isPopupTrigger() && !TablePCB.getItems().isEmpty()){
                mn_context.show(TablePCB, e.getScreenX(),e.getScreenY());
            }
            else{
                mn_context.hide();
            }
        });
        
        TablePCB.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TablePCB.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        this.setCenter(TablePCB);
    
    
    }
}
