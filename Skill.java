public abstract class Skill {

    private final String skillName;
    private final int staminaCost;

    protected Skill(String skillName, int staminaCost) {
        this.skillName = skillName;
        this.staminaCost = staminaCost;
    }

    public String getSkillName() {
        return skillName;
    }

    public int getStaminaCost() {
        return staminaCost;
    }

    public void useStamina(Entity caster) {
        if (caster.getCurrentStamina() < staminaCost) {
            throw new InsufficientStamina(String.format("%s needs %d stamina!%n", getSkillName(), getStaminaCost()));
        }
        caster.setCurrentStamina(caster.getCurrentStamina() - staminaCost);
    }

    public abstract void mechanic(Entity caster, Entity target);

    public abstract String combatMessage(Entity caster, Entity target);

    public abstract String getDescription(Entity caster);

}