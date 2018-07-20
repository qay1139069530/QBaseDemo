package com.qbase.auxilibrary.util.permission;

/**
 * Created by qay
 */

public interface PermissionGrant {
    /**
     * permission request success
     */
    void onPermissionGranted(int requestCode);
}
