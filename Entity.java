import java.util.ArrayList;

public abstract class Entity {
    
    private final String name;

    private int healthStat;
    private int defenseStat;
    private int attackStat;

    private int stamina;
    private int staminaRegen;

    protected int currentHealth;
    protected int currentStamina;

    private final int MITIGATION_CONSTANT = 100;

    protected ArrayList<Skill> skills = new ArrayList<>();

    public Entity(String name, int stamina, 
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

    protected Skill.Context useSkill(int choice, Entity target) {

        Skill chosenSkill = skills.get(choice);
        chosenSkill.spendStamina(this);
        Skill.Context context = chosenSkill.mechanic(this, target);
        return context;
    
    }

    protected int takeDamage(int baseDamage) {
        float reduction = ((float)getDefenseStat() / ((float)getDefenseStat() + (float)MITIGATION_CONSTANT));
        int actualDamage = (int)((float)baseDamage * (float)(1 - reduction));
        setCurrentHealth(getCurrentHealth() - actualDamage);
        return actualDamage;
    }

    protected int takeTrueDamage(int totalDamage) {
        setCurrentHealth(getCurrentHealth() - totalDamage);
        return totalDamage;
    }

    protected int heal(int totalHeal) {
        int amount = totalHeal;
        if(getCurrentHealth() + amount > getHealthStat()) { amount = getHealthStat() - getCurrentHealth(); }
        setCurrentHealth(getCurrentHealth() + amount);
        return amount;
    }

    protected void regenerateStamina() {
        setCurrentStamina(getCurrentStamina() + getStaminaRegen());
    }

    protected void regenerateStamina(int extraStamina) {
        setCurrentStamina(getCurrentStamina() + getStaminaRegen() + extraStamina);
    }
    
    protected String getStats() {
        return String.format("%s Stats:\nHealth: %d/%d\nStamina: %d/%d\nAttack: %d\nDefense: %d\n", 
        getName(), getCurrentHealth(), getHealthStat(), getCurrentStamina(), getStaminaStat(), getAttackStat(), getDefenseStat());
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

    //Setters
    protected final void setHealth(int healthStat){ this.healthStat = healthStat; }
    protected final void setDefense(int defenseStat){ this.defenseStat = defenseStat; }
    protected final void setAttack(int attackStat){ this.attackStat = attackStat; }
    protected final void setStamina(int staminaStat) { this.stamina = staminaStat; }
    protected final void setStaminaRegen(int staminaRegen) { this.staminaRegen = staminaRegen; }
    protected final void setCurrentHealth(int newCurrentHealth) { this.currentHealth = newCurrentHealth; }
    protected final void setCurrentStamina(int newCurrentStamina) { this.currentStamina = newCurrentStamina; }

}