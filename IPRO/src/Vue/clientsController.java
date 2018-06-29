package Vue;

import Controleur.Boutique;
import Controleur.Controleur;
import Metier.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class clientsController {
    @FXML private TableView<Client> tableView;
    @FXML private TableColumn<Client, String> UserId;
    @FXML private TableColumn<Client, String> UserLastName;
    @FXML private TableColumn<Client, String> UserName;
    @FXML private TableColumn<Client, String> Address;
    @FXML private TextField txt_field_id;
    @FXML private TextField txt_field_name;
    @FXML private Button btn_search_id;
    @FXML private Button btn_search_name;
    @FXML private ComboBox<String> action;
    
    private static Client client = null;

    @FXML
    public void initialize() {
    	client = null;
    	txt_field_id.setText("");
    	txt_field_name.setText("");
	    txt_field_id.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
		            txt_field_id.setText(newValue.replaceAll("[^\\d]", ""));
		        }
			}
		});
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					client = tableView.getSelectionModel().getSelectedItem();
					VistaNavigator.loadVista(VistaNavigator.CREATECLIENT);
				}
			}
		});
		
    
        UserId.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
        UserLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        UserName.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        Address.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));

        tableView.getItems().setAll(parseUserList());
        action.getItems().setAll("SearchById", "SearchByName", "Add");
        
        action.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					switch (newValue) {
						case "SearchById": 
							txt_field_id.setVisible(true);
							txt_field_name.setVisible(false);  
							btn_search_id.setVisible(true);
							btn_search_name.setVisible(false);
							txt_field_id.setVisible(true); 
							btn_search_id.setVisible(true); 
							break;
						case "SearchByName": 
							txt_field_id.setVisible(false);
							txt_field_name.setVisible(true);  
							btn_search_id.setVisible(false);
							btn_search_name.setVisible(true);
							txt_field_name.setVisible(true); 
							btn_search_name.setVisible(true); 
							break;
						case "Add": VistaNavigator.loadVista(VistaNavigator.CREATECLIENT); break;
					}
				} 
			}
		});
		
    }
    
    private List<Client> parseUserList(){
        return Boutique.getInstance().getClients();
    }
    
    @FXML
    void searchById() {
    	if(!txt_field_id.getText().trim().isEmpty()){
	    	Client client = Controleur.getClientById(Integer.parseInt(txt_field_id.getText().trim()));
	    	tableView.getItems().setAll(client);
    	} else { 
    		tableView.getItems().setAll(parseUserList());
    	}
    }
    
    @FXML
    void searchByName() {
    	if(!txt_field_name.getText().trim().isEmpty()){
    		tableView.getItems().setAll(Controleur.getClientByName(txt_field_name.getText().trim()));
    	} else {
    		tableView.getItems().setAll(parseUserList());
    	}
    	
    }
    
    public static Client getClient() {
    	return client;
    }
}
