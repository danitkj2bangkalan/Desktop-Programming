/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pertemuan1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.stage.WindowEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;

/**
 * Write a description of JavaFX class  here.
 *
 * @author (MR Firdaus)
 * @version (a version number or a date)
 */
public class Pertemuan1 extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    ComboBox<String> choice;
    @Override
    public void start(Stage stage) throws Exception
    {
        // Create a Button or any control item
        Label lblCharacter = new Label("Pilih nama: ");
        lblCharacter.setMinWidth(100);
        // Create the Choice box
        choice = new ComboBox<String>();
        choice.setVisibleRowCount(4);
        choice.setEditable(true);
        // Create the OK button
        Button btnOK = new Button("OK");
        btnOK.setMinWidth(75);
        btnOK.setOnAction(e -> btnOK_Click());
        // Create the Button pane
        HBox paneButton = new HBox(20, lblCharacter, choice, btnOK);
        paneButton.setPadding(new Insets(10));
        paneButton.setAlignment(Pos.BOTTOM_LEFT);
        // Set the stage
        Scene scene = new Scene(paneButton);
        Stage primaryStage =  new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("SQLite Combo Box");
        Connection connection = null;
        // Show the Stage (window)
        try {
            // Membuat database connection
                connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30); // set timeout 30 detik.
                statement.executeUpdate("DROP TABLE IF EXISTS person");
                statement.executeUpdate("CREATE TABLE person (id INTEGER, name STRING)");
                String names[] = { "Andi", "Budi", "Cici", "Doni", "Enggar" };
                for (int i = 0; i < names.length; i++) {
                    statement.executeUpdate("INSERT INTO person values(' "+ (i + 1) + "', '" + names[i] + "')");
                }
                ResultSet resultSet = statement.executeQuery("SELECT * from person");
                while (resultSet.next()) {
                    choice.getItems().add(resultSet.getString("name"));
                }
            }
            catch (SQLException e) {
            System.err.println(e.getMessage());
            } finally {
                try {
                    if (connection != null)
                    connection.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
            primaryStage.show();
    }
    public void btnOK_Click() {
            String msg = "";
            if (choice.getValue() == null)
            msg += "Anda tidak memilih";
            else
            msg = "Anda memilih: " + choice.getValue();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("INFO");
            alert.setHeaderText("Message");
            alert.setContentText(msg);
            alert.showAndWait();
    }
    
}
