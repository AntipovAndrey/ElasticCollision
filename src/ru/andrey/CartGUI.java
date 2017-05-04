package ru.andrey;

import javax.swing.*;
import java.awt.*;

public class CartGUI extends JFrame {

    CartGUI() {
        super("Абсолютно упругое соударение двух тел");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(30, 30, 1300, 700);
        setLayout(new BorderLayout());
    }

    CartGUI start() {
        PanelWithSettings settings = new PanelWithSettings(this);
        add(settings, BorderLayout.EAST);
        setVisible(true);
        return this;
    }

}
