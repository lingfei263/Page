package cn.ffb.page.http;

import cn.ffb.page.core.IPageRequester;
import cn.ffb.page.ui.PageFragment;

/**
 * 使用retrofit和okhttp封装的分页列表请求处理框架
 *
 * @param <T>
 * @param <I>
 * @author lingfei 2017-6-10
 */
public abstract class RetrofitPageFragment<T, I> extends PageFragment<T, I>
        implements
        RetrofitPageRequester.IPageRequestExecutor<T, I> {

    @Override
    public IPageRequester<T, I> getPageRequester() {
        return new RetrofitPageRequester<>(this.getContext(), this);
    }
}
