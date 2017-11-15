package com.admision;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.admision.adapter.ViewEventAdapter;
import com.admision.utils.AsyncHttpRequest;
import com.admision.utils.AsyncResponseHandler;
import com.admision.utils.Debug;
import com.admision.utils.ExitStrategy;
import com.admision.utils.RequestParamsUtils;
import com.admision.utils.URLs;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.HttpUrl;

public class ViewEventActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ViewEventAdapter vieweventadapter;

//    @BindView(R.id.btnSendRequest)
//    Button btnSendRequest;
//
//    @BindView(R.id.editForgetEmail)
//    EditText editForgetEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initBack();
        init();
    }

    private void init() {
        setTitleText("VIEW EVENTS");

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        vieweventadapter = new ViewEventAdapter(this);
        mRecyclerView.setAdapter(vieweventadapter);

        vieweventadapter.setmEventlistener(new ViewEventAdapter.Eventlistener() {
            @Override
            public void OnItemViewclick(int position, View view) {

            }

            @Override
            public void OnItemBittonclick(int position) {
            }
        });
//        mAdapter.setmEventlistener(new FindEventAdapter.Eventlistener() {
//            @Override
//            public void OnItemViewclick(int position, View view) {
////                Debug.e("getItem id", "" + mAdapter.getItem(position).ID);
////                String id = mAdapter.getItem(position).ID;
////                Intent intent = new Intent(getActivity(),TicketsActivity.class);
////                startActivity(intent);
//            }
//        });
    }

    public void getEventData() {
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
                Debug.e("", "getEventData# " + response);
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

    @Override
    public void onBackPressed() {

        try {
            if (result.isDrawerOpen()) {
                result.closeDrawer();
            } else {
                if (ExitStrategy.canExit()) {
                    super.onBackPressed();
                } else {
                    ExitStrategy.startExitDelay(2000);
                    Toast.makeText(getActivity(), getString(R.string.exit_msg),
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {

        }
    }
}
