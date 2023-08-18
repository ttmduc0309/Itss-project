
package screenhandler;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.rentalInfo.RentalInfo;

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
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Bike rentedBike;
    private RentalInfo rentalInfo;

    @FXML
    void changeHome(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReturnScreen.fxml"));
		root=loader.load();
		ReturnScreenHandler control = loader.getController();
		control.setBikeRented(this.rentedBike);
		control.setRentalInfo(this.rentalInfo);
		control.initReturnScreen();
		
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    void setBikeRented(Bike bike) {
    	this.rentedBike=bike;
    	bikeID.setText("" + this.rentedBike.getId());
    	bikeType.setText(this.rentedBike.typeString());
    	plateNum.setText(this.rentedBike.getLicensePlate());
    	if(this.rentedBike.getTypeId() == 1) {
    		bikeImg.setImage(new Image(getClass().getResourceAsStream("/image/bikeimg.jpg")));
    	}else if(this.rentedBike.getTypeId() == 2) {
    		bikeImg.setImage(new Image(getClass().getResourceAsStream("/image/ebike.jpg")));
    	}else {
    		bikeImg.setImage(new Image(getClass().getResourceAsStream("/image/twinbike.jpg")));
    	}
    }
    
    void setRentalInfo(RentalInfo rentalInfo) {
    	this.rentalInfo = rentalInfo;
    }

}