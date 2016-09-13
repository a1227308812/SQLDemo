package umeng.mysqllitedemo.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ZWP on 2016/8/26.
 * Explain:
 */
public class MyRecycleView extends RecyclerView {
    public MyRecycleView(Context context) {
        super(context);
        initView();
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    private void initView() {


    }


    //触摸监听事件针对于每一个Item的监听）
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch(e.getAction()){

            case MotionEvent.ACTION_DOWN://按下

            break;
            case MotionEvent.ACTION_MOVE://划动

            break;
            case MotionEvent.ACTION_UP://抬起

            break;

        }

        return super.onTouchEvent(e);
    }
}
