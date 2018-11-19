import java.util.*;
import java.text.*;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.effect.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.util.concurrent.ScheduledExecutorService;
import java.time.*;

public class pertemuan extends Application
{
    // We keep track of the count, and label displaying the count:
    public static void main(String args[]){
        launch(args);
    }
     
    Button Kotak,Jajar_Genjang,Tanggal_Sekarang,Jam_Sekarang;
    BorderPane pane;
    VBox vbox,vbox2;
    Text text;
    Calendar cal;
    DateFormat sdf;
    Rectangle persegi;
    Polyline jajarGenjang;
    
    public void start(Stage stage) {
        Calendar cal;
        DateFormat sdf;
        
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
        
        pane = new BorderPane();
        pane.setLeft(vbox);
        pane.setCenter(vbox2);
        
        Scene scene = new Scene(pane, 400, 200);
        stage.setTitle("Modul 6");
        stage.setScene(scene);
        stage.show();
    }
    
    public void Kotak(){
        text = new Text();
        text.setText("Kotak\n");
        text.setFont(new Font(20));
        text.setFill(Color.BLUE);
        
        persegi = new Rectangle();
        
        ColorInput warna = new ColorInput();
        
        warna.setX(50);
        warna.setY(50);
        warna.setHeight(50);
        warna.setWidth(50);
        warna.setPaint(Color.BLUE);
        persegi.setEffect(warna);
        
        Group grup = new Group(persegi);
        vbox2 = new VBox(text,grup);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(10));
        pane.setCenter(vbox2);
        
    }
    
    public void Jajar_Genjang(){
        text = new Text();
        text.setText("Jajar Genjang\n");
        text.setFont(new Font(20));
        text.setFill(Color.BLUE);
        
        jajarGenjang = new Polyline();
        jajarGenjang.getPoints().addAll(30.0, 0.0, 130.0, 0.0, 100.0, 50.0, 0.0, 50.0);
        jajarGenjang.setFill(Color.BLUE);
        
        Group grup = new Group(jajarGenjang);
        vbox2 = new VBox(text,grup);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(10));
        pane.setCenter(vbox2);
    }
    
    public void Tanggal_Sekarang(){
        DateFormat sdf = new SimpleDateFormat("E, dd/MM/yyyy");
        cal = Calendar.getInstance();
        
        text.setText(sdf.format(cal.getTime()));
        vbox2 = new VBox(text);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(10));
        
        pane.setCenter(vbox2);
    }
    
    public void Jam_Sekarang(){
        cal = Calendar.getInstance();
        
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        
        /*boolean state= true;
        while(state){
            if (detik == 59){
                detik=0;
                //detik++;
            }else if (menit == 59){
                menit=0;
                //menit++;
            }else if (jam == 23){
                jam = 0;
                //jam++;
            }else if(Jam_Sekarang.getText() != "Jam Sekarang"){
                state = false;
            }
            detik++;
            menit++;
            jam++;
        }**/
        
        text.setText(sdf.format(cal.getTime()));
        vbox2 = new VBox(text);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(10));
        
        pane.setCenter(vbox2);
    }
}
