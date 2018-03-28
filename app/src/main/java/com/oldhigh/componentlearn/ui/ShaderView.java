package com.oldhigh.componentlearn.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.oldhigh.libres.util.L;

/**
 * Created by oldhigh on 2018/3/27.
 */

public class ShaderView extends android.support.v7.widget.AppCompatTextView {

    //着色器的配置
    private  LinearGradient shader;
    //字体的颜色
    private  int mCurrentTextColor;
    //着色的矩阵
    private Matrix shaderMatrix;
    //动画开始 结束 标记 来进行着色器的添加
    private boolean isShimmering;
    //动画标记
    private float count;
    //动画执行的时长 默认10秒
    private long mDuration = 8_000;
    //动画的循环次数 默认5次 -1 表示无限
    private int mRepeatCount = 5;

    private ObjectAnimator mAnimator;

    public ShaderView(Context context) {
        super(context);



    }


    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
        postInvalidate();
    }

    public boolean isShimmering() {
        return isShimmering;
    }

    /**
     *  设置是否进行着色器的动画操作
     * @param shimmering
     */
    public void setShimmering(boolean shimmering) {
        isShimmering = shimmering;
    }






    /**
     * 初始化 矩阵 和 着色器 ，开始动画操作 ， 开始 和 结束 会开始绘制操作
     */
    public void start(){

        post(new Runnable() {
            @Override
            public void run() {

                ObjectAnimator.ofFloat(this ,"" , 0 , 1);
                startShaderAnim();
            }
        });
    }

    private void startShaderAnim() {
        mCurrentTextColor = getCurrentTextColor();
        shaderMatrix = new Matrix();

        shader = new LinearGradient(-  getWidth(), 0, 0, 0,
                new int[]{
                        mCurrentTextColor,
                        Color.RED,
                        mCurrentTextColor,
                },
                new float[]{
                        0,
                        0.5f,
                        1
                },
                Shader.TileMode.CLAMP
        );


        setShimmering(true);

        mAnimator = ObjectAnimator.ofFloat(this ,
                "count" , 0  , getWidth() )
                .setDuration(mDuration);

        mAnimator.setRepeatCount(-1);
        mAnimator.start();
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                setShimmering(false);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isShimmering) {

            if (getPaint().getShader() == null) {
                getPaint().setShader(shader);
            }
            // translate shader accordingly to maskX maskY positions
            // maskY is affected by the offset to vertically center the wave
            shaderMatrix.setTranslate( 2 * count, 0);

            // assign matrix to invalidate the shader
            shader.setLocalMatrix(shaderMatrix);

        } else {
            getPaint().setShader(null);
        }

        super.onDraw(canvas);
    }

    /**
     * 动画进行结束
     */
    public void cancel(){
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }
}
