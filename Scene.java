import java.util.Random;
import java.util.Scanner;

public class Scene {

    private final String PLAYER_NAME;
    private final int PLAYER_STAMINA;
    private final int PLAYER_STAMINA_REGEN;
    private final int PLAYER_HEALTH;
    private final int PLAYER_DEFENSE;
    private final int PLAYER_ATTACK;

    private final int BASIC_ENEMY_INDEX = 0;
    private final int ADVANCED_ENEMY_INDEX = 1;
    private final int EXIT_CHOICE_INDEX = -1;
    
    private final int MAX_ENEMIES_TO_DEFEAT;

    private boolean conquering;
    private boolean playerIsAlive;

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

    private boolean sceneMeetsCriteria(Player player) {
        return conquering && playerIsAlive && player.getKillCount() < MAX_ENEMIES_TO_DEFEAT;
    }

    private Player makePlayer() {
        return new Player(PLAYER_NAME, PLAYER_STAMINA, PLAYER_STAMINA_REGEN, PLAYER_HEALTH, PLAYER_DEFENSE, PLAYER_ATTACK);
    }

    public void playScene(Scanner scanner) {

        Player player = makePlayer();
        conquering = true;
        playerIsAlive = true;
        
        while(sceneMeetsCriteria(player)) {

            Enemy enemy = randomizeEnemySpawn();
            System.out.println(enemy.appearanceMessage());
            System.out.println(enemy.getStats());
            combat(scanner, player, enemy);

        }

        if(playerIsAlive && player.getKillCount() == MAX_ENEMIES_TO_DEFEAT) {
            Display.printFramedMessage(String.format("Congratulations, %s! You conquered the dungeon!", PLAYER_NAME));
        }
        System.out.println();
    }

    // =============================================================================================

    private void combat(Scanner scanner, Player player, Enemy enemy) {
        playerIsAlive = true;
        boolean enemyIsAlive = true;
        boolean isPlayerTurn = true;

        while(enemyIsAlive && playerIsAlive && conquering) {

            if(isPlayerTurn) {
                showPlayerInfo(player);
                playerTurn(player, enemy, scanner);
                regenerateStaminaOf(player);
                isPlayerTurn = false;

            } else {
                enemyTurn(enemy, player);
                regenerateStaminaOf(enemy);
                isPlayerTurn = true;
            }

            if(!conquering) { return; }

            System.out.println(enemy.getStats());

            enemyIsAlive = enemy.checkIfAlive();
            if(!enemyIsAlive) {
                enemyDefeatedMessage(player, enemy);
                playerImprovementScreen(player, enemy);
                enemyIsAlive = false;

            }
            
            playerIsAlive = player.checkIfAlive();
            if(!playerIsAlive) {
                Display.printFramedMessage(player.deathMessage(enemy));
                playerIsAlive = false;
            }
        }
    }

    // =============================================================================================

    private Enemy spawnBasicEnemy() {
        int slimeIndex = 0;
        int goblinIndex = 1;
        Random randomizer = new Random();
        int chosenEnemy = randomizer.nextInt(2);

        BasicEnemy finalEnemy = null;
        if(chosenEnemy == goblinIndex) {
            finalEnemy = new BasicEnemy("Goblin");
        } else if (chosenEnemy == slimeIndex) {
            finalEnemy = new BasicEnemy("Slime");
        }
        return finalEnemy;
    }

    private Enemy spawnAdvancedEnemy() {
        int orcIndex = 0;
        int dragonIndex = 1;
        Random randomizer = new Random();
        int chosenEnemy = randomizer.nextInt(2);

        AdvancedEnemy finalEnemy = null;
        if(chosenEnemy == orcIndex) {
            finalEnemy = new AdvancedEnemy("Orc");
        } else if (chosenEnemy == dragonIndex) {
            finalEnemy = new AdvancedEnemy("Dragon");
        }
        return finalEnemy;
    }

    private Enemy randomizeEnemySpawn() {
        Random randomizer = new Random();
        int chosenEnemy = randomizer.nextInt(2);
        if(chosenEnemy == BASIC_ENEMY_INDEX) {
            return spawnBasicEnemy();
        } else if (chosenEnemy == ADVANCED_ENEMY_INDEX){
            return spawnAdvancedEnemy();
        }
        return spawnBasicEnemy();
    }

    private void regenerateStaminaOf(Entity entity) {
        int entityStaminaRegen = entity.getStaminaRegen();
        int entityCurrentStamina = entity.getCurrentStamina();

        if(entityCurrentStamina + entityStaminaRegen > entity.getStaminaStat()) {
            entity.setCurrentStamina(entity.getStaminaStat());
            return;
        }

        entity.setCurrentStamina(entityCurrentStamina + entityStaminaRegen);
    }

    private void showPlayerInfo(Player player) {
        System.out.println(player.turnMessage());
        System.out.println(player.getStats());
        System.out.println(player.getExpStats());
        System.out.printf("Kill Count: %d%n%n", player.getKillCount());
        Display.printPlayerSkills(player);
    }

    private void playerTurn(Player player, Enemy enemy, Scanner scanner) {
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
            player.useSkill(choice, enemy);
            isValid = true;
        } catch(InsufficientStamina e) {
            System.out.println(e.getMessage());
            continue;
        }
        System.out.println(chosenSkill.combatMessage(player, enemy));
        } while (!isValid);

    }

    private void enemyTurn(Enemy enemy, Player player) {
        System.out.println(enemy.turnMessage());
        int chosenSkill = enemy.chooseSkill();
        enemy.useSkill(chosenSkill, player);
        System.out.println(enemy.skills.get(chosenSkill).combatMessage(enemy, player));
    }

    private void enemyDefeatedMessage(Player player, Enemy enemy) {
        String message = enemy.deathMessage(player);
        message += (player.expGainMessage(enemy));
        Display.printFramedMessage(message);
    }

    private void playerImprovementScreen(Player player, Enemy enemy) {
        player.addExp(enemy.getExpReward());
        player.addKillCount();
        if(player.getTotalExp() >= player.getExpToNextLevel()) {
            player.levelUp();
            Display.printFramedMessage(player.getLevelUpMessage());
        }
    }

}