package com.playground.vr.vr_playground.pages;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.playground.vr.vr_playground.R;
import com.playground.vr.vr_playground.dataset.VrImage;
import com.playground.vr.vr_playground.dataset.VrImageDao;
import com.playground.vr.vr_playground.pages.view.MainActivityView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements LifecycleRegistryOwner {

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    MainActivityView view;

    @Inject
    VrImageDao vrImageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view.setLifecycleOwner(this);
        getLifecycle().addObserver(view);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
