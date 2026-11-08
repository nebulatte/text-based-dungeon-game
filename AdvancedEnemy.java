import java.util.Random;

public class AdvancedEnemy extends Enemy {

    private static final int MAX_STAMINA = 50;
    private static final int STAMINA_REGEN = 10;
    private static final int MAX_HEALTH = 100;
    private static final int DEFENSE = 25;
    private static final int ATTACK = 7;
    private static final int EXP_REWARD = 100;

    public AdvancedEnemy(String name) {
        super(name, MAX_STAMINA, STAMINA_REGEN, MAX_HEALTH, DEFENSE, ATTACK, EXP_REWARD);
    }

    @Override 
    public String appearanceMessage() {
        Random randomizer = new Random();
        int randomizedText = randomizer.nextInt(4);

        String message = "";
        switch(randomizedText) {
            case 0 -> message = String.format("You have been ambushed by a %s!%n", getName());
            case 1 -> message = String.format("A %s blocks your path!%n", getName());
            case 2 -> message = String.format("A wild %s appears before you!%n", getName());
            case 3 -> message = String.format("A terrifying %s emerges from the shadows!%n", getName());
       }
       
        return message;
    }

}
