package com.canplay.medical.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;


import com.canplay.medical.R;
import com.canplay.medical.bean.KPI;

import java.util.ArrayList;
import java.util.List;

public class HistogramView extends View {

    private Paint xLinePaint;// 坐标轴 轴线 画笔：
    private Paint hLinePaint;// 坐标轴水平内部 虚线画笔
    private Paint titlePaint;// 绘制文本的画笔
    private Paint chartLinePaint;// 折线
    private Paint paint;// 矩形画笔 柱状图的样式信息
    private int[] progress = { 2000, 5000, 6000, 8000, 500, 6000, 9000 };// 7
    // 条，显示各个柱状的数据
    private int[] aniProgress;// 实现动画的值
    private List<Integer> aniProgres;// 实现动画的值

    private final int TRUE = 1;// 在柱状图上显示数字
    private int[] text;// 设置点击事件，显示哪一条柱状的信息
    private Bitmap bitmap;
    // 坐标轴左侧的数标
    private String[] ySteps;
    private List<String> yStep=new ArrayList<>();
    // 坐标轴底部的星期数
    private String[] xWeeks;
    private List<String> xWeek;
    private int flag;// 是否使用动画
    private Context context;
    private int cout=7;
    private HistogramAnimation ani;

    public HistogramView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    private List<KPI> list;
    int max=0;
    public void setDatas(List<KPI> list){
        this.list=list;
         max=0;
      for(KPI kpi:list){
          if(Integer.valueOf(kpi.getY_axis())>=max){
              max= Integer.valueOf(kpi.getY_axis());
          }
      }
        yStep.clear();
        double add = max / 7.0;
        for(int i=0;i<9;i++){
            yStep.add((add==0?100*(8-i):(int)add*(8-i))+"");
      }
//        "month": "Dec",
//                "y_axis": 0
    }
    private void init() {

        ySteps = new String[] { "90Hg", "100Hg", "110Hg", "120Hg", "130Hg", "140Hg", "150Hg", "160Hg" };
        xWeeks = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"
                , "Aug", "Sep", "Oct", "Nov", "Dec" };
        text = new int[] { 0, 0, 0, 0, 0, 0, 0 , 0, 0, 0, 0, 0};
        aniProgress = new int[] { 500, 300, 900, 350, 680, 690, 720 , 900, 350, 680, 690, 720 };
        ani = new HistogramAnimation();
        ani.setDuration(2000);

        xLinePaint = new Paint();
        hLinePaint = new Paint();
        titlePaint = new Paint();
        chartLinePaint = new Paint();

        paint = new Paint();

        // 给画笔设置颜色
        xLinePaint.setColor(Color.DKGRAY);


        hLinePaint.setColor(Color.LTGRAY);
        titlePaint.setColor(getResources().getColor(R.color.color9));
        //绘制的折线
        chartLinePaint.setStyle(Paint.Style.FILL);
        chartLinePaint.setStrokeWidth(3);
        chartLinePaint.setColor(Color.RED);//(1)黄色
        chartLinePaint.setAntiAlias(true);
        // 加载画图
        bitmap = BitmapFactory
                .decodeResource(context.getResources(), R.drawable.menu_bg);
    }

    public void start(int flag) {
        this.flag = flag;
        this.startAnimation(ani);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight() - dp2px(50);

        int leftHeight = height - dp2px(5);// 左侧外周的 需要划分的高度：



            int hPerHeight = height / (ySteps.length-1);// 分成四部分
            // 绘制底部的线条
            canvas.drawLine(dp2px(30), height+dp2px(5  ) , width - dp2px(30), height+dp2px(5), xLinePaint);
            canvas.drawLine(dp2px(30), dp2px(2),dp2px(30), dp2px(10) + height-dp2px(6), hLinePaint);
            hLinePaint.setTextAlign(Align.CENTER);
            // 设置四条虚线
            for (int i = 0; i < ySteps.length-1; i++) {
                if(i==4){
                    canvas.drawLine(dp2px(30), i * hPerHeight, width
                            - dp2px(30), i * hPerHeight, hLinePaint);
                }

            }

            // 绘制 Y 周坐标
            titlePaint.setTextAlign(Align.RIGHT);
            titlePaint.setTextSize(sp2px(8));
            titlePaint.setAntiAlias(true);
            titlePaint.setStyle(Paint.Style.FILL);
            // 设置左部的数字
            for (int i = 0; i <  ySteps.length; i++) {
                canvas.drawText(ySteps[i], dp2px(25), dp2px(13) + i * hPerHeight,
                        titlePaint);
            }

            // 绘制 X 周 做坐标
            int xAxisLength = width - dp2px(30);
//            int columCount = list.size() + 1;
//            int step = (xAxisLength-dp2px(12)) / columCount;

//            // 设置底部的数字
//            for (int i = 0; i < columCount - 1; i++) {
//                // text, baseX, baseY, textPaint
//                canvas.drawText(list.get(i).month, dp2px(27) + step * (i + 1), height
//                        + dp2px(14), titlePaint);
//            }

            canvas.drawLine(58, 120, 108, 60, chartLinePaint);
            canvas.drawLine(108, 60, 160, 160, chartLinePaint);
            canvas.drawLine(160, 160, 220, 100, chartLinePaint);
            canvas.drawLine(220, 130, 290, 80, chartLinePaint);
            canvas.drawLine(290, 80, 360, 200, chartLinePaint);
            canvas.drawLine(360, 200, 410, 240, chartLinePaint);






    }
    float gridX,gridY,xSpace = 0,ySpace = 0,spaceYT = 0,yStart=0;
    String[] dateX = null;
    float[] dateY = null;

    List<float[]> data = null;
    private int dp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    private int sp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (v * value + 0.5f);
    }

    /**
     * 设置点击事件，是否显示数字
     */
    public boolean onTouchEvent(MotionEvent event) {
        int step = (getWidth() - dp2px(30)) / 8;
        int x = (int) event.getX();
        for (int i = 0; i < 12; i++) {
            if (x > (dp2px(15) + step * (i + 1) - dp2px(15))
                    && x < (dp2px(15) + step * (i + 1) + dp2px(15))) {
                text[i] = 1;
                for (int j = 0; j < 12; j++) {
                    if (i != j) {
                        text[j] = 0;
                    }
                }
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    invalidate();
                } else {
                    postInvalidate();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 集成animation的一个动画类
     *
     * @author 李垭超
     */
    private class HistogramAnimation extends Animation {
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f && flag == 2) {
                for (int i = 0; i < aniProgress.length; i++) {
                    aniProgress[i] = (int) (progress[i] * interpolatedTime);
                }
            } else {
                for (int i = 0; i < aniProgress.length; i++) {
                    aniProgress[i] = progress[i];
                }
            }
            invalidate();
        }
    }

}