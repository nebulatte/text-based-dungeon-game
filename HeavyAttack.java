// Heavy Attack = 20 Stamina Cost

public class HeavyAttack extends DamageSkill {

    public HeavyAttack() {
        // Skill Name / Stamina Cost / Damage Multiplier
        super("Heavy Attack", 20, 2.5f);
    }

    @Override
    public String getDescription(Entity caster) {
        return String.format("[%d Stamina Cost] Gathers strength to deal %d base damage", getStaminaCost(), getTotalDamage(caster));
    }
    @Override 
    public String combatMessage(Entity caster, Entity target) {
        String message = String.format("Dealt considerable %d damage to the %s using \"%s\"!%n", getReducedDamage(caster, target), target.getName(), getSkillName());
        message += String.format("The %s has %d health remaining!%n", target.getName(), target.getCurrentHealth());
        message += String.format("Stamina spent: %d%n", getStaminaCost());
        return message;
    }

}
