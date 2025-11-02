package Creational;

public abstract class NPC {
    protected NPCType type;
    protected String name;
    protected float maxHealth;
    protected float health;
    protected boolean isAlive;

    public NPC(NPCType type, String name, float health) {
        this.type = type;
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.isAlive = true;
    }

    public abstract NPC clone();

    public boolean isTalkable() {
        return type == NPCType.VILLAGER && isAlive;
    }

    public boolean isAttackable() {
        return (type == NPCType.BOSS || type == NPCType.MOB) && isAlive;
    }

    public abstract void getHit(float damage);

    public void dying() {
        if (health <= 0 && isAlive) {
            health = 0;
            isAlive = false;
            System.out.println(name + " (" + type + ") погиб!");
        }
    }

    public void heal(float amount) {
        if (isAlive) {
            health = Math.min(health + amount, maxHealth);
            System.out.println(name + " восстановил " + amount + " здоровья. Текущее здоровье: " + health);
        }
    }

    public void displayStatus() {
        String status = isAlive ? "Жив (" + health + "/" + maxHealth + " HP)" : "Мертв";
        System.out.println(name + " [" + type + "] - " + status);
    }

    public NPCType getType() {
        return type;
    }

    public void setHealth(float health) {
        this.health = Math.max(0, health);
        dying();
    }

    public float getHealth() {
        return health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxHealth() {
        return maxHealth;
    }
}