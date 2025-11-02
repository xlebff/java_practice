package Behavioral;

public class Item {
    private String name;
    private ItemType type;
    private float value;
    private int quantity;
    private int durability;
    private int maxDurability;

    public Item(String name, ItemType type, float value) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.quantity = 1;
        initializeDurability();
    }

    public Item(String name, ItemType type, float value, int quantity) {
        this(name, type, value);
        this.quantity = quantity;
    }

    public Item(ItemType type, float value) {
        this(type.getDisplayName(), type, value);
    }

    private void initializeDurability() {
        if (type.isEquippable()) {
            this.maxDurability = 100;
            this.durability = maxDurability;
        } else {
            this.maxDurability = -1;
            this.durability = -1;
        }
    }

    public void use(Player player) {
        if (quantity <= 0) {
            System.out.println(name + " закончился!");
            return;
        }

        switch (type.getCategory()) {
            case "FOOD" -> {
                player.heal(value);
                quantity--;
                System.out.println("Использован " + name + ", восстановлено: " + value + " HP");
            }
            case "POTION" -> {
                applyPotionEffect(player);
                quantity--;
            }
            case "WEAPON" -> {
                if (durability > 0) {
                    player.getInventory().equipWeapon(this);
                    reduceDurability(1);
                } else {
                    System.out.println(name + " сломалось!");
                }
            }
            default -> System.out.println("Использован предмет: " + name);
        }
    }

    private void applyPotionEffect(Player player) {
        switch (type) {
            case HEALTH_POTION -> {
                player.heal(value * 2);
                System.out.println("Использовано зелье здоровья, восстановлено: " + (value * 2) + " HP");
            }
            case STRENGTH_POTION -> {
                player.setAttackDamage(player.getAttackDamage() + value);
                System.out.println("Использовано зелье силы, урон увеличен на: " + value);
            }
            default -> {
                player.heal(value);
                System.out.println("Использовано зелье " + name + ", эффект: " + value);
            }
        }
    }

    public void reduceDurability(int amount) {
        if (durability > 0) {
            durability -= amount;
            if (durability <= 0) {
                durability = 0;
                System.out.println(name + " сломалось!");
            }
        }
    }

    public void repair() {
        if (maxDurability > 0) {
            durability = maxDurability;
            System.out.println(name + " отремонтировано!");
        }
    }

    public boolean isBroken() {
        return durability == 0;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public float getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDurability() {
        return durability;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public float getDurabilityPercent() {
        return maxDurability > 0 ? (float)durability / maxDurability * 100 : 100;
    }

    @Override
    public String toString() {
        String info = name + " [" + type.getDisplayName() + "]";
        if (quantity > 1) {
            info += " x" + quantity;
        }
        if (type.isEquippable() && durability > 0) {
            info += " (прочность: " + durability + "/" + maxDurability + ")";
        } else if (type.isConsumable()) {
            info += " (эффект: " + value + ")";
        }
        return info;
    }
}