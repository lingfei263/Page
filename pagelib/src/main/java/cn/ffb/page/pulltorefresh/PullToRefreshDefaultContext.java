package cn.ffb.page.pulltorefresh;

import android.view.View;

import cn.ffb.page.core.OnPullToRefreshProvider;

/**
 * Created by lingfei on 2017/6/11.
 */

public class PullToRefreshDefaultContext extends PullToRefreshContext<View> {

    public PullToRefreshDefaultContext(View pageView) {
        super(pageView);
    }

    @Override
    public void setHaveMoreData(boolean isHaveMoreData) {

    }

    @Override
    public void setPullRefreshEnable(boolean enable) {

    }

    @Override
    public boolean isPullRefreshEnable() {
        return false;
    }

    @Override
    public void setPullLoadMoreEnable(boolean enable) {

    }

    @Override
    public boolean isPullLoadMoreEnable() {
        return false;
    }

    @Override
    public void onRefreshComplete(int newDataSize, boolean success) {

    }

    @Override
    public void setOnRefreshListener(OnPullToRefreshProvider.OnRefreshListener onRefreshListener) {

    }
}
