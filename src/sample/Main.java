package sample;

import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class Main extends Application {

    Stage stage;
    boolean gamevar=true;
    static boolean server;
    DataOutputStream dos;
    DataInputStream dis;
    AnchorPane anchorPane;
    public int width=1016,height=751;
    Timeline timeline;
    Bike bike1;
    Bike bike2;
    Double x=0.0,y=0.0;
    boolean keyPressed = false;
    KeyCode keyPressedCode = null;
    static String serverIPAddress="127.0.0.1";
    int carPassed=0;
    int carPassedVar=0;
    int youHavePassed=0;
    int opponetPassed=0;
    String usermain="No Opponent";
    int car4Y=-500, car5Y=-2500;
    static boolean connected=false;
    boolean startvar=false;
    double carSpeed=0;
    double carSpeed2=0;
    boolean var1=true,var2=true,var3=true;
    int count1=0;
    boolean withcar2=false,withcar3=false;
    int numbOfLaps=0;
    boolean alreadyReached=false;
    boolean youReached=false;
    int controll=1;
    double timeneeded=0;
    boolean timecount=false;
    ///boolean oppotimecount=false;
    boolean texfielddis=false;
    boolean buttoncntrol=false;
    int showingtime=5;
    int mili=0;
    int lapdistance=10000;
    Text status;
    boolean carrrrrrr=true;

    /*
    status=new Text();
    status.setText(message);
    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), this);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(0.5);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(2);
        fadeTransition.play();
*/
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;

        anchorPane=new AnchorPane();


        Image image=new Image(Main.class.getResourceAsStream("resources/road.png"));
        ImageView imageView=new ImageView(image);

        ImageView finishingLine=new ImageView(new Image(Main.class.getResourceAsStream("resources/finishing.png")));
        finishingLine.setLayoutX(192);
        finishingLine.setLayoutY(-500);

        ImageView lapLine=new ImageView(new Image(Main.class.getResourceAsStream("resources/lapline.png")));
        lapLine.setLayoutX(192);
        lapLine.setLayoutY(-500);

        ImageView showLine=new ImageView(new Image(Main.class.getResourceAsStream("resources/showLine.png")));
        showLine.setLayoutX(745);
        showLine.setLayoutY(0);

        ImageView carArrow1=new ImageView(new Image(Main.class.getResourceAsStream("resources/bike1_indicator.png")));
        carArrow1.setLayoutX(742.5);
        double carArrow1val=600-(1.0*youHavePassed)/(numbOfLaps*lapdistance)*600.0;
        carArrow1.setLayoutY(600);

        ImageView carArrow2=new ImageView(new Image(Main.class.getResourceAsStream("resources/bike2_indicator.png")));
        carArrow2.setLayoutX(745);
        double carArrow1val2=600-(1.0*opponetPassed)/(numbOfLaps*lapdistance)*600.0;
        carArrow2.setLayoutY(600);

        ImageView imageviewtree1=new ImageView(new Image(Main.class.getResourceAsStream("resources/flow4.png")));

        ImageView imageviewcar4=new ImageView(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
        imageviewcar4.setX(390);
        imageviewcar4.setY(-500);
        ImageView imageviewcar4arr=new ImageView(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
        imageviewcar4arr.setX(imageviewcar4.getX());
        imageviewcar4arr.setY(-500);

        ImageView imageviewcar5arr=new ImageView(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));

        imageviewcar5arr.setY(-500);

        ImageView imageviewcar5=new ImageView(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
        imageviewcar5.setY(-2500);
        imageviewcar5.setX(240);
        imageviewcar5arr.setX(imageviewcar5.getX());


        imageviewtree1.setX(610);
        //imageviewtree1.setY(-100);

        ImageView imageviewtree2=new ImageView(new Image(Main.class.getResourceAsStream("resources/flow4.png")));
        ImageView imageviewtree3=new ImageView(new Image(Main.class.getResourceAsStream("resources/flow3.png")));
        imageviewtree3.setX(610);
/*


        imageviewtree3.setY(-100);
*/

        ImageView imageviewtree5=new ImageView(new Image(Main.class.getResourceAsStream("resources/flow1.png")));
        ImageView imageviewtree6=new ImageView(new Image(Main.class.getResourceAsStream("resources/flow2.png")));
        imageviewtree5.setX(610);
        imageviewtree6.setX(50);

      /*
        imageviewtree6.setY(-100);
        imageviewtree5.setY(-100);

*/
        ImageView imageviewtree4=new ImageView(new Image(Main.class.getResourceAsStream("resources/flow5.png")));
        imageviewtree4.setX(50);
      /*
        imageviewtree4.setY(-100);*/

        imageviewtree1.setY(carPassedVar - 600);
        imageviewtree2.setY(carPassedVar - 600);
        imageviewtree3.setY(carPassedVar - 100);
        imageviewtree4.setY(carPassedVar - 100);

        double pos = imageviewtree3.getY();

        if (pos >= -100 && pos <= 800) {
            imageviewtree6.setY(pos + 500);
            imageviewtree5.setY(pos + 500);
        } else {
            imageviewtree6.setY(imageviewtree1.getY() - 500);
            imageviewtree5.setY(imageviewtree1.getY() - 500);
        }


       /* ImageView imageViewhouse1=new ImageView(new Image(Main.class.getResourceAsStream("house1.png")));
        imageViewhouse1.setX(567);
        imageViewhouse1.setY(-500);*/

        imageviewtree2.setX(50);

        anchorPane.getChildren().addAll(imageView,imageviewtree1,imageviewtree2,imageviewtree3,imageviewtree4,imageviewtree6,imageviewtree5);

        imageView.setPreserveRatio(true);

        primaryStage.setTitle("Speed Race!");

        Scene scene=new Scene(anchorPane, width,height);

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("resources/logo.png")));

        primaryStage.setScene(scene);

        primaryStage.show();
        stage.setResizable(false);



        bike1 =new Bike(server,height-150);
        bike2 =new Bike(!server,height-150);



        Label label1=new Label("No Opponent");
        Label label2=new Label("No Opponent");

        int s=(int)carSpeed;
        Label label4=new Label(Integer.toString(s));
        Label label5=new Label("0");
        Label label6=new Label("Speed");

        label6.setLayoutY(170);
        label6.setLayoutX(800);

        label4.setLayoutX(780);
        label4.setLayoutY(210);
        label5.setLayoutX(780);
        label5.setLayoutY(280);

        label1.setText(Controller.user);

        label1.setLayoutX(870);
        label2.setLayoutX(870);
        label1.setLayoutY(200);

        label2.setLayoutY(280);
        label1.setTextFill(Color.FLORALWHITE);
        label2.setTextFill(Color.FLORALWHITE);
        label4.setTextFill(Color.BLUEVIOLET);
        label5.setTextFill(Color.BLUEVIOLET);
        label6.setTextFill(Color.BLUEVIOLET);

        label1.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.THIN,30));
        label2.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.THIN,30));
        label4.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.EXTRA_BOLD,30));
        label5.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.EXTRA_BOLD,30));
        label6.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.EXTRA_BOLD,30));

        TextArea textArea=new TextArea();
        textArea.setLayoutX(767.0);
        textArea.setLayoutY(50);
        textArea.setPrefWidth(1200-767);
        textArea.setPrefHeight(80);
        anchorPane.getChildren().add(textArea);
        textArea.appendText("Distance covered by YOU: ");
        textArea.appendText(Integer.toString(youHavePassed)+"m\n");
        textArea.appendText("Distance covered by " + usermain + ": ");
        textArea.appendText(Integer.toString(opponetPassed)+"m");
        textArea.setEditable(false);
        textArea.setDisable(true);
        textArea.setOpacity(5);
        textArea.fontProperty().set(Font.font(Font.getDefault().getStyle(), FontWeight.EXTRA_BOLD,15));
        Label label=new Label("Live Status");
        label.setLayoutY(0);
        label.setLayoutX(800);
        label.setTextFill(Color.YELLOW);
        label.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.EXTRA_BOLD, 36));

        Label labelspeed=new Label();
        labelspeed.setLayoutY(12);
        labelspeed.setLayoutX(390);
        labelspeed.setTextFill(Color.GREENYELLOW);
        labelspeed.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.EXTRA_BOLD, 36));

        Label labellap=new Label("Enter Laps:");
        labellap.setLayoutX(800);
        labellap.setLayoutY(410);
        labellap.setTextFill(Color.GOLD);
        labellap.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.EXTRA_BOLD, 36));

        Label timeshow=new Label();
        timeshow.setLayoutX(250);
        timeshow.setLayoutY(400);
        timeshow.setTextFill(Color.RED);
        timeshow.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.EXTRA_BOLD,55));

        Label winlos=new Label("");
        winlos.setLayoutX(250);
        winlos.setLayoutY(400);
        winlos.setTextFill(Color.GOLD);
        winlos.setFont(Font.font(Font.getDefault().getStyle(),FontWeight.EXTRA_BOLD, 25));

        TextField textFieldlap=new TextField();
        textFieldlap.setLayoutX(800);
        textFieldlap.setLayoutY(460);
        ///textFieldlap.setMaxWidth(170);
        textFieldlap.setPromptText("Num of Laps");


        Button startbutton=new Button("Start");
        startbutton.setLayoutX(860);
        startbutton.setLayoutY(490);
        //  startbutton.setPrefHeight(50);
        //startbutton.setPrefWidth(110);
        int val=(int)carSpeed;
        labelspeed.setText(Integer.toString(val));

        status=new Text();

        anchorPane.getChildren().addAll(finishingLine,lapLine, bike1.getImageView(), bike2.getImageView(),showLine,labellap,carArrow1,carArrow2,timeshow,textFieldlap,imageviewcar5,imageviewcar5arr,winlos,imageviewcar4,imageviewcar4arr,label,labelspeed,label1,label2,label4,label5,label6,startbutton);

        anchorPane.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event) {
                keyPressed=true;
                keyPressedCode=event.getCode();
            }
        });

        anchorPane.setOnKeyReleased(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event) {
                keyPressed=false;
            }
        });

        timeline=new Timeline(new KeyFrame(Duration.millis(25.00), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                startbutton.setOnAction(event1 -> {

                    if(textFieldlap.getText().isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("No Input!");
                        alert.setHeaderText("Lap numbers not fixed!!!");
                        alert.setContentText("Please input lap numbers.");
                        alert.showAndWait();
                    }

                    else {
                        numbOfLaps=Integer.valueOf(textFieldlap.getText());
                        //timecount=true;
                        textFieldlap.setDisable(true);
                        texfielddis=true;
                        startvar = true;
                        buttoncntrol=true;
                    }
                });

                if(texfielddis){
                    textFieldlap.setDisable(true);
                }

                if(numbOfLaps!=0)
                    textFieldlap.setText(Integer.toString(numbOfLaps));

                if (startvar) {


                    if (keyPressed) {

                        if (keyPressedCode == KeyCode.LEFT) {

                            if(Math.abs(imageviewcar4.getY()-600)<=94){
                                if(imageviewcar4.getX()> bike1.getX()){
                                    if (bike1.getX() >= 193) {
                                        bike1.setX(bike1.getX() - 15);
                                    }
                                }
                                else if(Math.abs(imageviewcar4.getX()- bike1.getX())>=57.5){
                                    bike1.setX(bike1.getX() - 15);
                                }
                                else{
                                    if(carSpeed>=1) {

                                        // carSpeed -= 1;
                                    }
                                    else{
                                        carSpeed=0;
                                    }
                                }
                            }

                            else if(Math.abs(imageviewcar5.getY()-600)<=94){
                                if(imageviewcar5.getX()> bike1.getX()){
                                    if (bike1.getX() >= 193) {
                                        bike1.setX(bike1.getX() - 15);
                                    }
                                }
                                else if(Math.abs(imageviewcar5.getX()- bike1.getX())>=57.5){
                                    bike1.setX(bike1.getX() - 15);
                                }
                                else{
                                    if(carSpeed>=1) {

                                        // carSpeed -= 1;
                                    }
                                    else{
                                        carSpeed=0;
                                    }
                                }
                            }

                            else if (Math.abs(youHavePassed - opponetPassed) <= 94) {
                                if (x > bike1.getX()) {
                                    if (bike1.getX() >= 193) {
                                        bike1.setX(bike1.getX() - 15);
                                    }
                                } else if ((bike1.getX() - x) > 57.5) {
                                    bike1.setX(bike1.getX() - 15);
                                } else {

                                    if(carSpeed>=1) {

                                        // carSpeed -= 1;
                                    }
                                    else{
                                        carSpeed=0;
                                    }
                                }
                            } else if (bike1.getX() >= 193) {
                                bike1.setX(bike1.getX() - 15);
                            }
                        }
                        if (keyPressedCode == KeyCode.RIGHT) {

                            if(Math.abs(imageviewcar4.getY()-600)<=94){
                                if(imageviewcar4.getX()< bike1.getX()){
                                    if (bike1.getX() <= 510) {
                                        bike1.setX(bike1.getX() + 15);
                                    }
                                }
                                else if(Math.abs(imageviewcar4.getX()- bike1.getX())>=57.5){
                                    bike1.setX(bike1.getX() + 15);
                                }
                                else{
                                    if(carSpeed>=1) {

                                        // carSpeed -= 1;
                                    }
                                    else{
                                        carSpeed=0;
                                    }
                                }
                            }

                            else if(Math.abs(imageviewcar5.getY()-600)<=94){
                                if(imageviewcar5.getX()< bike1.getX()){
                                    if (bike1.getX() <= 510) {
                                        bike1.setX(bike1.getX() + 15);
                                    }
                                }
                                else if(Math.abs(imageviewcar5.getX()- bike1.getX())>=57.5){
                                    bike1.setX(bike1.getX() + 15);
                                }
                                else{
                                    if(carSpeed>=1) {

                                        // carSpeed -= 1;
                                    }
                                    else{
                                        carSpeed=0;
                                    }
                                }
                            }

                            else if (Math.abs(youHavePassed - opponetPassed) <= 94) {
                                if (x < bike1.getX()) {
                                    if (bike1.getX() <= 510) {
                                        bike1.setX(bike1.getX() + 15);
                                    }
                                } else if (Math.abs(x - bike1.getX()) > 57.5) {
                                    bike1.setX(bike1.getX() + 15);
                                } else {
                                    if(carSpeed>=1) {

                                        //arSpeed -= 1;
                                    }
                                    else{
                                        carSpeed=0;
                                    }
                                }
                            } else if (bike1.getX() <= 510) {
                                bike1.setX(bike1.getX() + 15);
                            }
                        }


                        if (keyPressedCode == KeyCode.UP) {

                            if(Math.abs(imageviewcar5.getX()- bike1.getX())<=50){
                                if(imageviewcar5.getY()>600){
                                    if(carSpeed<60){
                                        carSpeed+=.20;
                                    }
                                }
                                else if(Math.abs(car5Y-600)>=110){
                                    if(carSpeed<60) {
                                        carSpeed += .20;
                                    }
                                }
                                else{
                                    carSpeed=0;
                                    var3=false;
                                }
                            }

                            if(Math.abs(imageviewcar4.getX()- bike1.getX())<=50){
                                if(imageviewcar4.getY()>600){
                                    if(carSpeed<60){
                                        carSpeed+=.20;
                                    }
                                }
                                else if(Math.abs(car4Y-600)>=110){
                                    if(carSpeed<60) {
                                        carSpeed += .20;
                                    }
                                }
                                else{
                                    carSpeed=0;
                                    var1=false;
                                }
                            }

                            if (Math.abs(x - bike1.getX()) <= 50) {
                                if (youHavePassed > opponetPassed) {

                                    if(carSpeed<60){
                                        carSpeed+=.20;
                                    }
                                } else if (Math.abs(youHavePassed - opponetPassed) >= 110) {
                                    if(carSpeed<60) {
                                        carSpeed += .20;
                                    }
                                } else {
                                    carSpeed=0;
                                }
                            } else {

                                if(carSpeed<60) {
                                    carSpeed += .20;
                                }
                            }

                        }
                        if (keyPressedCode == KeyCode.DOWN&&gamevar) {

                            if(Math.abs(bike1.getX()-imageviewcar5.getX())<=50){
                                if(imageviewcar5.getY()< bike1.imageView.getY()){
                                    if(carSpeed>-15){
                                        carSpeed-=2.5;
                                    }

                                }
                                else if(Math.abs(imageviewcar5.getY()-600)>=110){
                                    if(carSpeed>-15){
                                        carSpeed-=2.5;
                                    }
                                }
                                else {
                                    carSpeed=0;
                                }
                            }

                            else if(Math.abs(bike1.getX()-imageviewcar4.getX())<=50){
                                if(imageviewcar4.getY()< bike1.imageView.getY()){
                                    if(carSpeed>-15){
                                        carSpeed-=2.5;
                                    }

                                }
                                else if(Math.abs(imageviewcar4.getY()-600)>=110){
                                    if(carSpeed>-15){
                                        carSpeed-=2.5;
                                    }
                                }
                                else {
                                    carSpeed=0;
                                }
                            }

                            else if (Math.abs(x - bike1.getX()) <= 50) {
                                if (youHavePassed < opponetPassed) {

                                    if(carSpeed>-15){
                                        carSpeed-=2.5;
                                    }

                                } else if (Math.abs(youHavePassed - opponetPassed) >= 110) {
                                    if(carSpeed>-15){
                                        carSpeed-=2.5;
                                    }

                                } else {
                                    carSpeed=0;
                                }
                            } else {
                                if(carSpeed>-15){
                                    carSpeed-=2.5;
                                }
                            }
                        }

                        if(keyPressedCode==KeyCode.S){
                            carSpeed=0;
                        }
                    }

                    else if(carSpeed!=0&&carSpeed>=0){
                        carSpeed-=.15;
                        if(carSpeed<=0.30&&carSpeed>=-.35){
                            carSpeed=0;
                        }
                    }
                    else if(carSpeed!=0&&carSpeed<0){
                        carSpeed+=0.15;
                        if(carSpeed<=0.30&&carSpeed>=-.30){
                            carSpeed=0;
                        }
                    }

                    if(bike1.getX()>=515|| bike1.getX()<=195){

                        if(carSpeed>=2.5) {
                            carSpeed -=1;
                        }
                        else {
                            carSpeed=0;
                        }
                    }

                    int s=(int)carSpeed;

                    label4.setText(Integer.toString(s));

                    carPassed += carSpeed;
                    carPassedVar += carSpeed;
                    youHavePassed += carSpeed;

                    if(carPassed>=1500){
                        carPassed=0;
                        carPassedVar=0;
                    }

                    if(carPassed<0){
                        carPassed=1500;
                        carPassedVar=1500;
                    }

                    if(!gamevar){
                        carSpeed=0;
                    }

                    bike1.setY(bike1.getY() - carSpeed);

                    if(Math.abs(imageviewcar4.getX()- bike1.getX())<=50){
                        if(imageviewcar4.getY()>600){
                            var1=true;
                        }
                        else if(Math.abs(car4Y-600)>=110){
                            var1=true;
                        }
                        else{
                            var1=false;
                            carSpeed=0;
                        }
                    }
                    else{
                        var1=true;
                    }

                    if(Math.abs(imageviewcar5.getX()- bike1.getX())<=50){
                        if(imageviewcar5.getY()>600){
                            var3=true;
                        }
                        else if(Math.abs(car5Y-600)>=110){
                            var3=true;
                        }
                        else{
                            var3=false;
                            carSpeed=0;
                        }
                    }
                    else{
                        var3=true;
                    }

                    if(Math.abs(imageviewcar4.getX()- bike2.getX())<=50) {
                        if (car4Y >Math.abs(bike2.getY())) {
                            var2=true;
                            withcar2=false;
                        } else if (Math.abs(car4Y - bike2.getY()) >= 110) {
                            var2=true;
                            withcar2=false;
                        } else {
                            var2=false;
                            withcar2=true;
                        }
                    }
                    else{
                        var2=true;
                        withcar2=false;
                    }

                    if(Math.abs(imageviewcar5.getX()- bike2.getX())<=50) {
                        if (car5Y >Math.abs(bike2.getY())) {
                            withcar3=false;
                        } else withcar3= !(Math.abs(car5Y - bike2.getY()) >= 110);
                    }
                    else{

                        withcar3=false;
                    }

                    if(withcar3){
                        car5Y=600+(youHavePassed-opponetPassed)-Math.abs((int)(car5Y- bike2.imageView.getY()));
                    }

                    if(withcar2)
                    {
                        car4Y=600+(youHavePassed-opponetPassed)-Math.abs((int)(car4Y- bike2.imageView.getY()));
                    }

                    if(youHavePassed>=numbOfLaps*lapdistance){
                        youReached = true;
                        gamevar=false;
                    }

                    if((var3&&!withcar3)&&timecount) {
                        if (Main.connected) {

                            if(carSpeed>25){
                                car5Y += carSpeed;
                            }
                            else {

                                if(carSpeed>15){
                                    car5Y+=25;
                                }

                                else{
                                    car5Y += 10 + carSpeed;
                                }
                            }
                        }

                        if (car5Y >= 4000) {
                            car5Y = -2500;
                            count1++;

                            if(count1==5||count1==10||count1==15||count1==20||count1==25||count1==30||count1==35){
                                carrrrrrr=true;
                            }
                        }
                    }

                    if((var1&&var2)&&timecount) {
                        if (Main.connected) {


                            if(carSpeed>25){
                                car4Y += carSpeed;
                            }
                            else {

                                if(carSpeed>20){
                                    car4Y+=25;
                                }

                                else{
                                    car4Y += 5 + carSpeed;
                                }
                            }
                        }

                        if (car4Y >= 3000) {
                            car4Y = -1500;
                            count1++;
                            if(count1==5||count1==10||count1==15||count1==20||count1==25||count1==30||count1==35){
                                carrrrrrr=true;
                            }
                        }
                    }


                    if(count1==5&&carrrrrrr){
                        imageviewcar4.setX(199);
                        imageviewcar5.setX(450);

                        Random r=new Random();
                        int x=r.nextInt();
                        int abx=Math.abs(x);
                        Random r1=new Random();
                        int x1=r1.nextInt();
                        int abx1=Math.abs(x1);

                        System.out.println(abx+ " "+abx1);

                        if(abx%7==0){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==1){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==2){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx%7==3){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx%7==4){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx%7==5){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx%7==6){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        if(abx1%7==0){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==1){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==2){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx1%7==3){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx1%7==4){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx1%7==5){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx1%7==6){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        carrrrrrr=false;
                    }
                    else if(count1==10&&carrrrrrr){
                        imageviewcar4.setX(400);
                        imageviewcar5.setX(250);
                        Random r=new Random();
                        int x=r.nextInt();
                        int abx=Math.abs(x);
                        Random r1=new Random();
                        int x1=r1.nextInt();
                        int abx1=Math.abs(x1);

                        if(abx%7==0){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==1){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==2){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx%7==3){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx%7==4){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx%7==5){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx%7==6){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        if(abx1%7==0){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==1){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==2){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx1%7==3){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx1%7==4){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx1%7==5){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx1%7==6){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        carrrrrrr=false;
                    }
                    else if(count1==15&&carrrrrrr){
                        imageviewcar4.setX(500);
                        imageviewcar5.setX(300);
                        Random r=new Random();
                        int x=r.nextInt();
                        int abx=Math.abs(x);
                        Random r1=new Random();
                        int x1=r1.nextInt();
                        int abx1=Math.abs(x1);

                        if(abx%7==0){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==1){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==2){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx%7==3){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx%7==4){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx%7==5){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx%7==6){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        if(abx1%7==0){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==1){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==2){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx1%7==3){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx1%7==4){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx1%7==5){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx1%7==6){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        carrrrrrr=false;
                    }
                    else if(count1==20&&carrrrrrr){
                        imageviewcar4.setX(400);
                        imageviewcar5.setX(350);
                        Random r=new Random();
                        int x=r.nextInt();
                        int abx=Math.abs(x);
                        Random r1=new Random();
                        int x1=r1.nextInt();
                        int abx1=Math.abs(x1);

                        if(abx%7==0){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==1){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==2){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx%7==3){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx%7==4){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx%7==5){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx%7==6){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        if(abx1%7==0){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==1){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==2){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx1%7==3){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx1%7==4){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx1%7==5){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx1%7==6){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        carrrrrrr=false;
                    }
                    else if(count1==25&&carrrrrrr){
                        imageviewcar4.setX(425);
                        imageviewcar5.setX(220);

                        Random r=new Random();
                        int x=r.nextInt();
                        int abx=Math.abs(x);
                        Random r1=new Random();
                        int x1=r1.nextInt();
                        int abx1=Math.abs(x1);

                        if(abx%7==0){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==1){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==2){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx%7==3){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx%7==4){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx%7==5){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx%7==6){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        if(abx1%7==0){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==1){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==2){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx1%7==3){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx1%7==4){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx1%7==5){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx1%7==6){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        carrrrrrr=false;

                    }

                    else if(count1==30&&carrrrrrr){
                        imageviewcar4.setX(475);
                        imageviewcar5.setX(375);

                        Random r=new Random();
                        int x=r.nextInt();
                        int abx=Math.abs(x);
                        Random r1=new Random();
                        int x1=r1.nextInt();
                        int abx1=Math.abs(x1);

                        if(abx%7==0){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==1){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==2){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx%7==3){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx%7==4){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx%7==5){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx%7==6){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        if(abx1%7==0){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==1){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==2){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx1%7==3){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx1%7==4){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx1%7==5){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx1%7==6){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        carrrrrrr=false;
                    }
                    else if(count1==35&&carrrrrrr){

                        imageviewcar4.setX(275);
                        imageviewcar5.setX(330);

                        Random r=new Random();
                        int x=r.nextInt();
                        int abx=Math.abs(x);
                        Random r1=new Random();
                        int x1=r1.nextInt();
                        int abx1=Math.abs(x1);

                        if(abx%7==0){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==1){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx%7==2){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx%7==3){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx%7==4){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx%7==5){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx%7==6){
                            imageviewcar5.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar5arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        if(abx1%7==0){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar2yellow.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==1){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar1.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/yellowarr.png")));
                        }
                        else if(abx1%7==2){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar3green.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/greenarr.png")));
                        }
                        else if(abx1%7==3){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar4white.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/whitearr.png")));
                        }
                        else if(abx1%7==4){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr2.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow2.png")));
                        }
                        else if(abx1%7==5){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/carrr6.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/arrow1.png")));
                        }
                        else if(abx1%7==6){
                            imageviewcar4.setImage(new Image(Main.class.getResourceAsStream("resources/lcar5violet.png")));
                            imageviewcar4arr.setImage(new Image(Main.class.getResourceAsStream("resources/violetarr.png")));
                        }

                        carrrrrrr=false;
                        count1=0;

                    }

                    imageviewcar4arr.setX(imageviewcar4.getX());
                    imageviewcar5arr.setX(imageviewcar5.getX());

                    int val=(int)carSpeed;
                    int lllap=(youHavePassed/lapdistance);
                    lllap=lllap+1;
                    if(lllap>=numbOfLaps){
                        lllap=numbOfLaps;
                    }

                    labelspeed.setText("("+lllap+"/"+numbOfLaps+")"+"-"+Integer.toString(s));

                    imageviewcar4.setY(car4Y);
                    imageviewcar5.setY(car5Y);

                    if(imageviewcar5.getY()>=-2500&&imageviewcar5.getY()<=-50){
                        imageviewcar5arr.setY(0);
                    }
                    else{
                        imageviewcar5arr.setY(-500);
                    }

                    if(imageviewcar4.getY()>=-1500&&imageviewcar4.getY()<=-50){
                        imageviewcar4arr.setY(0);
                    }
                    else{
                        imageviewcar4arr.setY(-500);
                    }

                    bike1.imageView.setX(bike1.getX());

                    imageviewtree1.setY(carPassedVar - 600);
                    imageviewtree2.setY(carPassedVar - 600);
                    imageviewtree3.setY(carPassedVar - 100);
                    imageviewtree4.setY(carPassedVar - 100);

                    double pos = imageviewtree3.getY();

                    if (pos >= -100 && pos <= 800) {
                        imageviewtree6.setY(pos + 500);
                        imageviewtree5.setY(pos + 500);
                    } else {
                        imageviewtree6.setY(imageviewtree1.getY() - 500);
                        imageviewtree5.setY(imageviewtree1.getY() - 500);
                    }

                }

                if(youHavePassed>=numbOfLaps*lapdistance&&numbOfLaps!=0){
                    carSpeed = 0;
                    var1=false;
                    var2=false;
                    var3=false;
                    withcar3=true;
                    withcar2=true;

                    if(youHavePassed>=opponetPassed&&!alreadyReached){

                        if(controll==1) {

                            int timetook = (int) (timeneeded / 1000);
                            winlos.setText("Congrats!! You Won\n" + "You took " + timetook + " seconds");

                            Connection conn = null;
                            try {
                                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            PreparedStatement stmt;
                            try {
                                stmt = conn.prepareStatement("UPDATE list SET gamesWon = gamesWon + 1 WHERE username = ?");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                stmt.setString(1, Controller.user);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                stmt.executeUpdate();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                    else{

                        if(controll==1) {
                            int timetook = (int) (timeneeded / 1000);

                            winlos.setText("You Lost the game+\n" + "You took " + timetook + " seconds");
                        }
                    }
                    controll=2;
                }

                if(timecount) {
                    timeneeded += 25;
                }

                if(buttoncntrol){
                    mili+=25;
                    if(mili==1000){
                        showingtime--;
                        mili=0;
                    }
                    if(showingtime>=0){
                        carSpeed=0;
                        timeshow.setText("Ready . . ."+Integer.toString(showingtime));
                    }
                    else{
                        showingtime=-1;
                        timecount=true;
                        timeshow.setText("");
                        ///  buttoncntrol=false;
                    }
                }

                if(numbOfLaps!=0) {
                    double carArrow1val = 600 - (1.0 * youHavePassed) / (numbOfLaps * lapdistance) * 600.0;
                    carArrow1.setLayoutY(carArrow1val);

                    double carArrow1val2 = 600 - (1.0 * opponetPassed) / (numbOfLaps * lapdistance) * 600.0;
                    carArrow2.setLayoutY(carArrow1val2);
                }

                if(youHavePassed<2000){
                    finishingLine.setLayoutY(youHavePassed+625);
                }
                else {

                    finishingLine.setLayoutY((youHavePassed - (numbOfLaps * lapdistance)) + 625);
                }

                int laaap=youHavePassed/lapdistance;

                if(laaap!=numbOfLaps&&laaap!=0){
                    lapLine.setLayoutY(youHavePassed-(laaap*lapdistance)-850);
                }

                try {
                    if (dos != null) {
                        dos.writeDouble(bike1.getX());
                        dos.writeDouble(bike1.getY());
                        dos.writeInt(youHavePassed);
                        dos.writeUTF(label1.getText());
                        dos.writeBoolean(true);
                        dos.writeBoolean(startvar);
                        dos.writeDouble(carSpeed);
                        dos.writeInt(numbOfLaps);
                        dos.writeBoolean(timecount);
                        dos.writeBoolean(texfielddis);
                        dos.writeBoolean(buttoncntrol);
                        if(!alreadyReached)
                            dos.writeBoolean(youReached);
                        else dos.writeBoolean(false);
                    }
                } catch (Exception exception) {

                }
                try {
                    if (dis != null) {
                        bike2.setX(x);
                        bike2.imageView.setX(bike2.getX());
                        bike2.setY(600 + (double) (youHavePassed - opponetPassed));
                        bike2.imageView.setY(bike2.getY());
                        label2.setText(usermain);
                        textArea.clear();
                        textArea.appendText("You travelled: ");
                        textArea.appendText(Integer.toString(youHavePassed) + "m\n");
                        textArea.appendText(usermain + " has travelled: ");
                        textArea.appendText(Integer.toString(opponetPassed) + "m");
                        int s=(int)carSpeed2;
                        label5.setText(Integer.toString(s));

                    }
                } catch (Exception e) {

                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        if(server) {
            Rectangle rectangle=new Rectangle(width+50,height+50);
            rectangle.setFill(Color.WHITE);
            rectangle.setOpacity(0.95);
            anchorPane.getChildren().addAll(rectangle);

            Text text=new Text("Waiting For Player2 . . . ");


            ProgressIndicator progressIndicator = new ProgressIndicator();

            progressIndicator.setLayoutX(width/2+50);
            progressIndicator.setLayoutY(height/2 - 40);
            text.setX(width/2-200);
            text.setY(height/2);
            text.setFont(Font.font(Font.getDefault().getName(),FontWeight.BOLD, 20 ));
            text.setFill(new Color(110/255,162/255,255/255,1.0));

            anchorPane.getChildren().addAll(text,progressIndicator);
        }

        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    Socket socket;
                    if (server) {
                        ServerSocket serverSocket = new ServerSocket(444);
                        socket = serverSocket.accept();
                        Platform.runLater(new Runnable() {

                            public void run() {
                                anchorPane.getChildren().remove(anchorPane.getChildren().size()-3,anchorPane.getChildren().size());
                                anchorPane.requestFocus();
                            }
                        });
                    }
                    else{
                        socket=new Socket(serverIPAddress,444);
                        Main.connected=true;
                        Platform.runLater(new Runnable() {

                            public void run() {
                                anchorPane.requestFocus();
                            }
                        });
                    }

                    dos=new DataOutputStream(socket.getOutputStream());
                    dis=new DataInputStream(socket.getInputStream());

                    while(true){
                        x=dis.readDouble();
                        y=dis.readDouble();
                        opponetPassed=dis.readInt();
                        usermain=dis.readUTF();
                        connected=dis.readBoolean();
                        startvar=dis.readBoolean();
                        carSpeed2=dis.readDouble();
                        numbOfLaps=dis.readInt();
                        timecount=dis.readBoolean();
                        texfielddis=dis.readBoolean();
                        buttoncntrol=dis.readBoolean();
                        alreadyReached=dis.readBoolean();
                    }

                } catch (Exception exception) {

                }
            }
        }).start();

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

    public static void main(String[] args) {
        launch(args);
    }
}
