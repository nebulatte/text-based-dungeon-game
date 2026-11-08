// Basic Attack = 0 Stamina Cost

public class BasicAttack extends DamageSkill{
    
    public BasicAttack() {
        // Skill Name / Stamina Cost / Damage Multiplier
        super("Basic Attack", 0, 1.0f);
    }

    @Override
    public String getDescription(Entity caster) {
        return String.format("[%d Stamina Cost] Deals %d base damage", getStaminaCost(), getTotalDamage(caster));
    }

    @Override 
    public String combatMessage(Entity caster, Entity target) {
        String message = String.format("Dealt %d damage to the %s using \"%s\"!%n", getReducedDamage(caster, target), target.getName(), getSkillName());
        message += String.format("The %s has %d health remaining!%n", target.getName(), target.getCurrentHealth());
        message += String.format("Stamina spent: %d%n", getStaminaCost());
        return message;
    }

}
