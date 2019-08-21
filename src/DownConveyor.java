import java.awt.*;

public class DownConveyor {
    private int x = 0;
    private int y = 0;
    private int ya = 0;
    private DistributionCentre dc;

    public DownConveyor(DistributionCentre dc, int x, int y, int ya) {
        this.x = x;
        this.y = y;
        this.ya = ya;
        this.dc = dc;
    }

    public void move() {
        if (y + ya > 665) {
            y = y - 150;
        }
        //update x
        y = y + ya;
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.white);
        g2d.drawLine(x,y,x+100,y);
    }
}
