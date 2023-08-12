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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bike.Bike;
import model.dock.Dock;
import model.dock.DockDAO;

public class ReturnScreenHandler implements Initializable {

    @FXML
    private VBox cardLayout;

    @FXML
    private Pane pageContainer;

    @FXML
    private Button viewBtn;
    
    private Bike bikeRented;
    @FXML
	private List<Dock> dockList;
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void changeRentedBike(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RentedBike.fxml"));
    		root=loader.load();
    		RentedBikeHandler control = loader.getController();
    		control.setBikeRented(bikeRented);
    		
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
				fxmlLoader.setLocation(getClass().getResource("/views/DockReturnCard.fxml"));
				Pane cardBox = fxmlLoader.load();
				DockReturnCardHandler cardController = fxmlLoader.getController();
				cardController.setData(dockList.get(i));
				cardLayout.getChildren().add(cardBox);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}


