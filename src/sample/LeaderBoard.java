package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
public class LeaderBoard extends Application {
    static Stage stage;
    static String user;

    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/leaderboard.fxml"));

        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Logged In As "+user);
        stage.show();
        stage.setResizable(false);


            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {

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
