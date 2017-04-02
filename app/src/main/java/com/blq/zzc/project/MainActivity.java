package com.blq.zzc.project;


import android.animation.Animator;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.blq.zzc.project.Bomb.BombActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {
    @Bind(R.id.Main_FloatingActionButton)
    FloatingActionButton mMainFloatingActionButton;
    @Bind(R.id.circle_CircularReveal)
    ImageView mCircleCircularReveal;
    @Bind(R.id.rect_CircularReveal)
    ImageView mRectCircularReveal;
    @Bind(R.id.button_biLiTu)
    Button mButtonBiLiTu;
    @Bind(R.id.listButton)
    Button mListButton;
    @Bind(R.id.bomb)
    Button mBomb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Init();
        initCircularReaveal();
    }

    private void initCircularReaveal() {
        mCircleCircularReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(mCircleCircularReveal, mCircleCircularReveal.getWidth() / 2, mCircleCircularReveal.getHeight() / 2, mCircleCircularReveal.getWidth(), 0);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
        mRectCircularReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(mRectCircularReveal, 0, 0, 0, (float) Math.hypot(mRectCircularReveal.getWidth(), mRectCircularReveal.getHeight()));
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
    }

    AnimationSet animationSet;
    private AnimationDrawable animationDrawable;
    private ImageView imageView;
    private int NoTIFICATION_ID_COLLAPSE = 0;
    @Bind(R.id.Main_ImageView_animation)
    ImageView mMain_ImageView_animation;

    private void Init() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), xiaoQiu.class));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WuDianLingActivity.class), ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, mMainFloatingActionButton, "floatButton").toBundle());
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessageDelayed(0, 2000);
            }
        });
        mButtonBiLiTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewActivity.class));
            }
        });
        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });
        mBomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BombActivity.class));
            }
        });
        animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.set_splash);
        animationSet.setAnimationListener(this);
        imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.setBackgroundResource(R.drawable.drawable_animation);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMain_ImageView_animation.setImageDrawable(getResources().getDrawable(R.drawable.stat_animation));
            }
        });
        mMain_ImageView_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(v);
            }
        });

    }

    private static int[] STATE_CHECKED = new int[]{android.R.attr.state_checked};
    private static int[] STATE_UNCHECKED = new int[]{};
    private boolean mIsCheck;

    public void anim(View view) {
        if (mIsCheck) {
            System.out.println("去勾");
            mMain_ImageView_animation.setImageState(STATE_UNCHECKED, true);
            mIsCheck = false;
        } else {
            System.out.println("打勾");
            mMain_ImageView_animation.setImageState(STATE_CHECKED, true);
            mIsCheck = true;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                Intent intent=new Intent();;
//                intent.setAction("com.xiazdong.action");
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                builder.setSmallIcon(R.drawable.ic_backup_black_18dp);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_swap_vertical_circle_black_18dp));
                builder.setContentTitle("这是标题");
                builder.setContentText("这是内容");
                builder.setSubText("这我不知道");
                builder.setColor(getResources().getColor(android.R.color.darker_gray));
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setFullScreenIntent(pendingIntent, true);
                RemoteViews contentViews = new RemoteViews(getPackageName(), R.layout.notification_practice);
                contentViews.setTextViewText(R.id.textView, "这这这");
                Notification notification = builder.build();
//                notification.contentView=contentViews;
                RemoteViews expanded = new RemoteViews(getPackageName(), R.layout.notification_practice_expanded);
                Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
                inboxStyle.setBigContentTitle("1111111111");

                builder.setStyle(inboxStyle);
                notification.bigContentView = expanded;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(NoTIFICATION_ID_COLLAPSE, notification);
            }
        }
    };

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

