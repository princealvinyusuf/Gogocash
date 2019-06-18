package com.uas.uas.activity;

import android.widget.Filter;
import java.util.ArrayList;

public class CustomFilter extends Filter {

    Adapter adapter;
    ArrayList<Notes> filterList;

    public CustomFilter(ArrayList<Notes> filterList,Adapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Notes> filteredNotes=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getName().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredNotes.add(filterList.get(i));
                }
            }

            results.count=filteredNotes.size();
            results.values=filteredNotes;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.notes= (ArrayList<Notes>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();

    }
}