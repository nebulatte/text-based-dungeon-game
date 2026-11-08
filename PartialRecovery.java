// Partial Recovery = 50% Health Restoration, 30 Stamina Cost

public class PartialRecovery extends HealingSkill{

    public PartialRecovery() {
        // Skill Name / Stamina Cost / Healing Multiplier
        super("Partial Recovery", 30, 0.5f);
    }

    @Override
    public String getDescription(Entity caster) {
        return String.format("[%d Stamina Cost] Restores %d%% health", getStaminaCost(), (int)(getHealingMultiplier() * 100));
    }
    
    @Override 
    public String healingMessage(Entity caster) {
        String message = String.format("%s used the skill \"%s\" to restore %d health!%n", caster.getName(), 
            getSkillName(), getTotalHealing(caster));
        message += String.format("Your health is now %d!%n", caster.getCurrentHealth());
        message += String.format("Stamina spent: %d%n", getStaminaCost());
        return message;
    }
}
