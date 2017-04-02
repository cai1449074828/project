package com.blq.zzc.project.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blq.zzc.project.R;

/**
 * Created by Administrator on 2016/9/15.
 */
public class MyView2 extends TextView{
    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureWidth(heightMeasureSpec));
    }
    private int measureWidth(int measureSpec){
        int result=0;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        if(specMode==MeasureSpec.EXACTLY){
            result=specSize;
        }
        else if (specMode==MeasureSpec.AT_MOST){
            result=200;
            result=Math.min(result,specSize);
        }
        mViewWidth=specSize;
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint1=new Paint();
        paint1.setColor(getResources().getColor(android.R.color.darker_gray));
        paint1.setStyle(Paint.Style.FILL);
        Paint paint2=new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),paint1);
        canvas.drawRect(30,30,getMeasuredWidth()-30,getMeasuredHeight()-30,paint2);
        canvas.save();
        canvas.translate(30,30);
        super.onDraw(canvas);
        canvas.restore();
//        Bitmap bitmap=Bitmap.createBitmap(bitmap2.getWidth(),bitmap2.getHeight(),bitmap2.getConfig());
//        Canvas mCanvas=new Canvas(bitmap);
//        mCanvas.drawBitmap(bitmap2,0,0,new Paint());
        if (mGradientMatrix!=null){
            t+=Math.PI/8;
            mTranslate= (int) (Math.cos(t)*getMeasuredWidth());
            mGradientMatrix=new Matrix();
            mGradientMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
//            postInvalidateDelayed(100);
            postInvalidate();
        }
        Bitmap bitmap2= BitmapFactory.decodeResource( getResources(), R.drawable.ic_account_circle_black_18dp);
        if (mTranslate>0)canvas.drawBitmap(bitmap2,0,0,new Paint());
    }
    private double t=0;
    private int mViewWidth;
    private int mTranslate;
    private Paint mPaint;
    private Matrix mGradientMatrix;
    private LinearGradient mLinearGradient;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLinearGradient=new LinearGradient(0,0,mViewWidth,0,new int[]{Color.BLUE,0xffffffff},null, Shader.TileMode.CLAMP);
        mPaint=getPaint();
        mPaint.setShader(mLinearGradient);
        mGradientMatrix=new Matrix();
    }
}
