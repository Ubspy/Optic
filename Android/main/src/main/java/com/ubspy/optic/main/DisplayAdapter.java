package com.ubspy.optic.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

//This class manages the views for each element (view holder)
public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.MyViewHolder>
{
    //Private version of base context and the displayList array list
    private Context context;
    private ArrayList<Display> displayList;

    //Custom view holder class
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        //Holds text views
        public TextView titleView, contentsView;

        //Constructor, creates a new view holder, and sets the text views to their templates
        public MyViewHolder(View view)
        {
            super(view);
            titleView = view.findViewById(R.id.cardTitle);
            contentsView = view.findViewById(R.id.cardContents);
        }
    }

    //Constructor, sets context and display list
    public DisplayAdapter(Context context, ArrayList<Display> displayList)
    {
        this.context = context;
        this.displayList = displayList;
    }

    //Runs on creation, basically contructor
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //Creates a new view containing all items in the display_card xml file
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_card, parent, false);

        //Returns a new custom view holder for the items
        return new MyViewHolder(itemView);
    }

    //For each view holder
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        //Gets display object
        Display display = displayList.get(position);

        //Sets text elements of the view
        holder.titleView.setText(display.getTitle());
        holder.contentsView.setText(display.getContents());
    }

    //Gets size of view holders to make
    @Override
    public int getItemCount()
    {
        return displayList.size();
    }
}
