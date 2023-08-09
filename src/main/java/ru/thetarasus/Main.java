package ru.thetarasus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Main {


    int tileSize = 16, verticalTilesCount = 12;

    public static JLabel debugPanel;

    public static void main(String[] args) {
        JFrame initFrame = new JFrame("BydloStrategy Launch Options");
        initFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initFrame.setVisible(true);
        initFrame.setLayout(new FlowLayout());
        JLabel resLabel = new JLabel("Choose a resolution: ");
        resLabel.setAlignmentX(JFrame.LEFT_ALIGNMENT);
        initFrame.getContentPane().add(resLabel);

        String[] resolutions = {"768x576", "1024x768", "1280x1024", "1920x1080"};

        JComboBox<String> resComboBox = new JComboBox<>(resolutions);
        resComboBox.setAlignmentX(JFrame.RIGHT_ALIGNMENT);
        resComboBox.addActionListener(listener -> {
            JComboBox box = (JComboBox) listener.getSource();
            String item = (String)box.getSelectedItem();
        });
        initFrame.getContentPane().add(resComboBox);

        JButton launchBtn = new JButton("LAUNCH");
        launchBtn.addActionListener(e -> {
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("BydloStrategy");
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);

            window.setLayout(new FlowLayout());
            debugPanel = new JLabel("x: null, y: null;");
            debugPanel.setVisible(true);
            window.add(debugPanel);
            GamePanel gamePanel = new GamePanel();
            gamePanel.startGameThread();
            window.add(gamePanel);
            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);
            initFrame.dispose();
        });
        launchBtn.setSize(100, 80);
        launchBtn.setAlignmentY(JFrame.BOTTOM_ALIGNMENT);
        initFrame.getContentPane().add(launchBtn);

        initFrame.setLocation(430, 100);
        initFrame.setSize(new Dimension(500, 250));
        initFrame.setVisible(true);
        initFrame.setLocationRelativeTo(null);
    }
}