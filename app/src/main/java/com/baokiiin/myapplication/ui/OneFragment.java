package com.baokiiin.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baokiiin.myapplication.R;
import com.baokiiin.myapplication.adapter.ItemAdapter;
import com.baokiiin.myapplication.manager.AdsManager;
import com.baokiiin.myapplication.model.Item;

import java.util.ArrayList;
import java.util.Random;

import static com.baokiiin.myapplication.utils.Utils.randItem;


public class OneFragment extends Fragment {


    public OneFragment(AdsManager adsManager) {
        this.adsManager = adsManager;
    }

    public static OneFragment newInstance(AdsManager adsManager) {
        return new OneFragment(adsManager);
    }

    //---------------------------------------- create value ----------------------------------------
    ItemAdapter itemAdapter;
    RecyclerView recyclerView;
    AdsManager adsManager;

    //---------------------------------------- onCreate --------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_one, container, false);
        unitView(view);
        setUpRecycleView();
        loadData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    //---------------------------------------- func --------------------------------------------
    void unitView(View view){
        recyclerView = view.findViewById(R.id.rv_one_fragment);
        itemAdapter = new ItemAdapter(adsManager);
    }
    void setUpRecycleView(){
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    void loadData(){
        ArrayList<Item> items = new ArrayList<>();
        for(int i=0;i<20;i++)
            items.add(new Item("Quocbao "+i,i*100));
        itemAdapter.randItem = randItem(10);
        itemAdapter.submitList(items);
    }

}