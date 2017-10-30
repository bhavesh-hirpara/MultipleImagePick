package com.admision.utils;

import android.os.Environment;

import java.io.File;

public class Constant {
    public static final String FOLDER_NAME = ".diamfair";
    public static final String CACHE_DIR = ".diamfair/Cache";

    public static final String TMP_DIR = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + File.separator
            + FOLDER_NAME + "/tmp";

    public static final String PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + "" + FOLDER_NAME;

    public static final String FOLDER_RIDEINN_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + File.separator
            + "DiamFair";


    public static final String API_KEY = "123";

    public static final String USER_LATITUDE = "lat";
    public static final String USER_LONGITUDE = "longi";

    public static final String LOGIN_INFO = "login_info";
    public static final String MASTER_DATA = "master_data";
    public static final String COUNTRY = "country";

    public static final String ROLE = "role";
    public static final int ROLE_SELLER = 1;
    public static final int ROLE_BUYER = 2;
    public static final int ROLE_GENERAL = 3;

    public static final String FINISH_ACTIVITY = "finish_activity";

    public static final String PROFILE = "profile";

    public static final int ANDROID_TYPE = 1;

    public static final int TIMEOUT = 5 * 60 * 1000;

    //
    public static final String LOCATION_UPDATED = "location_updated";

    public static final int REQ_CODE_SETTING = 555;

    public static final String TAG_ADD = "add";
    public static final String TAG_CANCEL = "cancel";
    public static final String TAG_REMOVE = "remove";
    public static final String TAG_REJECT = "reject";
    public static final String TAG_BLOCK = "block";
    public static final String TAG_UNBLOCK = "unblock";
    public static final String TAG_ACCEPT = "accept";
}
