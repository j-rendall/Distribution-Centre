import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Scanner {
    private int x;
    private int y;
    private int xa = 1;
    private int ya = 0;
    private int colour;
    private boolean scanning;
    private BufferedImage international = null;
    private BufferedImage domestic = null;
    private BufferedImage unknown = null;
    private boolean isVisible1 = false;
    private boolean isVisible2 = false;
    private boolean isVisible3 = false;
    private boolean isRed = false;

    public Scanner()
    {
        this.x = x;
        this.y = y;
        this.xa = xa;
        this.ya = ya;
        this.colour = colour;
        this.scanning = scanning;
        try {
            international = ImageIO.read(new File("res\\International.png"));
        } catch (IOException e) {
            System.out.println("Could not find international image");
        }
        try {
            domestic = ImageIO.read(new File("res\\domestic.png"));
        } catch (IOException e) {
            System.out.println("Could not find domestic image");
        }
        try {
            unknown = ImageIO.read(new File("res\\unknown.png"));
        } catch (IOException e) {
            System.out.println("Could not find unknown image");
        }
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (xa==0)
                xa=1;
            else
                xa=0;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            xa = 1;
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            xa = 0;
    }

    public void scan(Parcel[] p, Conveyor[] c)
    {
        for (Conveyor j: c) {
            j.xa=xa;
        }
        for (Parcel k: p) {
            k.xa=xa;
            k.ya=ya;

            if (k.x > 805) {
                k.xa = 0;
                if (k.colour == 1)
                    k.ya = 1;
                else if (k.colour == 2)
                    k.xa = 1;
                else if (k.colour == 3)
                    k.ya = -1;
            }
            if (k.x > 805 && k.x < 845 && k.y > 435 && k.y < 550) {
                k.xa = 0;
                if (k.colour == 1)
                    k.ya = 1;
                else if (k.colour == 2)
                    k.xa = 1;
                else if (k.colour == 3)
                    k.ya = -1;
            }
            if (k.x > 500 && k.x < 600) {
                if (k.colour == 1) {
                    isVisible1 = true;
                    isVisible2 = false;
                    isVisible3 = false;
                    k.scanning = true;
                    isRed = true;
                } else if (k.colour == 2) {
                    isVisible1 = false;
                    isVisible2 = true;
                    isVisible3 = false;
                    k.scanning = true;
                    isRed = true;
                } else if (k.colour == 3) {
                    isVisible1 = false;
                    isVisible2 = false;
                    isVisible3 = true;
                    k.scanning = true;
                    isRed = true;
                }
            }
            if (k.x == 601){
                k.scanning = false;
                isRed = false;
            }
        }
    }

    public void paint(Graphics g2d) {
        if (isVisible1) {
            g2d.drawImage(unknown,20, 450, 150, 150, null);
        }
        else if (isVisible2) {
            g2d.drawImage(domestic,20, 450, 150, 150, null);
        }
        else if (isVisible3) {
            g2d.drawImage(international,20, 450, 150, 150, null);
        }
        g2d.setColor(Color.decode("#440000"));
        if (isRed)
            g2d.setColor(Color.decode("#ff0000"));
        g2d.fillOval(550, 500, 25, 25);
    }
}
