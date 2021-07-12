package com.baokiiin.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.baokiiin.myapplication.R;
import com.baokiiin.myapplication.adapter.ViewPagerAdapter;
import com.baokiiin.myapplication.manager.AdsManager;
import com.baokiiin.myapplication.manager.AdsManagerCallback;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdsManagerCallback {


    //---------------------------------------- create value -----------------------------------------------
    ViewPager2 viewPager;
    TabLayout tabLayout;
    AdView adView;
    ViewPagerAdapter viewPagerAdapter;
    AdsManager adsManager;
    int countSelectTab = 0;


    //---------------------------------------- onCreate --------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unitView();
        setup();
        mediatorViewpager();
        clickView();

    }

    //---------------------------------------- callback --------------------------------------------

    @Override
    public void loadAdsCallback(AdRequest adRequest) {
        adView.loadAd(adRequest);
    }

    @Override
    public void startActivityCallback() {
    }
    @Override
    public void loadAdsNativeCallback(NativeTemplateStyle style, NativeAd nativeAd) {

    }


    //---------------------------------------- func -----------------------------------------------
    void clickView() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                countSelectTab++;
                if(countSelectTab == 3){
                    adsManager.showAdsInterstitial();
                    countSelectTab = 0;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    void unitView() {
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        adView = findViewById(R.id.adView);


    }

    void setup() {
        adsManager = new AdsManager(this, this);
        adsManager.setup();
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(OneFragment.newInstance(adsManager));
        fragmentArrayList.add(TwoFragment.newInstance(adsManager));
        viewPagerAdapter = new ViewPagerAdapter(fragmentArrayList, this);
    }

    void mediatorViewpager() {
        viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("OneFragment");
                            break;
                        case 1:
                            tab.setText("TwoFragment");
                            break;
                    }

                }
        ).attach();
    }
}