package cn.ffb.page.http;

import cn.ffb.page.core.IPageRequester;
import cn.ffb.page.ui.PageActivity;
/**
 * Created by lingfei on 2017/6/11.
 */
public abstract class RetrofitPageActivity<T, I> extends PageActivity<T, I>
        implements
        RetrofitPageRequester.IPageRequestExecutor<T, I> {

    @Override
    public IPageRequester<T, I> getPageRequester() {
        return new RetrofitPageRequester<>(this, this);
    }


}
