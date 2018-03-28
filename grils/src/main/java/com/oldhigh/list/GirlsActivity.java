package com.oldhigh.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.oldhigh.libres.RouterModule;

/**
 * Created by oldhigh on 2018/3/25.
 */

@Route(path = RouterModule.MODULE_GIRLS)
public class GirlsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView view = new TextView(this);
        setContentView(view);
        view.setTextSize(44);
        view.setText("3333333333333");


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(GirlsActivity.this, "asdsaas", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build(RouterModule.MODULE_NEWS).navigation();
            }
        });
    }
}
