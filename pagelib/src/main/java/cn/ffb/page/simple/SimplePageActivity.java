package cn.ffb.page.simple;

import java.util.List;

import cn.ffb.page.core.IPageDataParser;
import cn.ffb.page.core.IPageRequester;
import cn.ffb.page.ui.PageActivity;

/**
 * Created by lingfei on 2017/6/11.
 */
public abstract class SimplePageActivity<I> extends PageActivity<List<I>, I> {

    @Override
    public IPageDataParser<List<I>, I> getPageDataParser() {
        return new SimplePageDataParser();
    }

    @Override
    public IPageRequester<List<I>, I> getPageRequester() {
        return new SimplePageRequester<I>(this) {
            @Override
            public List<I> getSimplePageData() {
                return getPageData();
            }
        };
    }

    public abstract List<I> getPageData();
}
