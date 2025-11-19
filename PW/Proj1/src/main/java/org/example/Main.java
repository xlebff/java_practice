package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main extends JFrame {
    private JaveMP3Player player;
    private Timer positionTimer;

    private JLabel currentTimeLabel;
    private JLabel totalTimeLabel;
    private JSlider progressSlider;
    private JSlider volumeSlider;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton openButton;
    private JLabel trackNameLabel;
    private JLabel statusLabel;

    public Main() {
        initializeGUI();
        setupTimer();
    }

    private void initializeGUI() {
        setTitle("MP3 Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Создание компонентов
        createComponents();

        // Размещение компонентов
        setupLayout();

        // Настройка слушателей
        setupListeners();
    }

    private void createComponents() {
        // Метки времени
        currentTimeLabel = new JLabel("00:00");
        totalTimeLabel = new JLabel("00:00");

        // Слайдер прогресса
        progressSlider = new JSlider(0, 100, 0);
        progressSlider.setEnabled(false);

        // Слайдер громкости
        volumeSlider = new JSlider(0, 100, 80);
        volumeSlider.setPreferredSize(new Dimension(100, 40));

        // Кнопки управления
        playButton = new JButton("▶");
        pauseButton = new JButton("⏸");
        stopButton = new JButton("⏹");
        openButton = new JButton("📁");

        // Метки информации
        trackNameLabel = new JLabel("Выберите MP3 файл");
        trackNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel = new JLabel("Готов");
        statusLabel.setForeground(Color.GRAY);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель - информация о треке
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(trackNameLabel, BorderLayout.NORTH);
        infoPanel.add(statusLabel, BorderLayout.SOUTH);

        // Центральная панель - прогресс
        JPanel progressPanel = new JPanel(new BorderLayout());
        progressPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JPanel timePanel = new JPanel(new BorderLayout());
        timePanel.add(currentTimeLabel, BorderLayout.WEST);
        timePanel.add(totalTimeLabel, BorderLayout.EAST);

        progressPanel.add(progressSlider, BorderLayout.CENTER);
        progressPanel.add(timePanel, BorderLayout.SOUTH);

        // Нижняя панель - управление
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(openButton);
        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(stopButton);

        // Панель громкости
        JPanel volumePanel = new JPanel();
        volumePanel.add(new JLabel("🔊"));
        volumePanel.add(volumeSlider);

        controlPanel.add(volumePanel);

        // Добавление всех панелей
        add(infoPanel, BorderLayout.NORTH);
        add(progressPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // Начальное состояние кнопок
        updateButtonsState(false);
    }

    private void setupListeners() {
        // Кнопка открытия файла
        openButton.addActionListener(e -> openMP3File());

        // Кнопка воспроизведения
        playButton.addActionListener(e -> {
            if (player != null) {
                player.play();
                updateButtonsState(true);
                statusLabel.setText("Воспроизведение");
                statusLabel.setForeground(Color.BLUE);
            }
        });

        // Кнопка паузы
        pauseButton.addActionListener(e -> {
            if (player != null && player.isPlaying()) {
                player.pause();
                updateButtonsState(false);
                statusLabel.setText("Пауза");
                statusLabel.setForeground(Color.ORANGE);
            }
        });

        // Кнопка остановки
        stopButton.addActionListener(e -> {
            if (player != null) {
                player.stop();
                updateButtonsState(false);
                progressSlider.setValue(0);
                currentTimeLabel.setText("00:00");
                statusLabel.setText("Остановлено");
                statusLabel.setForeground(Color.RED);
            }
        });

        // Слайдер прогресса
        progressSlider.addChangeListener(e -> {
            if (!progressSlider.getValueIsAdjusting() && player != null) {
                long duration = player.getDuration();
                int value = progressSlider.getValue();
                long newPosition = (long) (duration * (value / 100.0));
                player.seek(newPosition);
            }
        });

        // Слайдер громкости
        volumeSlider.addChangeListener(e -> {
            if (player != null) {
                int volumeValue = volumeSlider.getValue();
                float volume = volumeValue / 100.0f;
                player.setVolume(volume);
            }
        });
    }

    private void setupTimer() {
        positionTimer = new Timer(100, e -> updatePosition());
        positionTimer.start();
    }

    private void updatePosition() {
        if (player != null && player.isPlaying()) {
            long position = player.getPosition();
            long duration = player.getDuration();

            // Обновление времени
            currentTimeLabel.setText(formatTime(position));
            totalTimeLabel.setText(formatTime(duration));

            // Обновление слайдера
            if (duration > 0) {
                int progress = (int) ((position * 100) / duration);
                progressSlider.setValue(progress);
            }

            // Проверка окончания трека
            if (position >= duration && duration > 0) {
                player.stop();
                updateButtonsState(false);
                progressSlider.setValue(0);
                statusLabel.setText("Трек завершен");
                statusLabel.setForeground(Color.GREEN);
            }
        }
    }

    private void openMP3File() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "MP3 Files", "mp3"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadMP3File(selectedFile);
        }
    }

    private void loadMP3File(File file) {
        try {
            // Закрываем предыдущий плеер
            if (player != null) {
                player.close();
            }

            // Создаем новый плеер
            player = new JaveMP3Player(file);

            // Обновляем GUI
            trackNameLabel.setText(file.getName());
            totalTimeLabel.setText(formatTime(player.getDuration()));
            currentTimeLabel.setText("00:00");
            progressSlider.setValue(0);
            progressSlider.setEnabled(true);

            // Устанавливаем громкость
            player.setVolume(volumeSlider.getValue() / 100.0f);

            updateButtonsState(false);
            statusLabel.setText("Файл загружен");
            statusLabel.setForeground(Color.GREEN);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка загрузки файла: " + ex.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Ошибка загрузки");
            statusLabel.setForeground(Color.RED);
        }
    }

    private void updateButtonsState(boolean playing) {
        playButton.setEnabled(!playing && player != null);
        pauseButton.setEnabled(playing && player != null);
        stopButton.setEnabled(player != null);
    }

    private String formatTime(long microseconds) {
        long seconds = microseconds / 1_000_000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public void dispose() {
        if (positionTimer != null) {
            positionTimer.stop();
        }
        if (player != null) {
            player.close();
        }
        super.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}