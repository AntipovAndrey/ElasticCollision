package ru.andrey;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

class PanelWithSettings extends JPanel {

    private JSlider leftWeightSlider, leftSpeedSlider;
    private JSlider rightWeightSlider, rightSpeedSlider;
    private JSlider recoveryCoefficient;
    private PanelWithCarts carts;


    PanelWithSettings(JFrame mainFrame) {
        super();


        setBorder(BorderFactory.createLineBorder(Color.black));

        Box settings = Box.createVerticalBox();

        Box leftCartSettings = Box.createVerticalBox();
        leftCartSettings.setBorder(BorderFactory.createTitledBorder("Левая тележка"));

        Box leftWeight = Box.createHorizontalBox();
        Box leftSpeed = Box.createHorizontalBox();

        leftWeight.add(Box.createHorizontalStrut(5));
        leftWeight.add(new JLabel("m (кг)     "));
        leftWeightSlider = new JSlider(SwingConstants.HORIZONTAL, 10, 100, 50);
        leftWeightSlider.setMajorTickSpacing(20);
        leftWeightSlider.setMinorTickSpacing(5);
        leftWeightSlider.setPaintTicks(true);
        leftWeightSlider.setPaintLabels(true);
        leftWeight.add(leftWeightSlider);

        leftSpeed.add(Box.createHorizontalStrut(5));
        leftSpeed.add(new JLabel("v (м/с)  "));
        leftSpeed.add(Box.createHorizontalStrut(5));
        leftSpeedSlider = new JSlider(SwingConstants.HORIZONTAL, -10, 10, 5);
        leftSpeedSlider.setMajorTickSpacing(5);
        leftSpeedSlider.setPaintTicks(true);
        leftSpeedSlider.setPaintLabels(true);
        leftSpeed.add(leftSpeedSlider);

        leftCartSettings.add(leftWeight);
        leftCartSettings.add(Box.createVerticalStrut(10));
        leftCartSettings.add(leftSpeed);
        leftCartSettings.add(Box.createVerticalStrut(3));

        Box rightCartSettings = Box.createVerticalBox();
        rightCartSettings.setBorder(BorderFactory.createTitledBorder("Правая тележка"));

        Box rightWeight = Box.createHorizontalBox();
        Box rightSpeed = Box.createHorizontalBox();

        rightWeight.add(Box.createHorizontalStrut(5));
        rightWeight.add(new JLabel("m (кг)     "));
        rightWeightSlider = new JSlider(SwingConstants.HORIZONTAL, 10, 100, 50);
        rightWeightSlider.setMajorTickSpacing(20);
        rightWeightSlider.setMinorTickSpacing(5);
        rightWeightSlider.setPaintTicks(true);
        rightWeightSlider.setPaintLabels(true);
        rightWeight.add(rightWeightSlider);

        rightSpeed.add(Box.createHorizontalStrut(5));
        rightSpeed.add(new JLabel("v (м/с)  "));
        rightSpeed.add(Box.createHorizontalStrut(5));
        rightSpeedSlider = new JSlider(SwingConstants.HORIZONTAL, -10, 10, -5);
        rightSpeedSlider.setMajorTickSpacing(5);
        rightSpeedSlider.setPaintTicks(true);
        rightSpeedSlider.setPaintLabels(true);
        rightSpeed.add(rightSpeedSlider);

        rightCartSettings.add(rightWeight);
        rightCartSettings.add(Box.createVerticalStrut(10));
        rightCartSettings.add(rightSpeed);
        rightCartSettings.add(Box.createVerticalStrut(3));

        Box buttons = Box.createHorizontalBox();

        JButton startButton = new JButton("Старт");
        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            startSimulation();
        });
        buttons.add(startButton);
        buttons.add(Box.createHorizontalStrut(25));
        JButton stopButton = new JButton(" Стоп ");
        stopButton.addActionListener(e -> {
            startButton.setEnabled(true);
            stopSimulation(mainFrame);
        });
        buttons.add(stopButton);
        recoveryCoefficient = new JSlider(5, 10, 10);

        Dictionary<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(5, new JLabel("0.5"));
        labelTable.put(6, new JLabel("0.6"));
        labelTable.put(7, new JLabel("0.7"));
        labelTable.put(8, new JLabel("0.8"));
        labelTable.put(9, new JLabel("0.9"));
        labelTable.put(10, new JLabel("1.0"));

        recoveryCoefficient.setLabelTable(labelTable);
        recoveryCoefficient.setMajorTickSpacing(1);
        recoveryCoefficient.setPaintTicks(true);
        recoveryCoefficient.setPaintLabels(true);
        recoveryCoefficient.setBorder(BorderFactory.createTitledBorder("Коэфф. восстановления"));


        settings.add(Box.createVerticalStrut(25));
        settings.add(leftCartSettings);
        settings.add(Box.createVerticalStrut(25));
        settings.add(rightCartSettings);

        settings.add(Box.createVerticalStrut(25));
        settings.add(recoveryCoefficient);
        settings.add(Box.createVerticalStrut(25));
        settings.add(buttons);
        add(settings);

        carts = new PanelWithCarts(
                leftSpeedSlider.getValue(), leftWeightSlider.getValue(),
                rightSpeedSlider.getValue(), rightWeightSlider.getValue(),
                1.0 * recoveryCoefficient.getValue() / recoveryCoefficient.getMaximum()
        );
        mainFrame.add(carts, BorderLayout.CENTER);
    }

    private void startSimulation() {
        carts.changeProperties(
                leftSpeedSlider.getValue(), leftWeightSlider.getValue(),
                rightSpeedSlider.getValue(), rightWeightSlider.getValue()
        );
        carts.setRecoveryCoefficient(1.0 * recoveryCoefficient.getValue() / recoveryCoefficient.getMaximum());
        carts.play();
    }

    private void stopSimulation(JFrame mainFrame) {
        mainFrame.remove(carts);
        carts = new PanelWithCarts(
                leftSpeedSlider.getValue(), leftWeightSlider.getValue(),
                rightSpeedSlider.getValue(), rightWeightSlider.getValue(),
                1.0 * recoveryCoefficient.getValue() / recoveryCoefficient.getMaximum()
        );
        mainFrame.add(carts, BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
    }






}

