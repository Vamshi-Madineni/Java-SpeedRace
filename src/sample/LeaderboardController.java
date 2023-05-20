package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class LeaderboardController implements Initializable {

    @FXML
    private TableView<Data> tableView;
    @FXML
    private TableColumn<Data, String> usernameColumn;
    @FXML
    private TableColumn<Data, Integer> gamesWonColumn;
    @FXML
    Button back;
    @FXML
    private Button loadData;

    public void initialize(URL url, ResourceBundle resourceBundle){

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        gamesWonColumn.setCellValueFactory(new PropertyValueFactory<>("gamesWon"));

        back.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Controller.user=LeaderBoard.user;
                Controller controller=new Controller();
                try{
                    controller.start(LeaderBoard.stage);
                    LeaderBoard.stage.centerOnScreen();
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });

        loadData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT username,gamesWon FROM list ORDER BY gamesWon DESC LIMIT 10");

                    tableView.getItems().clear();

                    while (rs.next()) {
                        String username  = rs.getString("username");
                        int gamesWon = rs.getInt("gamesWon");
                        tableView.getItems().add(new Data(username,gamesWon));
                    }

                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
