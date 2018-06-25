package Vue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Main controller class for the entire layout.
 */
public class MainController {

    /** Holder of a switchable vista. */
    @FXML
    private StackPane vistaHolder;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }
    @FXML
    void dashBoard(ActionEvent event) {

        VistaNavigator.loadVista(VistaNavigator.DASHBOARD);
     }

    @FXML
    void orders(ActionEvent event) {
        VistaNavigator.loadVista(VistaNavigator.ORDERS);
    }

    @FXML
    void clients(ActionEvent event) {
        VistaNavigator.loadVista(VistaNavigator.CLIENTS);
    }

    @FXML
    void stock(ActionEvent event) {
        VistaNavigator.loadVista(VistaNavigator.STOCK);
    }


}