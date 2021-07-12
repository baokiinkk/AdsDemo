package com.baokiiin.myapplication.adapter;


import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import com.baokiiin.myapplication.manager.AdsManager;
import com.baokiiin.myapplication.model.Item;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.baokiiin.myapplication.adapter.ItemAdapter.ADS_ITEM;
import static com.baokiiin.myapplication.adapter.ItemAdapter.HEADER_ITEM;
import static com.baokiiin.myapplication.adapter.ItemAdapter.ITEM;


public class ItemTwoAdapter extends
        ListAdapter<Item,ViewHolder> {
    AdsManager adsManager;
    ArrayList<Integer> randItem;
    public ItemTwoAdapter(AdsManager adsManager, ArrayList<Integer> randItem) {
        super(new ItemDIff());
        this.adsManager = adsManager;
        this.randItem = randItem;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+randItem.size()-1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount()-1)
            return HEADER_ITEM;
        else if (randItem.contains(position))
            return ADS_ITEM;
        return ITEM;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(getItem(getRealPosition(position)),getItemViewType(position),adsManager);
    }

    private int getRealPosition(int position) {
        if(position > randItem.get(1) && position<=randItem.get(2))
            return position - 1;
        else if( position > randItem.get(2))
            return position -2;
        else
            return position;
    }
}


