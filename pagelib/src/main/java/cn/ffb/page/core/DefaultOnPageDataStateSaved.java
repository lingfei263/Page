package cn.ffb.page.core;

import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lingfei on 2017/6/11.
 */

public class DefaultOnPageDataStateSaved<I> implements OnPageDataStateSaved<I> {

    @Override
    public void onStateSaved(Bundle state, String key, List<I> entity) {
        if (entity instanceof Serializable) {
            state.putSerializable(key, (Serializable) entity);
        }
    }

    @Override
    public List<I> onStateRestored(Bundle state, String key) {
        List<I> list = (List<I>) state.getSerializable(key);
        return list;
    }

}
