package com.example.billpayment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FakePaymentActivity extends AppCompatActivity {

    TextView txtBillInfo;
    Button btnSuccess, btnCancel;

    DBHelper dbHelper;
    int billId;
    int amount;
    String billType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_payment);

        txtBillInfo = findViewById(R.id.txtBillInfo);
        btnSuccess = findViewById(R.id.btnSuccess);
        btnCancel = findViewById(R.id.btnCancel);

        dbHelper = new DBHelper(this);

        billId = getIntent().getIntExtra("bill_id", -1);

        if (billId == -1) {
            finish();
            return;
        }

        loadBillInfo();

        btnSuccess.setOnClickListener(v -> paymentSuccess());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void loadBillInfo() {

        Cursor cursor = dbHelper.getBillById(billId);

        if (cursor.moveToFirst()) {
            billType = cursor.getString(cursor.getColumnIndexOrThrow("bill_type"));
            amount = cursor.getInt(cursor.getColumnIndexOrThrow("amount"));

            txtBillInfo.setText(
                    "نوع قبض: " + billType + "\nمبلغ: " + amount + " تومان"
            );
        }

        cursor.close();
    }

    private void paymentSuccess() {

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId == -1) return;

        String date = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm",
                Locale.getDefault()
        ).format(new Date());

        dbHelper.insertPayment(userId, billId, amount, "success", date);
        dbHelper.markBillAsPaid(billId);

        Toast.makeText(this, "پرداخت موفقیت\u200Cآمیز ✅", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, BillPaymentActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
