package com.nauh.demo.touch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nauh.demo.R;

public class TouchActivity extends AppCompatActivity {
    private EditText eXAxis, eYAxis, eMoveX, eMoveY;
    private ImageView img;
    private float xDown = 0, yDown = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.touch_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        img.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        xDown = motionEvent.getX();
                        yDown = motionEvent.getY();
                        eXAxis.setText(String.valueOf(xDown));
                        eYAxis.setText(String.valueOf(yDown));
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float xMove = motionEvent.getX();
                        float yMove = motionEvent.getY();
                        eMoveX.setText(String.valueOf(xMove));
                        eMoveY.setText(String.valueOf(yMove));
                        float deltaX = xMove - xDown;
                        float deltaY = yMove - yDown;
                        img.setX(img.getX() + deltaX);
                        img.setY(img.getY() + deltaY);
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        eXAxis = findViewById(R.id.eXAxis);
        eYAxis = findViewById(R.id.eYAxis);
        eMoveX = findViewById(R.id.eMoveX);
        eMoveY = findViewById(R.id.eMoveY);
        img = findViewById(R.id.img);
    }
}