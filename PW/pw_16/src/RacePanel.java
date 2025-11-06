import javax.swing.*;
import java.awt.*;

class RacePanel extends JPanel {
    private final int SQUARE_SIZE = 40;
    private final int FINISH_LINE;

    private final int[] squareX = { 50, 50, 50 };
    private final int[] squareY = { 100, 170, 240 };
    private final Color[] colors = { Color.decode("#404040"),
            Color.decode("#A3A3A3"),
            Color.decode("#737373") };
    private final String[] names = { "Gray", "Gray?", "Gray..?" };
    private final int[] speeds = { 2, 3, 18 };

    private boolean raceStarted = false;
    private int winner = -1;
    private Timer timer;

    private final int delay = 30;

    public RacePanel() {
        FINISH_LINE = 500;
        setupTimer();
        setBackground(Color.WHITE);
    }

    private void setupTimer() {
        timer = new Timer(delay, e -> {
            if (raceStarted) {
                for (int i = 0; i < names.length; i++) {
                    squareX[i] += speeds[i];

                    if (squareX[i] + SQUARE_SIZE >= FINISH_LINE && winner == -1) {
                        winner = i;
                        raceStarted = false;
                        timer.stop();
                        break;
                    }
                }
                repaint();
            }
        });
    }

    public void startRace() {
        if (!raceStarted) {
            raceStarted = true;
            winner = -1;
            for (int i = 0; i < names.length; i++) {
                squareX[i] = 50;
            }
            timer.start();
        }
    }

    public void resetRace() {
        raceStarted = false;
        winner = -1;
        for (int i = 0; i < names.length; i++) {
            squareX[i] = 50;
        }
        timer.stop();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTracks(g);
        drawFinishLine(g);
        drawSquares(g);
        drawRaceInfo(g);
    }

    private void drawTracks(Graphics g) {
        final String BACKGROUND_COLOR = "#E5E5E5";

        for (int i = 0; i < names.length; i++) {
            g.setColor(Color.decode(BACKGROUND_COLOR));
            g.fillRect(0, squareY[i] - 10, getWidth(), 60);

            g.setColor(Color.WHITE);
            for (int x = 0; x < getWidth(); x += 20) {
                g.fillRect(x, squareY[i] + 20, 10, 3);
            }
        }
    }

    private void drawFinishLine(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(FINISH_LINE, 80, FINISH_LINE, 280);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("ФИНИШ", FINISH_LINE - 40, 70);
    }

    private void drawSquares(Graphics g) {
        for (int i = 0; i < names.length; i++) {
            g.setColor(colors[i]);
            g.fillRect(squareX[i], squareY[i], SQUARE_SIZE, SQUARE_SIZE);

            g.setColor(Color.BLACK);
            g.drawRect(squareX[i], squareY[i], SQUARE_SIZE, SQUARE_SIZE);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(String.valueOf(i + 1), squareX[i] + 15, squareY[i] + 25);
        }
    }

    private void drawRaceInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 12));

        for (int i = 0; i < 3; i++) {
            String info = String.format("%s: speed %d", names[i], speeds[i]);
            g.drawString(info, 10, squareY[i] - 15);
        }

        String status = raceStarted ? "Racing!" :
                "Press START to.. start?";
        g.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g.getFontMetrics();

        int textWidth = fm.stringWidth(status);
        int centerX = (getWidth() - textWidth) / 2;

        g.drawString(status, centerX, 30);
    }

    public boolean isRaceFinished() {
        return winner != -1;
    }

    public int getWinner() {
        return winner;
    }

    public String getWinnerName() {
        return winner != -1 ? names[winner] : "No winner";
    }
}