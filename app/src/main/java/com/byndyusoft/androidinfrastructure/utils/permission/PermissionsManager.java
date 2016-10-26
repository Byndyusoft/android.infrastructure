package com.byndyusoft.androidinfrastructure.utils.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.HashMap;

import hugo.weaving.DebugLog;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

public class PermissionsManager {

    private int requestCode = 1;
    private HashMap<Integer, PermissionRequest> permissionRequests = new HashMap<>();

    @DebugLog
    public PermissionRequest buildRequest() {
        PermissionRequest permissionRequest = new PermissionRequest(this, requestCode);
        requestCode++;
        return permissionRequest;
    }

    @DebugLog
    void requestPermission(@NonNull final Activity activity, @NonNull final PermissionRequest permissionRequest) {
        if (hasAllPermissions(activity, permissionRequest)) {
            permissionRequest.onPermissionsGranted.onGranted();
            return;
        }
        permissionRequests.put(permissionRequest.requestCode, permissionRequest);
        ActivityCompat.requestPermissions(activity,
                permissionRequest.permissionsToArray(activity),
                permissionRequest.requestCode);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @DebugLog
    void requestPermission(@NonNull final Fragment fragment, @NonNull final PermissionRequest permissionRequest) {
        if (hasAllPermissions(fragment.getActivity(), permissionRequest)) {
            permissionRequest.onPermissionsGranted.onGranted();
            return;
        }
        permissionRequests.put(permissionRequest.requestCode, permissionRequest);
        fragment.requestPermissions(permissionRequest.permissionsToArray(fragment.getActivity()), permissionRequest.requestCode);
    }

    @DebugLog
    void requestPermission(@NonNull final android.support.v4.app.Fragment fragment, @NonNull final PermissionRequest permissionRequest) {
        if (hasAllPermissions(fragment.getContext(), permissionRequest)) {
            permissionRequest.onPermissionsGranted.onGranted();
            return;
        }
        permissionRequests.put(permissionRequest.requestCode, permissionRequest);
        fragment.requestPermissions(permissionRequest.permissionsToArray(fragment.getContext()), permissionRequest.requestCode);
    }

    @DebugLog
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissionRequests.containsKey(requestCode)) {
            PermissionRequest permissionRequest = permissionRequests.get(requestCode);
            permissionRequests.remove(requestCode);
            for (int res : grantResults) {
                if (res != PackageManager.PERMISSION_GRANTED) {
                    if (permissionRequest.onPermissionsCancelled != null) {
                        permissionRequest.onPermissionsCancelled.onCancelled();
                    }
                    permissionRequest.recycle();
                    return;
                }
            }
            if (permissionRequest.onPermissionsGranted != null) {
                permissionRequest.onPermissionsGranted.onGranted();
            }
            permissionRequest.recycle();
        }
    }

    private static boolean hasAllPermissions(@NonNull final Context context, @NonNull final PermissionRequest permissionRequest) {
        return permissionRequest.permissionsToArray(context).length ==  0;
    }
}

