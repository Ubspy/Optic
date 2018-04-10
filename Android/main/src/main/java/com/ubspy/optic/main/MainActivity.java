package com.ubspy.optic.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    static ArrayList<Display> displays;

    BroadcastReceiver messageReceiver;
    File displaysFile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO: Test for displays file, if so load it's contents into displays array

        //Floating add button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //Sets image
        fab.setImageResource(R.drawable.ic_add_white_24dp);

        //Sets background color
        fab.setBackgroundTintList(ColorStateList.valueOf(getColor(com.ubspy.optic.R.color.colorPrimary)));

        //Sets click event
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, BasicDisplay.class));
            }
        });

        //Sets up message receiver
        messageReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                notifyBluetooth();
            }
        };

        //Opens displays file
        displaysFile = new File(MainActivity.this.getFilesDir(), "displays");

        //Reads file if it exists
        if(displaysFile.exists() && displaysFile.length() != 0)
        {
            try
            {
                //Reads the file
                FileInputStream inputStream = openFileInput("displays");
                ObjectInputStream objectStream = new ObjectInputStream(inputStream);

                //Stores the persistent arraylist in local storage
                displays = (ArrayList<Display>) objectStream.readObject();

                //Closes streams
                objectStream.close();
                inputStream.close();

                for(Display currentDisplay : displays)
                {
                    Log.v("FILE", (currentDisplay.getTitle() + " " + currentDisplay.getContents()));
                }

            }
            catch(Exception e) {  }
        }
        else
        {
            try
            {
                //Creates the file
                displaysFile.createNewFile();

                Log.v("FILE", "OOPS NO FILE");

                //Sets the arraylist to an empty arraylist
                displays = new ArrayList<>();
            }
            catch(IOException e) {  }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void notifyBluetooth()
    {
        //Displays text at the bottom, saying that bluetooth isn't properly connected
        Snackbar.make(this.findViewById(android.R.id.content),
                "Bluetooth isn't properly configured, please check your settings", Snackbar.LENGTH_LONG)
                .setAction(R.string.action_settings, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        //Opens bluetooth settings on action click
                        startActivityForResult(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS), 0);
                    }
                }).show();
    }

    //You need a context, because openFileOutput can't be referenced statically, and it's really really stupid
    public static void addDisplay(Display display, Context context)
    {
        displays.add(display);

        try
        {
            //Overwrites the whole file since we already have the whole persistent data stored in the arraylist
            FileOutputStream outputStream = context.openFileOutput("displays", Context.MODE_PRIVATE);
            ObjectOutputStream objectStream = new ObjectOutputStream(outputStream);

            //Writes the object
            objectStream.writeObject(displays);

            //LOG TODO: REMOVE
            for(Display currentDisplay : displays)
            {
                Log.v("FILE WRITE", (currentDisplay.getTitle() + " " + currentDisplay.getContents()));
            }

            //Closes the streams
            objectStream.close();
            outputStream.close();
        }
        catch(IOException e) {  }
    }
}