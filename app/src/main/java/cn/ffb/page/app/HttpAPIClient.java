package cn.ffb.page.app;

import android.content.Context;

import cn.ffb.http.core.HttpRequester;

/**
 * Created by lingfei on 2017/6/20.
 */

public class HttpAPIClient {

    public static void init(Context context) {
        HttpRequester.init(context);
        HttpRequester.registerXAPI("http://v5.pc.duomi.com", API.class);
    }

    public static API getAPI() {
        return HttpRequester.getAPI(API.class);
    }
}
