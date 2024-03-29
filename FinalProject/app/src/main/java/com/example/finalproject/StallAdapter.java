package com.example.finalproject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class StallAdapter extends RealmRecyclerViewAdapter<Stall, StallAdapter.MyViewHolder> {
    StallList context;

    public StallAdapter(StallList context, @Nullable RealmResults<Stall> data)
    {
        super(data, true); // autoupdate true
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create row view / viewHolder
        // only a limited number will be created
        View view = context.getLayoutInflater().inflate(R.layout.activity_stall_row, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // copy from the result object to the viewHolder for display
        Stall stall = getItem(position);

        // fill in the ViewHolder
        // non-strings need to be converted to String via String.valueOf()
        holder.stallName.setText(stall.getStallName());

        holder.delete.setTag(stall);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView stallName;
        Button delete;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            stallName = itemView.findViewById(R.id.stallNameRow);
            delete = itemView.findViewById(R.id.deleteStall);

            delete.setOnClickListener(deleteListener);
        }
    }
    private View.OnClickListener deleteListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
//            Tag is attaching arbitrary object to an existing object
            Stall stall = (Stall) v.getTag();
//            System.out.println(u);
            context.delete(stall);
        }
    };
}





