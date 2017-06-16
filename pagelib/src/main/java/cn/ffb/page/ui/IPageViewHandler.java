package cn.ffb.page.ui;

import android.view.View;

/**
 * Created by lingfei on 2017/6/11.
 */

public interface IPageViewHandler<V extends View> {

    void setup(V pageView, View loadingView, View emptyView);

    void showPageLoadingView();

    void showPageEmptyView();

    void showPageView();
}
