package cn.ffb.page.core;

import android.os.Bundle;

/**
 * Created by lingfei on 2017/6/11.
 */

interface IStateSaved<I> {
    boolean saveState(Bundle state, OnPageDataStateSaved<I> listener);

    boolean restoreState(Bundle state, OnPageDataStateSaved<I> listener);
}
