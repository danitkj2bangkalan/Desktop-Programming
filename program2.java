import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class no2 extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    ChoiceBox<String> choice;
    ListView<String> list;
    
    @Override
    public void start(Stage primaryStage) {
        // Create the label tittle
        Label lbh_choice = new Label("b. Choice Box");
        Label lbh_list = new Label("a. List View");
        
        // Create the Character
        Label lblCharacter = new Label("Pilih nama: ");
        lblCharacter.setMinWidth(100);
        Label lblCharacter2 = new Label("Pilih nama: ");
        lblCharacter2.setMinWidth(100);
        
        // Create the Choice box
        choice = new ChoiceBox<String>();
        //choice.setVisibleRowCount(4);
        //choice.setEditable(true);
        
        // Create the List view
        list = new ListView<String>();
        list.setEditable(true);
        list.setMaxHeight(140);
        
        // Create the OK button
        Button btnOK = new Button("OK");
        btnOK.setId("btnOK");
        btnOK.setMinWidth(75);
        btnOK.setOnAction(this::btnOK_Click);
        Button btnOK2 = new Button("OK");
        btnOK2.setId("btnOK2");
        btnOK2.setMinWidth(75);
        btnOK2.setOnAction(this::btnOK_Click);
        
        // Create the pane 1
        HBox pane1 = new HBox(20, lblCharacter, choice, btnOK);
        pane1.setPadding(new Insets(10));
        pane1.setAlignment(Pos.BOTTOM_LEFT);
        
        // Create the pane 2
        HBox pane2 = new HBox(20, lblCharacter2, list, btnOK2);
        pane2.setPadding(new Insets(10));
        
        // Create the Grid pane
        GridPane grid = new GridPane();   
        grid.setMinSize(500, 500);     
        grid.setPadding(new Insets(10, 10, 10, 10));            
        grid.setVgap(5);        
        grid.setHgap(5);                     
        grid.setAlignment(Pos.CENTER);   
        grid.add(lbh_list, 0, 0);
        grid.add(pane2, 0, 1);
        grid.add(lbh_choice, 0, 2);
        grid.add(pane1, 0, 3);
        
        
        // Set the stage
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SQLite Combo Box");
        
        Connection connection = null;
        try {
            // Membuat database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout 30 detik.
            
            statement.executeUpdate("DROP TABLE IF EXISTS person");
            statement.executeUpdate("CREATE TABLE person (id INTEGER, name STRING)");
            String names[] = { "Andi", "Budi", "Cici", "Doni", "Enggar" };
            
            for (int i = 0; i < names.length; i++) {
                statement.executeUpdate("INSERT INTO person values(' " + (i + 1) + "', '" + names[i] + "')");
            }
            
            ResultSet resultSet = statement.executeQuery("SELECT * from person");
            while (resultSet.next()) {
                // result for choice box
                choice.getItems().add(resultSet.getString("name"));
                
                // result for list view
                list.getItems().add(resultSet.getString("name"));
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
    
    public void btnOK_Click(ActionEvent ev) {
        String msg = "";
        String getId = ((Control)ev.getSource()).getId(); // get id from button
        
        // checking id button
        if(getId == "btnOK"){ 
            if (choice.getValue() == null)
                msg += "Anda tidak memilih";
            else
                msg = "Anda memilih: " + choice.getValue();
        }
        else if(getId == "btnOK2"){
            if (list.getSelectionModel().getSelectedItem() == null)
                msg += "Anda tidak memilih";
            else
                msg = "Anda memilih: " + list.getSelectionModel().getSelectedItem();
        }
            
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("INFO");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(msg);
 
        alert.showAndWait();
    }
}
