package cn.ffb.page.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import cn.ffb.page.core.IPageAdapter;
import cn.ffb.page.core.IPageDataParser;
import cn.ffb.page.http.RetrofitPageDialog;
import cn.ffb.page.app.entity.AlbumInfo;
import cn.ffb.page.app.entity.Tracks;
import cn.ffb.page.ui.PageConfig;
import cn.ffb.dialog.core.IDialog;
import cn.ffb.http.core.ICall;

/**
 * Created by lingfei on 2017/6/20.
 */

public class AlbumPageDialog1 extends RetrofitPageDialog<AlbumInfo, Tracks> {
    private ListView mListView;
    private AlbumPageAdapter mPageAdapter;

    public AlbumPageDialog1(Context context) {
        super(context);
    }

    @Override
    protected IDialog onCreateDialog(Bundle args) {
        return this.getDefaultDialogBuilder().setTitle("搜索结果").create();
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
    public void onViewCreated(Bundle args) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.activity_network_page, null, false);
        mListView = (ListView) v.findViewById(R.id.list_view);
        mPageAdapter = new AlbumPageAdapter(getContext());
        mListView.setAdapter(mPageAdapter);
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
