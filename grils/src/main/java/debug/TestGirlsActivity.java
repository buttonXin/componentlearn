package debug;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.oldhigh.list.GirlsActivity;

/**
 * 组件开发下，用于传递 跳转 等， 继承模式下无效！！！ 在gradle中配置了
 * @author oldhigh
 * @date 2018/3/25
 */

public class TestGirlsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView view = new TextView(this);
        setContentView(view);

        view.setTextSize(44);
        view.setText("test girl 333333");


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getApplicationContext() , GirlsActivity.class));
            }
        });

    }
}
