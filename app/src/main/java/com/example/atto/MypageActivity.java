package com.example.atto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MypageActivity extends AppCompatActivity {

    ImageButton backbtn;
    Button managescrapbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_list_page);

        //뒤로가기 버튼 ->메인페이지로
        backbtn=(ImageButton) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        //스크랩 관리 버튼 -> 스크랩 관리 페이지로
        managescrapbtn=(Button) findViewById(R.id.managescrapbtn);
        managescrapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ScrapListActivity.class);
                startActivity(intent);
            }
        });
    }
}