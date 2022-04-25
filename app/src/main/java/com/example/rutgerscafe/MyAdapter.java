package com.example.rutgerscafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> flavors;
    private Context context;
    private OnRVListener mOnRVListener;

    public MyAdapter(List<String> flavors, Context context, OnRVListener onRVListener) {
        this.flavors = flavors;
        this.context = context;
        this.mOnRVListener = onRVListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donut_flavor_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view, mOnRVListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.donut_flavorName.setText(flavors.get(position));
    }

    @Override
    public int getItemCount() {
        return flavors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView donut_flavorName;
        ConstraintLayout parentLayout;
        OnRVListener onRVListener;

        public MyViewHolder(@NonNull View itemView, OnRVListener onRVListener) {
            super(itemView);
            donut_flavorName = itemView.findViewById(R.id.donut_flavorName);
            parentLayout = itemView.findViewById(R.id.donutFlavorLayout);
            this.onRVListener = onRVListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRVListener.onRVClick(getAbsoluteAdapterPosition());
        }
    }
}
