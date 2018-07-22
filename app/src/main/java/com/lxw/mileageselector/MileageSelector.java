package com.lxw.mileageselector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * author  LiXiaoWei
 * date  2018/7/16.
 * desc:
 */

public class MileageSelector extends View {
    private int sCircleRadius = 30;
    private int bCircleRadius = ToolUtil.dp2px(11);
    private int lineHeight = ToolUtil.dp2px(6);
    private int width;
    private int height;
    private Paint whitePaint;
    private Paint whiteCirclePaint, bluePaint, redPaint, blackPaint;
    private int mileageValue = 2;

    public MileageSelector(Context context) {
        this(context, null);
    }

    public MileageSelector(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MileageSelector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        whitePaint = new Paint();
        whitePaint.setColor(getResources().getColor(R.color.white));
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setAntiAlias(true);

        bluePaint = new Paint();
        bluePaint.setColor(getResources().getColor(R.color.bg));
        bluePaint.setStyle(Paint.Style.FILL);
        bluePaint.setAntiAlias(true);

        redPaint = new Paint();
        redPaint.setColor(getResources().getColor(R.color.red));
        redPaint.setStyle(Paint.Style.FILL);
        redPaint.setAntiAlias(true);


        blackPaint = new Paint();
        blackPaint.setColor(getResources().getColor(R.color.black));
        blackPaint.setStyle(Paint.Style.FILL);
        blackPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        int h = getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(bCircleRadius, height / 2 - lineHeight/2, width * mileageValue / 5, height / 2 + lineHeight/2, whitePaint);
        canvas.drawRect(width * mileageValue / 5, height / 2 - lineHeight/2, width - bCircleRadius, height / 2 + lineHeight/2, bluePaint);
        for (int i = 0; i < 6; i++) {
            if (i <= mileageValue) {
                if (i == 0) {
                    canvas.drawCircle(width * i / 5 + bCircleRadius, height / 2, sCircleRadius, whitePaint);
                } else {
                    canvas.drawCircle(width * i / 5, height / 2, sCircleRadius, whitePaint);
                }

            } else {
                if (i == 5) {
                    canvas.drawCircle(width * i / 5 - bCircleRadius, height / 2, sCircleRadius, bluePaint);
                } else {
                    canvas.drawCircle(width * i / 5, height / 2, sCircleRadius, bluePaint);
                }
            }
        }
        if (mileageValue == 0) {
            canvas.drawCircle(width * mileageValue / 5 + bCircleRadius, height / 2, bCircleRadius, redPaint);
        } else if (mileageValue == 5) {
            canvas.drawCircle(width * mileageValue / 5 - bCircleRadius, height / 2, bCircleRadius, redPaint);
        } else {
            canvas.drawCircle(width * mileageValue / 5, height / 2, bCircleRadius, redPaint);
        }
        //        canvas.drawLine(width * 2 / 5 + 10, height / 2 - 10, width * 2 / 5 + 10, height / 2 + 10, blackPaint);
        //        canvas.drawLine(width * 2 / 5 + 20, height / 2 - 10, width * 2 / 5 + 20, height / 2 + 10, blackPaint);
        //        canvas.drawLine(width * 2 / 5 + 30, height / 2 - 10, width * 2 / 5 + 30, height / 2 + 10, blackPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("location", String.format("%s----DOWN-----%s", event.getX(), event.getY()));
                Log.d("location", String.format("%s----width-----s", width));
                break;
            case MotionEvent.ACTION_MOVE:
                moveMileage(event.getX());
                Log.d("location", String.format("%s----MOVE-----%s", event.getX(), event.getY()));
                break;
            case MotionEvent.ACTION_UP:
                moveMileage(event.getX());
                Log.d("location", String.format("%s-----UP----%s", event.getX(), event.getY()));
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    private void moveMileage(float x) {
        if (x < width / 10) {
            mileageValue = 0;
        } else if (x >= width / 10 && x < width * 3 / 10) {
            mileageValue = 1;
        } else if (x >= width * 3 / 10 && x < width * 5 / 10) {
            mileageValue = 2;
        } else if (x >= width * 5 / 10 && x < width * 7 / 10) {
            mileageValue = 3;
        } else if (x >= width * 7 / 10 && x < width * 9 / 10) {
            mileageValue = 4;
        } else if (x >= width * 9 / 10) {
            mileageValue = 5;
        }
    }

    public void setMileageValue(int mileageValue) {
        this.mileageValue = mileageValue / 2 - 1;
        invalidate();
    }
}
