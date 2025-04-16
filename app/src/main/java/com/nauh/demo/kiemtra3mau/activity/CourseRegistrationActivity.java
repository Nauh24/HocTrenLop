package com.nauh.demo.kiemtra3mau.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.nauh.demo.R;
import com.nauh.demo.kiemtra3mau.database.DatabaseHelper;
import com.nauh.demo.kiemtra3mau.model.Course;
import com.nauh.demo.kiemtra3mau.model.Registration;

import java.util.Date;
import java.util.List;

public class CourseRegistrationActivity extends AppCompatActivity {
    private Spinner spinnerCourses;
    private Button btnRegister;
    private DatabaseHelper databaseHelper;
    private int studentId;
    private List<Course> courseList;
    private Course selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_registration);
        
        databaseHelper = new DatabaseHelper(this);
        studentId = getIntent().getIntExtra("student_id", -1);
        
        if (studentId == -1) {
            finish();
            return;
        }
        
        spinnerCourses = findViewById(R.id.spinner_courses);
        btnRegister = findViewById(R.id.btn_register);
        
        loadCourses();
        
        spinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (Course) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForCourse();
            }
        });
    }

    private void loadCourses() {
        courseList = databaseHelper.getAllCourses();
        
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, courseList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapter);
    }

    private void registerForCourse() {
        if (selectedCourse == null) {
            Toast.makeText(this, "Vui lòng chọn khóa học", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Check if already registered
        if (databaseHelper.isStudentRegisteredForCourse(studentId, selectedCourse.getId())) {
            Toast.makeText(this, "Bạn đã đăng ký khóa học này rồi", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Register for course
        Registration registration = new Registration(studentId, selectedCourse.getId(), new Date());
        long result = databaseHelper.registerCourse(registration);
        
        if (result > 0) {
            Toast.makeText(this, "Đăng ký khóa học thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Đăng ký khóa học thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
