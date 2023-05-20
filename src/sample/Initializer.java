package sample;

import com.sun.org.apache.bcel.internal.generic.Instruction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Initializer implements Initializable {

    @FXML
    ToggleButton  serverButton;
    @FXML
    ToggleButton clientButton;
    @FXML
    Button playButton;
    @FXML
    TextField textfield;
    @FXML
    Label label;
    @FXML
    Label created;
    @FXML
    ToggleButton quit;
    @FXML
    ToggleButton aboutus;
    @FXML
    ToggleButton instructions;

    @FXML
    ToggleButton leaderboard;

    public void initialize(URL url, ResourceBundle resourceBundle){

        label.setText(Controller.user);

        String entered=null, en=null;

        //String s=null;

        /*try{
            BufferedReader br=new BufferedReader(new FileReader("Created.txt"));
            s=br.readLine();

            br.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        if(s!=null){
            created.setText("Game is created, Join!");
        }*/

        /*try {
            BufferedReader br = new BufferedReader(new FileReader("Entered.txt"));
            br.readLine();
            entered=br.readLine();
            en=br.readLine();
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        if(entered!=null&&en==null){
            created.setText("Game is Created Join!");
        }*/

        serverButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                serverButton.setSelected(true);
                clientButton.setSelected(false);
                playButton.setDisable(false);
                textfield.setDisable(true);

            }
        });

        clientButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                /*String s=null;

                try{
                    BufferedReader br=new BufferedReader(new FileReader("Created.txt"));
                    s=br.readLine();

                    br.close();
                }
                catch(Exception e){
                    System.out.println(e);
                }

                if(s!=null){
                    created.setText("Game is created, Join!");
                }*/

               /* else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("OOps!");
                    alert.setContentText("Game is not created! Create New Game");
                    alert.showAndWait();
                }
*/
                serverButton.setSelected(false);
                clientButton.setSelected(true);
                playButton.setDisable(false);
                textfield.setDisable(false);
            }
        });
        playButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Main.server=serverButton.isSelected();
                Main.serverIPAddress=textfield.getText();
                Main obj=new Main();

                if(serverButton.isSelected()){
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("Created.txt"));
                        bw.write("Created");
                        bw.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }

                Stage stage=new Stage();

                try{
                    obj.start(stage);
                    stage.centerOnScreen();
                    Controller.stage.close();
                   // Controller.stage.centerOnScreen();
                }
                catch(Exception ex){

                }
            }
        });

        instructions.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Instructions.user=Controller.user;
                Instructions instructions=new Instructions();
                try{
                    instructions.start(Controller.stage);
                    Controller.stage.centerOnScreen();
                }
                catch(Exception exception){
                    System.out.println(exception);
                }
            }
        });

        aboutus.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                AboutUs.user=Controller.user;
                AboutUs aboutUs=new AboutUs();
                try{
                    aboutUs.start(Controller.stage);
                    Controller.stage.centerOnScreen();

                }catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });
        leaderboard.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                LeaderBoard.user = Controller.user;
                LeaderBoard leaderBoard = new LeaderBoard();
                try {
                    leaderBoard.start(new Stage());
                    Controller.stage.centerOnScreen();
                } catch(Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        quit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Controller.stage.close();
            }
        });
    }

}