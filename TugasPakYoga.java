/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugaspakyoga;

import javafx.application.Application;  
import javafx.geometry.Insets;  
import javafx.geometry.Pos;  
import javafx.scene.Scene;  
import javafx.scene.control.Button;  
import javafx.scene.control.PasswordField;    
import javafx.scene.layout.GridPane;  
import javafx.scene.text.Text;  
import javafx.scene.control.TextField;  
import javafx.stage.Stage;   
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
/**
 *
 * @author Dani
 */

/**
 *
 * @author mrfirdaus
 */
public class TugasPakYoga extends Application {
    
    Stage stage ;
    TextField textField1;
    PasswordField textField2;
    ResultSet resultSet1,resultSet2;
    String pas,surel;
    
    @Override
    public void start(Stage primaryStage) {
      stage = new Stage();
      
      Text text1 = new Text("Email");        
      //creating label password  
      Text text2 = new Text("Password");  
      //Creating Text Filed for email         
      textField1 = new TextField();      
      //Creating Text Filed for password         
      textField2 = new PasswordField();   
      //Creating Buttons  
      Button button1 = new Button("Submit"); 
      button1.setOnAction(e -> check());
      Button button2 = new Button("Clear");  
      button2.setOnAction(e ->clear());
      //Creating a Grid Pane  
      GridPane gridPane = new GridPane();     
      //Setting size for the pane  
      gridPane.setMinSize(400, 200);  
      //Setting the padding   
      gridPane.setPadding(new Insets(10, 10, 10, 10));  
      //Setting the vertical and horizontal gaps between the columns  
      gridPane.setVgap(5);  
      gridPane.setHgap(5);        

      gridPane.setAlignment(Pos.CENTER);  
      //Arranging all the nodes in the grid  
      gridPane.add(text1, 0, 0);  
      gridPane.add(textField1, 1, 0);  
      gridPane.add(text2, 0, 1);        
      gridPane.add(textField2, 1, 1);  
      gridPane.add(button1, 0, 2);  
      gridPane.add(button2, 1, 2);  
      //Styling nodes   
      button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");  
      button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");  
      text1.setStyle("-fx-font: normal bold 20px 'serif' ");  
      text2.setStyle("-fx-font: normal bold 20px 'serif' ");   
      gridPane.setStyle("-fx-background-color: BEIGE;");  
      //Creating a scene object  
      Scene scene = new Scene(gridPane);  
      //Setting title to the Stage  
      stage.setTitle("Tugas SQLite");  
      //Adding scene to the stage  
      stage.setScene(scene); 
      //Displaying the contents of the stage  
      Connection koneksi = null;
      try{
          
          String mail = "terserah@gmail.com";
         
          String p = "tugassql";
          
          koneksi = DriverManager.getConnection("jdbc:sqlite:contoh.db");
          Statement pernyataan = koneksi.createStatement();
          pernyataan.setQueryTimeout(30);
          pernyataan.executeUpdate("DROP TABLE IF EXISTS data");
          pernyataan.executeUpdate("CREATE TABLE data (Password STRING,email STRING)");
          pernyataan.executeUpdate("INSERT INTO data values(' "+ p + "', '" + mail + "')");
          resultSet1 = pernyataan.executeQuery("SELECT * from data");
          while(resultSet1.next()){
              pas = resultSet1.getString("Password");
              surel = resultSet1.getString("email");
          }
          
      }catch (SQLException e) {
            System.err.println(e.getMessage());
            } finally {
                try {
                    if (koneksi != null)
                    koneksi.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
      stage.show();  
    }
    public void clear(){
        textField1.setText("");
        textField2.setText("");
    }
    public void check(){
        String a = pas; 
        String b = textField2.getText();
        //char[] pasword = textField2.getPassword();
        String pesan ;
        if (textField1.getText().equals(surel) & a.equals(" "+b)){
            pesan = "Login berhasil ";
        }else{
            pesan = "Login gagal ";
        }
        Alert notif = new Alert(AlertType.INFORMATION);
        notif.setTitle("INFO");
        notif.setHeaderText("Message");
        notif.setContentText(pesan);
        notif.showAndWait();
    }
    public static void main(String[] args) {
        TugasPakYoga a = new TugasPakYoga();
        System.out.println("Berikut Email dan Password yang benar :\nEmail : terserah@gmail.com\nPassword : tugassql");
        launch(args);
    }
}
