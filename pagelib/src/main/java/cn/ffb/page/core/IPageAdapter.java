package cn.ffb.page.core;

import android.os.Bundle;

import java.util.List;

/**
 * Created by lingfei on 2017/6/11.
 * <p>
 * 主要负责将解析后的数据适配到Adapter上并显示出来，
 * <p>
 * 如ListAdapter、ExpandableListAdapter以及RecyclerAdapter，
 * <p>
 * 只要实现了此接口，都是可以进行数据适配的。
 */
public interface IPageAdapter<I> {

    int getPageDataCount();

    void clear();

    void appendBefore(List<I> data);

    void appendAfter(List<I> data);

    List<I> getPageListData();

    void notifyDataSetChanged();

    // 数据附加模式
    DataAppendMode getDataAppendMode();

    void setHighlightKeywords(List<String> keywords);

    void setCheckedListData(List<I> data);

    void setDisabledListData(List<I> data);

    void onStateSaved(Bundle state);

    void onStateRestored(Bundle state);
}
