package Graphic;

import Pattern.Patterns;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Exception.SyntaxErrorException;
import Interpretation.Interpretation;

import java.io.*;

public class MainFrame extends Application {

    private final TextArea inputTextArea = new TextArea();
    private final static TextArea outputTextArea = new TextArea();
    private boolean themeFlag = false;
    private final Button runBtn = new Button();
    private final Button exitBtn = new Button();
    private final Button openFile = new Button();
    private final Button themeBtn = new Button();
    private final Button saveBtn = new Button();

    public static TextArea getOutputTextArea() {
        return outputTextArea;
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Interpreter");
        primaryStage.setResizable(false);
        primaryStage.setHeight(870);
        primaryStage.setWidth(1620);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("Graphic/Pics/icon.png"));

        setRunBtn();
        setExitBtn(primaryStage);
        setOpenFileBtn(primaryStage);
        setSaveBtn(primaryStage);

        inputTextArea.setPromptText("Type your code here...");
        inputTextArea.setPrefSize(800.0, 800.0);
        inputTextArea.setWrapText(true);
        inputTextArea.setFont(Font.font("Fira Code", 25));

        outputTextArea.setWrapText(true);
        outputTextArea.setEditable(false);
        outputTextArea.setPrefSize(802.0, 800.0);
        outputTextArea.setFont(Font.font("Fira Code", 25));


        VBox vBox = new VBox();
        vBox.setPrefSize(primaryStage.getWidth(), primaryStage.getHeight());
        HBox hBox = new HBox(runBtn, openFile, saveBtn, themeBtn, exitBtn);
        HBox hBox2 = new HBox(inputTextArea, outputTextArea);
        vBox.setVisible(true);
        vBox.getChildren().addAll(hBox, hBox2);
        Scene scene = new Scene(vBox);
        setThemeBtn(hBox, scene);
        primaryStage.setScene(scene);
    }


    public void setRunBtn() {

        runBtn.setPrefSize(40, 40);
        runBtn.setTranslateX(10);
        runBtn.setTranslateY(5);
        Image image = new Image("Graphic/Pics/play.png");
        runBtn.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(25, 25, false, false, false, false))));

        runBtn.setOnAction(actionEvent -> {

            outputTextArea.setText(null);

            try (BufferedWriter output = new BufferedWriter(new FileWriter("code.txt"))) {

                output.write(inputTextArea.getText());
                output.close();
                Interpretation.fileReader("code.txt");

            } catch (SyntaxErrorException ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Syntax  Error");
                alert.setContentText("Your code has syntax error. Please fix the error first and then try again");
                alert.showAndWait();


            } catch (IOException ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("File error");
                alert.setContentText("File not found or ...");
                alert.showAndWait();
            }
        });
    }

    public void setExitBtn(Stage primaryStage) {

        exitBtn.setPrefSize(40, 40);
        exitBtn.setTranslateX(15);
        exitBtn.setTranslateY(3);
        Image image2 = new Image("Graphic/Pics/exit.png");
        exitBtn.setBackground(new Background(new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(30, 30, false, false, false, false))));

        exitBtn.setOnAction(actionEvent -> {
            primaryStage.close();
        });
    }


    public void setOpenFileBtn(Stage primaryStage) {

        openFile.setPrefSize(40, 40);
        openFile.setTranslateX(10);
        openFile.setTranslateY(2);
        Image image3 = new Image("Graphic/Pics/file.png");
        openFile.setBackground(new Background(new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(30, 30, false, false, false, false))));

        openFile.setOnAction(actionEvent -> {

            inputTextArea.setText(null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("File error");
                alert.setContentText("File not found");
                alert.showAndWait();
            } else {

                try (BufferedReader input = new BufferedReader(new FileReader(file))) {

                    while (true) {

                        String line = input.readLine();

                        if (line == null)
                            break;

                        inputTextArea.appendText(line + "\n");
                    }
                } catch (IOException ex) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("File error");
                    alert.setContentText("File not found or ...");
                    alert.showAndWait();
                }
            }
        });
    }

    public void setThemeBtn(HBox hBox, Scene scene) {

        themeBtn.setPrefSize(40, 40);
        themeBtn.setTranslateX(15);
        themeBtn.setTranslateY(3);
        Image image4 = new Image("Graphic/Pics/theme.png");
        themeBtn.setBackground(new Background(new BackgroundImage(image4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(30, 30, false, false, false, false))));

        themeBtn.setOnAction(actionEvent -> {

            if (!themeFlag) {
                themeFlag = true;
                hBox.setStyle("-fx-background-image: url(Graphic/Pics/hbox.jpg); -fx-background-size: 49.9% 100%");
                scene.getStylesheets().remove("Graphic/Pics/lightStyle.css");
                scene.getStylesheets().add("Graphic/Pics/darkStyle.css");
                inputTextArea.setStyle("-fx-text-fill: #ffffff");
                outputTextArea.setStyle("-fx-text-fill: #ffffff");

            } else {
                themeFlag = false;
                hBox.setStyle("-fx-background-image: url(Graphic/Pics/hbox2.jpg); -fx-background-size: 49.9% 100%");
                scene.getStylesheets().remove("Graphic/Pics/darkStyle.css");
                scene.getStylesheets().add("Graphic/Pics/lightStyle.css");
                inputTextArea.setStyle("-fx-text-fill: #000000");
                outputTextArea.setStyle("-fx-text-fill: #000000");
            }
        });
    }


    public void setSaveBtn(Stage primaryStage) {

        saveBtn.setPrefSize(40, 40);
        saveBtn.setTranslateX(5);
        saveBtn.setTranslateY(3);
        Image image5 = new Image("Graphic/Pics/save.png");
        saveBtn.setBackground(new Background(new BackgroundImage(image5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(50, 30, false, false, false, false))));

        saveBtn.setOnAction(actionEvent -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("File error");
                alert.setContentText("File not found");
                alert.showAndWait();
            } else {

                try (BufferedWriter output = new BufferedWriter(new FileWriter(file))) {

                    output.write(inputTextArea.getText());


                } catch (IOException ex) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("File error");
                    alert.setContentText("File not found or ...");
                    alert.showAndWait();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);

    }
}