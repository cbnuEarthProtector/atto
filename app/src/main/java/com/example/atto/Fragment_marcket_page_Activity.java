package com.example.atto;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;

import java.util.List;

public class Fragment_marcket_page_Activity extends Fragment {
    private LinearLayout lineartable;
    ImageButton mybtn;
    Button categorybtn, brandbtn;

    public Fragment_marcket_page_Activity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.activity_fragment_marcket_page, container, false);
        //getXmlData(); // network 동작, 인터넷에서 xml을 받아오는 코드

        //마이페이지 버튼 ->마이페이지로 이동
        mybtn = (ImageButton) fv.findViewById(R.id.mybtn);
        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fragment에서 intent 호출할 땐 getActivity()사용
                Intent intent = new Intent(getActivity(), MypageActivity.class);
                startActivity(intent);
            }
        });

        categorybtn = (Button) fv.findViewById(R.id.categorybtn);
        brandbtn = (Button) fv.findViewById(R.id.brandbtn);
        //카테고리 별 상품보기 fragment 호출
        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categorybtn.setTextColor(getResources().getColorStateList(R.color.mainGreen));
                brandbtn.setTextColor(getResources().getColorStateList(R.color.black));
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment_marcket_category categoryFragment = new Fragment_marcket_category();
                transaction.replace(R.id.marcketframe, categoryFragment);
                transaction.commit();
            }
        });

        //브랜드별 상품보기 fragment 호출
        brandbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brandbtn.setTextColor(getResources().getColorStateList(R.color.mainGreen));
                categorybtn.setTextColor(getResources().getColorStateList(R.color.black));
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment_marcket_brand brandFragment = new Fragment_marcket_brand();
                transaction.replace(R.id.marcketframe, brandFragment);
                transaction.commit();
            }
        });

        //인기상품 출력
        lineartable=(LinearLayout)fv.findViewById(R.id.lineartable);

        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        ProductDao productDao = appDatabase.productDao();

        List<ProductWithBrandName> productWithBrandNameList = productDao.getAll();

        LinearLayout horlinear=new LinearLayout(getActivity().getApplicationContext());
        for (ProductWithBrandName productWithBrandName : productWithBrandNameList) {
            //상품 정보 vertical layout으로 출력
            LinearLayout linearLayout= new LinearLayout(getActivity().getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(70,10,30,20);  //마진 수정
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
}