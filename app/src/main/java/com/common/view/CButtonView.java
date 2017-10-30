package com.common.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.admision.utils.Utils;


/**
 * @author VaViAn Labs.
 */
public class CButtonView extends AppCompatButton {

    public CButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CButtonView(Context context) {
        super(context);
        init();
    }

    public void init() {
        if (!isInEditMode()) {
            try {
                setTypeface(Utils.getNormal(getContext()));
            } catch (Exception e) {
            }
        }
    }

};