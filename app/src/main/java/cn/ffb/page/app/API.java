package cn.ffb.page.app;

import cn.ffb.page.app.entity.AlbumInfo;
import cn.ffb.http.core.ICall;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lingfei on 2017/6/20.
 */

public interface API {

    @GET("search-ajaxsearch-searchall")
    ICall<AlbumInfo> getAlbumInfo(@Query("kw") String keyword, @Query("pz") int pageSize, @Query("pi") int pageIndex);

}
