package com.admision;

import android.os.Bundle;

import butterknife.ButterKnife;

public class EventDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        
        init();
    }

    private void init() {
        
    }
}
