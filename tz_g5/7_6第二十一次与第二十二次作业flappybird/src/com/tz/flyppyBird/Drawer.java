package com.tz.flyppyBird;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by qinhan on 15/7/9.
 */


public class Drawer extends Thread {
    //画家绘画需要基本的画板，画笔，图形
    private Context context;
    private SurfaceHolder holder;
    private int w, h;//屏幕或者是画板的宽高
    private Paint paint;
    private boolean isStart;
    private int pipeSpan;//管子间距
    private int pipeTopHeight1, pipeTopHeight2;//上面两根管子的高度
    private Spirit bg, floor, bird, getReady, tap, pipeTop1, pipeTop2, pipeBottom1, pipeBottom2, gameOver, panel, Score;
    private int gameStatu;
    private boolean isDrop;//鸟是否已经坠落
    private final int PIPE_SPEED = -5;
    private int score;
    private Paint mScorePaint;

    public boolean isDrop() {
        return isDrop;
    }

    public void setDrop(boolean isDrop) {
        this.isDrop = isDrop;
    }

    public Spirit getBird() {
        return bird;
    }

    public void setBird(Spirit bird) {
        this.bird = bird;
    }

    public static final int READY = 0;
    public static final int RUNNING = 1;

    public int getGameStatu() {
        return gameStatu;
    }

    public void setGameStatu(int gameStatu) {
        this.gameStatu = gameStatu;
    }

    public static final int OVER = 2;
    private Bitmap pipeBottomBm, pipeTopBm;

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    public Drawer(Context context, SurfaceHolder holder, int w, int h) {
        this.context = context;
        this.holder = holder;
        this.w = w;
        this.h = h;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        isStart = true;

        //创建游戏的精灵
        initSpirits();
        //初始化精灵的属性
        initAttrs();
    }

    /**
     * 初始化精灵的坐标速度等属性
     */
    public void initAttrs() {
        isDrop = false;
        score = 0;
        pipeSpan = h / 4;//管子的间距
        pipeTopHeight1 = h / 4;
        pipeTopHeight2 = h / 3;//直接设置的高
        getReady.setX((w - getReady.getWidth()) / 2);
        getReady.setY(h / 3);
        tap.setX((w - tap.getWidth()) / 2);
        tap.setY(h / 2);
        bird.setX(w / 4);
        bird.setY(h / 2);
        bird.setySpeed(2);
        pipeTop1.setX(w);
        pipeTop1.setY(0);
        pipeBottom1.setX(w);
        pipeBottom1.setY(pipeTopHeight1 + pipeSpan);
        pipeTop2.setX(3 * w / 2);
        pipeTop2.setY(0);
        pipeBottom2.setX(3 * w / 2);
        pipeBottom2.setY(pipeTopHeight2 + pipeSpan);
        gameOver.setX((w - gameOver.getWidth()) / 2);
        gameOver.setY(h / 4);
        panel.setX((w - panel.getWidth()) / 2);
        panel.setY((h - panel.getHeight()) / 2);
    }

    /**
     * 创建游戏的精灵
     */
    private void initSpirits() {
        Bitmap bm_bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg1);
        bg = new Spirit(bm_bg);
        Bitmap bm_floor = BitmapFactory.decodeResource(context.getResources(), R.drawable.floor);
        floor = new Spirit(bm_floor);
        Bitmap bm_ready = BitmapFactory.decodeResource(context.getResources(), R.drawable.getready);
        getReady = new Spirit(bm_ready);
        Bitmap bm_tap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tap);
        tap = new Spirit(bm_tap);
        int[] birds = new int[]{
                R.drawable.bird_1,
                R.drawable.bird_2,
                R.drawable.bird_3
        };
        Bitmap[] bm_birds = new Bitmap[birds.length];
        for (int i = 0; i < birds.length; i++) {
            Bitmap bm_bird = BitmapFactory.decodeResource(context.getResources(), birds[i]);
            bm_birds[i] = bm_bird;
        }
        bird = new Spirit(bm_birds);
        pipeTopBm = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe1);
        pipeTop1 = new Spirit(pipeTopBm);
        pipeBottomBm = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe2);
        pipeBottom1 = new Spirit(pipeBottomBm);
        pipeTop2 = pipeTop1.getCloneObject();
        pipeBottom2 = pipeBottom1.getCloneObject();
        Bitmap bm_over = BitmapFactory.decodeResource(context.getResources(), R.drawable.gameover);
        gameOver = new Spirit(bm_over);
        Bitmap bm_panel = BitmapFactory.decodeResource(context.getResources(), R.drawable.panel);
        panel = new Spirit(bm_panel);

        mScorePaint = new Paint();
        mScorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        mScorePaint.setColor(Color.WHITE);
        mScorePaint.setAntiAlias(true);
        mScorePaint.setTextSize(context.getResources().getDimension(R.dimen.sp_25));
        mScorePaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        //执行绘画的任务
        while (isStart) {
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    //真正可以开始执行绘画了
//					canvas.drawText("今天准备做一个游戏", 100, 100, paint);
//					bird.drawSelf(canvas);//画鸟
                    drawGameView(canvas);
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);//解锁画布
                }
            }
        }

    }

    /**
     * 通过游戏状态绘制不同的界面
     *
     * @param canvas
     * @author Danny 未婚 qq:
     */
    private void drawGameView(Canvas canvas) {
        //先绘制各个状态都需要的精灵
        //地图和地板
        canvas.drawBitmap(bg.getBitmap(), null, new Rect(0, 0, w, h), paint);
        canvas.drawBitmap(floor.getBitmap(), null, new Rect(0, 3 * h / 4, w, h), paint);
        switch (gameStatu) {
            case READY:
                drawGameReadyView(canvas);
                break;
            case RUNNING:
                drawGameRunningView(canvas);
                break;
            case OVER:
                drawGameOverView(canvas);
                break;
            default:
                break;
        }

    }

    private void drawGameOverView(Canvas canvas) {
        //游戏结束
        gameOver.drawSelf(canvas);
        panel.drawSelf(canvas);
    }

    private void drawGameRunningView(Canvas canvas) {
        //绘制游戏运行的视图
        //绘制管子，先得让管子运动
        //第一对管子
        if (!isDrop) {
            pipeTop1.setX(pipeTop1.getX() + PIPE_SPEED);
            pipeTop1.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight() - pipeTopHeight1, pipeTopBm.getWidth(), pipeTopHeight1));

            pipeBottom1.setX(pipeTop1.getX());
            pipeBottom1.setY(pipeTopHeight1 + pipeSpan);
            pipeBottom1.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h / 2 - pipeTopHeight1));

            //第二对管子
            pipeTop2.setX(pipeTop2.getX() + PIPE_SPEED);
            pipeTop2.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight() - pipeTopHeight2, pipeTopBm.getWidth(), pipeTopHeight2));

            pipeBottom2.setX(pipeTop2.getX());
            pipeBottom2.setY(pipeTopHeight2 + pipeSpan);
            pipeBottom2.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h / 2 - pipeTopHeight2));
        }

        //设置鸟的位置
        bird.setySpeed(bird.getySpeed() + 1.3f);//加速度
        bird.setY(bird.getY() + bird.getySpeed());
        if (bird.getY() <= 0 || birdHitPipe(pipeTop1) || birdHitPipe(pipeBottom1) || birdHitPipe(pipeTop2) || birdHitPipe(pipeBottom2)) {
            //鸟撞晕了
            isDrop = true;
        }

        if (bird.getY() + bird.getHeight() >= 3 * h / 4) {
            gameStatu = OVER;
            bird.setY(3 * h / 4 - bird.getHeight());
        }
        //管子出界了以后让管子再回到右边
        chceckOutBound(pipeTop1, true);
        chceckOutBound(pipeTop2, false);
        /**
         * 判断鸟是否穿过了管子
         */
        int pipe_right1 = (int) (pipeTop1.getX() + pipeTop1.getWidth());
        int pipe_right2 = (int) (pipeTop2.getX() + pipeTop2.getWidth());
        if (pipe_right1 < bird.getX() && bird.getX() - pipe_right1 <= (-PIPE_SPEED)) {
            score++;
        }
        if (pipe_right2 < bird.getX() && bird.getX() - pipe_right2 <= (-PIPE_SPEED)) {
            score++;
        }



        //绘制管子
        pipeTop1.drawSelf(canvas);
        pipeBottom1.drawSelf(canvas);
        pipeTop2.drawSelf(canvas);
        pipeBottom2.drawSelf(canvas);
        //红烧麻雀
        //画鸟
        bird.drawSelf(canvas);

        //画积分
        drawScore(score,canvas);
    }

    private void drawScore(int score,Canvas canvas) {
        int centerXOfText = w / 2;
        int centerYOfText = (int) (h / 5 - (mScorePaint.descent() + mScorePaint.ascent()) / 2);
        canvas.drawText(score+"",centerXOfText,centerYOfText,mScorePaint);
    }

    /**
     * 碰撞检测
     *
     * @return
     */
    private boolean birdHitPipe(Spirit pipe) {
        if (bird.getSpiritRect().intersect(pipe.getSpiritRect())) {
            return true;
        }
        return false;
    }

    private void chceckOutBound(Spirit pipe, boolean isFirst) {
        if (pipe.getX() + pipe.getWidth() <= 0) {
            //管子出去了
            pipe.setX(w);//回到右变
            if (isFirst) {
                pipeTopHeight1 = (int) (h * (Math.random() * 2 + 1) / 8);
            } else {
                pipeTopHeight2 = (int) (h * (new Random().nextFloat() * 2 + 1) / 8);
            }
        }
    }

    private void drawGameReadyView(Canvas canvas) {
        getReady.drawSelf(canvas);//绘制ready
        tap.drawSelf(canvas);//绘制tap视图
        bird.drawSelf(canvas);//画鸟
    }
}

