package com.common.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.admision.utils.Utils;


/**
 * @author VaViAn Labs.
 */
public class CTextView extends AppCompatTextView {

    public CTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CTextView(Context context) {
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