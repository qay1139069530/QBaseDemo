package com.qbase.auxilibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**base activity*/
public abstract class QBaseBarAct extends QBaseAct{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusColor();
        initialize();
    }

    /**初始化*/
    protected abstract void initialize();

}
