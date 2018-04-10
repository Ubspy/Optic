package com.ubspy.optic.main;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class BluetoothCommunicationService extends Service
{
    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice bluetoothDevice;
    BluetoothSocket socket;

    InputStream input;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    //Runs on creation
    @Override
    public void onCreate()
    {
        Toast.makeText(this, "New service was created", Toast.LENGTH_LONG).show();

        //Declares bluetooth adapter object
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //If bluetooth isn't enabled, or there isn't a connected device, notify user
        if(!bluetoothAdapter.isEnabled() || bluetoothAdapter == null)
        {
            //Sends a broadcast
            Intent broad = new Intent("EVENT_SNACKBAR");
            LocalBroadcastManager.getInstance(this).sendBroadcast(broad);
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Toast.makeText(this, "Service started", Toast.LENGTH_LONG).show();

        //TODO: Put bluetooth listener here

        //Checks is bluetooth is enabled, and is connected to a device
        if(bluetoothAdapter.isEnabled() && bluetoothAdapter != null)
        {
            //Opens bluetooth up for receiving
            try { openBlueTooth(); } catch(IOException e) {  }
        }

        //This makes sure the event continues running
        return START_STICKY;
    }

    //Runs on destruction of event
    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_LONG).show();
    }

    private void openBlueTooth() throws IOException
    {
        //Gets paired devices
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if(!pairedDevices.isEmpty())
        {
            bluetoothDevice = pairedDevices.iterator().next();

            //Gets first bluetooth device in paired devices list (hopefully it's just one)
            socket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(bluetoothDevice.getUuids()[0].getUuid());


            //Gets input stream from bluetooth socket
            input = socket.getInputStream();

            //TODO: Get any bluetooth message and process it
            //Start with just printing the message
        }
    }
}