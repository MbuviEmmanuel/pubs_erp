package pubs_erp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static Stage primaryStage;

    @FXML private AnchorPane login_pane;
    @FXML private Label err_msg, forgot_pass;
    @FXML private TextField usr_id;
    @FXML private PasswordField usr_pass;
    @FXML private JFXCheckBox keep_loggedin;
    @FXML private JFXButton cancel, login;

    @FXML private AnchorPane main_pane;
    @FXML private Button clz, max, res, min;

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

        keep_loggedin.setOnAction(event -> {
            //TODO: Add implementation here
        });

        login.setOnAction(event -> {
            //TODO: Add implementation here
            main_pane.toFront();
        });

        forgot_pass.setOnMouseClicked(event -> {
            //TODO: Add implementation here
        });
    }

    @FXML void windowControls(ActionEvent event) {
        if (event.getSource()==clz || event.getSource()==cancel) System.exit(0);
        if (event.getSource()==max) primaryStage.setFullScreen(true);
        if (event.getSource()==min) primaryStage.setIconified(true);
    }
}