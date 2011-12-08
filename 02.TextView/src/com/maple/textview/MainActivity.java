package com.maple.textview;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView tv = new TextView(this);
//        tv.setText("你好2!");
//        setContentView(tv);
        TextView tv = (TextView) findViewById(R.id.txtHello);
        tv.setText(Html.fromHtml("欢迎大家收看<font color='blue'>《从零开始学Android开发》</font>系列课程"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
