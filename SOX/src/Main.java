import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by hufeiya on 16-2-5.
 */
public class Main {

    static int currentIndex = 0;
    public static void main(String[] args) {
        final SocketServer socketServer = new SocketServer(8890);
        socketServer.startListen();
        final ShapeFactory shapeFactory = new ShapeFactory(socketServer.v);
        shapeFactory.startGenerateShape();

        Window w = new Window(null) {

            @Override
            public void paint(Graphics g) {
                Graphics2D g2d = ((Graphics2D) g);

                setShape(shapeFactory.getGeneralPath());
                currentIndex = socketServer.v.size();

                g.setColor(Color.RED);
                g2d.fill(getGraphicsConfiguration().getBounds());
            }

            @Override
            public void update(Graphics g) {
                paint(g);
            }
        };
        w.setAlwaysOnTop(true);
        w.setBounds(w.getGraphicsConfiguration().getBounds());
        w.setVisible(true);
        w.setOpacity(0.5f);

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(currentIndex < socketServer.v.size()){
                w.repaint();
            }

        }

    }
}
