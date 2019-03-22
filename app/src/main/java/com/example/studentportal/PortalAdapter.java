package com.example.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PortalAdapter extends RecyclerView.Adapter<PortalAdapter.ViewHolder> {

    private List<Portal> mPortals;
    public PortalAdapter(List<Portal> mPortals) { this.mPortals = mPortals; }


    @NonNull
    @Override
    public PortalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        PortalAdapter.ViewHolder viewHolder = new PortalAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PortalAdapter.ViewHolder viewHolder, int i) {
        Portal portal = mPortals.get(i);
        if(portal != null) viewHolder.textView.setText(portal.getTitle());
    }

    @Override
    public int getItemCount() {
        return mPortals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }







}
