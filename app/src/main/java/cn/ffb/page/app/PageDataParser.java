package cn.ffb.page.app;

import java.util.ArrayList;
import java.util.List;

import cn.ffb.page.core.IPageDataParser;
import cn.ffb.page.app.entity.AlbumInfo;
import cn.ffb.page.app.entity.Tracks;

/**
 * Created by lingfei on 2017/6/20.
 */

public class PageDataParser implements IPageDataParser<AlbumInfo, Tracks> {

    @Override
    public List<Tracks> getPageList(AlbumInfo data, boolean isFromCache) {
        if (data == null) {
            return new ArrayList<>();
        }
        return data.getTracks();
    }

    @Override
    public long getPageTotal(AlbumInfo data, boolean isFromCache) {
        if (data == null) {
            return 0;
        }
        return data.getTotal_tracks();
    }
}
