package screenhandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    @FXML
	private List<Dock> dockList;

    private Bike bikeRented;

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

}