import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class DistributionCentre extends JPanel {
    Parcel[] list = new Parcel[20];
    Conveyor[] con = new Conveyor[21];
    UpConveyor[] ucon = new UpConveyor[9];
    DownConveyor[] dcon = new DownConveyor[3];
    Scanner s = new Scanner();

    public DistributionCentre() {
        for (int i=0; i<20; i++) {
            int width = (int) ((Math.random() * (50 - 20) + 1) + 20);
            int height = (int) ((Math.random() * (50 - 20) + 1) + 20);
            int depth = (int) ((Math.random() * (50 - 10) + 1) + 10);
            int colour = (int) Math.ceil(Math.random() * 3);
            int x = -4700 + (i*250);
            list[i] = new Parcel(x, (500-height), width, height, depth, colour);
        }

        for (int c=0; c<21; c++) {
            con[c] = new Conveyor(this,(-100+(50*c)),510,1);
        }
        for (int u=0; u<9; u++) {
            ucon[u] = new UpConveyor(this,800,435 - (u*50),-1);
        }
        for (int d=0; d<3; d++) {
            dcon[d] = new DownConveyor(this,800,660 + (d*50),1);
        }

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                s.keyTyped(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                s.keyReleased(e);
            }
            @Override
            public void keyPressed(KeyEvent e) {
                s.keyPressed(e);
            }
        });
        setFocusable(true);
    }
    private void move() {
        for (Parcel p:list)
            p.move();

        for (Conveyor c:con)
            c.move();
        //if (s.upMove) {
            for (UpConveyor u : ucon)
                u.move();
        //}
        //if (s.downMove) {
            for (DownConveyor d : dcon)
                d.move();
        //}
        s.scan(list, con);
    }

    public void paint(Graphics g)
    {
        super.paint(g); //Clears the panel, for a fresh start
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.decode("0"));  //Paints Background
        g2d.fillRect(0,0,5000, 5000);

        g2d.setColor(Color.decode("#666666"));
        g2d.fillRect(0,435,1500,125); //Main Conveyor Top
        g2d.fillRect(800,0, 125, 1500); //Up/down Conveyor Top
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0,510, 800, 510);//Main line
        g2d.drawLine(900,510,1500,510);
        g2d.drawLine(900, 0, 900, 435); //Up/down line
        g2d.drawLine(900,510,900,1500);
        g2d.drawLine(900,510,925,560); //detail lines
        g2d.drawLine(800,510,800,1500);
        g2d.drawLine(900,435,1500,435);

        g2d.setColor(Color.darkGray);
        for (int f=0; f<800; f=f+50)
            g2d.fillOval(f,510,50,50);

        for (Conveyor c:con)
            c.paint(g2d);
        for (UpConveyor u:ucon)
            u.paint(g2d);
        for (DownConveyor d:dcon)
            d.paint(g2d);

        g2d.setColor(Color.decode("#a4a4a4"));
        int x2array[] = new int[] {140,200,200};
        int y2array[] = new int[] {435,435,360};
        g2d.fillPolygon(x2array,y2array,3);

        g2d.setColor(Color.decode("#a4a4a4"));
        int x3array[] = new int[] {640,700,700};
        g2d.fillPolygon(x3array,y2array,3);

        for (Parcel p:list)
            p.paint(g2d);

        g2d.setColor(Color.decode("#a4a4a4"));
        g2d.fillRect(-10,435, 150,125); //entry box
        int x1array[] = new int[] {-10,140,200,-10};
        int y1array[] = new int[] {435,435,360,360};
        g2d.fillPolygon(x1array,y1array,4);
        g2d.setColor(Color.BLACK);
        g2d.drawLine(140,435,200,360);
        g2d.drawLine(-10,435,140,435);
        g2d.drawLine(140,435,140,560);

        g2d.setColor(Color.decode("#a4a4a4"));
        g2d.fillRect(500,435, 140,125); //scanner box
        int x4array[] = new int[] {500,640,700,560};
        g2d.fillPolygon(x4array,y1array,4);
        g2d.setColor(Color.BLACK);
        g2d.drawLine(640,435,700,360);
        g2d.drawLine(500,435,640,435);
        g2d.drawLine(640,435,640,560);
        g2d.drawLine(500,435,560,360);
        g2d.drawLine(500,435,500,560);

        s.paint(g2d);
    }
    public static void main(String[] args) throws InterruptedException
    {
        JFrame f = new JFrame("Canada Post Simulator 2018");
        DistributionCentre dc = new DistributionCentre();
        f.add(dc);
        f.setSize(1020,640);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true)
        {
            dc.move(); //Updates the coordinates
            dc.repaint(); //Calls the paint method
            Thread.sleep(10); //Pauses for a moment
        }
    }
}
