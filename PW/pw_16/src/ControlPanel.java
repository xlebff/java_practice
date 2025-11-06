import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ControlPanel extends JPanel {
    private final RacePanel racePanel;
    private final JButton startButton;
    private final JButton resetButton;
    private final JLabel winnerLabel;


    public ControlPanel(RacePanel racePanel) {
        final String BUTTONS_COLOR = "#F0F0F0";
        final int CHECK_DELAY = 50;

        this.racePanel = racePanel;
        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);

        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        winnerLabel = new JLabel("Waiting for winner..");

        startButton.setBackground(Color.decode(BUTTONS_COLOR));
        resetButton.setBackground(Color.decode(BUTTONS_COLOR));
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 14));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                racePanel.startRace();
                startButton.setEnabled(false);

                Timer checkTimer = new Timer(CHECK_DELAY, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (racePanel.isRaceFinished()) {
                            winnerLabel.setText("Winner: " + racePanel.getWinnerName());
                            startButton.setEnabled(true);
                            ((Timer)e.getSource()).stop();
                        }
                    }
                });
                checkTimer.start();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                racePanel.resetRace();
                startButton.setEnabled(true);
                winnerLabel.setText("Waiting..");
            }
        });

        add(startButton);
        add(resetButton);
        add(winnerLabel);
    }
}