package com.nauh.demo.baitapintent.bai7backgroundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    private Handler handler;
    private Runnable toastRunnable;
    private boolean isServiceRunning = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // Khởi tạo Handler và Runnable
        handler = new Handler();
        toastRunnable = new Runnable() {
            @Override
            public void run() {
                // Hiển thị Toast
                Toast.makeText(MyService.this, "Service đang chạy...", Toast.LENGTH_SHORT).show();

                // Lập lịch chạy lại sau 5 giây nếu service vẫn đang chạy
                if (isServiceRunning) {
                    handler.postDelayed(this, 5000); // 5000ms = 5 giây
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Hiển thị thông báo khi service bắt đầu
        Toast.makeText(this, "Service đã bắt đầu", Toast.LENGTH_SHORT).show();

        // Đánh dấu service đang chạy
        isServiceRunning = true;

        // Bắt đầu hiển thị Toast định kỳ
        handler.post(toastRunnable);

        // Trả về START_STICKY để service được khởi động lại nếu bị hệ thống kill
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Hiển thị thông báo khi service bị hủy
        Toast.makeText(this, "Service đã dừng", Toast.LENGTH_SHORT).show();

        // Đánh dấu service đã dừng
        isServiceRunning = false;

        // Hủy bỏ các callback đang chờ để tránh memory leak
        handler.removeCallbacks(toastRunnable);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Service này không hỗ trợ binding
        return null;
    }
}