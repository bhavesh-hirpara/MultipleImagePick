package com.admision;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.admision.adapter.FindEventAdapter;
import com.admision.utils.ExitStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends com.admision.BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FindEventAdapter mAdapter;

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

        initDrawer(true);
        init();
    }

    private void init() {
        setTitleText("FIND EVENTS");

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new FindEventAdapter(this);
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
