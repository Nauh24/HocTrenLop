package com.nauh.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nauh.demo.model.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intent2Activity extends AppCompatActivity {
private Button bt;
private TextView tvName;
    private TextView tvSub, tvStudent, tvList;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.intent2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bt = findViewById(R.id.bt);
        tvName = findViewById(R.id.tvName);
        tvSub = findViewById(R.id.tvSub);
        tvStudent = findViewById(R.id.tvStudent);
        img = findViewById(R.id.img);
        tvList = findViewById(R.id.tvList);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        int age = intent.getIntExtra("age", 0);

        String[] subjects = intent.getStringArrayExtra("subjects");

        tvName.setText(name + " " + age);
        tvSub.setText(Arrays.toString(subjects));

        Student student = (Student) intent.getSerializableExtra("student");
        img.setImageResource(student.getImg());
        tvStudent.setText("Name: " + student.getName() + " age:" + student.getAge());

        List<Student> list = (List<Student>) intent.getSerializableExtra("data");
        String tt = "";
        for (Student s : list) {
            tt += "Name: " + s.getName() + " age:" + s.getAge() + "\n";
        }
        tvList.setText(tt);


        bt.setOnClickListener(v -> {
            finish();
        });
    }
}