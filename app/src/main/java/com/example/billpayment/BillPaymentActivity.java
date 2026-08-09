package com.example.billpayment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BillPaymentActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listView;
    ArrayList<String> billList = new ArrayList<>();
    ArrayList<Integer> billIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);

        listView = findViewById(R.id.listBills);
        Button btnPay = findViewById(R.id.btnPay);

        dbHelper = new DBHelper(this);

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId == -1) {
            Toast.makeText(this, "خطا در شناسایی کاربر", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        loadBills(userId);

        btnPay.setOnClickListener(v -> {
            int position = listView.getCheckedItemPosition();

            if (position == ListView.INVALID_POSITION) {
                Toast.makeText(this, "یک قبض انتخاب کنید", Toast.LENGTH_SHORT).show();
                return;
            }

            int billId = billIds.get(position);

            Intent intent = new Intent(this, FakePaymentActivity.class);
            intent.putExtra("bill_id", billId);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    private void loadBills(int userId) {

        Cursor cursor = dbHelper.getBillsForUser(userId);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "قبض پرداخت ‌نشده‌ای وجود ندارد", Toast.LENGTH_LONG).show();
            return;
        }

        while (cursor.moveToNext()) {
            int billId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("bill_type"));
            int amount = cursor.getInt(cursor.getColumnIndexOrThrow("amount"));

            billIds.add(billId);
            billList.add(type + " - مبلغ: " + amount + " تومان");
        }

        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                billList
        );

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
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
