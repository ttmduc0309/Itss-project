package screenhandler;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;

import model.bike.Bike;
import model.bike.BikeDAO;
import model.dock.Dock;

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
    private TextField barcodeField;
	
	@FXML
	private TableColumn<Bike,String> bikebtn;

    @FXML
    private TableColumn<Bike, String> biketype;
    
    @FXML
    private TableColumn<Bike, String> price;
    
    @FXML
    private Text noti;
	 
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
	                		control.setData(this.getTableRow().getItem(), dock);
	                		
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
    
    public void showListBikeInDock(Dock dock) throws SQLException {
    	this.dock = dock;
    	ArrayList<Bike> bikeListInDock = new ArrayList<Bike>();
    	bikeListInDock = BikeDAO.getListBikeInDock(dock.getId());
    	DockName.setText("Dock - " + dock.getName());
    	
    	ObservableList<Bike> bikeObservableList = FXCollections.observableArrayList(bikeListInDock);

    	id.setCellValueFactory(new PropertyValueFactory<>("Id"));
    	licensePlate.setCellValueFactory(new PropertyValueFactory<>("LicensePlate"));
    	barcode.setCellValueFactory(new PropertyValueFactory<>("BarCode"));
    	biketype.setCellValueFactory(cellData -> {
            int typeId = cellData.getValue().getTypeId();
            if (typeId == 1) {
                return new SimpleStringProperty("standard");
            } else if (typeId == 2) {
                return new SimpleStringProperty("e-bike");
            } else {
            	return new SimpleStringProperty("twin e-bike");
            }
        });
    	price.setCellValueFactory(cellData -> {
            long deposit = cellData.getValue().getPrice();
            DecimalFormat formatter = new DecimalFormat("###,###,###");
//            String formattedDeposit = formatCurrency(deposit); // You can create a method to format the deposit
            return new SimpleStringProperty("" + formatter.format(deposit) + "VND");
        });
    	bikebtn.setCellFactory(cellFactory);
    	
    	bikeTable.setItems(bikeObservableList);
    }
    
    @FXML
    void changeHome(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/views/MainScene.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

	
    @FXML
    void enterBarcode(ActionEvent event) {
    	String inputCode = barcodeField.getText();
    	try {
    		BikeDAO bikeDAO = new BikeDAO();
    		Bike bike = bikeDAO.getBikeByBarcode(inputCode);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ViewBike.fxml"));
    		root=loader.load();
    		BikePageHandler control = loader.getController();
    		control.setData(bike, dock);
    		
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    		
    	}catch(SQLException e) {
    		noti.setText("Barcode Not Found !!!!");
//    		e.printStackTrace();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
