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
    private int sCircleRadius = ToolUtil.dp2px(6);
    private int bCircleRadius = ToolUtil.dp2px(11);
    private int lineHeight = ToolUtil.dp2px(6);
    private int width;
    private int height;
    private Paint whitePaint, bluePaint, redPaint, blackPaint;
    private float progress;
    private OnRangeChangedListener onRangeChangedListener;


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
        redPaint.setColor(getResources().getColor(R.color.white));
        redPaint.setStyle(Paint.Style.FILL);
        redPaint.setAntiAlias(true);


        blackPaint = new Paint();
        blackPaint.setColor(getResources().getColor(R.color.blue1));
        blackPaint.setStyle(Paint.Style.FILL);
        blackPaint.setStrokeWidth(ToolUtil.dp2px(1));
        blackPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        progress = width * 2 / 5;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(bCircleRadius, height / 2 - lineHeight / 2, progress, height / 2 + lineHeight / 2, whitePaint);
        canvas.drawRect(progress, height / 2 - lineHeight / 2, width - bCircleRadius, height / 2 + lineHeight / 2, bluePaint);

        canvas.drawCircle(bCircleRadius, height / 2, sCircleRadius, progress * 5 / width >= 0 ? whitePaint : bluePaint);
        canvas.drawCircle(width / 5, height / 2, sCircleRadius, progress * 5 / width >= 1 ? whitePaint : bluePaint);
        canvas.drawCircle(width * 2 / 5, height / 2, sCircleRadius, progress * 5 / width >= 2 ? whitePaint : bluePaint);
        canvas.drawCircle(width * 3 / 5, height / 2, sCircleRadius, progress * 5 / width >= 3 ? whitePaint : bluePaint);
        canvas.drawCircle(width * 4 / 5, height / 2, sCircleRadius, progress * 5 / width >= 4 ? whitePaint : bluePaint);
        canvas.drawCircle(width - bCircleRadius, height / 2, sCircleRadius, progress * 5 / width >= 5 ? whitePaint : bluePaint);
        float bigStartPos;
        if (progress <= bCircleRadius) {
            bigStartPos = bCircleRadius;
        } else if (progress >= width - bCircleRadius) {
            bigStartPos = width - bCircleRadius;
        } else {
            bigStartPos = progress;
        }
        canvas.drawCircle(bigStartPos, height / 2, bCircleRadius, redPaint);
        /*Drawable drawable = App.getAppContext().getResources().getDrawable(R.mipmap.ic_kk);
        BitmapDrawable  bd = (BitmapDrawable) drawable;
        Bitmap bp= bd.getBitmap();
        canvas.drawBitmap(bp,bigStartPos,height / 2,redPaint);*/
        canvas.drawLine(bigStartPos - lineHeight / 2, height / 2 - lineHeight * 2 / 3, bigStartPos - lineHeight / 2, height / 2 + lineHeight * 2 / 3, blackPaint);
        canvas.drawLine(bigStartPos, height / 2 - lineHeight * 2 / 3, bigStartPos, height / 2 + lineHeight * 2 / 3, blackPaint);
        canvas.drawLine(bigStartPos + lineHeight / 2, height / 2 - lineHeight * 2 / 3, bigStartPos + lineHeight / 2, height / 2 + lineHeight * 2 / 3, blackPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("location", String.format("%s----DOWN-----%s", event.getX(), event.getY()));
                Log.d("location", String.format("%s----width-----s", width));
                break;
            case MotionEvent.ACTION_MOVE:
                progress = event.getX();
                //moveMileage(event.getX());
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
        int mileage = 6;
        if (x < width / 10) {
            progress = 0;
            mileage = 2;
        } else if (x >= width / 10 && x < width * 3 / 10) {
            progress = width / 5;
            mileage = 4;
        } else if (x >= width * 3 / 10 && x < width * 5 / 10) {
            progress = width * 2 / 5;
            mileage = 6;
        } else if (x >= width * 5 / 10 && x < width * 7 / 10) {
            progress = width * 3 / 5;
            mileage = 8;
        } else if (x >= width * 7 / 10 && x < width * 9 / 10) {
            progress = width * 4 / 5;
            mileage = 10;
        } else if (x >= width * 9 / 10) {
            progress = width;
            mileage = 12;
        }
        if (onRangeChangedListener != null) {
            onRangeChangedListener.onRangeChanged(mileage);
        }
    }

    public void setMileageValue(int mileageValue) {
        int mileage;
        if (mileageValue < 38000) {
            progress = 0;
            mileage = 2;
        } else if (mileageValue < 58000) {
            progress = width / 5;
            mileage = 4;
        } else if (mileageValue < 78000) {
            progress = width * 2 / 5;
            mileage = 6;
        } else if (mileageValue < 98000) {
            progress = width * 3 / 5;
            mileage = 8;
        } else if (mileageValue < 118000) {
            progress = width * 4 / 5;
            mileage = 10;
        } else {
            progress = width;
            mileage = 12;
        }
        invalidate();
        if (onRangeChangedListener != null) {
            onRangeChangedListener.onRangeChanged(mileage);
        }
    }

    public interface OnRangeChangedListener {
        void onRangeChanged(int mileage);
    }

    public void setOnRangeChangedListener(OnRangeChangedListener callBack) {
        this.onRangeChangedListener = callBack;
    }
}
