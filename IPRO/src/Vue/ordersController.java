package Vue;

import Controleur.Boutique;
import Metier.Client;
import Metier.Commande;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the second vista.
 */
public class ordersController implements Initializable {

    /**
     * Event handler fired when the user requests a previous vista.
     *
     * @param event the event that triggered the handler.
     */
    @FXML private TableView<Commande> tableView;
    @FXML private TableColumn<Commande, String> id;
    @FXML private TableColumn<Commande, String> client;
    @FXML private TableColumn<Commande, String> port;
    @FXML private TableColumn<Commande, String> discount;
    @FXML private TableColumn<Commande, String> price;
    @FXML private TableColumn<Commande, String> finalised;
    @FXML
    private ComboBox<String> action;
    @FXML
    private Button btn_id;
    @FXML
    private Button btn_client;
    @FXML
    private Button btn_add;
    @FXML
    private TextField txt_field;
   /* @FXML // fx:id="selectedFruit"
    private Label selectedAction;
*/

    private List<Commande> parseOrderList(){
        System.out.println(Boutique.getInstance().getCommandes());
        return Boutique.getInstance().getCommandes();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Commande, String>("id"));
        client.setCellValueFactory(new PropertyValueFactory<Commande, String>("client"));
        port.setCellValueFactory(new PropertyValueFactory<Commande, String>("prenom"));
        discount.setCellValueFactory(new PropertyValueFactory<Commande, String>("tauxreduc"));
        price.setCellValueFactory(new PropertyValueFactory<Commande, String>("prixTotal"));
        finalised.setCellValueFactory(new PropertyValueFactory<Commande, String>("estFinalisee"));
        tableView.getItems().setAll(parseOrderList());

        action.getItems().setAll("SearchById", "SearchByClient", "Add");
        System.out.println("test");
        // bind the selected fruit label to the selected fruit in the combo box.


/*
        selectedAction.textProperty().bind(action.getSelectionModel().selectedItemProperty());
*/


        // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
        action.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldAction, String newAction) {
                txt_field.setVisible(true);
                if (oldAction != null) {
                    switch(oldAction) {
                        case "SearchById": btn_id.setVisible(false); break;
                        case "SearchByClient": btn_client.setVisible(false); break;
                        case "Add": btn_add.setVisible(false); break;
                    }
                }
                if (newAction != null) {
                    switch(newAction) {
                        case "SearchById": btn_id.setVisible(true); break;
                        case "SearchByClient": btn_client.setVisible(true); break;
                        case "Add": btn_add.setVisible(true); break;
                    }
                }
            }
        });
    }

}