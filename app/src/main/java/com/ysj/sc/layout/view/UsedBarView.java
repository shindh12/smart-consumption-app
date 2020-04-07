package com.ysj.sc.layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.ysj.sc.R;

public class UsedBarView extends View {
    private static final String TAG = UsedBarView.class.getSimpleName();
    private Context context;
    private double ratio; // 0 ~ 1

    public UsedBarView(Context context) {
        super(context);
        this.context = context;
    }

    public UsedBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public UsedBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public UsedBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width  =  this.getMeasuredWidth();
        int height = this.getMeasuredHeight();

        canvas.drawColor(Color.alpha(R.color.used_color));

        Rect unusedRect = new Rect(0, 0, width, height);
        // 그리기 속성
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.unused_color));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);

        // 기본 unused_color 그려내고
        canvas.drawRect(unusedRect, paint);
        // score 그리기
        width = (int) (width * ratio);
        Rect usedRect = new Rect(0, 0, width, height);
        paint.setColor(ContextCompat.getColor(context, R.color.used_color));
        canvas.drawRect(usedRect, paint);
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
        invalidate();
    }
}
