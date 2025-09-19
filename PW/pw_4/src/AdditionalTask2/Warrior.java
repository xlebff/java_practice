package AdditionalTask2;

import java.util.Arrays;

public class Warrior implements Character, Character.Inventory {
    private String name;
    private String[] items = { "Sword", "Shield" };

    public Warrior(String name) { this.name = name; }

    @Override
    public void act() {
        System.out.println(this.name + " is attacking!");
    }

    @Override
    public void listItems() {
        System.out.println(this.name + "`s inventory:");
        for (String i : items) {
            System.out.println(i);
        }
    }
}
