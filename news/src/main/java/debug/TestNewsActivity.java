package debug;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.oldhigh.libres.BaseActivity;

/**
 * Created by oldhigh on 2018/3/26.
 */

public class TestNewsActivity extends BaseActivity {

    private TextView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new TextView(this);
        setContentView(mView);
        mView.setText("test news act");
    }
}
