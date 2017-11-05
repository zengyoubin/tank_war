package cn.zeng;

import java.awt.*;

/**
 * @author zyb
 */
public class Explode {
    int x, y;
    private boolean live = true;
    private TankClient tankClient;

    public Explode(int x, int y, TankClient tankClient) {
        this.x = x;
        this.y = y;
        this.tankClient = tankClient;
    }

    int[] diameter = {4, 7, 12, 18, 26, 32, 49, 30, 14, 6};
    int step = 0;

    public void draw(Graphics g) {
        if (!live) {
            tankClient.explodes.remove(this);
            return;
        }
        if (step == diameter.length) {
            live = false;
            step = 0;
            return;
        }
        Color color = g.getColor();
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, diameter[step], diameter[step]);
        step++;
        g.setColor(color);
    }
}
