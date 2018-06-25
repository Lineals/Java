package Vue;

import Controleur.Boutique;
import Metier.Client;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class clientsController {
    @FXML private TableView<Client> tableView;
    @FXML private TableColumn<Client, String> UserId;
    @FXML private TableColumn<Client, String> UserLastName;
    @FXML private TableColumn<Client, String> UserName;
    @FXML private TableColumn<Client, String> Address;

    @FXML
    public void initialize() {
        UserId.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
        UserLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        UserName.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        Address.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));

        tableView.getItems().setAll(parseUserList());
    }
    private List<Client> parseUserList(){
        return Boutique.getInstance().getClients();
    }

        /**
         * Event handler fired when the user requests a new vista.
         *
         * @param event the event that triggered the handler.
         */


}
