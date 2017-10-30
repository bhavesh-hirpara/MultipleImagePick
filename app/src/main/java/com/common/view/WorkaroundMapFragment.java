//package com.common.view;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//
//import com.google.android.gms.maps.SupportMapFragment;
//
//public class WorkaroundMapFragment extends SupportMapFragment {
//    private OnTouchListener mListener;
//
//    @Override
//    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
//        View layout = super.onCreateView(layoutInflater, viewGroup, savedInstance);
//
//        TouchableWrapper frameLayout = new TouchableWrapper(getActivity());
//
//        frameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//
//        ((ViewGroup) layout).addView(frameLayout,
//                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//        return layout;
//    }
//
//    public void setListener(OnTouchListener listener) {
//        mListener = listener;
//    }
//
//    public interface OnTouchListener {
//        public abstract void onTouch();
//    }
//
//    public class TouchableWrapper extends FrameLayout {
//
//        public TouchableWrapper(Context context) {
//            super(context);
//        }
//
//        @Override
//        public boolean dispatchTouchEvent(MotionEvent event) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    mListener.onTouch();
//                    break;
//                case MotionEvent.ACTION_UP:
//                    mListener.onTouch();
//                    break;
//            }
//            return super.dispatchTouchEvent(event);
//        }
//    }
//}
