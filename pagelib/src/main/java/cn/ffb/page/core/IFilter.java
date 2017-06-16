package cn.ffb.page.core;

/**
 * Created by lingfei on 2017/6/11.
 */

public interface IFilter<I> {

    boolean filter(String keyword, I entity);

}
