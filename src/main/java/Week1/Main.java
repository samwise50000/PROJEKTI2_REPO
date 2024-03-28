package Week1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    private JButton englishButton, persianButton, japaneseButton;
    private JLabel nameLabel;

    public Main() {
        setTitle("Localized UI Application");
        setLayout(new BorderLayout()); // Using BorderLayout for the main layout

        JPanel buttonPanel = new JPanel(); // Panel to hold the buttons
        buttonPanel.setLayout(new FlowLayout()); // Using FlowLayout for the button panel
        englishButton = new JButton("This is the first button.");
        persianButton = new JButton("This is the second button.");
        japaneseButton = new JButton("This is the third button.");
        buttonPanel.add(englishButton);
        buttonPanel.add(persianButton);
        buttonPanel.add(japaneseButton);
        add(buttonPanel, BorderLayout.NORTH); // Adding button panel to the top of the frame

        nameLabel = new JLabel("");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label text
        add(nameLabel, BorderLayout.CENTER); // Adding label to the center of the frame

        englishButton.addActionListener(this);
        persianButton.addActionListener(this);
        japaneseButton.addActionListener(this);

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Locale locale = null;

        if (source == englishButton) {
            locale = new Locale("en", "UK");
            nameLabel.setText(getMessage("en_UK"));
        } else if (source == persianButton) {
            locale = new Locale("fa", "IR");
            nameLabel.setText(getMessage("fa_IR"));
        } else if (source == japaneseButton) {
            locale = new Locale("ja", "JP");
            nameLabel.setText(getMessage("ja_JP"));
        }

        updateButtonLabels(locale);
    }

    private String getMessage(String locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale(locale));
        return bundle.getString("myname");
    }

    private void updateButtonLabels(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        englishButton.setText(bundle.getString("first"));
        persianButton.setText(bundle.getString("second"));
        japaneseButton.setText(bundle.getString("third"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
