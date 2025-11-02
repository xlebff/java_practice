import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickCounterDemo {
    private int count = 0;

    public ClickCounterDemo() {
        JFrame frame = new JFrame("Click Counter");
        JButton button = new JButton("Нажми меня");
        JLabel label = new JLabel("Кликов: 0");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                label.setText("Кликов: " + count);
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.add(label);
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClickCounterDemo::new);
    }
}
