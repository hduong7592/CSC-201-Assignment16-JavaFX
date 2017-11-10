import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Create by hieuduong on 11/9/17
 *
 * CSC 201 - Assignment 16
 * Problem 16.4:
 *
 * (Create a miles/kilometers converter) Write a program that converts miles and
 * kilometers, as shown in Figure 16.37b. If you enter a value in the Mile text field
 * and press the Enter key, the corresponding kilometer measurement is displayed
 * in the Kilometer text field. Likewise, if you enter a value in the Kilometer text
 * field and press the Enter key, the corresponding miles is displayed in the Mile
 * text field.
 */

public class Main extends Application {

    private TextField mileTxt = new TextField();
    private TextField kilometerTxt = new TextField();
    private Label status = new Label();

    @Override
    public void start(Stage primaryStage) {

        //Create a new gridPane to hold the textfields
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Mile:"), 0, 0);
        gridPane.add(mileTxt, 1, 0);
        gridPane.add(new Label("Kilometer:"), 0, 1);
        gridPane.add(kilometerTxt, 1, 1);
        gridPane.add(new Label("Staus:"), 0, 2);
        gridPane.add(status, 1, 2);

        //Set alignments
        gridPane.setAlignment(Pos.CENTER);
        mileTxt.setAlignment(Pos.BOTTOM_RIGHT);
        kilometerTxt.setAlignment(Pos.BOTTOM_RIGHT);
        status.setAlignment(Pos.BOTTOM_LEFT);

        //add handler event to textfields
        //the event will trigger when user click the ENTER key
        mileTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER))
                {
                    Convert(mileTxt.getText(),"toKilometer");
                }
            }
        });

        //add handler event to textfields
        //the event will trigger when user click the ENTER key
        kilometerTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    Convert(kilometerTxt.getText(), "toMile");
                }
            }
        });

        //Create scene
        Scene scene = new Scene(gridPane, 350, 150);
        primaryStage.setTitle("Converter"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * Conversion method
     * @param text
     * @param whichConversion
     */

    private void Convert(String text, String whichConversion) {


        double value = 0.0;
        boolean isNumber = false;

        //Check for real input number
        try{
            value = Double.parseDouble(text);
            isNumber = true;
        }
        catch (Exception ex){
            value = 0.0;
            isNumber = false;
            status.setText("Not a real number");
        }

        //If input is real number, perform the conversion
        if(isNumber){

            double convertedValue = 0.0;
            DecimalFormat df = new DecimalFormat("#.#####");
            df.setRoundingMode(RoundingMode.CEILING);

            if(whichConversion.equals("toMile")){
                convertedValue = value*0.621371;
                mileTxt.setText(df.format(convertedValue));
                status.setText("Successfully converted kilometer to mile.");
            }
            else{
                convertedValue = value/0.621371;
                kilometerTxt.setText(df.format(convertedValue));
                status.setText("Successfully converted mile to kilometer.");
            }
        }
    }
}
