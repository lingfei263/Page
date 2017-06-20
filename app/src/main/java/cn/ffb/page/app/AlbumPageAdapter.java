package cn.ffb.page.app;

import android.content.Context;

import cn.ffb.page.adapter.PageListAdapter;
import cn.ffb.page.core.DataAppendMode;
import cn.ffb.page.app.entity.Tracks;
import cn.ffb.adapter.core.ViewHolder;
import cn.ffb.adapter.list.ItemViewProvider2;

/**
 * Created by lingfei on 2017/6/20.
 */

public class AlbumPageAdapter extends PageListAdapter<Tracks> {

    public AlbumPageAdapter(Context context) {
        super(context);
        this.register(new ItemViewProvider2<Tracks>() {
            @Override
            public int getItemViewLayoutId() {
                return android.R.layout.simple_list_item_1;
            }

            @Override
            public void setupView(ViewHolder viewHolder, int position, Tracks entity) {
                viewHolder.setText(android.R.id.text1, highlight(entity.getTitle()));
            }

            @Override
            public boolean isForProvider(int position, Tracks entity) {
                return true;
            }
        });
    }

    @Override
    public DataAppendMode getDataAppendMode() {
        return DataAppendMode.MODE_BEFORE;
    }
}
