package com.nauh.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SphereCalculatorActivity extends AppCompatActivity {
    private EditText etRadius;
    private RadioButton rbVolume, rbArea;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sphere_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String radius = etRadius.getText().toString();

                if (radius.isEmpty()) {
                    tvResult.setText("Please enter radius");
                    return;
                } else {
                    double r;
                    try {
                        r = Double.parseDouble(radius);
                        if (r > 0) {
                            if (rbArea.isChecked()) {
                                double area = 4 * Math.PI * r * r;
                                String formattedArea = String.format("%.2f", area);
                                tvResult.setText(formattedArea);
                            } else {
                                double volume = (4.0 / 3.0) * Math.PI * r * r * r;
                                String formattedVolume = String.format("%.2f", volume);
                                tvResult.setText(formattedVolume);
                            }
                        } else {
                            tvResult.setText("Radius must be greater than 0");
                            return;
                        }

                    } catch (Exception e) {
                        tvResult.setText("Please enter valid radius");
                        return;
                    }
                }
            }
        });
    }

    private void initView() {
        etRadius = findViewById(R.id.etRadius);
        rbVolume = findViewById(R.id.rbVolume);
        rbArea = findViewById(R.id.rbArea);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
    }
}