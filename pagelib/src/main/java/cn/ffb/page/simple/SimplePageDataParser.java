package cn.ffb.page.simple;

import java.util.List;

import cn.ffb.page.core.IPageDataParser;

/**
 * Created by lingfei on 2017/6/11.
 */

public class SimplePageDataParser<I> implements IPageDataParser<List<I>, I> {

    @Override
    public List<I> getPageList(List<I> data, boolean isFromCache) {
        return data;
    }

    @Override
    public long getPageTotal(List<I> data, boolean isFromCache) {
        return data.size();
    }
}
