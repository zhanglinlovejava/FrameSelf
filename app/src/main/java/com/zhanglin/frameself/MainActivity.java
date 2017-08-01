package com.zhanglin.frameself;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanglin.frameself.inject.OnClick;
import com.zhanglin.frameself.inject.StringInject;
import com.zhanglin.frameself.inject.ViewInject;
import com.zhanglin.frameself.inject.ViewUtilsTest;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.tvTestInject)
    TextView textView;

    @StringInject(R.string.app_name)
    String log;

    @OnClick(R.id.tvTestInject)
    void click(View view) {
        Toast.makeText(this, ((TextView) view).getText().toString(), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtilsTest.inject(this);
        textView.setText(log);
    }
}
