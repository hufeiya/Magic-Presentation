/**
 * Created by hufeiya on 16-2-8.
 */
public class Action {
    public static final int ACTION_DOWN = 0;
    public static final int ACTION_UP = 1;
    public static final int ACTION_MOVE = 2;
    public static final int ACTION_CLEAR = 8;
    public static final int ACTION_PREVENT_WHITE_SCREEN = 9;
    public double x;
    public double y;
    public int type;

    public Action(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
