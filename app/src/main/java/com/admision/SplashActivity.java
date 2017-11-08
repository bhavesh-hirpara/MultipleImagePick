package com.admision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.admision.utils.AsyncHttpRequest;
import com.admision.utils.AsyncResponseHandler;
import com.admision.utils.Constant;
import com.admision.utils.Debug;
import com.admision.utils.RequestParamsUtils;
import com.admision.utils.URLs;
import com.admision.utils.Utils;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;

public class SplashActivity extends BaseActivity {
    String TAG = "SplashActivity";
    Handler handler = new Handler();
    TextView splashMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        splashMsg = (TextView) findViewById(R.id.splashMsg);
        splashMsg.setVisibility(View.INVISIBLE);

        if (Utils.isInternetConnected(getActivity())) {
//            new GCMUtilities(getActivity()).initGCM();

//            getUpdateVersion();
            startApplication(1000);
//            if (Utils.isLocationProviderEnabled(getActivity())) {
//            startApplication(1000);
//            } else {
//                handler.post(postLocationDialog);
//            }
        } else {
            handler.post(mPostInternetConDialog);
        }
    }

    private void startApplication(long sleepTime) {
        handler.postDelayed(startApp, sleepTime);
    }

    Runnable startApp = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(startApp);
//            Debug.e(TAG, "startApp");
//            if (!Utils.isUserLoggedIn(getActivity())) {
//
//                Intent i = new Intent(getActivity(), LoginActivity.class);
//                startActivity(i);
//                finish();
//
//            } else {
//
//                Intent i = new Intent(getActivity(),
//                        MainActivity.class);
//                startActivity(i);
//                finish();
//
//            }

            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
        }

    };


    int count = 30;

    Runnable mPostInternetConDialog = new Runnable() {

        @Override
        public void run() {

            MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                    .title(R.string.connection_title)
                    .content(R.string.connection_not_available)
                    .positiveText(R.string.btn_enable)
                    .negativeText(R.string.btn_cancel)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                            try {
                                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                startActivity(intent);
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {

                        }
                    });

            MaterialDialog dialog = builder.build();
            dialog.show();

        }
    };

    Runnable postLocationDialog = new Runnable() {

        @Override
        public void run() {

            MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                    .title(R.string.location_title)
                    .content(R.string.location_msg)
                    .positiveText(R.string.btn_settings)
                    .negativeText(R.string.btn_cancel)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                            try {
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {

                        }
                    });

            MaterialDialog dialog = builder.build();
            dialog.show();


        }
    };

    Runnable checkConnection = new Runnable() {
        @Override
        public void run() {
            Debug.e(TAG, "checkConnection");
            if (Utils.isInternetConnected(getActivity())) {

                splashMsg.setText(getString(R.string.connected));
                handler.removeCallbacks(checkConnection);

                if (Utils.isInternetConnected(getActivity())) {
                    startApplication(1000);
                } else {
                    handler.post(mPostInternetConDialog);
                }

            } else {

                if (count != 0) {
                    splashMsg.setText(String.format(
                            getString(R.string.retrying), "" + (count--)));
                    handler.postDelayed(checkConnection, 1000);
                } else {
                    splashMsg.setText("Finishing... ");
                    finish();
                }

            }
        }
    };

    public void getUpdateVersion() {
        try {
//            showDialog("");

//            RequestParams params = RequestParamsUtils.newRequestParams(getActivity());
////            params.put(RequestParamsUtils.MEMBERCODE, "" + Utils.getMemberCode(getActivity()));
//
//            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest(getActivity());
//            clientPhotos.post(URLs.GET_CHALLAN(), params, new GetVersionDataHandler(getActivity()));

            FormBody.Builder body = RequestParamsUtils.newRequestFormBody(getActivity());
            body.addEncoded(RequestParamsUtils.ADVANCE, "");

            Call call = AsyncHttpRequest.newRequestPost(getActivity(), body.build(), URLs.GET_CHALLAN());
            call.enqueue(new GetVersionDataHandler(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GetVersionDataHandler extends AsyncResponseHandler {

        public GetVersionDataHandler(Activity context) {
            super(context);
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onFinish() {

            try {
                dismissDialog();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSuccess(String response) {

            try {
                Debug.e("", "getUpdateVersion# " + response);
                if (response != null && response.length() > 0) {
//                    VersionUpdate loginRider = new Gson().fromJson(
//                            response, new TypeToken<VersionUpdate>() {
//                            }.getType());
//
//                    if (loginRider.St == 1) {
//                        if (Integer.valueOf(loginRider.Data.get(0).anroidVersion) > Utils.getAppVersionCode(getActivity())) {
//                            if (Debug.DEBUG) {
//                                startApplication(1000);
//                            } else {
//                                versionUpgradeDialog();
//                            }
//                        } else {
//                            startApplication(1000);
//                        }
//                    } else {
//                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailure(Throwable e, String content) {
            startApplication(1000);
            dismissDialog();
        }
    }

//    public void versionUpgradeDialog() {
//        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
//                .title(R.string.upgrade)
//                .content(R.string.update_text)
//                .positiveText(R.string.btn_update)
//                .negativeText(R.string.btn_cancel)
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
//                        try {
//                            final String appPackageName = getPackageName();
//                            try {
//                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//                            } catch (android.content.ActivityNotFoundException anfe) {
//                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
//                            }
//                            finish();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
//                        finish();
//                    }
//                });
//
//        MaterialDialog dialog = builder.build();
//        dialog.show();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.REQ_CODE_SETTING) {
            splashMsg.setVisibility(View.VISIBLE);
            handler.post(checkConnection);
        }

    }

    @Override
    public void onDestroy() {
        try {
            handler.removeCallbacks(startApp);
            handler.removeCallbacks(checkConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

}
