package cn.ffb.page.app;

import android.content.Context;

import cn.ffb.page.adapter.PageRecyclerListAdapter;
import cn.ffb.page.core.DataAppendMode;
import cn.ffb.page.app.entity.Tracks;
import cn.ffb.adapter.recycler.ItemViewProvider2;
import cn.ffb.adapter.recycler.ViewHolder;

/**
 * Created by lingfei on 2017/6/20.
 */

public class AlbumRecyclerPageAdapter extends PageRecyclerListAdapter<Tracks> {

    public AlbumRecyclerPageAdapter(Context context) {
        super(context);
        this.register(new ItemViewProvider2<Tracks>() {

            @Override
            public boolean isForProvider(int position, Tracks entity) {
                return true;
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position, Tracks entity) {
                super.onBindViewHolder(viewHolder, position, entity);
                viewHolder.setText(android.R.id.text1, highlight(entity.getTitle()));
            }

            @Override
            public int getItemViewLayoutId() {
                return android.R.layout.simple_list_item_1;
            }
        });
    }

    @Override
    public DataAppendMode getDataAppendMode() {
        return DataAppendMode.MODE_BEFORE;
    }

}
