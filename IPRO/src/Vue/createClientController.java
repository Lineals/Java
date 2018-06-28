package Vue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controleur.Boutique;
import Controleur.Controleur;
import Metier.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class createClientController implements Initializable {
	@FXML private TextField txt_prenom;
	@FXML private TextField txt_nom;
	@FXML private TextField txt_adresse;
	@FXML private Button btn_cancel;
	@FXML private Button btn_add_client;
	@FXML private Button btn_delete;
    @FXML private Label mandatory;
    
    private Client client;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (clientsController.getClient() != null) {
			btn_delete.setVisible(true);
			this.client = clientsController.getClient();
			loadClient(this.client);
		} else {
			txt_adresse.setText("");
			txt_prenom.setText("");
			txt_nom.setText("");
		}

		
	}
	
	private void loadClient(Client client) {
		txt_nom.setText(client.getNom());
		txt_prenom.setText(client.getPrenom());
		txt_adresse.setText(client.getAdresse());
		txt_nom.setDisable(true);
		txt_prenom.setDisable(true);
	}
	
	@FXML
	void addClient() {
		if(!txt_prenom.getText().trim().isEmpty() && !txt_adresse.getText().trim().isEmpty() && !txt_prenom.getText().trim().isEmpty()) {
			if(clientsController.getClient() != null) {
				client = new Client(txt_nom.getText().trim(), txt_prenom.getText().trim(), txt_adresse.getText().trim(), this.client.getId());
			} else { 
				client = new Client(txt_nom.getText().trim(), txt_prenom.getText().trim(), txt_adresse.getText().trim());
			}
			Boutique.getInstance().ajouterClient(client);
		} else {
			mandatory.setVisible(true);
		}
		VistaNavigator.loadVista(VistaNavigator.CLIENTS);
	}
	
	@FXML
	void cancel() {
		VistaNavigator.loadVista(VistaNavigator.CLIENTS);
	}
	
	@FXML
	void deleteClient() {
		Controleur.deleteClient(this.client);
		VistaNavigator.loadVista(VistaNavigator.CLIENTS);
	}
}
