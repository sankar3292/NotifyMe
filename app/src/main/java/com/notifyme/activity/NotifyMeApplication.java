package com.notifyme.activity;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

//import com.squareup.leakcanary.LeakCanary;

//import com.squareup.leakcanary.LeakCanary;


public class NotifyMeApplication extends MultiDexApplication
{

//    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//    private static final String TWITTER_KEY = "4UZL5ZREfLKXtJBVrIhSOnnko";
//    private static final String TWITTER_SECRET = "DSKSy85UJq9FZohkwJg0bAyji7TxNdb0rsCFmm6rZpNkQaEIX4";




    @Override
    public void onCreate()

    {
        super.onCreate();
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));

//
//        if (LeakCanary.isInAnalyzerProcess(this))
//        {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);






    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
