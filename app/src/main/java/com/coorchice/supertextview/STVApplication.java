package com.coorchice.supertextview;

import android.app.Application;

import com.coorchice.library.ImageEngine;
import com.coorchice.supertextview.SuperTextView.ImageEngine.GlideEngine;
import com.coorchice.supertextview.SuperTextView.ImageEngine.PicassoEngine;

/**
 * Project Name:SuperTextView
 * Notes:
 *
 * @author Admin
 */

public class STVApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//    ImageEngine.install(new GlideEngine(this));
//    ImageEngine.install(new PicassoEngine(this));
    }
}
