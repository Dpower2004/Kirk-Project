import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.text.NumberFormat;

public class StartScreen extends Application {
    HoldemState gameState = HoldemState.SETUP;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        final double SCREEN_WIDTH = 1280;
        final double SCREEN_HEIGHT = 720;

        final double MINI_SCREEN_WIDTH = 960;
        final double MINI_SCREEN_HEIGHT = 540;

        Player mainPlayer = new Player(1000,false,10000.0);


        Button START = new Button("");
        START.setPrefSize(282,57);
        START.setLayoutX(950);
        START.setLayoutY(350);
        START.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        ArrayList<Image> uiButtonImages = new ArrayList<Image>();
        uiButtonImages.add(new Image("/ui/uiButtonHover.png"));
        uiButtonImages.add(new Image("/ui/uiButton.png"));
        uiButtonImages.add(new Image("/ui/uiButtonPressed.png"));

        ImageView Starthover = new ImageView((uiButtonImages.get(1)));


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


        START.setOnAction(e ->{
            Scene gameScene = createGameSelectScene(stage,mainPlayer);
            stage.setScene(gameScene);
        });

        Button ACHIEVEMENTS = new Button("");
        ACHIEVEMENTS.setPrefSize(282,57);
        ACHIEVEMENTS.setLayoutX(950);
        ACHIEVEMENTS.setLayoutY(425);
        ACHIEVEMENTS.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        ImageView Achievementhover = new ImageView((uiButtonImages.get(1)));
        Achievementhover.setPreserveRatio(false);
        Achievementhover.setFitWidth(282);
        Achievementhover.setFitHeight(57);
        Achievementhover.setSmooth(true);

        ACHIEVEMENTS.setOnMouseEntered(e ->{
            Achievementhover.setImage(uiButtonImages.get(0));
        });
        ACHIEVEMENTS.setOnMouseExited(e ->{
            Achievementhover.setImage(uiButtonImages.get(1));
        });
        ACHIEVEMENTS.setOnMousePressed(e ->{
            Achievementhover.setImage(uiButtonImages.get(2));
        });


        ACHIEVEMENTS.setOnAction(e ->{

        });

        Button SAVE = new Button("");
        SAVE.setPrefSize(282,57);
        SAVE.setLayoutX(950);
        SAVE.setLayoutY(500);
        SAVE.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        /*ImageView Savehover = new ImageView((uiButtonImages.get(1)));
        Savehover.setPreserveRatio(false);
        Savehover.setFitWidth(282);
        Savehover.setFitHeight(57);
        Savehover.setSmooth(true);

        SAVE.setOnMouseEntered(e ->{
            Savehover.setImage(uiButtonImages.get(0));
        });
        SAVE.setOnMouseExited(e ->{
            Savehover.setImage(uiButtonImages.get(1));
        });
        SAVE.setOnMousePressed(e ->{
            Savehover.setImage(uiButtonImages.get(2));
        });*/


        Button CLOSE = new Button("");
        CLOSE.setPrefSize(282,57);
        CLOSE.setLayoutX(950);
        CLOSE.setLayoutY(500);
        CLOSE.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        ImageView Closehover = new ImageView((uiButtonImages.get(1)));
        Closehover.setPreserveRatio(false);
        Closehover.setFitWidth(282);
        Closehover.setFitHeight(57);
        Closehover.setSmooth(true);

        CLOSE.setOnMouseEntered(e ->{
            Closehover.setImage(uiButtonImages.get(0));
        });
        CLOSE.setOnMouseExited(e ->{
            Closehover.setImage(uiButtonImages.get(1));
        });
        CLOSE.setOnMousePressed(e ->{
            Closehover.setImage(uiButtonImages.get(2));
        });

        CLOSE.setOnAction(e ->{
            stage.close();
        });

        START.setGraphic(Starthover);
        ACHIEVEMENTS.setGraphic(Achievementhover);

        CLOSE.setGraphic(Closehover);

        Pane OptionButtons = new Pane();
        OptionButtons.setPrefSize(1280,720);
        OptionButtons.getChildren().addAll(START,ACHIEVEMENTS,SAVE,CLOSE);

        Font myFont = Font.loadFont(getClass().getResourceAsStream("Super-Mario-64-DS.ttf"), 20);

        Label startLabel = new Label("START");

        startLabel.setFont(myFont);
        startLabel.setStyle("-fx-text-fill: white;");
        startLabel.setLayoutX(1050); // center of 282 minus half label width
        startLabel.setLayoutY(362);
        startLabel.setMouseTransparent(true);

        Label achievementsLabel = new Label("ACHIEVEMENTS");
        achievementsLabel.setFont(myFont);
        achievementsLabel.setStyle("-fx-text-fill: white;");
        achievementsLabel.setLayoutX(995);
        achievementsLabel.setLayoutY(437);
        achievementsLabel.setMouseTransparent(true);

        /*Label saveLabel = new Label("SAVE");
        saveLabel.setFont(myFont);
        saveLabel.setStyle("-fx-text-fill: white;");
        saveLabel.setLayoutX(1058);
        saveLabel.setLayoutY(512);
        saveLabel.setMouseTransparent(true);*/

        Label closeLabel = new Label("CLOSE");
        closeLabel.setFont(myFont);
        closeLabel.setStyle("-fx-text-fill: white;");
        closeLabel.setLayoutX(950 + 141 - 35);
        closeLabel.setLayoutY(512);
        closeLabel.setMouseTransparent(true);

        OptionButtons.getChildren().addAll(startLabel, achievementsLabel, closeLabel);






        VBox SCREEN = new VBox();
        SCREEN.setPrefSize(1280,720);
        SCREEN.getChildren().addAll(OptionButtons);

        ImageView backgroundView = new ImageView(new Image("/backgrounds/E_title.png"));
        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(stage.widthProperty());
        backgroundView.fitHeightProperty().bind(stage.heightProperty());


        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView,SCREEN,Starthover,Achievementhover,Closehover);











        Scene scene1 = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);

        //Scene scene2 = new Scene(SCREEN,SCREEN_WIDTH,SCREEN_HEIGHT);
        //Scene scene3 = new Scene(MINI_SCREEN_WIDTH,MINI_SCREEN_HEIGHT);
        //Scene scene4 = new Scene(MINI_SCREEN_WIDTH,MINI_SCREEN_HEIGHT);







        stage.setTitle("Krik's House of Kards");
        stage.setScene(scene1);
        stage.show();



    }

    /**
     * Creates and returns the game selection scene where the player can choose between
     * different casino games (e.g., Blackjack, Slots, etc.).
     *
     * This method sets up a layered JavaFX scene using a base Pane for custom positioning
     * and an AnchorPane to support structured UI layout.
     *
     * @param stage  the primary stage used for scene switching
     * @param player the current player instance with chip and money data
     * @return a Scene object representing the game selection screen
     */
    public Scene createGameSelectScene(Stage stage, Player player) {
        final double SCREEN_WIDTH = 1280;
        final double SCREEN_HEIGHT = 720;


        Player mainPlayer = new Player(player.getChips(), false,player.getMoney());

        // Base root that allows layering
        Pane root = new Pane();

        // AnchorPane to place items at specific screen corners
        AnchorPane gameLayer = new AnchorPane();
        gameLayer.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        //Store pixel are font in game creation scene
        Font myFont = Font.loadFont(getClass().getResourceAsStream("Super-Mario-64-DS.ttf"), 20);

        // Main game content
        ImageView gameScreen = new ImageView(new Image("/backgrounds/lobby.png"));

        gameScreen.setPreserveRatio(false);
        gameScreen.setMouseTransparent(true);
        gameScreen.setSmooth(true);
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
        ImageView BankMenuImage = new ImageView(new Image("/backgrounds/chipExchange.png"));
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

        Button closeBank = new Button("");
        closeBank.setStyle("-fx-background-radius: 100%; -fx-padding: 0;");
        closeBank.setPrefSize(105,105);

        closeBank.setLayoutX(15);
        closeBank.setLayoutY(425);
        closeBank.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        closeBank.setOnAction(e -> popupBank.setVisible(false));



        double[] cashInCart ={0.0} ;
        int[] chipsInCart = {0};
        int cashToChipRatio = 10;
        final boolean[] isCashingChips = {false};

        Label cashBalance = new Label(String.format("%.2f",mainPlayer.getMoney()));

        cashBalance.setFont(myFont);
        cashBalance.setTextFill(Color.WHITE);;
        cashBalance.setLayoutX(170);
        cashBalance.setLayoutY(50);

        Label chipBalance = new Label(""+ mainPlayer.getChips());
        chipBalance.setFont(myFont);
        chipBalance.setTextFill(Color.WHITE);;
        chipBalance.setLayoutX(170);
        chipBalance.setLayoutY(100);

        Label cashLabel = new Label(""+cashInCart[0]);
        Label chipLabel = new Label(""+chipsInCart[0]);

        Label cashInCartLabel = new Label("Cash in Cart:"+cashInCart[0]);
        cashInCartLabel.setLayoutX(750);
        cashInCartLabel.setLayoutY(480);
        cashInCartLabel.setFont(myFont);


        Label chipsInCartLabel = new Label("Chips in Cart:"+chipsInCart[0]);
        chipsInCartLabel.setLayoutX(750);
        chipsInCartLabel.setLayoutY(430);
        chipsInCartLabel.setFont(myFont);

        Button buy = new Button("Buy Chips");
        buy.setLayoutX(430);
        buy.setLayoutY(480);
        buy.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        buy.setFont(myFont);
        buy.setOnAction(e ->{
            if (isValidSale(mainPlayer,cashInCart)){
                mainPlayer.setMoney(mainPlayer.getMoney()-(cashToChipRatio*chipsInCart[0]));
                mainPlayer.setChips(mainPlayer.getChips()+chipsInCart[0]);
                cashBalance.setText(String.format("%.2f",mainPlayer.getMoney()));
                chipBalance.setText(""+mainPlayer.getChips());
                chipsInCart[0] = 0;
                chipsInCartLabel.setText("Chips in Cart:"+chipsInCart[0]);
                cashInCartLabel.setText("Cash in Cart:"+ String.format("%.2f",cashInCart[0]));

            }
        });

        Button exchange = new Button("Exchange Chips");
        exchange.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        exchange.setFont(myFont);
        exchange.setOnAction(e ->{
            if (isValidExchange(mainPlayer,chipsInCart)){
                mainPlayer.setChips(mainPlayer.getChips()-(int) (cashInCart[0]/cashToChipRatio));
                mainPlayer.setMoney(mainPlayer.getMoney()+cashInCart[0]);
                cashBalance.setText(String.format("%.2f",mainPlayer.getMoney()));
                chipBalance.setText(""+mainPlayer.getChips());
                cashInCart[0] = 0;
                chipsInCartLabel.setText("Chips in Cart:"+chipsInCart[0]);
                cashInCartLabel.setText("Cash in Cart:"+ String.format("%.2f",cashInCart[0]));
            }
        });

        exchange.setLayoutX(430);
        exchange.setLayoutY(430);

        Button Cash = new Button("Cash");
        Button Chips = new Button("Chips");
        Cash.setLayoutX(650);
        Cash.setLayoutY(480);
        Cash.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        Cash.setFont(myFont);
        Chips.setLayoutX(650);
        Chips.setLayoutY(430);
        Chips.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        Chips.setFont(myFont);
        Cash.setOnAction(e -> {
            isCashingChips[0] = true;  // You're cashing in chips (converting chips to cash)
            Cash.setVisible(false);
            Cash.setDisable(true);
            Chips.setVisible(true);
            Chips.setDisable(false);
            chipsInCart[0]=0;
            cashInCart[0] = 0;
            cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);
            chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);

        });


        Chips.setDisable(true);
        Chips.setVisible(false);
        Chips.setOnAction(e -> {
            isCashingChips[0] = false; // You're buying chips with cash
            Chips.setVisible(false);
            Chips.setDisable(true);
            Cash.setVisible(true);
            Cash.setDisable(false);
            chipsInCart[0] = 0;
            cashInCart[0] = 0;
            chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);
            cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);

        });

        Button increase10 = new Button();
        increase10.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        increase10.setLayoutX(562);
        increase10.setLayoutY(50);
        increase10.setVisible(false);
        increase10.setOnAction(e -> {
            if (!isCashingChips[0]) { // Buying chips
                if (chipsInCart[0] + 10 <= mainPlayer.getMoney() / cashToChipRatio) {
                    chipsInCart[0] += 10;
                    chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);
                }
            } else { // Exchanging chips for cash
                if (cashInCart[0] + 100 <= mainPlayer.getChips() * cashToChipRatio) {
                    cashInCart[0] += 100;
                    cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);
                }
            }
        });

        increase10.setOnMouseEntered( e->{
            increase10.setVisible(true);
        });
        increase10.setOnMouseExited(e->{
            increase10.setVisible(false);
        });


        Button decrease10 = new Button();
        decrease10.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        decrease10.setLayoutX(525);
        decrease10.setLayoutY(83);
        decrease10.setOnAction(e -> {
            if (!isCashingChips[0]) {
                if (chipsInCart[0] - 10 >= 0) {
                    chipsInCart[0] -= 10;
                    chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);
                }
            } else {
                if (cashInCart[0] - 100 >= 0) {
                    cashInCart[0] -= 100;
                    cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);
                }
            }
        });

        ImageView up10Arrow= new ImageView(new Image("/ui/greenArrowHighlight.png"));
        ImageView down10Arrow= new ImageView(new Image("/ui/redArrowHighlight.png"));
        up10Arrow.setFitWidth(103);
        up10Arrow.setFitHeight(32);
        down10Arrow.setFitWidth(103);
        down10Arrow.setFitHeight(32);
        increase10.setGraphic(up10Arrow);
        decrease10.setGraphic(down10Arrow);


        Button increase50 = new Button();
        increase50.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        increase50.setLayoutX(562);
        increase50.setLayoutY(130);
        increase50.setOnAction(e -> {
            if (!isCashingChips[0]) {
                if (chipsInCart[0] + 50 <= mainPlayer.getMoney() / cashToChipRatio) {
                    chipsInCart[0] += 50;
                    chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);
                }
            } else {
                if (cashInCart[0] + 500 <= mainPlayer.getChips() * cashToChipRatio) {
                    cashInCart[0] += 500;
                    cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);
                }
            }
        });


        Button decrease50 = new Button();
        decrease50.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        decrease50.setLayoutX(525);
        decrease50.setLayoutY(163);
        decrease50.setOnAction(e -> {
            if (!isCashingChips[0]) {
                if (chipsInCart[0] - 50 >= 0) {
                    chipsInCart[0] -= 50;
                    chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);
                }
            } else {
                if (cashInCart[0] - 500 >= 0) {
                    cashInCart[0] -= 500;
                    cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);
                }
            }
        });

        ImageView up50Arrow= new ImageView(new Image("/ui/greenArrowHighlight.png"));
        ImageView down50Arrow= new ImageView(new Image("/ui/redArrowHighlight.png"));
        up50Arrow.setFitWidth(103);
        up50Arrow.setFitHeight(32);
        down50Arrow.setFitWidth(103);
        down50Arrow.setFitHeight(32);
        increase50.setGraphic(up50Arrow);
        decrease50.setGraphic(down50Arrow);

        Button increase250 = new Button();
        increase250.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        increase250.setLayoutX(562);
        increase250.setLayoutY(210);
        increase250.setOnAction(e -> {
            if (!isCashingChips[0]) {
                if (chipsInCart[0] + 250 <= mainPlayer.getMoney() / cashToChipRatio) {
                    chipsInCart[0] += 250;
                    chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);
                }
            } else {
                if (cashInCart[0] + 2500 <= mainPlayer.getChips() * cashToChipRatio) {
                    cashInCart[0] += 2500;
                    cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);
                }
            }
        });

        Button decrease250 = new Button();
        decrease250.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        decrease250.setLayoutX(525);
        decrease250.setLayoutY(243);
        decrease250.setOnAction(e -> {
            if (!isCashingChips[0]) {
                if (chipsInCart[0] - 250 >= 0) {
                    chipsInCart[0] -= 250;
                    chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);
                }
            } else {
                if (cashInCart[0] - 2500 >= 0) {
                    cashInCart[0] -= 2500;
                    cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);
                }
            }
        });

        ImageView up250Arrow= new ImageView(new Image("/ui/greenArrowHighlight.png"));
        ImageView down250Arrow= new ImageView(new Image("/ui/redArrowHighlight.png"));
        up250Arrow.setFitWidth(103);
        up250Arrow.setFitHeight(32);
        down250Arrow.setFitWidth(103);
        down250Arrow.setFitHeight(32);
        increase250.setGraphic(up250Arrow);
        decrease250.setGraphic(down250Arrow);

        Button increase1000 = new Button();
        increase1000.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        increase1000.setLayoutX(562);
        increase1000.setLayoutY(290);
        increase1000.setOnAction(e -> {
            if (!isCashingChips[0]) {
                if (chipsInCart[0] + 1000 <= mainPlayer.getMoney() / cashToChipRatio) {
                    chipsInCart[0] += 1000;
                    chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);
                }
            } else {
                if (cashInCart[0] + 10000 <= mainPlayer.getChips() * cashToChipRatio) {
                    cashInCart[0] += 10000;
                    cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);
                }
            }
        });

        Button decrease1000 = new Button();
        decrease1000.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        decrease1000.setLayoutX(525);
        decrease1000.setLayoutY(323);
        decrease1000.setOnAction(e -> {
            if (!isCashingChips[0]) {
                if (chipsInCart[0] - 1000 >= 0) {
                    chipsInCart[0] -= 1000;
                    chipsInCartLabel.setText("Chips in Cart: " + chipsInCart[0]);
                }
            } else {
                if (cashInCart[0] - 10000 >= 0) {
                    cashInCart[0] -= 10000;
                    cashInCartLabel.setText("Cash in Cart: " + cashInCart[0]);
                }
            }
        });

        ImageView up1000Arrow= new ImageView(new Image("/ui/greenArrowHighlight.png"));
        ImageView down1000Arrow= new ImageView(new Image("/ui/redArrowHighlight.png"));
        up1000Arrow.setFitWidth(103);
        up1000Arrow.setFitHeight(32);
        down1000Arrow.setFitWidth(103);
        down1000Arrow.setFitHeight(32);
        increase1000.setGraphic(up1000Arrow);
        decrease1000.setGraphic(down1000Arrow);



        popupBank.getChildren().addAll(BankMenuImage,bankMenu,
                closeBank,increase10,increase50,increase250,increase1000,decrease10,decrease50,decrease250,decrease1000,
                cashBalance,chipBalance,
                buy,exchange,Cash,Chips,chipsInCartLabel,cashInCartLabel);

        // Bank button opens it
        Bank.setOnAction(e -> {
            popupBank.setVisible(true);
        });



        // ⬇️ Popup overlay
        Pane popupAchievements = new Pane();
       ImageView AchievementMenuImage = new ImageView(new Image("/backgrounds/achievments.png"));
        AchievementMenuImage.setPreserveRatio(false);
        AchievementMenuImage.setMouseTransparent(true);
        AchievementMenuImage.fitWidthProperty().bind(popupAchievements.widthProperty());
        AchievementMenuImage.fitHeightProperty().bind(popupAchievements.heightProperty());


        popupAchievements.setPrefSize(960, 540);
        popupAchievements.setLayoutX(200);
        popupAchievements.setLayoutY(100);
        popupAchievements.setVisible(false);

        ArrayList<Image> achievesStatusImage = new ArrayList<Image>();
        achievesStatusImage.add(new Image("/ui/owned.png"));
        achievesStatusImage.add(new Image("/ui/purchase.png"));
        ImageView ringStatus = new ImageView(achievesStatusImage.get(1));
        ringStatus.setPreserveRatio(false);
        ringStatus.setFitWidth(180);
        ringStatus.setFitHeight(48);
        ringStatus.setSmooth(true);

        ImageView carStatus = new ImageView(achievesStatusImage.get(1));
        carStatus.setPreserveRatio(false);
        carStatus.setFitWidth(180);
        carStatus.setFitHeight(48);
        carStatus.setSmooth(true);

        ImageView houseStatus = new ImageView(achievesStatusImage.get(1));
        houseStatus.setPreserveRatio(false);
        houseStatus.setFitWidth(180);
        houseStatus.setFitHeight(48);
        houseStatus.setSmooth(true);

        ImageView islandStatus = new ImageView(achievesStatusImage.get(1));
        islandStatus.setPreserveRatio(false);
        islandStatus.setFitWidth(180);
        islandStatus.setFitHeight(48);
        islandStatus.setSmooth(true);

        Label prizes = new Label("Prizes");
        prizes.setLayoutX(740);
        prizes.setLayoutY(110);
        prizes.setFont(myFont);
        prizes.setTextFill(Color.GOLD);

        Label achieveCashBalance = new Label(""+ mainPlayer.getMoney());
        achieveCashBalance.setLayoutX(750);
        achieveCashBalance.setLayoutY(43);
        achieveCashBalance.setFont(myFont);
        achieveCashBalance.setTextFill(Color.GOLD);



        // Close button inside Achievement popup
        Button closeAchieve = new Button ();
        closeAchieve.setStyle("-fx-background-radius: 100%; -fx-padding: 0;");
        closeAchieve.setPrefSize(105,105);

        closeAchieve.setLayoutX(15);
        closeAchieve.setLayoutY(425);
        closeAchieve.setOnAction(e -> popupAchievements.setVisible(false));

        boolean[] achievemntUnlocked = {false,false,false,false};

        Button unlock1 = new Button("");
        unlock1.setLayoutX(400);
        unlock1.setLayoutY(43);
        unlock1.setPrefSize(180, 48);
        unlock1.setGraphic(ringStatus);
        unlock1.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        unlock1.addEventFilter(MouseEvent.MOUSE_PRESSED, MouseEvent::consume);
        unlock1.setOnAction(e -> {
            if (mainPlayer.getMoney() >= 8000) {
                ringStatus.setImage(achievesStatusImage.getFirst()); // FIXED
                mainPlayer.setMoney(mainPlayer.getMoney() - 8000);
                achievemntUnlocked[0] = true;
            }
        });

        Button unlock2 = new Button("");
        unlock2.setLayoutX(400);
        unlock2.setLayoutY(123);
        unlock2.setPrefSize(180, 48);
        unlock2.setGraphic(carStatus);
        unlock2.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        // Car
        unlock2.setOnAction(e -> {
            if (mainPlayer.getMoney() >= 150000) {
                carStatus.setImage(achievesStatusImage.get(0)); // FIXED
                mainPlayer.setMoney(mainPlayer.getMoney() - 150000);
                achievemntUnlocked[1] = true;
            }
        });

        Button unlock3 = new Button("");
        unlock3.setLayoutX(400);
        unlock3.setLayoutY(203);
        unlock3.setPrefSize(180, 48);
        unlock3.setGraphic(houseStatus);
        unlock3.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        // House
        unlock3.setOnAction(e -> {
            if (mainPlayer.getMoney() >= 1600000) {
                houseStatus.setImage(achievesStatusImage.get(0)); // FIXED
                mainPlayer.setMoney(mainPlayer.getMoney() - 1600000);
                achievemntUnlocked[2] = true;
            }
        });

        Button unlock4 = new Button("");
        unlock4.setLayoutX(400);
        unlock4.setLayoutY(283);
        unlock4.setPrefSize(180, 48);
        unlock4.setGraphic(islandStatus);
        unlock4.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        unlock4.setOnAction(e -> {
            if (mainPlayer.getMoney() >= 25000000) {
                islandStatus.setImage(achievesStatusImage.get(0)); // This one was correct
                mainPlayer.setMoney(mainPlayer.getMoney() - 25000000);
                achievemntUnlocked[3] = true;
            }
        });

        popupAchievements.getChildren().addAll(AchievementMenuImage,unlock1,unlock2,unlock3,unlock4,prizes,achieveCashBalance,closeAchieve);



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
            Scene blackjackScene = createBlackJackScene(stage,mainPlayer);
            stage.setScene(blackjackScene);
        });

        // Add Slots button
        Button playSlots = new Button("Play Slots");
        playSlots.setPrefSize(190, 150);
        playSlots.setLayoutX(550);
        playSlots.setLayoutY(140);
        playSlots.setOnAction(e -> {
            Scene slotsScene = createSlotsScene(stage, mainPlayer);
            stage.setScene(slotsScene);

        });

        // Add Holdem button
        Button playHoldem = new Button("Play Texas Holdem");
        playHoldem.setPrefSize(105, 199);
        playHoldem.setLayoutX(729);
        playHoldem.setLayoutY(325);
        playHoldem.setRotate(20);

        playHoldem.setOnAction(e -> {
            Scene HoldemScene = createTexasHoldemScene(stage, mainPlayer);
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

    /**
     * isValidSale method - verify if player has high enough balance to make purchase
     * @param player player object using store
     * @param cartCashTotal the amount of cash attempting to be exchanged
     * @return boolean value that represent a valid purchase or not
     */
    public boolean isValidSale(Player player,double[] cartCashTotal) {

        //Checks if player has more chips than they can afford
        double spendLimit = player.getMoney();
        return cartCashTotal[0] <= spendLimit;



    }

    /**
     * isValidExchange -  verify if player has high enough balance to make purchase
     * @param player player object using store
     * @param cartChipTotal the amount of chip being attempted for exchange
     * @return boolean value that represent a valid purchase or not
     */
    public boolean isValidExchange(Player player,int[] cartChipTotal) {

        //Checks if player has more money than chips they can exchange
        int spendLimit = player.getChips();
        return cartChipTotal[0] <= spendLimit;

    }

    /**
     * Creates and returns the scene for the Blackjack game.
     * This method initializes the Blackjack table, dealer/player labels, betting system, buttons, and gameplay flow.
     * Handles UI updates, game resets, and player interactions such as hitting, standing, doubling down, and replaying.
     *
     * @param stage the current JavaFX Stage where the scene will be set
     * @param player1 the Player object whose chip balance will be used and updated
     * @return the fully constructed Blackjack game Scene
     */
    private Scene createBlackJackScene(Stage stage, Player player1) {
        final double SCREEN_WIDTH = 1280;
        final double SCREEN_HEIGHT = 720;

        StackPane root = new StackPane();
        Pane gameContent = new Pane();
        gameContent.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        Font myFont = Font.loadFont(getClass().getResourceAsStream("Super-Mario-64-DS.ttf"), 20);
        Player mainPlayer = player1;

        ImageView blackJackBackGround = new ImageView(new Image("/backgrounds/tableBackDrop.png"));
        blackJackBackGround.setPreserveRatio(false);
        blackJackBackGround.setMouseTransparent(true);
        blackJackBackGround.fitWidthProperty().bind(gameContent.widthProperty());
        blackJackBackGround.fitHeightProperty().bind(gameContent.heightProperty());

        ImageView blackJackTable = new ImageView(new Image("/gameAssets/table.png"));
        blackJackTable.setPreserveRatio(false);
        blackJackTable.setMouseTransparent(true);
        blackJackTable.fitWidthProperty().bind(gameContent.widthProperty());
        blackJackTable.fitHeightProperty().bind(gameContent.heightProperty());

        ImageView kirkDealer = new ImageView(new Image("/gameAssets/kirkDealer.png"));
        kirkDealer.setScaleX(4);
        kirkDealer.setScaleY(4);
        kirkDealer.setTranslateY(-104);
        StackPane imageHolder = new StackPane(kirkDealer);
        imageHolder.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(imageHolder);

        final BlackjackPlayer[] player = new BlackjackPlayer[1];
        final Blackjack[] game = new Blackjack[1];
        final BlackjackPlayer[] dealer = new BlackjackPlayer[1];

        Label dealerLabel = new Label("Dealer:");
        dealerLabel.setLayoutX(600);
        dealerLabel.setLayoutY(395);
        dealerLabel.setFont(myFont);

        Label playerLabel = new Label("Player:");
        playerLabel.setLayoutX(550);
        playerLabel.setLayoutY(685);
        playerLabel.setFont(myFont);

        Label statusLabel = new Label("");
        statusLabel.setLayoutX(100);
        statusLabel.setLayoutY(620);

        Label balanceLabel = new Label("Balance: "+mainPlayer.getChips());
        balanceLabel.setLayoutX(120);
        balanceLabel.setLayoutY(470);
        balanceLabel.setFont(myFont);

        Label betLabel = new Label("Bet: 10 chips");
        betLabel.setLayoutX(130);
        betLabel.setLayoutY(550);
        betLabel.setFont(myFont);

        Button increaseBet = new Button("");
        increaseBet.setLayoutX(191);
        increaseBet.setLayoutY(510);
        increaseBet.setPrefSize(30, 30);
        increaseBet.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Button decreaseBet = new Button("");
        decreaseBet.setLayoutX(191);
        decreaseBet.setLayoutY(585);
        decreaseBet.setPrefSize(30, 30);
        decreaseBet.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        HBox dealerCardsBox = new HBox(30);
        dealerCardsBox.setAlignment(Pos.CENTER);
        StackPane dealerCardPane = new StackPane(dealerCardsBox);
        dealerCardPane.setLayoutX(0);
        dealerCardPane.setLayoutY(430);
        dealerCardPane.setPrefWidth(SCREEN_WIDTH);

        HBox playerCardsBox = new HBox(20);
        playerCardsBox.setAlignment(Pos.CENTER);
        StackPane playerCardPane = new StackPane(playerCardsBox);
        playerCardPane.setLayoutX(0);
        playerCardPane.setLayoutY(605);
        playerCardPane.setPrefWidth(SCREEN_WIDTH);

        Button hitButton = new Button("");
        hitButton.setLayoutX(900);
        hitButton.setLayoutY(400);
        hitButton.setPrefSize(90, 70);
        hitButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        ImageView hitImage = new ImageView(new Image("/ui/hit.png"));
        hitImage.setSmooth(true);
        hitImage.setFitWidth(90);
        hitImage.setFitHeight(54);
        hitImage.setMouseTransparent(true);

        Button standButton = new Button("");
        standButton.setLayoutX(1040);
        standButton.setLayoutY(400);
        standButton.setPrefSize(90, 70);
        standButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        ImageView StandImage = new ImageView(new Image("/ui/stand.png"));
        StandImage.setSmooth(true);
        StandImage.setFitWidth(92);
        StandImage.setFitHeight(54);
        StandImage.setMouseTransparent(true);

        Button doubleButton = new Button("");
        doubleButton.setLayoutX(1040);
        doubleButton.setLayoutY(500);
        doubleButton.setPrefSize(90, 70);
        doubleButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        ImageView doubleDownImage = new ImageView(new Image("/ui/doublDown.png"));
        doubleDownImage.setSmooth(true);
        doubleDownImage.setFitWidth(92);
        doubleDownImage.setFitHeight(54);

        doubleDownImage.setMouseTransparent(true);

        ImageView betAdjust = new ImageView(new Image("/ui/arrows.png"));
        betAdjust.setScaleX(3);
        betAdjust.setScaleY(3);
        betAdjust.setLayoutX(200);
        betAdjust.setLayoutY(545);
        betAdjust.setMouseTransparent(true);
        hitButton.setGraphic(hitImage);
        standButton.setGraphic(StandImage);
        doubleButton.setGraphic(doubleDownImage);





        Button playAgainButton = new Button("Play Again");
        playAgainButton.setLayoutX(850);
        playAgainButton.setLayoutY(650);
        playAgainButton.setPrefSize(150, 50);
        playAgainButton.setVisible(false);

        Button closeButton = new Button("Close");
        closeButton.setLayoutX(1120);
        closeButton.setLayoutY(650);
        closeButton.setPrefSize(90, 90);
        closeButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        closeButton.setFont(myFont);
        closeButton.setOnAction(e -> stage.setScene(createGameSelectScene(stage, mainPlayer)));

        int[] playerBalance = {player1.getChips()};
        int[] betAmount = {10};
        final boolean[] wasDoubleDown = {false};

        resetGame(player, game, dealer, dealerLabel, playerLabel, statusLabel, dealerCardsBox, playerCardsBox, hitButton, standButton, playAgainButton);

        balanceLabel.setText("Chip Balance: " + playerBalance[0]);
        betLabel.setText("Bet: " + betAmount[0] + " chips");

        increaseBet.setOnAction(e -> {
            if (playerBalance[0] - betAmount[0] >= 10) {
                betAmount[0] += 10;
                betLabel.setText("Bet: " + betAmount[0] + " chips");
            } else {
                new Alert(Alert.AlertType.WARNING, "You do not have enough chips to increase your bet.").showAndWait();
            }
        });

        decreaseBet.setOnAction(e -> {
            if (betAmount[0] > 10) {
                betAmount[0] -= 10;
                betLabel.setText("Bet: " + betAmount[0] + " chips");
            }
        });

        hitButton.setOnAction(e -> {
            Card card = game[0].deck.getCard(0);
            player[0].cards.add(card);
            game[0].deck.remove(card);
            updateUI(player[0], dealer[0], dealerLabel, playerLabel, dealerCardsBox, playerCardsBox);
            doubleButton.setDisable(true);
            if (player[0].getScore() > 21) {
                statusLabel.setText("You bust!");
                playerBalance[0] -= betAmount[0];
                balanceLabel.setText("Balance: " + playerBalance[0] + " chips");
                player1.setChips(playerBalance[0]);
                hitButton.setDisable(true);
                standButton.setDisable(true);
                playAgainButton.setVisible(true);
            }
        });

        standButton.setOnAction(e -> {
            while (dealer[0].getScore() < 17) {
                Card card = game[0].deck.getCard(0);
                dealer[0].cards.add(card);
                game[0].deck.remove(card);
            }
            int playerScore = player[0].getScore();
            int dealerScore = dealer[0].getScore();
            dealerLabel.setText("Dealer Score: " + dealerScore);
            updateCardDisplay(dealerCardsBox, dealer[0].cards);
            if (playerScore > 21) {
                statusLabel.setText("You bust!");
                playerBalance[0] -= betAmount[0];
            } else if (dealerScore > 21 || playerScore > dealerScore) {
                statusLabel.setText("You win!");
                playerBalance[0] += betAmount[0];
            } else if (playerScore == dealerScore) {
                statusLabel.setText("Push.");
            } else {
                statusLabel.setText("You lose.");
                playerBalance[0] -= betAmount[0];
            }
            balanceLabel.setText("Balance: " + playerBalance[0] + " chips");
            player1.setChips(playerBalance[0]);
            hitButton.setDisable(true);
            standButton.setDisable(true);
            doubleButton.setDisable(true);
            playAgainButton.setVisible(true);
        });

        doubleButton.setOnAction(e -> {
            if (playerBalance[0] >= betAmount[0]) {
                playerBalance[0] -= betAmount[0];
                betAmount[0] *= 2;
                wasDoubleDown[0] = true;
                betLabel.setText("Bet: " + betAmount[0] + " chips");
                Card card = game[0].deck.getCard(0);
                player[0].cards.add(card);
                game[0].deck.remove(card);
                updateUI(player[0], dealer[0], dealerLabel, playerLabel, dealerCardsBox, playerCardsBox);
                hitButton.setDisable(true);
                doubleButton.setDisable(true);
                standButton.fire();
            } else {
                new Alert(Alert.AlertType.WARNING, "You do not have enough chips to double down.").showAndWait();
            }
        });

        playAgainButton.setOnAction(e -> {
            resetGame(player, game, dealer, dealerLabel, playerLabel, statusLabel, dealerCardsBox, playerCardsBox, hitButton, standButton, playAgainButton);
            doubleButton.setDisable(false);
            betAmount[0] = 10;
            betLabel.setText("Bet: " + betAmount[0] + " chips");
            balanceLabel.setText("Balance: " + playerBalance[0] + " chips");
            player1.setChips(playerBalance[0]);
        });

        Pane buttons = new Pane(increaseBet, decreaseBet, playAgainButton, closeButton, hitButton, standButton, doubleButton);
        gameContent.getChildren().addAll(
                blackJackBackGround, blackJackTable, imageHolder,StandImage,doubleDownImage,hitImage,betAdjust,
                dealerLabel, playerLabel, statusLabel, balanceLabel, betLabel, dealerCardPane, playerCardPane, buttons
        );

        root.getChildren().add(gameContent);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }








    /**
     * updateCardDisplay() - Clears the HBox and displays all cards from the player's hand as images
     *
     * @param box   The HBox where card images will be displayed
     * @param cards The CardList representing the player's hand
     */
    private void updateCardDisplay(HBox box, CardList cards) {
        box.getChildren().clear(); // Clear existing images

        for (Card card : cards.getCardList()) {
            // Build the file path based on card rank and suit
            String imagePath = "/gameAssets/cards/" + card.getRank().toUpperCase() + card.getSuit().toUpperCase().charAt(0) + ".png";

            // Load the image
            Image img = new Image(getClass().getResource(imagePath).toExternalForm());

            // Create and configure the ImageView
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(60);
            imageView.setFitHeight(80);

            // Add to HBox
            box.getChildren().add(imageView);
        }
    }

    /**
     * updateDealerHidden() - Displays the dealer's first card and hides the second card with a card back
     *
     * @param box    The HBox where dealer's cards will be displayed
     * @param dealer The BlackjackPlayer representing the dealer
     */
    private void updateDealerHidden(HBox box, BlackjackPlayer dealer) {
        box.getChildren().clear(); // Clear previous dealer cards

        // Load visible dealer card
        Card visible = dealer.cards.getCard(0);
        String visiblePath = "/gameAssets/cards/" + visible.getRank().toUpperCase() + visible.getSuit().toUpperCase().charAt(0) + ".png";
        ImageView card1 = new ImageView(new Image(getClass().getResource(visiblePath).toExternalForm()));
        card1.setFitWidth(60);
        card1.setFitHeight(80);

        // Load hidden card (card back)
        ImageView card2 = new ImageView(new Image(getClass().getResource("/gameAssets/cards/back.png").toExternalForm()));
        card2.setFitWidth(60);
        card2.setFitHeight(80);

        // Add both cards to the HBox
        box.getChildren().addAll(card1, card2);
    }

    /**
     * updateUI() - Updates the labels and card displays for both the player and the dealer
     *
     * @param player       The current BlackjackPlayer
     * @param dealer       The dealer BlackjackPlayer
     * @param dealerLabel  The label displaying the dealer's hand
     * @param playerLabel  The label displaying the player's hand
     * @param dealerBox    The HBox where dealer cards are shown
     * @param playerBox    The HBox where player cards are shown
     */
    private void updateUI(BlackjackPlayer player, BlackjackPlayer dealer, Label dealerLabel, Label playerLabel, HBox dealerBox, HBox playerBox) {
        dealerLabel.setText("Dealer");
        playerLabel.setText("Player Score: " + player.getScore());
        updateDealerHidden(dealerBox, dealer);
        updateCardDisplay(playerBox, player.cards);
    }

    /**
     * resetGame() - Resets the game state and UI for a new round of Blackjack
     *
     * @param player           The player array to initialize with a new BlackjackPlayer
     * @param game             The game array to initialize with a new Blackjack instance
     * @param dealer           The dealer array to set the dealer from the new game
     * @param dealerLabel      Label to show dealer info
     * @param playerLabel      Label to show player info
     * @param statusLabel      Label to show win/loss status
     * @param dealerCardsBox   HBox where dealer cards are displayed
     * @param playerCardsBox   HBox where player cards are displayed
     * @param hitButton        Button to trigger "Hit"
     * @param standButton      Button to trigger "Stand"
     * @param playAgainButton  Button to restart the game
     */
    private void resetGame(
            BlackjackPlayer[] player,
            Blackjack[] game,
            BlackjackPlayer[] dealer,
            Label dealerLabel,
            Label playerLabel,
            Label statusLabel,
            HBox dealerCardsBox,
            HBox playerCardsBox,
            Button hitButton,
            Button standButton,
            Button playAgainButton
    ) {
        // Create new player and game setup
        player[0] = new BlackjackPlayer(100, true);
        Player[] players = new Player[]{player[0]};
        game[0] = new Blackjack(players);
        game[0].setup();
        dealer[0] = game[0].getDealer();

        // Refresh UI
        updateUI(player[0], dealer[0], dealerLabel, playerLabel, dealerCardsBox, playerCardsBox);
        statusLabel.setText("");
        hitButton.setDisable(false);
        standButton.setDisable(false);
        playAgainButton.setVisible(false);
    }



    /**
     * Creates and returns the slot machine game scene for the player.
     *
     * This method initializes the slot machine interface, loads the custom font for styling,
     * and prepares all visual and interactive components for the slot game. The provided
     * player instance is used to reflect and update the player's money and chip balances.
     *
     * @param stage  the main application stage used for scene transitions
     * @param player the player object containing current money and chip information
     * @return a Scene object representing the slot machine game screen
     */
    private Scene createSlotsScene(Stage stage, Player player) {
        final double SCREEN_WIDTH = 1280;
        final double SCREEN_HEIGHT = 720;

        Player mainPlayer = player;
        // Create the main slot machine object
        Font marioFont = null;
        try {
            marioFont = Font.loadFont(getClass().getResourceAsStream("Super-Mario-64-DS.ttf"), 20);
            if (marioFont == null) {
                System.out.println("Failed to load super_mario.ttf - check file path");
                // Try loading from resources folder
                marioFont = Font.loadFont(getClass().getResourceAsStream("Super-Mario-64-DS.ttf"), 20);
            }
        } catch (Exception e) {
            System.out.println("Error loading font: " + e.getMessage());
        }

        // Array of image filenames representing different symbols on the slot machine
        final String[] SYMBOL_FILES = {
                "cherry.png", "lemon.png", "orange.png", "grape.png", "kirkDealer.png"
        };

        // Probability weights for each symbol
        final double[] SYMBOL_WEIGHTS = {
                0.20,  // cherry - common
                0.15,  // lemon
                0.12,  // orange
                0.10,  // grape
                0.05   // kirkDealer - rare but achievable
        };

        //  0.25 to allow more matching possibilities
        final double THREE_DIFFERENT_SYMBOLS_PROBABILITY = 0.25;

        //  Add probability to force three matching symbols (20% chance)
        final double THREE_MATCHING_SYMBOLS_PROBABILITY = 0.20;

        // This gives kirkDealer a chance to appear even in the "three different" scenario
        final double KIRK_DEALER_IN_DIFFERENT_PROBABILITY = 0.15;

        // Initialize components
        ImageView[] reelImages = new ImageView[3];
        TextField betInput;
        Label balanceLabel;
        Label resultLabel;
        Random random = new Random();

        // Initialize audio
        MediaPlayer spinSound;
        MediaPlayer loseSound;

        try {
            Media spinMedia = new Media(new File("resources/sfx/spin_sound_trimmed.mp3").toURI().toString());
            spinSound = new MediaPlayer(spinMedia);
            Media loseMedia = new Media(new File("resources/sfx/youtube__asNhzXq72w_audio.mp3").toURI().toString());
            loseSound = new MediaPlayer(loseMedia);
        } catch (Exception e) {
            System.out.println("Error loading sound files: " + e.getMessage());
            spinSound = null;
            loseSound = null;
        }

        // Initialize balance (placeholder value)
        double[] balance = {mainPlayer.getMoney()};

        // Initialize main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(40));

        BackgroundImage backgroundImage = new BackgroundImage(
                //file: insert location
                new Image("/backgrounds/slots.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(backgroundImage));

        // Create and style the header label
        Label headerLabel = new Label("KIRK'S SLOT MACHINE");
        if (marioFont != null) {
            headerLabel.setFont(Font.font(marioFont.getFamily(), FontWeight.BOLD, 48));
        } else {
            headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        }
        headerLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        root.setTop(headerLabel);
        BorderPane.setAlignment(headerLabel, Pos.CENTER);
        BorderPane.setMargin(headerLabel, new Insets(0, 0, 50, 0));

        // Create container for the slot machine reels
        HBox reelsBox = new HBox(20);
        reelsBox.setAlignment(Pos.CENTER);
        reelsBox.setPadding(new Insets(-400,-420,30,-50));

        // Initialize each of the three reel image views
        for (int i = 0; i < 3; i++) {
            reelImages[i] = new ImageView();
            reelImages[i].setFitWidth(100);
            reelImages[i].setFitHeight(100);
            reelsBox.getChildren().add(reelImages[i]);
        }

        // Create and style the result message label
        resultLabel = new Label("Place your bet and spin!");
        if (marioFont != null) {
            resultLabel.setFont(Font.font(marioFont.getFamily(), FontWeight.BOLD, 24));
        } else {
            resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        }
        resultLabel.setTextFill(javafx.scene.paint.Color.RED);
        resultLabel.setAlignment(Pos.CENTER_LEFT);
        resultLabel.setMinWidth(100);
        StackPane.setAlignment(resultLabel, Pos.CENTER_LEFT);

        // Add these lines to create a box around the label
        resultLabel.setBackground(new Background(new BackgroundFill(
                javafx.scene.paint.Color.rgb(0, 0, 0, 0.7), // Semi-transparent black background
                new CornerRadii(10), // Rounded corners (10px radius)
                Insets.EMPTY
        )));
        resultLabel.setBorder(new Border(new BorderStroke(
                javafx.scene.paint.Color.GREEN, // GREEN border color
                BorderStrokeStyle.SOLID, // Solid border style
                new CornerRadii(10), // Rounded corners (10px radius)
                new BorderWidths(2) // 2px border width
        )));
        resultLabel.setPadding(new Insets(1, 20, 10, 20));
        resultLabel.setTranslateX(175);
        resultLabel.setTranslateY(-80);

        // Container for reels and result message
        VBox centerBox = new VBox(40);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(200,0,0,-100));
        centerBox.getChildren().addAll(reelsBox, resultLabel);
        root.setCenter(centerBox);

        // Container for game controls (bet input, spin button)
        VBox controlsBox = new VBox(20);
        controlsBox.setAlignment(Pos.CENTER);

        // Create and style balance display label
        balanceLabel = new Label("BALANCE: $" + String.format("%.2f", balance[0]));
        if (marioFont != null) {
            balanceLabel.setFont(Font.font(marioFont.getFamily(), FontWeight.BOLD, 24));
        } else {
            balanceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        }
        balanceLabel.setTextFill(javafx.scene.paint.Color.YELLOW);

        // Container for bet label and input field
        HBox betBox = new HBox(15);
        betBox.setAlignment(Pos.CENTER);

        // Create and style bet label
        Label betLabel = new Label("Your Bet: $");
        if (marioFont != null) {
            betLabel.setFont(Font.font(marioFont.getFamily(), 20));
        } else {
            betLabel.setFont(Font.font("Arial", 20));
        }
        betLabel.setTextFill(javafx.scene.paint.Color.WHITE);

        // Create and style bet input field
        betInput = new TextField();
        if (marioFont != null) {
            betInput.setFont(Font.font(marioFont.getFamily(), 20));
        } else {
            betInput.setFont(Font.font("Arial", 20));
        }
        betInput.setMaxWidth(200);
        betInput.setPromptText("Enter bet");

        // Add label and input field to bet container
        betBox.getChildren().addAll(betLabel, betInput);

        Button spinButton = new Button("SPIN");
        if (marioFont != null) {
            spinButton.setFont(Font.font(marioFont.getFamily(), FontWeight.BOLD, 24));
        } else {
            spinButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        }
        spinButton.setMinWidth(70);
        spinButton.setMinHeight(40);
        spinButton.setTextFill(javafx.scene.paint.Color.RED);

        // Create a stack pane to overlay the spin button on the background
        StackPane overlayPane = new StackPane();
        overlayPane.getChildren().add(spinButton);
        // Position the button to align with handle in the top right
        StackPane.setAlignment(spinButton, Pos.TOP_RIGHT);
        StackPane.setMargin(spinButton, new Insets(-70, 160, 40, 30));
        // Set the overlay pane to cover the entire scene
        root.setRight(overlayPane);

        // Add back button to return to the main menu
        Button backButton = new Button("Return to Lobby");
        if (marioFont != null) {
            backButton.setFont(Font.font(marioFont.getFamily(), FontWeight.BOLD, 20));
        } else {
            backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        }
        backButton.setOnAction(e -> stage.setScene(createGameSelectScene(stage,mainPlayer)));

        // Spin button action
        MediaPlayer finalLoseSound = loseSound;
        MediaPlayer finalSpinSound = spinSound;
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
                if (betAmount > balance[0]) {
                    resultLabel.setText("Not enough money! Your balance is $" + String.format("%.2f", balance[0]));
                    return;
                }

                // Deduct bet from balance
                balance[0] -= betAmount;
                if (finalSpinSound != null) {
                    finalSpinSound.stop();
                    finalSpinSound.seek(javafx.util.Duration.ZERO);
                    finalSpinSound.play();
                }

                // Update balance display
                balanceLabel.setText("Balance: $" + String.format("%.2f", balance[0]));

                // Show spinning message
                resultLabel.setText("Spinning...");

                // Start the reels animation
                animateReels(reelImages,() -> {
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
                                symbolIndex = getRandomSymbol(true, random, KIRK_DEALER_IN_DIFFERENT_PROBABILITY);
                            } while (selectedSymbols.contains(symbolIndex));

                            selectedSymbols.add(symbolIndex);
                            results[i] = symbolIndex;
                            reelImages[i].setImage(new Image("/gameAssets/slots/" + SYMBOL_FILES[symbolIndex]));
                        }

                        // All 3 are guaranteed to be different, so it's a loss
                        if (finalLoseSound != null) {
                            finalLoseSound.stop();
                            finalLoseSound.seek(javafx.util.Duration.ZERO);
                            finalLoseSound.play();
                        }
                    }
                    // Force three matching symbols (20% chance)
                    else if (spinType < THREE_DIFFERENT_SYMBOLS_PROBABILITY + THREE_MATCHING_SYMBOLS_PROBABILITY) {
                        // Select one random symbol using weighted probabilities
                        int symbolIndex = getWeightedRandomSymbol(random, SYMBOL_WEIGHTS);

                        // Set all three reels to show this symbol
                        for (int i = 0; i < 3; i++) {
                            results[i] = symbolIndex;
                            reelImages[i].setImage(new Image("/gameAssets/slots/" + SYMBOL_FILES[symbolIndex]));
                        }
                    }
                    // Regular weighted spin (now 55%)
                    else {
                        // Generate results using weighted probability
                        for (int i = 0; i < 3; i++) {
                            results[i] = getWeightedRandomSymbol(random, SYMBOL_WEIGHTS);
                            reelImages[i].setImage(new Image("/gameAssets/slots/" + SYMBOL_FILES[results[i]]));
                        }
                    }

                    // Calculate winnings based on results
                    double winnings = calculateWinnings(results, betAmount);

                    // Update display based on win/loss
                    if (winnings > 0) {
                        // Add winnings to balance
                        balance[0] += winnings + betAmount;
                        resultLabel.setText("You won $" + String.format("%.2f", winnings) + "!");
                    } else {
                        resultLabel.setText("You lost $" + String.format("%.2f", betAmount));
                        // Play lose sound
                        if (finalLoseSound != null) {
                            finalLoseSound.stop();
                            finalLoseSound.seek(javafx.util.Duration.ZERO);
                            finalLoseSound.play();
                        }
                    }

                    // Update balance display
                    balanceLabel.setText("Balance: $" + String.format("%.2f", balance[0]));
                    mainPlayer.setMoney(balance[0]);
                });

            } catch (NumberFormatException ex) {
                // Handle case where input is not a valid number
                resultLabel.setText("Please enter a valid number!");
            }
        });

        // Add all controls to the controls container
        controlsBox.getChildren().addAll(balanceLabel, betBox, backButton);
        root.setBottom(controlsBox);
        BorderPane.setMargin(controlsBox, new Insets(40, 0, 0, 0));

        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        return scene;
    }

    /**
     * Animates the slot machine reels by displaying a sequence of randomly selected images
     * to simulate spinning. Once the animation is complete, the specified callback is executed.
     *
     * @param reelImages an array of ImageView objects representing the reels to animate
     * @param onFinished a Runnable callback to execute once the animation finishes
     */

    private void animateReels(ImageView[] reelImages, Runnable onFinished) {
        Timeline timeline = new Timeline();
        int cycles = 30;

        // Create animation frames
        for (int i = 0; i < cycles; i++) {
            int index = i;
            // Add keyframe to the timeline
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 75), e -> {
                // For each reel, show a random symbol during animation
                for (int j = 0; j < 3; j++) {
                    int symbolIndex = getWeightedRandomSymbol(new Random(), new double[]{0.20, 0.15, 0.12, 0.10, 0.05});
                    try {
                        // This will be replaced by the actual reelImages array
                        reelImages[j].setImage(new Image("/gameAssets/slots/" + new String[]{"cherry.png", "lemon.png", "orange.png", "grape.png", "kirkDealer.png"}[symbolIndex]));
                    } catch (Exception ex) {
                        System.out.println("Error loading image during animation: " + ex.getMessage());
                    }
                }
            }));
        }

        // Set action to run when animation completes
        timeline.setOnFinished(e -> onFinished.run());
        // Start the animation
        timeline.play();
    }

    /**
     * Returns a random symbol index based on weighted probabilities.
     * This method is used to simulate different symbol rarities.
     *
     * @param random         the Random instance used to generate randomness
     * @param symbolWeights  an array of doubles representing the selection probability for each symbol
     * @return the index of the selected symbol, based on the provided weights
     */

    private int getWeightedRandomSymbol(Random random, double[] symbolWeights) {
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < symbolWeights.length; i++) {
            cumulativeProbability += symbolWeights[i];
            if (randomValue < cumulativeProbability) {
                return i;
            }
        }

        // Fallback (shouldn't happen if weights sum to 1)
        return 0;
    }

    /**
     * Returns a random symbol index, optionally allowing a rare symbol ("kirkDealer")
     * to appear based on a given probability.
     *
     * @param allowKirkDealer        whether the rare symbol is eligible to be selected
     * @param random                 the Random instance used to generate randomness
     * @param kirkDealerProbability the probability of selecting the rare "kirkDealer" symbol
     * @return the selected symbol index (0–4)
     */

    private int getRandomSymbol(boolean allowKirkDealer, Random random, double kirkDealerProbability) {
        // If kirkDealer is allowed and we roll the probability check
        if (allowKirkDealer && random.nextDouble() < kirkDealerProbability) {
            return 4; // Return kirkDealer index
        } else {
            // Only include indices 0-3 (fruits)
            return random.nextInt(4);
        }
    }

    /**
     * Calculates the player's winnings based on the spin result and the amount bet.
     * Awards are given for 2 or 3 matching symbols, with increasing multipliers for rarer symbols.
     *
     * @param results   an array of integers representing the indices of the symbols landed on each reel
     * @param betAmount the amount of money the player bet for this spin
     * @return the amount of money won based on the result; returns 0 if there are no matches
     */

    private double calculateWinnings(int[] results, double betAmount) {
        // Count occurrences of each symbol
        int[] counts = new int[5]; // Match length to SYMBOL_FILES
        for (int r : results) {
            counts[r]++;  // Increment count for this symbol
        }

        // Check for triple match (3 of the same symbol)
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 3) {
                switch (i) {
                    case 0: return betAmount * 1.5;   // cherry - 1.5x multiplier
                    case 1: return betAmount * 2;     // lemon - 2x multiplier
                    case 2: return betAmount * 5;     // orange - 5x multiplier
                    case 3: return betAmount * 10;    // grape - 10x multiplier
                    case 4: return betAmount * 30;    // kirkDealer - 30x multiplier
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
        return 0;  // Player loses their bet
    }


    /**
     * Creates and returns the scene for the Texas Hold'em poker game.
     *
     * This method sets up the main visual layout, including the background and poker table,
     * and initializes the player with their current chips and money state.
     * It uses a StackPane as the base layout for layering game elements, and a Pane for game content.
     *
     * @param stage   the main application window used for managing scene transitions
     * @param player1 the current player whose chip and money data is used for gameplay
     * @return a Scene object representing the Texas Hold'em game interface
     */
    private Scene createTexasHoldemScene(Stage stage, Player player1) {
        final double SCREEN_WIDTH = 1366; // Set window params
        final double SCREEN_HEIGHT = 768;

        Player mainPlayer = new Player(player1.getChips(),false, player1.getMoney()); // mainPlayer has attributes as global player
        StackPane root = new StackPane(); // Declare root to draw

        Pane gameContent = new Pane(); // Pane containing all of the game content
        ImageView holdemBackground = new ImageView(new Image("/backgrounds/tableBackDrop.png")); // Holdem backdrop
        holdemBackground.setMouseTransparent(true);
        holdemBackground.setScaleX(4); // scale up 4x
        holdemBackground.setScaleY(4);

        ImageView holdemTable = new ImageView(new Image("/gameAssets/holdemTable.png")); // table sprite
        holdemTable.setMouseTransparent(true);
        holdemTable.setScaleX(4); // scale up 4 times
        holdemTable.setScaleY(4);
        holdemTable.setTranslateY(-30); // Move down a bit
        gameContent.setPrefSize(1366, 768);

        ImageView kirkDealer = new ImageView(new Image("/gameAssets/kirkDealer.png")); // MAKE KIRK
        kirkDealer.setScaleX(4); // scale up 4
        kirkDealer.setScaleY(4);
        kirkDealer.setTranslateY(-150); // adjust pos
        StackPane imageHolder = new StackPane(kirkDealer);
        imageHolder.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT); // match your main screen size

        HoldemPlayer mainHoldemPlayer = new HoldemPlayer(mainPlayer.getChips(), true); // Create holdemPlayer with mainPlayer
                                                                                              // attributes
        /// CREATE HOLDEM GAME ///
        Player[] players = new Player[4]; // array of players
        players[0] = new HoldemPlayer(1000, false); // Create players
        players[1] = new HoldemPlayer(1000, false);
        players[2] = mainHoldemPlayer;
        players[3] = new HoldemPlayer(1000, false);
        Holdem game = new Holdem(players, 2); // create game, pass in players and big blind

        Pane cardPane = new Pane(); // Pane holding all of the cards
        Pane chipPane = new Pane(); // Pane holding all of the chips

        HBox communityBox = new HBox(48); // HBox for community cards
        communityBox.setLayoutX(514);
        communityBox.setLayoutY(472);

        Image cardBack = new Image("/gameAssets/cards/back.png"); // Declare path to back of card
        CardList communityCards = game.communityCards; // Declare community cards reference

        StackPane pot = game.pot.chipSignature; // Reference pot stackpane image signature
        pot.setLayoutX(320); // adjust position
        pot.setLayoutY(452);
        chipPane.getChildren().addAll(pot); // add it to the chip pane

        Pane indicatorPane = new Pane(); // Pane holds fold indicators and win indicators (Overlays over player spots)

        for (Player p : players) { // Add all of the fold indicators to indicator pane
            HoldemPlayer hp = (HoldemPlayer) p;
            indicatorPane.getChildren().add(hp.foldMarker);
        }

        Button nextRound = new Button(); // Create nextRound button. Advances between betting rounds
        nextRound.setGraphic(new ImgExpand(new Image("/ui/roundAdvance.png")));
        nextRound.setPrefSize(184, 108);
        nextRound.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Button nextTurn = new Button(); // Create nextTurn button. Advances between player turns for visual continuity
        nextTurn.setGraphic(new ImgExpand(new Image("/ui/turnAdvance.png")));
        nextTurn.setPrefSize(184, 108);
        nextTurn.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Button call = new Button(); // Call button
        call.setGraphic(new ImgExpand(new Image("/ui/call.png")));
        call.setPrefSize(184, 108);
        call.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Button bet = new Button(); // Bet / Raise button
        bet.setGraphic(new ImgExpand(new Image("/ui/bet.png")));
        bet.setPrefSize(184, 108);
        bet.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Button fold = new Button(); // Fold button
        fold.setGraphic(new ImgExpand(new Image("/ui/fold.png")));
        fold.setPrefSize(184, 108);
        fold.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        TextField betAmount = new TextField(); // Amount you want to bet is placed here
        betAmount.setPromptText("Enter Bet"); 
        betAmount.setMaxWidth(75);

        VBox advanceButtons = new VBox(nextRound, nextTurn); // both advance buttons in vbox

        VBox actionButtons = new VBox(5, call, fold, bet, betAmount); // All action buttons in vbox
        actionButtons.setAlignment(Pos.TOP_CENTER);

        HBox buttons = new HBox(1004, advanceButtons,actionButtons); // HBox contains both vbox above

        // By default, disable the call button because it is the default action
        call.setDisable(true);
        mainHoldemPlayer.mainPlayerAction = "C"; // default action = call

        call.setOnAction(e->{ // When call but is pressed toggle other buttons and set action to call
            call.setDisable(true);
            bet.setDisable(false);
            fold.setDisable(false);
            mainHoldemPlayer.mainPlayerAction = "C";
        });

        bet.setOnAction(e->{ // Textfield must have a valid bet / raise in before bet button can be pressed succesfully
            if (!betAmount.getText().isEmpty()) { // If not empty...
                // If input is valid based on bank../
                if (InputValidator.validateBet(betAmount.getText(), mainHoldemPlayer.chipBank.getChips(), game.highBet)) {
                    call.setDisable(false); // Toggle buttons 
                    bet.setDisable(true);
                    fold.setDisable(false);
                    mainHoldemPlayer.mainPlayerAction = "B"; // action = bet
                    mainHoldemPlayer.mainPlayerBet = Integer.parseInt(betAmount.getText()); // bet = whats in text field
                }
                else { // if NOT valid display warning and reset box
                    new Alert(Alert.AlertType.WARNING, "Enter a valid value! It must not excede what you can afford.").showAndWait();
                    betAmount.setText("");
                }
            }
            else { // If IS empty display warning
                new Alert(Alert.AlertType.WARNING, "Enter a bet!").showAndWait();
            }
        });

        fold.setOnAction(e->{ // Toggle same as with others
            call.setDisable(false);
            bet.setDisable(false);
            fold.setDisable(true);
            mainHoldemPlayer.mainPlayerAction = "F";
        });

        nextTurn.setDisable(true); // Disable next turn button to start. Only next round button is on
        nextTurn.setOnAction(e->{ // When nextTurn is pressed...
            if (game.allPlayersFolded()) { // If all the players have folded calculate a winner
                // This block is copy and pasted from below. Better comments down there. Not the best practice but im out of time
                nextTurn.setDisable(true);
                nextRound.setDisable(false);
                game.updatePot();
                for (Player p : players) {
                    HoldemPlayer hp = (HoldemPlayer) p;
                    ImageView card1 = new ItemExpand(new Image("/gameAssets/cards/" + hp.cards.getCard(0) + ".png"));
                    ImageView card2 = new ItemExpand(new Image("/gameAssets/cards/" + hp.cards.getCard(1) + ".png"));
                    hp.cardBox.getChildren().clear();
                    hp.cardBox.getChildren().addAll(card1, card2);
                }
                ArrayList<HoldemPlayer> winners = game.getWinner();
                for (HoldemPlayer hp : winners) {
                    ImgExpand winnerMarker = new ImgExpand(new Image("/gameAssets/winner.png"));
                    indicatorPane.getChildren().addAll(winnerMarker);
                    switch (hp.playerID) {
                        case 0:
                            winnerMarker.setLayoutX(1064);
                            winnerMarker.setLayoutY(416);
                            break;
                        case 1:
                            winnerMarker.setLayoutX(1064);
                            winnerMarker.setLayoutY(636);
                            break;
                        case 2:
                            winnerMarker.setLayoutX(652);
                            winnerMarker.setLayoutY(636);
                            break;
                        case 3:
                            winnerMarker.setLayoutX(244);
                            winnerMarker.setLayoutY(636);
                            break;
                    }
                }
                int take = game.pot.chipAmount / winners.size();
                    for (HoldemPlayer winner : winners) {
                        winner.chipBank.addChips(take);
                        player1.setChips(winner.chipBank.getChips());
                        System.out.println(player1);
                    }
                    gameState = HoldemState.END;
            }
            else { // If all players have not folded
                if (game.remainingTurns > 0) { // Advance to next turn if there are more than 0 turns remaing
                    game.nextTurn();
                }
                else { // Otherwise disable button and enable next round. update pot
                    nextTurn.setDisable(true);
                    nextRound.setDisable(false);
                    game.updatePot();
                }
            }
        });

        nextRound.setOnAction(e->{ // When next round pushed....
            nextTurn.setDisable(false); // Disable button, enable nextTurn button
            nextRound.setDisable(true);
            switch (gameState) { // Check states. This acts as a kind of game controller / state machine
                case HoldemState.SETUP: // If in setup
                    game.setup(); // Run setup function in Holdem
                    for (Player p : players) { // Cycle through players
                        HoldemPlayer hp = (HoldemPlayer) p;
                        // Declare players cards
                        ImageView card1 = new ItemExpand(cardBack); // Both cards are flipped over by default
                        ImageView card2 = new ItemExpand(cardBack);
                        switch (hp.playerID) { // Cycle through players to determine position for card HBox on table
                            case 0:
                                hp.handChips.chipSignature.setLayoutX(1072);
                                hp.handChips.chipSignature.setLayoutY(432);
                                hp.cardBox.setLayoutX(1044);
                                hp.cardBox.setLayoutY(388);
                                break;
                            case 1:
                                hp.handChips.chipSignature.setLayoutX(1072);
                                hp.handChips.chipSignature.setLayoutY(552);
                                hp.cardBox.setLayoutX(1044);
                                hp.cardBox.setLayoutY(676);
                                break;
                            case 2:
                                hp.handChips.chipSignature.setLayoutX(660); // Little different. Main player is always id 2, also need
                                hp.handChips.chipSignature.setLayoutY(552); // to be able to see cards. Below lines specify path
                                card1 = new ItemExpand(new Image("/gameAssets/cards/" + hp.cards.getCard(0) + ".png"));
                                card2 = new ItemExpand(new Image("/gameAssets/cards/" + hp.cards.getCard(1) + ".png"));
                                hp.cardBox.setLayoutX(632);
                                hp.cardBox.setLayoutY(676);
                                break;
                            case 3:
                                hp.handChips.chipSignature.setLayoutX(248);
                                hp.handChips.chipSignature.setLayoutY(552);
                                hp.cardBox.setLayoutX(220);
                                hp.cardBox.setLayoutY(676);
                                break;
                        }
                        // Add the two cards to cardBox HBox for every player
                        hp.cardBox.getChildren().addAll(card1, card2);
                        cardPane.getChildren().addAll(hp.cardBox); // Add HBoxes to cardPane
                        chipPane.getChildren().add(hp.handChips.chipSignature); // Add chip signatures to chipPane
                    }
                    game.handleBettingRound(HoldemState.FIRST_BET); // Initiate first betting round
                    gameState = HoldemState.FLOP; // Set next state
                    break;

                case HoldemState.FLOP: // If in flop
                    game.flop();
                    for (Card c : communityCards.getCardList()) { // Add community card images to community box from earlier
                        ImageView card = new ItemExpand(new Image("/gameAssets/cards/" + c + ".png")); // Filepath based on toString
                        communityBox.getChildren().addAll(card); 
                    }
                    communityBox.getChildren().addAll(new ItemExpand(cardBack)); // Add 2 face down cards
                    communityBox.getChildren().addAll(new ItemExpand(cardBack));
                    cardPane.getChildren().addAll(communityBox); // Add community cards to cardPane
                    game.handleBettingRound(HoldemState.SECOND_BET); // start second bet
                    gameState = HoldemState.TURN; // advance state
                    break;

                case HoldemState.TURN: // All of these follow a similar pattern
                    game.turn();
                    communityBox.getChildren().clear();
                    for (Card c : communityCards.getCardList()) { // Update card images
                        ImageView card = new ItemExpand(new Image("/gameAssets/cards/" + c + ".png"));
                        communityBox.getChildren().addAll(card);
                    }
                    communityBox.getChildren().addAll(new ItemExpand(cardBack)); // Add only one flipped over card
                    game.handleBettingRound(HoldemState.THIRD_BET); // next betting round
                    gameState = HoldemState.RIVER; // river state
                    break;

                case HoldemState.RIVER: // same thing
                    game.river();
                    communityBox.getChildren().clear();
                    for (Card c : communityCards.getCardList()) {
                        ImageView card = new ItemExpand(new Image("/gameAssets/cards/" + c + ".png"));
                        communityBox.getChildren().addAll(card);
                    }
                    game.handleBettingRound(HoldemState.FINAL_BET);
                    gameState = HoldemState.SHOWDOWN; // advance to SHOWDOWN
                    break;
                case HoldemState.SHOWDOWN: // Flips over cards, determines a winner
                    for (Player p : players) { // Cycle through players
                        HoldemPlayer hp = (HoldemPlayer) p;
                        // Set all cards to face up
                        ImageView card1 = new ItemExpand(new Image("/gameAssets/cards/" + hp.cards.getCard(0) + ".png"));
                        ImageView card2 = new ItemExpand(new Image("/gameAssets/cards/" + hp.cards.getCard(1) + ".png"));
                        hp.cardBox.getChildren().clear(); // Clear cardBox for each player, add new cards in
                        hp.cardBox.getChildren().addAll(card1, card2);
                    }
                    // Find the winners
                    ArrayList<HoldemPlayer> winners = game.getWinner(); // Returns list of winners 
                    for (HoldemPlayer hp : winners) { // Cycle through winners
                        // For each winner, add a winner indicator
                        ImgExpand winnerMarker = new ImgExpand(new Image("/gameAssets/winner.png"));
                        indicatorPane.getChildren().addAll(winnerMarker);
                        switch (hp.playerID) {
                            case 0: // Sets positions for all 4 players if they happen to be a winner
                                winnerMarker.setLayoutX(1064);
                                winnerMarker.setLayoutY(416);
                                break;
                            case 1:
                                winnerMarker.setLayoutX(1064);
                                winnerMarker.setLayoutY(636);
                                break;
                            case 2:
                                winnerMarker.setLayoutX(652);
                                winnerMarker.setLayoutY(636);
                                break;
                            case 3:
                                winnerMarker.setLayoutX(244);
                                winnerMarker.setLayoutY(636);
                                break;
                        }
                    }
                    // Distribute pot
                    int take = game.pot.chipAmount / winners.size(); // Split among winners (typically one)
                    for (HoldemPlayer winner : winners) { // Cycle through winners
                        winner.chipBank.addChips(take); // Add the take to their chips 
                    }
                    System.out.println(players[2].chipBank.getChips());
                    player1.setChips(players[2].chipBank.getChips()); // Set main players chips to what they now have in holdem
                    gameState = HoldemState.END; // Next state
                    break;
                case HoldemState.END: // Close window
                    stage.setScene(createGameSelectScene(stage, player1));
                    gameState = HoldemState.SETUP;
                    break;
                    
            }
        });
        // Add stuff to root
        root.getChildren().addAll(holdemBackground,imageHolder,holdemTable,gameContent,cardPane,chipPane,indicatorPane,buttons);
        // Return root to draw
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT); // use your defined width/height
    }
}


