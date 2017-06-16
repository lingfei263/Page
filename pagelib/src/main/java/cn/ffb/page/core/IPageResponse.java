package cn.ffb.page.core;

import android.content.Context;

/**
 * Created by lingfei on 2017/6/11.
 */

public interface IPageResponse<T> {
    void onResponse(PageAction pageAction, T response, boolean isFromCache);

    void onException(Context context, PageAction pageAction, Throwable throwable);
}
