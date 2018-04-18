package com.ubspy.optic.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
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

        //Holds menu view
        public ImageView menu;

        //Constructor, creates a new view holder, and sets the text views to their templates
        public MyViewHolder(View view)
        {
            super(view);
            titleView = view.findViewById(R.id.cardTitle);
            contentsView = view.findViewById(R.id.cardContents);
            menu = view.findViewById(R.id.cardMenu);
        }
    }

    class MenuItemClickListener implements PopupMenu.OnMenuItemClickListener
    {
        private Display display;
        private int index;

        public MenuItemClickListener(Display display, int index)
        {
            this.display = display;
            this.index = index;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            //Have events for clicking on the items here
            if(item.getItemId() == R.id.action_edit)
            {
                //Sets views display
                EditBasicDisplay.setCurrentDisplay(display, index);

                //Edit item activity here
                context.startActivity(new Intent(context, EditBasicDisplay.class));

                return true;
            }
            else if(item.getItemId() == R.id.action_delete)
            {
                MainActivity.deleteDisplay(this.display, context);
                return true;
            }
            else
            {
                return false;
            }
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
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        //Gets display object
        final Display display = displayList.get(position);

        //Sets text elements of the view
        holder.titleView.setText(display.getTitle());
        holder.contentsView.setText(display.getContents());

        //Sets click event for the menu
        holder.menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Shows popup menu for options
                showPopupMenu(view, display, position);
            }
        });
    }

    private void showPopupMenu(View view, Display display, int index)
    {
        //Inflate the menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_display, popup.getMenu());

        //Set click listener
        popup.setOnMenuItemClickListener(new MenuItemClickListener(display, index));

        //Show menu
        popup.show();
    }

    //Gets size of view holders to make
    @Override
    public int getItemCount()
    {
        return displayList.size();
    }
}
