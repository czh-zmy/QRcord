package com.example.myapplication;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class ActivityDenglu extends AppCompatActivity {
    //声明
    private Button btn_qr;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        //关联
        init();
    }
    /*关联*/
    private void init() {
        btn_qr=findViewById(R.id.btn_qr);
    }
    /*点击识别二维码*/
    public void getQnumber(View view){
        // 创建IntentIntegrator对象
        IntentIntegrator intentIntegrator = new IntentIntegrator(ActivityDenglu.this);
        intentIntegrator.setCaptureActivity(CustomCaptureActivity.class);
        intentIntegrator.setPrompt("请将二维码放置取景框扫描");
        intentIntegrator.initiateScan();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取解析结果
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "取消扫描", Toast.LENGTH_LONG).show();
            } else {
                Intent intent=new Intent(ActivityDenglu.this,NextActivity.class);
                intent.putExtra("name",result.getContents());
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}



