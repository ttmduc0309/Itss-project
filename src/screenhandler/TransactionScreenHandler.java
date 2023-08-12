package screenhandler;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;

public class TransactionScreenHandler {

    @FXML
    private Text message;

    @FXML
    private Label notification;

    @FXML
    private Pane pageContainer;

    @FXML
    private Button returnBtn;
    
    private Bike bikeRented;
    
    private Stage stage;
	private Scene scene;
	private Parent root;
    
    public void setBikeRented(Bike bike) {
		 this.bikeRented=bike;
	 }
    
    @FXML
    void changeReturnScreen(ActionEvent event) throws IOException {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReturnScreen.fxml"));
    		root=loader.load();
    		ReturnScreenHandler control = loader.getController();
    		control.setBikeRented(bikeRented);
    		
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}catch(IOException e){
			e.printStackTrace();
		}
    }

}