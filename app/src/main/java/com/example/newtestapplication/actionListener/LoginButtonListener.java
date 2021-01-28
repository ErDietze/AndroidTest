package com.example.newtestapplication.actionListener;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newtestapplication.R;
import com.example.newtestapplication.activitys.MainActivity;
import com.example.newtestapplication.database.DatabaseConnectionHelper;

public class LoginButtonListener implements View.OnClickListener {
    private AppCompatActivity baseActivity;
    private EditText userNameInput;
    private String username;
    private EditText pwInput;
    private String pw;

    public LoginButtonListener(AppCompatActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onClick(View v) {
        userNameInput = baseActivity.findViewById(R.id.inputUsername);
        pwInput = baseActivity.findViewById(R.id.inputPassword);
        username = userNameInput.getText().toString();
        pw = pwInput.getText().toString();
        if (username.isEmpty() && pw.isEmpty()) {
            Toast.makeText(baseActivity, R.string.usernameAndPwEmpty, Toast.LENGTH_SHORT).show();
        } else if (username.isEmpty()) {
            Toast.makeText(baseActivity, R.string.usernameEmpty, Toast.LENGTH_SHORT).show();
        } else if (pw.isEmpty()) {
            Toast.makeText(baseActivity, R.string.passwordEmpty, Toast.LENGTH_SHORT).show();
        } else {
            validate(username, pw);
        }
        /**    if (!username.isEmpty() && !pw.isEmpty()) {
         Intent mainActivity = new Intent(baseActivity, MainActivity.class);
         Toast.makeText(baseActivity, R.string.loginToast, Toast.LENGTH_LONG).show();
         this.baseActivity.startActivity(mainActivity);
         } */
    }

    private void validate(String username, String password) {
        StringBuffer sb = new StringBuffer();
        Cursor res = new DatabaseConnectionHelper(baseActivity).getAllData();
        System.out.printf("bla");
        if (res.getCount() == 0) {
            Toast.makeText(baseActivity, "Keine daten vorhanden", Toast.LENGTH_SHORT).show();
            new DatabaseConnectionHelper(baseActivity).insertData(username,username,password,true);
            return;
        }
        while (res.moveToNext()) {
            sb.append(res.getString(0) + "\n");
            sb.append(res.getString(1) + "\n");
            sb.append(res.getString(2) + "\n");
            sb.append(res.getString(3) + "\n");
            sb.append(res.getString(4) + "\n");
        }
        System.out.printf(sb.toString());

    }
}
