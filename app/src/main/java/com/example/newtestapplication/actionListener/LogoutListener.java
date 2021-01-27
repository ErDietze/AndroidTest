package com.example.newtestapplication.actionListener;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newtestapplication.activitys.LoginActivity;

public class LogoutListener {

    public LogoutListener(AppCompatActivity baseActvity){
        Intent login = new Intent(baseActvity, LoginActivity.class);
        Toast.makeText(baseActvity,"abmelden", Toast.LENGTH_SHORT).show();
        baseActvity.startActivity(login);
    }
}
