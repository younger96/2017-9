package com.animcricleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mButtonBegin,mButtonEnd;
    private AnimCircle mAnimCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAnimCircle = (AnimCircle) findViewById(R.id.anim_circle);
        mButtonBegin = (Button) findViewById(R.id.btn_begin);
        mButtonBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimCircle.begin();
            }
        });
        mButtonEnd = (Button) findViewById(R.id.btn_end);
        mButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
