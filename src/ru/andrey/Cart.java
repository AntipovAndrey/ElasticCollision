package ru.andrey;

import javax.swing.*;
import java.awt.*;

public class Cart extends JComponent {
    public static final int WIDTH_OF_CART = 100, HEIGHT_OF_CART = 50;
    private double speed;
    private int weight;
    private Point position;
    private Color topColor;

    public Point getPosition() {
        return position;
    }

    Cart(double speed, Point position, int weight, Color topColor) {
        this.speed = speed;
        this.weight = weight;
        this.position = position;
        this.topColor = topColor;
        setBounds(0, 0, WIDTH_OF_CART, (int) (HEIGHT_OF_CART * 1.25));
    }

    void setSpeed(double speed) {
        this.speed = Math.abs(speed) <= 0.2 ? 0.0 : speed;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    double getSpeed() {
        return speed;
    }

    int getWeight() {
        return weight;
    }


    void changeXPosition(int x) {
        this.position = new Point(x, (int) position.getY());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setLocation(position);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Color oldColor = g.getColor();
        g.setColor(topColor);
        g.fillRoundRect(0, 0,
                WIDTH_OF_CART,
                HEIGHT_OF_CART,
                15, 15
        );

        g.setColor(new Color(127, 0, 23));
        g.fillOval(
                (int) (WIDTH_OF_CART * 0.125),
                (int) (HEIGHT_OF_CART * 0.75),
                HEIGHT_OF_CART / 2,
                HEIGHT_OF_CART / 2
        );


        g.fillOval(
                (int) (WIDTH_OF_CART * 0.625),
                (int) (HEIGHT_OF_CART * 0.75),
                HEIGHT_OF_CART / 2,
                HEIGHT_OF_CART / 2
        );
        g.setColor(oldColor);

    }

    void move() {
        int newSpeed;
        if (Math.abs(speed) < 1 && speed != 0) {
            newSpeed = (int) (position.getX() + 1);
        } else {
            newSpeed = (int) (position.getX() + Math.round(speed));
        }
        position = new Point(newSpeed, (int) position.getY());
        try {
            int sleepTime = Math.abs(10);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ArithmeticException ignored) {
        }
        repaint();
    }

    boolean isCrashed(Cart secondCart) {
        return position.distance(secondCart.position) - WIDTH_OF_CART <= 0;
    }

    boolean isCrashedToWall(Dimension d) {
        return position.getX() <= 0 || position.getX() >= d.width - Cart.WIDTH_OF_CART;
    }
}
