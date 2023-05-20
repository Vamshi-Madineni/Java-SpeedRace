package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.WindowEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Controller extends Application{

    static Stage stage;

    static String user=null;

    @Override
    public void start(Stage primaryStage)throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Logged in as, "+user);

        primaryStage.getIcons().add(new Image(Controller.class.getResourceAsStream("resources/logo.png")));

        primaryStage.show();
        stage = primaryStage;
        stage.setResizable(false);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {



            @Override
            public void handle(WindowEvent event) {

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Created.txt"));
                    bw.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Entered.txt"));
                    bw.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                System.exit(1);
            }
        });
    }

    public static void main(String [] args){
        launch(args);
    }

}
