package com.hufeiya.magicpresentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hufeiya.magicpresentation.fragment.DrawingFragment;

public class MainActivity extends AppCompatActivity {

    private DrawingFragment drawingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //VDContextHolder.initial(getApplicationContext());

        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, getDrawingFragment(), "drawingFragment")
                .commit();
    }

    public DrawingFragment getDrawingFragment() {
        if (drawingFragment == null) {
            drawingFragment = (DrawingFragment) getFragmentManager().findFragmentByTag("drawingFragment");
            if (drawingFragment == null) {
                drawingFragment = new DrawingFragment();
            }
        }
        return drawingFragment;
    }
}
