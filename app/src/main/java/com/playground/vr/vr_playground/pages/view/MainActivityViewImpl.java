package com.playground.vr.vr_playground.pages.view;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.playground.vr.vr_playground.R;
import com.playground.vr.vr_playground.dataset.VrImage;
import com.playground.vr.vr_playground.dataset.VrImageDao;
import com.playground.vr.vr_playground.pages.adapter.VrListAdapter;

import java.util.List;

/**
 * Created by petnagy on 2017. 09. 01..
 */

public class MainActivityViewImpl implements MainActivityView {

    private VrListAdapter adapter;

    private VrImageDao vrImageDao;

    private LifecycleOwner lifecycleOwner;

    public MainActivityViewImpl(VrListAdapter adapter, VrImageDao vrImageDao) {
        this.adapter = adapter;
        this.vrImageDao = vrImageDao;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onCreate(LifecycleOwner source) {
        Activity activity = (Activity) source;
        RecyclerView vrList = activity.findViewById(R.id.vr_list);
        vrList.setLayoutManager(new GridLayoutManager(activity, 1));
        vrList.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL));
        vrList.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(vrList);
    }

    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void loadImages() {
        LiveData<List<VrImage>> imageList = vrImageDao.getAllVrImage();
        imageList.observe(lifecycleOwner, new Observer<List<VrImage>>() {
            @Override
            public void onChanged(@Nullable List<VrImage> vrImages) {
                adapter.setImageList(vrImages);
            }
        });
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int adapterPosition = viewHolder.getAdapterPosition();
            VrImage vrImage = adapter.getItemByPosition(adapterPosition);
            new DeleteAsyncTask().execute(vrImage);
            adapter.removeItem(adapterPosition);
        }
    };

    private class DeleteAsyncTask extends AsyncTask<VrImage, Void, Void> {

        @Override
        protected Void doInBackground(VrImage... vrImages) {
            vrImageDao.deleteImageData(vrImages[0]);
            return null;
        }
    }
}
