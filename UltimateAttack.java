// Ultimate Attack = 50 Stamina Cost

public class UltimateAttack extends DamageSkill {
    
    public UltimateAttack() {
        // Skill Name / Stamina Cost / Damage Multiplier
        super("Ultimate Attack", 50, 4.5f);

    }

    @Override
    public String getDescription(Entity caster) {
        return String.format("[%d Stamina Cost] Unleashes a devastating %d base damage, ignoring the target's defense", getStaminaCost(), getTotalDamage(caster));
    }
    
    @Override
    public void damageMechanic(Entity caster, Entity target) {
        int currentHealth = target.getCurrentHealth();
        int totalDamage = getTotalDamage(caster);
        target.setCurrentHealth(currentHealth - totalDamage);
    }

    @Override 
    public String combatMessage(Entity caster, Entity target) {
        String message = String.format("Dealt a massive %d damage to the %s using \"%s\"!%n", getTotalDamage(caster), target.getName(), getSkillName());
        message += String.format("The %s has %d health remaining!%n", target.getName(), target.getCurrentHealth());
        message += String.format("Stamina spent: %d%n", getStaminaCost());
        return message;
    }

}
