package cn.ffb.page.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import cn.ffb.page.core.IPageAdapter;
import cn.ffb.page.core.IPageDataParser;
import cn.ffb.page.core.IPageRequester;
import cn.ffb.page.app.entity.AlbumInfo;
import cn.ffb.page.app.entity.Tracks;
import cn.ffb.page.ui.PageConfig;
import cn.ffb.page.ui.PageDialog;

/**
 * Created by lingfei on 2017/6/20.
 */

public class AlbumPageDialog2 extends PageDialog<AlbumInfo, Tracks> {
    private ListView mListView;
    private AlbumPageAdapter mPageAdapter;

    public AlbumPageDialog2(Context context) {
        super(context);
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
    public IPageAdapter<Tracks> getPageAdapter() {
        return mPageAdapter;
    }

    @Override
    public IPageRequester<AlbumInfo, Tracks> getPageRequester() {
        return new OkHttpPageRequester<AlbumInfo, Tracks>(getContext()) {

            @Override
            public RequestCall getRequestCall(int pageSize, int pageIndex, Tracks data) {
                return OkHttpUtils.get()
                        .url("http://v5.pc.duomi.com/search-ajaxsearch-searchall")
                        .addParams("kw", "夜曲")
                        .addParams("pz", String.valueOf(pageSize))
                        .addParams("pi", String.valueOf(pageIndex))
                        .build();
            }
        };
    }

    @Override
    public IPageDataParser<AlbumInfo, Tracks> getPageDataParser() {
        return new PageDataParser();
    }

}
