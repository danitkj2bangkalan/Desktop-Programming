import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class practice extends Application
{
   public static void main(String[] args){
        launch(args);
    }
    
    Button btn1,btn2;
        
    @Override public void start(Stage primaryStage){
        btn1 = new Button();
        btn2 = new Button();
        
        btn1.setText("Satu");
        btn1.setOnAction(e -> buttonClick());
        
        btn2.setText("Dua");
        btn2.setOnAction(e -> buttonClick2());
        
        BorderPane pane = new BorderPane();
        pane.setLeft(btn1);
        pane.setRight(btn2);
        
        Scene scene = new Scene(pane,300,250);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("The Click Me app");
        primaryStage.show();
    }
    
    public void buttonClick(){
            if(btn1.getText() == "Satu"){
                btn1.setText("Angka 1");
            }else if(btn1.getText() == "Angka 1"){
                btn1.setText("Satu");
            }
            else{
                btn1.setText("false button");
            }
    }
    
    public void buttonClick2(){
        if(btn2.getText() == "Dua"){
            btn2.setText("Angka 2");
        }
        else if(btn2.getText() == "Angka 2"){
            btn2.setText("Dua");
        }
        else{
             btn2.setText("false button");
        }
    }
}
