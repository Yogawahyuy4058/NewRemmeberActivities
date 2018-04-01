package org.d3if.rememberactivities;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Yoga Wahyu Yuwono on 18/03/2018.
 */

public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
