package cn.ffb.page.ui;

import android.view.View;

import cn.ffb.widget.xrecyclerview.BaseRecyclerView;

/**
 * Created by lingfei on 2017/6/11.
 */

public class PageRecyclerViewHandler extends BasePageViewHandler<BaseRecyclerView> {

    @Override
    public void setEmptyView(BaseRecyclerView pageView, View view) {
        pageView.setEmptyView(view);
    }

}
