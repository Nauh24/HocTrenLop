package com.nauh.demo.baitapintent.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nauh.demo.R;

public class Bai2ActionDialActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView callButton;
    private EditText phoneNumberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2_action_dial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        callButton = findViewById(R.id.callButton);
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        callButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phoneNumber = phoneNumberInput.getText().toString();
        if (phoneNumber.isEmpty()) {
            phoneNumberInput.setError("Vui lòng nhập số điện thoại");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            phoneNumberInput.setError("Không có ứng dụng nào để thực hiện cuộc gọi");
        }
    }
}