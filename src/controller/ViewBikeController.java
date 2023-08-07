package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bike;

public class ViewBikeController {
    @FXML
    private Text bikeID;

    @FXML
    private ImageView bikeImg;

    @FXML
    private Text bikeType;

    @FXML
    private Button btnHome;

    @FXML
    private Button rentBtn;
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Bike bike;

    @FXML
    void changeHome(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/views/MainScene.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    public void setData(Bike bike) {
    	this.bike = bike;
    }
}
