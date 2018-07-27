package com.luminous.pick;

import android.support.v7.app.AppCompatActivity;

import com.luminous.pick.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Kartum Infotech (Bhavesh Hirpara) on 09-Jul-18.
 */
public class BaseActivity extends AppCompatActivity {

    ImageLoader imageLoader;;

    public void initImageLoader() {
        try {
            imageLoader = Utils.initImageLoader(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseActivity getActivity() {
        return this;
    }


}
