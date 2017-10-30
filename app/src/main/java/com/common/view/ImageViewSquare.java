package com.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * @author VaViAn Labs.
 */
public class ImageViewSquare extends ImageView {

    public ImageViewSquare(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public ImageViewSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewSquare(Context context) {
        super(context);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
};