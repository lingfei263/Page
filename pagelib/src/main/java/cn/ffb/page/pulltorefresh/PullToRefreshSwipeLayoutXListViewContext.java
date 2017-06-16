package cn.ffb.page.pulltorefresh;

import com.markmao.pulltorefresh.widget.XListView;

import cn.ffb.page.core.OnPullToRefreshProvider;

/**
 * Created by lingfei on 2017/6/11.
 */

public class PullToRefreshSwipeLayoutXListViewContext extends PullToRefreshSwipeLayoutContext<XListView> {

    public PullToRefreshSwipeLayoutXListViewContext(XListView pageView) {
        super(pageView);
        this.mPageView.setPullRefreshEnable(false);
    }

    @Override
    public void setOnRefreshListener(final OnPullToRefreshProvider.OnRefreshListener onRefreshListener) {
        super.setOnRefreshListener(onRefreshListener);
        this.mPageView.setXListViewListener(new XListView.IXListViewListener() {

            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                onRefreshListener.onPullToLoadMore();
            }

        });
    }

    @Override
    public void setHaveMoreData(boolean isHaveMoreData) {
        this.mPageView.setHasMoreData(isHaveMoreData);
    }

    @Override
    public void onRefreshComplete(int newDataSize, boolean success) {
        super.onRefreshComplete(newDataSize, success);
        this.mPageView.stopLoadMore();
    }

    @Override
    public void setPullLoadMoreEnable(boolean enable) {
        this.mPageView.setPullLoadEnable(enable);
    }

    @Override
    public boolean isPullLoadMoreEnable() {
        return this.mPageView.isEnablePullLoad();
    }
}
