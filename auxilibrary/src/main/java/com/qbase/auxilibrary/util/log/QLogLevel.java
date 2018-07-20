package com.qbase.auxilibrary.util.log;

/**
 * LOG Level
 */
public enum QLogLevel {
    DEBUG("调试"), INFO("信息"), WARNING("警告"), ERROR("错误");

    public String descript;//描述

    QLogLevel(String descript) {
        this.descript = descript;
    }
}
