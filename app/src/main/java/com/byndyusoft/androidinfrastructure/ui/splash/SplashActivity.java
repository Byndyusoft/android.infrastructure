package com.byndyusoft.androidinfrastructure.ui.splash;

import android.content.Intent;
import android.os.Handler;

import com.byndyusoft.androidinfrastructure.R;
import com.byndyusoft.androidinfrastructure.ui.base.BaseActivity;
import com.byndyusoft.androidinfrastructure.ui.main.MainActivity;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 28.10.2016.
 */

public class SplashActivity extends BaseActivity implements Runnable {

    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(this, 2000);
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(this);
        super.onPause();
    }

    @Override
    public void run() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
