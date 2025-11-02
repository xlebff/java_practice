package Behavioral;

public class DeadState implements PlayerState {
    private int respawnTimer;
    private final int RESPAWN_TIME = 5;

    @Override
    public void enterState(Player player) {
        System.out.println("Игрок " + player.getName() + " погиб!");
        respawnTimer = RESPAWN_TIME;
        player.setMovementSpeed(0f);

        penalizePlayer(player);
    }

    @Override
    public void handleInput(Player player, String input) {
        switch (input.toLowerCase()) {
            case "respawn" -> {
                if (respawnTimer <= 0) {
                    respawnPlayer(player);
                } else {
                    System.out.println("До возрождения осталось: " + respawnTimer + " тиков");
                }
            }
            case "quit" -> System.out.println("Выход из игры...");
            default -> System.out.println("Игрок мертв. Доступные команды: respawn, quit");
        }
    }

    @Override
    public void update(Player player) {
        if (respawnTimer > 0) {
            respawnTimer--;
            if (respawnTimer == 0) {
                System.out.println("Можно возродиться! Введите 'respawn'");
            }
        }
    }

    @Override
    public void exitState(Player player) {
        System.out.println("Игрок возрождается!");
    }

    private void penalizePlayer(Player player) {
        Inventory inventory = player.getInventory();

        int lostItems = 0;
        var itemsToRemove = inventory.getItems().entrySet().stream()
                .filter(entry -> !entry.getKey().equals(inventory.getEquippedWeapon().getName()))
                .filter(entry -> Math.random() > 0.5)
                .map(entry -> entry.getKey())
                .toList();

        for (String itemName : itemsToRemove) {
            inventory.removeItem(itemName);
            lostItems++;
        }

        if (lostItems > 0) {
            System.out.println("Потеряно предметов при смерти: " + lostItems);
        }

        System.out.println("Вы погибли. Возрождение через " + RESPAWN_TIME + " тиков");
    }

    private void respawnPlayer(Player player) {
        player.setHealth(player.getMaxHealth() * 0.5f);
        player.setState(new ExplorationState());
        player.getGameMap().moveTo("Начальная деревня");
    }

    @Override
    public String getStateName() {
        return "СМЕРТЬ";
    }
}