import java.util.Random;

public class BasicEnemy extends Enemy {

    private static final int MAX_STAMINA = 20;
    private static final int STAMINA_REGEN = 5;
    private static final int MAX_HEALTH = 50;
    private static final int DEFENSE = 10;
    private static final int ATTACK = 5;
    private static final int EXP_REWARD = 40;

    public BasicEnemy(String name) {
    super(name, MAX_STAMINA, STAMINA_REGEN, MAX_HEALTH, DEFENSE, ATTACK, EXP_REWARD);
    }

    @Override 
    public String appearanceMessage() {
        Random randomizer = new Random();
        int randomizedText = randomizer.nextInt(4);

        String message = "";
        switch(randomizedText) {
            case 0 -> message = String.format("You have been ambushed by a %s!%n", getName());
            case 1 -> message = String.format("A %s surprises you!%n", getName());
            case 2 -> message = String.format("A wild %s appeared!%n", getName());
            case 3 -> message = String.format("Beware! A %s stands in your way!%n", getName());
        }

        return message;
    }
    
}
