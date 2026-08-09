package com.example.billpayment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<PaymentModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId == -1) {
            Toast.makeText(this, "User error", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadHistory(userId);
    }

    private void loadHistory(int userId) {

        Cursor cursor = dbHelper.getPaymentsForUser(userId);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "هنوز مبلغی پرداخت نشده", Toast.LENGTH_LONG).show();
            return;
        }

        while (cursor.moveToNext()) {

            String billType = cursor.getString(cursor.getColumnIndexOrThrow("bill_type"));
            int amount = cursor.getInt(cursor.getColumnIndexOrThrow("amount"));
            String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String finalStatus = "ناموفق";
            if ("success".equals(status)){
                finalStatus = "موفق";
                list.add(new PaymentModel(billType, amount, finalStatus, date));
            }else {
                list.add(new PaymentModel(billType, amount, finalStatus, date));
            }
        }

        cursor.close();

        PaymentAdapter adapter = new PaymentAdapter(list);
        recyclerView.setAdapter(adapter);
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
