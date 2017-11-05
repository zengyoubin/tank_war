package cn.zeng;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

/**
 * @author zyb
 */
public class Tank {
    private static final int X_SPEED = 5;
    private static final int Y_SPEED = 5;

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private int x, y;
    private int oldX, oldY;

    private int life = 100;

    private BloodBar bloodBar = new BloodBar();

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    private TankClient tankClient;
    private boolean bL, bU, bR, bD;

    public boolean isGood() {
        return good;
    }

    private boolean good = true;

    private int step = 0;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live = true;

    private static final Random RANDOM = new Random();

    enum Direction {L, LU, U, RU, R, RD, D, LD, STOP}

    private Direction direction = Direction.STOP;
    private Direction ptDirection = Direction.D;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
    }

    public Tank(int x, int y, TankClient tankClient) {
        this(x, y);
        this.tankClient = tankClient;
    }

    public Tank(int x, int y, TankClient tankClient, boolean good) {
        this(x, y, tankClient);
        this.good = good;
    }

    public Tank(int x, int y, TankClient tankClient, boolean good, Direction direction) {
        this(x, y, tankClient, good);
        this.direction = direction;
    }

    public void draw(Graphics g) {
        if (!live) {
            if (!good) {
                tankClient.tanks.remove(this);
            }
            return;
        }
        Color color = g.getColor();
        if (good) {
            bloodBar.draw(g);
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLUE);
        }
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);

        switch (ptDirection) {
            case L:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT / 2);
                break;
            case LU:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y);

                break;
            case U:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y);

                break;
            case RU:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y);

                break;
            case R:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT / 2);

                break;
            case RD:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT);

                break;
            case D:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y + Tank.HEIGHT);

                break;
            case LD:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT / 2);
                break;
            default:
                break;

        }

        move();


    }

    private void move() {
        this.oldX = x;
        this.oldY = y;
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
            case STOP:
            default:
                break;
        }
        if (!this.direction.equals(Direction.STOP)) {
            this.ptDirection = this.direction;
        }
        if (x < 0) {
            x = 0;
        } else if (y < 25) {
            y = 25;
        } else if (x > TankClient.GAME_WIDTH - Tank.WIDTH) {
            x = TankClient.GAME_WIDTH - Tank.WIDTH;
        } else if (y > TankClient.GAME_HEIGHT - Tank.HEIGHT) {
            y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
        }

        if (!good) {
            if (step == 0) {
                step = RANDOM.nextInt(15) + 3;
                Direction[] directions = Direction.values();
                this.direction = directions[RANDOM.nextInt(directions.length)];
            }
            step--;
            if (RANDOM.nextInt(40) > 35) {
                this.fire();
            }
        }
    }

    private void stay() {
        this.x = this.oldX;
        this.y = this.oldY;
    }

    public boolean collideWall(Wall wall) {
        if (this.getRect().intersects(wall.getRect())) {
            stay();
            return true;
        }
        return false;
    }

    public boolean collideTanks(List<Tank> tanks) {
        for (Tank tank : tanks) {
            collideTank(tank);
        }
        return false;
    }

    private boolean collideTank(Tank tank) {
        if (this != tank) {
            if (this.live && tank.isLive() && this.getRect().intersects(tank.getRect())) {
                this.stay();
                tank.stay();
                return true;
            }
        }
        return false;
    }

    private Missile fire() {
        return fire(this.ptDirection);
    }

    private Missile fire(Direction ptDirection) {
        if (!this.live) {
            return null;
        }
        int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
        int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        Missile missile = new Missile(x, y, this.good, ptDirection, this.tankClient);
        this.tankClient.missiles.add(missile);
        return missile;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            case KeyEvent.VK_META:
                //case KeyEvent.VK_CONTROL:
                fire();
                break;
            case KeyEvent.VK_A:
                superFire();
                break;

            default:
                break;
        }
        locateDirection();
    }

    public void KeyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            default:
                break;
        }
        locateDirection();
    }

    void locateDirection() {
        if (!bL && !bU && !bR && !bD) {
            direction = Direction.STOP;
        } else if (bL && !bU && !bR && !bD) {
            direction = Direction.L;
        } else if (bL && bU && !bR && !bD) {
            direction = Direction.LU;
        } else if (!bL && bU && !bR && !bD) {
            direction = Direction.U;
        } else if (!bL && bU && bR && !bD) {
            direction = Direction.RU;
        } else if (!bL && !bU && bR && !bD) {
            direction = Direction.R;
        } else if (!bL && !bU && bR && bD) {
            direction = Direction.RD;
        } else if (!bL && !bU && !bR && bD) {
            direction = Direction.D;
        } else if (bL && !bU && !bR && bD) {
            direction = Direction.LD;
        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    private void superFire() {
        for (Direction direction : Direction.values()) {
            if (Direction.STOP.equals(direction)) {
                continue;
            }
            this.fire(direction);
        }
    }

    private class BloodBar {
        public void draw(Graphics g) {
            Color color = g.getColor();
            g.setColor(Color.RED);
            g.drawRect(x, y - 10, WIDTH, 10);
            g.fillRect(x, y - 10, WIDTH * life / 100, 10);
            g.setColor(color);
        }
    }
}
