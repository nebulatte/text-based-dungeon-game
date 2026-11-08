public abstract class HealingSkill extends Skill{
        
    private final float HEALING_MULTIPLIER;

    public HealingSkill(String skillName, int staminaCost, float multiplier) {
        super(skillName, staminaCost);
        this.HEALING_MULTIPLIER = multiplier;
    }

    public float getHealingMultiplier() {
        return HEALING_MULTIPLIER;
    }

    public int getTotalHealing(Entity caster) {
        float totalHealing = caster.getHealthStat() * HEALING_MULTIPLIER;
        return (int)totalHealing;
    }
    
    public void healingMechanic(Entity caster) {
        int currentHealth = caster.getCurrentHealth();
        if (currentHealth + getTotalHealing(caster) > caster.getHealthStat()) {
            caster.setCurrentHealth(caster.getHealthStat());
            return;
        }
        caster.setCurrentHealth(currentHealth + getTotalHealing(caster));
    }

    @Override
    public void mechanic(Entity caster, Entity target) {
        healingMechanic(caster);
    }

    public abstract String healingMessage(Entity caster);

    @Override
    public String combatMessage(Entity caster, Entity target) {
        return healingMessage(caster);
    }

}
