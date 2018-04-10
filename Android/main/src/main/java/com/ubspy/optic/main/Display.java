package com.ubspy.optic.main;

import java.io.Serializable;

//Implements serializable for storing objects in files
public class Display implements Serializable
{
    String slideTitle, slideContents;

    public Display(String title, String contents)
    {
        slideTitle = title;
        slideContents = contents;
    }

    public String getTitle()
    {
        return this.slideTitle;
    }

    public String getContents()
    {
        return this.slideContents;
    }
}
