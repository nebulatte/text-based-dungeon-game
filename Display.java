import java.util.ArrayList;

public class Display {
    
    private Display () {}

    public static void mainMenu() {
        System.out.println("=======================================================");
        System.out.println("                       MAIN MENU                       ");
        System.out.println("=======================================================");
        System.out.println("1 - Play Game");
        System.out.println("0 - Exit");
    }

    public static void framedMessage(String message) {
        System.out.println("=======================================================");
        System.out.println(message);
        System.out.println("=======================================================");
    }

    public static void framedMessage(String message1, String message2) {
        System.out.println("=======================================================");
        System.out.println(message1);
        System.out.println(message2);
        System.out.println("=======================================================");
    }

    public static void linedMessage(String message) {
        System.out.println("------------------" + message + "---------------------");     
    }

    public static void playerSkills(Player player) {
        System.out.println("================ SKILLS =================");
        ArrayList<Skill> skills = player.getSkills();
        for(int i = 0; i < skills.size(); i++) {
            Skill currentSkill = skills.get(i);
            System.out.printf("%d - %-20s : %s%n", i + 1, currentSkill.getSkillName(), currentSkill.getSkillDescription());
        }
        System.out.println("0 - EXIT");
    }

    public static void difficulties() {
        linedMessage("DIFFICULTY");
        for(int i = 0; i < Difficulty.values().length; i++) {
            Difficulty currentDifficulty = Difficulty.values()[i];
            System.out.printf("%d - %-20s: %s%n", i + 1, currentDifficulty.getDifficultyLevelName(), currentDifficulty.getDifficultyDetails());
        }
    }

    public static void skillUsage(Entity caster, Entity target, Skill skill, Skill.Context context) {
        linedMessage(caster.getName() + "'s turn");
        for(Effect e : skill.getEffects()) {
            System.out.print(e.getCombatMessage(caster, target, context));
        }
        System.out.println(target.getStats());
    }

    public static void entityDeath(Entity dead, Entity killer) {
        framedMessage(dead.deathMessage(killer));
    }

    public static void showPlayerInfo(Player player) {
        System.out.println(player.getStats());
        System.out.println(player.getExpStats());
        System.out.printf("Kill Count: %d%n%n", player.getKillCount());
        playerSkills(player);
    }

}