package com.example.strangedemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.strangedemo.R;


/**
 * author: MaGua
 * create on:2021/6/29 10:00
 * description 可以触摸移动吸附的View，且不与子组件点击事件冲突
 */
@SuppressLint("AppCompatCustomView")
public class ScrollFloatinigButton extends FrameLayout {
    private static final String TAG = "ScrollFloatinigButton";
    private float mX;
    private float mY;
    private int mParentWidth;
    private int mParentHeight;
    private boolean mScrollEnable = true;
    private int mScrollLeft;
    private int mScrollTop;
    private int mRight;
    private int mScrollRight;
    private int mScrollBottom;
    private boolean hasScroll;
    boolean isScroll = false;
    private boolean isAdsorb = true;

    public ScrollFloatinigButton(Context context) {
        this(context, null);
    }

    public ScrollFloatinigButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollFloatinigButton(Context context, AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ScrollFloatinigView);
        mScrollEnable = ta.getBoolean(R.styleable.ScrollFloatinigView_scrollEnable, true);
        ta.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow(); //防止布局重置时重置ScrollFloatinigView的位置
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) getParent()).addOnLayoutChangeListener(new OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (hasScroll && mScrollRight != 0 && mScrollBottom != 0)
                        layout(mScrollLeft, mScrollTop, mScrollRight, mScrollBottom);
                }
            });
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getParent() instanceof ViewGroup) {
            mParentWidth = ((ViewGroup) getParent()).getWidth();
            mParentHeight = ((ViewGroup) getParent()).getHeight();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mScrollEnable) return super.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mX = ev.getX();
                mY = ev.getY();
                super.onTouchEvent(ev);
                return true;
            case MotionEvent.ACTION_MOVE:
                int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
                float x = ev.getX();
                float y = ev.getY();
                x = x - mX;
                y = y - mY;
                if (Math.abs(scaledTouchSlop) < Math.abs(x) || Math.abs(scaledTouchSlop) < Math.abs(y)) {
                    isScroll = true;
                }
                if (isScroll) {
                    mScrollLeft = (int) (getX() + x);
                    mScrollTop = (int) (getY() + y);
                    mScrollRight = (int) (getX() + getWidth() + x);
                    mScrollBottom = (int) (getY() + getHeight() + y);
                    if (mScrollLeft < 0 || mScrollRight > mParentWidth) {
                        mScrollLeft = (int) getX();
                        mScrollRight = (int) getX() + getWidth();
                    }
                    if (mScrollTop < 0 || mScrollBottom > mParentHeight) {
                        mScrollTop = (int) getY();
                        mScrollBottom = (int) getY() + getHeight();
                    }
                    layout(mScrollLeft, mScrollTop, mScrollRight, mScrollBottom);
                    hasScroll = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isScroll) {
                    isScroll = false;
                    setPressed(false);
                    if (isAdsorb) {
                        int mind = getScreenWidth() / 2;
                        //获取控件宽度的中间值
                        int viewWithMind = getWidth() / 2;
                        if (mScrollLeft + viewWithMind > mind) {
                            mScrollRight = getScreenWidth();
                            mScrollLeft = getScreenWidth() - getWidth();
                        } else {
                            mScrollRight = getWidth();
                            mScrollLeft = 0;
                        }
                        layout(mScrollLeft, mScrollTop, mScrollRight, mScrollBottom);
                    }
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isAdsorb() {
        return isAdsorb;
    }

    public void setAdsorb(boolean adsorb) {
        isAdsorb = adsorb;
    }

    /**
     * 得到屏幕宽度 * * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private boolean mScrolling;
    private float touchDownX;
    private float touchDownY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownX = event.getX();
                touchDownY = event.getY();
                mScrolling = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(touchDownX - event.getX()) >= ViewConfiguration.get(getContext()).getScaledTouchSlop()||Math.abs(touchDownY - event.getY()) >= ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    mScrolling = true;
                } else {
                    mScrolling = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                mScrolling = false;
                break;
        }
        return mScrolling;
    }

}
