import Controleur.Boutique;
import Metier.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Vue.MainController;
import Vue.VistaNavigator;

import java.io.IOException;

/**
 * Main application class.
 */
public class Main extends Application {
    Boutique maboutique;

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Vista Viewer");
        maboutique = Boutique.getInstance();
        maboutique.ajouterClient(new Client("lineal","remy","1 avenue de la resistance"));
        maboutique.ajouterClient(new Client("toast","theo","1 rue de la resistance"));
        maboutique.ajouterClient(new Client("oropo","emilio","1 square de la resistance"));
        maboutique.setCA(100204);
        maboutique.setCoutFonctionnement(1004);
        stage.setScene(
            createScene(
                loadMainPane()
            )
        );

        stage.show();
    }

    /**
     * Loads the main fxml layout.
     * Sets up the vista switching VistaNavigator.
     * Loads the first vista into the fxml layout.
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
            getClass().getResourceAsStream(
                VistaNavigator.MAIN
            )
        );


        MainController mainController = loader.getController();

        VistaNavigator.setMainController(mainController);
        VistaNavigator.loadVista(VistaNavigator.DASHBOARD);


        return mainPane;
    }

    /**
     * Creates the main application scene.
     *
     * @param mainPane the main application layout.
     *
     * @return the created scene.
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
            mainPane
        );

        scene.getStylesheets().setAll(
            getClass().getResource("Vue/css/modena.css").toExternalForm()
        );

        return scene;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
