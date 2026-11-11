import java.util.List;

public class Skill {

    public class Context {
    
        private int damageDealt;
        private int healthRestored;

        public void addDamage(int amount) { damageDealt += amount; }
        public int getDamageDealt() { return damageDealt; }

        public void addHealthRestored(int amount) { healthRestored += amount; }
        public int getHealthRestored() { return healthRestored; }

    }

    private final String skillName;
    private final String skillDescription;
    private final int staminaCost;
    private final List<Effect> effects;

    public Skill(String skillName, String skillDescription,
            int staminaCost, List<Effect> effects) {
    
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.staminaCost = staminaCost;
        this.effects = effects;

    }

    public String getSkillName() {
        return skillName;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public int getStaminaCost() {
        return staminaCost;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void spendStamina(Entity caster) {
        if (caster.getCurrentStamina() < staminaCost) {
            throw new InsufficientStamina(String.format("%s needs %d stamina!%n", getSkillName(), getStaminaCost()));
        }
        caster.setCurrentStamina(caster.getCurrentStamina() - staminaCost);
    }

    public Context mechanic(Entity caster, Entity target) {
        Context context = new Context();
        for(Effect e : effects) { e.apply(caster, target, context); }
        return context;
    }

}