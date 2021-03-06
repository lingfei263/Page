package cn.ffb.page.adapter;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import cn.ffb.page.core.DataAppendMode;
import cn.ffb.page.core.IPageAdapter;
import cn.ffb.page.core.SearchHelper;
import cn.ffb.adapter.expand.BaseExpandableListAdapter;

public abstract class PageExpandableListAdapter<G, C> extends BaseExpandableListAdapter<G, C>
        implements
        IPageAdapter<G> {
    private List<String> mKeywords;
    private List<G> mCheckedList;
    private List<G> mDisabledList;

    public PageExpandableListAdapter(Context context, List<G> listData) {
        super(context, listData);
    }

    public PageExpandableListAdapter(Context context) {
        super(context);
    }

    @Override
    public List<G> getPageListData() {
        return this.getListData();
    }

    /**
     * 清空列表数据
     */
    @Override
    public void clear() {
        mListData.clear();
    }

    @Override
    public int getPageDataCount() {
        return this.getGroupCount();
    }

    @Override
    public void appendBefore(List<G> data) {
        this.mListData.addAll(0, data);
    }

    @Override
    public void appendAfter(List<G> data) {
        this.mListData.addAll(data);
    }

    @Override
    public DataAppendMode getDataAppendMode() {
        return DataAppendMode.MODE_AFTER;
    }

    @Override
    public void setHighlightKeywords(List<String> keywords) {
        this.mKeywords = keywords;
    }

    public CharSequence highlight(String text) {
        return SearchHelper.match(text, mKeywords);
    }

    @Override
    public void setCheckedListData(List<G> data) {
        this.mCheckedList = data;
    }

    @Override
    public void setDisabledListData(List<G> data) {
        this.mDisabledList = data;
    }

    public boolean isChecked(int position) {
        return mCheckedList.contains(this.getGroup(position)) || isDisabled(position);
    }

    public boolean isDisabled(int position) {
        return mDisabledList.contains(this.getGroup(position));
    }

    @Override
    public void onStateRestored(Bundle state) {

    }

    @Override
    public void onStateSaved(Bundle state) {

    }
}
