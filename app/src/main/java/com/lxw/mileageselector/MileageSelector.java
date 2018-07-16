package com.lxw.mileageselector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author  LiXiaoWei
 * date  2018/7/16.
 * desc:
 */

public class MileageSelector extends View {
    private int sCircleRadius = 11;
    private int bCircleRadius = 44;
    private int width;
    private int height;
    private Paint whitePaint;
    private Paint whiteCirclePaint, bluePaint, redPaint, blackPaint;
    private int mileageValue = 3;

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
        canvas.drawRect(sCircleRadius, height / 2 - 12, width * mileageValue / 5, height / 2 + 10, whitePaint);
        canvas.drawRect(width * mileageValue / 5, height / 2 - 12, width - sCircleRadius, height / 2 + 12, bluePaint);
        switch (mileageValue) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            default:
                break;

        }
        canvas.drawCircle(sCircleRadius, height / 2, sCircleRadius, whitePaint);
        canvas.drawCircle(width / 5, height / 2, 22, whitePaint);
        canvas.drawCircle(width * 2 / 5, height / 2, 22, whitePaint);

        canvas.drawCircle(width * 2 / 5, height / 2, 44, redPaint);
        canvas.drawLine(width * 2 / 5 + 10, height / 2 - 10, width * 2 / 5 + 10, height / 2 + 10, blackPaint);
        canvas.drawLine(width * 2 / 5 + 20, height / 2 - 10, width * 2 / 5 + 20, height / 2 + 10, blackPaint);
        canvas.drawLine(width * 2 / 5 + 30, height / 2 - 10, width * 2 / 5 + 30, height / 2 + 10, blackPaint);


        canvas.drawCircle(width * 3 / 5, height / 2, 22, bluePaint);
        canvas.drawCircle(width * 4 / 5, height / 2, 22, bluePaint);
        canvas.drawCircle(width - 22, height / 2, 22, bluePaint);
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg);
        //canvas.drawBitmap(bitmap, 100, 100, whitePaint);
    }

    public void setMileageValue(int mileageValue) {
        this.mileageValue = mileageValue / 2 - 1;
    }
}
