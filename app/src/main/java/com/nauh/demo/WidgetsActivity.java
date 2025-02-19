package com.nauh.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class WidgetsActivity extends AppCompatActivity {
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10;
    private RadioButton male, female;
    private RatingBar rb;
    private Spinner sp1;
    private Spinner sp2;
    private TextView rs;
    private Button btSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.some_widgets);

        initView();
        initSpinner();

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ss = "Platform: ";
                if (cb1.isChecked()) {
                    ss += cb1.getText() + ", ";
                }
                if (cb2.isChecked()) {
                    ss += cb2.getText() + ", ";
                }
                if (cb3.isChecked()) {
                    ss += cb3.getText() + ", ";
                }
                if (cb4.isChecked()) {
                    ss += cb4.getText() + ", ";
                }
                if (cb5.isChecked()) {
                    ss += cb5.getText() + ", ";
                }
                if (ss.endsWith(", ")) ss = ss.substring(0, ss.length() - 2);

                ss += "\nGender: ";
                if (male.isChecked()) {
                    ss += male.getText();
                } else if (female.isChecked()) {
                    ss += female.getText();
                }

                ss += "\nRating: " + rb.getRating();

                ss += "\nCountry: " + sp1.getSelectedItem().toString();

                ss += "\nUniversity: " + sp2.getSelectedItem().toString();

                ss += "\nMy favorite: ";
                if (cb6.isChecked()) {
                    ss += cb6.getText() + ", ";
                }
                if (cb7.isChecked()) {
                    ss += cb7.getText() + ", ";
                }
                if (cb8.isChecked()) {
                    ss += cb8.getText() + ", ";
                }
                if (cb9.isChecked()) {
                    ss += cb9.getText() + ", ";
                }
                if (cb10.isChecked()) {
                    ss += cb10.getText() + ", ";
                }

                if (ss.endsWith(", ")) ss = ss.substring(0, ss.length() - 2);
                rs.setText(ss);
            }
        });
    }

    private void initView() {
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        cb6 = findViewById(R.id.cb6);
        cb7 = findViewById(R.id.cb7);
        cb8 = findViewById(R.id.cb8);
        cb9 = findViewById(R.id.cb9);
        cb10 = findViewById(R.id.cb10);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        rb = findViewById(R.id.rb);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        rs = findViewById(R.id.rs);
        btSubmit = findViewById(R.id.btSubmit);
    }

    private void initSpinner() {
        String[] list = getResources().getStringArray(R.array.university);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.tech_item2, list);
        sp2.setAdapter(adapter);
    }
}