package com.blq.zzc.project.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/10/6.
 */

public class MyViewGroup extends ViewGroup{
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private int childCount;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(measureWidth(widthMeasureSpec),measureWidth(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        childCount=getChildCount();
        for (int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
        init();
    }
    private int mScreenHeight;
    private void init() {
        WindowManager manager = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;
    }

    private int measureWidth(int widthMeasureSpec){
        int measureMode=MeasureSpec.getMode(widthMeasureSpec);
        int measureSize=MeasureSpec.getSize(widthMeasureSpec);
        if (measureMode==MeasureSpec.EXACTLY)return measureSize;
        else if (measureMode==MeasureSpec.AT_MOST)return Math.min(300,measureSize);
        else return measureSize;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        childCount=getChildCount();
        MarginLayoutParams marginLayoutParams= (MarginLayoutParams) getLayoutParams();
        marginLayoutParams.height=mScreenHeight*childCount;
        setLayoutParams(marginLayoutParams);
        System.out.println("mScreenHeight*childCount"+mScreenHeight*childCount);
        System.out.println("getHeight"+getHeight());
        for (int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            if (childView.getVisibility()!=View.GONE){
                childView.layout(l,i*mScreenHeight,r,(i+1)*mScreenHeight);
            }
        }
    }
    private int lastY;
    private int start;
    private int end;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                lastY=y;
                start=getScrollY();
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                if (!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                int dy=lastY-y;
                if (getScrollY()<0){
                    System.out.println("进入1");
                    dy=0;
                }
                else if (getScrollY()>mScreenHeight*childCount-mScreenHeight){
                    System.out.println("mScreenHeight"+mScreenHeight);
                    System.out.println("getHeight()"+getHeight());
                    dy=0;
                }
                scrollBy(0,dy);
                lastY=y;
                break;
            }
            case MotionEvent.ACTION_UP:{
                end=getScrollY();
                int dScrollY=end-start;
                System.out.println("开始"+end+":"+start);
                if (dScrollY>0){
                    System.out.println("开始1");
                    if (dScrollY<mScreenHeight/3){
                        scroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }
                    else {
                        scroller.startScroll(0,getScrollY(),0,mScreenHeight-dScrollY);
                    }
                }
                else {
                    System.out.println("开始2");
                    if (-dScrollY<mScreenHeight/3){
                        scroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }
                    else {
                        scroller.startScroll(0,getScrollY(),0,-mScreenHeight-dScrollY);
                    }
                }
                break;
            }
        }
        postInvalidate();
        return true;
    }

    private Scroller scroller=new Scroller(getContext());
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(0,scroller.getCurrY());
            postInvalidate();
        }
    }
}
