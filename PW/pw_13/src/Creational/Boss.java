package Creational;

import Behavioral.Player;

public class Boss extends NPC {
    private float strength;
    private float damage;
    private float defence;
    private int phase;
    private String[] phaseDialogues;
    private boolean enraged;

    public Boss(String name, float health, float strength, float damage, float defence) {
        super(NPCType.BOSS, name, health);
        this.strength = strength;
        this.damage = damage;
        this.defence = Math.min(defence, 0.7f);
        this.phase = 1;
        this.enraged = false;
        this.phaseDialogues = new String[]{
                "Вы смеете бросать вызов мне?!",
                "Вы сильнее, чем я ожидал!",
                "НИКТО не сможет остановить меня!"
        };
    }

    @Override
    public NPC clone() {
        Boss cloned = new Boss(name, health, strength, damage, defence);
        cloned.phase = this.phase;
        cloned.enraged = this.enraged;
        return cloned;
    }

    @Override
    public void getHit(float damage) {
        if (isAlive) {
            float damageAfterDefence = damage * (1 - defence);
            health -= damageAfterDefence;
            System.out.println(name + " получает " + damageAfterDefence + " урона");

            checkPhaseTransition();
            dying();
        }
    }

    private void checkPhaseTransition() {
        if (phase == 1 && health < maxHealth * 0.7f) {
            phase = 2;
            System.out.println("\n" + name + " входит во вторую фазу!");
            System.out.println(phaseDialogues[0]);
            strength *= 1.3f;
        } else if (phase == 2 && health < maxHealth * 0.3f) {
            phase = 3;
            enraged = true;
            System.out.println("\n"+ name + " ВПАДАЕТ В ЯРОСТЬ!");
            System.out.println(phaseDialogues[1]);
            strength *= 1.5f;
            damage *= 1.4f;
        }
    }

    public void specialAttack(Player player) {
        if (isAlive) {
            float specialDamage = damage * strength * (enraged ? 1.8f : 1.3f);
            System.out.println(name + " использует специальную атаку! Урон: " + specialDamage);
            player.takeDamage(specialDamage);
        }
    }

    public void areaAttack(Player[] players) {
        if (isAlive) {
            float areaDamage = damage * strength * 0.7f;
            System.out.println(name + " использует атаку по области! Урон: " + areaDamage);
            for (Player player : players) {
                player.takeDamage(areaDamage);
            }
        }
    }

    public int getPhase() {
        return phase;
    }

    public boolean isEnraged() {
        return enraged;
    }

    public float getStrength() {
        return strength;
    }

    public float getDamage() {
        return damage;
    }

    public float getDefence() {
        return defence;
    }
}