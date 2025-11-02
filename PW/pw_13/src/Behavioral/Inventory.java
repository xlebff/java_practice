package Behavioral;

import java.util.*;

public class Inventory {
    private Map<String, Item> items;
    private int capacity;
    private Item equippedWeapon;

    public Inventory(int capacity) {
        this.items = new HashMap<>();
        this.capacity = capacity;
        this.equippedWeapon = new Item(ItemType.FISTS, 5.0f);
    }

    public void equipWeapon(Item weapon) {
        if (weapon.getType().isWeapon() && !weapon.isBroken()) {
            equippedWeapon = weapon;
            System.out.println("Экипировано оружие: " + weapon.getName());
        } else if (weapon.isBroken()) {
            System.out.println(weapon.getName() + " сломан и не может быть экипирован!");
        }
    }

    public void unequipWeapon() {
        if (equippedWeapon.getType() != ItemType.FISTS) {
            System.out.println("Оружие снято, экипированы кулаки");
            equippedWeapon = new Item(ItemType.FISTS, 5.0f);
        } else {
            System.out.println("Кулаки нельзя снять!");
        }
    }

    public boolean addItem(Item item) {
        if (item.getType() == ItemType.FISTS) {
            System.out.println("Кулаки нельзя положить в инвентарь!");
            return false;
        }

        if (items.size() >= capacity) {
            System.out.println("Инвентарь полон!");
            return false;
        }

        if (items.containsKey(item.getName())) {
            Item existing = items.get(item.getName());
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
        } else {
            items.put(item.getName(), item);
        }

        System.out.println("Добавлен предмет: " + item.getName());
        return true;
    }

    public boolean removeItem(String itemName) {
        if (itemName.equalsIgnoreCase("кулаки") || itemName.equalsIgnoreCase("fists")) {
            System.out.println("Кулаки нельзя удалить!");
            return false;
        }

        if (items.containsKey(itemName)) {
            Item item = items.get(itemName);
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else {
                if (equippedWeapon.getName().equals(itemName)) {
                    unequipWeapon();
                }
                items.remove(itemName);
            }
            System.out.println("Удален предмет: " + itemName);
            return true;
        }
        return false;
    }

    public void showInventory() {
        System.out.println("\nИНВЕНТАРЬ");
        System.out.println("Занято: " + items.size() + "/" + capacity);
        System.out.println("Экипированное оружие: " + equippedWeapon.getName() +
                " (урон: " + equippedWeapon.getValue() + ")");

        if (equippedWeapon.getType() == ItemType.FISTS) {
            System.out.println("Кулаки - базовое оружие, всегда доступно");
        }

        if (items.isEmpty()) {
            System.out.println("Инвентарь пуст");
        } else {
            System.out.println("\nОружие");
            items.values().stream()
                    .filter(item -> item.getType().isWeapon())
                    .forEach(System.out::println);

            System.out.println("\nБроня");
            items.values().stream()
                    .filter(item -> item.getType().isArmor())
                    .forEach(System.out::println);

            System.out.println("\nЗелья");
            items.values().stream()
                    .filter(item -> item.getType().isPotion())
                    .forEach(System.out::println);

            System.out.println("\nЕда");
            items.values().stream()
                    .filter(item -> item.getType().isFood())
                    .forEach(System.out::println);

            System.out.println("\nПрочее");
            items.values().stream()
                    .filter(item -> !item.getType().isWeapon() &&
                            !item.getType().isArmor() &&
                            !item.getType().isPotion() &&
                            !item.getType().isFood())
                    .forEach(System.out::println);
        }
        System.out.println("\n");
    }

    public float getWeaponDamage() {
        return equippedWeapon.getValue();
    }

    public void useItem(String itemName, Player player) {
        if (items.containsKey(itemName)) {
            Item item = items.get(itemName);
            if (item.getType().isEquippable() && item.isBroken()) {
                System.out.println(itemName + " сломан и не может быть использован!");
                return;
            }
            item.use(player);
            if (item.getQuantity() <= 0 && !item.getType().isEquippable()) {
                items.remove(itemName);
            }
        } else {
            System.out.println("Предмет не найден: " + itemName);
        }
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public Map<String, Item> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public int getCapacity() {
        return capacity;
    }
}