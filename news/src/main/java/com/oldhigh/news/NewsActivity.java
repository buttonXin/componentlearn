package com.oldhigh.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.oldhigh.libres.BaseActivity;
import com.oldhigh.libres.RouterModule;

/**
 * Created by oldhigh on 2018/3/26.
 */

@Route(path = RouterModule.MODULE_NEWS)
public class NewsActivity extends BaseActivity{


    private TextView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new TextView(this);
        setContentView(mView);
        mView.setText("news");
    }
}
