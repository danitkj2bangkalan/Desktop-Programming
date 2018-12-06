import java.util.*;
import java.text.*;
import javafx.geometry.Pos;
import javafx.application.Application;
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
import java.lang.Math.*;
import java.io.*;
public class Praktikum8 extends Application
{
    public static void main(String[] args){
        launch(args);
    }
    Button Left,Right,Bottom,Top;
        
    @Override public void start(Stage primaryStage){
        Left = new Button("LEFT");
        Left.setOnAction(e -> L());
        Right = new Button("RIGHT");
        Right.setOnAction(e -> R());
        Top =  new Button("TOP");
        Top.setOnAction(e -> T());
        Bottom = new Button("BOTTOM");
        Bottom.setOnAction(e -> B());
        
        HBox Tbox = new HBox(Top);
        Tbox.setAlignment(Pos.CENTER);
        
        HBox Bbox = new HBox(Bottom);
        Bbox.setAlignment(Pos.CENTER);
        
        VBox Lbox = new VBox(Left);
        Lbox.setAlignment(Pos.CENTER);
        
        VBox Rbox = new VBox(Right);
        Rbox.setAlignment(Pos.CENTER);
        
        
        BorderPane pane = new BorderPane();
        pane.setLeft(Lbox);
        pane.setRight(Rbox);
        pane.setTop(Tbox);
        pane.setBottom(Bbox);
        
        Scene scene = new Scene(pane,300,250);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("The Click Me app");
        primaryStage.show();
    }
    
    public void T(){
        if(Top.getText() == "TOP"){
            Right.setText("Kanan");
        }else{
            Top.setText("TOP");
        }
    }
    public void R(){
        if(Right.getText()=="RIGHT"){
            Bottom.setText("Bawah");
        }else{
            Right.setText("RIGHT");
        }
    }
    public void L(){
        if(Left.getText() == "LEFT"){
            Top.setText("Atas");
        }else{
            Left.setText("LEFT");
        }
    }
    public void B(){
        if(Bottom.getText() == "BOTTOM"){
            Left.setText("Kiri");
        }else{
            Bottom.setText("BOTTOM");
        }
    }
}
