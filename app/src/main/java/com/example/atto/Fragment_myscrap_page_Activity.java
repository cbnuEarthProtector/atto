package com.example.atto;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.atto.database.AppDatabase;
import com.example.atto.database.Product;
import com.example.atto.database.ProductBookmark;
import com.example.atto.database.ProductBookmarkDao;
import com.example.atto.database.ProductDao;
import com.example.atto.database.ProductWithBrandName;
import com.example.atto.database.UserDatabase;

import java.util.List;

public class Fragment_myscrap_page_Activity extends Fragment {
    private LinearLayout linearLayout;
    private Dialog_scrap_popup dialog_scrap_popup;

    private ScrollView scrollView;
    Button goupbtn;

    public Fragment_myscrap_page_Activity(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.activity_fragment_myscrap_page, container, false);

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

        linearLayout = fv.findViewById(R.id.linearScrap);

        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        ProductDao productDao = appDatabase.productDao();
        UserDatabase userDatabase = UserDatabase.getInstance(getActivity().getApplicationContext());
        ProductBookmarkDao productBookmarkDao = userDatabase.productBookmarkDao();
        List<ProductBookmark> productBookmarks = productBookmarkDao.getAll();
        for(ProductBookmark productBookmark : productBookmarks) {
            ProductWithBrandName product = productDao.findById(productBookmark.productId);
            LinearLayout linearScrapElement = new LinearLayout(getActivity().getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(70,40,30,40);
            linearScrapElement.setLayoutParams(params);
            linearScrapElement.setOrientation(LinearLayout.VERTICAL);
            linearScrapElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 스크랩 pop up 페이지
                    String price = Integer.toString(product.price) ;
                    dialog_scrap_popup = new Dialog_scrap_popup( getActivity(), product.id, product.photoURL, product.brandName, product.name, price, productBookmark.memo);
                    dialog_scrap_popup.show();
                }
            });

            //상품 정보 레이아웃
            LinearLayout hlinear= new LinearLayout(getActivity().getApplicationContext());
            LinearLayout.LayoutParams hparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            hlinear.setLayoutParams(hparams);
            linearScrapElement.addView(hlinear);

            //상품 사진
            ImageView imageView= new ImageView(getActivity().getApplicationContext());
            String image_url = product.photoURL;
            Glide.with(this).load(image_url).into(imageView);
            LinearLayout.LayoutParams imageparams= new LinearLayout.LayoutParams(300, 300);
            imageView.setLayoutParams(imageparams);
            imageView.setColorFilter(Color.parseColor("#f1f3f4"), PorterDuff.Mode.DST_OVER);
            hlinear.addView(imageView);

            //상품 정보 레이아웃
            LinearLayout vlinear= new LinearLayout(getActivity().getApplicationContext());
            LinearLayout.LayoutParams vparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            vlinear.setLayoutParams(vparams);
            vlinear.setPadding(60,0,0,0);
            vlinear.setOrientation(LinearLayout.VERTICAL);
            hlinear.addView(vlinear);

            //상품 카테고리
            TextView textView2 = new TextView(getActivity().getApplicationContext());
            textView2.setText("["+product.category+"]");  //카테고리
            textView2.setGravity(Gravity.LEFT);
            textView2.setTextSize(18);
            vlinear.addView(textView2);

            //상품 정보
            TextView textView = new TextView(getActivity().getApplicationContext());
            textView.setText(product.name);  //이름
            textView.setGravity(Gravity.LEFT);
            textView.setEms(10);  //한 줄에 글자 수
            textView.setTextSize(18);
            vlinear.addView(textView);

            //가격
            TextView textView3 = new TextView(getActivity().getApplicationContext());
            if (product.price == -1) textView3.setText("품절");
            else {  //가격 출력
                int thwon =product.price/1000;
                int onewon=product.price%1000;
                if (onewon == 0) {
                    textView3.setText(thwon + ",000 원");
                } else {
                    textView3.setText(thwon+","+onewon+" 원");
                }
            }
            textView3.setGravity(Gravity.LEFT);
            textView3.setPadding(0, 10, 0, 0);
            textView3.setTextSize(18);
            vlinear.addView(textView3);


            //상품 메모
            TextView productMemo = new TextView(getActivity().getApplicationContext());
            productMemo.setText(productBookmark.memo);
            productMemo.setGravity(Gravity.LEFT);
            productMemo.setMaxLines(2);  // 두 줄 출력
            productMemo.setEms(20);  // 한 줄에 글자 수
            productMemo.setEllipsize(TextUtils.TruncateAt.END); // 말줄임표
            productMemo.setTextSize(18);
            productMemo.setPadding(0, 30, 0, 0);
            linearScrapElement.addView(productMemo);


            linearLayout.addView(linearScrapElement);

            //수평선
            View view= new View(getActivity().getApplicationContext());
            LinearLayout.LayoutParams viewparam= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
            view.setLayoutParams(viewparam);
            view.setBackgroundColor(Color.parseColor("#f1f3f4"));
            view.setPadding(0,30,0, 30);
            linearLayout.addView(view);
        }
        return fv;
    }
}