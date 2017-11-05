package cn.zeng;

import java.awt.*;

/**
 * @author zyb
 */
public class Wall {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 200;
    private int x, y;
    private TankClient tankClient;

    public Wall(int x, int y, TankClient tankClient) {
        this.x = x;
        this.y = y;
        this.tankClient = tankClient;
    }

    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.CYAN);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(color);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
