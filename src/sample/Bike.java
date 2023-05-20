package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bike {

    public ImageView imageView;
    Double X=0.0,Y=0.0;

    public Double getX() {
        return X;
    }

    public void setX(Double x) {
        X = x;
    }

    public Double getY() {
        return Y;
    }

    public void setY(Double y) {
        Y = y;
    }

    public Bike(boolean server,double y){
        if(server)
            imageView=new ImageView(new Image(Bike.class.getResourceAsStream("resources/bike_player1.png")));
        else imageView=new ImageView(new Image(Bike.class.getResourceAsStream("resources/bike_player2.png")));

        if(server){
            setX(250.00);
            imageView.setX(250.00);
        }
        else {
            setX(430.00);
            imageView.setX(430.00);
        }

        imageView.setY(y);
        setY(y);

    }


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
