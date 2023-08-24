package screenhandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import common.exception.*;
import controller.PaymentController;
import controller.RentBikeController;
import controller.ReturnBikeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.dock.Dock;
import model.rentalInfo.RentalInfo;
import model.transaction.Transaction;

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
    private Text cardNumberErr;
    
    @FXML
    private Text cvvErr;
    
    @FXML
    private Text expirationDateErr;
    
    @FXML
    private Text holderNameErr;
    
    @FXML
    private Text requiredErrText;
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Bike bike;
    private Dock dock;
    
    private boolean returning = false;
    private RentalInfo rentalInfo;
    
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
    		PaymentController paymentController = new PaymentController();
    		if (!this.returning) {
    			control.setBikeRented(this.bike);
        		
        		// create new RentalInfo
        		RentBikeController rbc = new RentBikeController();
        		Map<String, String> cardPayload = getCardDetails();
        		paymentController.validatePaymentDetails(cardPayload.get("holderName"), cardPayload.get("cardNumber"),
        				cardPayload.get("cvv"), cardPayload.get("expirationDate"));
        		
        		RentalInfo rentalInfo = rbc.createNewRentalInfo(bike.getId(), dock.getId());
        		control.setRentalInfo(rentalInfo);
        		
        		Transaction transaction = paymentController.makeDepositPayment(cardPayload, this.bike.getId());
        		control.setData(transaction);
    		} else {
    			Map<String, String> cardPayload = getCardDetails();
    			paymentController.validatePaymentDetails(cardPayload.get("holderName"), cardPayload.get("cardNumber"),
        				cardPayload.get("cvv"), cardPayload.get("expirationDate"));
    			
    			Transaction transaction = paymentController.makeRentalPayment(cardPayload, this.rentalInfo.getId());
    			control.setData(transaction);
    			control.setReturning();
    		}
    		
    		btnHome.setVisible(true);
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch(IOException e){
			e.printStackTrace();
		} catch (InvalidPaymentDetailsException e) {
			if (e instanceof NullCardHolderNameException || e instanceof NullCardNumberException
					|| e instanceof NullExpirationDateException || e instanceof NullSecurityCodeException) {
				requiredErrText.setVisible(true);
			} else {
				requiredErrText.setVisible(false);
			}
			
			if (e instanceof InvalidCardNumberException) {
				cardNumberErr.setText("wrong card number format");
			} else {
				cardNumberErr.setText("");
			}
			if (e instanceof InvalidCardHolderNameException) {
				holderNameErr.setText("wrong card holder name format");
			} else {
				holderNameErr.setText("");
			}
			if (e instanceof InvalidExpirationDateException) {
				expirationDateErr.setText("wrong expiration date format");
			} else {
				expirationDateErr.setText("");
			}
			if (e instanceof InvalidSecurityCodeException) {
				cvvErr.setText("wrong security code format");
			} else {
				cvvErr.setText("");
			}
		}
    }
    
    public void setBike(Bike bike) {
    	this.bike= bike;
    }
    
    public void setDock(Dock dock) {
    	this.dock = dock;
    }
    
    public void setReturning() {
    	btnHome.setVisible(false);
    	this.returning = true;
    }
    
    public void setRentalInfo(RentalInfo rentalInfo) {
    	this.rentalInfo = rentalInfo;
    }
    
    public Map<String,String> getCardDetails(){
    	Map<String, String> cardPayload = new HashMap<>();
		cardPayload.putAll(Map.of("holderName", holderName.getText(), "cardNumber", cardNum.getText(), 
				"cvv", cardCVV.getText(), "expirationDate", expireDate.getText(), "content", transContent.getText()));
		return cardPayload;
    }
}