package cn.ffb.page.app;

import android.os.Bundle;
import android.view.View;

import cn.ffb.page.core.IPageAdapter;
import cn.ffb.page.core.IPageDataParser;
import cn.ffb.page.http.RetrofitPageActivity;
import cn.ffb.page.app.entity.AlbumInfo;
import cn.ffb.page.app.entity.Tracks;
import cn.ffb.page.ui.PageConfig;
import cn.ffb.widget.AutoRefreshRecyclerListView;
import cn.ffb.http.core.ICall;

/**
 * Created by lingfei on 2017/6/20.
 */

public class AutoRefreshPageActivity extends RetrofitPageActivity<AlbumInfo, Tracks> {
    private AutoRefreshRecyclerListView mListView;
    private AlbumRecyclerPageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_auto_refresh;
    }

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        mListView = (AutoRefreshRecyclerListView) this.findViewById(R.id.list_view);
        mPageAdapter = new AlbumRecyclerPageAdapter(this);
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
        pageConfig.setPageSize(12);
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
