package cn.ffb.page.app;

import android.content.Context;

import cn.ffb.page.adapter.PageListAdapter;
import cn.ffb.adapter.core.ViewHolder;
import cn.ffb.adapter.list.ItemViewProvider2;

/**
 * Created by lingfei on 2017/6/20.
 */

public class SimplePageAdapter extends PageListAdapter<String> {

    public SimplePageAdapter(Context context) {
        super(context);
        this.register(new ItemViewProvider2<String>() {
            @Override
            public int getItemViewLayoutId() {
                return android.R.layout.simple_list_item_1;
            }

            @Override
            public void setupView(ViewHolder viewHolder, int position, String entity) {
                viewHolder.setText(android.R.id.text1, entity);
            }

            @Override
            public boolean isForProvider(int position, String entity) {
                return true;
            }
        });
    }
}
