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
        setContentView(R.layout.activity_mypage_scrap);

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
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        Fragment_myscrap_page_Activity myscrapFragment = new Fragment_myscrap_page_Activity();
        transaction.replace(R.id.scrapframe, myscrapFragment);
        transaction.commit();
    }
}