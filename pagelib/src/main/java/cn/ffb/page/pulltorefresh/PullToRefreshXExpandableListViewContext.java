package cn.ffb.page.pulltorefresh;

import com.markmao.pulltorefresh.widget.XExpandableListView;
import com.markmao.pulltorefresh.widget.XExpandableListView.IXListViewListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.ffb.page.core.OnPullToRefreshProvider;

/**
 * Created by lingfei on 2017/6/11.
 */
public class PullToRefreshXExpandableListViewContext extends PullToRefreshContext<XExpandableListView> {

    public PullToRefreshXExpandableListViewContext(XExpandableListView pageView) {
        super(pageView);
    }

    @Override
    public void setHaveMoreData(boolean isHaveMoreData) {
        mPageView.setHasMoreData(isHaveMoreData);
    }

    @Override
    public void setPullRefreshEnable(boolean enable) {
        mPageView.setPullRefreshEnable(enable);
    }

    @Override
    public void setPullLoadMoreEnable(boolean enable) {
        mPageView.setPullLoadEnable(enable);
    }

    @Override
    public void onRefreshComplete(int newDataSize, boolean success) {
        mPageView.stopLoadMore();
        mPageView.stopRefresh();
        mPageView
                .setRefreshTime(new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINA).format(new Date()));
    }

    @Override
    public void setOnRefreshListener(final OnPullToRefreshProvider.OnRefreshListener onRefreshListener) {
        mPageView.setXListViewListener(new IXListViewListener() {

            @Override
            public void onRefresh() {
                mPageView.setHasMoreData(true);
                onRefreshListener.onPullToRefresh();
            }

            @Override
            public void onLoadMore() {
                onRefreshListener.onPullToLoadMore();
            }

        });
    }

    @Override
    public boolean isPullRefreshEnable() {
        return mPageView.isEnablePullRefresh();
    }

    @Override
    public boolean isPullLoadMoreEnable() {
        return mPageView.isEnablePullLoad();
    }
}
