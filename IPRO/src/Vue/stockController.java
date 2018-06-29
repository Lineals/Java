package Vue;
import Controleur.Boutique;
import Controleur.Controleur;
import Metier.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;


public class stockController implements Initializable {

    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */
    @FXML private TableView<Map.Entry<String,String>> tableView;
    @FXML private TableColumn<Map.Entry<String, String>, String> ref;
    @FXML private TableColumn<Map.Entry<String, String>, String> quantite;

    @FXML
    private Button btn_add;
    @FXML
    private Button btn_remove;
    @FXML
    private Button btn_change;
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
    private Boolean alreadyExists=false;
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
        cbb_article.getSelectionModel().selectFirst();
        txt_poids.setVisible(false);
        
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					Sellable sellable = Controleur.getArticleByReference(tableView.getSelectionModel().getSelectedItem().getKey());
					loadSellable(sellable);
					if (sellable instanceof Lot) {
						lot();
					} else { article(); }
				}
			}
		});
        
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
        ArrayList<Sellable> ArticlesEtLots = new ArrayList<Sellable>(Boutique.getInstance().getStock().keySet());
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
    void change(){
        loadSellable(Controleur.getArticleByReference(tableView.getSelectionModel().getSelectedItem().getKey()));
        alreadyExists=true;

    }
    @FXML
    void ajouterElem(){
        Sellable sell = null;
        if(lot.isSelected()){
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
        HashMap<Sellable,Integer> stockSell = Boutique.getInstance().getStock();
        HashMap<String,String> stockRef = new HashMap<>();
        Iterator it = stockSell.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Sellable sell = (Sellable) pair.getKey();
            stockRef.put(sell.getRef() , String.valueOf(pair.getValue()));
//            it.remove(); // avoids a ConcurrentModificationException
        }
        ref.setCellValueFactory(p -> {
            // this callback returns property for just one cell, you can't use a loop here
            // for first column we use key
            return new SimpleStringProperty(p.getValue().getKey());
        });

        quantite.setCellValueFactory(p -> {
            // for second column we use value
            return new SimpleStringProperty(p.getValue().getValue());
        });

        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(stockRef.entrySet());
        tableView.setItems(items);
        tableView.getColumns().setAll(ref, quantite);
    }
    private void setArticleVisible(boolean bool){
        article.setSelected(bool);
        txt_cout.setVisible(bool);
        txt_marque.setVisible(bool);
        txt_nom.setVisible(bool);
        txt_poids.setVisible(bool);
        txt_prix.setVisible(bool);
        cbb_couleur.setVisible(bool);
        cbb_article.setVisible(bool);
    }
    private void setLotVisible(boolean bool){
        txt_nombre.setVisible(bool);
        txt_pourcent.setVisible(bool);
        cbb_lot.setVisible(bool);
    }
    public void loadSellable(Sellable sell){


        if(sell instanceof Lot){
            Lot monlot = (Lot) sell;
            cbb_lot .getSelectionModel().select(monlot.article.getName());
            txt_nombre.setText(String.valueOf(monlot.getNum()));
            txt_pourcent.setText(String.valueOf(monlot.getTaux()));
        }else {
            Article monart = (Article) sell;
            cbb_article.getSelectionModel().select(monart.getName());
            txt_cout.setText(String.valueOf(monart.getCost()));
            txt_ref.setText(String.valueOf(monart.getRef()));
            txt_marque.setText(String.valueOf(monart.getBrand()));
            txt_nom.setText(String.valueOf(monart.getName()));
            if(sell instanceof Stylo){
                Stylo monstylo= (Stylo) sell;
                cbb_couleur.getSelectionModel().select(monstylo.getColor());

            }
            else if(sell instanceof Papier){
                Papier monpapier= (Papier) sell;
                txt_poids.setText(String.valueOf(monpapier.getPoids()));
            }
        }



    }



}
