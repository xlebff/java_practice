import Creational.*;
import Behavioral.*;
import Structural.*;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Ririsa");
        GameFacade game = player.getGameFacade();

        System.out.println("\n\nИССЛЕДОВАНИЕ И ИНВЕНТАРЬ");
        game.showPlayerStatus();
        game.showMap();
        game.showInventory();

        game.moveToLocation("Лес");
        game.showMap();

        System.out.println("\n\nСИСТЕМА СОСТОЯНИЙ");
        player.handleInput("inventory");
        player.handleInput("rest");

        player.setState(new RestingState());
        System.out.println("Текущее состояние: " + player.getCurrentStateName());

        for (int i = 0; i < 5; i++) {
            player.update();
        }

        System.out.println("\n\nБОЕВАЯ СИСТЕМА");

        Mob wolf = new Mob("Лютый волк", 50f, 1.2f, 8f, 0.1f);
        Villager blacksmith = new Villager("Кузнец Генри", 45, 80f, "кузнец",
                "Добро пожаловать в мою кузницу!");

        System.out.println("\nВстреча с мирным NPC");
        blacksmith.talk();
        blacksmith.offerQuest();

        System.out.println("\nВстреча с врагом");
        CombatState combatState = new CombatState();
        player.setState(combatState);
        combatState.setTarget(wolf);

        System.out.println("\nНачало боя");
        player.handleInput("attack");
        player.handleInput("attack");
        player.handleInput("inventory");
        player.takeDamage(15f);
        game.showPlayerStatus();
        player.handleInput("attack");
        player.handleInput("attack");

        System.out.println("\n\nСИСТЕМА ОРУЖИЯ");
        game.showWeaponInfo();

        game.equipWeapon("Простой меч");
        game.showWeaponInfo();

        game.showInventory();

        System.out.println("\n\nБОСС БИТВА");
        Boss dragon = new Boss("Дракон Смауг", 200f, 1.5f, 25f, 0.3f);

        CombatState bossFight = new CombatState();
        player.setState(bossFight);
        bossFight.setTarget(dragon);

        System.out.println("\nБой с боссом");
        player.handleInput("attack");
        player.handleInput("attack");
        dragon.specialAttack(player);
        player.handleInput("attack");
        player.handleInput("attack");
        player.handleInput("attack");
        player.handleInput("attack");
        game.useCombatItem();
        game.showPlayerStatus();

        System.out.println("\n\nСИСТЕМА СМЕРТИ");

        System.out.println("Игрок получает смертельный урон");
        player.takeDamage(100f);
        game.showDeathScreen();
        for (int i = 0; i < 6; i++) {
            player.update();
        }

        player.handleInput("respawn");
        game.showPlayerStatus();
        game.showInventory();

        System.out.println("\n\nПАТТЕРН PROTOTYPE");

        Villager originalVillager = new Villager("Оригинальный житель", 30, 100f, "фермер");
        System.out.println("Оригинальный NPC: " + originalVillager.getName());

        Villager clonedVillager = (Villager) originalVillager.clone();
        clonedVillager.setName("Клонированный житель");
        System.out.println("Клонированный NPC: " + clonedVillager.getName());

        Mob originalMob = new Mob("Оригинальный гоблин", 40f, 1.0f, 10f, 0.2f);
        Mob clonedMob = (Mob) originalMob.clone();
        clonedMob.setName("Клонированный гоблин");
        System.out.println("Клонированный моб: " + clonedMob.getName());

        System.out.println("\n\nМАССОВКА");
        Extra guard = new Extra("Стражник", 60f, "охранник");
        Extra merchant = new Extra("Торговец", 40f, "продавец");

        guard.performAction();
        merchant.performAction();
        guard.panic();
        merchant.rest();

        System.out.println("\n\nПАТТЕРН FACADE");
        game.showPlayerStatus();
        game.quickRest();
        game.useFood();
        game.moveToLocation("Горный перевал");
        game.showMap();

        System.out.println("\n\nСИСТЕМА ПРЕДМЕТОВ");

        player.getInventory().addItem(new Item("Стальной топор", ItemType.AXE, 18.0f));
        player.getInventory().addItem(new Item("Целебная трава", ItemType.HEALTH_POTION, 30.0f));
        player.getInventory().addItem(new Item("Золотой ключ", ItemType.KEY, 0f));
        player.getInventory().addItem(new Item("Жареное мясо", ItemType.MEAT, 20.0f));

        game.showInventory();

        game.equipWeapon("Стальной топор");
        game.useItem("Целебная трава");
        game.showWeaponInfo();

        System.out.println("\n\nДЕМОНСТРАЦИЯ ПОВЕДЕНИЯ NPC\n");

        Villager farmer = new Villager("Фермер Джон", 35, 80f, "фермер",
                "Урожай в этом году отличный!");
        Mob skeleton = new Mob("Скелет-воин", 45f, 1.1f, 12f, 0.15f);
        Boss lichKing = new Boss("Король-лич", 180f, 1.8f, 30f, 0.4f);
        Extra bard = new Extra("Бард Лютер", 50f, "музыкант");

        System.out.println("Мирные NPC");
        farmer.talk();
        farmer.offerQuest();

        System.out.println("\nВраждебные NPC");
        skeleton.displayStatus();
        System.out.println("Атакуем скелета...");
        skeleton.getHit(20f);
        skeleton.displayStatus();

        System.out.println("\nБосс");
        lichKing.displayStatus();
        System.out.println("Атакуем босса...");
        lichKing.getHit(40f);
        lichKing.getHit(40f);
        lichKing.displayStatus();

        System.out.println("\nМассовка");
        bard.performAction();
        bard.panic();
        bard.rest();

        System.out.println("\nПроверка взаимодействия");
        System.out.println("Фермер может говорить: " + farmer.isTalkable());
        System.out.println("Скелет может атаковать: " + skeleton.isAttackable());
        System.out.println("Бард может говорить: " + bard.isTalkable());
    }
}