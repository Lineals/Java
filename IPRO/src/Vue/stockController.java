package Vue;
import Controleur.Boutique;
import Controleur.Controleur;
import Metier.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class stockController implements Initializable {

    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */
    @FXML private TableView<Sellable> tableView;
    @FXML private TableColumn<Sellable, String> nom;
    @FXML private TableColumn<Sellable, String> marque;
    @FXML private TableColumn<Sellable, String> prix;
    @FXML private TableColumn<Sellable, String> ref;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_marque;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_cout;
    @FXML
    private TextField txt_poids;
    @FXML
    private TextField txt_ref;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_pourcent;
    @FXML
    private TextField txt_quantite;
    @FXML
    private ComboBox cbb_article;
    @FXML
    private ComboBox cbb_couleur;
    @FXML
    private ComboBox cbb_lot;
    @FXML
    private ToggleButton article;
    @FXML
    private ToggleButton lot;
    private Sellable sellToAdd;
    private String ValStylo="Stylo";
    private String ValPapier="papier";
    @FXML
    void retirer(){

    }
    @FXML
    void update(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayTableView();
        article.setSelected(true);
        setLotVisible(false);
        cbb_article.setVisible(true);
        txt_poids.setVisible(false);
        cbb_article.setItems(FXCollections.observableArrayList(ValStylo,ValPapier));
        cbb_article.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldString, String newString) {
                if (newString==ValStylo){
                    cbb_couleur.setVisible(true);
                    txt_poids.setVisible(false);
                }
                else if(newString==ValPapier){
                    cbb_couleur.setVisible(false);
                    txt_poids.setVisible(true);

                }
            }
        });
        ArrayList<Sellable> ArticlesEtLots = new ArrayList<>(Boutique.getInstance().getStock().keySet());
        /**
         * pour faire un lot il faut uniquement les articles donc on se débarasse des sellables type lot
         */
        ArrayList<Sellable> justeArticles = new ArrayList<>();
        for (Sellable sell: ArticlesEtLots) {
            if(sell.getClass().getName()!="Metier.Lot"){
                justeArticles.add(sell);
            }
        }
        cbb_lot.setItems(FXCollections.observableArrayList(justeArticles));

        cbb_couleur.setItems(FXCollections.observableArrayList(Stylo.Couleur.values()));
    }
    @FXML
    void article(){
        lot.setSelected(false);
        article.setSelected(true);
        setArticleVisible(true);
        setLotVisible(false);
    }
    @FXML
    void lot(){
        lot.setSelected(true);
        article.setSelected(false);
        setArticleVisible(false);
        setLotVisible(true);

    }
    @FXML
    void ajouterElem(){
        Sellable sell = null;
        if(lot.isSelected()){
            System.out.println("oui");
            sell = new Lot(txt_ref.getText(),Integer.parseInt(txt_nombre.getText()),Integer.parseInt(txt_pourcent.getText()),(Article)cbb_lot.getValue());
        }else {
            if(cbb_article.getValue()==ValStylo){
                sell = new Stylo(txt_nom.getText(),txt_marque.getText(),txt_ref.getText(),
                        Integer.parseInt(txt_cout.getText()),Integer.parseInt(txt_prix.getText()),(Metier.Stylo.Couleur)cbb_couleur.getValue());
            }
            else if(cbb_article.getValue()==ValPapier){
                sell = new Papier(txt_nom.getText(),txt_marque.getText(),txt_ref.getText(),
                        Integer.parseInt(txt_cout.getText()),Integer.parseInt(txt_prix.getText()),Integer.parseInt(txt_poids.getText()));
            }
        }

        Boutique.getInstance().ajouterArticle(sell,Integer.parseInt(txt_quantite.getText()));
        displayTableView();


    }
    private void displayTableView(){
        ArrayList<Sellable> sells ;
        sells=new ArrayList<>(Boutique.getInstance().getStock().keySet());
        nom.setCellValueFactory(new PropertyValueFactory<Sellable, String>("name"));
        marque.setCellValueFactory(new PropertyValueFactory<Sellable, String>("brand"));
        prix.setCellValueFactory(new PropertyValueFactory<Sellable, String>("price"));
        ref.setCellValueFactory(new PropertyValueFactory<Sellable, String>("ref"));
        if(sells!=null){
            tableView.getItems().setAll(sells);
        }
    }
    private void setArticleVisible(boolean bool){
        article.setSelected(bool);
        txt_cout.setVisible(bool);
        txt_marque.setVisible(bool);
        txt_nom.setVisible(bool);
        txt_poids.setVisible(bool);
        txt_prix.setVisible(bool);
        txt_ref.setVisible(bool);
        cbb_couleur.setVisible(bool);
    }
    private void setLotVisible(boolean bool){
        txt_nombre.setVisible(bool);
        txt_pourcent.setVisible(bool);
        cbb_lot.setVisible(bool);
    }
    @FXML
    void removeArticle(){
        Sellable sell = tableView.getSelectionModel().getSelectedItem();
        Boutique.getInstance().retirerStock(sell);

        displayTableView();

    }

}
