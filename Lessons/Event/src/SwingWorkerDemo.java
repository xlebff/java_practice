import javax.swing.*;

public class SwingWorkerDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SwingWorker Пример");
            JButton button = new JButton("Запустить долгую задачу");
            JLabel label = new JLabel("Ожидание...");

            button.addActionListener(e -> {
                button.setEnabled(false);
                label.setText("Выполняется...");

                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        Thread.sleep(3000); // имитация долгой работы
                        return null;
                    }

                    @Override
                    protected void done() {
                        label.setText("Готово!");
                        button.setEnabled(true);
                    }
                }.execute();
            });

            frame.add(button, "North");
            frame.add(label, "South");
            frame.setSize(300, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
