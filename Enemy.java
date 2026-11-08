public abstract class Enemy extends Entity {

    private final int BASIC_ATTACK_INDEX = 0;
    private final int HEAVY_ATTACK_INDEX = 1;
    private final int ULTIMATE_ATTACK_INDEX = 2;
    private final int EXP_REWARD;

    public Enemy(String name, int stamina, 
            int staminaRegen, int healthStat, 
            int defenseStat, int attackStat, 
            int EXP_REWARD) {

        super(name, stamina, staminaRegen, healthStat, defenseStat, attackStat);

        this.EXP_REWARD = EXP_REWARD;
        skills.add(new BasicAttack());
        skills.add(new HeavyAttack());
        skills.add(new UltimateAttack());

    }

    public abstract String appearanceMessage();

    public int chooseSkill() {
        
        // Priority 1: If the enemy has enough stamina, use the ultimate attack first
        if(getCurrentStamina() >= ((UltimateAttack)skills.get(ULTIMATE_ATTACK_INDEX)).getStaminaCost()) {
            return ULTIMATE_ATTACK_INDEX;
        }
        // Priority 2: If the enemy can't do an ultimate attack, try heavy attack
        if(getCurrentStamina() >= ((HeavyAttack)skills.get(HEAVY_ATTACK_INDEX)).getStaminaCost()) {
            return HEAVY_ATTACK_INDEX;
        }
        // Priority 3: If the enemy lacks stamina, use normal attacks instead
        return BASIC_ATTACK_INDEX;
        
    }

    public int getExpReward() {
        return EXP_REWARD;
    }

}