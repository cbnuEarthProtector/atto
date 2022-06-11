package com.example.atto;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;

import java.util.List;

public class Fragment_marcket_page_Activity extends Fragment {
    private LinearLayout lineartable;
    private FrameLayout marketframe;
    private ScrollView scrollView;
    Button categorybtn, brandbtn, goupbtn;

    private ViewPager2 sliderViewPager;
    private LinearLayout layoutIndicator;

    private String[] images = new String[] {
            "https://postfiles.pstatic.net/MjAyMDA4MTBfNDQg/MDAxNTk3MDU0NjcwNTYx.wvwPx6D_uqmEyMintb6tJkHI_cqOpddR2xryuDHu_Isg.FulNX1_0XbiOyxQ1zc1cIR640enh3ymV4Q35zvFIcyMg.PNG.tlswlgprjt/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2020-08-10_%EC%98%A4%ED%9B%84_6.28.36.png?type=w966",
            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA4MTBfMjY5%2FMDAxNTk3MDQ5OTkwMDQ1.gcJmG_7aCy9tHgr5wAbR_b8NbzAkfugPCIn1Jnl_Iigg.53tCYShI4Mv2S6nYfDNlh3xDuJphiftys0eFn_p18YYg.JPEG.gggrin_a%2F20200809_231034.jpg&type=sc960_832",
            "https://postfiles.pstatic.net/MjAyMDA4MTBfMjI5/MDAxNTk3MDU2MDY2ODM0.B4aiZsr90JtUNjFqGXK4zCCjdXE8RTsIMRBkv4Q7dRYg.Gcih9844E9k09vWoFQrNjEjgaW2idn20TSeAPVADWUgg.PNG.tlswlgprjt/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2020-08-10_%EC%98%A4%ED%9B%84_6.28.05.png?type=w966",
            "https://postfiles.pstatic.net/MjAyMDA4MTBfMTgz/MDAxNTk3MDU3MTM2ODI5.kSKKTpvnrrif_g3TlwNdRgFgRw9kRTmue4OfBvn5WmYg.w_0gHWSYLiuo-SBl8E9YHYUYzjI2GDXjJvZFr5ZmxhAg.PNG.tlswlgprjt/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2020-08-10_%EC%98%A4%ED%9B%84_7.57.43.png?type=w966",
            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA4MTBfMTIg%2FMDAxNTk3MDUwNDgyNzkx.MzVD3tPlWDmYgaXBiX4byt2olhuxNIuR71CrQdsinX8g.ucQsgQc8Fnmey7s8yQdyxYqTTYroqwIBBPyhv6PBCp0g.JPEG.gggrin_a%2F%25C0%25DA%25BF%25AC%25C0%25AF%25B7%25A1%25BF%25F8%25B7%25E1_%25282%2529.jpg&type=sc960_832"
    };

    public Fragment_marcket_page_Activity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.activity_fragment_marcket_page, container, false);
        //getXmlData(); // network 동작, 인터넷에서 xml을 받아오는 코드

        marketframe=(FrameLayout)fv.findViewById(R.id.marketframe);
        scrollView=(ScrollView)fv.findViewById(R.id.scrollView);
        goupbtn=(Button)fv.findViewById(R.id.go_up);
        goupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_UP);
                    }
                });
            }
        });
        categorybtn = (Button) fv.findViewById(R.id.categorybtn);
        brandbtn = (Button) fv.findViewById(R.id.brandbtn);
        //카테고리 별 상품보기 fragment 호출
        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams frameparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3000);
                marketframe.setLayoutParams(frameparams);

                categorybtn.setTextColor(getResources().getColorStateList(R.color.mainGreen));
                brandbtn.setTextColor(getResources().getColorStateList(R.color.black));
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment_marcket_category categoryFragment = new Fragment_marcket_category();
                transaction.replace(R.id.marketframe, categoryFragment);
                transaction.commit();
            }
        });

        //브랜드별 상품보기 fragment 호출
        brandbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams frameparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
                marketframe.setLayoutParams(frameparams);

                brandbtn.setTextColor(getResources().getColorStateList(R.color.mainGreen));
                categorybtn.setTextColor(getResources().getColorStateList(R.color.black));
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment_marcket_brand brandFragment = new Fragment_marcket_brand();
                transaction.replace(R.id.marketframe, brandFragment);
                transaction.commit();

            }
        });

        //슬라이드 배너
        sliderViewPager = fv.findViewById(R.id.sliderViewPager);
        layoutIndicator = fv.findViewById(R.id.layoutIndicators);

        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.setAdapter(new ImageSliderAdapter(getActivity().getApplicationContext(), images));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.length);

        //인기상품 출력
        lineartable=(LinearLayout)fv.findViewById(R.id.lineartable);

        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        ProductDao productDao = appDatabase.productDao();

        List<ProductWithBrandName> productWithBrandNameList = productDao.getAll();

        LinearLayout horlinear=new LinearLayout(getActivity().getApplicationContext());

        for (ProductWithBrandName productWithBrandName : productWithBrandNameList) {
            //상품 정보 vertical layout으로 출력
            LinearLayout linearLayout= new LinearLayout(getActivity().getApplicationContext());
            linearLayout.setPadding(60, 0, 20, 70);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(params);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Productdetail_page_Activity.class);
                    intent.putExtra("id", productWithBrandName.id);
                    startActivity(intent);
                }
            });

            //상품 사진
            ImageView imageView= new ImageView(getActivity().getApplicationContext());
            String image_url = productWithBrandName.photoURL;
            Glide.with(this).load(image_url).into(imageView);
            imageView.setColorFilter(Color.parseColor("#f1f3f4"), PorterDuff.Mode.DST_OVER);
            linearLayout.addView(imageView);

            //상품 카테고리
            TextView textView2 = new TextView(getActivity().getApplicationContext());
            textView2.setText("["+productWithBrandName.category+"]");  //카테고리
            textView2.setGravity(Gravity.LEFT);
            textView2.setTextSize(14);
            textView2.setPadding(30, 20, 0, 0);  //패딩
            linearLayout.addView(textView2);

            //상품 정보
            TextView textView = new TextView(getActivity().getApplicationContext());
            textView.setText(productWithBrandName.name);  //이름
            textView.setGravity(Gravity.LEFT);
            textView.setMaxLines(2);  //두 줄 출력
            textView.setEms(9);  //한 줄에 글자 수
            textView.setEllipsize(TextUtils.TruncateAt.END);  //말줄임표
            textView.setTextSize(14);
            textView.setPadding(30, 0, 0, 0);  //패딩
            linearLayout.addView(textView);

            TextView textView3 = new TextView(getActivity().getApplicationContext());
            if (productWithBrandName.price == -1) textView3.setText("품절");
            else {  //가격 출력
                int thwon =productWithBrandName.price/1000;
                int onewon=productWithBrandName.price%1000;
                if (onewon == 0) {
                    textView3.setText(thwon + ",000 원");
                } else {
                    textView3.setText(thwon+","+onewon+" 원");
                }
            }
            textView3.setGravity(Gravity.LEFT);
            textView3.setTextSize(14);
            textView3.setPadding(30, 40, 0, 0);  //패딩
            linearLayout.addView(textView3);

            //한 줄에 상품 두 개씩 출력
            if (productWithBrandName.id % 2 == 0) {
                horlinear = new LinearLayout(getActivity().getApplicationContext());
                lineartable.addView(horlinear);

                horlinear.addView(linearLayout);
            } else {
                horlinear.addView(linearLayout);
            }
        }

        return fv;
    }

    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getActivity().getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getActivity().getApplicationContext(),
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getActivity().getApplicationContext(),
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }
}