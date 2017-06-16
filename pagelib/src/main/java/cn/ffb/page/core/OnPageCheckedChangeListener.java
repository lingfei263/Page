package cn.ffb.page.core;

import java.util.List;

/**
 * Created by lingfei on 2017/6/12.
 */

public interface OnPageCheckedChangeListener<I> {
    void onPageCheckedChanged(List<I> checkedList, int count);
}
