package screenhandler;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Bike.Bike;
import model.Dock.Dock;

public class DockPageHandler{
	
	@FXML
    private TableView<Bike> bikeTable;
	
	@FXML
    private TableColumn<Bike, String> licensePlate;

	@FXML
    private TableColumn<Bike, String> barcode;
	
	@FXML
	private TableColumn<Bike, String> id;
	
	@FXML
	private TableColumn<Bike,String> bikebtn;

    @FXML
    private TableColumn<Bike, String> biketype;
	 
    @FXML
    private Label DockName;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Button rentBtn;
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Dock dock;
    
    ObservableList<Bike> list;
    
    Callback<TableColumn<Bike, String>, TableCell<Bike, String>> cellFactory
    = //
    new Callback<TableColumn<Bike, String>, TableCell<Bike, String>>() {
	@Override
	public TableCell<Bike,String> call(final TableColumn<Bike, String> param) {
	    final TableCell<Bike, String> cell = new TableCell<Bike, String>() {
	
	        final Button btn = new Button("Select");
	
	        @Override
	        public void updateItem(String item, boolean empty) {
	            super.updateItem(item, empty);
	            if (empty) {
	                setGraphic(null);
	                setText(null);
	            } else {
	                btn.setOnAction(event -> {
	                	try {
	                		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ViewBike.fxml"));
	                		root=loader.load();
	                		BikePageHandler control = loader.getController();
	                		control.setData(this.getTableRow().getItem());
	                		
	                		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	                		scene = new Scene(root);
	                		stage.setScene(scene);
	                		stage.show();
	                	}catch(IOException e){
	            			e.printStackTrace();
	            		}
	                    
	                });
	                setGraphic(btn);
	                setText(null);
	            }
	        }
	    };
	    return cell;
	}
	};
    
    public void showDockName(Dock dock) {
    	this.dock = dock;
    	this.DockName.setText(this.dock.getDockName());
    	
    	list=FXCollections.observableArrayList(this.dock.getBikeList());
    	id.setCellValueFactory(new PropertyValueFactory<Bike,String>("BikeID"));
    	licensePlate.setCellValueFactory(new PropertyValueFactory<Bike,String>("LicensePlate"));
    	barcode.setCellValueFactory(new PropertyValueFactory<Bike,String>("BarCode"));
    	biketype.setCellValueFactory(new PropertyValueFactory<Bike,String>("BikeType"));
    	bikebtn.setCellFactory(cellFactory);
    	bikeTable.setItems(list);
    }
    
    @FXML
    void changeHome(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/views/MainScene.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

	

	
    

}
