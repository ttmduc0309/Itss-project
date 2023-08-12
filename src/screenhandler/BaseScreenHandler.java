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
import model.bike.Bike;
import model.dock.Dock;

public class BaseScreenHandler implements Initializable {

	 @FXML
	 private Pane pageContainer;
	 
	 @FXML
	 private VBox cardLayout;
	 
	 @FXML
	 private List<Dock> dockList;
	 @FXML
	 private Button ViewBtn;
	 
	 
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
	 
	 List<Dock> dockList(){
		List<Dock> ls = new ArrayList<>();
		Dock dock = new Dock();
		dock.setName("Dock1");
		dock.setAddress("Tran Dai Nghia,Ha Noi");
		dock.setArea(350);
		dock.setNumOfAvailableBikes(3);
		dock.setNumOfEmptyPoints(4);
		dock.setDockImgSrc("/image/unnamed.png");
		dock.setBikeList(bikeList(dock));
		ls.add(dock);
		
		dock = new Dock();
		dock.setName("Dock2");
		dock.setAddress("Tran Dai Nghia,Ha Noi");
		dock.setArea(100);
		dock.setNumOfAvailableBikes(3);
		dock.setNumOfEmptyPoints(4);
		dock.setDockImgSrc("/image/unnamed.png");
		dock.setBikeList(bikeList(dock));
		ls.add(dock);
		
		return ls;
	 }
	 
	 List<Bike> bikeList(Dock dock){
		 List<Bike> ls = new ArrayList<>();
		 Bike bike =new Bike();
		 bike.setId("1");
		 bike.setBarCode("4740312401240712");
		 bike.setPrice(4000000);
		 bike.setLicensePlate("4740312401240712");
		 bike.setTypeId(1);
		 bike.setBikeImg("/image/ebike.jpg");
		 ls.add(bike);
		 
		 bike =new Bike();
		 bike.setId("2");
		 bike.setBarCode("4027402-481-284");
		 bike.setPrice(100000);
		 bike.setLicensePlate("4740312401240712");
		 bike.setTypeId(2);
		 bike.setBikeImg("/image/bikeimg.jpg");
		 ls.add(bike);
		 
		 bike =new Bike();
		 bike.setId("3");
		 bike.setBarCode("047041741");
		 bike.setPrice(100000);
		 bike.setLicensePlate("4740312401240712");
		 bike.setTypeId(3);
		 bike.setBikeImg("/image/bikeimg.jpg");
		 ls.add(bike);
		 
		 return ls;
	 }
	
}