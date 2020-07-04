package com.example.bonvoyaide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bonvoyaide.R;
import com.example.bonvoyaide.models.Country;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private List<Country> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.country);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CountryAdapter(List<Country> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.country, parent, false);

        // Return a new holder instance
        CountryAdapter.MyViewHolder viewHolder = new CountryAdapter.MyViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CountryAdapter.MyViewHolder holder, int position) {
        // Get the data model based on position
        String country = mDataset.get(position).getCountry();

        // Set item views based on your views and data model
        TextView textView = holder.textView;
        textView.setText(country);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
