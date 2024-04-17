package Week4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class MongoDBGUI extends JFrame implements ActionListener {
    private JTextField idField, nameField, ageField, cityField;
    private JButton addButton, readButton, updateButton, deleteButton;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDBGUI() {
        setTitle("MongoDB GUI");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Connect to MongoDB
        try {
            MongoClient mongoClient = MongoClients.create("mongodb+srv://tietokone:<PASSWORDHERE>@cluster0.4464b5v.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
            database = mongoClient.getDatabase("testdb");
            collection = database.getCollection("testcollection");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(10);
        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField(10);

        addButton = new JButton("Add");
        readButton = new JButton("Read");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        addButton.addActionListener(this);
        readButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(addButton);
        panel.add(readButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Add button clicked
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String city = cityField.getText();
            Document doc = new Document("_id", id).append("name", name).append("age", age).append("city", city);
            collection.insertOne(doc);
            clearFields();
        } else if (e.getSource() == readButton) {
            // Read button clicked
            String id = idField.getText();
            Document result = collection.find(Filters.eq("_id", id)).first();
            if (result != null) {
                nameField.setText(result.getString("name"));
                ageField.setText(Integer.toString(result.getInteger("age")));
                cityField.setText(result.getString("city"));
            } else {
                clearFields();
                JOptionPane.showMessageDialog(this, "Record not found.");
            }
        } else if (e.getSource() == updateButton) {
            // Update button clicked
            String id = idField.getText();
            Document result = collection.find(Filters.eq("_id", id)).first();
            if (result != null) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String city = cityField.getText();
                collection.updateOne(Filters.eq("_id", id),
                        new Document("$set", new Document("name", name)
                                .append("age", age)
                                .append("city", city)));
                JOptionPane.showMessageDialog(this, "Record updated successfully.");
            } else {
                clearFields();
                JOptionPane.showMessageDialog(this, "Record not found.");
            }
        } else if (e.getSource() == deleteButton) {
            // Delete button clicked
            String id = idField.getText();
            Document result = collection.find(Filters.eq("_id", id)).first();
            if (result != null) {
                collection.deleteOne(Filters.eq("_id", id));
                clearFields();
                JOptionPane.showMessageDialog(this, "Record deleted successfully.");
            } else {
                clearFields();
                JOptionPane.showMessageDialog(this, "Record not found.");
            }
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        cityField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MongoDBGUI());
    }
}

