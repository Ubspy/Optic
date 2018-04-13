package com.ubspy.optic.main;

import java.io.Serializable;

//Implements serializable for storing objects in files
public class Display implements Serializable
{
    String slideTitle, slideContents;

    public Display(String title, String contents)
    {
        this.slideTitle = title;
        this.slideContents = contents;
    }

    public String getTitle()
    {
        return this.slideTitle;
    }

    public String getContents()
    {
        return this.slideContents;
    }

    public void setTitle(String title)
    {
        this.slideTitle = title;
    }

    public void setContents(String contents)
    {
        this.slideContents = contents;
    }
}
