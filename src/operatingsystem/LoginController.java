/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.Graphic;

/**
 * FXML Controller class
 *
 * @author SOLITIONS PC_01
 */
public class LoginController implements Initializable {

    
    public static MenuBar menubar;
    public static TableView<process.Process> table;
    public static Tab overview;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lblmessage;
    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField textPassword;
    @FXML
    private void btnLoginAction(ActionEvent event)
    {
        if(textUsername.getText().equals("lala") && textPassword.getText().equals("lala"))
        { ((Node) (event.getSource())).getScene().getWindow().hide();
            BorderPane root = new BorderPane(); 
			Scene scene = new Scene(root,800,550, Color.WHITESMOKE);
                        Stage stage = new Stage();
			stage.setScene(scene);
	              
	                    //***********MENU**********
	                    
	                menubar = new MenuBar(); 
	                menubar = Graphic.menu();
	                root.setTop(menubar);
	                stage.show();
                           
                         //  table = new TableView<>();
	                //   table = Graphic.tabview();
	                  // root.setRight(table);
                           
                        TabPane tabpane = new TabPane();
			tabpane.setNodeOrientation(NodeOrientation.INHERIT);
                        overview = new Tab();
                        overview = Graphic.Overview();
                        tabpane.getTabs().setAll(overview);
                        root.setCenter(tabpane);
        } 
        else
        {
            lblmessage.setText("invalide");
        }
            
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
