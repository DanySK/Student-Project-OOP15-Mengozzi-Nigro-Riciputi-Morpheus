package gioco.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Morpheus extends Thread{
    private final int height;
    private final int width;
    private int x;
    private int y;
    private BufferedImage morpheus;
    private volatile boolean run;
    private int velRun = 5;
    private int velFly = 10;
    private int vel = 100;

    public Morpheus (final int height, final int width, final int x, final int y, final BufferedImage image, Graphics g) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.morpheus = image;
        this.run = true;
        g.drawImage(morpheus, this.x, this.y, this.width, this.height, null);
    }

    public void Run() {
        while(run) {
            goOn();
            getUp();
            try {
                Thread.sleep(vel);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    private void goOn(){
        this.x += velRun;
    }

    private void getUp(){
        this.y += velFly;
    }
}
