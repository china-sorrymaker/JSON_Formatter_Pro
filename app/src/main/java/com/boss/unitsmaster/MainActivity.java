package com.boss.unitsmaster;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 确保对应你的布局文件名

        // 1. 初始化广告（地基逻辑）
        MobileAds.initialize(this, status -> {});
        AdView mAdView = findViewById(R.id.adView);
        if (mAdView != null) {
            mAdView.loadAd(new AdRequest.Builder().build());
        }

        // 2. JSON 格式化业务逻辑
        EditText etInput = findViewById(R.id.etInput);
        Button btnFormat = findViewById(R.id.btnFormat);
        TextView tvOutput = findViewById(R.id.tvOutput);
        Button btnClear = findViewById(R.id.btnClear); // 增加一个清空按钮，好用度+1

        btnFormat.setOnClickListener(v -> {
            String input = etInput.getText().toString().trim();
            if (input.isEmpty()) return;
            
            try {
                // 核心：利用 Android 原生 JSON 库实现 4 空格缩进
                if (input.startsWith("[")) {
                    JSONArray array = new JSONArray(input);
                    tvOutput.setText(array.toString(4));
                } else {
                    JSONObject object = new JSONObject(input);
                    tvOutput.setText(object.toString(4));
                }
            } catch (Exception e) {
                tvOutput.setText("❌ JSON 格式错误，请检查！\n错误信息: " + e.getMessage());
            }
        });

        if (btnClear != null) {
            btnClear.setOnClickListener(v -> {
                etInput.setText("");
                tvOutput.setText("");
            });
        }
    }
}
