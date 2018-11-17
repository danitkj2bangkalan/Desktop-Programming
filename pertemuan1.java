import java.util.*;
import java.text.*;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.collections.ObservableList;


public class pertemuan1 extends Application
{
    // We keep track of the count, and label displaying the count:
    public static void main(String args[]){
        launch(args);
     }
    Button Kotak,Jajar_Genjang,Tanggal_Sekarang,Jam_Sekarang;
    BorderPane pane;
    VBox vbox,vbox2;
    Text text;
    Line line,line2,line3,line4;
    public void start(Stage stage) {
        
        line = new Line();
        line2 = new Line();
        line3 = new Line();
        line4 = new Line();
        
        Kotak = new Button("Kotak");
        Jajar_Genjang = new Button("Jajar Genjang");
        Tanggal_Sekarang = new Button("Tanggal Sekarang");
        Jam_Sekarang = new Button("Jam Sekarang");
        
        Kotak.setMaxWidth(Double.MAX_VALUE);
        Jajar_Genjang.setMaxWidth(Double.MAX_VALUE);
        Tanggal_Sekarang.setMaxWidth(Double.MAX_VALUE);
        Jam_Sekarang.setMaxWidth(Double.MAX_VALUE);
        
        Kotak.setOnAction(e -> Kotak());
        Jajar_Genjang.setOnAction(e -> Jajar_Genjang());
        Tanggal_Sekarang.setOnAction(e -> Tanggal_Sekarang());
        Jam_Sekarang.setOnAction(e -> Jam_Sekarang());
        
        text = new Text();
        text.setText("Output");
        text.setX(10);
        text.setY(10);
        
        vbox = new VBox(10,Kotak,Jajar_Genjang,Tanggal_Sekarang,Jam_Sekarang);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        
        vbox2 = new VBox(text);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(10));
        //Group grup = new Group();
        pane = new BorderPane();
        pane.setLeft(vbox);
        pane.setCenter(vbox2);
        
        Scene scene = new Scene(pane, 400, 200);
        stage.setTitle("Modul 6");
        stage.setScene(scene);
        stage.show();
    }
    
    public void Kotak(){
        
        line = new Line();
        line2 = new Line();
        line3 = new Line();
        line4 = new Line();
   
        line.setStartX(15.0);
        line.setStartY(15.0);
        line.setEndX(50.0);
        line.setEndY(15.0);
        
        line2.setStartX(15.0);
        line2.setStartY(15.0);
        line2.setEndX(15.0);
        line2.setEndY(50.0);
        
        line3.setStartX(15.0);
        line3.setStartY(50.0);
        line3.setEndX(50.0);
        line3.setEndY(50.0);
        
        line4.setStartX(50.0);
        line4.setStartY(15.0);
        line4.setEndX(50.0);
        line4.setEndY(50.0);
        
        vbox2 = new VBox(line,line2,line3,line4);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(10));
        pane.setCenter(vbox2);
        
        
    }
    
    public void Jajar_Genjang(){
        
        line = new Line();
        line2 = new Line();
        line3 = new Line();
        line4 = new Line();
   
        line.setStartX(15.0);
        line.setStartY(15.0);
        line.setEndX(55.0);
        line.setEndY(15.0);
        
        line2.setStartX(15.0);
        line2.setStartY(15.0);
        line2.setEndX(5.0);
        line2.setEndY(50.0);
        
        line3.setStartX(5.0);
        line3.setStartY(50.0);
        line3.setEndX(45.0);
        line3.setEndY(50.0);
        
        line4.setStartX(55.0);
        line4.setStartY(15.0);
        line4.setEndX(45.0);
        line4.setEndY(50.0);
        
        vbox2 = new VBox(line,line2,line3,line4);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(10));
        pane.setCenter(vbox2);
    }
    
    
    public void Tanggal_Sekarang(){
        DateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        
        if(Tanggal_Sekarang.getText() == "Tanggal Sekarang"){
            text.setText(sd.format(cal.getTime()));
        }
    }
    
    public void Jam_Sekarang(){
        DateFormat sdf = new SimpleDateFormat("E, HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        
        if(Jam_Sekarang.getText() == "Jam Sekarang"){
            text.setText(sdf.format(cal.getTime()));
        }
    }
}
