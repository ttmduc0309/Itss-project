
package screenhandler;

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
import model.bike.Bike;

public class RentedBikeHandler {

    @FXML
    private Text bikeID;

    @FXML
    private ImageView bikeImg;

    @FXML
    private Text bikeType;

    @FXML
    private Button btnHome;

    @FXML
    private Button lockBtn;

    @FXML
    private Text plateNum;

    @FXML
    private Text timeRented;
    
   @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Bike rentedBike;
    
    

    @FXML
    void changeHome(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/views/MainScene.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    void setBikeRented(Bike bike) {
    	this.rentedBike=bike;
    }

}