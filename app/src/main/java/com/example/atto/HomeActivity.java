package com.example.atto;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {

    Button marcketbtn, restaurantbtn, scrapbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        marcketbtn = (Button) findViewById(R.id.marcketbtn);
        restaurantbtn = (Button) findViewById(R.id.restaurantbtn);
        scrapbtn = (Button) findViewById(R.id.scrapbtn);

        marcketbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));
        //기본 프레임 -> 인기 상품 표시 프레임
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment_marcket_page_Activity marcketFragment = new Fragment_marcket_page_Activity();
            transaction.replace(R.id.frame, marcketFragment);
            transaction.commit();
        }

        marcketbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marcketbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));
                restaurantbtn.setBackgroundColor(Color.parseColor("#f1f3f4"));
                scrapbtn.setBackgroundColor(Color.parseColor("#f1f3f4"));

                for(Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if(fragment instanceof Fragment_marcket_page_Activity && fragment.isVisible())
                        return;
                    if(fragment.isVisible())
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Fragment_marcket_page_Activity marcketFragment = new Fragment_marcket_page_Activity();
                transaction.replace(R.id.frame, marcketFragment);
                transaction.commit();
            }
        });

        restaurantbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marcketbtn.setBackgroundColor(Color.parseColor("#f1f3f4"));
                restaurantbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));
                scrapbtn.setBackgroundColor(Color.parseColor("#f1f3f4"));

                for(Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if(fragment instanceof Fragment_restaurant_page_Activity && fragment.isVisible())
                        return;
                    if(fragment.isVisible())
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Fragment_restaurant_page_Activity restaurantFragment = new Fragment_restaurant_page_Activity();
                transaction.replace(R.id.frame, restaurantFragment);
                transaction.commit();

            }
        });

        scrapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marcketbtn.setBackgroundColor(Color.parseColor("#f1f3f4"));
                restaurantbtn.setBackgroundColor(Color.parseColor("#f1f3f4"));
                scrapbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));

                for(Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if(fragment instanceof Fragment_myscrap_page_Activity && fragment.isVisible())
                        return;
                    if(fragment.isVisible())
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Fragment_myscrap_page_Activity myscrapFragment = new Fragment_myscrap_page_Activity();
                transaction.replace(R.id.frame, myscrapFragment);
                transaction.commit();
            }
        });

        String str= getIntent().getStringExtra("productDetailPage");
        if (str != null) {
            if(str.equals("scrap")){
                marcketbtn.setBackgroundColor(Color.parseColor("#f1f3f4"));
                restaurantbtn.setBackgroundColor(Color.parseColor("#f1f3f4"));
                scrapbtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainGreen));

                for(Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if(fragment instanceof Fragment_myscrap_page_Activity && fragment.isVisible())
                        return;
                    if(fragment.isVisible())
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Fragment_myscrap_page_Activity myscrapFragment = new Fragment_myscrap_page_Activity();
                transaction.replace(R.id.frame, myscrapFragment);
                transaction.commit();
            }
        }
    }

}