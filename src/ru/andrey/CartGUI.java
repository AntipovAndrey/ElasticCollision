package ru.andrey;

import javax.swing.*;
import java.awt.*;

class CartGUI extends JFrame {

    CartGUI() {
        super("Соударение двух тел");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(30, 30, 1300, 700);
        setLayout(new BorderLayout());
    }

    void start() {
        PanelWithSettings settings = new PanelWithSettings(this);
        add(settings, BorderLayout.EAST);
        setVisible(true);
    }

}