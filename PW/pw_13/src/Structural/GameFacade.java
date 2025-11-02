package Structural;

import Behavioral.*;

public class GameFacade {
    private Player player;
    private Inventory inventory;
    private GameMap gameMap;

    public GameFacade(Player player) {
        this.player = player;
        this.inventory = player.getInventory();
        this.gameMap = player.getGameMap();
    }

    public void showPlayerStatus() {
        System.out.println("\nСТАТУС ИГРОКА");
        System.out.println("Здоровье: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Состояние: " + player.getCurrentStateName());
        System.out.println("Локация: " + gameMap.getCurrentLocation());
        System.out.println("\n");
    }

    public void showInventory() {
        inventory.showInventory();
    }

    public void showMap() {
        gameMap.showMap();
    }

    public void useItem(String itemName) {
        inventory.useItem(itemName, player);
    }

    public void useFood() {
        inventory.getItems().values().stream()
                .filter(item -> "FOOD".equals(item.getType()))
                .findFirst()
                .ifPresentOrElse(
                        food -> inventory.useItem(food.getName(), player),
                        () -> System.out.println("Еда не найдена в инвентаре")
                );
    }

    public void useCombatItem() {
        // В бою приоритет зельям
        inventory.getItems().values().stream()
                .filter(item -> "POTION".equals(item.getType()))
                .findFirst()
                .ifPresentOrElse(
                        potion -> inventory.useItem(potion.getName(), player),
                        () -> useFood()
                );
    }

    public void moveToLocation(String location) {
        gameMap.moveTo(location);
    }

    public void quickRest() {
        if (player.getHealth() < player.getMaxHealth() * 0.5f) {
            player.setState(new RestingState());
        } else {
            System.out.println("Нет необходимости отдыхать");
        }
    }

    public void respawn() {
        if (player.getCurrentState() instanceof DeadState) {
            player.handleInput("respawn");
        } else {
            System.out.println("Игрок не мертв!");
        }
    }

    public void showDeathScreen() {
        if (player.getCurrentState() instanceof DeadState) {
            System.out.println("\n=== ВЫ ПОГИБЛИ ===");
            System.out.println("Ваш герой пал в бою...");
            System.out.println("Доступные команды:");
            System.out.println("respawn - возродиться");
            System.out.println("quit - выйти из игры");
            System.out.println("==================\n");
        }
    }

    public void equipWeapon(String weaponName) {
        if (inventory.getItems().containsKey(weaponName)) {
            Item weapon = inventory.getItems().get(weaponName);
            if (weapon.getType().isWeapon()) {
                inventory.equipWeapon(weapon);
            } else {
                System.out.println(weaponName + " не является оружием!");
            }
        } else {
            System.out.println("Оружие не найдено: " + weaponName);
        }
    }

    public void unequipWeapon() {
        inventory.unequipWeapon();
    }

    public void showWeaponInfo() {
        Item weapon = inventory.getEquippedWeapon();
        System.out.println("\nТЕКУЩЕЕ ОРУЖИЕ");
        System.out.println("Название: " + weapon.getName());
        System.out.println("Урон: " + weapon.getValue());
        System.out.println("Тип: " + weapon.getType().getDisplayName());

        if (weapon.getType() == ItemType.FISTS) {
            System.out.println("Особенности:");
            System.out.println("- Всегда доступно");
            System.out.println("- +10% к базовому урону");
            System.out.println("- Не ломается");
        } else if (weapon.getDurability() > 0) {
            System.out.println("Прочность: " + weapon.getDurability() + "/" + weapon.getMaxDurability());
        }
        System.out.println("\n");
    }
}