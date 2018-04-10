package com.ubspy.optic.main;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BasicDisplay extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_display);

        //Gets button objects
        final Button doneButton = findViewById(R.id.doneButton);
        final Button cancelButton = findViewById(R.id.cancelButton);

        //Sets click listeners
        doneButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                TextInputLayout title = findViewById(R.id.titleInput);
                TextInputLayout contents = findViewById(R.id.contentsInput);

                //Creates new object to add to the display list
                MainActivity.addDisplay(new Display(title.getEditText().getText().toString(),
                        contents.getEditText().getText().toString()), getBaseContext());

                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
