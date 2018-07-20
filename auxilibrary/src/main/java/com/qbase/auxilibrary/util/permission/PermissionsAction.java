package com.qbase.auxilibrary.util.permission;

import android.Manifest;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum class to handle the different states
 * of permissions since the PackageManager only
 * has a granted and denied state.
 */
class PermissionsAction {
    public static List<String> getPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.CALL_PHONE);
        permissions.add(Manifest.permission.READ_CONTACTS);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissions;
    }

}
