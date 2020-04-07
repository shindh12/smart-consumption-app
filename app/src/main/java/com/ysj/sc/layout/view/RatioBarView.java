package com.ysj.sc.layout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import androidx.core.content.ContextCompat;

import com.ysj.sc.R;

public class RatioBarView extends View {
    private static final String TAG = RatioBarView.class.getSimpleName();
    private Context context;
    private int score; // 0 ~ 100
    private int mColor;
    private float mStrokeWidth;

    public RatioBarView(Context context) {
        super(context);
        this.context = context;
    }

    // xml 에 있는 속성값을 참조하려고 전달받음
    public RatioBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        init(attributeSet);
    }

    public RatioBarView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.context = context;
        init(attributeSet);
    }


    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            // activity 에서 지정한 속성 값 획득
            TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.RatioBarView);
            mColor = array.getColor(R.styleable.RatioBarView_foregroundColor, ContextCompat.getColor(getContext(), R.color.colorPrimary));
            mStrokeWidth = array.getDimension(R.styleable.RatioBarView_strokeWidth, 60f);
        }
    }

    // activity 에서 score 전달 목적으로 호출
    public void setScore(int score) {
        this.score = score;
        // 새로운 score 전달되면 그림 다시 그려야함
        invalidate(); // 자동으로 onDraw() 함수 다시 호출
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width  =  this.getMeasuredWidth() - 30;
        int height = this.getMeasuredHeight() - 120;

        score = 40;
        // 화면 지우고 다시 그려야함
        RectF rectF = new RectF(30, 70, width, 2*height);
        // 그리기 속성
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(80f);
        paint.setFakeBoldText(true);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(score + "%", (int) (width / 2) + 20, height - 30, paint);
        paint.setTextSize(26f);
        canvas.drawText("카드 소비 분배 일치율", (int) (width / 2) + 18, height + 40, paint);

        paint.setColor(ContextCompat.getColor(context, R.color.ac_unused_color));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        //기본원 회색으로 360도 그려내고
        canvas.drawArc(rectF, 180, 180, false, paint);
        float endAngle = (180 * score) / 100f;
        // score 그리기
        paint.setColor(mColor);
        // 0도가 동쪽이고 북쪽은 -90
        canvas.drawArc(rectF, 180, endAngle, false, paint);


    }
}
