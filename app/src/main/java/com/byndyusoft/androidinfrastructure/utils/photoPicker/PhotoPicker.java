package com.byndyusoft.androidinfrastructure.utils.photoPicker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

public class PhotoPicker {

    private static int REQUEST_CODE = 1000;

    private Uri tempUri;

    private OnPhotoPicked onPhotoPickedListener;

    private Activity activity;

    private Fragment fragment;

    private android.support.v4.app.Fragment supportFragment;

    public PhotoPicker(Activity activity) {
        this.activity = activity;
    }

    public PhotoPicker(Fragment fragment) {
        this.fragment = fragment;
    }

    public PhotoPicker(android.support.v4.app.Fragment supportFragment) {
        this.supportFragment = supportFragment;
    }

    public void pickFromGallery(OnPhotoPicked onPhotoPicked) {
        this.onPhotoPickedListener = onPhotoPicked;
        startActivity(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
    }

    private Context getContext() {
        if (activity != null) {
            return activity;
        } else if (fragment != null) {
            return fragment.getActivity();
        } else if (supportFragment != null) {
            return supportFragment.getContext();
        } else {
            return null;
        }
    }

    public void pickPhotoFromCamera(OnPhotoPicked onPhotoPicked) {
        this.onPhotoPickedListener = onPhotoPicked;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            tempUri = Uri.fromFile(ImageFilePathUtils.createTempImageFile(getContext()));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void startActivity(Intent intent) {
        if (supportFragment != null) {
            supportFragment.startActivityForResult(intent, REQUEST_CODE);
        } else if (fragment != null) {
            fragment.startActivityForResult(intent, REQUEST_CODE);
        } else if (activity != null) {
            activity.startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            if (onPhotoPickedListener != null) {
                Uri uri;
                if (data != null && data.getData() != null){
                    uri = data.getData();
                } else {
                    uri = tempUri;
                }
                onPhotoPickedListener.onPhotoPicked(uri);
            }
        }
    }


    public interface OnPhotoPicked {
        void onPhotoPicked(Uri uri);
    }

}
