package com.blq.zzc.project.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/6.
 */

public class BiLiTu extends View{
    public BiLiTu(Context context) {
        super(context);
    }
    public BiLiTu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(setMeasure(widthMeasureSpec),setMeasure(heightMeasureSpec));
    }

    private int setMeasure(int widthMeasureSpec) {
        int measureMode=MeasureSpec.getMode(widthMeasureSpec);
        int measureSize=MeasureSpec.getSize(widthMeasureSpec);
        if (measureMode==MeasureSpec.EXACTLY)return measureSize;
        else if (measureMode==MeasureSpec.AT_MOST)return Math.min(200,measureSize);
        else return measureSize;
    }

    private float length;
    private float circle_xy;
    private float radius;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        length=getMeasuredWidth();
        circle_xy=length/2;
        radius=length/4;
        Paint paint_circle=new Paint();
        paint_circle.setColor(Color.BLACK);
        canvas.drawCircle(circle_xy,circle_xy,radius,paint_circle);

        RectF rectF=new RectF((int)(length*0.1),(int)(length*0.1),(int)(length*0.9),(int)(length*0.9));
        Paint paint_man=new Paint();
        paint_man.setAntiAlias(true);                       //设置画笔为无锯齿
        paint_man.setColor(Color.BLUE);                    //设置画笔颜色
        paint_man.setStrokeWidth(length/10);              //线宽
        paint_man.setStyle(Paint.Style.STROKE);
        Paint paint_woman=new Paint();
        paint_woman.setAntiAlias(true);                       //设置画笔为无锯齿
        paint_woman.setColor(Color.YELLOW);                    //设置画笔颜色
        paint_woman.setStrokeWidth(length/10);              //线宽
        paint_woman.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,0,60,false,paint_man);
        canvas.drawArc(rectF,180,181,false,paint_man);//加一度，否则有间隙从180度开始，绘制181度的弧
        canvas.drawArc(rectF,60,120,false,paint_woman);

        Paint paint_text=new Paint();
        paint_text.setColor(Color.WHITE);
        String string_text="男女比例";
        paint_text.setTextSize(length/20);
        canvas.drawText(string_text,circle_xy-length/20,circle_xy,paint_text);
    }
}
