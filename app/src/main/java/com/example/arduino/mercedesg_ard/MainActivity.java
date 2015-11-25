package com.example.arduino.mercedesg_ard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //      Restart
        Button btRestart = (Button) findViewById(R.id.btRestart);
        btRestart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Alerts.ShowAlerts("Hi There!!", v.getContext());
                try {
//                    PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//                    pm.reboot(null);

                    Runtime.getRuntime().exec("su");
                    Runtime.getRuntime().exec("reboot");

                } catch (Exception ex) {
                    Alerts.ShowAlerts(ex.getMessage(),v.getContext());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void PopupMessage(View view)
    {
        try {
            Context context = getApplicationContext();
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;

//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
            TextView t;
            t=new TextView(this);

            t = (TextView)findViewById(R.id.textView10);
            t.setText("Clicked!");

            Intent intent = new Intent(this,bluetooth.class );
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast toast = Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
