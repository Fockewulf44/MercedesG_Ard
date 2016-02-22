package com.example.arduino.mercedesg_ard;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class bluetooth extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private List<String> liste;

    BluetoothAdapter mBluetoothAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        String[] strings = new String[]{"Android Introduction", "Android Setup/Installation", "Android Hello World"};
        ArrayList<String> codeLearnChapters = new ArrayList<>(Arrays.asList(strings));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, codeLearnChapters);

        final BluetoothSocket mmSocket;
        final BluetoothDevice mmDevice;




//        final ListView codeLearnLessons = (ListView) findViewById(R.id.lvBluetooth);
//        codeLearnLessons.setAdapter(arrayAdapter);

        //Filling ListView by Paired Devices
        ListView lvBluetooth = (ListView) findViewById(R.id.lvBtList);
        lvBluetooth.setAdapter(arrayAdapter);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            //Alerts.ShowAlerts("NoBluetooth!", getApplicationContext());
            finish();
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


//      SEARCH
        Button btnSearch = (Button) findViewById(R.id.btSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Alerts.ShowAlerts("Hi There!!", v.getContext());
                try {
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (mBluetoothAdapter == null) {
                        Alerts.ShowAlerts("NoBluetooth!", v.getContext());
                    }

                    //enable Bluetooth
                    if (!mBluetoothAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, 1);
                    }


                    mBluetoothAdapter.startDiscovery();

                    BroadcastReceiver mReceiver = new BroadcastReceiver() {
                        public void onReceive(Context context, Intent intent) {
                            String action = intent.getAction();

                            //Finding devices
                            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                                // Get the BluetoothDevice object from the Intent
                                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                                // Add the name and address to an array adapter to show in a ListView
                                arrayAdapter.add(device.getName() + "\n" + device.getAddress());
                            }
                        }
                    };

                    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(mReceiver, filter);

                } catch (Exception ex) {

                } finally {

                }
            }
        });


        //Stop
        Button btnStopDisc = (Button) findViewById(R.id.btStopDisc);
        btnStopDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBluetoothAdapter != null) {
                    mBluetoothAdapter.cancelDiscovery();
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

        //Fill ListView via button
        Button btnCreateList = (Button) findViewById(R.id.btStopDisc);
        btnCreateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
//                        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//                        "Linux", "OS/2"};
//
//                liste = new ArrayList<>();
//                Collections.addAll(liste, values);
//

                ListView lvBluetooth = (ListView) findViewById(R.id.lvBtList);

                lvBluetooth.setAdapter(arrayAdapter);
            }
        });

        Button btnAddItem = (Button) findViewById(R.id.btAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              arrayAdapter.add("New Item");
                                          }
                                      }
        );

        //Connect from ListView
        final ListView lvBltList = (ListView) findViewById(R.id.lvBtList);
        lvBltList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                try {
                    Object o = lvBltList.getItemAtPosition(position);
//                Alerts.ShowAlerts(o.toString(),arg1.getContext());
                    String[] Splitted = o.toString().split("\n", 2);
                    //BltConnectTHRD inst= new BltConnectTHRD()
                    Alerts.ShowAlerts(Splitted[1],arg1.getContext());
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//                Splitted[1]
                } catch (Exception ex) {
                    Alerts.ShowAlerts(ex.getMessage(), arg1.getContext());
                }
            }
        });


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
    public void onStart() {
        super.onStart();

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
