package Creational;

import Behavioral.Player;

public class Mob extends NPC {
    private float strength;
    private float damage;
    private float defence;
    private float attackCooldown;

    public Mob(String name, float health, float strength, float damage, float defence) {
        super(NPCType.MOB, name, health);
        this.strength = strength;
        this.damage = damage;
        this.defence = Math.min(defence, 0.8f);
        this.attackCooldown = 2.0f;
    }

    public Mob(String name, float health, float strength, float damage, float defence, float attackCooldown) {
        this(name, health, strength, damage, defence);
        this.attackCooldown = attackCooldown;
    }

    @Override
    public NPC clone() {
        return new Mob(name, health, strength, damage, defence, attackCooldown);
    }

    public void attack(Player player) {
        if (isAlive) {
            float actualDamage = damage * strength;
            System.out.println(name + " атакует с силой " + actualDamage);
            player.takeDamage(actualDamage);
        }
    }

    @Override
    public void getHit(float damage) {
        if (isAlive) {
            float damageAfterDefence = damage * (1 - defence);
            health -= damageAfterDefence;
            System.out.println(name + " получает " + damageAfterDefence + " урона (заблокировано: " + (damage * defence) + ")");
            dying();
        }
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDefence() {
        return defence;
    }

    public void setDefence(float defence) {
        this.defence = Math.min(defence, 0.8f);
    }

    public float getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(float attackCooldown) {
        this.attackCooldown = attackCooldown;
    }
}