package com.admision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class SignupVenueAndPromoterActivity extends BaseActivity {

    @BindView(R.id.btnSignupVenueOwner)
    Button btnSignupVenueOwner;
    @BindView(R.id.btnSignupPromoter)
    Button btnSignupPromoter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_venue_and_promoter);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        btnSignupVenueOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterAsVenueOwnerActivity.class);
                startActivity(intent);
            }
        });

        btnSignupPromoter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterAsPromoterActivity.class);
                startActivity(intent);
            }
        });
    }


    public void signupVenueOwner() {

        showDialog("");

        FormBody.Builder body = RequestParamsUtils.newRequestFormBody(getActivity());
//        body.addEncoded(RequestParamsUtils.EMAIL, editLoginEmail.getText().toString());
//        body.addEncoded(RequestParamsUtils.PASSWORD, editLoginPass.getText().toString());

        Call call = AsyncHttpRequest.newRequestPost(getActivity(), body.build(), URLs.GET_CHALLAN());
        call.enqueue(new GetLoginDataHandler(getActivity()));

        for (int i = 0; i < body.build().size(); i++) {
            Debug.e("signupVenueOwner:- ", "" + body.build().name(i) + " = " + body.build().value(i));
        }
    }

    private class GetLoginDataHandler extends AsyncResponseHandler {

        public GetLoginDataHandler(Activity context) {
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
                Debug.e("", "signupVenueOwner# " + response);
                if (response != null && response.length() > 0) {

                    LoginRes res = new Gson().fromJson(response,
                            new TypeToken<LoginRes>() {
                            }.getType());

                    if (res.status == 1) {
//                        Utils.setPref(getActivity(), RequestParamsUtils.SESSION_ID, "");
//                        Utils.setPref(getActivity(), RequestParamsUtils.EMAIL, editLoginEmail.getText().toString().trim());

//                        Intent intent = new Intent(getActivity(), MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    } else {
                        showToast(res.message, Toast.LENGTH_SHORT);
                    }
                }
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
