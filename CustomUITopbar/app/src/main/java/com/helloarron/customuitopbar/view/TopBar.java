package com.helloarron.customuitopbar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.helloarron.customuitopbar.R;

/**
 * Created by arron on 16/4/13.
 */
public class TopBar extends RelativeLayout {

    private Button leftButton, rightButton;
    private TextView tvTitle;

    private String leftText;
    private int leftTextColor;
    private Drawable leftBackground;

    private String rightText;
    private int rightTextColor;
    private Drawable rightBackground;

    private String title;
    private float titleTextSize;
    private int titleTextColor;

    private LayoutParams leftParams, rightParams, titleParams;

    /**
     * 接口
     * @param context
     * @param attrs
     */
    public interface TopBarClickListener{
        void leftClick();
        void rightClick();
    }

    private TopBarClickListener listener;

    public void setOnTopBarClickListener(TopBarClickListener listener){
        this.listener = listener;
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        leftText = ta.getString(R.styleable.TopBar_leftText);
        leftTextColor= ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);

        rightText = ta.getString(R.styleable.TopBar_rightText);
        rightTextColor= ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);

        title = ta.getString(R.styleable.TopBar_title);
        titleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        titleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 0);

        ta.recycle();

        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        leftButton.setText(leftText);
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);

        rightButton.setText(rightText);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);

        tvTitle.setText(title);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0xfff58563);

        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);

        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        addView(leftButton, leftParams);
        addView(rightButton, rightParams);
        addView(tvTitle, titleParams);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }

    public void setLeftIsVisible(boolean flag) {
        if (flag){
            leftButton.setVisibility(View.VISIBLE);
        }else{
            leftButton.setVisibility(View.GONE);
        }
    }
}

