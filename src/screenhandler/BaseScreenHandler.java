package screenhandler;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import data.ConnectDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.bike.BikeDAO;
import model.dock.Dock;
import model.dock.DockDAO;

public class BaseScreenHandler implements Initializable {

	 @FXML
	 private Pane pageContainer;
	 
	 @FXML
	 private VBox cardLayout;
	 
	 @FXML
	 private List<Dock> dockList;
	 
	 @FXML
	 private Text noti;
	 
	 @FXML
	 private TextField barcodeField;
	 
	 private Bike bikeRented;
	 
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				dockList = new ArrayList<Dock>(DockDAO.requestData());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	 
	 @FXML
	 void enterBarcode(ActionEvent event) {
		 String inputCode = barcodeField.getText();
	    	try {
	    		BikeDAO bikeDAO = new BikeDAO();
	    		DockDAO dockDao = new DockDAO();
	    		Bike bike = bikeDAO.getBikeByBarcode(inputCode);
	    		
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ViewBike.fxml"));
	    		root=loader.load();
	    		BikePageHandler control = loader.getController();
	    		control.setData(bike, dockDao.findDockById(bike.getDockId()));

	    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		scene = new Scene(root);
	    		stage.setScene(scene);
	    		stage.show();
	    		
	    	}catch(SQLException e) {
	    		noti.setText("Barcode Not Found !!!!");
//	    		e.printStackTrace();
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	 

	
}