package com.example.newtestapplication.activitys;

import android.os.Bundle;
import android.widget.Toast;
import com.example.newtestapplication.R;
import com.example.newtestapplication.actionListener.LogoutListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       /** FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); **/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_einstellungen) {
            Toast.makeText(this,"option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.menuAbmelden){
           /** Intent login = new Intent(this, LoginActivity.class);
            Toast.makeText(this,"abmelden", Toast.LENGTH_SHORT).show();
            this.startActivity(login); */
           new LogoutListener(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
