package cn.ffb.page.app;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import cn.ffb.page.adapter.PageRecyclerListAdapter;
import cn.ffb.page.app.entity.Tracks;
import cn.ffb.adapter.recycler.ItemViewProvider2;
import cn.ffb.adapter.recycler.ViewHolder;

/**
 * Created by lingfei on 2017/6/20.
 */

public class AlbumCheckedPageAdapter extends PageRecyclerListAdapter<Tracks> {
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener;
    private View.OnClickListener mOnCheckBoxClickListener;

    public AlbumCheckedPageAdapter(Context context) {
        super(context);
        this.register(new ItemViewProvider2<Tracks>() {

            @Override
            public boolean isForProvider(int position, Tracks entity) {
                return true;
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position, Tracks entity) {
                super.onBindViewHolder(viewHolder, position, entity);
                CheckBox checkBox = viewHolder.getView(R.id.checkbox);
                viewHolder.setChecked(R.id.checkbox, isChecked(position))
                        .setText(R.id.text, highlight(entity.getTitle()));
                checkBox.setEnabled(!isDisabled(position));
                checkBox.setTag(position);
                checkBox.setOnCheckedChangeListener(mOnCheckedChangeListener);
                checkBox.setOnClickListener(mOnCheckBoxClickListener);
            }

            @Override
            public int getItemViewLayoutId() {
                return R.layout.listitem_checked;
            }
        });
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        this.mOnCheckedChangeListener = listener;
    }

    public void setOnCheckBoxClickListener(View.OnClickListener listener) {
        this.mOnCheckBoxClickListener = listener;
    }
}
