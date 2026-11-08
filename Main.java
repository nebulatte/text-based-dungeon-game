import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        try(Scanner scanner = new Scanner(System.in)) {
            
            boolean running = true;
            int mainMenuChoice;

            System.out.println("Welcome to the Dungeon.");    

            while(running) {
                Display.printMainMenu();
                mainMenuChoice = Utils.readInt(scanner, "Enter your choice: ", 0, 1);
                System.out.println();
                switch(mainMenuChoice) {
                    
                    case 1 -> { gameSetUp(scanner); }
                    case 0 -> { running = false; }
                }
                System.out.println();
            }

            Display.printFramedMessage("Thank you for playing!");

        }

    }

    private static void gameSetUp(Scanner scanner) {

        Display.printFramedMessage("YOUR COURAGE IS ADMIRABLE", "Only the most valiant of knights would enter the dungeon");
        String playerName = "Knight";
        System.out.println();

        Display.printDifficulties();
        int difficultyChoice = Utils.readInt(scanner, "Choose your desired difficulty: ", 1, Difficulty.values().length);
        Difficulty difficulty = Difficulty.getDifficultyLevelFromInput(difficultyChoice);
        Scene scene = Difficulty.getSceneDifficulty(difficulty, playerName);
        System.out.println();
        Display.printLinedMessage("May you prevail victorious.");
        System.out.println();
        
        scene.playScene(scanner);

    }
}
