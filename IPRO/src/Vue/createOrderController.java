package Vue;

import Controleur.Boutique;
import Metier.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class createOrderController implements Initializable {
    @FXML
    private Label lbl_commande;
    @FXML
    private ComboBox cbb_commande;
    @FXML
    private ComboBox cbb_clients;
    @FXML
    private String createOrModify;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Commande> Commandes = Boutique.getInstance().getCommandes();
        cbb_commande.setItems(FXCollections.observableArrayList(Commandes));

        cbb_commande.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>() {
            @Override public void changed(ObservableValue<? extends Commande> selected, Commande oldCommande, Commande newCommande) {
                System.out.println(newCommande);
            }
        });

        ArrayList<Client> Clients = Boutique.getInstance().getClients();
        cbb_clients.setItems(FXCollections.observableArrayList(Clients));

        cbb_clients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override public void changed(ObservableValue<? extends Client> selected, Client oldClient, Client newClient) {
                System.out.println(newClient);
            }
        });

    }
}
