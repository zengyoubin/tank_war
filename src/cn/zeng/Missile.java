package cn.zeng;

import java.awt.*;

/**
 * @author zyb
 */
public class Missile {
    private static final int X_SPEED = 10;
    private static final int Y_SPEED = 10;

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private int x, y;
    private Tank.Direction direction;
    private boolean live = true;
    private TankClient tankClient;

    public Missile(int x, int y, Tank.Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public Missile(int x, int y, Tank.Direction direction,TankClient tankClient) {
        this(x, y, direction);
        this.tankClient = tankClient;
    }

    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);

        move();
    }

    private void move() {
        switch (direction) {
            case L:
                x -= X_SPEED;
                break;
            case LU:
                x -= X_SPEED;
                y -= Y_SPEED;
                break;
            case U:
                y -= Y_SPEED;
                break;
            case RU:
                x += X_SPEED;
                y -= Y_SPEED;
                break;
            case R:
                x += X_SPEED;
                break;
            case RD:
                x += X_SPEED;
                y += Y_SPEED;
                break;
            case D:
                y += Y_SPEED;
                break;
            case LD:
                x -= X_SPEED;
                y += Y_SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
            live = false;
            tankClient.missiles.remove(this);
        }
    }

    public boolean isLive() {
        return live;
    }
}
