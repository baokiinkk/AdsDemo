package com.baokiiin.myapplication.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baokiiin.myapplication.R;
import com.baokiiin.myapplication.adapter.ItemTwoAdapter;
import com.baokiiin.myapplication.manager.AdsManager;
import com.baokiiin.myapplication.model.Item;

import java.util.ArrayList;
import java.util.Random;

public class TwoFragment extends Fragment {

    public TwoFragment(AdsManager adsManager) {
        this.adsManager = adsManager;
    }

    public static TwoFragment newInstance(AdsManager adsManager) {
        return new TwoFragment(adsManager);
    }

    //---------------------------------------- create value ----------------------------------------
    AdsManager adsManager;
    ItemTwoAdapter itemAdapter;
    RecyclerView recyclerView;
    GridLayoutManager manager;


    //---------------------------------------- onCreate --------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        unitView(view);
        setUpRecycleView();
        loadData();
        return view;
    }


    //---------------------------------------- func --------------------------------------------
    ArrayList<Integer> randItem() {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(-2);
        int indexTemp = 1;
        do {
            Random rand = new Random();
            int posRand = rand.nextInt(10) + 1;
            if (posRand % 3 ==0 && Math.abs(posRand*2 - temp.get(indexTemp-1))>1) {
                temp.add(posRand*2);
                indexTemp++;
            }

        } while (temp.size() <= 2);
        temp.sort(Integer::compareTo);
        temp.set(2,temp.get(2)+1);
        return temp;
    }

    void unitView(View view) {
        ArrayList<Integer> temp = randItem();
        recyclerView = view.findViewById(R.id.rv_two_fragment);
        itemAdapter = new ItemTwoAdapter(adsManager, temp);
        manager = new GridLayoutManager(getContext(), 2);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (temp.contains(position) || position == itemAdapter.getItemCount() - 1)
                    return 2;
                else
                    return 1;
            }
        });
    }

    void setUpRecycleView() {
        recyclerView.setAdapter(itemAdapter);

        recyclerView.setLayoutManager(manager);
    }

    void loadData() {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            items.add(new Item("Quocbao Two " + i, i * 100));
        items.add(new Item("",0));
        itemAdapter.submitList(items);
    }
}