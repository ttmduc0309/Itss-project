package screenhandler;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ReturnBikeController;
import data.ConnectDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bike.Bike;
import model.dock.Dock;
import model.dock.DockDAO;
import model.rentalInfo.RentalInfo;

public class ReturnScreenHandler{

    @FXML
    private VBox cardLayout;

    @FXML
    private Pane pageContainer;

    @FXML
    private Button viewBtn;
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Bike bikeRented;
    private RentalInfo rentalInfo;
    @FXML
	private List<Dock> dockList;

    @FXML
    void changeRentedBike(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RentedBike.fxml"));
    		root=loader.load();
    		RentedBikeHandler control = loader.getController();
    		control.setBikeRented(this.bikeRented);
    		control.setRentalInfo(this.rentalInfo);
 
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void setBikeRented(Bike bike) {
		 this.bikeRented=bike;
	}
    
    public void setRentalInfo(RentalInfo rentalInfo) {
    	this.rentalInfo = rentalInfo;
    }
    
	public void initReturnScreen() {
		ReturnBikeController rbc = new ReturnBikeController();
		dockList = rbc.getUnfilledDocks();

		try {
			for(int i = 0; i < dockList.size(); i++) {
				FXMLLoader fxmlLoader =new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/views/ReturnDockCard.fxml"));
				Pane cardBox = fxmlLoader.load();
				ReturnDockCardHandler cardController = fxmlLoader.getController();
				cardController.setData(dockList.get(i), this.rentalInfo);
				cardLayout.getChildren().add(cardBox);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}

