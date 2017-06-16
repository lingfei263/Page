package cn.ffb.page.pulltorefresh;

import cn.ffb.page.core.OnPullToRefreshProvider;
import cn.ffb.widget.AutoRefreshRecyclerListView;
/**
 * Created by lingfei on 2017/6/11.
 */
public class PullToRefreshAutoRefreshRecyclerListViewContext extends PullToRefreshContext<AutoRefreshRecyclerListView> {

    public PullToRefreshAutoRefreshRecyclerListViewContext(AutoRefreshRecyclerListView autoRefreshListView) {
        super(autoRefreshListView);
    }

    @Override
    public void setHaveMoreData(boolean isHaveMoreData) {
        mPageView.setHaveMoreData(isHaveMoreData);
    }

    @Override
    public void setPullRefreshEnable(boolean enable) {
    }

    @Override
    public void setPullLoadMoreEnable(boolean enable) {
        mPageView.setAutoRefreshEnable(enable);
    }

    @Override
    public void onRefreshComplete(int newDataSize, boolean success) {
        mPageView.refreshComplete(newDataSize);
    }

    @Override
    public void setOnRefreshListener(final OnPullToRefreshProvider.OnRefreshListener onRefreshListener) {
        mPageView.setOnAutoRefreshListener(new AutoRefreshRecyclerListView.OnAutoRefreshListener() {
            @Override
            public void autoRefresh(AutoRefreshRecyclerListView view) {
                onRefreshListener.onPullToLoadMore();
            }
        });
    }

    @Override
    public boolean isPullRefreshEnable() {
        return false;
    }

    @Override
    public boolean isPullLoadMoreEnable() {
        return mPageView.isAutoRefreshEnable();
    }
}
