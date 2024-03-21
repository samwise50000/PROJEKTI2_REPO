package Week1;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
    public static void main(String[] args) {
        String lan= "fa";
        String country = "IR";

        Locale local = new Locale(lan, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", local);
        System.out.println(resourceBundle.getString("greetings"));
    }
}
