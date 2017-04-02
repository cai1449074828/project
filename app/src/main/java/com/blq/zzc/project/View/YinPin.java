package com.blq.zzc.project.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2016/10/6.
 */

public class YinPin extends View{
    public YinPin(Context context) {
        super(context);
    }

    public YinPin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureWidth(heightMeasureSpec));
    }
    private int measureWidth(int widthMeasureSpec){
        int measureMode=MeasureSpec.getMode(widthMeasureSpec);
        int measureSize=MeasureSpec.getSize(widthMeasureSpec);
        if (measureMode==MeasureSpec.EXACTLY)return measureSize;
        else if (measureMode==MeasureSpec.AT_MOST)return Math.min(300,measureSize);
        else return measureSize;
    }
    private int geshu;
    private Paint paint=new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Random random=new Random();
        geshu=5+random.nextInt(5);
//        canvas.drawColor(Color.BLACK);
        for (int i=0;i<geshu;i++){
            int height=random.nextInt(getMeasuredHeight());
            canvas.drawRect(0+(getMeasuredWidth()/geshu)*i,getMeasuredHeight()-height,(getMeasuredWidth()/geshu)*(i+1)-10,getMeasuredHeight(),paint);
        }
        postInvalidateDelayed(800+random.nextInt(500));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float width=getWidth();
        float rectHeight=getHeight();
        int rectWidth=(int)(width*0.6/rectHeight);
        LinearGradient linearGradient=new LinearGradient(0,0,rectWidth,rectHeight,Color.WHITE,Color.GRAY, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
    }

    private void setGeShu(int geshu){
        this.geshu=geshu;
    }
}
