package com.blq.zzc.project.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blq.zzc.project.R;

/**
 * Created by Administrator on 2016/9/17.
 */
public class TopBar extends RelativeLayout {
    public TopBar(Context context) {
        super(context);
    }
    private String titleText;
    private int titleTextColor;
    private float titleTextSize;
    private String leftText;
    private int leftTextColor;
    private Drawable leftBackground;
    private String rightText;
    private int rightTextColor;
    private Drawable rightBackground;
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        leftText=ta.getString(R.styleable.TopBar_leftText);
        leftTextColor=ta.getColor(R.styleable.TopBar_leftTextColor,0);
        leftBackground=ta.getDrawable(R.styleable.TopBar_leftBackground);

        rightText=ta.getString(R.styleable.TopBar_rightText);
        rightTextColor=ta.getColor(R.styleable.TopBar_rightTextColor,0);
        rightBackground=ta.getDrawable(R.styleable.TopBar_rightBackground);

        titleText=ta.getString(R.styleable.TopBar_cenerText);
        titleTextColor=ta.getColor(R.styleable.TopBar_cenerTextColor,0);
        titleTextSize=ta.getDimension(R.styleable.TopBar_cenerTextSize,10);
        ta.recycle();
        addView();
    }
    private TextView titleView;
    private ImageView leftButton;
    private ImageView rightButton;
    private void addView(){
        leftButton=new ImageView(getContext());
//        leftButton.setText(leftText);
        leftButton.setBackground(leftBackground);
//        leftButton.setTextColor(leftTextColor);


        rightButton=new ImageView(getContext());
//        rightButton.setText(rightText);
        rightButton.setBackground(rightBackground);
//        rightButton.setTextColor(rightTextColor);

        titleView=new TextView(getContext());
        titleView.setText(titleText);
        titleView.setTextColor(titleTextColor);
        titleView.setTextSize(titleTextSize);
        titleView.setGravity(Gravity.CENTER);

        LayoutParams centerLayoutParams=  new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        centerLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(titleView,centerLayoutParams);

        LayoutParams leftLayoutParams=  new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftButton,leftLayoutParams);

        LayoutParams rightLayoutParams=  new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton,rightLayoutParams);
    }
    TopClickListener topClickListener;
    public void setOnClickListener(TopClickListener topClickListener){
        this.topClickListener=topClickListener;
    }
    public interface TopClickListener{
        void leftClick();
        void rightClick();
    }
}
