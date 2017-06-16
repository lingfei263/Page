package cn.ffb.page.ui;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created by lingfei on 2017/6/11.
 */

public class PageListViewHandler extends BasePageViewHandler<AbsListView> {

    @Override
    public void setEmptyView(AbsListView pageView, View view) {
        pageView.setEmptyView(view);
    }

}
