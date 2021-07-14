package com.baokiiin.myapplication.utils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.baokiiin.myapplication.R;
import com.baokiiin.myapplication.ui.CustomDiaLogFragment;

import java.util.ArrayList;
import java.util.Random;

public class Utils {
    public static final int ADS_ITEM = 1;
    public static final int ITEM = 0;
    public static final int HEADER_ITEM = 100;
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    private static final String TAG= "custom";


    public static ArrayList<Integer> randItem(int max) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(-1);
        int indexTemp = 1;
        do {
            Random rand = new Random();
            int posRand = rand.nextInt(max) + 1;
            if (posRand % 3 == 0 && Math.abs(posRand * 2 - temp.get(indexTemp - 1)) > 1) {
                temp.add(posRand * 2);
                indexTemp++;
            }
        } while (temp.size() <= 2);
        temp.sort(Integer::compareTo);
        temp.set(2, temp.get(2) + 1);
        return temp;
    }

    public static void showDialog(FragmentActivity fragmentActivity, String title, Boolean... isCancel) {

        boolean typeDialog = false;
        if (isCancel.length >0)
            typeDialog = isCancel[isCancel.length-1];

        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putBoolean(TYPE, typeDialog);
        CustomDiaLogFragment diaLogFragment = new CustomDiaLogFragment();
        diaLogFragment.setArguments(bundle);
        diaLogFragment.show(fragmentActivity.getSupportFragmentManager(), TAG);
    }

    public static void showPopupWindow(Context context, View view){
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            ((TextView)view).setText(item.getTitle());
            return true;
        });
        popup.show();
    }
}
