package com.example.newtestapplication.actionListener;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newtestapplication.R;
import com.example.newtestapplication.activitys.MainActivity;
import com.example.newtestapplication.database.DatabaseConnectionHelper;

public class LoginButtonListener implements View.OnClickListener {
    private AppCompatActivity baseActivity;

    public LoginButtonListener(AppCompatActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onClick(View v) {
        EditText userNameInput = baseActivity.findViewById(R.id.inputUsername);
        EditText pwInput = baseActivity.findViewById(R.id.inputPassword);
        String username = userNameInput.getText().toString();
        String pw = pwInput.getText().toString();
        if (username.isEmpty() && pw.isEmpty()) {
            Toast.makeText(baseActivity, R.string.usernameAndPwEmpty, Toast.LENGTH_SHORT).show();
        } else if (username.isEmpty()) {
            Toast.makeText(baseActivity, R.string.usernameEmpty, Toast.LENGTH_SHORT).show();
        } else if (pw.isEmpty()) {
            Toast.makeText(baseActivity, R.string.passwordEmpty, Toast.LENGTH_SHORT).show();
        } else {
            if (new DatabaseConnectionHelper(baseActivity).userExist(username, pw)) {
                Intent mainActivity = new Intent(baseActivity, MainActivity.class);
                Toast.makeText(baseActivity, R.string.loginToast, Toast.LENGTH_LONG).show();
                this.baseActivity.startActivity(mainActivity);
            } else {
                Toast.makeText(baseActivity, R.string.wrongCredentials, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
