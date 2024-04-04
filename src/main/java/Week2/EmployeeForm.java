package Week2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ResourceBundle;

public class EmployeeForm extends Application {
    private Stage primaryStage;
    private ResourceBundle messages;

    // Declare UI elements
    private Label languageLabel;
    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label emailLabel;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private Button saveButton;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        messages = ResourceBundle.getBundle("Messages");

        primaryStage.setTitle(messages.getString("windowTitle"));

        // Initialize UI elements
        languageLabel = new Label(messages.getString("languageLabel"));
        firstNameLabel = new Label(messages.getString("firstNameLabel"));
        lastNameLabel = new Label(messages.getString("lastNameLabel"));
        emailLabel = new Label(messages.getString("emailLabel"));
        firstNameField = new TextField();
        lastNameField = new TextField();
        emailField = new TextField();
        saveButton = new Button(messages.getString("saveButton"));
        saveButton.setOnAction(event -> saveData());

        // Set up layout
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.add(languageLabel, 0, 0);
        root.add(firstNameLabel, 0, 1);
        root.add(lastNameLabel, 0, 2);
        root.add(emailLabel, 0, 3);
        root.add(firstNameField, 1, 1);
        root.add(lastNameField, 1, 2);
        root.add(emailField, 1, 3);
        root.add(saveButton, 0, 4, 2, 1);

        // Show the scene
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveData() {
        // Implement save data logic here
    }

    private void updateTexts() {
        // Update labels and button text based on current resource bundle
        // Note: Assumes JavaFX application thread
        primaryStage.setTitle(messages.getString("windowTitle"));
        languageLabel.setText(messages.getString("languageLabel"));
        firstNameLabel.setText(messages.getString("firstNameLabel"));
        lastNameLabel.setText(messages.getString("lastNameLabel"));
        emailLabel.setText(messages.getString("emailLabel"));
        saveButton.setText(messages.getString("saveButton"));
    }
}
