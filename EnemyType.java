import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class EnemyType {

    private EnemyType() {};

    enum enemyDifficulty {
        BASIC,
        ADVANCED;
    }

    // Name, Stamina, Stamina Regen, Health, Defense, Attack, Exp Reward
    private static final List<Supplier<Enemy>> basicEnemies = List.of
    (
        () -> new Enemy("Slime", 20, 5, 30, 5, 10, 50),
        () -> new Enemy("Goblin", 30, 5, 40, 5, 10, 80),
        () -> new Enemy("Skeleton", 30, 5, 50, 5, 15, 100),
        () -> new Enemy("Wolf", 50, 8, 60, 5, 20, 120)
    );

    // Name, Stamina, Stamina Regen, Health, Defense, Attack, Exp Reward
    private static final List<Supplier<Enemy>> advancedEnemies = List.of
    (
        () -> new Enemy("Bandit", 60, 10, 100, 10, 30, 200),
        () -> new Enemy("Orc", 60, 10, 120, 20, 40, 300),
        () -> new Enemy("Dire Wolf", 60, 15, 100, 20, 40, 300)
    );

    // Name, Stamina, Stamina Regen, Health, Defense, Attack, Exp Reward
    private static final List<Supplier<Enemy>> bossEnemies = List.of
    (
        () -> new Enemy("Goblin King", 100, 20, 200, 30, 60, 1000),
        () -> new Enemy("Dragon", 100, 20, 300, 30, 60, 1500)
    );

    public static Enemy getRandomEnemy() {
        return switch (getRandomDifficulty()) {
            case 0 -> basicEnemies.get(new Random().nextInt(basicEnemies.size())).get();
            case 1 -> advancedEnemies.get(new Random().nextInt(advancedEnemies.size())).get();
            default -> basicEnemies.get(new Random().nextInt(basicEnemies.size())).get();
        };
    }

    public static Enemy getRandomBoss() {
        return bossEnemies.get(new Random().nextInt(bossEnemies.size())).get();
    }

    private static int getRandomDifficulty() {
        return new Random().nextInt(enemyDifficulty.values().length);
    }
    

}
