package cn.ffb.page.core;

/**
 * Created by lingfei on 2017/6/13.
 */

public interface OnPageCheckedInitListener<I> {
    boolean isEnable(int position, I entity);

    boolean isChecked(int position, I entity);
}
