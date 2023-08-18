package screenhandler;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.dock.Dock;
import model.rentalInfo.RentalInfo;

public class ReturnDockCardHandler {

    @FXML
    private Text DockAddress;

    @FXML
    private Text DockArea;

    @FXML
    private Text DockBikeNum;

    @FXML
    private Text DockEmptyPoints;

    @FXML
    private ImageView DockImg;

    @FXML
    private Label DockName;

    @FXML
    private Button selectBtn;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    private Dock dock;
    private RentalInfo rentalInfo;

    @FXML
    void changeScene(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RentalInfoScreen.fxml"));
    		root=loader.load();
    		RentalInfoScreenHandler control = loader.getController();
    		
    		control.setDock(this.dock);
    		control.setRentalInfo(this.rentalInfo);
    		
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}catch(IOException e){
			e.printStackTrace();
		}
    }
    
    public void setData(Dock dock, RentalInfo rentalInfo) 
    {
    	this.dock = dock;
    	this.rentalInfo = rentalInfo;
    	DockName.setText(dock.getName());
    	DockAddress.setText(dock.getAddress());
    	DockArea.setText("" + dock.getArea());
    	DockBikeNum.setText("The number of available bikes: "+ dock.getNumOfAvailableBikes());
    	DockEmptyPoints.setText("The number of empty points: "+ dock.getNumOfEmptyPoints());
    }
}