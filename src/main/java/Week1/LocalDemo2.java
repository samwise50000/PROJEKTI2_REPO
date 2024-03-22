package Week1;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;
import java.util.Date;
import java.text.DateFormat;
public class LocalDemo2 {
    public static void main(String[] args) {

        Locale locale = Locale.getDefault();

        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getDisplayLanguage());
        System.out.println(locale.getDisplayName());
        System.out.println(locale.getISO3Country());
        System.out.println(locale.getISO3Language());
        System.out.println(locale.getLanguage());
        System.out.println(locale.getCountry());
        System.out.println("---------------------");

        Locale defaultLocale = new Locale(locale.getLanguage(), locale.getCountry());
        Date currentDate = new Date();
        DateFormat timeFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, defaultLocale);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", defaultLocale);

        String timeFormatPattern = resourceBundle.getString("timeFormatPattern");
        String greetings = resourceBundle.getString("greetings");
        String farewell = resourceBundle.getString("farewell");
        String inquiry = resourceBundle.getString("inquiry");
        System.out.println("Greetings: " + greetings + " farewell" + farewell + " " + inquiry);
        System.out.println(timeFormat.format(currentDate) + " " + greetings + " " + farewell + " " + inquiry + " " + timeFormatPattern);


    }
}

