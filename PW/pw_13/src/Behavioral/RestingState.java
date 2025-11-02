package Behavioral;

public class RestingState implements PlayerState {
    private int restTicks;

    @Override
    public void enterState(Player player) {
        System.out.println("Игрок начинает отдых");
        player.setMovementSpeed(0f);
        restTicks = 0;
    }

    @Override
    public void handleInput(Player player, String input) {
        switch (input.toLowerCase()) {
            case "stop" -> player.setState(new ExplorationState());
            case "eat" -> player.getGameFacade().useFood();
            case "inventory" -> player.getGameFacade().showInventory();
            default -> System.out.println("Игрок отдыхает...");
        }
    }

    @Override
    public void update(Player player) {
        if (player.getHealth() <= 0 && !(player.getCurrentState() instanceof DeadState)) {
            player.setState(new DeadState());
            return;
        }

        restTicks++;

        if (restTicks % 3 == 0) {
            float healAmount = player.getMaxHealth() * 0.1f;
            player.heal(healAmount);
            System.out.println("Восстановлено здоровья: +" + healAmount);
        }

        if (player.getHealth() >= player.getMaxHealth()) {
            System.out.println("Здоровье полностью восстановлено!");
            player.setState(new ExplorationState());
        }
    }

    @Override
    public void exitState(Player player) {
        System.out.println("Отдых завершен");
    }

    @Override
    public String getStateName() {
        return "ОТДЫХ";
    }
}