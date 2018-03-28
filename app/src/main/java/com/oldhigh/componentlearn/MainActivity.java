package com.oldhigh.componentlearn;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.oldhigh.componentlearn.ui.ShaderHelper;
import com.oldhigh.componentlearn.ui.ShaderView;
import com.oldhigh.libres.RouterModule;
import com.oldhigh.libres.util.L;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mRoot_content;

    private ShaderView mShaderView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.text_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext() , GirlsActivity.class));

                ARouter.getInstance().build(RouterModule.MODULE_GIRLS).navigation();
            }
        });

        mRoot_content = findViewById(R.id.root_content);
        mImageView = findViewById(R.id.image);


        mShaderView = new ShaderView(this);


        mRoot_content.addView(mShaderView , -1 , -1);


        mShaderView.setTextColor(Color.BLACK);
        mShaderView.setText("准备提交的mac集合21准备提交的mac集合21");

        mShaderView.start();

    }
}
