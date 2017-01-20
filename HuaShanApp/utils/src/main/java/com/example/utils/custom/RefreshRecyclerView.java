package com.example.utils.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2016/10/18.
 */

public class RefreshRecyclerView extends RecyclerView {

    public RefreshRecyclerView(Context context) {
        super(context);
    }
    public SwipeRefreshLayout swl_pl;

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        Refresh(adapter);
        super.setAdapter(adapter);
    }

    public SwipeRefreshLayout getSwl_pl() {
        return swl_pl;
    }

    public void setSwl_pl(SwipeRefreshLayout swl_pl) {
        this.swl_pl = swl_pl;
    }

    private int lastVisibleItem;

    private void Refresh(final Adapter adapter){
        if(this.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.getLayoutManager();
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();


            this.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    if(lastVisibleItem == -1){
                        lastVisibleItem = 0;
                    }
                    Log.i("newState",RecyclerView.SCROLL_STATE_IDLE+"    lastVisibleItem : "+lastVisibleItem+"  getItemCount  :  "+adapter.getItemCount());

                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 >= adapter.getItemCount()) {

                        if(mOnRefreshListener == null){
                            return;
                        }
                        mOnRefreshListener.onRefreshUp();
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                    if(swl_pl == null){
                        return;
                    }
                    //解决swiperefreshlayout 和 RecyclerView 的滑动冲突
                    int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                    swl_pl.setEnabled(topRowVerticalPosition >= 0);
                }
            });

        }
    }

    public interface OnRefreshListener{
        void onRefreshUp();
    }

    private static OnRefreshListener mOnRefreshListener;

    public static void setOnRefreshListener(OnRefreshListener onRefreshListener){
        mOnRefreshListener = onRefreshListener;
    }
}
