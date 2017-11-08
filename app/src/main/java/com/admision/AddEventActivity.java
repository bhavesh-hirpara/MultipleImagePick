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
import com.admision.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;

public class AddEventActivity extends BaseActivity {

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.editAddress)
    EditText editAddress;
    @BindView(R.id.editStartDate)
    EditText editStartDate;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editEndDateTime)
    EditText editEndDateTime;
    @BindView(R.id.editRate)
    EditText editRate;
    @BindView(R.id.editDescription)
    EditText editDescription;
    @BindView(R.id.editBrowese)
    EditText editBrowese;

    @BindView(R.id.tvSelectVenue)
    TextView tvSelectVenue;
    @BindView(R.id.tvBackArrow)
    TextView tvBackArrow;
    @BindView(R.id.tvAvailableTickets)
    TextView tvAvailableTickets;

    @BindView(R.id.radioEventMode)
    RadioGroup radioEventMode;
    @BindView(R.id.radioPrivate)
    RadioButton radioPrivate;
    @BindView(R.id.radioPublic)
    RadioButton radioPublic;

    @BindView(R.id.radioEventStatus)
    RadioGroup radioEventStatus;
    @BindView(R.id.radioApprove)
    RadioButton radioApprove;
    @BindView(R.id.radioReject)
    RadioButton radioReject;

    @BindView(R.id.radioAgeRequirement)
    RadioGroup radioAgeRequirement;
    @BindView(R.id.radioAge18)
    RadioButton radioAge18;
    @BindView(R.id.radioAge21)
    RadioButton radioAge21;
    @BindView(R.id.radioNoteRequired)
    RadioButton radioNoteRequired;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);

        initDrawer(true);
        init();
    }

    private void init() {
        initImageLoader();

        tvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEventActivity.super.onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setAddEvente();
            }
        });

    }

    ImageLoader imageLoader;

    private void initImageLoader() {
        try {
            imageLoader = Utils.initImageLoader(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setAddEvente() {
        try {
            showDialog("");

            FormBody.Builder body = RequestParamsUtils.newRequestFormBody(getActivity());
//            body.addEncoded(RequestParamsUtils.FORGOT_PASSWORD, editForgotEmail.getText().toString());
            Call call = AsyncHttpRequest.newRequestPost(getActivity(), body.build(), URLs.GET_CHALLAN());
            call.enqueue(new GetEventeDataHandle(getActivity()));

            for (int i = 0; i < body.build().size(); i++) {
                Debug.e("setAddEvente :- ", "" + body.build().name(i) + " = " + body.build().value(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GetEventeDataHandle extends AsyncResponseHandler {

        public GetEventeDataHandle(Activity context) {
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
                Debug.e("", "setAddEvente# " + response);

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
