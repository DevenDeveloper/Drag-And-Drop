package com.example.draganddrop;

import android.content.ClipData;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button dragbutton;
    LinearLayout dropbutton;
    TextView tv,sucess;
    int total,fail = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dragbutton = (Button) findViewById(R.id.one);
        dropbutton = (LinearLayout) findViewById(R.id.bottomlinear);
        tv = (TextView) findViewById(R.id.Total);
        sucess = (TextView) findViewById(R.id.Sucess);
        dropbutton.setOnDragListener(new View.OnDragListener(){
            @Override
            public boolean onDrag(View v, DragEvent event) {
            // TODO Auto-generated method stub
                final int action = event.getAction();
                switch (action){
                    case DragEvent.ACTION_DRAG_STARTED:
                        // Executed after startDrag() is called.
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        // Executed after the Drag Shadow enters the drop area
                        break;
                    case DragEvent.ACTION_DROP: {
                        //Executed when user drops the data
                        fail = fail + 1;
                        return (true);
                    }
                    case DragEvent.ACTION_DRAG_ENDED: {
                        total = total + 1;
                        int value = total - fail;
                        sucess.setText("Sucessful Drops:" + value);
                        tv.setText("Total Drops: " + total);
                        return (true);
                    }
                    default:
                        break;
                }
                return true;
            }
        });

        dragbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
            // TODO Auto-generated method stub
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(dragbutton);
                v.startDrag(data, shadow, null, 0);
                return false;
            }
        });
    }
}
