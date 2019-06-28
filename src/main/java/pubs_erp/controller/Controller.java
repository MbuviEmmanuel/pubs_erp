package pubs_erp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
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

    Connection connection = null;
    ResultSet rs;
    PreparedStatement pst;


   /* public static int All_UserID = 0;
    public static int All_OwnerID = 0;
    public static String All_FullName;
*/
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
            //main_pane.toFront();

            if(usr_id.getText().isEmpty() && usr_pass.getText().isEmpty()){

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Username And Password Empty");
                alert.setContentText("");
                alert.showAndWait();
            }
            else if(usr_id.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Username Empty");
                alert.setContentText("");
                alert.showAndWait();

            }else if(usr_pass.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Password is Empty");
                alert.setContentText("");
                alert.showAndWait();

            }else {
                String username = usr_id.getText();
                String password = usr_pass.getText();

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost/pub", "root", "Emmambu6&");
                    pst = connection.prepareStatement("select * from user where usename=? and password =?");

                    pst.setString(1, username);
                    pst.setString(2, password);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        FXMLLoader loader;
                        if (usr_pass.getText().equals(rs.getString("password"))) {
                            loader = new FXMLLoader(getClass().getResource("/GeneralUI.fxml"));
                            loader.setController(new Controller());
                            main_pane.toFront();

                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText("Wrong Password! Try Again.");
                            alert.setContentText("");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Invalid User Name!");
                        alert.setContentText("");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {

                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Connection Failed.");
                    alert.setContentText("");
                    alert.showAndWait();

                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

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