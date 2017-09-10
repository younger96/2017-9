package com.tickview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    CheckView mCheckView;
    private Button mButtonBegin,mButtonEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mCheckView = new CheckView(this);

        mCheckView = (CheckView) findViewById(R.id.check_view);
        mButtonBegin = (Button) findViewById(R.id.btn_begin);
        mButtonBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheckView.check();
            }
        });
        mButtonEnd = (Button) findViewById(R.id.btn_end);
        mButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheckView.unCheck();
            }
        });
    }
}
