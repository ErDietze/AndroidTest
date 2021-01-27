package com.example.newtestapplication.activitys;

import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newtestapplication.R;
import com.example.newtestapplication.actionListener.LoginButtonListener;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(new LoginButtonListener(this));
    }
}
