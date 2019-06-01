/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static spaceinvaders.FXMLDocumentController.score;
import static spaceinvaders.FXMLDocumentController.kak;


/**
 *
 * @author PC
 */
public class SpaceInvaders extends Application {

    //
    // Для листа с пулями
    //
    ArrayList<ImageView> list = new ArrayList();

    ImageView pup = new ImageView("spaceinvaders/pyl.png");
    ImageView pup1 = new ImageView("spaceinvaders/pyl.png");
    ImageView pup2 = new ImageView("spaceinvaders/pyl.png");
    ImageView pup3 = new ImageView("spaceinvaders/pyl.png");
    ImageView pup4 = new ImageView("spaceinvaders/pyl.png");
//    ImageView block1 = new ImageView("spaceinvaders/block.png");
    ArrayList<ImageView> listblock = new ArrayList();
    ArrayList<ImageView> hp3 = new ArrayList();
    static boolean starter = false;
    public static int counter = 116;
    public static Node platforma;

    public Image platImage;

    public static Node patron;

    public Image patronImage;

    boolean goEast, goWest, shoot;

    //
    // #Для листа с пулями
    //
    int hpe = 3;
    private static double W = 650;
    private static double H = 500;
    public static int lvl=1;
    public static int bob=0;
    public static int lvll = 1;
    public static ImageView[][] ufo = new ImageView[5][10];
    public AnimationTimer go;
    static String localUrl = "spaceinvaders/Space-Invaders-PNG-Image.png";
      static  String localUrl2 = "spaceinvaders/Invader_3-512.png";
      static   String localUrl3 = "spaceinvaders/Space-Invaders-PNG-Transparent-Picture.png";
       static Image image = new Image(localUrl, 40.0, 40.0, false, false);
       static Image image2 = new Image(localUrl2, 40.0, 40.0, false, false);
       static Image image3 = new Image(localUrl3, 40.0, 40.0, false, false);

    @Override
    @SuppressWarnings("empty-statement")
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        platImage = new Image("spaceinvaders/Space_Invaders_Second_Row.png", W / 18, H / 16, false, false);
        platforma = new ImageView(platImage);
        patronImage = new Image("spaceinvaders/dot_PNG4.png", W / 50, H / 40, false, false);
        patron = new ImageView(patronImage);
        //
        // массив для ufo
        //

        ArrayList<Node> olk = new ArrayList();
        olk.add(root);
        String localUrl = "spaceinvaders/Space-Invaders-PNG-Image.png";
        Image image = new Image(localUrl, 40.0, 40.0, false, false);
     

for (int m = 0, k = 18; m < ufo.length; m++) {
            for (int n = 0, a = 50; n < ufo[m].length; n++, k++) {
                if(m%2==0){
                ufo[m][n] = new ImageView(image);}
                else{ufo[m][n] = new ImageView(image2);}
                if(m==2){ufo[m][n] = new ImageView(image3);}
                ufo[m][n].visibleProperty();
                ufo[m][n].setX(ufo[m][n].getX() + 40 * n + a);
                ufo[m][n].setY(ufo[m][n].getY() + 40 * m);
                ufo[m][n].setVisible(false);
                olk.add(ufo[m][n]);
            }
        }
        long m = getElapsedTimeSecs();
        olk.add(pup);
        olk.add(pup1);
        olk.add(pup2);
        olk.add(pup3);
        olk.add(pup4);
        olk.add(platforma);
        olk.add(patron);
        hp3.add(new ImageView("spaceinvaders/hp.png"));
        hp3.add(new ImageView("spaceinvaders/hp.png"));
        hp3.add(new ImageView("spaceinvaders/hp.png"));
        olk.add(hp3.get(0));
        olk.add(hp3.get(1));
        olk.add(hp3.get(2));
        list.add(pup);
        list.add(pup1);
        list.add(pup2);
        list.add(pup3);
        list.add(pup4);
        timerr.start();
        for (int i=0;i<42;i++)
        {
           listblock.add(new ImageView("spaceinvaders/block.png")) ;
           olk.add(listblock.get(i));
        }
        
        block();
        platforma.setLayoutX(W / 2 - platforma.getBoundsInLocal().getWidth() / 2);
        platforma.setLayoutY(H - platforma.getBoundsInLocal().getHeight() * 3);
        patron.setLayoutX(W / 2 - platforma.getBoundsInLocal().getWidth() / 7);
        patron.setLayoutY(H - platforma.getBoundsInLocal().getHeight() * 3);
        Group aliens = new Group(olk);
        
        Scene scene = new Scene(aliens);

        //
        // # массив для ufo
        //
        //
        // Для листа с пулями
        //

        //
        // #Для листа с пулями
        //
        scene.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {

            @Override

            public void handle(javafx.scene.input.KeyEvent event) {

                switch (event.getCode()) {

                    case LEFT:
                        goWest = true;
//                        System.out.println("<");

                        break;
                    case RIGHT:
                        goEast = true;

//                        System.out.println(">");
                        break;

                    case SPACE:
                        if (!shoot) {
                            shoot = true;
                            counter = 116;
//                            System.out.println("-");
                        }
                        break;
                        case P:
                       for (int m = 0; m < ufo.length; m++) {
                    for (int n = 0; n < ufo[m].length; n++) {
//                       ufo[m][n].setDisable(true);
                       ufo[m][n].setVisible(false);
//                       ufo[m][n].setVisible(false);
                       score=5000;
                    }
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
//                     case P:
//                    goWest = false;
//
//                    break;

                case RIGHT:
                    goEast = false;

                    break;

            }

        });
        stage.setScene(scene);
        stage.show();

        //
        // движение ufo
        //
        double d = 0;
        go = new AnimationTimer() {
            int d = 1;

            @Override
            public void handle(long now) {
                //
                pupdown();
//                if (list.get(1).visibleProperty() == false)
//                {
//                    System.out.println(list.get(1).visibleProperty());
//                }
                //
                if (d == 1) {
                    vpravo();
                }
                for (int m = 0; m < ufo.length; m++) {
                    for (int n = 0; n < ufo[m].length; n++) {
                        if (ufo[m][n].isVisible()) {
                            if (ufo[m][n].getX() <= 5) {
                                vniz();
                                d = 1;
                            }
                        }
                    }
                }
                for (int m = 0; m < ufo.length; m++) {
                    for (int n = 0; n < ufo[m].length; n++) {
                        if (ufo[m][n].isVisible()) {
                            if (ufo[m][n].getX() >= 610) {
                                vniz();
                                d = 0;
                            }
                        }
                    }
                }

                if (d == 0) {
                    vlevo();
                }
            }
        };
        go.start();
        shooter.start();
        add.start();
        //
        //# движение ufo
        //

        /**
         *
         */
        starter = true;
        AnimationTimer target = new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                bob=0;
        
//              lvll=0;
             
                if (kak==1)
                {
                    kak=0;
                    System.out.println("sdguhogsdgssgd");
                    lvl=lvl+1;
                    
//                    lvll=lvll+1;
                for (int m = 0, k = 18; m < ufo.length; m++) {
            for (int n = 0, a = 50; n < ufo[m].length; n++, k++) {
                   
                ufo[m][n].setVisible(true);
//                ufo[m][n].setDisable(false);
//                ufo[m][n] = new ImageView(image);
//                ufo[m][n].visibleProperty();
ufo[m][n].setX(0);
ufo[m][n].setY(0);
                ufo[m][n].setX(ufo[m][n].getX() + 40 * n + a);
                ufo[m][n].setY(ufo[m][n].getY() + 40 * m);
//                olk.add(ufo[m][n]);

            }
            }
        }
                
                
                  for (int m = 0, k = 18; m < ufo.length; m++) {
            for (int n = 0, a = 50; n < ufo[m].length; n++, k++) {
                if (!ufo[m][n].isVisible())
                    bob=bob+1;
            }
            }
                
                
                
                
                for (int i = 0; i < ufo.length; i++) {
                    for (int g = 0; g < ufo[i].length; g++) {
                        if (ufo[i][g].isVisible()) {
                            if (ufo[i][g].getBoundsInLocal().contains(patron.getLayoutX(), patron.getLayoutY())) {
//                                System.out.println("Есть пробитие");
                                ufo[i][g].setVisible(false);
//                                ufo[i][g].setDisable(true);
//                                ufo[i][g].setX(-1000);
//                                ufo[i][g].setY(-1000);
                                
if(ufo[i][g].getImage().equals(image)){
                                score = score + 10;}
                                if(ufo[i][g].getImage().equals(image2)){
                                score = score + 50;}
                                if(ufo[i][g].getImage().equals(image3)){
                                score = score + 100;}
                                System.out.println(score);
//                    ufo[i][g].setLayoutX(W-100);
//                    ufo[i][g].setLayoutY(H+100);
                                patron.setLayoutY(W);
                                shoot = false;
                                
                            }
                        }
                    }

                }
            }

        };
//int hpe = 3;
        target.start();
        AnimationTimer targetx = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
//                System.out.println(getElapsedTimeSecs() - m);
                if (platforma != null) {
                    for (int g = 0; g < 5; g++) {
                        for (int i = 0; i < 32; i++) {
                            for (int j = 0; j < 15; j++) {
                        
                        if (list.get(g).getBoundsInLocal().contains(platforma.getLayoutX()+i, platforma.getLayoutY())) {
//                            System.out.println("Боеукладка повреждена танк горит");
                           hpe=hpe-1;
                           list.get(g).setY(H);
                           
                           if (hpe==2)
                               hp3.get(2).setVisible(false);
                                       else
                           if (hpe==1)
                               hp3.get(1).setVisible(false);
                                       else
                           if (hpe==0)
                           {
                            hp3.get(0).setVisible(false);   
                            platforma.setVisible(false);
                            platforma.setDisable(false);
                           }
                        }
                    }
                    }
                
                }
                }
            }

        };
        targetx.start();
                AnimationTimer targetblock;
        targetblock = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                
                for (int g = 0; g < 5; g++) {
                    for (int i = 0; i < 42; i++) {
                        for (int j = 0; j < 20; j++) {
                            if (listblock.get(i)!=null)
                            {
                            if (list.get(g).getBoundsInLocal().contains(listblock.get(i).getLayoutX()+j, listblock.get(i).getLayoutY())) {
//                            System.out.println("Боеукладка повреждена танк горит");

list.get(g).setY(H);
//                            System.out.println( list.get(g).getLayoutY());

listblock.get(i).setVisible(false);
listblock.get(i).setX(-1000);
listblock.get(i).setY(-1000);
listblock.set(i,null);

                            }
                            }
                        }
                    }

                }
                
            }

        };
        targetblock.start();
    }

    //
    //  движение ufo
    //
    void vniz() {
        for (int m = 0; m < ufo.length; m++) {
            for (int n = 0; n < ufo[m].length; n++) {
//                ufo[m][n].setDisable(false);
                if (ufo[m][n].isVisible()) {
                    ufo[m][n].setY(ufo[m][n].getY() + 5);
                }
            }
        }
    }

    void vlevo() {
        for (int m = 0; m < ufo.length; m++) {
            for (int n = 0; n < ufo[m].length; n++) {
                if (ufo[m][n].isVisible()) {
                    ufo[m][n].setX(ufo[m][n].getX() - 0.5);
                }
            }
        }
    }

    void vpravo() {

        for (int m = 0; m < ufo.length; m++) {
            for (int n = 0; n < ufo[m].length; n++) {
                if (ufo[m][n].isVisible()) {
                    ufo[m][n].setX(ufo[m][n].getX() + 0.5);
                }
            }
        }

    }

    //
    //  #движение ufo
    //
    
    
    
    
            
//        listblock.get(1).setLayoutX(75);
//        listblock.get(1).setLayoutY(390);
//        listblock.get(2).setLayoutX(95);
//        listblock.get(2).setLayoutY(390);
//        listblock.get(3).setLayoutX(275);
//        listblock.get(3).setLayoutY(390);
//        listblock.get(4).setLayoutX(295);
//        listblock.get(4).setLayoutY(390);
    
    
    int kk=-7;
            int hoh = 40;
            int alj = -200;
    public void block()
    {
        hp3.get(0).setX(500);
//        hp3.get(0).setY(-10);
        hp3.get(1).setX(530);
//        hp3.get(1).setY(30);
        hp3.get(2).setX(560);
//        hp3.get(2).setY(30);
        for (int gag=0;gag<3;gag++)
        {
            alj=alj+200;
            hoh = 40;
        for(int h=0;h<2;h++)
        {
            hoh=hoh-20;
            kk=kk+7;
            System.out.println(h);
      for (int i=kk,g=0;i<kk+7;i++)
        {
           
           g=g+20; 
//            System.out.println(hoh);
//            System.out.println(h);
           listblock.get(i).setLayoutX(40+g+alj);
           listblock.get(i).setLayoutY(350+hoh);
        }
      
        }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //
    // стрельбы инопланетян
    //
    Random rnd = new Random();

    public void pupdown() {
        int g = rnd.nextInt(5) + 2;
        int f = rnd.nextInt(2);
        //    начало   управление пулей 1
        int gg1 = rnd.nextInt(15) + 5;
        if (getElapsedTimeSecs() % gg1 == 0) {
list.get(1).setVisible(true);
            for (int i = 0; i < rnd.nextInt(3) + 1; i++) {
                if (list.get(1).getY() >= H) {

                    int m = rnd.nextInt(5);
                    int n = rnd.nextInt(10);
                    if (ufo[m][n].isVisible()) {
                        list.get(1).setX(ufo[m][n].getX());
                        list.get(1).setY(ufo[m][n].getY());
                         System.out.println(ufo[m][n].getX());
                          System.out.println(ufo[m][n].getX());
                        System.out.println(list.get(1).getX());
                         System.out.println(list.get(1).getY());

                    }
                }
            }
        }
        //  конец  управление пулей 1
        //    начало   управление пулей 2
        int gg2 = rnd.nextInt(25) + 22;
        if (getElapsedTimeSecs() % gg2 == 0) {
list.get(2).setVisible(true);
            for (int i = 0; i < rnd.nextInt(3) + 1; i++) {
                if (list.get(2).getY() >= H) {
                    int m = rnd.nextInt(5);
                    int n = rnd.nextInt(10);
                    if (ufo[m][n].isVisible()) {
                        list.get(2).setX(ufo[m][n].getX());
                        list.get(2).setY(ufo[m][n].getY());

                    }
                }
            }
        }
        //  конец  управление пулей 2
//        //   начало    управление пулей 3
//        int gg3 = rnd.nextInt(50)+45;
//       if (getElapsedTimeSecs()%gg3==0)
//       {
//        
//           for (int i=0;i < rnd.nextInt(3)+1;i++)
//       {
//           if (list.get(3).getY()>=H)
//          {
//                
//         list.get(3).setX(ufo[rnd.nextInt(5)][rnd.nextInt(10)].getX());
//        list.get(3).setY(ufo[rnd.nextInt(5)][rnd.nextInt(10)].getY());  
//            }
//       }
//       }
        //  конец  управление пулей 3
//        //  начало  управление пулей 4
//        int gg4 = rnd.nextInt(87) + 84;
//        if (getElapsedTimeSecs() % gg4 == 0) {
//list.get(4).setVisible(true);
//            if (list.get(4).getY() >= H) {
//                int m = rnd.nextInt(5);
//                int n = rnd.nextInt(10);
//                if (ufo[m][n] != null) {
//                    list.get(4).setX(ufo[m][n].getX());
//                    list.get(4).setY(ufo[m][n].getY());
//                }
//            }
//        }
//        //  конец  управление пулей 4
    }

    //
    // Полёт пули
    //
    //
    // #стрельбы инопланетян
    //
    AnimationTimer timerr = new AnimationTimer() {
        @Override
        public void handle(long now) {
            for (int i = 0; i < list.size(); i++) {

                list.get(i).setY(list.get(i).getY() + 2);

            }
        }
    };

    //
    // #Полёт пули
    //
    //
    // Секундамер
    //
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = true;

    public void start() {

        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    @Override
    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    //
    // счёт милисекунд
    //
    public long getElapsedTime() {
        long elapsed = 0;

        if (running) {
            elapsed = (System.currentTimeMillis() - startTime);
        } else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }

    //
    // #счёт милисекунд
    //
    //
    // счёт секунд
    //
    public long getElapsedTimeSecs() {
        long elapsed;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        } else {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }

    //
    // #счёт секунд
    //
    //
    // #Секундамер
    //
    AnimationTimer add = new AnimationTimer() {

        @Override

        public void handle(long now) {

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

    AnimationTimer shooter = new AnimationTimer() {

        @Override

        public void handle(long now) {

            if (shoot) {
                makeShoot();
            }
        }
    };

    void makeShoot() {

        patron.relocate(patron.getLayoutX(), patron.getLayoutY() - 5);
        if (patron.getLayoutY() <= -5) {
            patron.setLayoutY(platforma.getLayoutY());
            patron.setLayoutX(platforma.getLayoutX() + 23);
//            System.out.println("end");
            shoot = false;

        }

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
