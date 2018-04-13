package com.ubspy.optic.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList<Display> displayList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView titleView, contentsView;

        public MyViewHolder(View view)
        {
            super(view);
            titleView = view.findViewById(R.id.cardTitle);
            contentsView = view.findViewById(R.id.cardContents);
        }
    }

    public DisplayAdapter(Context context, ArrayList<Display> displayList)
    {
        this.context = context;
        this.displayList = displayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //Creates a new view containing all items in the display_card xml file
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_card, parent, false);

        //Returns a new custom view holder for the items
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        //Gets display object
        Display display = displayList.get(position);

        //Sets text elements of the view
        holder.titleView.setText(display.getTitle());
        holder.contentsView.setText(display.getContents());
    }

    @Override
    public int getItemCount()
    {
        return displayList.size();
    }
}
