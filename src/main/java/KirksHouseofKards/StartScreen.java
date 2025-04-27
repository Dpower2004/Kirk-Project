package KirksHouseofKards;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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


        //Font myFont = Font.loadFont(getClass().getResourceAsStream("Super-Mario-64-DS.ttf"), 10);


        Button START = new Button("");
        START.setPrefSize(282,57);
        START.setLayoutX(950);
        START.setLayoutY(350);
        START.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        ArrayList<Image> uiButtonImages = new ArrayList<Image>();
        uiButtonImages.add(new Image("uiButtonHover.png"));
        uiButtonImages.add(new Image("uiButton.png"));
        uiButtonImages.add(new Image("uiButtonPressed.png"));

        ImageView Starthover = new ImageView((uiButtonImages.get(1)));

        Starthover.setX(1100);
        Starthover.setY(350);
        Starthover.setFitWidth(282);
        Starthover.setFitHeight(57);
        Starthover.setSmooth(true);
        START.setOnMouseEntered(e ->{
             Starthover.setImage(uiButtonImages.get(0));
        });
        START.setOnMouseExited(e ->{
            Starthover.setImage(uiButtonImages.get(1));
        });
        START.setOnMousePressed(e ->{
            Starthover.setImage(uiButtonImages.get(2));
        });

        Starthover.setPreserveRatio(false);
        //Starthover.fitWidthProperty().bind(START.widthProperty());
        //Starthover.fitHeightProperty().bind(START.heightProperty());
        //START.setFont(myFont);
        /*Image startImage = new Image("file:icon.png");
        ImageView startBI = new ImageView(startImage);
        START.setGraphic(startBI);*/

        START.setOnAction(e ->{
            Scene gameScene = createGameSelectScene(stage);
            stage.setScene(gameScene);
        });

        Button ACHIEVEMENTS = new Button("");
        ACHIEVEMENTS.setPrefSize(282,57);
        ACHIEVEMENTS.setLayoutX(950);
        ACHIEVEMENTS.setLayoutY(425);

        ImageView Achievementhover = new ImageView((uiButtonImages.get(1)));
        Achievementhover.setPreserveRatio(false);

        ACHIEVEMENTS.setOnMouseEntered(e ->{
            Achievementhover.setImage(uiButtonImages.get(0));
        });
        ACHIEVEMENTS.setOnMouseExited(e ->{
            Achievementhover.setImage(uiButtonImages.get(1));
        });
        ACHIEVEMENTS.setOnMousePressed(e ->{
            Achievementhover.setImage(uiButtonImages.get(2));
        });

        /*Image AchievementImage = new Image("file:icon.png");
        ImageView AchievementBI = new ImageView(AchievementImage);
        ACHIEVEMENTS.setGraphic(AchievementBI);*/
        ACHIEVEMENTS.setOnAction(e ->{

        });

        Button SAVE = new Button("");
        SAVE.setPrefSize(282,57);
        SAVE.setLayoutX(950);
        SAVE.setLayoutY(500);
        ImageView Savehover = new ImageView((uiButtonImages.get(1)));
        Savehover.setPreserveRatio(false);

        SAVE.setOnMouseEntered(e ->{
            Savehover.setImage(uiButtonImages.get(0));
        });
        SAVE.setOnMouseExited(e ->{
            Savehover.setImage(uiButtonImages.get(1));
        });
        SAVE.setOnMousePressed(e ->{
            Savehover.setImage(uiButtonImages.get(2));
        });

        /*Image saveImage = new Image("file:icon.png");
        ImageView SaveBI = new ImageView(saveImage);
        SAVE.setGraphic(SaveBI);*/
        SAVE.setOnAction(e ->{});

        Button CLOSE = new Button("");
        CLOSE.setPrefSize(282,57);
        CLOSE.setLayoutX(950);
        CLOSE.setLayoutY(575);

        ImageView Closehover = new ImageView((uiButtonImages.get(1)));
        Closehover.setPreserveRatio(false);
        CLOSE.setOnMouseEntered(e ->{
            Closehover.setImage(uiButtonImages.get(0));
        });
        CLOSE.setOnMouseExited(e ->{
            Closehover.setImage(uiButtonImages.get(1));
        });
        CLOSE.setOnMousePressed(e ->{
            Closehover.setImage(uiButtonImages.get(2));
        });
        /*Image image = new Image("file:icon.png");
        ImageView startBI = new ImageView(image);
        CLOSE.setGraphic(startBI);*/
        CLOSE.setOnAction(e ->{
            stage.close();
        });

        START.setGraphic(Starthover);
        ACHIEVEMENTS.setGraphic(Achievementhover);
        SAVE.setGraphic(Savehover);
        CLOSE.setGraphic(Closehover);

        Pane OptionButtons = new Pane();
        OptionButtons.setPrefSize(1280,720);
        OptionButtons.getChildren().addAll(START,ACHIEVEMENTS,SAVE,CLOSE);







        VBox SCREEN = new VBox();
        SCREEN.setPrefSize(1280,720);
        SCREEN.getChildren().addAll(OptionButtons);

        ImageView backgroundView = new ImageView(new Image("E_title.png"));
        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(stage.widthProperty());
        backgroundView.fitHeightProperty().bind(stage.heightProperty());


        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView,SCREEN,Starthover,Savehover,Achievementhover,Closehover);











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
        Pane root = new Pane();

        // AnchorPane to place items at specific screen corners
        AnchorPane gameLayer = new AnchorPane();
        gameLayer.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        // Main game content
        ImageView gameScreen = new ImageView(new Image("lobby.png"));

        gameScreen.setPreserveRatio(false);
        gameScreen.setMouseTransparent(true);
        Button Achievement = new Button("Achievements");
        Achievement.setLayoutX(910); // Place anywhere
        Achievement.setLayoutY(60);
        Achievement.setPrefSize(245,255);

        Button mainMenu = new Button("Main Menu");
        mainMenu.setStyle("-fx-background-radius: 100%; -fx-padding: 0;");
        mainMenu.setPrefSize(140,140);
        mainMenu.setLayoutY(570);
        mainMenu.setLayoutX(40);

        mainMenu.setOnAction(e -> {
            try {
                start(stage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        Button Bank = new Button("Bank");
        Bank.setLayoutX(125); // Place anywhere
        Bank.setLayoutY(60);
        Bank.setPrefSize(245,255);
        // Create Bank popupAchievements
        Pane popupBank = new Pane();
        ImageView BankMenuImage = new ImageView(new Image("chipExchange.png"));
        BankMenuImage.setPreserveRatio(false);
        BankMenuImage.setMouseTransparent(true);
        BankMenuImage.fitWidthProperty().bind(popupBank.widthProperty());
        BankMenuImage.fitHeightProperty().bind(popupBank.heightProperty());
        popupBank.setStyle("-fx-background-color: maroon; -fx-padding: 20;");
        popupBank.setMaxSize(960, 540);
        popupBank.setVisible(false);
        popupBank.setLayoutX(200);
        popupBank.setLayoutY(100);

        Label bankMenu = new Label("Bank Menu");
        bankMenu.setLayoutX(460);
        bankMenu.setLayoutY(35);

        Button closeBank = new Button("Close");
        closeBank.setStyle("-fx-background-radius: 100%; -fx-padding: 0;");
        closeBank.setPrefSize(105,105);

        closeBank.setLayoutX(15);
        closeBank.setLayoutY(425);
        closeBank.setOnAction(e -> popupBank.setVisible(false));


        Button increase10 = new Button();
        increase10.setLayoutX(600);
        increase10.setLayoutY(45);

        Button decrease10 = new Button();
        decrease10.setLayoutX(600);
        decrease10.setLayoutY(80);

        Button increase50 = new Button();
        increase50.setLayoutX(600);
        increase50.setLayoutY(125);

        Button decrease50 = new Button();
        decrease50.setLayoutX(600);
        decrease50.setLayoutY(160);

        Button increase250 = new Button();
        increase250.setLayoutX(600);
        increase250.setLayoutY(205);

        Button decrease250 = new Button();
        decrease250.setLayoutX(600);
        decrease250.setLayoutY(240);

        Button increase1000 = new Button();
        increase1000.setLayoutX(600);
        increase1000.setLayoutY(285);

        Button decrease1000 = new Button();
        decrease1000.setLayoutX(600);
        decrease1000.setLayoutY(320);




        Label chipsInCart = new Label("Chips in Cart:");
        chipsInCart.setTextFill(javafx.scene.paint.Color.BLACK);;
        chipsInCart.setLayoutX(170);
        chipsInCart.setLayoutY(100);

        Button buy = new Button("Buy");
        buy.setLayoutX(480);
        buy.setLayoutY(500);

        popupBank.getChildren().addAll(BankMenuImage,bankMenu,closeBank,increase10,increase50,increase250,increase1000,decrease10,decrease50,decrease250,decrease1000,chipsInCart,buy);

        // Bank button opens it
        Bank.setOnAction(e -> {
            popupBank.setVisible(true);
        });



        // ⬇️ Popup overlay
        Pane popupAchievements = new Pane();
       ImageView AchievementMenuImage = new ImageView(new Image("achievments.png"));
        AchievementMenuImage.setPreserveRatio(false);
        AchievementMenuImage.setMouseTransparent(true);
        AchievementMenuImage.fitWidthProperty().bind(popupAchievements.widthProperty());
        AchievementMenuImage.fitHeightProperty().bind(popupAchievements.heightProperty());


        popupAchievements.setPrefSize(960, 540);
        popupAchievements.setLayoutX(200);
        popupAchievements.setLayoutY(100);
        popupAchievements.setVisible(false);

        Label Achievements = new Label("Paused");



        // Close button inside Achievement popup
        Button closeAchieve = new Button ();
        closeAchieve.setStyle("-fx-background-radius: 100%; -fx-padding: 0;");
        closeAchieve.setPrefSize(105,105);

        closeAchieve.setLayoutX(15);
        closeAchieve.setLayoutY(425);
        closeAchieve.setOnAction(e -> popupAchievements.setVisible(false));

        Button unlock1 = new Button("Unlock");
        Button unlock2 = new Button("Unlock");
        Button unlock3 = new Button("Unlock");
        Button unlock4 = new Button("Unlock");
        popupAchievements.getChildren().addAll(AchievementMenuImage,unlock1,unlock2,unlock3,unlock4,Achievements,closeAchieve);



        // Popup control
        Achievement.setOnAction(e -> popupAchievements.setVisible(true));
        Button resume = (Button) popupAchievements.getChildren().get(1);
        resume.setOnAction(e -> popupAchievements.setVisible(false));


        Button playBlackJack = new Button("Play BlackJack");
        playBlackJack.setPrefSize(85, 195);
        playBlackJack.setLayoutX(460);
        playBlackJack.setLayoutY(325);
        playBlackJack.setRotate(-20);

        playBlackJack.setOnAction(e -> {
            Scene blackjackScene = createBlackJackScene(stage);
            stage.setScene(blackjackScene);
        });

        // Add Slots button
        Button playSlots = new Button("Play Slots");
        playSlots.setPrefSize(190, 150);
        playSlots.setLayoutX(550);
        playSlots.setLayoutY(140);
        playSlots.setOnAction(e -> {
            Scene slotsScene = createSlotsScene(stage);
            stage.setScene(slotsScene);

        });

        // Add Holdem button
        Button playHoldem = new Button("Play Texas Holdem");
        playHoldem.setPrefSize(105, 199);
        playHoldem.setLayoutX(729);
        playHoldem.setLayoutY(325);
        playHoldem.setRotate(20);

        playHoldem.setOnAction(e -> {
            Scene HoldemScene = createTexasHoldemScene(stage);
            stage.setScene(HoldemScene);
        });

        // ⬇️ Jukebox layout
        Label jukeboxLabel = new Label("Kirk's Jukebox");
        jukeboxLabel.setLayoutX(1100);
        jukeboxLabel.setLayoutY(450);

        Button Song1 = new Button("Song1");
        Song1.setLayoutX(1047);
        Song1.setLayoutY(502);
        Song1.setPrefSize(168,33);

        Button Song2 = new Button("Song2");
        Song2.setLayoutX(1047);
        Song2.setLayoutY(542);
        Song2.setPrefSize(168,33);

        Button Song3 = new Button("Song3");
        Song3.setLayoutX(1047);
        Song3.setLayoutY(582);
        Song3.setPrefSize(168,33);

        Button Song4 = new Button("Song4");
        Song4.setLayoutX(1047);
        Song4.setLayoutY(624);
        Song4.setPrefSize(168,33);

        Button Play = new Button();
        Play.setLayoutX(1078);
        Play.setLayoutY(664);
        Play.setPrefSize(50,50);

        Button Pause = new Button();
        Pause.setLayoutX(1135);
        Pause.setLayoutY(664);
        Pause.setPrefSize(50,50);


        Pane jukebox = new Pane( jukeboxLabel, Song1, Song2, Song3, Song4,Pause,Play);
        jukebox.setPrefSize(SCREEN_WIDTH,SCREEN_HEIGHT);


        // Pin to bottom-right
        AnchorPane.setBottomAnchor(jukebox, 20.0);
        AnchorPane.setRightAnchor(jukebox, 20.0);

        // Add all content to game layer
        gameLayer.getChildren().addAll(jukebox,Achievement, mainMenu, Bank, playBlackJack,playSlots,playHoldem);



        // StackPane layers it all
        root.getChildren().addAll(gameLayer,gameScreen, popupAchievements, popupBank);

        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        gameScreen.fitWidthProperty().bind(scene.widthProperty());
        gameScreen.fitHeightProperty().bind(scene.heightProperty());

        return scene;
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
        final double SCREEN_WIDTH = 1280;
        final double SCREEN_HEIGHT = 720;

        // Array of image filenames representing different symbols on the slot machine
        final String[] SYMBOL_FILES = {
                "cherry.png", "lemon.png", "orange.png", "grape.png", "kirkDealer.png"
        };

        // Probability weights for each symbol
        final double[] SYMBOL_WEIGHTS = {
                0.35,  // cherry - common
                0.30,  // lemon
                0.20,  // orange
                0.10,  // grape
                0.05   // kirkDealer - rare but achievable
        };

        // 0.25 to allow more matching possibilities
        final double THREE_DIFFERENT_SYMBOLS_PROBABILITY = 0.25;

        // Add probability to force three matching symbols (25% chance)
        final double THREE_MATCHING_SYMBOLS_PROBABILITY = 0.25;

        // This gives kirkDealer a chance to appear even in the "three different" scenario
        final double KIRK_DEALER_IN_DIFFERENT_PROBABILITY = 0.15;

        // Create the scene components
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(40));

        // Create and style the header label
        Label headerLabel = new Label("KIRK'S SLOT MACHINE");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        root.setTop(headerLabel);
        BorderPane.setAlignment(headerLabel, Pos.CENTER);
        BorderPane.setMargin(headerLabel, new Insets(0, 0, 50, 0));

        // Create container for the slot machine reels
        HBox reelsBox = new HBox(80);
        reelsBox.setAlignment(Pos.CENTER);
        reelsBox.setPadding(new Insets(50));

        // Initialize image views for reels
        ImageView[] reelImages = new ImageView[3];
        for (int i = 0; i < 3; i++) {
            reelImages[i] = new ImageView();
            reelImages[i].setFitWidth(150);
            reelImages[i].setFitHeight(150);
            reelsBox.getChildren().add(reelImages[i]);
        }

        // Create and style the result message label
        Label resultLabel = new Label("Place your bet and spin!");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Container for reels and result message
        VBox centerBox = new VBox(40);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(reelsBox, resultLabel);
        root.setCenter(centerBox);

        // Container for game controls (bet input, spin button)
        VBox controlsBox = new VBox(20);
        controlsBox.setAlignment(Pos.CENTER);

        // Create and style balance display label
        double balance = 1000; // Initial player balance
        Label balanceLabel = new Label("Balance: $" + String.format("%.2f", balance));
        balanceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Container for bet label and input field
        HBox betBox = new HBox(15);
        betBox.setAlignment(Pos.CENTER);

        // Create and style bet label
        Label betLabel = new Label("Your Bet: $");
        betLabel.setFont(Font.font("Arial", 20));

        // Create and style bet input field
        TextField betInput = new TextField();
        betInput.setFont(Font.font("Arial", 20));
        betInput.setMaxWidth(200);
        betInput.setPromptText("Enter bet");

        // Add label and input field to bet container
        betBox.getChildren().addAll(betLabel, betInput);

        // Create spin button
        Button spinButton = new Button("SPIN");
        spinButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        spinButton.setMinWidth(200);
        spinButton.setMinHeight(60);

        // Initialize required variables for the slot machine
        Random random = new Random();

        // AudioClip setup - make sure you have these files
        AudioClip spinSound = null;
        AudioClip loseSound = null;
        try {
            spinSound = new AudioClip(new File("spin_sound_trimmed.mp3").toURI().toString());
            loseSound = new AudioClip(new File("youtube__asNhzXq72w_audio.mp3").toURI().toString());
        } catch (Exception e) {
            System.out.println("Could not load audio files. The game will continue without sound.");
        }

        // Helper functions
        // Returns a random symbol index based on the defined probability weights
        java.util.function.Supplier<Integer> getWeightedRandomSymbol = () -> {
            double randomVal = random.nextDouble();
            double cumulativeProbability = 0.0;

            for (int i = 0; i < SYMBOL_WEIGHTS.length; i++) {
                cumulativeProbability += SYMBOL_WEIGHTS[i];
                if (randomVal < cumulativeProbability) {
                    return i;
                }
            }

            // Fallback (shouldn't happen if weights sum to 1)
            return 0;
        };

        // Returns a random symbol index (0-4) with adjustable chance for kirkDealer
        java.util.function.Function<Boolean, Integer> getRandomSymbol = (allowKirkDealer) -> {
            // If kirkDealer is allowed and we roll the probability check
            if (allowKirkDealer && random.nextDouble() < KIRK_DEALER_IN_DIFFERENT_PROBABILITY) {
                return 4; // Return kirkDealer index
            } else {
                // Only include indices 0-3 (fruits)
                return random.nextInt(4);
            }
        };

        // Calculates winnings based on the results and bet amount
        java.util.function.BiFunction<int[], Double, Double> calculateWinnings = (results, betAmount) -> {
            // Count occurrences of each symbol
            int[] counts = new int[SYMBOL_FILES.length];
            for (int r : results) {
                counts[r]++;  // Increment count for this symbol
            }

            // Check for triple match (3 of the same symbol)
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] == 3) {
                    switch (i) {
                        case 0: return betAmount * 2;   // cherry - 2x multiplier
                        case 1: return betAmount * 3;   // lemon - 3x multiplier
                        case 2: return betAmount * 5;   // orange - 5x multiplier
                        case 3: return betAmount * 10;  // grape - 10x multiplier
                        case 4: return betAmount * 30;  // kirkDealer - 30x multiplier
                    }
                }
            }

            // Check for double matches (2 of the same symbol)
            if (counts[0] == 2) return betAmount * 0.15;  // 2 cherries - 15% return
            if (counts[1] == 2) return betAmount * 0.25;  // 2 lemons - 25% return
            if (counts[2] == 2) return betAmount * 0.3;   // 2 oranges - 30% return
            if (counts[3] == 2) return betAmount * 0.4;   // 2 grapes - 40% return

            // kirkDealer gets more
            if (counts[4] == 2) return betAmount * 5;     // 2 kirkDealers - 5x multiplier

            // No matches
            return 0.0;  // Player loses their bet
        };

        // Create a wrapper for balance to allow updates in lambda expressions
        final double[] balanceRef = {balance};

        // Animates the slot reels and calls the result logic when finished
        java.util.function.Consumer<Runnable> animateReels = (onFinished) -> {
            Timeline timeline = new Timeline();
            int cycles = 30;

            for (int i = 0; i < cycles; i++) {
                int index = i;
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 75), e -> {
                    // For each reel, show a random symbol during animation
                    for (int j = 0; j < reelImages.length; j++) {
                        int symbolIndex = getWeightedRandomSymbol.get();
                        try {
                            reelImages[j].setImage(new Image("" + SYMBOL_FILES[symbolIndex]));
                        } catch (Exception ex) {
                            System.out.println("Could not load image: " + SYMBOL_FILES[symbolIndex]);
                        }
                    }
                }));
            }

            timeline.setOnFinished(e -> onFinished.run());
            timeline.play();
        };

        // Keep references to sound effects for the spin button action
        final AudioClip finalSpinSound = spinSound;
        final AudioClip finalLoseSound = loseSound;

        // Set up the spin button action
        spinButton.setOnAction(e -> {
            try {
                // Parse bet amount from text field
                double betAmount = Double.parseDouble(betInput.getText());

                // Validate bet is positive
                if (betAmount <= 0) {
                    resultLabel.setText("Please enter a valid bet amount!");
                    return;
                }

                // Check if player has enough balance
                if (betAmount > balanceRef[0]) {
                    resultLabel.setText("Not enough money! Your balance is $" + String.format("%.2f", balanceRef[0]));
                    return;
                }

                // Deduct bet from balance
                balanceRef[0] -= betAmount;
                if (finalSpinSound != null) finalSpinSound.play();

                // Update balance display
                balanceLabel.setText("Balance: $" + String.format("%.2f", balanceRef[0]));
                // Show spinning message
                resultLabel.setText("Spinning...");

                // Start the reels animation
                animateReels.accept(() -> {
                    int[] results = new int[3];

                    // Random number to determine spin type
                    double spinType = random.nextDouble();

                    // Force three different symbols (25% chance)
                    if (spinType < THREE_DIFFERENT_SYMBOLS_PROBABILITY) {
                        // Create a set to track which symbols we've already selected
                        HashSet<Integer> selectedSymbols = new HashSet<>();

                        // For each reel
                        for (int i = 0; i < 3; i++) {
                            int symbolIndex;
                            // Keep generating random symbols until we get one we haven't used yet
                            do {
                                symbolIndex = getRandomSymbol.apply(true);
                            } while (selectedSymbols.contains(symbolIndex));

                            selectedSymbols.add(symbolIndex);
                            results[i] = symbolIndex;
                            try {
                                reelImages[i].setImage(new Image("file:" + SYMBOL_FILES[symbolIndex]));
                            } catch (Exception ex) {
                                System.out.println("Could not load image: " + SYMBOL_FILES[symbolIndex]);
                            }
                        }

                        // All 3 are guaranteed to be different, so it's a loss
                        if (finalLoseSound != null) finalLoseSound.play();
                    }
                    // Force three matching symbols (25% chance)
                    else if (spinType < THREE_DIFFERENT_SYMBOLS_PROBABILITY + THREE_MATCHING_SYMBOLS_PROBABILITY) {
                        // Select one random symbol using weighted probabilities
                        int symbolIndex = getWeightedRandomSymbol.get();

                        // Set all three reels to show this symbol
                        for (int i = 0; i < 3; i++) {
                            results[i] = symbolIndex;
                            try {
                                reelImages[i].setImage(new Image("file:" + SYMBOL_FILES[symbolIndex]));
                            } catch (Exception ex) {
                                System.out.println("Could not load image: " + SYMBOL_FILES[symbolIndex]);
                            }
                        }
                    }
                    // Regular weighted spin (now 50%)
                    else {
                        int symbolIndex = getWeightedRandomSymbol.get();
                        // Generate results using weighted probability
                        for (int i = 0; i < 3; i++) {
                            results[i] = getWeightedRandomSymbol.get();
                            try {
                                reelImages[i].setImage(new Image("file:" + SYMBOL_FILES[symbolIndex]));
                            } catch (Exception ex) {
                                System.out.println("Could not load image: " + SYMBOL_FILES[symbolIndex]);
                            }
                        }
                    }

                    // Calculate winnings based on results
                    double winnings = calculateWinnings.apply(results, betAmount);

                    // Update display based on win/loss
                    if (winnings > 0) {
                        // Add winnings to balance
                        balanceRef[0] += winnings;
                        resultLabel.setText("You won $" + String.format("%.2f", winnings) + "!");
                    } else {
                        resultLabel.setText("You lost $" + String.format("%.2f", betAmount));
                    }

                    // Update balance display
                    balanceLabel.setText("Balance: $" + String.format("%.2f", balanceRef[0]));
                });

            } catch (NumberFormatException ex) {
                // Handle case where input is not a valid number
                resultLabel.setText("Please enter a valid number!");
            }
        });

        // Add a back button to return to the game selection screen
        Button backButton = new Button("Back to Games");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        backButton.setOnAction(e -> stage.setScene(createGameSelectScene(stage)));

        // Add all controls to the controls container
        controlsBox.getChildren().addAll(balanceLabel, betBox, spinButton, backButton);
        root.setBottom(controlsBox);
        BorderPane.setMargin(controlsBox, new Insets(40, 0, 0, 0));

        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
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


