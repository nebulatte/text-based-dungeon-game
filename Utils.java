import java.util.Scanner;

public class Utils {
    
    private Utils() {}

    public static String readNonEmptyString(Scanner scanner, String prompt) {
        String input = "";
        boolean isValid = false;
        while(!isValid) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if(input.isBlank()) {
                System.out.println("Input cannot be empty.");
                continue;
            }
            isValid = true;
        }
        return input;
    }

    public static int readInt(Scanner scanner, String prompt, int min, int max) {
        int value = -1;
        boolean isValid = false;
        while(!isValid) {
            System.out.print(prompt);
            if(scanner.hasNextInt()) {

                value = scanner.nextInt();
                if(value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Please enter an integer between " + min + " and " + max + ".");     
                }

            } else {
                System.out.println("Please enter an integer.");
            }
            scanner.nextLine();
        }
        return value;
    }

    public static int readSkillChoice(Scanner scanner, String prompt, int min, int max) {
        return readInt(scanner, prompt, min, max) - 1;
    }

}
