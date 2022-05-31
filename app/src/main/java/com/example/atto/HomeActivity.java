package com.example.atto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {

    Button marcketbtn, restaurantbtn, scrapbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //기본 프레임 -> 인기 상품 표시 프레임
        {
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            Fragment_marcket_page_Activity marcketFragment = new Fragment_marcket_page_Activity();
            transaction.replace(R.id.frame, marcketFragment);
            transaction.commit();
        }

        marcketbtn=(Button) findViewById(R.id.marcketbtn);
        marcketbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Fragment_marcket_page_Activity marcketFragment = new Fragment_marcket_page_Activity();
                transaction.replace(R.id.frame, marcketFragment);
                transaction.commit();
            }
        });

        restaurantbtn=(Button) findViewById(R.id.restaurantbtn);
        restaurantbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Fragment_restaurant_page_Activity restaurantFragment = new Fragment_restaurant_page_Activity();
                transaction.replace(R.id.frame, restaurantFragment);
                transaction.commit();
            }
        });

        scrapbtn=(Button) findViewById(R.id.scrapbtn);
        scrapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Fragment_myscrap_page_Activity myscrapFragment = new Fragment_myscrap_page_Activity();
                transaction.replace(R.id.frame, myscrapFragment);
                transaction.commit();
            }
        });
    }
}