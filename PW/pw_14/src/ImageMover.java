import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageMover {
    private JFrame frame;
    private JLabel imageLabel;
    private JButton centerButton;
    private Point originalPosition;

    public ImageMover() {
        frame = new JFrame("Moving the image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        initializeComponents();
        setupEventHandlers();
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel imagePanel = new JPanel(null);
        imagePanel.setBackground(Color.decode("#171717"));
        imagePanel.setPreferredSize(new Dimension(600, 300));

        imageLabel = new JLabel(new ImageIcon(
                new ImageIcon("src/img.png").getImage()
                        .getScaledInstance(120, 120, Image.SCALE_SMOOTH)
        ));
        imageLabel.setSize(80, 80);

        originalPosition = new Point(260, 110);
        imageLabel.setLocation(originalPosition);

        imagePanel.add(imageLabel);

        centerButton = new JButton("To the center");
        centerButton.setBackground(Color.decode("#FAFAFA"));

        mainPanel.add(imagePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#262626"));
        buttonPanel.add(centerButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    private void setupEventHandlers() {
        JPanel imagePanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(0);

        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() - imageLabel.getWidth() / 2;
                int y = e.getY() - imageLabel.getHeight() / 2;

                x = Math.max(0, Math.min(x, imagePanel.getWidth() - imageLabel.getWidth()));
                y = Math.max(0, Math.min(y, imagePanel.getHeight() - imageLabel.getHeight()));

                imageLabel.setLocation(x, y);
                System.out.println("New position: (" + x + ", " + y + ")");
            }
        });

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                imageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                imageLabel.setCursor(Cursor.getDefaultCursor());
            }
        });

        centerButton.addActionListener(e -> {
            imageLabel.setLocation(originalPosition);
            System.out.println("At center");
        });

        centerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                centerButton.setBackground(new Color(200, 220, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                centerButton.setBackground(null);
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageMover().show();
        });
    }
}