package com.example.billpayment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPayBill = findViewById(R.id.btnPayBill);
        Button btnHistory = findViewById(R.id.btnHistory);
        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnPayBill.setOnClickListener(v -> {
                    startActivity(new Intent(this, BillPaymentActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                }

        );

        btnHistory.setOnClickListener(v -> {
                    startActivity(new Intent(this, HistoryActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
        );

        btnProfile.setOnClickListener(v ->{
                startActivity(new Intent(this, ProfileActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
        );

        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
            prefs.edit().clear().apply();
            finish();
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finishAffinity();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
