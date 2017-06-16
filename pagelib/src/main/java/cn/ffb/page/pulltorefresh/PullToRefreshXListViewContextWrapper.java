package cn.ffb.page.pulltorefresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.markmao.pulltorefresh.widget.XListView;

/**
 * Created by lingfei on 2017/6/11.
 */
public class PullToRefreshXListViewContextWrapper extends PullToRefreshContextWrapper<XListView> {

    public PullToRefreshXListViewContextWrapper(XListView pageView) {
        super(pageView);
    }

    @Override
    public PullToRefreshContext<XListView> getPullToRefreshContext(XListView pageView) {
        if (pageView.getParent() instanceof SwipeRefreshLayout) {
            return new PullToRefreshSwipeLayoutXListViewContext(pageView);
        } else {
            return new PullToRefreshXListViewContext(pageView);
        }
    }
}
