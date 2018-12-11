import java.util.*;
import java.text.*;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.effect.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.lang.Math.*;
import java.io.*;

/**
 * Write a description of JavaFX class Login here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Login extends Application
{
    // We keep track of the count, and label displaying the count:
    Stage spelanggan = new Stage();
    Stage sadmin = new Stage();
    public void start(Stage stage) throws Exception
    {
        Text Login = new Text("Login Reparasi");
        Login.setStyle("-fx-font-size  : 14pt;"+
                        "-fx-font-family: Tahoma;"+
                        "-fx-base       : #DFB951;"+
                        "-fx-background : #A78732;"+
                        "-fx-focus-color: #B6A678;");
        // Create a Button or any control item
        Button Admin = new Button("Admin");
        Admin.setOnAction(e -> Administrator(sadmin));
        Button Pelanggan = new Button("Pelanggan");
        Pelanggan.setOnAction(e -> User(spelanggan));
        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);
        HBox hb = new HBox(10,Admin,Pelanggan);
        
        //set an action on the button using method reference
        //myButton.setOnAction(this::buttonClick);

        // Add the button and label into the pane
        pane.add(Login, 0, 0);
        pane.add(hb, 0, 1);
        
        BorderPane bp = new BorderPane();
        bp.setCenter(pane);
        bp.setStyle("-fx-background-color: Green;");
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(bp, 300,100);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    }
    public void Administrator(Stage stage){
        Text tx = new Text("admin");
        HBox hb = new HBox(10,tx);
        Scene scene = new Scene(hb, 300,100);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);

        // Show the Stage (window)
        sadmin.show();
    }
    public void User(Stage stage){
        Text tx = new Text("pelanggan");
        HBox hb = new HBox(10,tx);
        Scene scene = new Scene(hb, 300,100);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);

        // Show the Stage (window)
        spelanggan.show();
    }
}
