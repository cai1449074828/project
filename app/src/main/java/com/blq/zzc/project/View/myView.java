package com.blq.zzc.project.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/5/23.
 */
public class myView extends View{
    Paint paint;
    public myView(Context context) {
        super(context);
        Init();
    }
    public myView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }
    private void Init() {
        paint= new Paint();
        paint.setColor(Color.WHITE);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(125,100,25,paint);
        canvas.drawRect(100,100,150,150,paint);
        Path path2=new Path();
        path2.moveTo(100,100);
        path2.lineTo(150,75);
        path2.lineTo(200,75);
        path2.lineTo(150,100);
        path2.close();
        canvas.drawPath(path2, paint);
        paint.setColor(Color.WHITE);
        Path path3=new Path();
        path3.moveTo(200,75);
        path3.lineTo(200,125);
        path3.lineTo(150,150);
        path3.lineTo(150,100);
        path3.close();
        canvas.drawPath(path3, paint);
        Paint paint2=new Paint();
        Path path4=new Path();
        path4.moveTo(100,100);
        path4.lineTo(150,100);
        path4.lineTo(200,75);
        path4.lineTo(200,125);
        path4.lineTo(150,150);
        path4.lineTo(150,100);
        path4.close();
        canvas.drawPath(path4, paint2);
//        Shader mShader=new LinearGradient(0,0,100,100, new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW}, null,Shader.TileMode.REPEAT);
//        paint.setShader(mShader);
    }
}
