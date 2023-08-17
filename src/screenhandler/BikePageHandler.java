package screenhandler;

import java.io.IOException;
import java.text.DecimalFormat;
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
    private Text rentPrice;
    
    @FXML
    private Text depoPrice;

    @FXML
    private Text plateNum;
    
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
    	
    	this.bike=bike;
//    	Image image = new Image(getClass().getResourceAsStream(bike.getBikeImg()));
//    	bikeImg.setImage(image);
    	DecimalFormat formatter = new DecimalFormat("###,###,###");
    	bikeID.setText("" + bike.getId());
//    	bikeType.setText("" + bike.getTypeId());
    	if(bike.getTypeId() == 1) {
    		bikeType.setText("standard bike");
    	}else if(bike.getTypeId() == 2) {
    		bikeType.setText("twin e-bike");
    	}else {
    		bikeType.setText("standard e-bike");
    	}
    	depoPrice.setText("" + formatter.format(bike.getDepositPrice()) + " vnd");
    	rentPrice.setText("" + formatter.format(bike.getPrice()) + " vnd");
    	plateNum.setText(bike.getLicensePlate());
//    	System.out.println(bike.getTypeId());
    }
    
    @FXML
    void changeScene(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PaymentForm.fxml"));
    		root=loader.load();
    		PaymentFormHandler control = loader.getController();
    		control.setCart(this.bike);
//    		System.out.println(this.bike.getBarCode());
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}catch(IOException e){
			e.printStackTrace();
		}
    }
}