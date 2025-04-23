//package KirksHouseofKards;

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

        final double SCREEN_WIDTH = 1280;
        final double SCREEN_HEIGHT = 720;

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




        VBox OptionButtons = new VBox(25);
        OptionButtons.getChildren().addAll(START,ACHIEVEMENTS,SAVE,CLOSE);
        OptionButtons.setAlignment(Pos.BOTTOM_RIGHT);





        VBox SCREEN = new VBox();
        SCREEN.setPrefSize(1280,720);
        SCREEN.getChildren().addAll(OptionButtons);

        ImageView backgroundView = new ImageView(new Image("E_title.png"));
        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(stage.widthProperty());
        backgroundView.fitHeightProperty().bind(stage.heightProperty());


        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, SCREEN);











        Scene scene1 = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);

        //Scene scene2 = new Scene(SCREEN,SCREEN_WIDTH,SCREEN_HEIGHT);
        //Scene scene3 = new Scene(MINI_SCREEN_WIDTH,MINI_SCREEN_HEIGHT);
        //Scene scene4 = new Scene(MINI_SCREEN_WIDTH,MINI_SCREEN_HEIGHT);







        stage.setTitle("Krik's House of Kards");
        stage.setScene(scene1);
        stage.show();



    }
    public Scene createGameSelectScene(Stage stage) {
        final double SCREEN_WIDTH = 1280;
        final double SCREEN_HEIGHT = 720;

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
        popupBank.setMaxSize(960, 540);
        popupBank.setVisible(false);
        popupBank.setAlignment(Pos.CENTER);


        Button increase10 = new Button();
        Button decrease10 = new Button();
        Button increase50 = new Button();
        Button decrease50 = new Button();
        Button increase250 = new Button();
        Button decrease250 = new Button();
        Button increase1000 = new Button();
        Button decrease1000 = new Button();
        Button buy = new Button("Buy");

        Label chipsInCart = new Label("Chips in Cart");

        popupBank.getChildren().addAll(increase10,increase50,increase250,increase1000,decrease10,decrease50,decrease250,decrease1000,buy);

        // Bank button opens it
        Bank.setOnAction(e -> {
            popupBank.setVisible(true);
        });

        // Close button inside Bank popup
        Button closeBank = (Button) popupBank.getChildren().get(1);
        closeBank.setOnAction(e -> popupBank.setVisible(false));

        // ⬇️ Popup overlay
        VBox popup = new VBox(10, new Label("Paused"), new Button("Close"));
        popup.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20;");
        popup.setMaxSize(960, 540);
        popup.setVisible(false);

        Button unlock1 = new Button("Unlock");
        Button unlock2 = new Button("Unlock");
        Button unlock3 = new Button("Unlock");
        Button unlock4 = new Button("Unlock");
        popup.getChildren().addAll(unlock1,unlock2,unlock3,unlock4);



        // Popup control
        Achievement.setOnAction(e -> popup.setVisible(true));
        Button resume = (Button) popup.getChildren().get(1);
        resume.setOnAction(e -> popup.setVisible(false));


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



        // StackPane layers it all
        root.getChildren().addAll(gameLayer, popup, popupBank);

        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private Scene createBlackJackScene(Stage stage) {
        final double SCREEN_WIDTH = 1280;
        final double SCREEN_HEIGHT = 720;

        StackPane root = new StackPane();
        Pane gameContent = new Pane();
        gameContent.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        // Game variables (wrapper so we can update inside lambdas)
        final BlackjackPlayer[] player = new BlackjackPlayer[1];
        final Blackjack[] game = new Blackjack[1];
        final BlackjackPlayer[] dealer = new BlackjackPlayer[1];

        // Labels
        Label dealerLabel = new Label("Dealer: ");
        dealerLabel.setLayoutX(50);
        dealerLabel.setLayoutY(50);

        Label playerLabel = new Label("Player: ");
        playerLabel.setLayoutX(50);
        playerLabel.setLayoutY(100);

        Label statusLabel = new Label("");
        statusLabel.setLayoutX(50);
        statusLabel.setLayoutY(300);

        // Buttons
        Button hitButton = new Button("Hit");
        hitButton.setLayoutX(50);
        hitButton.setLayoutY(200);

        Button standButton = new Button("Stand");
        standButton.setLayoutX(120);
        standButton.setLayoutY(200);

        Button playAgainButton = new Button("Play Again");
        playAgainButton.setLayoutX(200);
        playAgainButton.setLayoutY(200);
        playAgainButton.setVisible(false);

        Button closeButton = new Button("Close");
        closeButton.setLayoutX(1200);
        closeButton.setLayoutY(650);

        // Method to update card labels
        Runnable updateUI = () -> {
            dealerLabel.setText("Dealer: [" + dealer[0].cards.getCard(0) + ", ?]");
            playerLabel.setText("Player: " + player[0].cards.toString() + " (Score: " + player[0].getScore() + ")");
        };

        // Reset and start a new game
        Runnable resetGame = () -> {
            player[0] = new BlackjackPlayer(100, true);
            Player[] players = new Player[] { player[0] };
            game[0] = new Blackjack(players);
            game[0].setup();
            dealer[0] = game[0].getDealer();

            updateUI.run();
            statusLabel.setText("");
            hitButton.setDisable(false);
            standButton.setDisable(false);
            playAgainButton.setVisible(false);
        };

        // Start first game
        resetGame.run();

        // Hit logic
        hitButton.setOnAction(e -> {
            Card card = game[0].deck.getCard(0);
            player[0].cards.add(card);
            game[0].deck.remove(card);
            updateUI.run();

            if (player[0].getScore() > 21) {
                statusLabel.setText("You bust!");
                hitButton.setDisable(true);
                standButton.setDisable(true);
                playAgainButton.setVisible(true);
            }
        });

        // Stand logic
        standButton.setOnAction(e -> {
            while (dealer[0].getScore() < 17) {
                Card card = game[0].deck.getCard(0);
                dealer[0].cards.add(card);
                game[0].deck.remove(card);
            }

            int playerScore = player[0].getScore();
            int dealerScore = dealer[0].getScore();

            dealerLabel.setText("Dealer: " + dealer[0].cards + " (Score: " + dealerScore + ")");

            if (playerScore > 21) {
                statusLabel.setText("You bust!");
            } else if (dealerScore > 21 || playerScore > dealerScore) {
                statusLabel.setText("You win!");
            } else if (playerScore == dealerScore) {
                statusLabel.setText("Push.");
            } else {
                statusLabel.setText("You lose.");
            }

            hitButton.setDisable(true);
            standButton.setDisable(true);
            playAgainButton.setVisible(true);
        });

        // Play Again
        playAgainButton.setOnAction(e -> resetGame.run());

        // Close button returns to game select scene
        closeButton.setOnAction(e -> stage.setScene(createGameSelectScene(stage))); // Replace with your real method

        gameContent.getChildren().addAll(dealerLabel, playerLabel, statusLabel, hitButton, standButton, playAgainButton, closeButton);
        root.getChildren().add(gameContent);

        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }


    private Scene createSlotsScene(Stage stage) {
        final double SCREEN_WIDTH = 1366;
        final double SCREEN_HEIGHT = 768;
        StackPane root = new StackPane();

        Pane gameContent = new Pane();
        gameContent.setPrefSize(1366, 768);



        Button closeButton = new Button("Close");

        Button hitButton = new Button("Hit");
        Button standButton = new Button("Stand");
        Button betIncrease = new Button("Increase Bet");
        Button betDecrease = new Button("");




        gameContent.getChildren().add(closeButton);
        root.getChildren().addAll(gameContent);

        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT); // use your defined width/height
    }
    private Scene createTexasHoldemScene(Stage stage) {
        final double SCREEN_WIDTH = 1366;
        final double SCREEN_HEIGHT = 768;
        StackPane root = new StackPane();

        Pane gameContent = new Pane();
        gameContent.setPrefSize(1366, 768);



        Button closeButton = new Button("Close");

        Button hitButton = new Button("Hit");
        Button standButton = new Button("Stand");
        Button betIncrease = new Button("Increase Bet");
        Button betDecrease = new Button("");




        gameContent.getChildren().add(closeButton);
        root.getChildren().addAll(gameContent);

        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT); // use your defined width/height
    }
}


