package com.playground.vr.vr_playground.pages.adapter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.playground.vr.vr_playground.R;
import com.playground.vr.vr_playground.dataset.VrImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by petnagy on 2017. 09. 01..
 */

public class VrListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = VrListAdapter.class.getSimpleName();

    private List<VrImage> imageList;

    private LifecycleRegistry lifecycleRegistry;

    private Context context;

    public VrListAdapter(Context context, LifecycleRegistry lifecycleRegistry) {
        this.context = context;
        this.lifecycleRegistry = lifecycleRegistry;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.vr_list_item, parent, false);
        VrImageViewHolder holder = new VrImageViewHolder(view);
        lifecycleRegistry.addObserver(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VrImage image = imageList.get(position);
        ((VrImageViewHolder) holder).loadImage(image);
    }

    @Override
    public int getItemCount() {
        return imageList == null ? 0 : imageList.size();
    }

    public void setImageList(List<VrImage> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    public VrImage getItemByPosition(int adapterPosition) {
        return imageList == null ? null : imageList.get(adapterPosition);
    }

    public void removeItem(int adapterPosition) {
        notifyItemRemoved(adapterPosition);
    }

    public class VrImageViewHolder extends RecyclerView.ViewHolder implements LifecycleObserver {

        private VrPanoramaView vrImage;

        private ImageLoaderTask task;

        private VrImageViewHolder(View view) {
            super(view);
            vrImage = view.findViewById(R.id.vr_image);
            vrImage.setEventListener(new ActivityEventListener());
        }

        public void loadImage(VrImage imageData) {
            task = new ImageLoaderTask();
            task.execute(imageData);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause() {
            vrImage.pauseRendering();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            vrImage.resumeRendering();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {
            vrImage.shutdown();
            if (task != null) {
                task.cancel(true);
            }
        }

        private class ImageLoaderTask extends AsyncTask<VrImage, Void, Boolean> {

            @Override
            protected Boolean doInBackground(VrImage... fileInformation) {
                VrPanoramaView.Options options = new VrPanoramaView.Options();  // It's safe to use null VrPanoramaView.Options.
                InputStream istr;
                VrImage imageData = fileInformation[0];
                if (imageData.isAsset()) {
                    AssetManager assetManager = context.getAssets();
                    try {
                        istr = assetManager.open(imageData.getFileName());
                        options.inputType = imageData.getInputType();
                    } catch (IOException e) {
                        Log.e(TAG, "Could not decode default bitmap: " + e);
                        return false;
                    }
                } else {
                    try {
                        istr = new FileInputStream(new File(imageData.getUri().getPath()));
                        options.inputType = imageData.getInputType();
                    } catch (IOException e) {
                        Log.e(TAG, "Could not load file: " + e);
                        return false;
                    }
                }

                //Temperary solution copied from Cardboard SDK demo
                vrImage.loadImageFromBitmap(BitmapFactory.decodeStream(istr), options);
                try {
                    istr.close();
                } catch (IOException e) {
                    Log.e(TAG, "Could not close input stream: " + e);
                }

                return true;
            }
        }
    }

    private static class ActivityEventListener extends VrPanoramaEventListener {

        private boolean loadImageSuccessful;

        /**
         * Called by pano widget on the UI thread when it's done loading the image.
         */
        @Override
        public void onLoadSuccess() {
            loadImageSuccessful = true;
        }

        /**
         * Called by pano widget on the UI thread on any asynchronous error.
         */
        @Override
        public void onLoadError(String errorMessage) {
            loadImageSuccessful = false;
            Log.e("VrListAdapter", "Error loading pano: " + errorMessage);
        }
    }
}
