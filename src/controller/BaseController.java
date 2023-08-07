package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Bike;
import model.Dock;

public class BaseController implements Initializable {

	 @FXML
	 private Pane pageContainer;
	 
	 @FXML
	 private VBox cardLayout;
	 
	 @FXML
	 private List<Dock> dockList;
	 
	 
	 
	 @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			dockList = new ArrayList<>(dockList());
			try {
				for(int i=0;i<dockList.size();i++) {
					FXMLLoader fxmlLoader =new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/views/DockCard.fxml"));
					Pane cardBox = fxmlLoader.load();
					DockCardController cardController = fxmlLoader.getController();
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
		dock.setDockName("Dock1");
		dock.setDockDesc("This dock is here");
		dock.setDockImgSrc("/image/unnamed.png");
		dock.setBikeList(bikeList(dock));
		ls.add(dock);
		
		dock = new Dock();
		dock.setDockName("Dock2");
		dock.setDockDesc("This dock is here");
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
		 bike.setBikeType("Standard_Bike");
		 bike.setCurrentDock(dock.getDockName());
		 ls.add(bike);
		 
		 bike =new Bike();
		 bike.setBikeID("2");
		 bike.setBarCode("4027402-481-284");
		 bike.setBikeType("Standard_Bike");
		 bike.setCurrentDock(dock.getDockName());
		 ls.add(bike);
		 
		 bike =new Bike();
		 bike.setBikeID("3");
		 bike.setBarCode("047041741");
		 bike.setBikeType("Standard_Bike");
		 bike.setCurrentDock(dock.getDockName());
		 ls.add(bike);
		 
		 return ls;
	 }
	
}
