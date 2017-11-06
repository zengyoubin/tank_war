package cn.zeng;

import java.awt.*;

/**
 * @author zyb
 */
public class Blood {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live = true;
    private int x, y;
    private int step;
    private int[][] position = {
            {350, 300}, {360, 300}, {375, 275}, {400, 200}, {360, 270}, {365, 290}, {340, 280}
    };

    public Blood() {
        this.x = position[0][0];
        this.y = position[0][1];
    }


    public void draw(Graphics g) {
        if (!live) {
            return;
        }
        Color color = g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(color);

        move();

    }

    private void move() {
        step++;
        if (step == position.length) {
            step = 0;
        }
        this.x = position[step][0];
        this.y = position[step][1];
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

}
