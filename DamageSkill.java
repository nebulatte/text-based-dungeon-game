public abstract class DamageSkill extends Skill {

    private final float DMG_MULTIPLIER;
    private final float DEFENSE_REDUCTION_FACTOR = 0.2f;
    
    public DamageSkill(String skillName, int staminaCost, float multiplier) {
        super(skillName, staminaCost);
        this.DMG_MULTIPLIER = multiplier;
    }

    public float getDamageMultiplier() {
        return DMG_MULTIPLIER;
    }

    public int getTotalDamage(Entity caster) {
        return (int) (DMG_MULTIPLIER * caster.getAttackStat());
    }

    public int getReducedDamage(Entity caster, Entity target) {
        return (int) (getTotalDamage(caster) - (target.getDefenseStat() * DEFENSE_REDUCTION_FACTOR));
    }

    public void damageMechanic(Entity caster, Entity target) {
        int currentHealth = target.getCurrentHealth();
        int reducedDamage = getReducedDamage(caster, target);
        target.setCurrentHealth(currentHealth - reducedDamage);
    }

    @Override
    public void mechanic(Entity caster, Entity target) {
        damageMechanic(caster, target);
    }

    @Override
    public abstract String combatMessage(Entity caster, Entity target);
    
}