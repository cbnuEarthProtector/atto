package com.example.atto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MypageScrapActivity extends AppCompatActivity {

    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_list_page);

        //뒤로가기 버튼 -> 홈 화면으로 이동
        backbtn=(ImageButton) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }
        });

        //스크랩 관리 프레임
        Intent intent= new Intent(getApplicationContext(),ScrapListActivity.class);
        startActivity(intent);
    }
}