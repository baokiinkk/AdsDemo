package com.baokiiin.myapplication.utils;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.baokiiin.myapplication.ui.CustomDiaLogFragment;

import java.util.ArrayList;
import java.util.Random;

public class Utils {
    public static final int ADS_ITEM = 1;
    public static final int ITEM = 0;
    public static final int HEADER_ITEM = 100;
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final int ONEFRAGMENT = 0;
    public static final int TWOFRAGMENT = 1;

    public static ArrayList<Integer> randItem(int max){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(-1);
        int indexTemp = 1;
        do{
            Random rand = new Random();
            int posRand = rand.nextInt(max)+1;
            if (posRand % 3 ==0 && Math.abs(posRand*2 - temp.get(indexTemp-1))>1) {
                temp.add(posRand*2);
                indexTemp++;
            }
        }while (temp.size()<=2);
        temp.sort(Integer::compareTo);
        temp.set(2,temp.get(2)+1);
        return temp;
    }
    public static void showDialog(FragmentActivity fragmentActivity,String title,int type){
        Bundle bundle = new Bundle();
        bundle.putString(TITLE,title);
        bundle.putInt(TYPE,type);
        CustomDiaLogFragment diaLogFragment = new CustomDiaLogFragment();
        diaLogFragment.setArguments(bundle);
        diaLogFragment.show(fragmentActivity.getSupportFragmentManager(),"custom");
    }
}
