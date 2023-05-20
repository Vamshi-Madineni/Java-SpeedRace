package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.util.ResourceBundle;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class InstructionController implements Initializable {

    @FXML
    Button back;

    public void initialize(URL url, ResourceBundle resourceBundle){

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller.user=Instructions.user;
                Controller controller=new Controller();

                try{
                    controller.start(Instructions.stage);
                    Instructions.stage.centerOnScreen();
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });

    }

}
