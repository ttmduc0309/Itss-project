package screenhandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import model.Bike.Bike;
import model.Dock.Dock;

public class BaseScreenHandler implements Initializable {

	 @FXML
	 private Pane pageContainer;
	 
	 @FXML
	 private VBox cardLayout;
	 
	 @FXML
	 private List<Dock> dockList;
	 @FXML
	 private Button ViewBtn;
	 
	 private Bike bikeRented;
	 
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 
	 @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			dockList = new ArrayList<>(dockList());
			try {
				for(int i=0;i<dockList.size();i++) {
					FXMLLoader fxmlLoader =new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/views/DockCard.fxml"));
					Pane cardBox = fxmlLoader.load();
					DockCardHandler cardController = fxmlLoader.getController();
					cardController.setData(dockList.get(i));
					cardLayout.getChildren().add(cardBox);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
	 
	 @FXML
	 void changeToRented(ActionEvent event) {
		 try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RentedBike.fxml"));
	    		root=loader.load();
	    		RentedBikeHandler control = loader.getController();
	    		control.setBikeRented(this.bikeRented);
	    		
	    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		scene = new Scene(root);
	    		stage.setScene(scene);
	    		stage.show();
	    	}catch(IOException e){
				e.printStackTrace();
			}
		 }
	 
	 public void setBikeRented(Bike bike) {
		 this.bikeRented=bike;
	 }
	 
	 
	 List<Dock> dockList(){
		List<Dock> ls = new ArrayList<>();
		Dock dock = new Dock();
		dock.setDockName("Dock1");
		dock.setDockAddress("Tran Dai Nghia,Ha Noi");
		dock.setDockArea("100m2");
		dock.setDockNumBike(3);
		dock.setDockEmptyPoints(4);
		dock.setDockImgSrc("/image/unnamed.png");
		dock.setBikeList(bikeList(dock));
		ls.add(dock);
		
		dock = new Dock();
		dock.setDockName("Dock2");
		dock.setDockAddress("Tran Dai Nghia,Ha Noi");
		dock.setDockArea("100m2");
		dock.setDockNumBike(3);
		dock.setDockEmptyPoints(4);
		dock.setDockImgSrc("/image/unnamed.png");
		dock.setBikeList(bikeList(dock));
		ls.add(dock);
		
		return ls;
	 }
	 
	 List<Bike> bikeList(Dock dock){
		 List<Bike> ls = new ArrayList<>();
		 Bike bike =new Bike();
		 bike.setBikeID("1");
		 bike.setBarCode("4740312401240712");
		 bike.setPrice(400000);
		 bike.setLicensePlate("4740312401240712");
		 bike.setBikeType(1);
		 bike.setBikeImg("/image/ebike.jpg");
		 ls.add(bike);
		 
		 bike =new Bike();
		 bike.setBikeID("2");
		 bike.setBarCode("4027402-481-284");
		 bike.setPrice(100000);
		 bike.setLicensePlate("4740312401240712");
		 bike.setBikeType(2);
		 bike.setBikeImg("/image/bikeimg.jpg");
		 ls.add(bike);
		 
		 bike =new Bike();
		 bike.setBikeID("3");
		 bike.setBarCode("047041741");
		 bike.setPrice(100000);
		 bike.setLicensePlate("4740312401240712");
		 bike.setBikeType(3);
		 bike.setBikeImg("/image/bikeimg.jpg");
		 ls.add(bike);
		 
		 return ls;
	 }
	
}