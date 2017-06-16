package cn.ffb.page.core;

import java.util.List;

/**
 * 可以checked的接口
 *
 * @author lingfei
 *         <p>
 *         2017-6-11
 */
public interface IPageChecker<T> extends PageCheckerInterface, IStateSaved<T> {

    void setPageData(List<T> pageData);

    void appendPageData(List<T> pageData);

}
