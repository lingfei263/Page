package cn.ffb.page.simple;

import android.content.Context;

import java.util.List;

import cn.ffb.page.core.Page;
import cn.ffb.page.core.PageAction;
import cn.ffb.page.core.PageRequester;

/**
 * Created by lingfei on 2017/6/11.
 */

public abstract class SimplePageRequester<T> extends PageRequester<List<T>, T> {

    public SimplePageRequester(Context context) {
        super(context);
    }

    @Override
    public void executeRequest(Context context, PageAction pageAction, Page page) {
        this.callNetworkResponse(getSimplePageData());
    }

    @Override
    public void onCancel() {

    }

    public abstract List<T> getSimplePageData();
}
