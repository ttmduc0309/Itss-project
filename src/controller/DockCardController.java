package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Dock;

public class DockCardController  {

    @FXML
    private Text DockDesc;

    @FXML
    private ImageView DockImg;

    @FXML
    private Label DockName;
    
    @FXML
    private Button viewBtn;
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Dock dock;
    

    @FXML
    void changeScene(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ViewDock.fxml"));
    		root=loader.load();
    		ViewDockController control = loader.getController();
    		control.showDockName(dock);
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}catch(IOException e){
			e.printStackTrace();
		}
    }
    
    public void setData(Dock dock) 
    {
    	this.dock = dock;
    	Image image = new Image(getClass().getResourceAsStream(dock.getDockImgSrc()));
    	DockImg.setImage(image);
    	
    	DockName.setText(dock.getDockName());
    	DockDesc.setText(dock.getDockDesc());
    }

}