package com.baokiiin.myapplication.manager;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.nativead.NativeAd;

public interface AdsManagerCallback {
    void loadAdsCallback(AdRequest adRequest);

    void startActivityCallback();

    void loadAdsNativeCallback(NativeTemplateStyle style, NativeAd nativeAd);
}
