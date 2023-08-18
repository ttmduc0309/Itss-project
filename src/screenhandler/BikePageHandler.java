package screenhandler;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import model.dock.Dock;

public class BikePageHandler {
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
    private Text price;
    
    @FXML
    private Text plateNum;
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Dock viewDock;
    
    private Bike bike;

    @FXML
    void changeHome(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ViewDock.fxml"));
    		root=loader.load();
    		DockPageHandler control = loader.getController();
    		control.showListBikeInDock(viewDock);
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void setData(Bike bike, Dock dock) {
    	this.viewDock = dock;
    	this.bike=bike;
    	DecimalFormat formatter = new DecimalFormat("###,###,###");
    	bikeID.setText("" + bike.getId());
    	bikeType.setText(this.bike.typeString());
    	if(bike.getTypeId() == 1) {
    		bikeImg.setImage(new Image(getClass().getResourceAsStream("/image/bikeimg.jpg")));
    	}else if(bike.getTypeId() == 2) {
    		bikeImg.setImage(new Image(getClass().getResourceAsStream("/image/ebike.jpg")));
    	}else {
    		bikeImg.setImage(new Image(getClass().getResourceAsStream("/image/twinbike.jpg")));
    	}
    	price.setText("" + formatter.format(bike.getPrice()) + " vnd");
    	plateNum.setText(bike.getLicensePlate());
    }
    
    @FXML
    void changeScene(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PaymentForm.fxml"));
    		root=loader.load();
    		PaymentFormHandler control = loader.getController();
    		control.setBike(this.bike);
    		control.setDock(this.viewDock);
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}catch(IOException e){
			e.printStackTrace();
		}
    }
}