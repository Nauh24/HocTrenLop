package com.nauh.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {

    private TextView rs;
    private EditText et1, et2;
    private Button btCal;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        btCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = et1.getText().toString();
                String s2 = et2.getText().toString();
                double n1, n2;
                try {
                    n1 = Double.parseDouble(s1);
                    n2 = Double.parseDouble(s2);
                    String kq = calculate(n1,n2, "-");
                    rs.setText(kq);
                    Toast.makeText(getApplicationContext(), kq, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhập 2 số", Toast.LENGTH_LONG).show();
                }
            }
        });

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s1 = et1.getText().toString();
                String s2 = et2.getText().toString();
                String p = sp.getSelectedItem().toString();
                double n1, n2;
                try {
                    n1 = Double.parseDouble(s1);
                    n2 = Double.parseDouble(s2);
                    String kq = calculate(n1,n2, p);
                    rs.setText(kq);
                    Toast.makeText(getApplicationContext(), kq, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhập 2 số", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView() {
        rs = findViewById(R.id.rs);
        et1 = findViewById(R.id.e1);
        et2 = findViewById(R.id.e2);
        btCal = findViewById(R.id.btCal);
        sp = findViewById(R.id.sp);
    }

    private String calculate(double a, double b, String op) {
        String s = "";
        switch (op) {
            case "+":
                s = "Tổng: " + (a + b);
                break;
            case "-":
                s = "Hiệu: " + (a - b);
                break;
            case "x":
                s = "Tích: " + (a * b);
                break;
            case "/":
                if (b == 0) {
                    s = "Không thể chia cho 0";
                } else s = "Thương: " + (a / b);
                break;
        }
        return s;
    }

}