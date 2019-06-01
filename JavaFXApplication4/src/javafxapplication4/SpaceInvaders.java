/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class SpaceInvaders extends Application {

    
    static final double W = 1366, H = 768;

    ArrayList<Image> platskins = new ArrayList();

    static boolean bardestroy = false;
    boolean shoot;

    public static int counter = 116;

    public static Node platforma;

    public Image platImage;

    public static Node patron;

    public Image patronImage;

    boolean goEast, goWest;
    public static ImageView baricada_1;
    
  public static ImageView baricada_2;
  
    public static ImageView baricada_3;


    @Override
    public void start(Stage stage) throws Exception {
        platImage = new Image("javafxapplication4/spaceship1_1.png", W / 18, H / 16, false, false);
        platforma = new ImageView(platImage);
        patronImage = new Image("javafxapplication4/strelka__up.png", W / 50, H / 40, false, false);
        patron = new ImageView(patronImage);

        platforma.setLayoutX(W / 2 - platforma.getBoundsInLocal().getWidth() / 2);
        platforma.setLayoutY(H - platforma.getBoundsInLocal().getHeight() * 3);
        patron.setLayoutX(W / 2 - platforma.getBoundsInLocal().getWidth() / 7);
        patron.setLayoutY(H - platforma.getBoundsInLocal().getHeight() * 3);

        int a = (int) (W / 4);
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        for (int i = 0; i < 6; i++) {
            String str = "javafxapplication4/folder/shield" + i + ".png";
            platskins.add(new Image(str, W / 9, H / 9, false, false));
        }
        baricada_1 = new ImageView(platskins.get(0));
        baricada_2 = new ImageView(platskins.get(0));
        baricada_3 = new ImageView(platskins.get(0));
        
        baricada_1.setX(a - baricada_2.getBoundsInLocal().getWidth() / 2);
        baricada_1.setY(H - baricada_1.getBoundsInLocal().getHeight() * 3.5);

        baricada_2.setX(2 * a - baricada_2.getBoundsInLocal().getWidth() / 2);
        baricada_2.setY(H - baricada_2.getBoundsInLocal().getHeight() * 3.5);

        baricada_3.setX(3 * a - baricada_2.getBoundsInLocal().getWidth() / 2);
        baricada_3.setY(H - baricada_3.getBoundsInLocal().getHeight() * 3.5);


        Group dungeon = new Group(root, baricada_1, baricada_2, baricada_3, platforma, patron);
        Scene scene = new Scene(dungeon, W, H, Color.PINK);

        scene.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {

            @Override

            public void handle(javafx.scene.input.KeyEvent event) {

                switch (event.getCode()) {

                    case LEFT:
                        goWest = true;
                        System.out.println("<");

                        break;
                    case RIGHT:
                        goEast = true;

                        System.out.println(">");
                        break;

                    case SPACE:
                        if (!shoot) {
                            shoot = true;
                            counter = 116;
                            System.out.println("█");
                            patron.setVisible(true);
                        }
                        break;

                }
            }
        });
        scene.setOnKeyReleased((javafx.scene.input.KeyEvent event) -> {

            switch (event.getCode()) {
                case LEFT:
                    goWest = false;

                    break;

                case RIGHT:
                    goEast = false;

                    break;

            }

        });

        stage.setScene(scene);
        stage.show();

        AnimationTimer go = new AnimationTimer() {

            @Override

            public void handle(long now) {

                if (bardestroy) {
                    int x = platskins.indexOf(baricada_1.getImage());
                    baricada_1.setImage(platskins.get(x + 1));
                    int d = platskins.indexOf(baricada_2.getImage());
                    baricada_2.setImage(platskins.get(x + 1));
                    int f = platskins.indexOf(baricada_3.getImage());
                    baricada_3.setImage(platskins.get(x + 1));
                    bardestroy = false;
                }
                int dx = 0, dy = 0;
                if (goEast && platforma.getLayoutX() < 1080) {
                    dx += 5;
                }
                if (goWest && platforma.getLayoutX() > 0) {
                    dx -= 5;

                }

                moveHeroBy(dx, dy);

            }
        };

        go.start();

        stage.setScene(scene);
        stage.show();

        AnimationTimer shooter = new AnimationTimer() {

            @Override

            public void handle(long now) {

                if (shoot) {
                    makeShoot();
                }
            }
        };

        shooter.start();

        AnimationTimer baricads_destroy = new AnimationTimer() {
                boolean l = false, c = false, r = false;
            @Override
            public void handle(long now) {
                if (baricada_3.getBoundsInLocal().contains(patron.getLayoutX(), patron.getLayoutY())) {
                    if(!r) {
                        patron.setVisible(false);
                        int destroylevel = platskins.indexOf(baricada_3.getImage());
                        if(destroylevel < platskins.size()-1) {
                        comeBack();
                            baricada_3.setImage(platskins.get(destroylevel + 1));
                        }
                        else{
                            patron.setVisible(true);
                        }
                        r = true;
                    }
                } else {
                    r = false;
                }
                
                if (baricada_2.getBoundsInLocal().contains(patron.getLayoutX(), patron.getLayoutY())) {
                    if(!c) {
                        patron.setVisible(false);
                        int destroylevel = platskins.indexOf(baricada_2.getImage());
                        if(destroylevel < platskins.size()-1) {
                        comeBack();
                        baricada_2.setImage(platskins.get(destroylevel + 1));
                        }
                        else{
                            patron.setVisible(true);
                        }
                        c = true;
                    }
                } else {
                    c = false;
                }
                
                
                if (baricada_1.getBoundsInLocal().contains(patron.getLayoutX(), patron.getLayoutY())) {
                    if(!l) {
                        patron.setVisible(false);
                        int destroylevel = platskins.indexOf(baricada_1.getImage());
                        if(destroylevel < platskins.size()-1) {
                        baricada_1.setImage(platskins.get(destroylevel + 1));
                        }
                        else{
                        comeBack();
                            patron.setVisible(true);
                        }
                        l = true;
                    }
                }else {
                    l = false;
                }
            }

        };
        baricads_destroy.start();

    }

    void makeShoot() {
        patron.relocate(patron.getLayoutX(), patron.getLayoutY() - 5);
        if (patron.getLayoutY() <= -5) {
            patron.setLayoutY(platforma.getLayoutY());
            patron.setLayoutX(platforma.getLayoutX() + 23);
            System.out.println("end");
            shoot = false;

        }

    }
    void comeBack(){
        patron.setLayoutY(platforma.getLayoutY());
            patron.setLayoutX(platforma.getLayoutX() + 23);
            System.out.println("end");
            shoot = false;
    }

    private void moveHeroBy(int dx, int dy) {

        if (dx == 0 && dy == 0) {
            return;
        }

        final double cx = platforma.getBoundsInLocal().getWidth() / 2;
        final double cy = platforma.getBoundsInLocal().getHeight() / 2;

        double x = cx + platforma.getLayoutX() + dx;
        double y = cy + platforma.getLayoutY() + dy;

        moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {

        final double cx = platforma.getBoundsInLocal().getWidth() / 2;
        final double cy = platforma.getBoundsInLocal().getHeight() / 2;

        platforma.relocate(x - cx, y - cy);

        FXMLDocumentController.x = platforma.getLayoutX();
        FXMLDocumentController.y = platforma.getLayoutY();
        int a = (int) platforma.getBoundsInLocal().getWidth() / 2;
        int b = (int) patron.getBoundsInLocal().getWidth() / 2;
        if (!shoot) {
            patron.relocate((x - cx) + a - b, y - cy);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
