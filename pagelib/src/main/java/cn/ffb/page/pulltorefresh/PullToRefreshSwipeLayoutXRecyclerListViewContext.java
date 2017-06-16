package cn.ffb.page.pulltorefresh;

import cn.ffb.page.core.OnPullToRefreshProvider;
import cn.ffb.widget.xrecyclerview.OnLoadMoreListener;
import cn.ffb.widget.xrecyclerview.XRecyclerListView;

/**
 * Created by lingfei on 2017/6/11.
 */

public class PullToRefreshSwipeLayoutXRecyclerListViewContext extends PullToRefreshSwipeLayoutContext<XRecyclerListView> {

    public PullToRefreshSwipeLayoutXRecyclerListViewContext(XRecyclerListView pageView) {
        super(pageView);
    }

    @Override
    public void setOnRefreshListener(final OnPullToRefreshProvider.OnRefreshListener onRefreshListener) {
        super.setOnRefreshListener(onRefreshListener);
        this.mPageView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                onRefreshListener.onPullToLoadMore();
            }
        });
    }

    @Override
    public void setHaveMoreData(boolean isHaveMoreData) {
        this.mPageView.setHaveMoreData(isHaveMoreData);
    }

    @Override
    public void onRefreshComplete(int newDataSize, boolean success) {
        super.onRefreshComplete(newDataSize, success);
        this.mPageView.stopLoadMore();
    }

    @Override
    public void setPullLoadMoreEnable(boolean enable) {
        this.mPageView.setLoadMoreEnable(enable);
    }

    @Override
    public boolean isPullLoadMoreEnable() {
        return this.mPageView.isLoadMoreEnable();
    }
}
