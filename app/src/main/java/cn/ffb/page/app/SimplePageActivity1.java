package cn.ffb.page.app;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.ffb.page.core.IPageDataIntercept;
import cn.ffb.page.simple.SimpleListViewPageActivity;

/**
 * Created by lingfei on 2017/6/20.
 */

public class SimplePageActivity1 extends SimpleListViewPageActivity<String> {

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        this.setListAdapter(new SimplePageAdapter(this));
        this.addPageDataIntercept(new IPageDataIntercept<String>() {
            @Override
            public List<String> intercept(Chain<String> chain) throws Exception {
                List<String> data = chain.data();
                data.add(0, "拦截器增加的条目1");
                return chain.handle(data);
            }
        });
        this.addPageDataIntercept(new IPageDataIntercept<String>() {
            @Override
            public List<String> intercept(Chain<String> chain) throws Exception {
                List<String> data = chain.data();
                data.add(0, "拦截器增加的条目2");
                return chain.handle(data);
            }
        });
    }

    // 如果是非耗时操作，则可以直接返回要适配的数据
    @Override
    public List<String> getPageData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("条目" + i);
        }
        return data;
    }

}
