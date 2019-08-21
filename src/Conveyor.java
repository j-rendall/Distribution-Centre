import java.awt.*;

public class Conveyor {
    private int x = 0;
    private int y = 0;
    public int xa;
    private DistributionCentre dc;

    public Conveyor(DistributionCentre dc, int x, int y, int xa) {
        this.x = x;
        this.y = y;
        this.xa = xa;
        this.dc = dc;
    }

    public void move() {
        if ((x + xa < -50) || (x + xa > dc.getWidth() + 75)) {
            x = x - dc.getWidth();
        }
        //update x
        x = x + xa;
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.white);
        g2d.drawLine(x,y,x+75,y-75);
    }
}
