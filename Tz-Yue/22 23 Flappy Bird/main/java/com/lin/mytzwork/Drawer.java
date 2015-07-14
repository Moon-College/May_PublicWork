package com.lin.mytzwork;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;


import com.lin.mytzwork.util.DataUtils;

import java.util.Random;


/**
 * Created by lin on 15-7-8.
 */
public class Drawer extends Thread {
    private SurfaceHolder holder;
    private int wdith;
    private int height;
    private Context context;

    private Paint paint;
    private boolean isStart;

    private Bitmap[] pipeBmps;


    private Spirit bg, floor_bg, bird;
    private Spirit getready, panel, tap, gameover;
    private Spirit[] pipeUps, pipeDowns;

    private int distance; //障碍物宽度
    private int qk;
    private boolean isDrop;
    private boolean isAgain; //障碍物是否重来
    private boolean isOut;//障碍物是否出界了
    private int pipeWidth;
    private int pipeHeight;
    private int fw;
    private int czHeight;

    private int jf = 0;
    private int maxJf;
    private final float density;
    private int temTime; //临时记录时间

    public enum GemeStatues {
        READY, RUNNING, OVER
    }

    private final int PIPE_SPEED = 2;


    private Random random;

    public GemeStatues gemeStatue;


    public Drawer(SurfaceHolder holder, int wdith, int height, Context context) {

        density = context.getResources().getDisplayMetrics().density;
        this.holder = holder;
        this.wdith = wdith;
        this.height = height;
        this.context = context;
        this.paint = new Paint();
        this.paint.setColor(Color.GREEN);
        this.isStart = true;
        gemeStatue = GemeStatues.READY;
        this.pipeBmps = new Bitmap[]{
                getResBmp(R.mipmap.pipe1),
                getResBmp(R.mipmap.pipe2)
        };

        this.random = new Random();


        initSpirits();

        initAttrs();
    }


    public boolean isStart() {
        return isStart;
    }

    public void setIsStart(boolean isStart) {
        this.isStart = isStart;
    }

    public GemeStatues getGemeStatue() {
        return gemeStatue;
    }

    public void setGemeStatue(GemeStatues gemeStatue) {
        this.gemeStatue = gemeStatue;
    }


    @Override
    public void run() {
        super.run();
        Canvas canvas = null;
        while (isStart) {
            try {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    drawGameView(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


    private void initSpirits() {
        bird = new Spirit(new Bitmap[]{
                getResBmp(R.mipmap.bird_1),
                getResBmp(R.mipmap.bird_2),
                getResBmp(R.mipmap.bird_3)
        });

        bird.setySpeed(-80);

        //背景
        bg = new Spirit(getResBmp(R.mipmap.bg1));
        floor_bg = new Spirit(getResBmp(R.mipmap.floor));
        //积分显示
        getready = new Spirit(getResBmp(R.mipmap.getready)); //准备
        gameover = new Spirit(getResBmp(R.mipmap.gameover)); //结束了

        panel = new Spirit(getResBmp(R.mipmap.panel));
        tap = new Spirit(getResBmp(R.mipmap.tap));

        qk = bird.getWdith() * 3;
        pipeWidth = pipeBmps[0].getWidth();
        pipeHeight = pipeBmps[0].getHeight();
        fw = wdith * 3 / 4 - qk;//随机范围
        czHeight = height * 3 / 4;


        //障碍物
        distance = 4 * pipeBmps[0].getWidth();//障碍物相隔的距离
        int x = wdith / distance + 1;
        pipeUps = new Spirit[x];
        pipeDowns = new Spirit[x];
        for (int i = 0; i < x; i++) {
            Spirit pipeUp = new Spirit(pipeBmps[0]);
            Spirit pipeDown = new Spirit(pipeBmps[1]);
            reInitPipe(pipeUp, pipeDown);
            pipeUp.setXY(wdith + i * distance, 0);
            pipeDown.setXY(wdith + i * distance, pipeUp.getHeight() + qk);
            pipeDowns[i] = pipeDown;
            pipeUps[i] = pipeUp;
        }
    }


    public void initAttrs() {
        paint.setTextSize((float) (20 * density + 0.5));
        temTime = 0;

        bird.setXY(wdith / 5, height * 1 / 3);//小鸟初始化
        getready.setXY((wdith - getready.getWdith()) / 2, height / 6);
        gameover.setXY((wdith - getready.getWdith()) / 2, height / 6);
        tap.setXY((wdith - tap.getWdith()) / 2, height * 1 / 3);


        if (isDrop) { //当小鸟碰撞后 就重新初始化 障碍物的 位置 缺口 小鸟的图片
            for (int i = 0; i < pipeDowns.length; i++) {
                Spirit pipeDown = pipeDowns[i];
                Spirit pipeUp = pipeUps[i];
                reInitPipe(pipeUp, pipeDown);
                pipeUp.setXY(wdith + i * distance, 0);
                pipeDown.setXY(wdith + i * distance, pipeUp.getHeight() + qk);
            }


            bird.setBmps(new Bitmap[]{
                    getResBmp(R.mipmap.bird_1),
                    getResBmp(R.mipmap.bird_2),
                    getResBmp(R.mipmap.bird_3)
            });
        }

        isDrop = false;
        isOut = false;
        isAgain = false;
        jf = 0;

    }

    /**
     * 初始化随机高度
     *
     * @param pipeUp
     * @param pipeDown
     */
    private void reInitPipe(Spirit pipeUp, Spirit pipeDown) {

        int v = random.nextInt(fw);//得到的随机数

        Bitmap bmpUp = Bitmap.createBitmap(pipeBmps[0], 0, pipeHeight - v, pipeWidth, v);
        Bitmap bmpDown = Bitmap.createBitmap(pipeBmps[1], 0, 0, pipeWidth, height);

        pipeUp.setBmp(bmpUp);
        pipeDown.setBmp(bmpDown);

    }


    /**
     * 这里开始绘制游戏
     *
     * @param canvas
     */
    private void drawGameView(Canvas canvas) {
        canvas.drawBitmap(bg.getBmp(), null, new Rect(0, 0, wdith, height), paint); //绘制背景
        switch (gemeStatue) {
            case READY:
                drawGameReadyView(canvas);
                Log.i("info", "READY");
                break;
            case RUNNING:
                Log.i("info", "RUNNING");
                drawGameRunningView(canvas);
                break;
            case OVER:
                Log.i("info", "OVER");
                drawGameOverView(canvas);
                break;
            default:
                break;
        }
        canvas.drawBitmap(floor_bg.getBmp(), null, new Rect(0, 3 * height / 4, wdith, height), paint);//绘制地板

        //绘制当前分数
        if (gemeStatue == GemeStatues.RUNNING) {
            String jfStr = "当前分数：" + jf;
            canvas.drawText(jfStr, (int) ((wdith - paint.measureText(jfStr)) / 2), 3 * height / 4 + height / 4 / 2, paint);
        }

    }

    /**
     * 绘制 Over 时的 界面
     *
     * @param canvas
     */
    private void drawGameOverView(Canvas canvas) {
        temTime++;
        if (temTime < 1 * 100) {
            gameover.drawSelf(canvas);

            /*保持柱子原封不动*/
            for (int i = 0; i < pipeDowns.length; i++) {
                Spirit pipeDown = pipeDowns[i];
                Spirit pipeUp = pipeUps[i];
                pipeDown.drawSelf(canvas);
                pipeUp.drawSelf(canvas);
            }
            gameover.drawSelf(canvas);
            bird.drawSelf(canvas);

        } else {
            String maxStr = "最高记录：" + maxJf;
            canvas.drawText(maxStr, (wdith - paint.measureText(maxStr)) / 2, height / 6, paint);
            String nowJf = "当前记录：" + jf;
            canvas.drawText(nowJf, (wdith - paint.measureText(maxStr)) / 2, height * 2 / 6, paint);

            String hint = maxJf < jf ? "恭喜你 打破记录" : "挑战 失败 ！";
            canvas.drawText(hint, (wdith - paint.measureText(hint)) / 2, height * 3 / 6, paint);

        }

    }

    /**
     * 绘制 Running 时的界面
     *
     * @param canvas
     */
    private void drawGameRunningView(Canvas canvas) {

        int s = isDrop ? 0 : PIPE_SPEED;//小鸟的速度

        bird.setySpeed(bird.getySpeed() + 1.3f);//加速度
        float v = bird.getY() + bird.getySpeed();

        /*保证鸟不超出3/4的屏幕*/
        if (v < 0) {
            v = 0;
        }
        if (v >= czHeight - bird.getHeight()) {
            v = czHeight - bird.getHeight();
        }
        bird.setY(v);

        /*控制障碍物*/
        for (int i = 0; i < pipeDowns.length; i++) {
            Spirit pipeDown = pipeDowns[i];
            Spirit pipeUp = pipeUps[i];

            if (isAgain) {//障碍物超出了，重置障碍物
                pipeReset(pipeDown, pipeDown);
                isAgain = false;
                isOut = false;
            }
            if (!isDrop) { //鸟没有碰撞 障碍物就有 移动
                pipeDown.setX(pipeDown.getX() - s);
                pipeUp.setX(pipeDown.getX() - s);
            }

            /*当最后一个障碍物超过了 指定的距离，并且 这时候 有柱子已经 不是视线范围内 就代表可以 触发重置*/
            if (pipeDown.getX() == wdith - distance && isOut) {
                isAgain = true;
            }

            /*计算 小鸟 过了 柱子  积分 就 +1 */
            if (bird.getX() == pipeUp.getX() + bird.getWdith()) {
                jf++;
            }

            /*柱子在屏幕范围内 就 绘制*/
            if (pipeDown.getX() + pipeDown.getWdith() > 0) {
                pipeDown.drawSelf(canvas);
                pipeUp.drawSelf(canvas);
            } else {
                isOut = true;
            }

            /*碰撞检测*/
            if (bird.getSpiritRect().intersect(pipeDown.getSpiritRect()) || bird.getSpiritRect().intersect(pipeUp.getSpiritRect())) {
                bird.setBmps(null);
                isDrop = true;
            }

            /**
             * 小鸟已经碰撞，并且 小鸟已经落地 更改 游戏状态为 over
             * 并且计算积分 保存最大的积分
             * */
            if (isDrop && bird.getY() == czHeight - bird.getHeight() && gemeStatue == GemeStatues.RUNNING) {
                gemeStatue = GemeStatues.OVER;
                String maxFs = DataUtils.readData(context, "maxFs");
                maxJf = Integer.parseInt(TextUtils.isEmpty(maxFs) ? "0" : maxFs);
                DataUtils.saveData(context, "maxFs", String.valueOf(Math.max(maxJf, jf)));
            }

        }
        bird.drawSelf(canvas);
    }

    private void drawGameReadyView(Canvas canvas) {
        bird.drawSelf(canvas);
        getready.drawSelf(canvas);
        tap.drawSelf(canvas);
    }


    private Bitmap getResBmp(int bg1) {
        return BitmapFactory.decodeResource(context.getResources(), bg1);
    }


    private void pipeReset(Spirit pipeUp, Spirit pipeDown) {
        reInitPipe(pipeUp, pipeDown);
        pipeUp.setX(wdith);
        pipeDown.setX(wdith);
    }


    public boolean isDrop() {
        return isDrop;
    }

    public void setIsDrop(boolean isDrop) {
        this.isDrop = isDrop;
    }

    public Spirit getBird() {
        return bird;
    }

    public void setBird(Spirit bird) {
        this.bird = bird;
    }
}
