package com.nauh.demo.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nauh.demo.R;

public class ImplicitIntentActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iWeb, iSms, iPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.implicit_intent_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iWeb = findViewById(R.id.web);
        iSms = findViewById(R.id.sms);
        iPhone = findViewById(R.id.phone);

        iWeb.setOnClickListener(this);
        iSms.setOnClickListener(this);
        iPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.web) {
            // Open a web page
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://vnexpress.net/"));
            startActivity(intent);
        } else if(v.getId() == R.id.sms) {
            // Send an SMS
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:1234567890"));
            intent.putExtra("sms_body", "Hello, this is a test message.");
            startActivity(intent);
        } else if(v.getId() == R.id.phone) {
            // Make a phone call
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:1234567890"));
            startActivity(intent);
        }

    }
}