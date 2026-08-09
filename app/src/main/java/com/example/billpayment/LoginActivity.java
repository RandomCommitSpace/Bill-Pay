package com.example.billpayment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    EditText etInput;
    Button btnSwitchMode;
    TextInputLayout inputLayout;
    DBHelper dbHelper;

    enum LoginMode { PHONE, EMAIL }
    LoginMode currentMode = LoginMode.PHONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);

        etInput = findViewById(R.id.etPhone);
        inputLayout = findViewById(R.id.inputLayout);
        btnSwitchMode = findViewById(R.id.btnSwitchMode);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnSwitchMode.setOnClickListener(v -> switchMode());
        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {

        String input = etInput.getText().toString().trim();

        if (input.isEmpty()) {
            showError("ورودی خالی است");
            return;
        }

        int access = dbHelper.checkUserAccess(input);

        if (access == 1) {
            int userId = dbHelper.getUserId(input);

            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
            prefs.edit()
                    .putBoolean("isLoggedIn", true)
                    .putInt("user_id", userId)
                    .apply();

            startActivity(new Intent(this, MainActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


        } else if (access == 0) {
            showError("دسترسی این کاربر مسدود شده است");

        } else {
            showError("کاربری با این مشخصات وجود ندارد");
        }
    }

    private void showError(String msg) {

        etInput.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_error, null);

        TextView text = layout.findViewById(R.id.txtToast);
        text.setText(msg);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 150);
        toast.show();
    }


    private void switchMode() {

        etInput.setText("");

        if (currentMode == LoginMode.PHONE) {
            currentMode = LoginMode.EMAIL;
            inputLayout.setHint("ایمیل");
            etInput.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            etInput.setFilters(new InputFilter[]{});
            btnSwitchMode.setText("ورود با شماره موبایل");
        } else {
            currentMode = LoginMode.PHONE;
            inputLayout.setHint("شماره موبایل");
            etInput.setInputType(InputType.TYPE_CLASS_PHONE);
            etInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
            btnSwitchMode.setText("ورود با ایمیل");
        }
    }
}
