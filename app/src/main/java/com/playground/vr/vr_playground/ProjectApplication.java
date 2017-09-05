package com.playground.vr.vr_playground;

import android.net.Uri;
import android.os.AsyncTask;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.playground.vr.vr_playground.dataset.VrImage;
import com.playground.vr.vr_playground.dataset.VrImageDao;
import com.playground.vr.vr_playground.inject.component.DaggerApplicationComponent;
import com.playground.vr.vr_playground.service.PreferenceService;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by petnagy on 2017. 09. 01..
 */

public class ProjectApplication extends DaggerApplication {

    @Inject
    PreferenceService preferenceService;

    @Inject
    VrImageDao vrImageDao;

    @Override
    public void onCreate() {
        super.onCreate();

        if (preferenceService.isFirstLaunch()) {
            VrImage vrImage1 = new VrImage(Uri.EMPTY, VrPanoramaView.Options.TYPE_MONO, "boscolo-01.jpg", true);
            VrImage vrImage2 = new VrImage(Uri.EMPTY, VrPanoramaView.Options.TYPE_MONO, "boscolo-02.jpg", true);
            VrImage vrImage3 = new VrImage(Uri.EMPTY, VrPanoramaView.Options.TYPE_MONO, "boscolo-03.jpg", true);
            VrImage vrImage4 = new VrImage(Uri.EMPTY, VrPanoramaView.Options.TYPE_MONO, "boscolo-04.jpg", true);
            VrImage vrImage5 = new VrImage(Uri.EMPTY, VrPanoramaView.Options.TYPE_MONO, "boscolo-05.jpg", true);
            VrImage vrImage6 = new VrImage(Uri.EMPTY, VrPanoramaView.Options.TYPE_MONO, "boscolo-06.jpg", true);
            new SaveAsyncTask().execute(vrImage1, vrImage2, vrImage3, vrImage4, vrImage5, vrImage6 );
            preferenceService.setFirstLaunch();
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);
    }

    private class SaveAsyncTask extends AsyncTask<VrImage, Void, Void> {

        @Override
        protected Void doInBackground(VrImage... vrImages) {
            for (VrImage image : vrImages) {
                vrImageDao.insertVrImage(image);
            }
            return null;
        }
    }
}
