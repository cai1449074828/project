package com.blq.zzc.project.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

import com.blq.zzc.project.ListActivity;

/**
 * Created by Administrator on 2016/10/19.
 */

public class MyListView extends ListView{
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMmaxOverScrollY();
    }

    private void initMmaxOverScrollY() {
        mmaxOverScrollY=getMmaxOverScrollY();
    }

    /**
     * Scroll the view with standard behavior for scrolling beyond the normal
     * content boundaries. Views that call this method should override
     * {@link #onOverScrolled(int, int, boolean, boolean)} to respond to the
     * results of an over-scroll operation.
     * <p>
     * Views can use this method to handle any touch or fling-based scrolling.
     *
     * @param deltaX         Change in X in pixels
     * @param deltaY         Change in Y in pixels
     * @param scrollX        Current X scroll value in pixels before applying deltaX
     * @param scrollY        Current Y scroll value in pixels before applying deltaY
     * @param scrollRangeX   Maximum content scroll range along the X axis
     * @param scrollRangeY   Maximum content scroll range along the Y axis
     * @param maxOverScrollX Number of pixels to overscroll by in either direction
     *                       along the X axis.
     * @param maxOverScrollY Number of pixels to overscroll by in either direction
     *                       along the Y axis.
     * @param isTouchEvent   true if this scroll operation is the result of a touch event.
     * @return true if scrolling was clamped to an over-scroll boundary along either
     * axis, false otherwise.
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mmaxOverScrollY, isTouchEvent);
    }
    private int mmaxOverScrollY=30;

    public int getMmaxOverScrollY() {
        DisplayMetrics displayMetrics=getContext().getResources().getDisplayMetrics();
        float density=displayMetrics.density;
        System.out.println("density*mmaxOverScrollY="+density*mmaxOverScrollY);
        return (int) (density*mmaxOverScrollY);
    }
}
