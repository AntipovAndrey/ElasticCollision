package ru.andrey;

import javax.swing.*;
import java.awt.*;

public class PanelWithCarts extends JPanel {
    private Cart leftCart, rightCart;

    public PanelWithCarts(int leftSpeed, int leftWeight, int rightSpeed, int rightWeight) {
        super();
        leftCart = new Cart(leftSpeed, new Point(50, 300), leftWeight, new Color(118, 38, 255));
        rightCart = new Cart(rightSpeed, new Point(800, 300), rightWeight, new Color(13, 255, 139));
        setLayout(null);
        add(leftCart);
        add(rightCart);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(0, 300 + leftCart.getBounds().height, 1200, 20);
    }

    public void changeProperties(int leftSpeed, int leftWeight, int rightSpeed, int rightWeight) {
        leftCart.setSpeed(leftSpeed);
        leftCart.setWeight(leftWeight);
        rightCart.setSpeed(rightSpeed);
        rightCart.setWeight(rightWeight);
    }

    public void play() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (rightCart.isCrachedToWall())
                        rightCart.setSpeed(-rightCart.getSpeed());
                    if (leftCart.isCrachedToWall())
                        leftCart.setSpeed(-leftCart.getSpeed());
                    if (leftCart.isCrashed(rightCart)) {
                        System.out.println(leftCart.getSpeed() + " " + rightCart.getSpeed());
                        int lv = leftCart.getSpeed(),
                                lm = leftCart.getWeight(),
                                rv = rightCart.getSpeed(),
                                rm = rightCart.getWeight();
                        lv = lv - rv;
                        leftCart.setSpeed(
                                (lv * (lm - rm))
                                        /
                                        (lm + rm)
                        );

                        rightCart.setSpeed(
                                2 * lm * lv
                                        /
                                        (lm + rm)
                        );
                        System.out.println(leftCart.getSpeed() + " " + rightCart.getSpeed());
                    }
                    leftCart.move();
                    rightCart.move();
                }

            }
        }).start();
    }


}
