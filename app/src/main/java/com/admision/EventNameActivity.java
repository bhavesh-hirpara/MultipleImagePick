package com.admision;

import android.os.Bundle;

import butterknife.ButterKnife;

public class EventNameActivity extends BaseActivity {

//    @BindView(R.id.tvEventName)
//    TextView tvEventName;
//    @BindView(R.id.tvAge)
//    TextView tvAge;
//    @BindView(R.id.tvDate)
//    TextView tvDate;
//    @BindView(R.id.tvDate_)
//    TextView tvDate_;
//    @BindView(R.id.tvPrice)
//    TextView tvPrice;
//    @BindView(R.id.tvCity)
//    TextView tvCity;
//    @BindView(R.id.tvLocation)
//    TextView tvLocation;
//    @BindView(R.id.tvTimeSqure)
//    TextView tvTimeSqure;
//
//    @BindView(R.id.imgShare)
//    ImageView imgShare;
//
//    @BindView(R.id.btnBuyNow)
//    Button btnBuyNow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_name);
        ButterKnife.bind(this);
        
        init();
    }

    private void init() {
        
    }
}
