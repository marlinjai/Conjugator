import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.lang.reflect.AccessibleObject;

public class Main {

    public static void main(String[] args) {

        /*User printtest = Database.Userlist.get(0);
        System.out.println(printtest.firstName);*/



        // Create a map to store the translated words
        Map<String, Map<String, String>> translations = new HashMap<>();

        // Create a scanner to read input from the user
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Prompt the user to enter a name for the list of translated words
            System.out.println("Enter a name for the list of translated words: ");
            System.out.print(">> ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                break;
            }

            // Create a new map for the list of translated words
            Map<String, String> list = new HashMap<>();

            // Prompt the user to enter the translated words
            System.out.println("Enter the translated words in the format: ");
            System.out.println("english_word: translated_word");
            System.out.println("Enter an empty line to finish the list.");


            // Read the translated words from the user and add them to the map
            while (true) {
                System.out.print(">> ");
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    break;
                }
                // Split the line into the english and translated words
                String[] parts = line.split(":");
                if (parts.length != 2) {
                    System.out.println("Invalid input. Please try again.");
                    continue;
                }

                // Add the translation to the map
                String english = parts[0].trim();
                String translated = parts[1].trim();
                list.put(english, translated);
            }

            // Add the list of translated words to the main map
            translations.put(name, list);




        }

        // Print the translated words
        System.out.println("Translated words: ");
        for (Map.Entry<String, Map<String, String>> entry : translations.entrySet()) {
            System.out.println("List name: " + entry.getKey());
            Map<String, String> list = entry.getValue();
            for (Map.Entry<String, String> word : list.entrySet()) {
                System.out.println(word.getKey() + ": " + word.getValue());
            }
        }

    }

}
