import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        initializeUI();
    }

    private void initializeUI() {
        final String BACKGROUND_COLOR = "#E5E5E5";

        setTitle("Race");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(Color.decode(BACKGROUND_COLOR));

        add(mainPanel);

        RacePanel racePanel = new RacePanel();
        ControlPanel controlPanel = new ControlPanel(racePanel);

        mainPanel.add(racePanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        mainPanel.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}