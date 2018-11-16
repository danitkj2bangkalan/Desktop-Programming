import java.awt.Insets;
import javafx.collections.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * Write a description of JavaFX class fsae here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class fsae extends Application
{
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("GridPaneForm");
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 380,150, Color.WHITE);
            
            int a = 5;
            GridPane gridpane = new GridPane();
            //gridpane.setPadding(new Insets(15,12,10,180));
            gridpane.setHgap(5);
            gridpane.setVgap(5);
            ColumnConstraints column1 = new ColumnConstraints(100);
            ColumnConstraints column2 = new ColumnConstraints(50,150,300);
            column2.setHgrow(Priority.ALWAYS);
            gridpane.getColumnConstraints().addAll(column1,column2);
            
            Label fNameLbl = new Label("First Name");
            TextField fNameFld = new TextField();
            Label lNameLbl = new Label("Last Name");
            TextField lNameFld = new TextField();
            
            Button saveButton = new Button("Save");
            
            GridPane.setHalignment(fNameLbl, HPos.RIGHT);
            gridpane.add(fNameLbl,0,0);
            
            GridPane.setHalignment(lNameLbl, HPos.RIGHT);
            gridpane.add(lNameLbl,0,1);
            
            GridPane.setHalignment(fNameLbl, HPos.LEFT);
            gridpane.add(fNameLbl,1,0);
            
            GridPane.setHalignment(fNameLbl, HPos.LEFT);
            gridpane.add(fNameLbl,1,1);
            
            FlowPane topBanner = new FlowPane();
            topBanner.setPrefHeight(40);
            String backgroundStyle = "-fx-background-color: lightblue;"+"-fx-background-radius: 30%;"+"-fx-background-insert: 5px;";
            topBanner.setStyle(backgroundStyle);
            
            SVGPath svgIcon = new SVGPath();
            svgIcon.setContent("fd");
            svgIcon.setStroke(Color.LIGHTGRAY);
            svgIcon.setFill(Color.WHITE);
            
            Text contactText = new Text("Contacts");
            contactText.setFill(Color.WHITE);
            
            Font serif = Font.font("Dialog",30);
            contactText.setFont(serif);
            topBanner.getChildren().addAll(svgIcon,contactText);
            
            root.setTop(topBanner);
            root.setCenter(gridpane);
            
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }
        //public static void main(String[] args) {
            //launch(args);
        //}
}
