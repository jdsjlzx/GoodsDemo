package com.jiangjieqiang.goodsdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.jiangjieqiang.goodsdemo.R;

/**
 * Created by jiangjieqiang on 16/2/21.
 */
public class GoodsLayout extends LinearLayout{

    private View top1;
    private View top2;
    private ScrollView descLayout;

    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMaxiumVelocity,mMinimumVelocity;

    private boolean isTopArriveBelow = false;  //topView是否到达底部
    private boolean mDragging = false;
    private boolean isInControl = false;

    private float mLastY;
    private int mTopViewHeight;

    public GoodsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);

        mScroller = new OverScroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaxiumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        top1 = findViewById(R.id.id_top1);
        top2 = findViewById(R.id.id_top2);
        descLayout = (ScrollView)findViewById(R.id.id_good_desc);
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        mTopViewHeight = top1.getMeasuredHeight() + top2.getMeasuredHeight();
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float y = ev.getY();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mLastY;






        }


        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        float y = ev.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mLastY;
                //view2隐藏或者
                //topView隐藏，&&view2到达屏幕顶端，&&用户往下滑动&&滑动速度大于最小滑动速度，layout拦截事件
                if (descLayout.  ||
                        ())





        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        initVelocityTracker();
        mVelocityTracker.addMovement(event);
        int action = event.getAction();
        float y = event.getY();

//        switch (action){
//            case MotionEvent.ACTION_DOWN:
//                if (!mScroller.isFinished()){
//                    mScroller.abortAnimation();
//                }
//                mLastY = y;
//                return true;
//
//            case MotionEvent.ACTION_MOVE:
//                float dy = y - mLastY;
//
//                if (!mDragging && Math.abs(dy) > mTouchSlop){
//                    mDragging = true;
//                }
//                //拖拽
//                if (mDragging){
//
//                    if (getScrollY() < mTopViewHeight){
//                        scrollBy(0 , (int)-dy);
//                    }
//
//                    //如果topView隐藏，且往上滑动的时候，改变当前事件为ACTION_DOWN
//                    if (getScrollY() == mTopViewHeight && dy < 0){
//                        event.setAction(MotionEvent.ACTION_DOWN);
//                        dispatchTouchEvent(event);
//                        isInControl = false;
//                    }
//                    mLastY = y;
//
//                }
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                mDragging = false;
//                recycleVelocityTracker();
//                if (!mScroller.isFinished()){
//                    mScroller.abortAnimation();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                mDragging = false;
//                mVelocityTracker.computeCurrentVelocity(1000,mMaxiumVelocity);
//                int velocityY = (int)mVelocityTracker.getYVelocity();
//
//                //当y方向的滑动速度大于最小滑动速度的时候，&&topView到达底部，&&向上滑，才会快速滑动到topView消失
//                //当y方向当滑动速度大于最小滑动速度当时候，&&topView消失，&&向下滑，才会快速滑动到topView显示
//                //当view2隐藏的时候，layout拦截滑动事件
//                //当topView隐藏，&&view2到达屏幕顶端，&&用户往下滑动&&滑动速度大于最小滑动速度，layout拦截滑动事件
//
//                if (Math.abs(velocityY) > mMinimumVelocity){
//                    fling(-velocityY);
//                }
//                recycleVelocityTracker();
//                break;
//        }
        return super.onTouchEvent(event);
    }

    public void fling(int velocityY){
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0){
            y = 0;
        }
        if (y > mTopViewHeight){
            y = mTopViewHeight;
        }
        if (y != getScrollY()){
            super.scrollTo(x, y);
        }

    }

    private void initVelocityTracker() {
        if (mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker(){
        if (mVelocityTracker != null){
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }
}
