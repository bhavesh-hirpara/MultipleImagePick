package com.admision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class LoginActivity extends BaseActivity {


    Handler handler = new Handler();

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindView(R.id.editLoginEmail)
    AppCompatEditText editLoginEmail;

    @BindView(R.id.editLoginPass)
    AppCompatEditText editLoginPass;

    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;

    @BindView(R.id.tvNewAdmision)
    TextView tvNewAdmision;

    boolean isRider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initImageLoader();

        if (Debug.DEBUG) {
            editLoginEmail.setText("tgmcteam@gmail.com");
            editLoginPass.setText("1234");

//            editLoginEmail.setText("manish");
//            editLoginPass.setText("123");
        }

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ForgotPasswordActivity.class);
                startActivity(i);
            }
        });

        tvNewAdmision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), SignupActivity.class);
//                startActivity(i);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
//                    login();
                }
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
        if (editLoginEmail.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_email), Toast.LENGTH_SHORT);
            return false;
        }

//        if (!Patterns.EMAIL_ADDRESS.matcher(editLoginEmail.getText()).matches()) {
//            showToast(getString(R.string.err_email_invalid), Toast.LENGTH_SHORT);
//            return false;
//        }

        if (editLoginPass.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_password), Toast.LENGTH_SHORT);
            return false;
        }

        return true;
    }

    public void login() {

        showDialog("");

        FormBody.Builder body = RequestParamsUtils.newRequestFormBody(getActivity());
        body.addEncoded(RequestParamsUtils.EMAIL, editLoginEmail.getText().toString());
        body.addEncoded(RequestParamsUtils.PASSWORD, editLoginPass.getText().toString());

        Call call = AsyncHttpRequest.newRequestPost(getActivity(), body.build(), URLs.GET_CHALLAN());
        call.enqueue(new GetLoginDataHandler(getActivity()));

        for (int i = 0; i < body.build().size(); i++) {
            Debug.e("login Data :- ", "" + body.build().name(i) + " = " + body.build().value(i));
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
                Debug.e("", "login# " + response);
                if (response != null && response.length() > 0) {

                    LoginRes res = new Gson().fromJson(response,
                            new TypeToken<LoginRes>() {
                            }.getType());

                    if (res.status == 1) {
                        Utils.setPref(getActivity(), RequestParamsUtils.SESSION_ID, "");
                        Utils.setPref(getActivity(), RequestParamsUtils.EMAIL, editLoginEmail.getText().toString().trim());

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        finish();
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
