package screenhandler;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.dock.Dock;
import model.dock.DockDAO;

public class DockReturnCardHandler  {
	
	@FXML
    private Text DockEmptyPoints;
	
	@FXML
    private Text DockAddress;

    @FXML
    private Text DockArea;

    @FXML
    private Text DockBikeNum;

    @FXML
    private ImageView DockImg;

    @FXML
    private Label DockName;
    
    @FXML
    private Button viewBtn;
    
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Dock dock;
    

    @FXML
    void changeScene(ActionEvent event) throws SQLException {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PaymentForm.fxml"));
    		root=loader.load();
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}catch(IOException e){
			e.printStackTrace();
		}
    }
    
    public void setData(Dock dock) 
    {
    	this.dock = dock;
//    	Image image = new Image(getClass().getResourceAsStream(dock.getDockImgSrc()));
//    	DockImg.setImage(image);
    	
    	DockName.setText(dock.getName());
    	DockAddress.setText(dock.getAddress());
    	DockArea.setText("" + dock.getArea());
    	DockBikeNum.setText("The number of available bikes: "+ dock.getNumOfAvailableBikes());
    	DockEmptyPoints.setText("The number of empty points: "+ dock.getNumOfEmptyPoints());
    }

}