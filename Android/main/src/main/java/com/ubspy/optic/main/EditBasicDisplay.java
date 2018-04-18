package com.ubspy.optic.main;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditBasicDisplay extends AppCompatActivity
{
    static Display currentDisplay;
    static int displayIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_basic_display);

        //Gets button objects
        final Button doneButton = findViewById(R.id.doneButton);
        final Button cancelButton = findViewById(R.id.cancelButton);

        //Gets text objects
        final TextInputLayout title = findViewById(R.id.titleInput);
        final TextInputLayout contents = findViewById(R.id.contentsInput);

        //Sets text for text layouts
        title.getEditText().setText(currentDisplay.getTitle(), null);
        contents.getEditText().setText(currentDisplay.getContents(), null);

        //Sets click listeners
        doneButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                //Sets new text elements
                currentDisplay.setTitle(title.getEditText().getText().toString());
                currentDisplay.setContents(contents.getEditText().getText().toString());

                //Edit current display
                MainActivity.editDisplay(currentDisplay, displayIndex, getBaseContext());

                //Closes the activity
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

    public static void setCurrentDisplay(Display display, int index)
    {
        currentDisplay = display;
        displayIndex = index;
    }
}
