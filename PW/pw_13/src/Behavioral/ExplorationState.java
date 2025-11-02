package Behavioral;

public class ExplorationState implements PlayerState {
    @Override
    public void enterState(Player player) {
        System.out.println("Игрок начинает исследование мира");
        player.setMovementSpeed(5.0f);
    }

    @Override
    public void handleInput(Player player, String input) {
        switch (input.toLowerCase()) {
            case "attack" -> player.setState(new CombatState());
            case "rest" -> player.setState(new RestingState());
            case "map" -> player.getGameFacade().showMap();
            case "inventory" -> player.getGameFacade().showInventory();
            default -> System.out.println("Игрок исследует: " + input);
        }
    }

    @Override
    public void update(Player player) {
        if (player.getHealth() < player.getMaxHealth() * 0.3f) {
            System.out.println("Здоровье низкое, рекомендуется отдохнуть");
        }

        if (player.getHealth() <= 0 && !(player.getCurrentState() instanceof DeadState)) {
            player.setState(new DeadState());
        }
    }

    @Override
    public void exitState(Player player) {
        System.out.println("Завершено исследование");
    }

    @Override
    public String getStateName() {
        return "ПОИСК";
    }
}