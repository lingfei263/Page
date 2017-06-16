package cn.ffb.page.http;

import android.content.Context;

import cn.ffb.page.core.IPageRequester;
import cn.ffb.page.ui.PageDialog;

/**
 * 使用retrofit和okhttp封装的分页列表请求处理框架
 *
 * @param <T>
 * @param <I>
 * @author lingfei 2017-5-10
 */
public abstract class RetrofitPageDialog<T, I> extends PageDialog<T, I>
        implements
        RetrofitPageRequester.IPageRequestExecutor<T, I> {

    public RetrofitPageDialog(Context context) {
        super(context);
    }

    @Override
    public IPageRequester<T, I> getPageRequester() {
        return new RetrofitPageRequester<>(this.getContext(), this);
    }
}
