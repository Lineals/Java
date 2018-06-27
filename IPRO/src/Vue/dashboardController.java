package Vue;

import Controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import Controleur.*;


/**
 * Controller class for the first vista.
 */
public class dashboardController {

    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */
    @FXML
    private Label ca;
    @FXML
    private Label cout;
    @FXML
    private Label benefice;
    @FXML
    private void initialize(){
        ca.setText(Float.toString(Boutique.getInstance().getCA())+" €");
        cout.setText(Float.toString(Boutique.getInstance().getCoutFonctionnement())+" €");
        benefice.setText(Float.toString(Boutique.getInstance().getBenefice())+" €");
    }
/*    public void setLabel(String labelText){
        ca.setText(labelText);
    }*/


}