package com.nauh.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nauh.demo.model.Student;

import java.util.ArrayList;
import java.util.List;

public class IntentActivity extends AppCompatActivity {
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.intent1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity.this, Intent2Activity.class);
                String name = "Huan ne";
                intent.putExtra("name", name);

                int age = 21;
                intent.putExtra("age", age);

                String[] subjects = {"Math", "English", "Physics"};
                intent.putExtra("subjects", subjects);

                Student student = new Student(R.drawable.img_1, "Huan ne", 21);
                intent.putExtra("student", (CharSequence) student);

                List<Student> list = new ArrayList<>();
                list.add(student);
                list.add(new Student(R.drawable.cat1, "Meo ", 23));
                list.add(new Student(R.drawable.cat2, "Meo 2", 11));

                startActivity(intent);
            }
        });
    }
}