package cn.ffb.page.core;

import android.os.Bundle;

import java.util.List;

/**
 * Created by lingfei on 2017/6/12.
 */

public interface OnPageDataStateSaved<I> {
    void onStateSaved(Bundle state, String key, List<I> entity);

    List<I> onStateRestored(Bundle state, String key);
}
