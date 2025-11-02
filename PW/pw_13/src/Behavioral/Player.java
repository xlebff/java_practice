package Behavioral;

import Structural.GameFacade;

public class Player {
    private String name;
    private float maxHealth;
    private float health;
    private float attackDamage;
    private float movementSpeed;

    private PlayerState currentState;
    private Inventory inventory;
    private GameMap gameMap;
    private GameFacade gameFacade;

    public Player(String name) {
        this.name = name;
        this.maxHealth = 100.0f;
        this.health = maxHealth;
        this.attackDamage = 10.0f;
        this.movementSpeed = 5.0f;

        this.inventory = new Inventory(20);
        this.gameMap = new GameMap();
        this.gameFacade = new GameFacade(this);
        this.currentState = new ExplorationState();

        this.currentState.enterState(this);
        initializeStartingItems();
    }

    private void initializeStartingItems() {
        inventory.addItem(new Item("Свежий хлеб", ItemType.BREAD, 15.0f, 3));
        inventory.addItem(new Item(ItemType.HEALTH_POTION, 25.0f));
        inventory.addItem(new Item("Простой меч", ItemType.SWORD, 12.0f));
        inventory.addItem(new Item(ItemType.KEY, 0f));
        inventory.addItem(new Item("Кожаный шлем", ItemType.HELMET, 5.0f));
    }

    public void update() {
        currentState.update(this);
    }

    public void handleInput(String input) {
        currentState.handleInput(this, input);
    }

    public void setState(PlayerState newState) {
        currentState.exitState(this);
        this.currentState = newState;
        newState.enterState(this);
    }

    public void takeDamage(float damage) {
        if (getCurrentState() instanceof DeadState) {
            return;
        }

        health = Math.max(0, health - damage);
        System.out.println("Игрок получает " + damage + " урона. Здоровье: " + health);

        if (health <= 0 && !(getCurrentState() instanceof DeadState)) {
            setState(new DeadState());
        }
    }

    public void heal(float amount) {
        if (getCurrentState() instanceof DeadState) {
            System.out.println("Нельзя лечиться будучи мертвым!");
            return;
        }

        float oldHealth = health;
        health = Math.min(maxHealth, health + amount);
        float actualHeal = health - oldHealth;
        if (actualHeal > 0) {
            System.out.println("Восстановлено здоровья: +" + actualHeal + " (" + health + "/" + maxHealth + ")");
        }
    }

    public float calculateDamage() {
        float weaponBonus = inventory.getWeaponDamage();
        float baseDamage = attackDamage;

        if (inventory.getEquippedWeapon().getType() == ItemType.FISTS) {
            baseDamage *= 1.1f;
        }

        return baseDamage + weaponBonus;
    }

    public void switchToFists() {
        inventory.unequipWeapon();
        System.out.println("Переключение на кулаки");
    }

    public String getName() {
        return name;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public PlayerState getCurrentState() {
        return currentState;
    }

    public String getCurrentStateName() {
        return currentState.getStateName();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public GameFacade getGameFacade() {
        return gameFacade;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}