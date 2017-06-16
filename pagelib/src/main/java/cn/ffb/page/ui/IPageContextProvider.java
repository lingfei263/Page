package cn.ffb.page.ui;

import android.view.View;

import cn.ffb.page.core.IPageAdapter;
import cn.ffb.page.core.IPageDataParser;
import cn.ffb.page.core.IPageRequester;

/**
 * 分页组件提供接口
 *
 * @author lingfei 2017-5-4
 */
public interface IPageContextProvider<T, I> {

    /**
     * 获取分页列表界面的适配配器
     *
     * @return
     */
    IPageAdapter<I> getPageAdapter();

    /**
     * 数据请求器
     *
     * @return
     */
    IPageRequester<T, I> getPageRequester();

    /**
     * 获取分页解析器
     *
     * @return
     */
    IPageDataParser<T, I> getPageDataParser();

    /**
     * 分页配置
     *
     * @param pageConfig
     */
    void onPageConfig(PageConfig pageConfig);

    /**
     * 获取分页视图
     *
     * @return
     */
    View getPageView();

}
