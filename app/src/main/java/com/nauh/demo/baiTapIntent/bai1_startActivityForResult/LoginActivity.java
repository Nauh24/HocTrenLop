package com.nauh.demo.baiTapIntent.bai1_startActivityForResult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nauh.demo.R;
import com.nauh.demo.model.Account;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView etUser, etPass;
    private Button btLogin, btRegister;
    private final static int REQUEST_CODE = 1;
    private Account user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUser = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);

        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btLogin) {
            // Handle login button click
            Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
            Account acc = new Account(etUser.getText().toString(), etPass.getText().toString());
            loginIntent.putExtra("account", acc);
            loginIntent.putExtra("user", user);
            startActivity(loginIntent);
        } else if (v.getId() == R.id.btRegister) {
            // Handle register button click
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(registerIntent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                user = (Account) data.getSerializableExtra("data");
                etUser.setText(user.getUsername());
                etPass.setText(user.getPassword());
            }
        } else {
            user = null;
        }
    }
}