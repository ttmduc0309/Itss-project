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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.rentalInfo.RentalInfo;
import model.transaction.Transaction;

public class TransactionScreenHandler {

    @FXML
    private Text amount;

    @FXML
    private Text content;

    @FXML
    private Label notification;

    @FXML
    private Button returnBtn;

    @FXML
    private Text timeCreated;

    @FXML
    private Text transactionID;
    
    private Bike bikeRented;
    
    private Stage stage;
	private Scene scene;
	private Parent root;
	
	private RentalInfo rentalInfo;
	private boolean returning = false;
    
    public void setBikeRented(Bike bike) {
		 this.bikeRented=bike;
	 }
    
    public void setRentalInfo(RentalInfo rentalInfo) {
    	this.rentalInfo = rentalInfo;
    }
    
    @FXML
    void changeReturnScreen(ActionEvent event) throws IOException {
    	try {
    		if (!this.returning) {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReturnScreen.fxml"));
        		root=loader.load();
        		ReturnScreenHandler control = loader.getController();
        		control.setBikeRented(this.bikeRented);
        		control.setRentalInfo(this.rentalInfo);
        		control.initReturnScreen();
        		
        		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        		scene = new Scene(root);
        		stage.setScene(scene);
        		stage.show();
    		} else {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainScene.fxml"));
        		root=loader.load();
        		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        		scene = new Scene(root);
        		stage.setScene(scene);
        		stage.show();
    		}
    		
    	}catch(IOException e){
			e.printStackTrace();
		}
    }
    
    void setData(Transaction transaction) {
    	transactionID.setText("" + transaction.getId());
    	amount.setText("" + transaction.getAmount() + " VND");
    	timeCreated.setText(transaction.getTimeCreated().toString());
    	content.setText(transaction.getContent());
    }
    
    void setReturning() {
    	this.returning = true;
    }
    

}