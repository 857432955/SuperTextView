package com.coorchice.supertextview;

import android.content.Context;
import android.util.AttributeSet;

import com.coorchice.library.SuperTextView;

/**
 * @author coorchice
 * @date 2019/03/19
 */
public class MySuperTextView extends SuperTextView {


    public MySuperTextView(Context context) {
        super(context);
    }

    public MySuperTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySuperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public SuperTextView setUrlImage(String url, boolean asBackground) {
        return super.setUrlImage(url, asBackground);
    }
}
