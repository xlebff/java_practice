import javax.swing.*;
import java.awt.event.ActionListener;

public class MultiListenerDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Много слушателей");
            JButton button = new JButton("Кликни");

            // 1 слушатель — считает клики
            final int[] clicks = {0};
            button.addActionListener(e -> button.setText("Кликов: " + ++clicks[0]));

            // 2 слушатель — выводит в консоль
            button.addActionListener(e -> System.out.println("Clicked!"));

            frame.add(button);
            frame.setSize(200, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
