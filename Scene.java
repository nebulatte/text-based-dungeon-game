import java.util.Scanner;

public class Scene {

    private final String PLAYER_NAME;
    private final int PLAYER_STAMINA;
    private final int PLAYER_STAMINA_REGEN;
    private final int PLAYER_HEALTH;
    private final int PLAYER_DEFENSE;
    private final int PLAYER_ATTACK;
    
    private final int MAX_ENEMIES_TO_DEFEAT;

    public Scene(String playerName, int playerStamina,
                int playerStaminaRegen, int playerHealth,
                int playerDefense, int playerAttack,
                int maxEnemiesToDefeat) {

        PLAYER_NAME = playerName;
        PLAYER_STAMINA = playerStamina;
        PLAYER_STAMINA_REGEN = playerStaminaRegen;
        PLAYER_HEALTH = playerHealth;
        PLAYER_DEFENSE = playerDefense;
        PLAYER_ATTACK = playerAttack;
        MAX_ENEMIES_TO_DEFEAT = maxEnemiesToDefeat;

    }

    private Player makePlayer() {
        return new Player(PLAYER_NAME, PLAYER_STAMINA, PLAYER_STAMINA_REGEN, PLAYER_HEALTH, PLAYER_DEFENSE, PLAYER_ATTACK);
    }

    public void playScene(Scanner scanner) {

        Player player = makePlayer();
        int killsUntilBoss = 10;
        
        while(player.getKillCount() < MAX_ENEMIES_TO_DEFEAT) {

            Enemy enemy;

            if(player.getKillCount() % killsUntilBoss != 0 || player.getKillCount() == 0) { enemy = EnemyType.getRandomEnemy(); } 
            else { enemy = EnemyType.getRandomBoss(); }
            
            Battle.BattleResult result = new Battle(player, enemy).run(scanner);
            if(result != Battle.BattleResult.PLAYER_WON) { break; }
            playerImprovementScreen(player, enemy);
        }

        if(player.getKillCount() == MAX_ENEMIES_TO_DEFEAT) {
            Display.framedMessage(String.format("Congratulations, %s! You conquered the dungeon!", PLAYER_NAME));
        }
        System.out.println();
    }

    // =============================================================================================

    private void playerImprovementScreen(Player player, Enemy enemy) {
        player.gainExp(enemy.getExpReward());
        player.gainKill();
        if(player.getTotalExp() >= player.getExpToNextLevel()) {
            player.levelUp();
            Display.framedMessage(player.getLevelUpMessage());
        }
    }

}