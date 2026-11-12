import java.util.Scanner;

public class Battle {

    public enum BattleResult {
        PLAYER_WON,
        PLAYER_LOST,
        PLAYER_LEFT;
    }
    
    private final Player player;
    private final Enemy enemy;

    private final int EXIT_CHOICE_INDEX = -1;
    private final float PASSIVE_HEALING_RATE = 0.1f;

    private boolean conquering = true;

    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public BattleResult run(Scanner scanner) {

        boolean isPlayerAlive = true;
        boolean isPlayerTurn = true;
        boolean isEnemyAlive = true;

        Display.framedMessage(enemy.appearanceMessage());
        System.out.println(enemy.getStats());

        while(conquering && isPlayerAlive && isEnemyAlive) {

            if(isPlayerTurn) {
                playerAction(scanner);
                player.regenerateStamina(0);
                player.heal((int)(player.getHealthStat() * PASSIVE_HEALING_RATE));
                isPlayerTurn = false;
            } else {
                enemyAction(enemy);
                enemy.regenerateStamina(0);
                enemy.heal((int)(enemy.getHealthStat() * PASSIVE_HEALING_RATE));
                isPlayerTurn = true;
            }

            isEnemyAlive = enemy.checkIfAlive();
            if(!isEnemyAlive) {
                Display.entityDeath(enemy, player);
            }
            isPlayerAlive = player.checkIfAlive();
            if(!isPlayerAlive) {
                Display.entityDeath(player, enemy);
            }

        }

        if(!isEnemyAlive) { return BattleResult.PLAYER_WON; }
        else if (!isPlayerAlive) { return BattleResult.PLAYER_LOST; }
        return BattleResult.PLAYER_LEFT;

    }

    private void playerAction(Scanner scanner) {
        Display.showPlayerInfo(player);
        Skill.Context context;
        boolean isValid = false;
        do {
        int choice = Utils.readSkillChoice(scanner, "Enter your choice: ", 0, player.getTotalSkills());

        if(choice == EXIT_CHOICE_INDEX) {
            System.out.println("Returning to the Main Menu...");
            conquering = false;
            return;
        }
        Skill chosenSkill = player.getSkills().get(choice);
        try {
            context = player.useSkill(choice, enemy);
            isValid = true;
        } catch(InsufficientStamina e) {
            System.out.println(e.getMessage());
            continue;
        }
        Display.skillUsage(player, enemy, chosenSkill, context);
        } while (!isValid);
    }

    private void enemyAction(Enemy enemy) {
        System.out.println(enemy.getStats());
        Skill.Context context;
        int index = enemy.chooseSkill();
        Skill chosenSkill = enemy.getSkills().get(index);
        context = enemy.useSkill(index, player);
        Display.skillUsage(enemy, player, chosenSkill, context);
    }

}
