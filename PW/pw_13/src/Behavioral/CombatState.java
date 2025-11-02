package Behavioral;

import Creational.NPC;

public class CombatState implements PlayerState {
    private NPC currentTarget;

    @Override
    public void enterState(Player player) {
        System.out.println("Игрок вступает в бой!");
        player.setMovementSpeed(2.0f);
    }

    @Override
    public void handleInput(Player player, String input) {
        switch (input.toLowerCase()) {
            case "attack" -> {
                if (currentTarget != null && currentTarget.isAlive()) {
                    float damage = player.calculateDamage();
                    System.out.println("Игрок атакует " + currentTarget.getName() + " с уроном: " + damage);
                    currentTarget.getHit(damage);
                } else {
                    System.out.println("Нет цели для атаки");
                }
            }
            case "flee" -> {
                System.out.println("Игрок пытается сбежать...");
                if (Math.random() > 0.3) {
                    player.setState(new ExplorationState());
                } else {
                    System.out.println("Не удалось сбежать!");
                }
            }
            case "inventory" -> player.getGameFacade().useCombatItem();
            case "rest" -> System.out.println("Нельзя отдыхать во время боя!");
            default -> System.out.println("Действие в бою: " + input);
        }
    }

    @Override
    public void update(Player player) {
        if (currentTarget != null && !currentTarget.isAlive()) {
            System.out.println("Противник побежден!");
            player.setState(new ExplorationState());
        }

        if (player.getHealth() <= 0 && !(player.getCurrentState() instanceof DeadState)) {
            player.setState(new DeadState());
        }
    }

    @Override
    public void exitState(Player player) {
        System.out.println("Бой завершен");
        currentTarget = null;
    }

    public void setTarget(NPC target) {
        this.currentTarget = target;
        System.out.println("Цель установлена: " + target.getName());
    }

    @Override
    public String getStateName() {
        return "БОЙ";
    }
}