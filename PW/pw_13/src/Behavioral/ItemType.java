package Behavioral;

public enum ItemType {
    FISTS("Кулаки", "WEAPON"),
    SWORD("Меч", "WEAPON"),
    AXE("Топор", "WEAPON"),
    BOW("Лук", "WEAPON"),
    STAFF("Посох", "WEAPON"),
    DAGGER("Кинжал", "WEAPON"),

    HELMET("Шлем", "ARMOR"),
    CHESTPLATE("Нагрудник", "ARMOR"),
    LEGGINGS("Поножи", "ARMOR"),
    BOOTS("Ботинки", "ARMOR"),
    SHIELD("Щит", "ARMOR"),

    BREAD("Хлеб", "FOOD"),
    APPLE("Яблоко", "FOOD"),
    MEAT("Мясо", "FOOD"),
    HEALTH_POTION("Зелье здоровья", "POTION"),
    MANA_POTION("Зелье маны", "POTION"),
    STRENGTH_POTION("Зелье силы", "POTION"),

    GOLD("Золото", "CURRENCY"),
    KEY("Ключ", "KEY"),
    MATERIAL("Материал", "RESOURCE"),
    QUEST_ITEM("Предмет задания", "QUEST"),
    SCROLL("Свиток", "MAGIC");

    private final String displayName;
    private final String category;

    ItemType(String displayName, String category) {
        this.displayName = displayName;
        this.category = category;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCategory() {
        return category;
    }

    public boolean isWeapon() {
        return "WEAPON".equals(category);
    }

    public boolean isArmor() {
        return "ARMOR".equals(category);
    }

    public boolean isFood() {
        return "FOOD".equals(category);
    }

    public boolean isPotion() {
        return "POTION".equals(category);
    }

    public boolean isConsumable() {
        return isFood() || isPotion();
    }

    public boolean isEquippable() {
        return isWeapon() || isArmor();
    }

    public boolean isQuestItem() {
        return "QUEST".equals(category);
    }

    public static ItemType fromString(String name) {
        for (ItemType type : values()) {
            if (type.name().equalsIgnoreCase(name) ||
                    type.getDisplayName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown item type: " + name);
    }

    public static ItemType[] getByCategory(String category) {
        return java.util.Arrays.stream(values())
                .filter(type -> type.getCategory().equals(category))
                .toArray(ItemType[]::new);
    }

    @Override
    public String toString() {
        return displayName;
    }
}