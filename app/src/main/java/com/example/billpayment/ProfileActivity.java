package com.example.billpayment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView txtUser, txtStatus;
    Button btnLogout;

    DBHelper dbHelper;
    int userId;
    TextView txtTotalPaid, txtUnpaidCount, txtUnpaidAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtUser = findViewById(R.id.txtUser);
        txtStatus = findViewById(R.id.txtStatus);
        btnLogout = findViewById(R.id.btnLogout);

        txtTotalPaid = findViewById(R.id.txtTotalPaid);
        txtUnpaidCount = findViewById(R.id.txtUnpaidCount);
        txtUnpaidAmount = findViewById(R.id.txtUnpaidAmount);

        dbHelper = new DBHelper(this);

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        userId = prefs.getInt("user_id", -1);

        if (userId == -1) {
            goToLogin();
            return;
        }

        loadUserInfo();

        btnLogout.setOnClickListener(v -> logout());
    }

    private void loadUserInfo() {

        Cursor c = dbHelper.getUserById(userId);

        if (c.moveToFirst()) {

            String phone = c.getString(c.getColumnIndexOrThrow("phone"));
            String email = c.getString(c.getColumnIndexOrThrow("email"));
            int isAllowed = c.getInt(c.getColumnIndexOrThrow("is_allowed"));

            if (phone != null) {
                txtUser.setText("شماره موبایل: " + phone);
            } else {
                txtUser.setText("ایمیل: " + email);
            }

            if (isAllowed == 1) {
                txtStatus.setText("وضعیت حساب: فعال ✅");
            } else {
                txtStatus.setText("وضعیت حساب: مسدود 🚫");
            }
        }
        int totalPaid = dbHelper.getTotalPaidAmount(userId);
        int unpaidCount = dbHelper.getUnpaidBillsCount(userId);
        int unpaidAmount = dbHelper.getUnpaidBillsAmount(userId);

        txtTotalPaid.setText("جمع پرداخت‌ها: " + totalPaid + " تومان");
        txtUnpaidCount.setText("قبض پرداخت‌نشده: " + unpaidCount + " عدد");
        txtUnpaidAmount.setText("مبلغ قبض‌های معوق: " + unpaidAmount + " تومان");


        c.close();
    }

    private void logout() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        prefs.edit().clear().apply();
        goToLogin();
    }

    private void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
