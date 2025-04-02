package com.nauh.demo.baitapintent.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat.Type;
import com.nauh.demo.R.id;
import com.nauh.demo.R.layout;

public class Bai3ActionPickActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button selectButton;
    private final ActivityResultLauncher<Intent> pickImageLauncher = this.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result) -> {
        if (result.getResultCode() == -1 && result.getData() != null) {
            Uri imageUri = result.getData().getData();
            if (imageUri != null) {
                this.imageView.setImageURI(imageUri);
            }
        }
    });

    public Bai3ActionPickActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        this.setContentView(layout.activity_bai3_action_pick);
        ViewCompat.setOnApplyWindowInsetsListener(this.findViewById(id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.imageView = (ImageView)this.findViewById(id.imageView);
        this.selectButton = (Button)this.findViewById(id.selectButton);
        this.selectButton.setOnClickListener(v -> Bai3ActionPickActivity.this.openGallery());
    }

    private void openGallery() {
        try {
            Intent intent = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            this.pickImageLauncher.launch(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Không thể mở thư viện ảnh: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}