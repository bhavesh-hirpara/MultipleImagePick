package com.admision.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.admision.R;


public class AsyncProgressDialog extends Thread {

    Dialog pd;
    Activity context;

    public AsyncProgressDialog(Activity context) {
        this.context = context;
        pd = new Dialog(context);
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pd.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // pd.setContentView(R.layout.custom_progress);
        pd.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        pd.setContentView(
                context.getLayoutInflater().inflate(R.layout.custom_progress,
                        null),
                new ViewGroup.LayoutParams(Utils.getDeviceWidth(context), Utils
                        .getDeviceHeight(context)));
    }

    public AsyncProgressDialog(Activity context, String msg) {
        this.context = context;
        pd = new ProgressDialog(context);
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pd.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // pd.setContentView(R.layout.custom_progress);
        pd.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(
                context.getLayoutInflater().inflate(R.layout.custom_progress,
                        null),
                new ViewGroup.LayoutParams(Utils.getDeviceWidth(context), Utils
                        .getDeviceHeight(context)));
    }

    @Override
    public void run() {
        try {

            // preparing a looper on current thread
            // the current thread is being detected implicitly
            Looper.prepare();

            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    pd.show();
                }
            });

            Looper.loop();
            // Thread will start

        } catch (Exception t) {
            t.printStackTrace();
        }
    }

    public static AsyncProgressDialog getInstant(Activity context) {
        return new AsyncProgressDialog(context);
    }

    public static AsyncProgressDialog getInstant(Activity context, String msg) {
        return new AsyncProgressDialog(context, msg);
    }

    public void show(String message) {
        try {
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void dismiss() {
        try {

            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (pd != null && pd.isShowing()) {
                        pd.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void setMessage(final String message) {

        // Wrap DownloadTask into another Runnable to track the statistics
        try {
            if (pd != null) {

                context.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // pd.setMessage(message);
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShowing() {
        try {
            return pd.isShowing();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
