package cn.ffb.page.pulltorefresh;

import android.view.View;

import cn.ffb.page.core.OnPullToRefreshProvider;

/**
 * Created by lingfei on 2017/6/11.
 */

public abstract class PullToRefreshContext<T extends View> implements OnPullToRefreshProvider {
    protected T mPageView;

    public PullToRefreshContext(T pageView) {
        this.mPageView = pageView;
    }
}
