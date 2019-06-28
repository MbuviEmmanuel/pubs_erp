package pubs_erp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pubs_erp.controller.Controller;

public class Main extends Application {
    private static final Rectangle2D SCREEN_BOUNDS= Screen.getPrimary().getVisualBounds();
    private static double[] offset_XY;

    /**
     * The main entry point for our application. This method is called after the init method has returned, and after the
     * system is ready for the application to begin running.
     *
     * <p> NOTE: This method is called on the JavaFX Application Thread. </p>
     *
     * @param stage The primary stage for this application, onto which the application scene can be set. The primary
     *              stage will be embedded in the browser if the application was launched as an applet. Applications may
     *              create other stages, if needed, but they will not be primary stages and will not be embedded in the
     *              browser.
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("/view/GeneralUI.fxml"));

        root.setOnMousePressed( p-> offset_XY= new double[]{p.getSceneX(), p.getSceneY()});
        root.setOnMouseDragged(d-> {
            if (d.getScreenY()<(SCREEN_BOUNDS.getMaxY()-20)) stage.setY(d.getScreenY() - offset_XY[1]);
            stage.setX(d.getScreenX() - offset_XY[0]);
        });
        root.setOnMouseReleased(r-> {if (stage.getY()<0.0) stage.setY(0.0);});

        Controller.primaryStage= stage;

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/img/icons8_module_32px.png")));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Pubs ERP");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}