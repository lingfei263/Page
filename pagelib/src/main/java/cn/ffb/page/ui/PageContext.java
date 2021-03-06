package cn.ffb.page.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import cn.ffb.page.core.DefaultOnPageDataStateSaved;
import cn.ffb.page.core.DefaultOnPageListener;
import cn.ffb.page.core.DefaultPageSearcher;
import cn.ffb.page.core.IFilterName;
import cn.ffb.page.core.IPageAdapter;
import cn.ffb.page.core.IPageChecker;
import cn.ffb.page.core.IPageDataIntercept;
import cn.ffb.page.core.IPageSearcher;
import cn.ffb.page.core.OnPageCheckedChangeListener;
import cn.ffb.page.core.OnPageCheckedEquals;
import cn.ffb.page.core.OnPageCheckedInitListener;
import cn.ffb.page.core.OnPageDataStateSaved;
import cn.ffb.page.core.OnPageListener;
import cn.ffb.page.core.OnPullToRefreshProvider;
import cn.ffb.page.core.PageAction;
import cn.ffb.page.core.PageChecker;
import cn.ffb.page.core.PageEngine;
import cn.ffb.page.core.PageManager;
import cn.ffb.page.pulltorefresh.PullToRefreshContextFactory;

/**
 * 是对PageEngine的UI层级的封装了，主要封装一些接口给Activity以及Fragment提供了。
 * <p>
 * 此外，PageContext里面提供了一个分页的配置类PageConfig，用于一些分页的基本配置，比如分页大小、是否在初始的时候自动加载数据等等。
 * <p>
 * Created by lingfei on 2017/6/11.
 */

public final class PageContext<T, I> {
    private Context mContext;
    private PageEngine<T, I> mPageEngine;
    private PageConfig mPageConfig = new PageConfig();
    private IPageContextProvider<T, I> mPageContextProvider;
    private PageViewManager mPageViewManager;
    private IPageViewProvider mPageViewProvider;
    private IPageAdapter<I> mPageAdapter;
    private View mPageView;
    private OnPullToRefreshProvider mOnPullToRefreshProvider;
    private IPageViewHandler<? extends View> mPageViewHandler;
    private boolean isPrepared;

    public PageContext(Context context, IPageContextProvider<T, I> provider) {
        this.mContext = context;
        this.mPageContextProvider = provider;
        provider.onPageConfig(mPageConfig);
        mPageEngine = new PageEngine(this.mContext, mPageConfig.pageSize);
        mPageViewManager = new PageViewManager();
        mPageViewManager.setOnReRequestListener(new OnReRequestListener() {
            @Override
            public void onReRequest() {
                initPageData();
            }
        });
        mPageEngine.addOnPageListener(new DefaultOnPageListener() {
            @Override
            public void onPageCancelRequests() {
                mPageViewManager.cancelPageRequest(mPageContextProvider.getPageAdapter().getPageDataCount());
            }

            @Override
            public void onPageRequestStart(PageAction pageAction) {
                mPageViewManager.startPageRequest(pageAction);
            }

            @Override
            public void onPageLoadComplete(PageAction pageAction, boolean isFromCache, boolean isSuccess) {
                mPageViewManager.completePageRequest(pageAction, isFromCache, mPageContextProvider.getPageAdapter().getPageDataCount());
            }
        });
        this.setOnPageDataStateSaved(new DefaultOnPageDataStateSaved<I>());
    }

    private final void prepare() {
        if (isPrepared) {
            return;
        }
        View pageView = mPageContextProvider.getPageView();
        if (mPageView != pageView) {
            mPageView = pageView;
            if (mPageViewProvider == null) {
                mPageViewProvider = new PageViewProvider(mPageView);
                this.setPageViewProvider(mPageViewProvider);
            }
            if (mPageViewHandler == null) {
                mPageViewHandler = PageViewHandlerFactory.createPageViewHandler(mPageView);
                this.setPageViewHandler(mPageViewHandler);
            }
            mPageViewManager.inflate(mPageViewProvider, mPageViewHandler);
            if (mOnPullToRefreshProvider == null) {
                mOnPullToRefreshProvider = PullToRefreshContextFactory.getPullToRefreshProvider(mPageView);
                this.setOnPullToRefreshProvider(mOnPullToRefreshProvider);
            }
        }
        mPageAdapter = mPageContextProvider.getPageAdapter();
        mPageEngine.setPageDataParser(mPageContextProvider.getPageDataParser());
        mPageEngine.setPageRequester(mPageContextProvider.getPageRequester());
        mPageEngine.setPageAdapter(mPageAdapter);
        isPrepared = true;
    }

    public final void start(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            boolean success = restorePageDataState(savedInstanceState);
            if (!success) {
                this.initPageData();
            }
        } else {
            if (mPageConfig.autoInitPageData) {
                this.initPageData();
            }
        }
    }

    public final void attachSearchEditText(final EditText searchEditText, IFilterName<I> filterName) {
        this.attachSearchEditText(searchEditText, new DefaultPageSearcher<>(searchEditText.getContext(), filterName));
    }

    public final void attachSearchEditText(final EditText searchEditText, IPageSearcher<I> pageSearcher) {
        this.setPageSearcher(pageSearcher);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchPageData(searchEditText.getText().toString());
            }
        });
    }

    public final void setPageViewHandler(IPageViewHandler<? extends View> pageViewHandler) {
        this.mPageViewHandler = pageViewHandler;
    }

    public final void setOnPullToRefreshProvider(OnPullToRefreshProvider onPullToRefreshProvider) {
        this.mPageEngine.setOnPullToRefreshProvider(onPullToRefreshProvider);
    }

    public final IPageChecker<I> getPageChecker() {
        return mPageEngine.getPageChecker();
    }

    public final void setPageChecker(IPageChecker<I> pageChecker) {
        this.mPageEngine.setPageChecker(pageChecker);
    }

    public final void setPageChecker(int type, final OnPageCheckedChangeListener<I> listener1, OnPageCheckedInitListener<I> listener2) {
        setPageChecker(type, new OnPageCheckedEquals<I>() {
            @Override
            public boolean equals(I t1, I t2) {
                return t1 == t2;
            }
        }, listener1, listener2);
    }

    public final void setPageChecker(int type, final OnPageCheckedChangeListener<I> listener) {
        setPageChecker(type, new OnPageCheckedEquals<I>() {
            @Override
            public boolean equals(I t1, I t2) {
                return t1 == t2;
            }
        }, listener);
    }

    public final void setPageChecker(int type, OnPageCheckedEquals<I> equals, final OnPageCheckedChangeListener<I> listener) {
        setPageChecker(type, equals, listener, new OnPageCheckedInitListener<I>() {
            @Override
            public boolean isEnable(int position, I entity) {
                return true;
            }

            @Override
            public boolean isChecked(int position, I entity) {
                return false;
            }
        });
    }

    public final void setPageChecker(int type, OnPageCheckedEquals<I> equals, final OnPageCheckedChangeListener<I> listener1, OnPageCheckedInitListener<I> listener2) {
        PageChecker<I> pageChecker = new PageChecker<>(type, equals, listener2);
        pageChecker.setOnCheckedChangeListener(new OnPageCheckedChangeListener<I>() {
            @Override
            public void onPageCheckedChanged(List<I> checkedList, int count) {
                mPageAdapter.notifyDataSetChanged();
                listener1.onPageCheckedChanged(checkedList, count);
            }
        });
        this.mPageEngine.setPageChecker(pageChecker);
    }

    public final void setPageSearcher(IPageSearcher<I> pageSearcher) {
        this.mPageEngine.setPageSearcher(pageSearcher);
    }

    public final void setPageViewProvider(IPageViewProvider pageViewProvider) {
        this.mPageViewProvider = pageViewProvider;
    }

    public final boolean savePageDataState(Bundle savedInstanceState) {
        return mPageEngine.saveState(savedInstanceState);
    }

    public final boolean restorePageDataState(Bundle savedInstanceState) {
        prepare();
        return mPageEngine.restoreState(savedInstanceState);
    }

    public final PageManager<T, I> getPageManager() {
        return mPageEngine.getPageManager();
    }

    public final void clearPageData() {
        mPageEngine.clearPageData();
    }

    public final void initPageData() {
        isPrepared = false;
        prepare();
        clearPageData();
        mPageEngine.initPageData();
    }

    public final void refreshPageData() {
        prepare();
        mPageEngine.refreshPageData();
    }

    public final void onDestroy() {
        if (isPrepared) {
            mPageEngine.cancel();
        }
    }

    public final PageConfig getPageConfig() {
        return this.mPageConfig;
    }

    public final void searchPageData(String keyword) {
        mPageEngine.searchPageData(keyword);
    }

    public final void addOnPageListener(OnPageListener listener) {
        this.mPageEngine.addOnPageListener(listener);
    }

    public final void addPageDataIntercept(IPageDataIntercept<I> intercept) {
        this.mPageEngine.addPageDataIntercept(intercept);
    }

    public final void setOnPageDataStateSaved(OnPageDataStateSaved<I> onPageDataStateSaved) {
        mPageEngine.setOnPageDataStateSaved(onPageDataStateSaved);
    }

}
