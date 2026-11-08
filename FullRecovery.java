// Full Recovery = 100% Health Restoration, 50 Stamina Cost

public class FullRecovery extends HealingSkill {

    public FullRecovery() {
        // Skill Name / Stamina Cost / Healing Multiplier
        super("Full Recovery", 50, 1.0f);
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
