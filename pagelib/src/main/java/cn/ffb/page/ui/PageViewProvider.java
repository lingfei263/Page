package cn.ffb.page.ui;

import android.view.View;

import cn.ffb.page.R;

/**
 * Created by lingfei on 2017/6/11.
 */

class PageViewProvider implements IPageViewProvider {
    private View mPageView;

    public PageViewProvider(View pageView) {
        this.mPageView = pageView;
    }

    @Override
    public View getPageView() {
        return mPageView;
    }

    @Override
    public int getPageLoadingView() {
        return R.layout.loadingview;
    }

    @Override
    public int getPageEmptyView() {
        return R.layout.emptyview;
    }
}
