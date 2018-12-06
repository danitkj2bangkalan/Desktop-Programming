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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.effect.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.util.concurrent.ScheduledExecutorService;
import java.time.*;
import javafx.stage.WindowEvent;
import javafx.stage.*;
import javafx.scene.chart.*;
/**
 * Write a description of JavaFX class Praktimkum9 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Praktimkum9 extends Application
{
    public static void main(String args[]){
        launch(args);
    }
     
    Button Datadiri,Riwayatpend,Matkul,Motivasi,GrafikNilaiSem,Keluar;
    BorderPane pane;
    VBox vbox,vbox2;
    Text text;
    Line line,line2,line3,line4;
    Calendar cal;
    DateFormat sdf;
    Rectangle persegi;
    Polyline jajarGenjang;
    GridPane gp;
    Stage mainStage;
    public void start(Stage stage) {
        this.mainStage = stage;
        Datadiri = new Button("Data diri");
        Datadiri.setOnAction(e -> DataDiri());
        
        Riwayatpend = new Button("Riwayat pendidikan");
        Riwayatpend.setOnAction(e -> RiwayatPendidikan());
        
        Matkul = new Button("Mata kuliah yang \n        diambil");
        Matkul.setOnAction(e -> MataKuliah());
        
        Motivasi = new Button("Motivasi");
        Motivasi.setOnAction(e -> show());
        
        GrafikNilaiSem = new Button("Grafik nilai semester");
        GrafikNilaiSem.setOnAction(e -> GrafikNilaiSemester());
        
        this.mainStage = stage;
        stage.setOnCloseRequest(confirmCloseEventHandler);

        Keluar = new Button("Keluar");
        Keluar.setOnAction(event ->
                stage.fireEvent(
                        new WindowEvent(
                                stage,
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                )
        );
        
        
        
        Datadiri.setMaxWidth(Double.MAX_VALUE);
        Riwayatpend.setMaxWidth(Double.MAX_VALUE);
        Matkul.setMaxWidth(Double.MAX_VALUE);
        Motivasi.setMaxWidth(Double.MAX_VALUE);
        GrafikNilaiSem.setMaxWidth(Double.MAX_VALUE);
        Keluar.setMaxWidth(Double.MAX_VALUE);
        
        
        text = new Text();
        text.setText(" ");
        text.setX(10);
        text.setY(10);
        
        vbox = new VBox(10,Datadiri,Riwayatpend,Matkul,Motivasi,GrafikNilaiSem,Keluar);
        vbox.setMargin(Keluar, new Insets(0,0,40,0));
        vbox.setMargin(Datadiri, new Insets(20,0,0,0));
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        
        vbox2 = new VBox(text);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(10));
        
        pane = new BorderPane();
        pane.setLeft(vbox);
        pane.setCenter(vbox2);
        
        Scene scene = new Scene(pane, 500, 300);
        stage.setTitle("Modul 6");
        stage.setScene(scene);
        stage.show();
    }
    public void DataDiri(){
        Label lNama = new Label("Nama");
        Label lNim = new Label("Nim");
        Label jk = new Label("Jenis Kelamin");
        Label ttl = new Label("Tempat dan Tanggal Lahir");
        Label ljurusan = new Label("Jurusan / Prodi");
        Text tNama = new Text(": Moh. Romadhani Firdaus");
        Text tNim = new Text(": 170411100107");
        Text tjk = new Text(": Laki - Laki");
        Text textl = new Text(": Bangkalan,6/1/1999");
        Text tjurusan = new Text(": Teknik Informatika");
        
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.add(lNama,0,0);
        gp.add(lNim,0,1);
        gp.add(jk,0,2);
        gp.add(ttl,0,3);
        gp.add(ljurusan,0,4);
        gp.add(tNama,1,0);
        gp.add(tNim,1,1);
        gp.add(tjk,1,2);
        gp.add(textl,1,3);
        gp.add(tjurusan,1,4);
        
        pane.setCenter(gp);
    }
    public void RiwayatPendidikan(){
        Label lNama = new Label("Taman Kanak-Kanak");
        Label lNim = new Label("Sekolah Dasar");
        Label jk = new Label("Sekolah Menengah Pertama");
        Label ttl = new Label("Sekolah Menengah Kejuruan");
        Label ljurusan = new Label("Universitas");
        Text tNama = new Text(": TK YKK");
        Text tNim = new Text(": SDN PANGERANAN 03");
        Text tjk = new Text(": SMPN 3 BANGKALAN");
        Text textl = new Text(": SMKN 2 BANGKALAN");
        Text tjurusan = new Text(": UNIVERSITAS TRUNOJOYO MADURA");
        
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.add(lNama,0,0);
        gp.add(lNim,0,1);
        gp.add(jk,0,2);
        gp.add(ttl,0,3);
        gp.add(ljurusan,0,4);
        gp.add(tNama,1,0);
        gp.add(tNim,1,1);
        gp.add(tjk,1,2);
        gp.add(textl,1,3);
        gp.add(tjurusan,1,4);
        
        pane.setCenter(gp);
    }
    public void MataKuliah(){
        Text pbo = new Text("Pemrograman Berorientasi Objek");
        Text pemdesk = new Text("Pemrograman Dekstop");
        Text Matdis = new Text("Matematika Diskrit");
        Text Basdat = new Text("Basis Data");
        Text Mulmed = new Text("Multimedia");
        Text so = new Text("System Operasi");
        VBox box = new VBox(10,pbo,pemdesk,Matdis,Basdat,Mulmed,so);
        box.setAlignment(Pos.CENTER_LEFT);
        pane.setCenter(box);
    }
    public void show(){
        Text motivasi = new Text("Positive Thingking and Never Give Up");
        pane.setCenter(motivasi);
    }
    public void GrafikNilaiSemester(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mata Kuliah");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Grafik Nilai Semester Ganjil, 2017");
         XYChart.Series series = new XYChart.Series();
        series.setName("Nilai");
        series.getData().add(new XYChart.Data("Struktur Data", 65));
        series.getData().add(new XYChart.Data("Fisika", 90));
        series.getData().add(new XYChart.Data("Statistik", 40));
        series.getData().add(new XYChart.Data("Dasar Pemrograman Web", 70));
        series.getData().add(new XYChart.Data("Komputasi Aljabar Linier", 75));
        series.getData().add(new XYChart.Data("Kewarganegaraan", 90));
        series.getData().add(new XYChart.Data("Organisasi Komputer", 70));        
        lineChart.getData().add(series);
        pane.setCenter(lineChart);
    }
    private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Apakah anda yakin untuk keluar?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Keluar");
        closeConfirmation.setHeaderText("Konfirmasi Keluar");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(mainStage);

        closeConfirmation.setX(mainStage.getX());
        closeConfirmation.setY(mainStage.getY() + mainStage.getHeight());

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };
}
