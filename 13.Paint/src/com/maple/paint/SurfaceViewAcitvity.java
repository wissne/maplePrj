package com.maple.paint;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.view.SurfaceHolder.Callback;


public class SurfaceViewAcitvity extends Activity {

    MyView mAnimView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ȫ����ʾ����
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ǿ��Ϊ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // ���� SCREEN_ORIENTATION_LANDSCAPE 
        // ��ʾ�Զ������ϷView
        mAnimView = new MyView(this);
        setContentView(mAnimView);
    }

    public class MyView extends SurfaceView implements Callback,Runnable {

         /**ÿ50֡ˢ��һ����Ļ**/  
        public static final int TIME_IN_FRAME = 50; 

        /** ��Ϸ���� **/
        Paint mPaint = null;
        Paint mTextPaint = null;
        SurfaceHolder mSurfaceHolder = null;

        /** ������Ϸ����ѭ�� **/
        boolean mRunning = false;

        /** ��Ϸ���� **/
        Canvas mCanvas = null;

        /**������Ϸѭ��**/
        boolean mIsRunning = false;
        
        /**���߷���**/
        private Path mPath;
        
        private float mposX, mposY;
        
        public MyView(Context context) {
            super(context);
            /** ���õ�ǰViewӵ�п��ƽ��� **/
            this.setFocusable(true);
            /** ���õ�ǰViewӵ�д����¼� **/
            this.setFocusableInTouchMode(true);
            /** �õ�SurfaceHolder���� **/
            mSurfaceHolder = this.getHolder();
            /** ��mSurfaceHolder��ӵ�Callback�ص������� **/
            mSurfaceHolder.addCallback(this);
            /** �������� **/
            mCanvas = new Canvas();
            /** �������߻��� **/
            mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            /**���û��ʿ����**/
            mPaint.setAntiAlias(true);
            /**���ʵ�����**/
            mPaint.setStyle(Paint.Style.STROKE);
            /**���û��ʱ�ΪԲ��״**/
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            /**�����ߵĿ��**/
            mPaint.setStrokeWidth(5);
            /**����·������**/
            mPath = new Path();
            /** �������ֻ��� **/
            mTextPaint = new Paint();
            /**������ɫ**/
            mTextPaint.setColor(Color.BLACK);
            /**�������ִ�С**/
            mTextPaint.setTextSize(15);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            /** �õ�������״̬ **/
            int action = event.getAction();
            float x = event.getX();
            float y = event.getY();
            switch (action) {
            // �������µ��¼�
            case MotionEvent.ACTION_DOWN:
                /**�������߹켣��� X Y����**/
                mPath.moveTo(x, y);
                break;
            // �����ƶ����¼�
            case MotionEvent.ACTION_MOVE:
                /**�������߹켣**/
                //����1 ��ʼ��X����
                //����2 ��ʼ��Y����
                //����3 ������X����
                //����4 ������Y����
                mPath.quadTo(mposX, mposY, x, y);
                break;
            // ����̧����¼�
            case MotionEvent.ACTION_UP:
                /**����̧������·���켣**/
                mPath.reset();
                break;
            }
           //��¼��ǰ����X Y����
            mposX = x;
            mposY = y;
            return true;
        }
        
        private void Draw() {
            /**��ջ���**/
            mCanvas.drawColor(Color.WHITE);
            /**��������**/
            mCanvas.drawPath(mPath, mPaint);
        
            /**��¼��ǰ����λ��**/
            mCanvas.drawText("��ǰ���� X��" + mposX, 0, 20,mTextPaint);
            mCanvas.drawText("��ǰ���� Y��" + mposY, 0, 40,mTextPaint);
        }
        
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            /**��ʼ��Ϸ��ѭ���߳�**/
            mIsRunning = true;
            new Thread(this).start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mIsRunning = false;
        }

        @Override
        public void run() {
            while (mIsRunning) {

                /** ȡ�ø�����Ϸ֮ǰ��ʱ�� **/
                long startTime = System.currentTimeMillis();

                /** ����������̰߳�ȫ�� **/
                synchronized (mSurfaceHolder) {
                    /** �õ���ǰ���� Ȼ������ **/
                    mCanvas = mSurfaceHolder.lockCanvas();
                    Draw();
                    /** ���ƽ����������ʾ����Ļ�� **/
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                }

                /** ȡ�ø�����Ϸ������ʱ�� **/
                long endTime = System.currentTimeMillis();

                /** �������Ϸһ�θ��µĺ����� **/
                int diffTime = (int) (endTime - startTime);

                /** ȷ��ÿ�θ���ʱ��Ϊ50֡ **/
                while (diffTime <= TIME_IN_FRAME) {
                    diffTime = (int) (System.currentTimeMillis() - startTime);
                    /** �̵߳ȴ� **/
                    Thread.yield();
                }

            }

        }
    }
}