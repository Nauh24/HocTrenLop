package com.nauh.demo.kiemtra3mau.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nauh.demo.R;
import com.nauh.demo.kiemtra3mau.database.DatabaseHelper;
import com.nauh.demo.kiemtra3mau.fragment.CourseFragment;
import com.nauh.demo.kiemtra3mau.fragment.StatisticsFragment;

public class CourseManagementActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fabRegister;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;
    private static final int LOGIN_REQUEST_CODE = 1001;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_management);

        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("course_management", MODE_PRIVATE);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        fabRegister = findViewById(R.id.fab_register);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_courses) {
                    selectedFragment = new CourseFragment();
                } else if (item.getItemId() == R.id.nav_statistics) {
                    selectedFragment = new StatisticsFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }

                return true;
            }
        });

        // Set default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CourseFragment())
                .commit();

//        fabRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Kiểm tra xem người dùng đã đăng nhập chưa
//                int studentId = sharedPreferences.getInt("student_id", -1);
//                if (studentId == -1) {
//                    // Chưa đăng nhập, chuyển đến màn hình đăng nhập
//                    Intent loginIntent = new Intent(CourseManagementActivity.this, LoginActivity.class);
//                    startActivityForResult(loginIntent, LOGIN_REQUEST_CODE);
//                } else {
//                    // Đã đăng nhập, mở màn hình đăng ký khóa học
//                    showCourseRegistrationDialog(studentId);
//                }
//            }
//        });
//        fabRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Mở luôn màn hình đăng ký khóa học mà không cần đăng nhập
//                Intent intent = new Intent(CourseManagementActivity.this, CourseRegistrationActivity.class);
//                startActivity(intent);
//            }
//        });
        fabRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CourseManagementActivity.this, "FAB clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CourseManagementActivity.this, CourseRegistrationActivity.class);
                startActivity(intent);
            }
        });


    }

    private void showCourseRegistrationDialog(int studentId) {
        Intent intent = new Intent(CourseManagementActivity.this, CourseRegistrationActivity.class);
        intent.putExtra("student_id", studentId);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            // Đăng nhập thành công, lấy studentId từ SharedPreferences
            int studentId = sharedPreferences.getInt("student_id", -1);
            if (studentId != -1) {
                showCourseRegistrationDialog(studentId);
            }
        }
    }
}
