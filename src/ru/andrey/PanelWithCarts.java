package ru.andrey;

import javax.swing.*;
import java.awt.*;

public class PanelWithCarts extends JPanel {
    private Cart leftCart, rightCart;
    JLabel leftCartLabel, rightCartLabel;
    double recoveryCoefficient;

    public void setRecoveryCoefficient(double recoveryCoefficient) {
        this.recoveryCoefficient = recoveryCoefficient;
    }

    PanelWithCarts(int leftSpeed, int leftWeight, int rightSpeed, int rightWeight, double recoveryCoefficient, int widthOfPanel) {
        super();
        int xMargin = 50;
        leftCart = new Cart(leftSpeed, new Point(xMargin, 300), leftWeight, new Color(118, 38, 255));
        rightCart = new Cart(rightSpeed, new Point(widthOfPanel - Cart.WIDTH_OF_CART - xMargin, 300), rightWeight, new Color(13, 255, 139));
        setLayout(null);
        add(leftCart);
        add(rightCart);
        leftCartLabel = new JLabel();
        rightCartLabel = new JLabel();
        leftCartLabel.setBounds(50, 440, 300, 20);
        rightCartLabel.setBounds(50, 480, 300, 20);
        add(leftCartLabel);
        add(rightCartLabel);
        this.recoveryCoefficient = recoveryCoefficient;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(-1, 300 + leftCart.getBounds().height, 1200, 20);

    }

    void changeProperties(int leftSpeed, int leftWeight, int rightSpeed, int rightWeight) {
        leftCart.setSpeed(leftSpeed);
        leftCart.setWeight(leftWeight);
        rightCart.setSpeed(rightSpeed);
        rightCart.setWeight(rightWeight);
    }

    void play() {
        new Thread(() -> {
            while (true) {
                if (rightCart.isCrashedToWall(getSize())) {
                    rightCart.setSpeed(-rightCart.getSpeed());
                    rightCart.changeXPosition(getSize().width - Cart.WIDTH_OF_CART);
                    rightCart.setSpeed(rightCart.getSpeed() * recoveryCoefficient);
                }
                if (leftCart.isCrashedToWall(getSize())) {
                    leftCart.setSpeed(-leftCart.getSpeed());
                    leftCart.changeXPosition(0);
                    leftCart.setSpeed(leftCart.getSpeed() * recoveryCoefficient);
                }
                if (leftCart.isCrashed(rightCart)) {
                    double lv = leftCart.getSpeed(),
                            lm = leftCart.getWeight(),
                            rv = rightCart.getSpeed(),
                            rm = rightCart.getWeight();
                    lv = lv - rv;
                    leftCart.setSpeed(((
                            (lv * (lm - rm))
                                    /
                                    (lm + rm)) + rv
                    ));

                    rightCart.setSpeed(((
                            2 * lm * lv
                                    /
                                    (lm + rm)) + rv
                    ));
                    leftCart.changeXPosition(rightCart.getPosition().x - Cart.WIDTH_OF_CART);
                    rightCart.setSpeed(rightCart.getSpeed() * recoveryCoefficient);
                    leftCart.setSpeed(leftCart.getSpeed() * recoveryCoefficient);

                }

                leftCartLabel.setText(String.format("Скорость левой телжки: %.2f м/c", leftCart.getSpeed()));
                rightCartLabel.setText(String.format("Скорость правой телжки: %.2f м/c", rightCart.getSpeed()));
                leftCart.move();
                rightCart.move();
            }
        }).start();
    }
}