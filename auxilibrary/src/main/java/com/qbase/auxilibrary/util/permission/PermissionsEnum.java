package com.qbase.auxilibrary.util.permission;

/**
 * Enum class to handle the different states
 * of permissions since the PackageManager only
 * has a granted and denied state.
 */
enum PermissionsEnum {
    GRANTED,
    DENIED,
    NOT_FOUND
}
