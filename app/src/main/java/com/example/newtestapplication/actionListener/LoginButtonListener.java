package com.example.newtestapplication.actionListener;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newtestapplication.R;
import com.example.newtestapplication.activitys.MainActivity;

public class LoginButtonListener implements View.OnClickListener {
    private AppCompatActivity baseActivity;
    public LoginButtonListener(AppCompatActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onClick(View v) {
            Intent mainActivity = new Intent(baseActivity, MainActivity.class);
            Toast.makeText(baseActivity, R.string.loginToast, Toast.LENGTH_LONG).show();
            this.baseActivity.startActivity(mainActivity);
    }
}
