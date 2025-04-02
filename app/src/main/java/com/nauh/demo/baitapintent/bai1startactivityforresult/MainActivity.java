package com.nauh.demo.baitapintent.bai1startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nauh.demo.R;
import com.nauh.demo.model.Account;

public class MainActivity extends AppCompatActivity {
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt = findViewById(R.id.tv);
        Intent intent = getIntent();
        if (intent.getSerializableExtra("account") != null && intent.getSerializableExtra("user") != null) {
            Account acc = (Account) intent.getSerializableExtra("account");
            Account user = (Account) intent.getSerializableExtra("user");
            if (acc.getUsername().equals(user.getUsername())  && acc.getPassword().equals(user.getPassword()) ) {
                txt.setText("Hello " + acc.getUsername() + " đã đăng nhập vào hệ thống");
            } else {
                txt.setText("Login Failed");
            }
        }
    }
}