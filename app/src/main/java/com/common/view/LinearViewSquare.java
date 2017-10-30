package com.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * @author VaViAn Labs.
 */
public class LinearViewSquare extends LinearLayout {

    public LinearViewSquare(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public LinearViewSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearViewSquare(Context context) {
        super(context);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
};