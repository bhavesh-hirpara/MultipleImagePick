package com.admision;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.admision.adapter.SideMenuAdapter;
import com.admision.utils.AsyncProgressDialog;
import com.admision.utils.Constant;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


public class BaseActivity extends AppCompatActivity {
    AsyncProgressDialog ad;

    ArrayList<MenuItem> menuItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        toast = Toast.makeText(getActivity(), "", Toast.LENGTH_LONG);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.FINISH_ACTIVITY);

        commonReciever = new MyEventServiceReciever();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                commonReciever, intentFilter);

    }

    Drawer result;

    public void initDrawer() {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        RecyclerView.LayoutManager layoutManager;
        SideMenuAdapter mAdapter;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        layoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mAdapter = new SideMenuAdapter(this);
//        mRecyclerView.setAdapter(mAdapter);

//        mAdapter.addAll(menuItems);


//create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withActivity(this).withCloseOnClick(true).withSelectedItemByPosition(-1)
                .withHeader(R.layout.nav_header_main)
//                .withCustomView(mRecyclerView)

                .build();

//                .addDrawerItems(
//                        new PrimaryDrawerItem().withName("Dashboard").withSelectable(false).withIcon(R.drawable.ic_place),
//                        new PrimaryDrawerItem().withName("Trip").withSelectable(false).withIcon(R.drawable.ic_call_24dp),
//                        new PrimaryDrawerItem().withName("Violations").withSelectable(false).withIcon(R.drawable.ic_chat),
//                        new PrimaryDrawerItem().withName("Hos").withSelectable(false).withIcon(R.drawable.ic_call_24dp),
//                        new PrimaryDrawerItem().withName("VIR").withSelectable(false).withIcon(R.drawable.ic_fax_24dp),
//                        new PrimaryDrawerItem().withName("Change Password").withSelectable(false).withIcon(R.drawable.ic_chat),
//                        new PrimaryDrawerItem().withName("Logout").withSelectable(false).withIcon(R.drawable.ic_delete_24dp)
//
//                )
//                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                    @Override
//                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                        if (position == 0) {
//
//
//                        } else if (position == 1) {
//
//
//                        } else if (position == 2) {
//
//
//                        } else if (position == 6) {
//                            confirmLogout();
//                        }
//                        return true;
//                    }
//                })
//                .build();

        ImageView imgMenu = (ImageView) findViewById(R.id.imgMenu);
        if (imgMenu != null) {
            imgMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (result.isDrawerOpen()) {
                        result.closeDrawer();
                    } else {
                        result.openDrawer();
                    }
                }
            });
        }

//        initMenuItems();
//        fillProfileData();
    }

    public static class MenuItem {
        //        public String navImage;
        public String navName = "abc";
    }

    private void confirmLogout() {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                .title(R.string.logout_title)
                .content(R.string.logout_msg)
                .positiveText(R.string.btn_yes)
                .negativeText(R.string.btn_no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        showToast("Logged Out", Toast.LENGTH_SHORT);
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {

                    }
                });

        MaterialDialog dialog = builder.build();
        dialog.show();
    }

    private void hideMenu(boolean b) {
        try {
//            if (b)
            result.closeDrawer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void finishActivity() {
        if ((getActivity() instanceof MainActivity)) {

        } else {
            getActivity().finish();
        }

    }

    ImageLoader imageLoader;


    MyEventServiceReciever commonReciever;

    class MyEventServiceReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().equalsIgnoreCase(
                        Constant.FINISH_ACTIVITY)) {
                    finish();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BaseActivity getActivity() {
        return this;
    }

    private TextView tvTitleText;

    public void setTitleText(String text) {
        try {

            if (tvTitleText == null)
                tvTitleText = (TextView) findViewById(R.id.tvTitleText);
            tvTitleText.setText(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialog(String msg) {
        try {
            if (ad != null && ad.isShowing()) {
                return;
            }

            ad = AsyncProgressDialog.getInstant(getActivity());
            ad.show(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMessage(String msg) {
        try {
            ad.setMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissDialog() {
        try {
            if (ad != null) {
                ad.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean handled = super.onKeyDown(keyCode, event);

        // Eat the long press event so the keyboard doesn't come up.
        if (keyCode == KeyEvent.KEYCODE_MENU && event.isLongPress()) {
            return true;
        }

        return handled;
    }

    Toast toast;

    public void showToast(final String text, final int duration) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                toast.setText(text);
                toast.setDuration(duration);
                toast.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        try {
            LocalBroadcastManager.getInstance(getApplicationContext())
                    .unregisterReceiver(commonReciever);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        try {
            if (result.isDrawerOpen()) {
                result.closeDrawer();
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            super.onBackPressed();
        }
    }


    BaseCallback baseCallback;

    public void setBaseCallback(BaseCallback baseCallback) {
        this.baseCallback = baseCallback;
    }

    interface BaseCallback {
        void onMasterDataLoad();
    }


}
