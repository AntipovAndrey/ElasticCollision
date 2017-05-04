package ru.andrey;

import javax.swing.*;
import java.awt.*;

public class Cart extends JComponent {
    public static final int WIDTH_OF_CART = 100, HEIGHT_OF_CART = 50;
    private int speed;
    private int weight;
    private Point position;
    private Color topColor;

    Cart(int speed, Point position, int weight, Color topColor) {
        this.speed = speed;
        this.weight = weight;
        this.position = position;
        this.topColor = topColor;
        setBounds(0, 0, WIDTH_OF_CART, (int) (HEIGHT_OF_CART * 1.25));
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWeight() {
        return weight;
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

    public void move() {
        position = new Point((int) position.getX() + speed, (int) position.getY());
        try {
            int sleepTime = Math.abs(10);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
        }
        repaint();
    }

    public boolean isCrashed(Cart secondCart) {
        return position.distance(secondCart.position) -  WIDTH_OF_CART  <= 0;
    }

    public boolean isCrachedToWall() {
        return position.getX() <= 0 || position.getX() >= 975;
    }
}
