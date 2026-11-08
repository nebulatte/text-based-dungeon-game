import java.util.ArrayList;

public class Display {
    
    private Display () {}

    public static void printMainMenu() {
        System.out.println("=======================================================");
        System.out.println("                       MAIN MENU                       ");
        System.out.println("=======================================================");
        System.out.println("1 - Play Game");
        System.out.println("0 - Exit");
    }

    public static void printPlayerSkills(Player player) {
        System.out.println("================ SKILLS =================");
        ArrayList<Skill> skills = player.getSkills();
        for(int i = 0; i < skills.size(); i++) {
            Skill currentSkill = skills.get(i);
            System.out.printf("%d - %-20s : %s%n", i + 1, currentSkill.getSkillName(), currentSkill.getDescription(player));
        }
        System.out.println("0 - EXIT");
    }

    public static void printDifficulties() {
        printLinedMessage("DIFFICULTY");
        for(int i = 0; i < Difficulty.values().length; i++) {
            Difficulty currentDifficulty = Difficulty.values()[i];
            System.out.printf("%d - %-20s: %s%n", i + 1, currentDifficulty.getDifficultyLevelName(), currentDifficulty.getDifficultyDetails());
        }
    }

    public static void printFramedMessage(String message) {
        System.out.println("=======================================================");
        System.out.println(message);
        System.out.println("=======================================================");
    }

    public static void printFramedMessage(String message1, String message2) {
        System.out.println("=======================================================");
        System.out.println(message1);
        System.out.println(message2);
        System.out.println("=======================================================");
    }

    public static void printLinedMessage(String message) {
        System.out.println("------------------" + message + "---------------------");     
    }

}