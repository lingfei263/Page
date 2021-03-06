package cn.ffb.page.core;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要负责对请求器、解析器、拦截器的调度管理，
 * <p>
 * 此外，PageManager里面还维护了一个Page对象，用来保存分页信息，比如分页大小，数据总大小以及当前页码等等，供数据请求器使用。
 * <p>
 * Created by lingfei on 2017/6/11.
 */

public final class PageManager<T, I> {
    private IPageRequester<T, I> mPageRequester;
    private IPageResponse<T> mPageResponse;
    private IPageDataParser<T, I> mPageDataParser;
    private T mPageResponseData;
    private IPageDataCallback<I> mPageDataCallback;
    private Page<I> mPage;
    private List<IPageDataIntercept<I>> mPageDataIntercepts = new ArrayList<>();
    private AsyncTask<Object, Integer, Object[]> mInterceptorTask;

    public interface IPageDataCallback<I> {
        void onPageDataCallback(PageAction pageAction, List<I> data, boolean isFromCache);

        void onException(Context context, PageAction pageAction, Throwable t);
    }

    PageManager(final Context context, int pageSize) {
        initPageInfo(pageSize);
        this.mPageResponse = new IPageResponse<T>() {

            @Override
            public void onResponse(PageAction pageAction, T response, boolean isFromCache) {
                if (pageAction == PageAction.INIT) {
                    mPageResponseData = response;
                }
                if (pageAction == PageAction.INIT || pageAction == PageAction.REFRESH) {
                    mPage.dataSize = response == null ? 0 : mPageDataParser.getPageTotal(response, isFromCache);
                    mPage.init();
                }
                List<I> result = response == null ? new ArrayList<I>() : mPageDataParser.getPageList(response, isFromCache);
                if (result != null && result.size() != 0) {
                    mPage.mData = result.get(result.size() - 1);
                }

                mInterceptorTask = new AsyncTask<Object, Integer, Object[]>() {
                    @Override
                    protected Object[] doInBackground(Object... params) {
                        try {
                            List<I> data = getDataWithInterceptorChain((List<I>) params[0]);
                            return new Object[]{data, params[1], params[2]};
                            // 最终要适配的数据
                        } catch (Exception e) {
                            return new Object[]{e, params[1], params[2]};
                        }
                    }

                    @Override
                    protected void onPostExecute(Object[] result) {
                        super.onPostExecute(result);
                        if (result[0] instanceof Throwable) {
                            onException(context, (PageAction) result[1], (Throwable) result[0]);
                        } else {
                            mPageDataCallback.onPageDataCallback((PageAction) result[1], (List<I>) result[0], (boolean) result[2]);
                        }
                    }
                };
                mInterceptorTask.execute(result, pageAction, isFromCache);

            }

            @Override
            public void onException(Context context, PageAction pageAction, Throwable throwable) {
                mPageDataCallback.onException(context, pageAction, throwable);
            }
        };
    }

    private List<I> getDataWithInterceptorChain(List<I> result) throws Exception {
        IPageDataIntercept.Chain<I> chain = new PageDataIntercept(0, result);
        return chain.handle(result);
    }

    class PageDataIntercept implements IPageDataIntercept.Chain<I> {
        private final int index;
        private List<I> data;

        PageDataIntercept(int index, List<I> data) {
            this.index = index;
            this.data = data;
        }

        @Override
        public List<I> data() {
            return data;
        }

        @Override
        public List<I> handle(List<I> data) throws Exception {
            if (index < mPageDataIntercepts.size()) {
                IPageDataIntercept.Chain chain = new PageDataIntercept(index + 1, data);
                IPageDataIntercept<I> intercept = mPageDataIntercepts.get(index);
                List<I> interceptData = intercept.intercept(chain);

                if (interceptData == null) {
                    throw new NullPointerException("intercept " + intercept + " returned null");
                }

                return interceptData;
            }
            return data;
        }
    }

    void setPageDataIntercepts(List<IPageDataIntercept<I>> intercepts) {
        this.mPageDataIntercepts = intercepts;
    }

    void addPageDataIntercept(IPageDataIntercept<I> intercept) {
        this.mPageDataIntercepts.add(intercept);
    }

    void setPageDataCallback(IPageDataCallback<I> pageDataCallback) {
        this.mPageDataCallback = pageDataCallback;
    }

    void setPageRequester(IPageRequester<T, I> pageRequester) {
        this.mPageRequester = pageRequester;
    }

    void setPageDataParser(IPageDataParser<T, I> pageParser) {
        this.mPageDataParser = pageParser;
    }

    void initPageInfo(int pageSize) {
        mPage = new Page<>();
        mPage.pageSize = pageSize;
        mPage.currentPage = 1;
    }

    public Page<I> getPage() {
        return mPage;
    }

    void doAction(PageAction action) {
        switch (action) {
            case SEARCH:
                break;
            case INIT:
                mPage.reset();
                this.mPageRequester.onRequest(action, mPage, mPageResponse);
                break;
            case REFRESH:
                mPage.reset();
                this.mPageRequester.onRequest(action, mPage, mPageResponse);
                break;
            case LOADMORE:
                mPage.next();
                this.mPageRequester.onRequest(action, mPage, mPageResponse);
                break;
        }
    }

    void cancel() {
        mPageRequester.onCancel();
        if (mInterceptorTask != null) {
            mInterceptorTask.cancel(true);
        }
    }

    public boolean saveState(Bundle state) {
        state.putSerializable("PageManager.Page", mPage);
        return true;
    }

    public boolean restoreState(Bundle state) {
        this.mPage = (Page<I>) state.getSerializable("PageManager.Page");
        return true;
    }

    public T getPageResponseData() {
        return mPageResponseData;
    }
}
