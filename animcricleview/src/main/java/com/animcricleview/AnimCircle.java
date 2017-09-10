package com.animcricleview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by 47420 on 2017/9/8.
 */

public class AnimCircle extends View {
    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束

    private static float currentValue = 0f;  //???
    private float arc_y = 0f;  //???
    private float tb ; // ???
    private float roundWidth; // 圆的细粗


    private float fStrokeWidth = 1.2f;  //圆圈的粗细
    private Handler mHandler;
    private Context mContext;           //上下文
    private int mWidth, mHeight;        // 宽高

    private Paint mPaint = new Paint();
    private RectF mRectF ;   //精度为float的可以操作矩形区域

    private boolean isCheck = false;//判断是否选中

    public AnimCircle(Context context) {
        super(context);
        init(context);
    }

    public AnimCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化画笔
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        Resources res = getResources();
        tb = res.getDimension(R.dimen.margin_10);
        roundWidth = 0.25f * tb;
//        mRectF = new RectF(roundWidth * 6, roundWidth * 6, 16.5f * tb, 16.5f * tb);
        mRectF = new RectF(0, 0, 16.5f * tb, 16.5f * tb);



        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(fStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true); //反锯齿

        this.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    public boolean onPreDraw() {
                        new thread();
                        getViewTreeObserver().removeOnPreDrawListener(this);
                        return false;
                    }
                });

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

            }
        };
    }

    /**
     * View大小确定
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     * 绘制内容
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 移动坐标系到画布中央
//        canvas.translate(mWidth / 2, mHeight / 2);
//        canvas.drawCircle(0, 0, 200, mPaint);

        canvas.drawArc(mRectF, -90, arc_y, false, mPaint);
//        canvas.drawCircle(0,0,arc_y,mPaint);

    }




    private class thread implements Runnable {
        private Thread thread;
        private int statek;
        int count;

        public thread() {
            thread = new Thread(this);
            thread.start();
        }

        public void run() {
            while (true) {
                switch (statek) {
                    case 0:
                        try {
                            Thread.sleep(400);
                            statek = 1;
                        } catch (InterruptedException e) {
                        }
                        break;
                    case 1:
                        try {
                            Thread.sleep(15);
                            arc_y += 3.6f;
                            count++;
                            postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                }
//                if (count >= score - 2)
//                    break;
            }
        }
    }

    public void begin(){
        mPaint.setColor(Color.YELLOW);

        //圆第一部分动画，0 至 score-1
        this.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    public boolean onPreDraw() {
                        new thread();
                        getViewTreeObserver().removeOnPreDrawListener(this);
                        return false;
                    }
                });
    }

//    public void cleanDraw(){
//        Canvas canvas = sh.lockCanvas();
//        canvas.drawColor(Color.TRANSPARENT);
//        Paint p = new Paint();
//        //清屏
//        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        canvas.drawPaint(p);
//        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
//    }

}
