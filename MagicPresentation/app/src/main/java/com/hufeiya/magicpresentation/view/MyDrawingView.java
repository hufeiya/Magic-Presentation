package com.hufeiya.magicpresentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.hufeiya.magicpresentation.net.SocketClient;
import com.vilyever.drawingview.VDDrawingView;

/**
 * Created by hufeiya on 16-2-7.
 */
public class MyDrawingView extends VDDrawingView {
    public static final int ACTION_CLEAR = 8;
    public static final int ACTION_PREVENT_WHITE_SCREEN = 9;

    private SocketClient socketClient = new SocketClient("192.168.31.8",8890);
    public MyDrawingView(Context context) {
        super(context);
    }

    public MyDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String s = "" + event.getX() + "," + event.getY() + "," + event.getAction();
        socketClient.send(s);
        return super.onTouchEvent(event);
    }

    @Override
    public void clear() {
        super.clear();
        socketClient.send("0,0," + ACTION_CLEAR);
        socketClient.send("0,0," + ACTION_PREVENT_WHITE_SCREEN);
    }
}
