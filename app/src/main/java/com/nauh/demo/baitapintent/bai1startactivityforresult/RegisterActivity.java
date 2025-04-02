package com.nauh.demo.baitapintent.bai1startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nauh.demo.R;
import com.nauh.demo.model.Account;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUser, etPass;
    private Button btCancel, btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etUser = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPassword);
        btCancel = findViewById(R.id.btCancel);
        btRegister = findViewById(R.id.btRegister);

        btCancel.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btCancel) {
            setResult(RESULT_CANCELED, null);
            finish();
        } else if (v.getId() == R.id.btRegister) {
            Account acc = new Account(etUser.getText().toString(), etPass.getText().toString());
            Intent registerIntent = new Intent();
            registerIntent.putExtra("data", acc);
            setResult(RESULT_OK, registerIntent);
            finish();
        }
    }
}