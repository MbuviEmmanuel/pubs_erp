package pubs_erp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static Stage primaryStage;

    @FXML private AnchorPane login_pane, _login_pane, main_pane, admin_pane, cashier_pane;
    @FXML private Button clz, max, res, min;

    @FXML void windowControls(ActionEvent event) {
        if (event.getSource()==clz) System.exit(0);
        if (event.getSource()==max) primaryStage.setFullScreen(true);
        if (event.getSource()==min) primaryStage.setIconified(true);
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login_pane.toFront();
    }
}