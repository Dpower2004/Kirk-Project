package KirksHouseofKards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        final double SCREEN_WIDTH = 1366;
        final double SCREEN_HEIGHT = 768;

        final double MINI_SCREEN_WIDTH = 960;
        final double MINI_SCREEN_HEIGHT = 540;




        Button START = new Button("START");
        START.setPrefSize(200,50);
        /*Image startImage = new Image("file:icon.png");
        ImageView startBI = new ImageView(startImage);
        START.setGraphic(startBI);*/
        START.setOnAction(e ->{
            Scene gameScene = createGameSelectScene(stage);
            stage.setScene(gameScene);
        });

        Button ACHIEVEMENTS = new Button("ACHIEVEMENTS");
        ACHIEVEMENTS.setPrefSize(200,50);
        /*Image AchievementImage = new Image("file:icon.png");
        ImageView AchievementBI = new ImageView(AchievementImage);
        ACHIEVEMENTS.setGraphic(AchievementBI);*/
        ACHIEVEMENTS.setOnAction(e ->{

        });

        Button SAVE = new Button("SAVE");
        SAVE.setPrefSize(200,50);
        /*Image saveImage = new Image("file:icon.png");
        ImageView SaveBI = new ImageView(saveImage);
        SAVE.setGraphic(SaveBI);*/
        SAVE.setOnAction(e ->{});

        Button CLOSE = new Button("CLOSE");
        CLOSE.setPrefSize(200,50);
        /*Image image = new Image("file:icon.png");
        ImageView startBI = new ImageView(image);
        CLOSE.setGraphic(startBI);*/
        CLOSE.setOnAction(e ->{
            stage.close();
        });

        Label TITLE = new Label("Kirk's House of Kards");
        TITLE.setPrefSize(1366,200);
        TITLE.setFont(new Font(100));
        TITLE.setAlignment(Pos.TOP_CENTER);
        /*Image titleImage = new Image("file:icon.png");
        ImageView titleBI = new ImageView(titleImage);
        TITLE.setGraphic(titleBI);*/


        VBox OptionButtons = new VBox(25);
        OptionButtons.getChildren().addAll(START,ACHIEVEMENTS,SAVE,CLOSE,TITLE);
        OptionButtons.setAlignment(Pos.CENTER);





        HBox titleArea = new HBox();
        titleArea.getChildren().addAll(TITLE);

        VBox SCREEN = new VBox();
        SCREEN.setPrefSize(1366,768);
        SCREEN.getChildren().addAll(titleArea,OptionButtons);
        //Background StartBackground = new Background();
        //SCREEN.setBackground();










        Scene scene1 = new Scene(SCREEN,SCREEN_WIDTH,SCREEN_HEIGHT);
        //Scene scene2 = new Scene(SCREEN,SCREEN_WIDTH,SCREEN_HEIGHT);
        //Scene scene3 = new Scene(MINI_SCREEN_WIDTH,MINI_SCREEN_HEIGHT);
        //Scene scene4 = new Scene(MINI_SCREEN_WIDTH,MINI_SCREEN_HEIGHT);







        stage.setTitle("Krik's House of Kards");
        stage.setScene(scene1);
        stage.show();



    }
    public Scene createGameSelectScene(Stage stage) {
        final double SCREEN_WIDTH = 1366;
        final double SCREEN_HEIGHT = 768;

        // Base root that allows layering
        StackPane root = new StackPane();

        // AnchorPane to place items at specific screen corners
        AnchorPane gameLayer = new AnchorPane();
        gameLayer.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        // Main game content
        Button Achievement = new Button("Achievements");
        Achievement.setLayoutX(100); // Place anywhere
        Achievement.setLayoutY(100);

        Button mainMenu = new Button("Main Menu");
        AnchorPane.setTopAnchor(mainMenu, 20.0);
        AnchorPane.setLeftAnchor(mainMenu, 20.0);

        mainMenu.setOnAction(e -> {
            try {
                start(stage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        Button Bank = new Button("Bank");
        AnchorPane.setTopAnchor(Bank, 20.0);
        AnchorPane.setRightAnchor(Bank, 20.0);
        // Create Bank popup
        VBox popupBank = new VBox(10, new Label("Bank Menu"), new Button("Close"));
        popupBank.setStyle("-fx-background-color: rgba(50, 50, 50, 0.9); -fx-padding: 20;");
        popupBank.setMaxSize(400, 300);
        popupBank.setVisible(false);
        popupBank.setAlignment(Pos.CENTER);

        // Bank button opens it
        Bank.setOnAction(e -> {
            popupBank.setVisible(true);
        });

        // Close button inside Bank popup
        Button closeBank = (Button) popupBank.getChildren().get(1);
        closeBank.setOnAction(e -> popupBank.setVisible(false));


        Button playBlackJack = new Button("Play BlackJack");
        playBlackJack.setPrefSize(100, 100);
        playBlackJack.setLayoutX(600);
        playBlackJack.setLayoutY(300);
        playBlackJack.setOnAction(e -> {
            Scene blackjackScene = createBlackJackScene(stage);
            stage.setScene(blackjackScene);
        });

        // ⬇️ Jukebox layout
        Label jukeboxLabel = new Label("Kirk's Jukebox");
        Button Song1 = new Button("Song1");
        Button Song2 = new Button("Song2");
        Button Song3 = new Button("Song3");
        Button Song4 = new Button("Song4");

        VBox jukebox = new VBox(10, jukeboxLabel, Song1, Song2, Song3, Song4);
        jukebox.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-padding: 10;");
        jukebox.setPrefWidth(200);
        jukebox.setAlignment(Pos.CENTER);

        // Pin to bottom-right
        AnchorPane.setBottomAnchor(jukebox, 20.0);
        AnchorPane.setRightAnchor(jukebox, 20.0);

        // Add all content to game layer
        gameLayer.getChildren().addAll(Achievement, mainMenu, Bank, jukebox, playBlackJack);

        // ⬇️ Popup overlay
        VBox popup = new VBox(10, new Label("Paused"), new Button("Resume"));
        popup.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20;");
        popup.setMaxSize(960, 540);
        popup.setVisible(false);

        // Popup control
        Achievement.setOnAction(e -> popup.setVisible(true));
        Button resume = (Button) popup.getChildren().get(1);
        resume.setOnAction(e -> popup.setVisible(false));

        // StackPane layers it all
        root.getChildren().addAll(gameLayer, popup);

        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private Scene createBlackJackScene(Stage stage) {
        final double SCREEN_WIDTH = 1366;
        final double SCREEN_HEIGHT = 768;
        StackPane root = new StackPane();

        Pane gameContent = new Pane();
        gameContent.setPrefSize(1366, 768);



        Button closeButton = new Button("Close");
        closeButton.setLayoutX(50);
        closeButton.setLayoutY(120);
        closeButton.setOnAction(e -> {
            Scene gameSelectScene = createGameSelectScene(stage);
            stage.setScene(gameSelectScene);
        });



        gameContent.getChildren().add(closeButton);
        root.getChildren().addAll(gameContent);

        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT); // use your defined width/height
    }

}


