package com.admision;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.admision.adapter.InvitedUserAdapter;
import com.common.view.SimpleListDividerDecorator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvtivedUsersActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    InvitedUserAdapter invitedUserAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invtived_users);
        ButterKnife.bind(this);

        initBack();
        init();
    }

    private void init() {
        setTitleText("INVITED USERS");

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
//        recyclerViewHost.setLayoutAnimation(Utils.getRowFadeSpeedAnimation(getActivity()));
        mRecyclerView.addItemDecoration(new SimpleListDividerDecorator(getResources().getDrawable(R.drawable.list_divider), true));
        invitedUserAdapter = new InvitedUserAdapter(this);
        mRecyclerView.setAdapter(invitedUserAdapter);
    }
}
