/*
 * Copyright (C) 2017 CoorChice <icechen_@outlook.com>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * <p>
 * Last modified 17-8-2 上午11:08
 */

package com.coorchice.supertextview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.coorchice.library.SuperTextView;
import com.coorchice.library.utils.track.Event;
import com.coorchice.library.utils.track.TimeEvent;
import com.coorchice.library.utils.track.Tracker;
import com.coorchice.supertextview.SuperTextView.Adjuster.MoveEffectAdjuster;
import com.coorchice.supertextview.SuperTextView.Adjuster.Ripple2Adjuster;
import com.coorchice.supertextview.SuperTextView.Adjuster.RippleAdjuster;
import com.coorchice.supertextview.Utils.LogUtils;

/**
 * @author Admin
 */
public class SecondActivity extends Activity {

    private SuperTextView btn;
    private SuperTextView stv_2;
    private SuperTextView stv_1;
    private SuperTextView stv_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn = (SuperTextView) findViewById(R.id.btn);

//    btn.setAutoAdjust(true);
        btn.addAdjuster(new RippleAdjuster(getResources().getColor(R.color.opacity_5_a58fed)));

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.e("onTouch");
                return false;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("onClick");
            }
        });

        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ListActivity.class));
            }
        });

        stv_2 = (SuperTextView) findViewById(R.id.stv_2);
        stv_2.addAdjuster(new MoveEffectAdjuster().setOpportunity(SuperTextView.Adjuster.Opportunity.BEFORE_TEXT));
        stv_2.addAdjuster(new Ripple2Adjuster(getResources().getColor(R.color.opacity_9_a58fed)));
        stv_2.setAutoAdjust(true).startAnim();
        Tracker stv_2_tracker = new Tracker("stv_2");
        stv_2_tracker.addWatcher(new Tracker.Watcher<TimeEvent>(Event.OnDrawSolidEnd) {
            @Override
            public void onEvent(TimeEvent event) {
                LogUtils.e(Event.OnDrawSolidEnd + " = " + event.time);
            }
        });
        stv_2_tracker.addWatcher(new Tracker.Watcher<TimeEvent>(Event.OnCreateDrawableBackgroundShaderEnd) {
            @Override
            public void onEvent(TimeEvent event) {
                LogUtils.e(Event.OnCreateDrawableBackgroundShaderEnd + " = " + event.time);
            }
        });
        stv_2_tracker.addWatcher(new Tracker.Watcher<TimeEvent>(Event.OnCopyDrawableBackgroundToShaderEnd) {
            @Override
            public void onEvent(TimeEvent event) {
                LogUtils.e(Event.OnCopyDrawableBackgroundToShaderEnd + " = " + event.time);
            }
        });
        stv_2_tracker.addWatcher(new Tracker.Watcher<TimeEvent>(Event.OnUpdateDrawableBackgroundShaderEnd) {
            @Override
            public void onEvent(TimeEvent event) {
                LogUtils.e(Event.OnUpdateDrawableBackgroundShaderEnd + " = " + event.time);
            }
        });
        stv_2_tracker.addWatcher(new Tracker.Watcher<TimeEvent>(Event.OnDrawDrawableBackgroundShaderEnd) {
            @Override
            public void onEvent(TimeEvent event) {
                LogUtils.e(Event.OnDrawDrawableBackgroundShaderEnd + " = " + event.time);
            }
        });
        stv_2_tracker.addWatcher(new Tracker.Watcher<TimeEvent>(Event.OnDrawDrawableBackgroundEnd) {
            @Override
            public void onEvent(TimeEvent event) {
                LogUtils.e(Event.OnDrawDrawableBackgroundEnd + " = " + event.time);
            }
        });
        stv_2_tracker.addWatcher(new Tracker.Watcher<TimeEvent>(Event.OnDrawDrawableEnd) {
            @Override
            public void onEvent(TimeEvent event) {
                LogUtils.e(Event.OnDrawDrawableEnd + " = " + event.time);
            }
        });
        stv_2_tracker.addWatcher(new Tracker.Watcher<TimeEvent>(Event.OnDrawAdjustersEnd) {
            @Override
            public void onEvent(TimeEvent event) {
                LogUtils.e(Event.OnDrawAdjustersEnd + " = " + event.time);
            }
        });
        stv_2_tracker.addWatcher(new Tracker.Watcher<TimeEvent>(Event.OnDrawEnd) {
            @Override
            public void onEvent(TimeEvent event) {
                LogUtils.e(Event.OnDrawEnd + " = " + event.time);
            }
        });
//        stv_2.setTracker(stv_2_tracker);

        SuperTextView stv_3 = (SuperTextView) findViewById(R.id.stv_3);
        stv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, GifListActivity.class));
            }
        });
        stv_3.setStateDrawable2Mode(SuperTextView.DrawableMode.LEFT);
        stv_3.setDrawable2PaddingLeft(150);

        stv_1 = (SuperTextView) findViewById(R.id.stv_1);
        stv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, TestActivity.class));
            }
        });

        stv_4 = (SuperTextView) findViewById(R.id.stv_4);
//        stv_4.setDrawable(R.drawable.gif_3);
        stv_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stv_4.setUrlImage("http://5b0988e595225.cdn.sohucs.com/images/20171128/bc6d5905a2524c2a8bde25e6a765aa2d.gif");
            }
        });
    }
}
