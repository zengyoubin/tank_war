package cn.zeng;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zyb
 */
public class TankClient extends Frame {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    private Image offScreenImage = null;

    Tank myTank = new Tank(50, 50, this);
    List<Missile> missiles = new ArrayList<>(16);


    public void launchFrame() {
        this.setTitle("TankWar");
        this.setLocation(400, 300);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyMonitor());
        this.setResizable(false);
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        new Thread(new PaintThread()).start();

    }

    @Override
    public void paint(Graphics g) {
        if (!missiles.isEmpty()) {
            for (int i = 0; i < missiles.size(); i++) {
                missiles.get(i).draw(g);
            }
        }
        myTank.draw(g);
        g.drawString("missiles count:" + missiles.size(), 10, 40);

    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics offScreenImageGraphics = offScreenImage.getGraphics();
        Color color = offScreenImageGraphics.getColor();
        offScreenImageGraphics.setColor(Color.GREEN);
        offScreenImageGraphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        offScreenImageGraphics.setColor(color);
        paint(offScreenImageGraphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        TankClient tankClient = new TankClient();
        tankClient.launchFrame();
    }

    private class PaintThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.KeyReleased(e);
        }
    }
}
