public interface Effect {

    public void apply(Entity caster, Entity target, Skill.Context context);
    public String getCombatMessage(Entity caster, Entity target, Skill.Context context);

}

class DamageEffect implements Effect {
    
    private final float multiplier;

    public DamageEffect(float multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void apply(Entity caster, Entity target, Skill.Context context) {
        int baseDamage = (int)(multiplier * caster.getAttackStat());
        int damageDealt = target.takeDamage(baseDamage);
        context.addDamage(damageDealt);
    }

    @Override
    public String getCombatMessage(Entity caster, Entity target, Skill.Context context) {
        return String.format("%s dealt %d damage to the %s!%n", caster.getName(), context.getDamageDealt(), target.getName());
    }

}

class TrueDamageEffect implements Effect {
    
    private final float multiplier;

    public TrueDamageEffect(float multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void apply(Entity caster, Entity target, Skill.Context context) {
        int totalDamage = (int)(multiplier * caster.getAttackStat());
        int damageDealt = target.takeTrueDamage(totalDamage);
        context.addDamage(damageDealt);
    }

    @Override
    public String getCombatMessage(Entity caster, Entity target, Skill.Context context) {
        return String.format("%s dealt %d true damage to the %s!%n", caster.getName(), context.getDamageDealt(), target.getName());
    }

}

class HealEffect implements Effect {
    
    private final float multiplier;

    public HealEffect(float multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void apply(Entity caster, Entity target, Skill.Context context) {
        int totalHeal = (int)(multiplier * caster.getHealthStat());
        caster.heal(totalHeal);
        context.addHealthRestored(totalHeal);
    }

    @Override
    public String getCombatMessage(Entity caster, Entity target, Skill.Context context) {
        return String.format("%s restored %d health!%n", caster.getName(), context.getHealthRestored());
    }
    
}

class LifestealEffect implements Effect {
    
    private final float multiplier;

    public LifestealEffect(float multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void apply(Entity caster, Entity target, Skill.Context context) {
        int lifesteal = (int)(context.getDamageDealt() * multiplier);
        caster.heal(lifesteal);
        context.addHealthRestored(lifesteal);
    }

    @Override
    public String getCombatMessage(Entity caster, Entity target, Skill.Context context) {
        return String.format("%s restored %d health from lifesteal!%n", caster.getName(), context.getHealthRestored());
    }

}

class LostHpDamageEffect implements Effect {

    private final float multiplier;

    public LostHpDamageEffect(float multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void apply(Entity caster, Entity target, Skill.Context context) {
        int lostHp = target.getHealthStat() - target.getCurrentHealth();
        int extraDamage = (int)(lostHp * multiplier);
        int damageDealt = target.takeDamage(extraDamage);
        context.addDamage(damageDealt);
    }

    @Override
    public String getCombatMessage(Entity caster, Entity target, Skill.Context context) {
        int lostHp = target.getHealthStat() - target.getCurrentHealth();
        int extraDamage = (int)(lostHp * multiplier);
        return String.format("%s dealt additional %d damage to the %s based on their missing %d health!%n", caster.getName(), extraDamage, target.getName(), lostHp);
    }

}