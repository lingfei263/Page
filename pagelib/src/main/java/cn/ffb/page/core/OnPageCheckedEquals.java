package cn.ffb.page.core;

/**
 * Created by lingfei on 2017/6/12.
 */

public interface OnPageCheckedEquals<T> {
    /**
     * 两个实体之间是否相等
     *
     * @param t1
     * @param t2
     * @return
     */
    boolean equals(T t1, T t2);
}
