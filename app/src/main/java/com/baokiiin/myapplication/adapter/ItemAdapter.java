package com.baokiiin.myapplication.adapter;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.baokiiin.myapplication.R;
import com.baokiiin.myapplication.manager.AdsManager;
import com.baokiiin.myapplication.manager.AdsManagerCallback;
import com.baokiiin.myapplication.model.Item;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.nativead.NativeAd;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class ItemAdapter extends
        ListAdapter<Item, ViewHolder> {
    public ArrayList<Integer> randItem;
    AdsManager adsManager;
    static final int ADS_ITEM = 1;
    static final int ITEM = 0;
    static final int HEADER_ITEM = 100;
    public ItemAdapter(AdsManager adsManager) {
        super(new ItemDIff());
        this.adsManager = adsManager;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+randItem.size()-1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return HEADER_ITEM;
        if (randItem.contains(position))
            return ADS_ITEM;
        return ITEM;
    }

    @Override
    public @NotNull ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
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

class ItemDIff extends DiffUtil.ItemCallback<Item> {

    @Override
    public boolean areItemsTheSame(@NonNull @NotNull Item oldItem, @NonNull @NotNull Item newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull @NotNull Item oldItem, @NonNull @NotNull Item newItem) {
        return oldItem == newItem;
    }
}

class ViewHolder extends
        RecyclerView.ViewHolder  {
    TemplateView adsTemp;
    public ViewHolder(View itemView) {
        super(itemView);
    }

    static ViewHolder from(ViewGroup parent,int type) {
        View view;
        if(type == 0)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        else if(type == 1)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ads, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ads_header, parent, false);
        return new ViewHolder(
                view
        );
    }

    void bind(Item item,int type,AdsManager adsManager) {
        if(type == 0){
            TextView name = itemView.findViewById(R.id.txtName);
            TextView price = itemView.findViewById(R.id.txtPrice);
            name.setText(item.getName());
            price.setText(item.getPrice());
        }
        else{
            adsTemp = itemView.findViewById(R.id.my_template);
            adsManager.showAdsNativeTemplates(adsTemp);
        }

    }
}


