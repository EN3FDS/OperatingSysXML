/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
//import operatingsystem.OS;

/**
 *
 * @author SOLITIONS PC_01
 */
public class Graphic extends BorderPane
{
    public Graphic()
    {
        Tab overviewTab = new Tab("Overview");
		Tab memoryTab = new Tab("Memory");
		Tab diskTab = new Tab("Disk");
				

		Overview overview = new Overview();
		//RamPane memoryPane = new RamPane();
		//DiskPane diskPane = new DiskPane();

		overviewTab.setContent(overview);
		//memoryTab.setContent(memoryPane);
		//diskTab.setContent(diskPane);

		TabPane onglets = new TabPane(overviewTab, memoryTab, diskTab);
		onglets.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		this.setTop(Menu());
		this.setCenter(onglets);
    }
    
    
    public MenuBar Menu()
    {
        MenuBar menu = new MenuBar();
        
        Menu file = new Menu("File");
        Menu help = new Menu("Help");
        
        MenuItem sim = new MenuItem("Simulate");
        MenuItem exit = new MenuItem("Exit");
        
        MenuItem about = new MenuItem("About");
        
        sim.setOnAction(e -> {
			
			sim.setDisable(true);
		});
         exit.setOnAction(e->{System.exit(0);});
        
         file.getItems().add(sim);
         file.getItems().add(exit);
         
         help.getItems().add(about);
         
         //Menubar
         menu.getMenus().add(file);
         menu.getMenus().add(help);
         
         return menu;
        
        
    }
    
    
    
}
