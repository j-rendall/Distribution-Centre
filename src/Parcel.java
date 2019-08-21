import java.awt.*;
import java.awt.image.BufferedImage;

public class Parcel
{
    public int x;
    public int y;
    public int xa = 1;
    public int ya = 0;
    public boolean scanning = false;
    private int width;
    private int height;
    private int depth;
    public int colour;

    public Parcel(int x, int y, int width, int height, int depth, int colour)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.colour = colour;
    }

    public void move() {
        x = x + xa;
        y = y + ya;
        if (x > 500 && x < 600) {
//                if (scanning)
//                    scanning = false;
//                else
            scanning = true;
        }
        else {
            scanning = false;
        }
    }

    public void paint(Graphics2D g2d)
    {
        int x1array[] = new int[] {x,(x+width),(x+depth+width),(x+depth)};
        int y1array[] = new int[] {y,y,(y-depth),(y-depth)};
        int x2array[] = new int[] {x+width,x+width,(x+depth)+width,(x+depth)+width};
        int y2array[] = new int[] {y,(y+height),(y-depth+height),(y-depth)};

        if (colour==1)
            g2d.setColor(Color.yellow);
        else if (colour==2)
            g2d.setColor(Color.green);
        else if (colour==3)
            g2d.setColor(Color.blue);
        else g2d.setColor(Color.BLACK);

        g2d.fillRect(x,y,width,height);
        g2d.fillPolygon(x1array,y1array,4);
        g2d.fillPolygon(x2array,y2array,4);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y,width,height);
        g2d.drawPolygon(x1array,y1array,4);
        g2d.drawPolygon(x2array,y2array,4);
    }
}