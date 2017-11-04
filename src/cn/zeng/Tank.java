package cn.zeng;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author zyb
 */
public class Tank {
    private static final int X_SPEED = 5;
    private static final int Y_SPEED = 5;

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private int x, y;

    private TankClient tankClient;
    private boolean bL, bU, bR, bD;

    enum Direction {L, LU, U, RU, R, RD, D, LD, STOP}

    private Direction direction = Direction.STOP;
    private Direction ptDirection = Direction.D;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank(int x, int y, TankClient tankClient) {
        this(x, y);
        this.tankClient = tankClient;
    }

    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
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
        if(x<0){
            x=0;
        }else if(y<25){
            y=25;
        }else if(x>TankClient.GAME_WIDTH-Tank.WIDTH){
            x = TankClient.GAME_WIDTH - Tank.WIDTH;
        }else if(y>TankClient.GAME_HEIGHT-Tank.HEIGHT){
            y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
        }
    }

    private Missile fire() {
        int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
        int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        Missile missile = new Missile(x, y, ptDirection, this.tankClient);
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
            case KeyEvent.VK_CONTROL:
                fire();
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
}
