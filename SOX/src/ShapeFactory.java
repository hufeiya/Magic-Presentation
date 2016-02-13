import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Vector;

/**
 * Created by hufeiya on 16-2-8.
 */
public class ShapeFactory {

    private GeneralPath generalPath;
    private Vector<Action> v;
    private int i = 0;      //the index of the current draw point of v
    private boolean isDrawing = true;
    private int lineWidth = 1;

    public  ShapeFactory(Vector<Action> v){
        this.v = v;
        generalPath = new GeneralPath();
        preventWhiteScreen();
    }

    public void startGenerateShape(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isDrawing) {
                    for (; i < v.size() - 1; i++) {
                        Action action = v.get(i);
                        if (action.type == Action.ACTION_MOVE || action.type == Action.ACTION_UP) {
                            Action preAction = v.get(i - 1);
                            drawLine2(preAction.x, preAction.y, action.x, action.y, generalPath, lineWidth);
                        }else if(action.type == Action.ACTION_CLEAR){
                            clear();
                        }else if (action.type == Action.ACTION_PREVENT_WHITE_SCREEN){
                            preventWhiteScreen();
                        }
                    }
                }
            }
        }).start();
    }

    //depresed
    private void drawLine(double x1,double y1,double x2,double y2,GeneralPath generalPath,int size){
        Line2D line2D = new Line2D.Double(x1, y1, x2, y2);
        Line2D line2D2 = new Line2D.Double(x2, y2 + size, x1, y1 + size);
        generalPath.append(line2D, false);
        generalPath.append(line2D2, true);
    }

    private void drawLine2(double x1,double y1,double x2,double y2,GeneralPath generalPath,int size){
        generalPath.moveTo(x1,y1);
        generalPath.lineTo(x2,y2);
        generalPath.lineTo(x2+size,y2+size);
        generalPath.lineTo(x1+size,y1+size);
        generalPath.closePath();


    }

    private void drawLine3(double x1,double y1,double x2,double y2,GeneralPath generalPath,int size){
        generalPath.moveTo(x1,y1);
        generalPath.lineTo(x2,y2);
        generalPath.lineTo(x2+size,y2+size);
        generalPath.lineTo(x1+size,y1+size);
        generalPath.closePath();


    }

    public void clear(){
        generalPath.reset();
        v.clear();
        preventWhiteScreen();
    }

    public GeneralPath getGeneralPath() {
        return generalPath;
    }

    private void preventWhiteScreen(){
        drawLine(0,0,1,1,generalPath,1);//Draw a point first to prevent the white screen when v is empty.
    }
}
