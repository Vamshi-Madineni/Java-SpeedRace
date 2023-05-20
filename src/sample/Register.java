package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register implements Initializable{

    @FXML
    ToggleButton register;
    @FXML
    ToggleButton login;
    @FXML
    Button save;
    @FXML
    Button go;
    @FXML
    TextField username;
    //TextField password;
    @FXML
    Button quit;
    @FXML
    TextField password;

    String jdbcUrl = "jdbc:mysql://localhost:3306/users";
    String dbUser = "root";
    String dbPassword = "";

    public void initialize(URL url, ResourceBundle resourceBundle){


        login.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                username.setDisable(false);
                password.setDisable(false);
                save.setDisable(true);
                go.setDisable(false);
            }
        });

        register.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                go.setDisable(true);
                password.setDisable(false);
                username.setDisable(false);
                save.setDisable(false);
            }
        });

        go.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                String user = username.getText();
                String pass = password.getText();



                try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                    String sql = "SELECT * FROM list WHERE username = ? AND password = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, user);
                    stmt.setString(2, pass);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        // Login successful, open the main window
                        Controller controller = new Controller();
                        Controller.user = user;
                        Stage stage = new Stage();
                        controller.start(stage);
                        stage.centerOnScreen();
                        FirstWindow.stage.close();
                    } else {
                        // Login failed, show an error message
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Incorrect Credentials");
                        alert.setHeaderText("Incorrect Input");
                        alert.setContentText("The username and password you provided is not correct.");
                        alert.showAndWait();
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });



        save.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                String user = "Admin";
                String pass = "123456789";

                if (username.getText().isEmpty() || password.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect Credentials");
                    alert.setHeaderText("Incorrect Input");
                    alert.setContentText("Input is incomplete.");
                    alert.showAndWait();
                } else {
                    user = username.getText();
                    pass = password.getText();

                    try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)){
                        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM list WHERE username = ?");
                        stmt.setString(1, user);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Incorrect Credentials");
                            alert.setHeaderText("Try a different username");
                            alert.setContentText("The Username Already Exists");
                            alert.showAndWait();
                        } else {
                            if (user.length() < 5) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Incorrect Credentials");
                                alert.setHeaderText("Invalid Username");
                                alert.setContentText("Usernames should be at least 5 characters long.");
                                alert.showAndWait();
                            } else if (!pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$")) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Incorrect Credentials");
                                alert.setHeaderText("Invalid Password");
                                alert.setContentText("Passwords should contain at least 1 small, 1 capital, 1 number, and 1 special character. Passwords should be at least 6 characters long. Only these @$!%*?& special characters are allowed.");
                                alert.showAndWait();
                            } else {
                                stmt = connection.prepareStatement("INSERT INTO list VALUES (?, ?,0)");
                                stmt.setString(1, user);
                                stmt.setString(2, pass);
                                stmt.executeUpdate();

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Done!!!");
                                alert.setHeaderText("Username is Saved!!!");
                                alert.setContentText("Thank you for registering!!!");
                                alert.showAndWait();
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });

        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FirstWindow.stage.close();
            }
        });
    }


}
