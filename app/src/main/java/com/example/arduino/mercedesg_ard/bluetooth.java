package com.example.arduino.mercedesg_ard;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;


public class bluetooth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        String[] strings = new String[]{"Android Introduction", "Android Setup/Installation", "Android Hello World", "Android Layouts/Viewgroups", "Android Activity & Lifecycle", "Intents in Android"};
        ArrayList<String> codeLearnChapters = new ArrayList<>(Arrays.asList(strings));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, codeLearnChapters);

        final BluetoothSocket mmSocket;
        final BluetoothDevice mmDevice;


//        final ListView codeLearnLessons = (ListView) findViewById(R.id.lvBluetooth);
//        codeLearnLessons.setAdapter(arrayAdapter);

//      SEARCH
        Button btnSearch = (Button) findViewById(R.id.btSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Alerts.ShowAlerts("Hi There!!", v.getContext());
                try {
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (mBluetoothAdapter == null) {
                        Alerts.ShowAlerts("NoBluetooth!", v.getContext());
                    }

                    //enable Bluetooth
                    if (!mBluetoothAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, 1);
                    }


                    mBluetoothAdapter.startDiscovery();

                } catch (Exception ex) {

                }

            }
        });
//        GET PAIRED DEVICES LIST
        Button btnPaired = (Button) findViewById(R.id.btPaired);
        btnPaired.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter == null) {
                    Alerts.ShowAlerts("NoBluetooth!", v.getContext());
                }

                //enable Bluetooth
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, 1);
                }

                arrayAdapter.clear();
//                 If there are paired devices
                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

                if (pairedDevices.size() > 0) {
                    // Loop through paired devices
                    for (BluetoothDevice device : pairedDevices) {
                        // Add the name and address to an array adapter to show in a ListView
                        arrayAdapter.add(device.getName() + "\n" + device.getAddress());
                    }
                }
            }
        });

//        CONNECT TO PAIRED DEVICE
//        Button buttonConnect = (Button) findViewById(R.id.btConnect);
//        buttonConnect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    BluetoothSocket tmp = null;
//                    mmDevice = device;
//
//
//                    // MY_UUID is the app's UUID string, also used by the server code
//                    tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
//
//                    mmSocket = tmp;
//
//                } catch (Exception ex) {
//                    Alerts.ShowAlerts(ex.toString(), v.getContext());
//                }
//            }
//        });

//      BACK Button
        Button button = (Button) findViewById(R.id.btBack);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                finish();
                onBackPressed();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bluetooth, menu);
        return true;
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
