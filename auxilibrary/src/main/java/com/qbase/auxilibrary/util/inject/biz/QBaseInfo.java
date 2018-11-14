package com.qbase.auxilibrary.util.inject.biz;

/**
 * view info
 */
 final class QBaseInfo {
    public int value;
    public int parentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QBaseInfo viewInfo = (QBaseInfo) o;

        if (value != viewInfo.value) return false;
        return parentId == viewInfo.parentId;

    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + parentId;
        return result;
    }
}
