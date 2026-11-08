import java.util.ArrayList;

public abstract class Entity {
    
    private final String name;

    private int stamina;
    private int staminaRegen;

    private int healthStat;
    private int defenseStat;
    private int attackStat;

    protected int currentHealth;
    protected int currentStamina;

    protected ArrayList<Skill> skills = new ArrayList<>();

    protected Entity(String name, int stamina, 
            int staminaRegen, int healthStat, 
            int defenseStat, int attackStat) {

        this.name = name;
        this.stamina = stamina;
        this.staminaRegen = staminaRegen;
        setHealth(healthStat);
        setDefense(defenseStat);
        setAttack(attackStat);
        this.currentHealth = healthStat;
        this.currentStamina = stamina;

    }

    public void useSkill(int choice, Entity target) {

        Skill chosenSkill = skills.get(choice);
        chosenSkill.useStamina(this);
        chosenSkill.mechanic(this, target);
    
    }
    
    public String getStats() {
        return String.format("%s Stats:\nHealth: %d/%d\nStamina: %d/%d\nAttack: %d\nDefense: %d\n", 
        getName(), getCurrentHealth(), getHealthStat(), getCurrentStamina(), getStaminaStat(), getAttackStat(), getDefenseStat());
    }

    public String turnMessage() {
        return String.format("-------------- %s's turn! --------------\n", getName());
    }

    protected boolean checkIfAlive() {
        return currentHealth > 0;
    }

    public String deathMessage(Entity entity) {
        return String.format("%s has been defeated by the %s!%n", this.getName(), entity.getName());
    }

    //Getter
    public String getName() { return name; }
    public int getStaminaStat() { return stamina; }
    public int getStaminaRegen() { return staminaRegen; }
    public int getHealthStat() { return healthStat; }
    public int getDefenseStat() { return defenseStat; }
    public int getAttackStat() { return attackStat; }
    public int getCurrentHealth() { if(currentHealth <= 0) return 0; return currentHealth; }
    public int getCurrentStamina() { if(currentStamina <= 0) return 0; return currentStamina; }
    public ArrayList<Skill> getSkills() { return new ArrayList<>(skills); }

    //Setter
    public final void setHealth(int healthStat){
        this.healthStat = healthStat;
    }
    public final void setDefense(int defenseStat){
        this.defenseStat = defenseStat;
    }
    public final void setAttack(int attackStat){
        this.attackStat = attackStat;
    }
    public final void setStamina(int staminaStat) {
        this.stamina = staminaStat;
    }
    public final void setStaminaRegen(int staminaRegen) {
        this.staminaRegen = staminaRegen;
    }
    protected final void setCurrentHealth(int newCurrentHealth) {
        this.currentHealth = newCurrentHealth;
    }
    protected final void setCurrentStamina(int newCurrentStamina) {
        this.currentStamina = newCurrentStamina;
    }

}