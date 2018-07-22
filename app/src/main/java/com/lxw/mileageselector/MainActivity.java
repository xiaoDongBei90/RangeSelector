package com.lxw.mileageselector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MileageSelector ms = findViewById(R.id.ms);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ms.setMileageValue(38000);
            }
        });
        ms.setOnRangeChangedListener(new MileageSelector.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(int mileage) {
                Log.d("mileage", String.format("%s----width-----s", mileage));
            }
        });
    }
}
