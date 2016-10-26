package com.byndyusoft.androidinfrastructure.utils.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

public class PermissionRequest {

    int requestCode;
    OnPermissionsGranted onPermissionsGranted;
    OnPermissionsCancelled onPermissionsCancelled;

    List<String> permissions = new ArrayList<>();
    PermissionsManager permissionsManager;

    PermissionRequest(@NonNull final PermissionsManager permissionsManager, int requestCode) {
        this.requestCode = requestCode;
        this.permissionsManager = permissionsManager;
    }

    public PermissionRequest addPermission(@NonNull final String permission) {
        permissions.add(permission);
        return this;
    }

    public PermissionRequest onGranted(OnPermissionsGranted l) {
        this.onPermissionsGranted = l;
        return this;
    }

    public PermissionRequest onCancelled(OnPermissionsCancelled l) {
        this.onPermissionsCancelled = l;
        return this;
    }

    @DebugLog
    String[] permissionsToArray(@NonNull final Context context) {
        List<String> perms = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                perms.add(permission);
            }
        }
        String[] arr = new String[perms.size()];
        perms.toArray(arr);
        return arr;
    }

    @DebugLog
    public void requestPermission(@NonNull final Activity activity) {
        permissionsManager.requestPermission(activity, this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @DebugLog
    public void requestPermission(@NonNull final Fragment fragment) {
        permissionsManager.requestPermission(fragment, this);
    }

    @DebugLog
    public  void requestPermission(@NonNull final android.support.v4.app.Fragment fragment) {
        permissionsManager.requestPermission(fragment, this);
    }

    void recycle() {
        onPermissionsGranted = null;
        onPermissionsCancelled = null;
        permissions.clear();
        permissions = null;
        permissionsManager = null;
    }
}

