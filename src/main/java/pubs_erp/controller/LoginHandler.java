package pubs_erp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginHandler implements Initializable {

    @FXML private Label err_msg, forgot_pass;
    @FXML private TextField usr_id;
    @FXML private PasswordField usr_pass;
    @FXML private JFXCheckBox keep_loggedin;
    @FXML private JFXButton cancel, login;

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override public void initialize(URL location, ResourceBundle resources) {
        cancel.setOnAction(event -> System.exit(0));

        //TODO: keep_loggedin.setOnAction(event -> {});
        //TODO: login.setOnAction(event -> {});
        //TODO: forgot_pass.setOnMouseClicked(event -> {});
    }
}