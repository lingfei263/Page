package cn.ffb.page.core;

import java.util.List;

/**
 * Created by lingfei on 2017/6/9.
 */

public interface IPageSearcher<I> extends IStateSaved<I> {
    void onSearch(PageAction pageAction, String keyword, PageManager.IPageDataCallback<I> callback);

    void onCancel();

    void setPageData(List<I> mPageData);

    List<String> getHighlightKeywords();
}
