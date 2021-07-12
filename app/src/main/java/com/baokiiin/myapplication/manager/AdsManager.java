package com.baokiiin.myapplication.manager;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.baokiiin.myapplication.R;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;

import org.jetbrains.annotations.NotNull;

public class AdsManager extends Application {
    private InterstitialAd mInterstitialAd;
    AdsManagerCallback adsCallback;
    Activity context;
    private static AdsManager singleton;

    public AdsManager(AdsManagerCallback adsCallback, Activity context) {
        this.adsCallback = adsCallback;
        this.context = context;
    }

    public AdsManager(){

    }

    public void setup() {
        MobileAds.initialize(context, initializationStatus -> {
        });
        loadAdmod();
        if (mInterstitialAd != null)
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    Log.d("quocbao", "Ad was dismissed.");
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull @NotNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    Log.d("quocbao", "Ad failed to show.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    Log.d("quocbao", "Ad showed fullscreen content.");
                    mInterstitialAd = null;
                }
            });
    }

    public void loadAdmod() {
        AdRequest adRequest = new AdRequest.Builder().build();
        adsCallback.loadAdsCallback(adRequest);
        InterstitialAd.load(
                context,
                "ca-app-pub-3940256099942544/1033173712",
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull @NotNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull @NotNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        mInterstitialAd = interstitialAd;
                    }
                }
        );
    }

    public void showAdsInterstitial() {
        adsCallback.startActivityCallback();
        if (mInterstitialAd != null) {
            mInterstitialAd.show(context);
            loadAdmod();
        }
    }

    public void showAdsNativeTemplates(TemplateView templateView) {
        AdLoader adLoader = new AdLoader.Builder(context, context.getString(R.string.ad_native_template))
                .forNativeAd(NativeAd -> {
                    NativeTemplateStyle styles = new NativeTemplateStyle.Builder()
                            .withMainBackgroundColor(new ColorDrawable(Color.WHITE)).build();
                    templateView.setNativeAd(NativeAd);
                    templateView.setStyles(styles);

                })
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

}
