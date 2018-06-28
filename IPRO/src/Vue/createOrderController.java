package Vue;

import Controleur.Boutique;
import Metier.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class createOrderController implements Initializable {
    @FXML
    private Label lbl_commande;
    @FXML
    private TableView<Commande.Ligne> tableView;
    @FXML
    private ComboBox cbb_commande;
    @FXML
    private ComboBox cbb_clients;
    @FXML
    private ComboBox cbb_articles;
    @FXML
    private String createOrModify;
    @FXML
    private TextField txt_taux_reduc;
    @FXML
    private TextField txt_fdp;
    @FXML
    private TextField txt_quantity;
    @FXML
    private Button btn_remove;
    @FXML
    private Button btn_add_order;
    @FXML
    private Button btn_cancel_order;
    @FXML
    private TableColumn<Commande.Ligne, String> column_article;
    @FXML
    private TableColumn<Commande.Ligne, String> column_amount;
    @FXML
    private TableColumn<Commande.Ligne, String> column_price;

    private Commande commande ;
    private Client client ;
    private Article article ;
    private ArrayList<Commande.Ligne> lignes = new ArrayList<>();
    private int quantite ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * Initialisation des textFields
         */
        txt_taux_reduc.setText("");
        txt_fdp.setText("");


        /**
         * Commandes
         */
        ArrayList<Commande> Commandes = Boutique.getInstance().getCommandes();
        cbb_commande.setItems(FXCollections.observableArrayList(Commandes));

        cbb_commande.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>() {
            @Override public void changed(ObservableValue<? extends Commande> selected, Commande oldCommande, Commande newCommande) {
                loadCommande(newCommande);
            }
        });
        /**
         * Clients
         */
        ArrayList<Client> Clients = Boutique.getInstance().getClients();
        cbb_clients.setItems(FXCollections.observableArrayList(Clients));

        cbb_clients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override public void changed(ObservableValue<? extends Client> selected, Client oldClient, Client newClient) {
                client=newClient;
            }
        });
        /**
         * Articles ou lignes
         */

        Set<Sellable> setSellables = Boutique.getInstance().getStock().keySet();
        ArrayList<Sellable> sellables = new ArrayList<>(setSellables);
        cbb_articles.setItems(FXCollections.observableArrayList(sellables));


    }
    private List<Commande.Ligne> parseLineList(){
        if (commande!=null){
            return new ArrayList<>(commande.getArticles());
        }
        return null;
    }
    public void majTableView(){
        List<Commande.Ligne> meslignes = parseLineList();
        System.out.println(meslignes);
        if(meslignes!=null){
            column_amount.setCellValueFactory(new PropertyValueFactory<Commande.Ligne, String>("prix"));
            column_price.setCellValueFactory(new PropertyValueFactory<Commande.Ligne, String>("quantite"));
            column_article.setCellValueFactory(new PropertyValueFactory<Commande.Ligne, String>("article"));
            tableView.getItems().setAll(meslignes);
        }
    }
    public void loadCommande(Commande com){
        commande=com;
        cbb_clients.getSelectionModel().select(com.getClient());
        txt_taux_reduc.setText(String.valueOf(com.getTauxReduc()));
        txt_fdp.setText(String.valueOf(com.getFraisDePort()));

        majTableView();


    }
    @FXML
    void addArticle(){
        System.out.println(cbb_articles.getSelectionModel());
        Sellable monsellable = (Sellable) cbb_articles.getValue();
        System.out.println(txt_quantity.getText());
        System.out.println(monsellable.getRef());
        commande.addSellable(monsellable,Integer.parseInt(txt_quantity.getText()));
        majTableView();

    }
    @FXML
    void removeArticle(){
        Commande.Ligne ligne = tableView.getSelectionModel().getSelectedItem();
        commande.removeLigne(ligne);
        majTableView();

    }
    @FXML
    void saveOrder(){
        float reduc = Float.valueOf(txt_taux_reduc.getText());
        float fdp = Float.valueOf(txt_fdp.getText());
        Commande com = new Commande(client,reduc,fdp);
        Boutique.getInstance().ajouterCommande(com);
    }
    @FXML
    void cancelOrder(){
        System.out.println("test");

    }
    @FXML
    void finaliser(){
        if(commande!=null){
            commande.setEstFinalisee(true);
        }

    }

}
