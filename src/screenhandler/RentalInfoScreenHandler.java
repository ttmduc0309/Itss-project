package screenhandler;

import java.io.IOException;

import controller.ReturnBikeController;
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
import model.dock.Dock;
import model.rentalInfo.RentalInfo;

public class RentalInfoScreenHandler {

    @FXML
    private Text bikeId;

    @FXML
    private Text depositAmount;

    @FXML
    private Label notification;

    @FXML
    private Button proceedBtn;

    @FXML
    private Text rentEndTime;

    @FXML
    private Text rentStartTime;

    @FXML
    private Text rentalFee;

    @FXML
    private Text returnDockId;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    private Dock dock;
    private RentalInfo rentalInfo;

    @FXML
    void changeToPayment(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PaymentForm.fxml"));
    		root=loader.load();
    		PaymentFormHandler control = loader.getController();
    		
    		control.setRentalInfo(this.rentalInfo);
    		control.setDock(this.dock);
    		control.setReturning();
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    

    public void setDock(Dock dock) {
    	this.dock = dock;
    }
    
    public void setRentalInfo(RentalInfo rentalInfo) {
    	this.rentalInfo = rentalInfo;
    	ReturnBikeController rbc = new ReturnBikeController();
    	RentalInfo finalRentalInfo = rbc.finalizeRentalDetails(this.rentalInfo.getId(), this.rentalInfo.getBikeId(), this.dock.getId());
    	this.rentalInfo = finalRentalInfo;
    	
    	bikeId.setText("" + this.rentalInfo.getBikeId());
    	returnDockId.setText("" + this.rentalInfo.getReturnDockId());
    	rentStartTime.setText(this.rentalInfo.getRentStartTime().toString());
    	rentEndTime.setText(this.rentalInfo.getRentEndTime().toString());
    	depositAmount.setText("" + this.rentalInfo.getDepositAmount() + " VND");
    	rentalFee.setText("" + this.rentalInfo.getRentalFee());
    	
    }

}
