public class Player extends Entity {

    private int killCount;
    private int totalExp;
    private int currentLevel;
    private int expToNextLevel;

    private int oldHealthStat;
    private int oldAttackStat;
    private int oldDefenseStat;
    private int oldStaminaStat;
    private int oldStaminaRegenStat;

    private final int STAMINA_INCREASE = 20;
    private final int STAMINA_REGEN_INCREASE = 5;
    private final int HEALTH_INCREASE = 5;
    private final int DEFENSE_INCREASE = 2;
    private final int ATTACK_INCREASE = 5;

    private final int LEVELS_FOR_STAMINA_INCREASE = 2;
    private final int LEVELS_FOR_STAMINA_REGEN_INCREASE = 2;
    private final int LEVELS_FOR_HEALTH_INCREASE = 1;
    private final int LEVELS_FOR_DEFENSE_INCREASE = 2;
    private final int LEVELS_FOR_ATTACK_INCREASE = 1;
    
    public Player(String name, int staminaStat, 
            int staminaRegen, int healthStat, 
            int defenseStat, int attackStat) {

        super(name, staminaStat, staminaRegen, healthStat, defenseStat, attackStat);

        this.killCount = 0;
        this.totalExp = 0;
        this.currentLevel = 1;
        this.expToNextLevel = 100;

        skills.add(new BasicAttack());
        skills.add(new HeavyAttack());
        skills.add(new UltimateAttack());
        skills.add(new PartialRecovery());
        skills.add(new FullRecovery());

        this.oldHealthStat = getHealthStat();
        this.oldAttackStat = getAttackStat();
        this.oldDefenseStat = getDefenseStat();
        this.oldStaminaStat = getStaminaStat();
        this.oldStaminaRegenStat = getStaminaRegen();

    }

    public int getTotalSkills() { return skills.size(); }
    public int getKillCount() { return killCount; }
    public int getTotalExp() { return totalExp; }
    public int getCurrentLevel() { return currentLevel; }
    public int getExpToNextLevel() { return expToNextLevel; }

    public void addExp(int exp) { totalExp += exp; }
    public void addKillCount() { killCount++; }
    public String expGainMessage(Enemy enemy) {
        return getName() + " gained " + enemy.getExpReward() + " EXP from defeating " + enemy.getName() + "!";
    }

    public void levelUp() {
        raiseStats(currentLevel);
        currentLevel++;
        totalExp -= expToNextLevel;
        expToNextLevel = calcExpToNextLevel();
    }

    private int calcExpToNextLevel() {
        return (int) (Math.sqrt(currentLevel) * 100);
    }

    public String getLevelUpMessage() {
        String message = String.format("%s leveled up to level %d!%n", getName(), currentLevel);
        if(oldHealthStat != getHealthStat())message += getCurrentAndNextStats("Health", oldHealthStat, getHealthStat());
        if(oldAttackStat != getAttackStat())message += getCurrentAndNextStats("Attack", oldAttackStat, getAttackStat());
        if(oldDefenseStat != getDefenseStat())message += getCurrentAndNextStats("Defense", oldDefenseStat, getDefenseStat());
        if(oldStaminaStat != getStaminaStat())message += getCurrentAndNextStats("Stamina", oldStaminaStat, getStaminaStat());
        if(oldStaminaRegenStat != getStaminaRegen())message += getCurrentAndNextStats("Stamina Regen", oldStaminaRegenStat, getStaminaRegen());
        return message;
    }

    private String getCurrentAndNextStats(String stat, int previous, int now) {
        return String.format("%s: %d -> %d%n", stat, previous, now);
    }

    public String getExpStats() { 
        return String.format("EXP: %d/%d | Level: %d", totalExp, expToNextLevel, currentLevel);
    }

    private void raiseStats(int currentLevel) {
        if(currentLevel % LEVELS_FOR_ATTACK_INCREASE == 0) {
            oldAttackStat = getAttackStat();
            setAttack(oldAttackStat + ATTACK_INCREASE);
        }
        if(currentLevel % LEVELS_FOR_DEFENSE_INCREASE == 0) {
            oldDefenseStat = getDefenseStat();
            setDefense(oldDefenseStat + DEFENSE_INCREASE);
        }
        if(currentLevel % LEVELS_FOR_HEALTH_INCREASE == 0) {
            oldHealthStat = getHealthStat();
            setHealth(oldHealthStat + HEALTH_INCREASE);
        }
        if(currentLevel % LEVELS_FOR_STAMINA_INCREASE == 0) {
            oldStaminaStat = getStaminaStat();
            setStamina(oldStaminaStat + STAMINA_INCREASE);
        }
        if(currentLevel % LEVELS_FOR_STAMINA_REGEN_INCREASE == 0) {
            oldStaminaRegenStat = getStaminaRegen();
            setStaminaRegen(oldStaminaRegenStat + STAMINA_REGEN_INCREASE);
        }
    }

}