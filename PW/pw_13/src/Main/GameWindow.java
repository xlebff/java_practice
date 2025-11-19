// GameWindow.java - исправленная версия
package Main;

import Behavioral.*;
import Creational.*;
import Structural.GameFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GameWindow extends JFrame {
    private Player player;
    private GameFacade gameFacade;

    // Основные панели
    private JPanel mainPanel;
    private JTextArea gameLog;
    private JTextArea playerStatus;
    private JPanel locationPanel;
    private JPanel actionPanel;
    private JPanel inventoryPanel;

    // Текущий NPC для боя
    private NPC currentEnemy;

    public GameWindow() {
        initializeGame();
        setupUI();
        setupEventHandlers();
    }

    private void initializeGame() {
        player = new Player("Герой");
        gameFacade = player.getGameFacade();
        currentEnemy = null;
    }

    private void setupUI() {
        setTitle("RPG Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        // Панель статуса игрока
        setupPlayerStatusPanel();

        // Панель локации
        setupLocationPanel();

        // Панель действий
        setupActionPanel();

        // Панель инвентаря
        setupInventoryPanel();

        // Лог игры
        setupGameLog();

        add(mainPanel);
        updateGameState();
    }

    private void setupPlayerStatusPanel() {
        playerStatus = new JTextArea(5, 30);
        playerStatus.setEditable(false);
        playerStatus.setBackground(new Color(240, 240, 240));
        playerStatus.setBorder(BorderFactory.createTitledBorder("Статус игрока"));

        JScrollPane statusScroll = new JScrollPane(playerStatus);
        statusScroll.setPreferredSize(new Dimension(300, 120));
        mainPanel.add(statusScroll, BorderLayout.NORTH);
    }

    private void setupLocationPanel() {
        locationPanel = new JPanel();
        locationPanel.setBorder(BorderFactory.createTitledBorder("Текущая локация"));
        locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.Y_AXIS));
        locationPanel.setPreferredSize(new Dimension(300, 200));

        mainPanel.add(locationPanel, BorderLayout.WEST);
    }

    private void setupActionPanel() {
        actionPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Действия"));
        actionPanel.setPreferredSize(new Dimension(400, 300));

        mainPanel.add(actionPanel, BorderLayout.CENTER);
    }

    private void setupInventoryPanel() {
        inventoryPanel = new JPanel();
        inventoryPanel.setBorder(BorderFactory.createTitledBorder("Инвентарь"));
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
        inventoryPanel.setPreferredSize(new Dimension(300, 400));

        JScrollPane inventoryScroll = new JScrollPane(inventoryPanel);
        mainPanel.add(inventoryScroll, BorderLayout.EAST);
    }

    private void setupGameLog() {
        gameLog = new JTextArea(10, 50);
        gameLog.setEditable(false);
        gameLog.setBackground(Color.BLACK);
        gameLog.setForeground(Color.WHITE);
        gameLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane logScroll = new JScrollPane(gameLog);
        logScroll.setBorder(BorderFactory.createTitledBorder("Журнал игры"));
        mainPanel.add(logScroll, BorderLayout.SOUTH);
    }

    private void setupEventHandlers() {
        // Обработчики будут добавляться динамически в updateActionButtons()
    }

    private void updateGameState() {
        updatePlayerStatus();
        updateLocationInfo();
        updateActionButtons();
        updateInventoryDisplay();
        updateGameLog();
    }

    private void updatePlayerStatus() {
        StringBuilder status = new StringBuilder();
        status.append("Имя: ").append(player.getName()).append("\n");
        status.append("Здоровье: ").append(String.format("%.1f", player.getHealth()))
                .append("/").append(String.format("%.1f", player.getMaxHealth())).append("\n");
        status.append("Состояние: ").append(player.getCurrentStateName()).append("\n");
        status.append("Урон: ").append(String.format("%.1f", player.calculateDamage())).append("\n");

        playerStatus.setText(status.toString());

        // Изменение цвета в зависимости от здоровья
        if (player.getHealth() < player.getMaxHealth() * 0.3) {
            playerStatus.setBackground(new Color(255, 200, 200));
        } else if (player.getHealth() < player.getMaxHealth() * 0.7) {
            playerStatus.setBackground(new Color(255, 255, 200));
        } else {
            playerStatus.setBackground(new Color(200, 255, 200));
        }
    }

    private void updateLocationInfo() {
        locationPanel.removeAll();

        JLabel locationLabel = new JLabel(player.getGameMap().getCurrentLocation());
        locationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        locationPanel.add(locationLabel);

        // Кнопки перемещения
        JPanel movePanel = new JPanel(new GridLayout(0, 1, 5, 5));

        // Получаем доступные локации из GameMap
        List<String> availableLocations = getAvailableLocations();
        for (String location : availableLocations) {
            JButton moveButton = new JButton("→ " + location);
            moveButton.addActionListener(e -> {
                if (player.getGameMap().moveTo(location)) {
                    onLocationChanged();
                }
            });
            movePanel.add(moveButton);
        }

        locationPanel.add(movePanel);
        locationPanel.revalidate();
        locationPanel.repaint();
    }

    // Новый метод для получения доступных локаций
    private List<String> getAvailableLocations() {
        // Временная реализация - возвращаем список локаций
        // В реальной игре это должно браться из GameMap
        return java.util.Arrays.asList("Лес", "Горный перевал", "Заброшенный замок", "Пещера дракона");
    }

    private void updateActionButtons() {
        actionPanel.removeAll();

        String state = player.getCurrentStateName();

        switch (state) {
            case "ПОИСК":
                addExplorationButtons();
                break;
            case "БОЙ":
                addCombatButtons();
                break;
            case "ОТДЫХ":
                addRestingButtons();
                break;
            case "СМЕРТЬ":
                addDeathButtons();
                break;
        }

        actionPanel.revalidate();
        actionPanel.repaint();
    }

    private void addExplorationButtons() {
        JButton attackBtn = new JButton("⚔️ Атаковать");
        attackBtn.addActionListener(e -> {
            spawnRandomEnemy();
            player.setState(new CombatState());
            updateGameState();
        });

        JButton restBtn = new JButton("💤 Отдыхать");
        restBtn.addActionListener(e -> {
            player.setState(new RestingState());
            updateGameState();
        });

        JButton mapBtn = new JButton("🗺️ Показать карту");
        mapBtn.addActionListener(e -> {
            gameFacade.showMap();
            updateGameLog();
        });

        JButton inventoryBtn = new JButton("🎒 Инвентарь");
        inventoryBtn.addActionListener(e -> {
            gameFacade.showInventory();
            updateGameLog();
        });

        actionPanel.add(attackBtn);
        actionPanel.add(restBtn);
        actionPanel.add(mapBtn);
        actionPanel.add(inventoryBtn);
    }

    private void addCombatButtons() {
        if (currentEnemy == null) return;

        JButton attackBtn = new JButton("⚔️ Атаковать");
        attackBtn.addActionListener(e -> {
            player.handleInput("attack");
            if (currentEnemy.isAlive()) {
                // Враг контратакует
                if (currentEnemy instanceof Mob) {
                    ((Mob) currentEnemy).attack(player);
                } else if (currentEnemy instanceof Boss) {
                    ((Boss) currentEnemy).specialAttack(player);
                }
            }
            updateGameState();
        });

        JButton fleeBtn = new JButton("🏃 Бежать");
        fleeBtn.addActionListener(e -> {
            player.handleInput("flee");
            if (!(player.getCurrentState() instanceof CombatState)) {
                currentEnemy = null;
            }
            updateGameState();
        });

        JButton itemBtn = new JButton("🧪 Использовать предмет");
        itemBtn.addActionListener(e -> {
            showCombatItemSelection();
        });

        JButton enemyInfoBtn = new JButton("👁️ Информация о враге");
        enemyInfoBtn.addActionListener(e -> {
            showEnemyInfo();
        });

        actionPanel.add(attackBtn);
        actionPanel.add(fleeBtn);
        actionPanel.add(itemBtn);
        actionPanel.add(enemyInfoBtn);

        // Панель информации о враге
        JPanel enemyPanel = new JPanel(new FlowLayout());
        enemyPanel.add(new JLabel("Противник: " + currentEnemy.getName()));
        enemyPanel.add(new JLabel("HP: " + String.format("%.1f", currentEnemy.getHealth())));
        actionPanel.add(enemyPanel);
    }

    private void addRestingButtons() {
        JButton stopRestBtn = new JButton("⏹️ Прекратить отдых");
        stopRestBtn.addActionListener(e -> {
            player.setState(new ExplorationState());
            updateGameState();
        });

        JButton eatBtn = new JButton("🍎 Поесть");
        eatBtn.addActionListener(e -> {
            gameFacade.useFood();
            updateGameState();
        });

        JButton inventoryBtn = new JButton("🎒 Инвентарь");
        inventoryBtn.addActionListener(e -> {
            gameFacade.showInventory();
            updateGameLog();
        });

        actionPanel.add(stopRestBtn);
        actionPanel.add(eatBtn);
        actionPanel.add(inventoryBtn);
    }

    private void addDeathButtons() {
        JButton respawnBtn = new JButton("🔁 Возродиться");
        respawnBtn.addActionListener(e -> {
            player.handleInput("respawn");
            updateGameState();
        });

        JButton quitBtn = new JButton("🚪 Выйти");
        quitBtn.addActionListener(e -> {
            System.exit(0);
        });

        actionPanel.add(respawnBtn);
        actionPanel.add(quitBtn);
    }

    private void updateInventoryDisplay() {
        inventoryPanel.removeAll();

        // Экипированное оружие
        Item weapon = player.getInventory().getEquippedWeapon();
        JLabel weaponLabel = new JLabel("Оружие: " + weapon.getName() + " (" + weapon.getValue() + " урона)");
        inventoryPanel.add(weaponLabel);

        inventoryPanel.add(new JSeparator());

        // Список предметов
        Map<String, Item> items = player.getInventory().getItems();
        for (Item item : items.values()) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel itemLabel = new JLabel(item.toString());
            itemPanel.add(itemLabel);

            if (item.getType().isConsumable()) {
                JButton useBtn = new JButton("Исп.");
                useBtn.addActionListener(e -> {
                    gameFacade.useItem(item.getName());
                    updateGameState();
                });
                itemPanel.add(useBtn);
            }

            if (item.getType().isWeapon() && item.getType() != ItemType.FISTS) {
                JButton equipBtn = new JButton("Экип.");
                equipBtn.addActionListener(e -> {
                    gameFacade.equipWeapon(item.getName());
                    updateGameState();
                });
                itemPanel.add(equipBtn);
            }

            inventoryPanel.add(itemPanel);
        }

        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    private void updateGameLog() {
        // Здесь можно добавить логирование событий
        // Пока просто обновляем информацию о состоянии
        gameLog.setText(getGameStateSummary());
    }

    private String getGameStateSummary() {
        StringBuilder log = new StringBuilder();
        log.append("=== ИГРОВАЯ ИНФОРМАЦИЯ ===\n");
        log.append("Локация: ").append(player.getGameMap().getCurrentLocation()).append("\n");
        log.append("Состояние: ").append(player.getCurrentStateName()).append("\n");

        if (currentEnemy != null && currentEnemy.isAlive()) {
            log.append("Противник: ").append(currentEnemy.getName())
                    .append(" (HP: ").append(String.format("%.1f", currentEnemy.getHealth())).append(")\n");
        }

        log.append("Открытые локации: ").append(player.getGameMap().getDiscoveredLocations().size()).append("\n");
        log.append("Предметов в инвентаре: ").append(player.getInventory().getItems().size()).append("\n");

        return log.toString();
    }

    private void spawnRandomEnemy() {
        double rand = Math.random();
        if (rand < 0.7) {
            // Обычный моб
            currentEnemy = new Mob("Гоблин", 50, 1.2f, 8, 0.1f);
        } else if (rand < 0.9) {
            // Сильный моб
            currentEnemy = new Mob("Орк", 80, 1.5f, 12, 0.2f);
        } else {
            // Босс
            currentEnemy = new Boss("Дракон", 200, 2.0f, 20, 0.4f);
        }

        if (player.getCurrentState() instanceof CombatState) {
            ((CombatState) player.getCurrentState()).setTarget(currentEnemy);
        }

        addToGameLog("Появился враг: " + currentEnemy.getName());
    }

    private void showCombatItemSelection() {
        JDialog itemDialog = new JDialog(this, "Выбор предмета", true);
        itemDialog.setLayout(new BorderLayout());

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

        Map<String, Item> items = player.getInventory().getItems();
        for (Item item : items.values()) {
            if (item.getType().isConsumable() || item.getType().isWeapon()) {
                JButton itemBtn = new JButton(item.toString());
                itemBtn.addActionListener(e -> {
                    gameFacade.useItem(item.getName());
                    itemDialog.dispose();
                    updateGameState();
                });
                itemPanel.add(itemBtn);
            }
        }

        JScrollPane scrollPane = new JScrollPane(itemPanel);
        itemDialog.add(scrollPane, BorderLayout.CENTER);

        JButton cancelBtn = new JButton("Отмена");
        cancelBtn.addActionListener(e -> itemDialog.dispose());
        itemDialog.add(cancelBtn, BorderLayout.SOUTH);

        itemDialog.setSize(300, 400);
        itemDialog.setLocationRelativeTo(this);
        itemDialog.setVisible(true);
    }

    private void showEnemyInfo() {
        if (currentEnemy != null) {
            StringBuilder info = new StringBuilder();
            info.append("=== ИНФОРМАЦИЯ О ПРОТИВНИКЕ ===\n");
            info.append("Имя: ").append(currentEnemy.getName()).append("\n");
            info.append("Здоровье: ").append(currentEnemy.getHealth()).append("/").append(currentEnemy.getMaxHealth()).append("\n");
            info.append("Тип: ").append(currentEnemy.getType()).append("\n");

            if (currentEnemy instanceof Mob) {
                Mob mob = (Mob) currentEnemy;
                info.append("Сила: ").append(mob.getStrength()).append("\n");
                info.append("Защита: ").append(mob.getDefence()).append("\n");
            } else if (currentEnemy instanceof Boss) {
                Boss boss = (Boss) currentEnemy;
                info.append("Фаза: ").append(boss.getPhase()).append("\n");
                info.append("В ярости: ").append(boss.isEnraged() ? "Да" : "Нет").append("\n");
            }

            JOptionPane.showMessageDialog(this, info.toString(), "Информация о враге", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void onLocationChanged() {
        // При смене локации может происходить разные события
        double eventChance = Math.random();
        if (eventChance < 0.3) {
            // Найден предмет
            Item[] possibleItems = {
                    new Item(ItemType.HEALTH_POTION, 25.0f),
                    new Item("Яблоко", ItemType.APPLE, 10.0f),
                    new Item("Золотая монета", ItemType.GOLD, 1.0f, 5)
            };

            Item foundItem = possibleItems[(int)(Math.random() * possibleItems.length)];
            if (player.getInventory().addItem(foundItem)) {
                addToGameLog("Вы нашли: " + foundItem.getName());
            }
        }

        updateGameState();
    }

    private void addToGameLog(String message) {
        String currentText = gameLog.getText();
        gameLog.setText(currentText + "\n> " + message);
        // Автопрокрутка вниз
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
    }
}