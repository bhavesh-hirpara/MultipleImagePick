package com.admision;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.admision.objects.LoginRes;
import com.admision.utils.AsyncHttpRequest;
import com.admision.utils.AsyncResponseHandler;
import com.admision.utils.Debug;
import com.admision.utils.RequestParamsUtils;
import com.admision.utils.URLs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;

public class EditVenueActivity extends BaseActivity {

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.editVanueName)
    EditText editVanueName;
    @BindView(R.id.editCompanyName)
    EditText editCompanyName;
    @BindView(R.id.editCompanyType)
    EditText editCompanyType;
    @BindView(R.id.editAddAddress1)
    EditText editAddAddress1;
    @BindView(R.id.editAddAddress2)
    EditText editAddAddress2;
    @BindView(R.id.editZipcode)
    EditText editZipcode;
    @BindView(R.id.editLatitude)
    EditText editLatitude;
    @BindView(R.id.editLongitude)
    EditText editLongitude;
    @BindView(R.id.editHoursOfPeration)
    EditText editHoursOfPeration;

    @BindView(R.id.tvBackArrow)
    TextView tvBackArrow;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvCapcity)
    TextView tvCapcity;

    @BindView(R.id.radioStatus)
    RadioGroup radioStatus;
    @BindView(R.id.radioActive)
    RadioButton radioActive;
    @BindView(R.id.radioInactive)
    RadioButton radioInactive;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_venue);
        ButterKnife.bind(this);

        initDrawer(true);
        init();
    }

    private void init() {

        tvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditVenueActivity.super.onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    private boolean validate() {
//        if (editEmail.getText().toString().trim().length() <= 0) {
//            showToast(getString(R.string.err_email), Toast.LENGTH_SHORT);
//            return false;
//        }

//        if (!Patterns.EMAIL_ADDRESS.matcher(editLoginEmail.getText()).matches()) {
//            showToast(getString(R.string.err_email_invalid), Toast.LENGTH_SHORT);
//            return false;
//        }
        return true;
    }

    public void setEditVenueData() {
        try {
            showDialog("");

            FormBody.Builder body = RequestParamsUtils.newRequestFormBody(getActivity());
//            body.addEncoded(RequestParamsUtils.FORGOT_PASSWORD, editForgotEmail.getText().toString());
            Call call = AsyncHttpRequest.newRequestPost(getActivity(), body.build(), URLs.GET_CHALLAN());
            call.enqueue(new GetsetEditVenueDataHandle(getActivity()));

            for (int i = 0; i < body.build().size(); i++) {
                Debug.e("setEditVenueData :- ", "" + body.build().name(i) + " = " + body.build().value(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GetsetEditVenueDataHandle extends AsyncResponseHandler {

        public GetsetEditVenueDataHandle(Activity context) {
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
                Debug.e("", "setEditVenueData# " + response);

                LoginRes res = new Gson().fromJson(response, new TypeToken<LoginRes>() {
                }.getType());

//                if (res.status == 1) {
//                    showToast(res.message, Toast.LENGTH_SHORT);
//                } else {
//                    showToast(res.message, Toast.LENGTH_SHORT);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Throwable e, String content) {
            Debug.e("", "onFailure# " + content);
            dismissDialog();
        }
    }
}
