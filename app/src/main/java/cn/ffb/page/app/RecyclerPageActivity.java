package cn.ffb.page.app;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;

import cn.ffb.page.core.IFilterName;
import cn.ffb.page.core.IPageAdapter;
import cn.ffb.page.core.IPageDataParser;
import cn.ffb.page.http.RetrofitPageActivity;
import cn.ffb.page.app.entity.AlbumInfo;
import cn.ffb.page.app.entity.Tracks;
import cn.ffb.page.ui.PageConfig;
import cn.ffb.adapter.recycler.OnRecyclerViewItemClickListener;
import cn.ffb.http.core.ICall;
import cn.ffb.widget.xrecyclerview.XRecyclerListView;

/**
 * Created by lingfei on 2017/6/20.
 */

public class RecyclerPageActivity extends RetrofitPageActivity<AlbumInfo, Tracks> {
    private XRecyclerListView mListView;
    private AlbumRecyclerPageAdapter mPageAdapter;
    private EditText mEditText;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        mListView = this.getView(R.id.list_view);
        mPageAdapter = new AlbumRecyclerPageAdapter(getContext());
        mListView.setAdapter(mPageAdapter);
        mListView.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(View itemView, int position) {
                showToast("哈哈" + position);

            }
        });
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mEditText = this.getView(R.id.editText);
        this.attachSearchEditText(mEditText, new IFilterName<Tracks>() {
            @Override
            public String getFilterName(Tracks entity) {
                return entity.getTitle();
            }
        });
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
