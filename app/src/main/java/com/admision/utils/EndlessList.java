package com.admision.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class EndlessList {

    private boolean isLock = false;
    private boolean enable = true;
    private boolean stackFromEnd = false;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView recyclerView;

    public EndlessList(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.recyclerView = recyclerView;
        this.recyclerView.setOnScrollListener(onScrollListener);
    }

    public void setStackFromEnd(boolean stackFromEnd) {
        this.stackFromEnd = stackFromEnd;
    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (enable) {
                if (stackFromEnd) {

                    firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                    boolean loadMore = firstVisibleItem == 0;

                    if (loadMore && !isLock) {
                        if (loadMoreListener != null) {
                            loadMoreListener.onLoadMore();
                        }
                    }

                } else {
                    visibleItemCount = recyclerView.getChildCount();
                    totalItemCount = mLinearLayoutManager.getItemCount();
                    firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                    boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;

                    if (loadMore && !isLock) {
                        if (loadMoreListener != null) {
                            loadMoreListener.onLoadMore();
                        }
                    }
                }
            }
        }

    };

    public void lockMoreLoading() {
        isLock = true;
    }

    public void releaseLock() {
        isLock = false;
    }

    public void disableLoadMore() {
        enable = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    OnLoadMoreListener loadMoreListener;

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

}