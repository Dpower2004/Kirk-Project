import java.io.File;
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
//import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;

/**
//Shaina Xhelilaj
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
    
    // Probability weights for each symbol
    private static final double[] SYMBOL_WEIGHTS = 
    {
        0.20,  // cherry - common
        0.15,  // lemon  
        0.12,  // orange
        0.10,  // grape
        0.05   // kirkDealer - rare but achievable
    };
    
    //  0.25 to allow more matching possibilities
    private static final double THREE_DIFFERENT_SYMBOLS_PROBABILITY = 0.25;
    
    //  Add probability to force three matching symbols (20% chance)
    private static final double THREE_MATCHING_SYMBOLS_PROBABILITY = 0.20;
    
        // This gives kirkDealer a chance to appear even in the "three different" scenario
    private static final double KIRK_DEALER_IN_DIFFERENT_PROBABILITY = 0.15;

    private ImageView[] reelImages;   // array to hold the three reel image views
    private TextField betInput;       
    private Label balanceLabel;       // Label displaying current balance
    private Label resultLabel;       
    private Random random;  // Random number generator for symbol selection

    private MediaPlayer spinSound;
    private MediaPlayer loseSound;           
    //ill change this once we have the bank and real balance
    private double balance = 1000; // Initial player balance (placeholder value)

    @Override
    public void start(Stage primaryStage) 
    {
        random = new Random();   
        Media spinMedia = new Media(new File("spin_sound_trimmed.mp3").toURI().toString());
        spinSound = new MediaPlayer(spinMedia);
        Media loseMedia = new Media(new File("youtube__asNhzXq72w_audio.mp3").toURI().toString());
        loseSound = new MediaPlayer(loseMedia);        
            // Initialize random number generator
        reelImages = new ImageView[3];          // Create array for 3 reel images

        primaryStage.setTitle("Slot Machine");  // Set window title

        BorderPane root = new BorderPane();     // Main layout container 
        root.setPadding(new Insets(40));        // sdd padding around the borders
         BackgroundImage backgroundImage = new BackgroundImage(
        new Image("file:slots.png"),            BackgroundRepeat.NO_REPEAT, 
        BackgroundRepeat.NO_REPEAT, 
        BackgroundPosition.CENTER, 
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
    root.setBackground(new Background(backgroundImage));


        // Create and style the header label
        Label headerLabel = new Label("KIRK'S SLOT MACHINE");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));//going to change this to mario
        headerLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        root.setTop(headerLabel);                              //  header at top of layout
        BorderPane.setAlignment(headerLabel, Pos.CENTER);      // Center align the header
        BorderPane.setMargin(headerLabel, new Insets(0, 0, 50, 0)); // Add margin below header

        // Create container for the slot machine reels
        HBox reelsBox = new HBox(20);           // Horizontal box with 80px spacing
        reelsBox.setAlignment(Pos.CENTER);      // Center the reels
        reelsBox.setPadding(new Insets(-400,-420,30,-50));    // Add padding around reels

        // Initialize each of the three reel image views
        for (int i = 0; i < 3; i++) 
        {
            reelImages[i] = new ImageView();     // Create new image view for each reel
            reelImages[i].setFitWidth(100);      // set width of each reel image
            reelImages[i].setFitHeight(100);     // Set height of each reel image
            reelsBox.getChildren().add(reelImages[i]); // Add image to the reels container
        }

        // Create and style the result message label
resultLabel = new Label("Place your bet and spin!");
resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
resultLabel.setTextFill(javafx.scene.paint.Color.RED);
resultLabel.setAlignment(Pos.CENTER); // Set alignment to center
resultLabel.setMinWidth(500);
StackPane.setAlignment(resultLabel, Pos.CENTER_RIGHT);

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
    new BorderWidths(2) // 3px border width
)));
resultLabel.setPadding(new Insets(1, 20, 10, 20)); 

        // Container for reels and result message
        VBox centerBox = new VBox(40);           // Vertical box with 40px spacing
        centerBox.setAlignment(Pos.CENTER); 
        centerBox.setPadding(new Insets(200,0,0,-100));     // Center align contents
        centerBox.getChildren().addAll(reelsBox, resultLabel); // Add reels and result label
        root.setCenter(centerBox);               // Place in center of main layout

        // Container for game controls (bet input, spin button)
        VBox controlsBox = new VBox(20);         // Vertical box with 20px spacing
        controlsBox.setAlignment(Pos.CENTER);    // Center align controls

        // Create and style balance display label
        balanceLabel = new Label("BALANCE: $" + String.format("%.2f", balance));
        balanceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        balanceLabel.setTextFill(javafx.scene.paint.Color.YELLOW);

        // Container for bet label and input field
        HBox betBox = new HBox(15);              
        betBox.setAlignment(Pos.CENTER);         // Center align contents

        // Create and style bet label
        Label betLabel = new Label("Your Bet: $");
        betLabel.setFont(Font.font("Arial", 20));
        betLabel.setTextFill(javafx.scene.paint.Color.WHITE);

        // Create and style bet input field
        betInput = new TextField();
        betInput.setFont(Font.font("Arial", 20));
        betInput.setMaxWidth(200);               // Limit width of text field
        betInput.setPromptText("Enter bet");
             // Placeholder text

        // Add label and input field to bet container
        betBox.getChildren().addAll(betLabel, betInput);

        Button spinButton = new Button("SPIN");
        spinButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        spinButton.setMinWidth(70);             // Reduce width to fit on handle
spinButton.setMinHeight(40);             // Set minimum button height
spinButton.setOnAction(e -> spin());     // Attach spin method to button click
spinButton.setTextFill(javafx.scene.paint.Color.RED); // Make button text white

         // Create a stack pane to overlay the spin button on the background
         StackPane overlayPane = new StackPane();
         overlayPane.getChildren().add(spinButton);
         // Position the button to align with handle in the top right
         StackPane.setAlignment(spinButton, Pos.TOP_RIGHT);
         StackPane.setMargin(spinButton, new Insets(-70, 160, 40, 30)); 
         // Set the overlay pane to cover the entire scene
         root.setRight(overlayPane);
        // Add all controls to the controls container
        controlsBox.getChildren().addAll(balanceLabel, betBox);
        root.setBottom(controlsBox);           // place controls at bottom of layout
        BorderPane.setMargin(controlsBox, new Insets(40, 0, 0, 0)); // Add margin above controls

        // Create the main scene and set it on the stage
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();                     // Display the window
    }

    /**
     * Returns a random symbol index based on the defined probability weights
     */
    private int getWeightedRandomSymbol()
     {
        double random = this.random.nextDouble();
        double cumulativeProbability = 0.0;
        
        for (int i = 0; i < SYMBOL_WEIGHTS.length; i++)
         {
            cumulativeProbability += SYMBOL_WEIGHTS[i];
            if (random < cumulativeProbability) 
            {
                return i;
            }
        }
        
        // Fallback (shouldn't happen if weights sum to 1)
        return 0;
    }
    
    /**
     * Returns a random symbol index (0-4) with adjustable chance for kirkDealer
     */
    private int getRandomSymbol(boolean allowKirkDealer)
     {
        // If kirkDealer is allowed and we roll the probability check
        if (allowKirkDealer && random.nextDouble() < KIRK_DEALER_IN_DIFFERENT_PROBABILITY)
         {
            return 4; // Return kirkDealer index
        } else 
        {
            // Only include indices 0-3 (fruits)
            return random.nextInt(4);
        }
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
            if (betAmount > balance) 
            {
                resultLabel.setText("Not enough money! Your balance is $" + String.format("%.2f", balance));
                return;
            }

            // Deduct bet from balance
            balance -= betAmount;
            spinSound.stop(); // Stop any currently playing sound
            spinSound.seek(javafx.util.Duration.ZERO); // Reset to beginning
            spinSound.play();
            // Update balance display
            balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
            // Show spinning message
            resultLabel.setText("Spinning...");

            // Start the reels animation
            animateReels(() -> {
                int[] results = new int[3];
                
                // Random number to determine spin type
                double spinType = random.nextDouble();

                // Force three different symbols (25% chance)
               if (spinType < THREE_DIFFERENT_SYMBOLS_PROBABILITY) 
{
    // Create a set to track which symbols we've already selected
    HashSet<Integer> selectedSymbols = new HashSet<>();

    // For each reel
    for (int i = 0; i < 3; i++) 
    {
        int symbolIndex;
        // Keep generating random symbols until we get one we haven't used yet
        do {
            symbolIndex = getRandomSymbol(true);
        } while (selectedSymbols.contains(symbolIndex));
        
        selectedSymbols.add(symbolIndex);
        results[i] = symbolIndex;
        reelImages[i].setImage(new Image("file:" + SYMBOL_FILES[symbolIndex]));
    }

    // All 3 are guaranteed to be different, so it's a loss
    // And similarly for loseSound:
       loseSound.stop();
        loseSound.seek(javafx.util.Duration.ZERO);
        loseSound.play();
} 
                //  Force three matching symbols (25% chance)
                else if (spinType < THREE_DIFFERENT_SYMBOLS_PROBABILITY + THREE_MATCHING_SYMBOLS_PROBABILITY) {
                    // Select one random symbol using weighted probabilities
                    int symbolIndex = getWeightedRandomSymbol();
                    
                    // Set all three reels to show this symbol
                    for (int i = 0; i < 3; i++)
                     {
                        results[i] = symbolIndex;
                        reelImages[i].setImage(new Image("file:" + SYMBOL_FILES[symbolIndex]));
                    }
                }
                // Regular weighted spin (now 50% )
                else {
                    // Generate results using weighted probability
                    for (int i = 0; i < 3; i++)
                    
                     {
                        results[i] = getWeightedRandomSymbol();
                        reelImages[i].setImage(new Image("file:" + SYMBOL_FILES[results[i]]));
                    }
                }

                // Calculate winnings based on results
                double winnings = calculateWinnings(results, betAmount);
                
                // update display based on win/loss
                if (winnings > 0) {
                    // Add winnings to balance
                    balance += winnings + betAmount;
                    resultLabel.setText("You won $" + String.format("%.2f", winnings) + "!");
                    
                } else {
                    resultLabel.setText("You lost $" + String.format("%.2f", betAmount));
                 // And similarly for loseSound:
loseSound.stop();
loseSound.seek(javafx.util.Duration.ZERO);
loseSound.play();
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
        int cycles = 30;                     // Number of animation frames

        // Create animation frames
        for (int i = 0; i < cycles; i++) {
            int index = i;
            // Add keyframe to the timeline
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 75), e -> {
                // For each reel, show a random symbol during animation
                for (int j = 0; j < reelImages.length; j++) {
                    int symbolIndex = getWeightedRandomSymbol();
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
                    case 0: return betAmount * 1.5;   // cherry - 1.5x multiplier
                    case 1: return betAmount * 2;   // lemon - 2x multiplier
                    case 2: return betAmount * 5;   // orange - 5x multiplier
                    case 3: return betAmount * 10;  // grape - 10x multiplier
                    case 4: return betAmount * 30;  // kirkDealer - 30x multiplier
                }
            }
        }

        // Check for double matches (2 of the same symbol)
        if (counts[0] == 2) return betAmount * 0.15;  // 2 cherries - 15% return
        if (counts[1] == 2) return betAmount * 0.25;  // 2 lemons - 25% return
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
