package sample;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FirstWindow extends Application {

    public static void main(String[] args)throws Exception {
        launch(args);
    }

    static Stage stage;

    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/register.fxml"));

        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Join the Fun - Register/Login");
        primaryStage.getIcons().add(new Image(FirstWindow.class.getResourceAsStream("resources/logo.png")));
        stage.show();
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
}
