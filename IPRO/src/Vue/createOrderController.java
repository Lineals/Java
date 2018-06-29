package Vue;

import Controleur.Boutique;
import Controleur.Controleur;
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
    private Button btn_save;
    @FXML
    private TableColumn<Commande.Ligne, String> column_article;
    @FXML
    private TableColumn<Commande.Ligne, String> column_amount;
    @FXML
    private TableColumn<Commande.Ligne, String> column_price;
    @FXML
    private ToggleButton creer;
    @FXML
    private ToggleButton modifier;
    @FXML
    private Button finalize;
    @FXML
    private Button delete;

    private Commande commande = new Commande(null,0,0);
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
        cbb_commande.setVisible(false);


        /**
         * Commandes
         */
        ArrayList<Commande> Commandes = Boutique.getInstance().getCommandes();
        ArrayList<Commande> CommandesNonfinalises = new ArrayList<Commande>();
        for (Commande com : Commandes){
            if(!com.getEstFinalisee()){CommandesNonfinalises.add(com); }
        }
        cbb_commande.setItems(FXCollections.observableArrayList(CommandesNonfinalises));

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
                commande.setClient(newClient);

            }
        });
        /**
         * Articles ou lignes
         */

        Set<Sellable> setSellables = Boutique.getInstance().getStock().keySet();
        ArrayList<Sellable> sellables = new ArrayList<>(setSellables);
        cbb_articles.setItems(FXCollections.observableArrayList(sellables));
        
        if(ordersController.getCommande() != null) {
        	this.commande = ordersController.getCommande();
        	if (!this.commande.getEstFinalisee()) {
        		finalize.setVisible(true);
        		delete.setVisible(true);
        	} else {
        		txt_taux_reduc.setEditable(false);
        		txt_fdp.setEditable(false);
        		txt_quantity.setEditable(false);
//        		btn_save.setVisible(false);
        		btn_add_order.setVisible(false);
        	}
        	loadCommande(this.commande);
        }


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
            column_amount.setCellValueFactory(new PropertyValueFactory<Commande.Ligne, String>("quantite"));
            column_price.setCellValueFactory(new PropertyValueFactory<Commande.Ligne, String>("prix"));
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
        boolean exists=false;
        Sellable monsellable = (Sellable) cbb_articles.getValue();
        Integer maquantite = Integer.parseInt(txt_quantity.getText());
        ArrayList<Commande.Ligne> lignes = commande.getArticles();
        for (Commande.Ligne l:lignes) {
            if (l.getArticle().equals(monsellable)) {
                exists=true;
                l.setQuantite(l.getQuantite()+maquantite);
            }
        }
        if(!exists){
            commande.addSellable(monsellable,maquantite);
        }
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
        commande.setTauxReduc(reduc);
        commande.setFraisDePort(fdp);
        commande.setArticles(new ArrayList<>(parseLineList()));
        Boutique.getInstance().ajouterCommande(commande);
        VistaNavigator.loadVista(VistaNavigator.ORDERS);
    }
    @FXML
    void cancelOrder(){
        VistaNavigator.loadVista(VistaNavigator.ORDERS);

    }
    @FXML
    void finaliser(){
        if(commande!=null){
            commande.setEstFinalisee(true);
        }
        VistaNavigator.loadVista(VistaNavigator.ORDERS);
    }
    
    @FXML
    void deleteOrder() {
    	Controleur.deleteCommande(commande);
    }

}
