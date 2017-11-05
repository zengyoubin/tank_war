package cn.zeng;

import java.awt.*;
import java.util.List;

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
    private boolean good;
    private TankClient tankClient;

    public Missile(int x, int y, Tank.Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Missile(int x, int y, boolean good, Tank.Direction direction, TankClient tankClient) {
        this(x, y, direction);
        this.good = good;
        this.tankClient = tankClient;
    }

    public void draw(Graphics g) {
        if (!live) {
            tankClient.missiles.remove(this);
            return;
        }
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
        }
    }


    public boolean collideWall(Wall wall) {
        if (this.getRect().intersects(wall.getRect())) {
            this.live = false;
            this.tankClient.missiles.remove(this);
        }
        return false;
    }

    public boolean isLive() {
        return live;
    }


    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public boolean hitTank(Tank tank) {
        if (this.live && this.getRect().intersects(tank.getRect()) && tank.isLive() && this.good != tank.isGood()) {
            if (tank.isGood()) {
                int tankLife = tank.getLife() - 20;
                tank.setLife(tankLife);
                if (tankLife > 0) {
                    return false;
                }
            }
            tank.setLive(false);
            this.live = false;
            tankClient.explodes.add(new Explode(x, y, tankClient));
            return true;
        }
        return false;
    }

    public boolean hitTanks(List<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            Tank tank = tanks.get(i);
            if (hitTank(tank)) {
                return true;
            }
        }
        return false;
    }
}
