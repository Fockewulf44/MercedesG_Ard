package com.example.arduino.mercedesg_ard;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by FockeWulf on 6/23/2015.
 */
public class Alerts {

    public static void ShowAlerts(String str, Context ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("Alert Window");
        builder.setMessage(str);
        PromptListener pl = new PromptListener();
        builder.setPositiveButton("OK", pl);
        AlertDialog ad = builder.create();
        ad.show();

    }
}






