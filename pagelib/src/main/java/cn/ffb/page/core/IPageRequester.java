package cn.ffb.page.core;

/**
 * 主要负责客户端向服务端的数据请求以及数据回调，框架自带了Retrofit+okhttp的请求器
 * <p>
 * Created by lingfei on 2017/6/11.
 */
public interface IPageRequester<T, I> {

    void onRequest(PageAction pageAction, Page<I> page, IPageResponse<T> pageResponse);

    void onCancel();

}
