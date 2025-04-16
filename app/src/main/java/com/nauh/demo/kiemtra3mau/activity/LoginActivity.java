package com.nauh.demo.kiemtra3mau.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nauh.demo.R;
import com.nauh.demo.baitapintent.bai1startactivityforresult.MainActivity;
import com.nauh.demo.kiemtra3mau.database.DatabaseHelper;
import com.nauh.demo.kiemtra3mau.model.Student;


public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("course_management", MODE_PRIVATE);
        
        // Check if user is already logged in
        if (sharedPreferences.getInt("student_id", -1) != -1) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ  || password.isEmpty()) thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                Student student = databaseHelper.getStudentByEmailAndPassword(email, password);
                if (student != null) {
                    // Save login info
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("student_id", student.getId());
                    editor.putString("student_name", student.getName());
                    editor.apply();
                    
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
