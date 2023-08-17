package screenhandler;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bike.Bike;

public class PaymentFormHandler {

    @FXML
    private Button btnHome;

    @FXML
    private TextField cardCVV;

    @FXML
    private TextField cardNum;

    @FXML
    private Button checkOutBtn;

    @FXML
    private TextField expireDate;

    @FXML
    private TextField holderName;
    
    @FXML
    private TextField transContent;
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Bike bikeCart;
    
    @FXML
    void changeHome(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/views/MainScene.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    @FXML
    void changeSuccessRent(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TransactionScreen.fxml"));
    		root=loader.load();
    		TransactionScreenHandler control = loader.getController();
    		control.setBikeRented(this.bikeCart);
    		String name = holderName.getText();
    		String cardNumber = cardNum.getText();
    		String expiration = expireDate.getText();
    		String cvv = cardCVV.getText();
    		String content = transContent.getText();
    		
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}catch(IOException e){
			e.printStackTrace();
		}
    }
    
    public void setCart(Bike bike) {
    	this.bikeCart= bike;
    }
    
    public Bike getCart() {
    	return bikeCart;
    }
    
}