package cn.ffb.page.adapter;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import cn.ffb.page.core.DataAppendMode;
import cn.ffb.page.core.IPageAdapter;
import cn.ffb.page.core.SearchHelper;
import cn.ffb.adapter.list.ListAdapter;

/**
 * 数据集是List的适配器
 *
 * @param <T>
 * @author lingfei 2015-1-24
 */
public class PageListAdapter<T> extends ListAdapter<T> implements IPageAdapter<T> {
    private List<String> mKeywords;
    private List<T> mCheckedList;
    private List<T> mDisabledList;

    public PageListAdapter(Context context, List<T> listData) {
        super(context, listData);
    }

    public PageListAdapter(Context context) {
        super(context);
    }

    @Override
    public List<T> getPageListData() {
        return this.getListData();
    }

    @Override
    public DataAppendMode getDataAppendMode() {
        return DataAppendMode.MODE_AFTER;
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
        return this.getCount();
    }

    @Override
    public void appendBefore(List<T> data) {
        this.mListData.addAll(0, data);
    }

    @Override
    public void appendAfter(List<T> data) {
        this.mListData.addAll(data);
    }

    @Override
    public void setHighlightKeywords(List<String> keywords) {
        this.mKeywords = keywords;
    }

    public CharSequence highlight(String text) {
        return SearchHelper.match(text, mKeywords);
    }

    @Override
    public void setCheckedListData(List<T> data) {
        this.mCheckedList = data;
    }

    @Override
    public void setDisabledListData(List<T> data) {
        this.mDisabledList = data;
    }

    public boolean isChecked(int position) {
        return mCheckedList.contains(this.getItem(position)) || isDisabled(position);
    }

    public boolean isDisabled(int position) {
        return mDisabledList.contains(this.getItem(position));
    }

    @Override
    public void onStateRestored(Bundle state) {

    }

    @Override
    public void onStateSaved(Bundle state) {

    }
}
