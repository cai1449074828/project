package com.blq.zzc.project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.blq.zzc.mylibrary.base.LazyActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WuDianLingActivity extends LazyActivity {
    @Bind(R.id.WuDianLing_DrawerLayout)
    DrawerLayout mWuDianLingDrawerLayout;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler_animation.removeCallbacksAndMessages(null);
    }

    @Bind(R.id.WuDianLing_Toolbar)
    Toolbar toolbar;
    @Bind(R.id.WuDianLing_FloatingActionButton1)
    FloatingActionButton mWuDianLingFloatingActionButton1;
    @Bind(R.id.WuDianLing_FloatingActionButton2)
    FloatingActionButton mWuDianLingFloatingActionButton2;

    @Override
    public int getLayoutId() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setEnterTransition(new Explode());
        getWindow().setEnterTransition(new Slide());
//        getWindow().setEnterTransition(new Fade());
//        getWindow().setExitTransition(new Explode());
        getWindow().setExitTransition(new Slide());
//        getWindow().setExitTransition(new Fade());
        return R.layout.activity_wu_dian_ling;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initClipping();
        initPrimaryColor();
        initFloatButton();
    }

    @Bind(R.id.textView)
    TextView textView1;
    @Bind(R.id.textView2)
    TextView textView2;

    private void initClipping() {
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        };
        ViewOutlineProvider viewOutlineProvider2 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        };
        textView1.setOutlineProvider(viewOutlineProvider);
        textView2.setOutlineProvider(viewOutlineProvider2);
    }

    private Handler handler_animation = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                mWuDianLingFloatingActionButton2.animate().translationZ(100);
                textView1.animate().translationZ(10);
                handler_animation.sendEmptyMessageDelayed(1, 1000);
            } else {
                mWuDianLingFloatingActionButton2.animate().translationZ(0);
                textView1.animate().translationZ(0);
                handler_animation.sendEmptyMessageDelayed(0, 1000);
            }

        }
    };

    private void initFloatButton() {
        mWuDianLingFloatingActionButton1.setTranslationZ(0);
        handler_animation.sendEmptyMessageDelayed(0, 1000);
    }

    private void initPrimaryColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_ceshi);
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
                ColorDrawable colorDrawable = new ColorDrawable(vibrant.getRgb());
                getSupportActionBar().setBackgroundDrawable(colorDrawable);
                Window window = getWindow();
                window.setStatusBarColor(vibrant.getRgb());
            }
        });
    }

    @Override
    public void initToolBar() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(WuDianLingActivity.this, "action_settings", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_share:
                        Toast.makeText(WuDianLingActivity.this, "action_share", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(WuDianLingActivity.this,mWuDianLingDrawerLayout,toolbar,R.string.content,R.string.menu);
        actionBarDrawerToggle.syncState();
        mWuDianLingDrawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
