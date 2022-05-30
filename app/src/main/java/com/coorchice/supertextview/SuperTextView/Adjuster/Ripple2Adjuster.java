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
 * Last modified 17-11-16 上午12:36
 */

package com.coorchice.supertextview.SuperTextView.Adjuster;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.coorchice.library.SuperTextView;

/**
 * Project Name:SuperTextView
 * Notes:
 *
 * @author Admin
 */

public class Ripple2Adjuster extends SuperTextView.Adjuster {
    private static final float DEFAULT_RADIUS = 50;

    private PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private int rippleColor = Color.parseColor("#ce938d");
    private float x = -1;
    private float y = -1;
    private Paint paint;
    private float density;
    private float radius = DEFAULT_RADIUS;
    private RectF rectF = new RectF();
    private float velocity = 2f;
    private Path solidPath;
    private RectF solidRectF;

    private Bitmap src;
    private Canvas srcCanvas;
    private Bitmap dst;
    private Canvas dstCanvas;
    private Bitmap render;
    private Canvas renderCanvas;


    public Ripple2Adjuster(int rippleColor) {
        this.rippleColor = rippleColor;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(rippleColor);
    }


    @Override
    protected void adjust(SuperTextView v, Canvas canvas) {
        int width = v.getWidth();
        int height = v.getHeight();
        if (renderCanvas == null) {
            render = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            renderCanvas = new Canvas(render);
        }
        if (srcCanvas == null) {
            src = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            srcCanvas = new Canvas(src);
        }
        if (dstCanvas == null) {
            dst = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            dstCanvas = new Canvas(dst);
        }
        if (density == 0) {
            density = v.getResources().getDisplayMetrics().density;
        }
        if (x == -1) {
            x = width / 2;
        }
        if (y == -1) {
            x = height / 2;

        }
        if (radius < ((float) width) * 1.1) {
            radius = (radius + velocity);
        } else {
//      v.stopAnim();
        }
        rectF.setEmpty();
        rectF.set(0, 0, width, height);
        if (solidRectF == null) {
            solidRectF = new RectF();
        } else {
            solidRectF.setEmpty();
        }
        if (solidPath == null) {
            solidPath = new Path();

            float strokeWidth = v.getStrokeWidth();
            solidRectF.set(strokeWidth, strokeWidth, v.getWidth() - strokeWidth,
                    v.getHeight() - strokeWidth);
            solidPath.addRoundRect(solidRectF, v.getCorners(), Path.Direction.CW);

            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            srcCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            srcCanvas.drawPath(solidPath, paint);
        } else {
            solidPath.reset();
        }


        paint.setColor(rippleColor);
        dstCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        dstCanvas.drawCircle(x, y, radius * density, paint);

        renderCanvas.drawBitmap(src, 0, 0, paint);
        paint.setXfermode(xfermode);
        renderCanvas.drawBitmap(dst, 0, 0, paint);
        paint.setXfermode(null);
        canvas.drawBitmap(render, 0, 0, paint);
    }

    @Override
    public boolean onTouch(SuperTextView v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                radius = DEFAULT_RADIUS;
                v.setAutoAdjust(true);
                v.startAnim();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                radius = 0;
                break;
        }
        return true;
    }
}
