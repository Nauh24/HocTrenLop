package com.nauh.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BMICalculatorActivity extends AppCompatActivity {
    private EditText etHeight, etWeight;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bmicalculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight = etWeight.getText().toString();
                String height = etHeight.getText().toString();
                double w, h;
                try {
                    w = Double.parseDouble(weight);
                    h = Double.parseDouble(height);
                    double bmi = w / (h * h);
                    String formattedBmi = String.format("%.1f", bmi);
                    tvResult.setText(formattedBmi);
                } catch (Exception e) {
                    tvResult.setText("Please enter valid weight and height");
                }
            }
        });
    }

    private void initView() {
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
    }
}