package ru.andrey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelWithSettings extends JPanel {

    private JSlider leftWeightSlider, leftSpeedSlider;
    private JSlider rightWeightSlider, rightSpeedSlider;
    private PanelWithCarts carts;


    public PanelWithSettings(JFrame mainFrame) {
        super();
        setBorder(BorderFactory.createLineBorder(Color.black));

        Box settings = Box.createVerticalBox();

        Box leftCartSettings = Box.createVerticalBox();
        leftCartSettings.setBorder(BorderFactory.createTitledBorder("Левая тележка"));

        Box leftWeight = Box.createHorizontalBox();
        Box leftSpeed = Box.createHorizontalBox();

        leftWeight.add(Box.createHorizontalStrut(5));
        leftWeight.add(new JLabel("m "));
        leftWeightSlider = new JSlider(SwingConstants.HORIZONTAL, 10, 100, 50);
        leftWeightSlider.setMajorTickSpacing(10);
        leftWeightSlider.setMinorTickSpacing(5);
        leftWeightSlider.setPaintTicks(true);
        leftWeight.add(leftWeightSlider);

        leftSpeed.add(Box.createHorizontalStrut(5));
        leftSpeed.add(new JLabel("v "));
        leftSpeed.add(Box.createHorizontalStrut(5));
        leftSpeedSlider = new JSlider(SwingConstants.HORIZONTAL, -10, 10, 5);
        leftSpeedSlider.setMajorTickSpacing(1);
        leftSpeedSlider.setPaintTicks(true);
        leftSpeed.add(leftSpeedSlider);

        leftCartSettings.add(leftWeight);
        leftCartSettings.add(leftSpeed);

        Box rightCartSettings = Box.createVerticalBox();
        rightCartSettings.setBorder(BorderFactory.createTitledBorder("Правая тележка"));

        Box rightWeight = Box.createHorizontalBox();
        Box rightSpeed = Box.createHorizontalBox();

        rightWeight.add(Box.createHorizontalStrut(5));
        rightWeight.add(new JLabel("m "));
        rightWeightSlider = new JSlider(SwingConstants.HORIZONTAL, 10, 100, 50);
        rightWeightSlider.setMajorTickSpacing(10);
        rightWeightSlider.setMinorTickSpacing(5);
        rightWeightSlider.setPaintTicks(true);
        rightWeight.add(rightWeightSlider);

        rightSpeed.add(Box.createHorizontalStrut(5));
        rightSpeed.add(new JLabel("v "));
        rightSpeed.add(Box.createHorizontalStrut(5));
        rightSpeedSlider = new JSlider(SwingConstants.HORIZONTAL, -10, 10, -5);
        rightSpeedSlider.setMajorTickSpacing(1);
        rightSpeedSlider.setPaintTicks(true);
        rightSpeed.add(rightSpeedSlider);

        rightCartSettings.add(rightWeight);
        rightCartSettings.add(rightSpeed);

        Box buttons = Box.createHorizontalBox();

        JButton startButton = new JButton("Старт");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });
        buttons.add(startButton);
        buttons.add(Box.createHorizontalStrut(25));
        JButton stopButton = new JButton(" Стоп ");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSimulation(mainFrame);
            }
        });
        buttons.add(stopButton);


        settings.add(Box.createVerticalStrut(25));
        settings.add(leftCartSettings);
        settings.add(Box.createVerticalStrut(25));
        settings.add(rightCartSettings);
        settings.add(Box.createVerticalStrut(25));
        settings.add(buttons);
        add(settings);

        carts = new PanelWithCarts(
                leftSpeedSlider.getValue(), leftWeightSlider.getValue(),
                rightSpeedSlider.getValue(), rightWeightSlider.getValue()
        );
        mainFrame.add(carts, BorderLayout.CENTER);
    }

    public void startSimulation() {
        carts.changeProperties(
                leftSpeedSlider.getValue(), leftWeightSlider.getValue(),
                rightSpeedSlider.getValue(), rightWeightSlider.getValue()
        );
        carts.play();
    }

    public void stopSimulation(JFrame mainFrame) {

        mainFrame.remove(carts);
        carts = new PanelWithCarts(
                leftSpeedSlider.getValue(), leftWeightSlider.getValue(),
                rightSpeedSlider.getValue(), rightWeightSlider.getValue()
        );
        mainFrame.add(carts, BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
