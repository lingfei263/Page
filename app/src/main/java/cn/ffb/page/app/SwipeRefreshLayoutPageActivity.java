package cn.ffb.page.app;

import android.os.Bundle;
import android.view.View;

import com.markmao.pulltorefresh.widget.XListView;

import cn.ffb.page.core.IPageAdapter;
import cn.ffb.page.core.IPageDataParser;
import cn.ffb.page.http.RetrofitPageActivity;
import cn.ffb.page.app.entity.AlbumInfo;
import cn.ffb.page.app.entity.Tracks;
import cn.ffb.page.ui.PageConfig;
import cn.ffb.http.core.ICall;

/**
 * Created by lingfei on 2017/6/20.
 */

public class SwipeRefreshLayoutPageActivity extends RetrofitPageActivity<AlbumInfo, Tracks> {
    private XListView mListView;
    private AlbumPageAdapter mPageAdapter;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_swipe_refresh;
    }

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        mListView = (XListView) this.findViewById(R.id.list_view);
        mPageAdapter = new AlbumPageAdapter(getContext());
        mListView.setAdapter(mPageAdapter);
    }

    @Override
    public View getPageView() {
        return mListView;
    }

    @Override
    public void onPageConfig(PageConfig pageConfig) {
        super.onPageConfig(pageConfig);
        // 设置分页大小
        pageConfig.setPageSize(5);
    }

    @Override
    public ICall<AlbumInfo> executePageRequest(int pageSize, int currentPage, Tracks mData) {
        return HttpAPIClient.getAPI().getAlbumInfo("夜曲", pageSize, currentPage);
    }

    @Override
    public IPageAdapter<Tracks> getPageAdapter() {
        return mPageAdapter;
    }

    @Override
    public IPageDataParser<AlbumInfo, Tracks> getPageDataParser() {
        return new PageDataParser();
    }

}
