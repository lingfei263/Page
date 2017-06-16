package cn.ffb.page.simple;

import android.content.Context;

import java.util.List;

import cn.ffb.page.core.IPageDataParser;
import cn.ffb.page.core.IPageRequester;
import cn.ffb.page.ui.PageDialog;

/**
 * Created by lingfei on 2017/6/11.
 */

public abstract class SimplePageDialog<I> extends PageDialog<List<I>, I> {

    public SimplePageDialog(Context context) {
        super(context);
    }

    @Override
    public IPageDataParser<List<I>, I> getPageDataParser() {
        return new SimplePageDataParser();
    }

    @Override
    public IPageRequester<List<I>, I> getPageRequester() {
        return new SimplePageRequester<I>(this.getContext()) {
            @Override
            public List<I> getSimplePageData() {
                return getPageData();
            }
        };
    }

    public abstract List<I> getPageData();

}
