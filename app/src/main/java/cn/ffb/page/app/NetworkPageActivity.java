package cn.ffb.page.app;

import android.os.Bundle;
import android.view.View;

import com.markmao.pulltorefresh.widget.XListView;

import java.util.List;

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

public class NetworkPageActivity extends RetrofitPageActivity<AlbumInfo, Tracks> {
    private XListView mListView;
    private AlbumPageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_network_page;
    }

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        mListView = (XListView) this.findViewById(R.id.list_view);
        mPageAdapter = new AlbumPageAdapter(this);
        mListView.setAdapter(mPageAdapter);
    }

    @Override
    public View getPageView() {
        return mListView;
    }

    @Override
    public void onPageConfig(PageConfig pageConfig) {
        super.onPageConfig(pageConfig);
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
        return new IPageDataParser<AlbumInfo, Tracks>() {

            @Override
            public List<Tracks> getPageList(AlbumInfo data, boolean isFromCache) {
                return data.getTracks();
            }

            @Override
            public long getPageTotal(AlbumInfo data, boolean isFromCache) {
                return data.getTotal_tracks();
            }
        };
    }
}
