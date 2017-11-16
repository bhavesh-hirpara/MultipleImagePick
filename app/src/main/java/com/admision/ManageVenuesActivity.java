package com.admision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.admision.adapter.ManageVenuesAdapter;
import com.admision.utils.AsyncHttpRequest;
import com.admision.utils.AsyncResponseHandler;
import com.admision.utils.Debug;
import com.admision.utils.RequestParamsUtils;
import com.admision.utils.URLs;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.HttpUrl;

public class ManageVenuesActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ManageVenuesAdapter mAdapter;

    @BindView(R.id.btnAddNew)
    Button btnAddNew;

//    @BindView(R.id.tvBackArrow)
//    TextView tvBackArrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_venues);
        ButterKnife.bind(this);

        initBack();
//        initDrawer(true);
        init();
    }

    private void init() {
        setTitleText("MANAGE VANUE");

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ManageVenuesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

//        mAdapter.setmEventlistener(new FindEventAdapter.Eventlistener() {
//            @Override
//            public void OnItemViewclick(int position, View view) {
////                Debug.e("getItem id", "" + mAdapter.getItem(position).ID);
////                String id = mAdapter.getItem(position).ID;
////                Intent intent = new Intent(getActivity(),TicketsActivity.class);
////                startActivity(intent);
//            }
//        });

//        tvBackArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ManageVenuesActivity.super.onBackPressed();
//            }
//        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditVenueActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getManageVenuesData() {
        try {
            showDialog("");

            HttpUrl.Builder body = RequestParamsUtils.newRequestUrlBuilder(getActivity(), URLs.GET_CHALLAN());
            Call call = AsyncHttpRequest.newRequestGet(getActivity(), body.build().toString());
            call.enqueue(new GetEventDataHandler(getActivity()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GetEventDataHandler extends AsyncResponseHandler {

        public GetEventDataHandler(Activity context) {
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
                Debug.e("", "getManageVenuesData# " + response);
                if (response != null && response.length() > 0) {

//                    final SymptomTrackRes symptomTrackRes = new Gson().fromJson(response, new TypeToken<SymptomTrackRes>() {
//                    }.getType());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            symAdapter.addAll(symptomTrackRes.symptoms);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Throwable e, String content) {
            Debug.e("", "getEventData# " + content);
            dismissDialog();
        }
    }
}
