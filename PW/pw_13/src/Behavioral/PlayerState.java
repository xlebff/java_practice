package Behavioral;

public interface PlayerState {
    void enterState(Player player);
    void handleInput(Player player, String input);
    void update(Player player);
    void exitState(Player player);
    String getStateName();
}