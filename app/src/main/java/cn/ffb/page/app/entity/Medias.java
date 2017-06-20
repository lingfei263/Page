package cn.ffb.page.app.entity;

import java.io.Serializable;

/**
 * Created by lingfei on 2017/6/20.
 */
public class Medias implements Serializable {
    private int bitrate;

    private String p2purl;

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public void setP2purl(String p2purl) {
        this.p2purl = p2purl;
    }

    public String getP2purl() {
        return this.p2purl;
    }

}