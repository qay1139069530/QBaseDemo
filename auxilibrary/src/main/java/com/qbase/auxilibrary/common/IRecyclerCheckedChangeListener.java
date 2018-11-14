package com.qbase.auxilibrary.common;

import android.support.annotation.IdRes;
import android.widget.RadioGroup;

/**
 * Created by Qay on 2017/3/21.
 * RecyclerView点击事件
 */

public interface IRecyclerCheckedChangeListener {
    void onItemClick(RadioGroup group, @IdRes int checkedId);
}
