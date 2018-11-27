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
import java.lang.Math.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
public class practice1 extends Application
{
    // We keep track of the count, and label displaying the count:
    TextField Nama,Nim;
    Label lNama,lNim,lJenisKelamin,lAlamat,lTahunMasuk;
    ComboBox <String> TahunMasuk;
    RadioButton Lakilaki,Perempuan;
    Button Ok,Clear;
    TextArea Alamat;
    BorderPane pane;
    Stage data = new Stage();
    Label selectionLbl = new Label();
    Label selectionMsg = new Label();
    public static void main(String args[]){
        launch(args);
    }
    public void start(Stage stage) {
        lNama = new Label("Nama : ");
        lNim = new Label("NIM : ");
        lJenisKelamin = new Label("Jenis Kelamin : ");
        lAlamat = new Label("Alamat : ");
        lTahunMasuk = new Label("Tahun : ");
        // Label 
        Text header = new Text();
        header.setText("Form Mahasiswa");
        //header.setk
        // Header
        Ok = new Button("OK");
        Ok.setOnAction(a ->show(data));
        Clear = new Button("Clear");
        Clear.setOnAction(a ->Clear());
        
        // Button
        Lakilaki = new RadioButton("Laki - Laki");
        Perempuan = new RadioButton("Perempuan");
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(Lakilaki, Perempuan);
        group.selectedToggleProperty().addListener(new 
            ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
            final Toggle toggle, final Toggle new_toggle){
                String toggleBtn = ((ToggleButton)new_toggle).getText();
                selectionMsg.setText("Jenis Kelamin : "+toggleBtn);
            }
        });
        //Radio Button
        TahunMasuk = new ComboBox<>();
        TahunMasuk.getItems().addAll("2013","2014","2015","2016","2017","2018");
        TahunMasuk.getSelectionModel().selectFirst();
        TahunMasuk.getSelectionModel().selectedItemProperty().addListener(new 
            ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> ov, final String oldValuew, final String newValue){
                selectionLbl.setText("Tahun masuk anda : " + newValue);
            }  
        });
        // ComboBox untuk dropdown list
        Nama = new TextField();
        Nim = new TextField();
        // TextField untuk nim dan nama
        Alamat = new TextArea();
        // TextArea untuk alamat
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(lNama,0,1);
        grid.add(lNim,0,2);
        grid.add(lJenisKelamin,0,3);
        grid.add(lAlamat,0,4);
        grid.add(lTahunMasuk,0,5);
        grid.add(Nama,1,1,2,1);
        grid.add(Nim,1,2,2,1);
        grid.add(Lakilaki,1,3);
        grid.add(Perempuan,2,3);
        grid.add(Alamat,1,4,2,1);
        grid.add(TahunMasuk,1,5,2,1);
        HBox hheader = new HBox(header);
        hheader.setAlignment(Pos.CENTER);
        HBox ubox = new HBox(10,Ok,Clear);
        ubox.setAlignment(Pos.BOTTOM_RIGHT);
       
        pane = new BorderPane();
        pane.setCenter(grid);
        pane.setBottom(ubox);
        pane.setTop(hheader);
        Scene scene = new Scene(pane, 700, 400);
        stage.setTitle("TUGAS DI PAPAN");
        stage.setScene(scene);
        stage.show();
    }
    public void show(Stage data){
        Text tnama = new Text();
        tnama.setText("Nama : "+Nama.getText());
        Text tnim = new Text();
        tnim.setText("NIM : "+Nim.getText());
        Text talamat = new Text();
        talamat.setText("Alamat : "+Alamat.getText());
        Text tTahunMasuk = new Text();
        tTahunMasuk.setText(selectionLbl.getText());
        Text tJenisKelamin = new Text();
        tJenisKelamin.setText(selectionMsg.getText());
        VBox view = new VBox(10,tnama,tnim,tJenisKelamin,talamat,tTahunMasuk);
        BorderPane pane = new BorderPane();
        pane.setCenter(view);
        Scene scene = new Scene(pane,300,200);
        data.setTitle("List dari table sebelumnya");
        data.setScene(scene);
        data.show();
    }
    public void Clear(){
        Nama.setText("");
        Nim.setText("");
        Alamat.setText("");
    }
}
