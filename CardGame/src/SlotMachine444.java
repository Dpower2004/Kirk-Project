import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

/**
 *once we have a bank, ill add a refrence to a bank system and a contructor to recieve it 
 *going to add some sort of help box to learn how to play, and some sound effects
 *also going to adjust the layout once the background is finisb
 * Users can place bets, spin reels, and win based on matching symbols.
 */
public class SlotMachine444 extends Application {

    // Array of image filenames representing different symbols on the slot machine
    private static final String[] SYMBOL_FILES = 
    {
        "cherry.png", "lemon.png", "orange.png", "grape.png", "kirkDealer.png"
    };

    private ImageView[] reelImages;   // array to hold the three reel image views
    private TextField betInput;       
        private Label balanceLabel;       // Label displaying current balance
    private Label resultLabel;       
    private Random random;            // Random number generator for symbol selection
    //ill change this once we have the bank and real balance
    private double balance = 10000000; // Initial player balance (placeholder value)

    @Override
    public void start(Stage primaryStage) 
    {
        random = new Random();                  // Initialize random number generator
        reelImages = new ImageView[3];          // Create array for 3 reel images

        primaryStage.setTitle("Slot Machine");  // Set window title

        BorderPane root = new BorderPane();     // Main layout container 
        root.setPadding(new Insets(40));        // sdd padding around the borders

        // Create and style the header label
        Label headerLabel = new Label("KIRK'S SLOT MACHINE");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));//going to change this to mario
        root.setTop(headerLabel);                              //  header at top of layout
        BorderPane.setAlignment(headerLabel, Pos.CENTER);      // Center align the header
        BorderPane.setMargin(headerLabel, new Insets(0, 0, 50, 0)); // Add margin below header

        // Create container for the slot machine reels
        HBox reelsBox = new HBox(80);           // Horizontal box with 80px spacing
        reelsBox.setAlignment(Pos.CENTER);      // Center the reels
        reelsBox.setPadding(new Insets(50));    // Add padding around reels

        // Initialize each of the three reel image views
        for (int i = 0; i < 3; i++) 
        {
            reelImages[i] = new ImageView();     // Create new image view for each reel
            reelImages[i].setFitWidth(150);      // set width of each reel image
            reelImages[i].setFitHeight(150);     // Set height of each reel image
            reelsBox.getChildren().add(reelImages[i]); // Add image to the reels container
        }

        // Create and style the result message label
        resultLabel = new Label("Place your bet and spin!");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Container for reels and result message
        VBox centerBox = new VBox(40);           // Vertical box with 40px spacing
        centerBox.setAlignment(Pos.CENTER);      // Center align contents
        centerBox.getChildren().addAll(reelsBox, resultLabel); // Add reels and result label
        root.setCenter(centerBox);               // Place in center of main layout

        // Container for game controls (bet input, spin button)
        VBox controlsBox = new VBox(20);         // Vertical box with 20px spacing
        controlsBox.setAlignment(Pos.CENTER);    // Center align controls

        // Create and style balance display label
        balanceLabel = new Label("Balance: $" + String.format("%.2f", balance));
        balanceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Container for bet label and input field
        HBox betBox = new HBox(15);              
                betBox.setAlignment(Pos.CENTER);         // Center align contents

        // Create and style bet label
        Label betLabel = new Label("Your Bet: $");
        betLabel.setFont(Font.font("Arial", 20));

        // Create and style bet input field
        betInput = new TextField();
        betInput.setFont(Font.font("Arial", 20));
        betInput.setMaxWidth(200);               // Limit width of text field
        betInput.setPromptText("Enter bet");     // Placeholder text

        // Add label and input field to bet container
        betBox.getChildren().addAll(betLabel, betInput);

        // Create and style spin button
        Button spinButton = new Button("SPIN");
        spinButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        spinButton.setMinWidth(200);             // Set minimum button width
        spinButton.setMinHeight(60);             // Set minimum button height
        spinButton.setOnAction(e -> spin());     // Attach spin method to button click

        // Add all controls to the controls container
        controlsBox.getChildren().addAll(balanceLabel, betBox, spinButton);
        root.setBottom(controlsBox);             // place controls at bottom of layout
        BorderPane.setMargin(controlsBox, new Insets(40, 0, 0, 0)); // Add margin above controls

        // Create the main scene and set it on the stage
        Scene scene = new Scene(root, 1366, 768);
        primaryStage.setScene(scene);
        primaryStage.show();                     // Display the window
    }

    /**
     * Logic for validating bet and initiating reel animation.
     */
    private void spin() 
    {
        try {
            // Parse bet amount from text field
            double betAmount = Double.parseDouble(betInput.getText());

            // Validate bet is positive
            if (betAmount <= 0) {
                resultLabel.setText("Please enter a valid bet amount!");
                return;
            }

            // Check if player has enough balance
            if (betAmount > balance) {
                resultLabel.setText("Not enough money! Your balance is $" + String.format("%.2f", balance));
                return;
            }

            // Deduct bet from balance
            balance -= betAmount;
            // Update balance display
            balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
            // Show spinning message
            resultLabel.setText("Spinning...");

            // Start the reels animation
            animateReels(() -> {
                // Generate random results for each reel
                int[] results = new int[3];
                for (int i = 0; i < 3; i++) {
                    // Pick a random symbol index
                    results[i] = random.nextInt(SYMBOL_FILES.length);
                    // Set the image for this reel
                    reelImages[i].setImage(new Image("file:" + SYMBOL_FILES[results[i]]));
                }

                // Calculate winnings based on results
                double winnings = calculateWinnings(results, betAmount);
                
                // update display based on win/loss
                if (winnings > 0) {
                    // Add winnings to balance
                    balance += winnings;
                    resultLabel.setText("You won $" + String.format("%.2f", winnings) + "!");
                } else {
                    resultLabel.setText("You lost $" + String.format("%.2f", betAmount));
                }

                // Update balance display
                balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
            });

        } catch (NumberFormatException e) {
            // Handle case where input is not a valid number
            resultLabel.setText("Please enter a valid number!");
        }
    }

    /**
     * Animates the slot reels by quickly cycling symbols, then calls the result logic.
     * @param onFinished Callback to execute when animation completes.
     */
    private void animateReels(Runnable onFinished) {
        Timeline timeline = new Timeline();  // Create animation timeline
        int cycles = 20;                     // Number of animation frames

        // Create animation frames
        for (int i = 0; i < cycles; i++) {
            int index = i;
            // Add keyframe to the timeline
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 75), e -> {
                // For each reel, show a random symbol during animation
                for (int j = 0; j < reelImages.length; j++) {
                    int symbolIndex = random.nextInt(SYMBOL_FILES.length);
                    reelImages[j].setImage(new Image("file:" + SYMBOL_FILES[symbolIndex]));
                }
            }));
        }

        // Set action to run when animation completes
        timeline.setOnFinished(e -> onFinished.run());
        // Start the animation
        timeline.play();
    }

    /**
     * Calculates winnings based on the results and bet amount.
     * Supports full matches and partial wins for 2 symbols.
     * @param results Array containing the symbol indices for each reel
     * @param betAmount Amount bet by the player
     * @return Winning amount
     */
    private double calculateWinnings(int[] results, double betAmount) {
        // Count occurrences of each symbol
        int[] counts = new int[SYMBOL_FILES.length];
        for (int r : results) {
            counts[r]++;  // Increment count for this symbol
        }

        // Check for triple match (3 of the same symbol)
        for (int i = 0; i < counts.length; i++) 
        {
            if (counts[i] == 3) 
            {
                switch (i) 
                {
                    case 0: return betAmount * 3;   // cherry - 3x multiplier
                   case 1: return betAmount * 5;   // lemon - 5x multiplier
                    case 2: return betAmount * 8;   // orange - 8x multiplier
                    case 3: return betAmount * 10;  // grape - 10x multiplier
                    case 4: return betAmount *  30;  // kirkDealer - 30x multiplier
                }
            }
        }

        // Check for double matches (2 of the same symbol)
        if (counts[0] == 2) return betAmount * 0.1;  // 2 cherries - 10% return
        if (counts[1] == 2) return betAmount * 0.2;  // 2 lemons - 20% return
        if (counts[2] == 2) return betAmount * 0.3;  // 2 oranges - 30% return
        if (counts[3] == 2) return betAmount * 0.4;  // 2 grapes - 40% return

        // kirkDealer gets more
        if (counts[4] == 2) return betAmount * 5;    // 2 kirkDealers - 5x multiplier

        // No matches
        return 0;  // Player loses their bet
    }

    /**
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);  
            }
}