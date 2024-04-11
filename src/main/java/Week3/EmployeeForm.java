package Week3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class EmployeeForm extends Application {
    private Stage primaryStage;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private ComboBox<String> languageComboBox;

    private ResourceBundle resourceBundle;
    private Label languageLabel;
    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label emailLabel;
    private Button saveButton;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.languageLabel = new Label("Select Language:");
        this.firstNameLabel = new Label();
        this.lastNameLabel = new Label();
        this.emailLabel = new Label();
        this.saveButton = new Button();

        languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll("English", "Farsi", "Japanese");
        languageComboBox.setValue("English");
        languageComboBox.setOnAction(event -> loadResourceBundle());

        loadResourceBundle(); // Load resource bundle initially
        updateTexts(); // Update texts when the application starts

        firstNameLabel = new Label(resourceBundle.getString("firstNameLabel"));
        firstNameField = new TextField();

        lastNameLabel = new Label(resourceBundle.getString("lastNameLabel"));
        lastNameField = new TextField();

        emailLabel = new Label(resourceBundle.getString("emailLabel"));
        emailField = new TextField();

        saveButton = new Button(resourceBundle.getString("saveButton"));
        saveButton.setOnAction(event -> saveData());

        // Set up layout
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.addRow(0, languageLabel, languageComboBox);
        root.addRow(1, firstNameLabel, firstNameField);
        root.addRow(2, lastNameLabel, lastNameField);
        root.addRow(3, emailLabel, emailField);
        root.add(saveButton, 0, 4, 2, 1);

        // Show the scene
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Employee Form");
        primaryStage.show();
    }

    private void loadResourceBundle() {
        String selectedLanguage = languageComboBox.getValue().toLowerCase();
        System.out.println("Selected language: " + selectedLanguage);
        resourceBundle = ResourceBundle.getBundle("EmployeeForm", new Locale(selectedLanguage));
        System.out.println("Resource bundle loaded: " + resourceBundle.getLocale());
        updateTexts(); // Call updateTexts() after loading the resource bundle
    }

    private void saveData() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String selectedLanguage = languageComboBox.getValue().toLowerCase();

        // Determine the table name based on the selected language
        String tableName = "employee_" + selectedLanguage;

        // Database connection parameters
        String URL = "jdbc:mariadb://localhost:3306/demobase?characterEncoding=UTF-8";
        String USERNAME = "root";
        String PASSWORD = "Testi123!?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Insert data into the appropriate table
            String insertQuery = "INSERT INTO " + tableName + " (first_name, last_name, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, email);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Data inserted successfully.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to insert data.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while saving data.");
        }
    }

    private void updateTexts() {
        System.out.println("Updating texts...");
        System.out.println("Resource bundle: " + resourceBundle.getLocale());
        System.out.println("First name label: " + resourceBundle.getString("firstNameLabel"));
        // Update labels and button text based on current resource bundle
        primaryStage.setTitle(resourceBundle.getString("windowTitle"));
        languageLabel.setText(resourceBundle.getString("languageLabel"));
        firstNameLabel.setText(resourceBundle.getString("firstNameLabel"));
        lastNameLabel.setText(resourceBundle.getString("lastNameLabel"));
        emailLabel.setText(resourceBundle.getString("emailLabel"));
        saveButton.setText(resourceBundle.getString("saveButton"));
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
